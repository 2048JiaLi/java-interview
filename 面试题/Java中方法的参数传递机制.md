## [Java中方法的参数传递机制](https://www.cnblogs.com/sum-41/p/10799555.html)

> 结论：Java中只有值传递

### 形参与实参

- 形式参数：是在定义函数名和函数体的时候使用的参数，目的是用来接收调用该函数时传入的参数，简称“形参”。

- 实际参数：在主调函数中调用一个函数时，函数名后面括号中的参数称为“实际参数”，简称“实参”。

```java
public class ParamTest {
     public static void main(String[] args) {
        ParamTest pt = new ParamTest();
        // 实际参数为“张三”
        pt.sout("张三");
    }

    public void sout(String name) {
        // 形式参数为 name
        System.out.print(name);
    }   
}
```

### 值传递与引用传递

- 值传递：是指在调用函数时将**实际参数复制**一份传递到函数中，这样在函数中如果**对参数进行修改**，将不会影响到实际参数。

- 引用传递：是指在调用函数时将**实际参数的地址**传递到函数中，那么在函数中**对参数所进行的修改**，将影响到实际参数。

|          |         值传递         |        引用传递        |
| :------: | :--------------------: | :--------------------: |
| 根本区别 |       会创建副本       |      不会创建副本      |
|   所以   | 函数中无法改变原始对象 | 函数中可以改变原始对象 |

> **复制的是参数的引用（地址值），并不是引用指向的存在于堆内存中的实际对象。**

## 理解

> 个人观点

直观上，值传递与引用传递的区别在于方法内对参数的修改，会不会导致实际参数的内容。

Java中，对于基本数据类型，对形参的修改并不会影响实参的值，所以**对于基本类型，必然是值传递方式**

对于引用数据类型，虽然对形参的修改会影响实参，但是考虑到Java中引用类型对象参数保存的是实际对象的地址值，该地址值去指向虚拟机堆中的对象。实际上，对于该引用类型对象，形参传递的就是该地址值，所以自然改变具体内容也会根据该地址，查找堆中对象进行修改。

**但是**，这并不是引用传递的方式。因为，若在方法内部，修改了形参的地址之后再改变值，原实参的值是不会改变的。

如：

```java
public class Test{
    public static void main(String[] args) {
        User user = new User("张三", 18);
        
        System.out.println("pass方法调用前，user= " + user.toString());
        p.pass(user);
        System.out.println("pass方法调用后，user= " + user.toString());
    }
    
    public static pass(User user){
        // 修改形参的地址
        user = new User("李四", 20);
        System.out.println("pass方法中,user = " + user.toString());
    }
}
```

最终输出的结果中，原实参的值是没有发生变化的，这样是满足值传递方式的。

```java
pass方法调用前，user= User{name='张三', age=18}
pass方法中,user = User{name='李四', age=20}
pass方法调用后，user= User{name='张三', age=18}
```

**即，Java中其实还是值传递的，只不过对于引用类型参数，值的内容是对象的引用。**

