## MySQL如何有效的存储IP地址？
首先声明一下，将 IP地址 以字符串的形式保存在数据库是完全没问题的。

但是除了存字符串以外，我们还有其他的存储方式。例如比较常用的，将 IP地址 存成 int 型的数据，这种存储方式虽然实现起来不复杂，但是能想到该方法，也从一定程度上表明了你是一个善于思考，对数据底层基础把握的比较到位。

因为一个 int 型的数据占 4 个字节，每个字节 8 位，其范围就是 0~(2^8-1)，而 ipv4地址 可以分成4段，每段的范围是 0~255 刚刚好能存下，所以将其稍稍转换，就巧妙的将 IP地址 用最小的空间存在了数据库中（接下来的描述若无特殊说明，则都是指的 ipv4地址）。可能你会觉得这个小小的改变没有什么关系，但是当数据量越来越多的时候，15个字节和4个字节相差的数据量会让你吃惊。所以在设计数据库的时候，字段类型用合适的，够用就行，能省则省。
![](../面试题/image/ip地址int存储.png)

相对字符串存储，使用无符号整数来存储有如下的好处：
- 节省空间，不管是数据存储空间，还是索引存储空间
- 便于使用范围查询（`BETWEEN...AND`），且效率更高

通常，在保存IPv4地址时，一个IPv4最小需要7个字符，最大需要15个字符，所以，使用VARCHAR(15)即可。MySQL在保存变长的字符串时，还需要额外的一个字节来保存此字符串的长度。而如果使用无符号整数来存储，只需要4个字节即可。另外还可以使用4个字段分别存储IPv4中的各部分，但是通常这不管是存储空间和查询效率应该都不是很高（虽然有的场景适合使用这种方式存储）。不过使用无符号整数来存储也有不便于阅读和需要手动转换的缺点。


### 工具类实现转换
而要实现将IP地址存储成 int 型保存在数据库中，一种是通过java代码中的 `移位操作` 和 `&` 计算得到相应的值：
```java
public class Ipv4Covert {
    public static void main(String[] args) {
        String ip = "10.108.149.219";

        // step1: 分解IP字符串，并对应写对字节数组
        byte[] ip1 = ipToBytes(ip);

        // step2: 对字节数组里的每个字节进行左移位处理，分别对应到整型变量的4个字节
        int ip2 = bytesToInt(ip1);
        System.out.println("整型ip ----> " + ip2);

        // step3: 对整型变量进行右位移处理，恢复IP字符串
        String ip3 = intToIp(ip2);
        System.out.println("字符串ip---->" + ip3);

    }

    
    /**
     * 把IP地址转化为int
     * @param ipAddr
     * @return int
     */
    public static byte[] ipToBytesByReg(String ipAddr) {
        byte[] ret = new byte[4];
        try {
            String[] ipArr = ipAddr.split("\\.");
            ret[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
            return ret;
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }

    }



    /**
     * 第一步，把IP地址分解为一个btye数组
     */
    public static byte[] ipToBytes(String ipAddr) {
        // 初始化字节数组，定义长度为4
        byte[] ret = new byte[4];
        try {
            String[] ipArr = ipAddr.split("\\.");
            // 将字符串数组依次写入字节数组
            ret[0] = (byte) (Integer.parseInt(ipArr[0]));
            ret[1] = (byte) (Integer.parseInt(ipArr[1]));
            ret[2] = (byte) (Integer.parseInt(ipArr[2]));
            ret[3] = (byte) (Integer.parseInt(ipArr[3]));
            return ret;
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid IP : " + ipAddr);
        }
    }

    /**
     * 根据位运算把 byte[] -> int
     * 原理：将每个字节强制转化为8位二进制码，然后依次左移8位，对应到Int变量的4个字节中
     */
    public static int bytesToInt(byte[] bytes) {
        // 先移位后直接强转的同时指定位数
        int addr = bytes[3] & 0xFF;
        addr |= ((bytes[2] << 8) & 0xFF00);
        addr |= ((bytes[1] << 16) & 0xFF0000);
        addr |= ((bytes[0] << 24) & 0xFF000000);
        return addr;
    }

    /**
     * 把int->string地址
     *
     * @param ipInt
     * @return String
     */
    public static String intToIp(int ipInt) {
        // 先强转二进制，再进行移位处理
        return new StringBuilder()
                // 右移3个字节（24位），得到IP地址的第一段也就是byte[0],为了防止符号位是1也就是负数，最后再一次& 0xFF
                .append(((ipInt & 0xFF000000) >> 24) & 0xFF).append('.')
                .append((ipInt & 0xFF0000) >> 16).append('.')
                .append((ipInt & 0xFF00) >> 8).append('.')
                .append((ipInt & 0xFF))
                .toString();
    }
}
```
> 这是一种二进制的思维，也是计算技术中广泛采用的一种数制，虽然平时用的不多，但是熟练掌握后，有助于加强我们对机器语言的理解和提升我们的编码水平，特别是面对资源紧张（运存）的场景时，有助于我们分析和优化问题。

### 数据库函数实现转换
通过数据库自带的函数 `INET_ATON` 和 `INET_NTOA` 进行转化：
```sql
mysql> SELECT INET_ATON('192.168.0.1');
+--------------------------+
| INET_ATON('192.168.0.1') |
+--------------------------+
|               3232235521 |
+--------------------------+
1 row in set

mysql> SELECT INET_NTOA(3232235521);  
+-----------------------+
| INET_NTOA(3232235521) |
+-----------------------+
| 192.168.0.1           |
+-----------------------+
1 row in set
```

如果是 IPv6地址 的话，则使用函数 `INET6_ATON` 和 `INET6_NTOA` 进行转化：
```sql
mysql> SELECT HEX(INET6_ATON('1030::C9B4:FF12:48AA:1A2B'));

+----------------------------------------------+
| HEX(INET6_ATON('1030::C9B4:FF12:48AA:1A2B')) |
+----------------------------------------------+
| 1030000000000000C9B4FF1248AA1A2B             |
+----------------------------------------------+
1 row in set

mysql> SELECT INET6_NTOA(UNHEX('1030000000000000C9B4FF1248AA1A2B')); 
+-------------------------------------------------------+
| INET6_NTOA(UNHEX('1030000000000000C9B4FF1248AA1A2B')) |
+-------------------------------------------------------+
| 1030::c9b4:ff12:48aa:1a2b                             |
+-------------------------------------------------------+
1 row in set
```

然后数据库定义为 varbinary 类型，分配 128bits 空间（因为 ipv6采用的是128bits，16个字节）；或者定义为 char 类型，分配 32bits 空间。

用数据库的函数的话，只需要在 IP地址 入库时，稍稍做一下转换即可，方便快捷；而且到了这里，你不觉得将 IP地址 转换成数字存储是一种不错的选择么，毕竟数据库都帮我们考虑到了这一点，也间接性的证明了这的确是一种可行、并且不错的存储方式。