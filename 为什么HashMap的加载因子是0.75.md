> https://mp.weixin.qq.com/s/8z5D3didzf48rk8jd58MoA

## 为什么HashMap需要加载因子？

HashMap的底层是哈希表，是存储键值对的结构类型，它需要通过一定的计算才可以确定数据在哈希表中的存储位置：
```java
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}

// AbstractMap
public int hashCode() {
     int h = 0;
     Iterator<Entry<K,V>> i = entrySet().iterator();
     while (i.hasNext())
         h += i.next().hashCode();

     return h;
}
```

HashMap就是一个插入慢、查询快的数据结构。

但这种数据结构容易产生两种问题：

1、哈希碰撞

2、如果为了避免发生哈希冲突，增大数组容量，就会导致空间利用率不高。

- 加载因子就是表示Hash表中元素的填满程度:
`加载因子 = 填入表中的元素个数 / 散列表的长度`

加载因子越大，填满的元素越多，空间利用率越高，但发生冲突的机会变大了；

加载因子越小，填满的元素越少，冲突发生的机会减小，但空间浪费了更多了，而且还会提高扩容rehash操作的次数。

影响查找效率的因素主要有这几种：

- 散列函数是否可以将哈希表中的数据均匀地散列？

- 怎么处理冲突？

- 哈希表的加载因子怎么选择？

## 处理冲突
1. 开放定址法
`h = (hash(key) + di) mod m;`其中`i=1,2,…,k(k<=m-1)`

hash(key)为哈希函数，m为哈希表表长，di为增量序列，i为已发生冲突的次数。其中，开放定址法根据步长不同可以分为3种：

- 线性探查法（Linear Probing）：di = 1,2,3,…,m-1

以当前冲突位置为起点，步长为1循环查找，直到找到一个空的位置，如果循环完了都占不到位置，就说明容器已经满了。

- 平方探测法（Quadratic Probing）：`di = ±1*2, ±2*2，±3*2，…，±k*2（k≤m/2）`

步长为`di = i*2`来循环查找，直到找到空的位置。

- 伪随机探测法：di = 伪随机数序列：取随机数来作为步长

>缺点：
>
> 当冲突多的时候数据容易堆集在一起，这时候对查找不友好；
>
> 删除结点的时候不能简单将结点的空间置空，否则将截断在它填入散列表之后的同义词结点查找路径。因此如果要删除结点，只能在被删结点上添加删除标记，而不能真正删除结点；
>
> 如果哈希表的空间已经满了，还需要建立一个溢出表，来存入多出来的元素。

2、再哈希法：hash簇

> 增加了计算时间。

3、建立一个公共溢出区
假设哈希函数的值域为`[0, m-1]`，设向量`HashTable[0,…,m-1]`为基本表，每个分量存放一个记录，另外还设置了向量`OverTable[0,…,v]`为溢出表。基本表中存储的是关键字的记录，一旦发生冲突，不管他们哈希函数得到的哈希地址是什么，都填入溢出表。

> 查找冲突数据的时候，需要遍历溢出表才能得到数据。

4、链地址法（拉链法）
以链表的形式保存冲突元素

> 优点 
>
> 1.处理冲突的方式简单，且无堆集现象，非同义词绝不会发生冲突，因此平均查找长度较短；
>
> 2.由于拉链法中各链表上的结点空间是动态申请的，所以它更适合造表前无法确定表长的情况；
>
> 3.删除结点操作易于实现，只要简单地删除链表上的相应的结点即可。

> 缺点
> 
> 额外的存储空间

从HashMap的底层结构中我们可以看到，HashMap采用是数组+链表/红黑树的组合来作为底层结构，也就是开放地址法+链地址法的方式来实现HashMap。
> JDK1.8的时候使用红黑树

## 为什么HashMap加载因子一定是0.75？而不是0.8，0.6？

HashMap的底层其实也是哈希表（散列表），而解决冲突的方式是链地址法。HashMap的初始容量大小默认是16，为了减少冲突发生的概率，当HashMap的数组长度到达一个临界值的时候，就会触发扩容，把所有元素rehash之后再放在扩容后的容器中，这是一个相当耗时的操作。

而这个临界值就是由加载因子和当前容器的容量大小来确定的：

`临界值 = DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR`

即默认情况下是`16x0.75=12`时，就会触发扩容操作。

> 0.75的设置原理与泊松分布：适用于描述单位时间内随机事件发生的次数的概率分布。

HashMap的源码中有这么一段注释：
```java
* Ideally, under random hashCodes, the frequency of
* nodes in bins follows a Poisson distribution
* (http://en.wikipedia.org/wiki/Poisson_distribution) with a
* parameter of about 0.5 on average for the default resizing
* threshold of 0.75, although with a large variance because of
* resizing granularity. Ignoring variance, the expected
* occurrences of list size k are (exp(-0.5) * pow(0.5, k) /
* factorial(k)). The first values are:
* 0:    0.60653066
* 1:    0.30326533
* 2:    0.07581633
* 3:    0.01263606
* 4:    0.00157952
* 5:    0.00015795
* 6:    0.00001316
* 7:    0.00000094
* 8:    0.00000006
* more: less than 1 in ten million
```



常数0.5是作为参数代入泊松分布来计算的，而加载因子0.75是作为一个条件，当HashMap长度为length/size ≥ 0.75时就扩容，在这个条件下，冲突后的拉链长度和概率结果为即为上所示。
> 当一个bin中的链表长度达到8个元素的时候，概率为0.00000006，几乎是一个不可能事件。


HashMap中除了哈希算法之外，有两个参数影响了性能：初始容量和加载因子。初始容量是哈希表在创建时的容量，加载因子是哈希表在其容量自动扩容之前可以达到多满的一种度量。

在维基百科来描述加载因子：

对于开放定址法，加载因子是特别重要因素，应严格限制在0.7-0.8以下。超过0.8，查表时的CPU缓存不命中（cache missing）按照指数曲线上升。因此，一些采用开放定址法的hash库，如Java的系统库限制了加载因子为0.75，超过此值将resize散列表。

在设置初始容量时应该考虑到映射中所需的条目数及其加载因子，以便最大限度地减少扩容rehash操作次数，所以，一般在使用HashMap时建议根据预估值设置初始容量，以便减少扩容操作。

选择0.75作为默认的加载因子，完全是时间和空间成本上寻求的一种折衷选择。