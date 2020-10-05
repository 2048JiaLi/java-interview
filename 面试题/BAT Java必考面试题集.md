## 1、面向对象的特征有哪些方面? (抽象、继承、封装、多态)
> 三大特性指的是继承、封装、多态

- 抽象：将一类对象的共同特征总结出来构造类的过程，包括数据抽象和行为抽象。其只关注对象有哪些属性和行为，并不去关注这些行为的细节

- 继承：继承是从已有类得到继承信息创建新类的过程。提供继承信息的类被称为父类（超类、基类）；得到继承信息的类被称为子类（派生类）
> 语法： `class 子类 extends 父类`  //定义子类时，显示继承父类
> java为单继承，一个类只能有一个直接父类，但可以多级继承，属性和方法逐级叠加

- 封装：封装是把数据和操作数据的方法绑定起来（`private`），对数据的访问只能通过已定义的接口，从而尽可能隐藏对象的内部实现细节，控制对象的修改及访问权限。
> 封装就是隐藏一切可隐藏的东西，只向外界提供最简单的编程接口（可以想想普通洗衣机和全自动洗衣机的差别，明显全自动洗衣机封装更好因此操作起来更简单；我们现在使用的智能手机也是封装得足够好的，因为几个按键就搞定了所有的事情）。

- 多态：指允许不同子类型的对象对同一消息作出不同的响应。即，用同样的对象引用调用同样的方法但是做了不同的事情。

多态性分为编译时的多态性和运行时的多态性。方法重载（ove
rload）实现的是编译时的多态性（也称为前绑定），而方法重写（override）实现的是运行时的多态性（也称为后绑定）。

运行时的多态是面向对象最精髓的，要实现多态需要做两件事：1. 方法重写（子类继承父类并重写父类中已有的或抽象的方法）；2. 对象造型（用父类型引用引用子类型对象，这样同样的引用调用同样的方法就会根据子类对象的不同而表现出不同的行为）。


## 2、float f=3.4;是否正确?
答:不正确。3.4 是双精度数，将双精度型（double）赋值给浮点型（float）属于下转型（down-casting，也称为窄化）会造成精度损失，因此需要强制类型转换`float f =(float)3.4;` 或者写成`float f =3.4F;`。
> 注：在java里面，没小数点的默认是int,有小数点的默认是 double;


## 3、作用域public,private,protected,以及不写时default的区别

| 作用域| 	当前类|	同包|	子孙类|	其他|
|-|-|-|-|-|
|public| √| √| √| √|
|protected| √|	   √|    √| ×|
|default| √| √| ×| ×|
|private| √| ×| ×| ×|

## 4、Java 有没有goto? 
goto 是java 中的保留字，现在没有在java 中使用。

## 5、int 和Integer 有什么区别? 
答：Java 提供两种不同的类型：引用类型和基本类型（或内置类型）；
`int` 是java 的基本数据类型，`Integer` 是java 为`int` 提供的封装类。

Java 为每个原始类型提供了封装类：
- 原始类型: `boolean,char,byte,short,int,long,float,double`
- 封装类型：`Boolean，Character，Byte，Short，Integer，Long，Float，Double`

引用类型和原始类型的行为完全不同，并且它们具有不同的语义。引用类型和原始类型具有不同的特征和用法，它们包括：大小和速度问题，这种类型以哪种类型的数据结构存储，当引用类型和原始类型用作某个类的实例数据时所指定的缺省值。对象引用实例变量的缺省值为null，而原始类型实例变量的缺省值与它们的类型有关。

## 6、&和&&的区别？
答：`&`是位运算符，表示按位与运算，`&&`是逻辑运算符，表示逻辑与（and）。 注意`A&&B`条件，若A不成立，B不会进行判断.



答：`&`运算符有两种用法：(1)按位与；(2)逻辑与。`&&`运算符是**短路与运算**。

逻辑与跟短路与的差别是非常巨大的，虽然二者都要求运算符左右两端的布尔值都是 true 整个表达式的值才是 `true`。`&&`之所以称为短路运算是因为，如果`&&`左边的表达式的值是 `false`，右边的表达式会被直接短路掉，不会进行运算。

很多时候我们可能都需要用`&&`而不是`&`，例如在验证用户登录时判定用户名不是`null`而且不是空字符串，应当写为：`username != null &&!username.equals("")`，二者的顺序不能交换，更不能用&运算符，因为第一个条件如果不成立，根本不能进行字符串的`equals`比较，否则会产生`NullPointerException`异常。注意：逻辑或运算符（`|`）和短路或运算符（`||`）的差别也是如此。



## 7、String 和StringBuffer 的区别? 
答：JAVA 平台提供了两个类：`String` 和`StringBuffer`，它们可以储存和操作字符串，即包含多个字符的字符数据。这个`String `类提供了数值**不可改变**的字符串。而这个StringBuffer 类提供的**字符串进行修改**。当你知道字符数据要改变的时候你就可以使用StringBuffer。典型地，你可以使用StringBuffer 来动态构造字符数据。

## 8、描述一下JVM 加载class 文件的原理机制? 
答：JVM 中类的装载是由ClassLoader 和它的子类来实现的,Java ClassLoader是一个重要的Java 运行时系统组件。它负责在运行时查找和装入类文件的类。

> https://mp.weixin.qq.com/s/lVdCsIuQ6UAvOhaAlXrWuA

## 9、Java有没有goto
goto 是Java中的保留字，在目前版本的Java中没有使用。（根据James Gosling（Java 之父）编写的《The Java Programming Language》一书的附录中给出了一个 Java 关键字列表，其中有 goto 和 const，但是这两个是目前无法使用的关键字，因此有些地方将其称之为保留字，其实保留字这个词应该有更广泛的意义，因为熟悉 C 语言的程序员都知道，在系统类库中使用过的
有特殊意义的单词或单词的组合都被视为保留字）

## 10、包装类的自动装箱拆箱相关
```java
public class Test{

    public static void main(String[] args){

        Integer f1 = 100;  // jdk5之后提供基本类型的自动装箱与拆箱
        Integer f2 = 100;
        Integer f3 = 100;
        Integer f4 = 100;

        System.out.println(f1 == f2);
        System.out.println(f3 == f4);
    }
}
```
输出:
```java
true
false
```

首先需要注意的是f1、 f2、f3、f4四个变量都是 `Integer` 对象，所以下面的==运算比较的不是值而是引用。装箱的本质是什么呢？当我们给一个 `Integer` 对象赋一个`int`值的时候，会调用`Integer` 类的静态方法`valueOf`，如果看看`valueOf` 的源代码就知道发生了什么。

```java
public static Integer valueOf(int i){

    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];

    return new Integer(i);
}
```
`IntegerCache` 是`Integer` 的内部类，其代码如下所示：
```java
    private static class IntegerCache {
        static final int low = -128;
        static final int high;
        static final Integer cache[];

        static {
            // high value may be configured by property
            int h = 127;
            String integerCacheHighPropValue =
                sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
            if (integerCacheHighPropValue != null) {
                try {
                    int i = parseInt(integerCacheHighPropValue);
                    i = Math.max(i, 127);
                    // Maximum array size is Integer.MAX_VALUE
                    h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
                } catch( NumberFormatException nfe) {
                    // If the property cannot be parsed into an int, ignore it.
                }
            }
            high = h;

            cache = new Integer[(high - low) + 1];
            int j = low;
            for(int k = 0; k < cache.length; k++)
                cache[k] = new Integer(j++);

            // range [-128, 127] must be interned (JLS7 5.1.7)
            assert IntegerCache.high >= 127;
        }

        private IntegerCache() {}
    }
```

简单的说，如果字面量的值在-128 到 127 之间，那么不会 new 新的 Integer
对象，而是直接引用常量池中的Integer对象，所以上面的面试题中f1==f2的
结果是true，而 f3==f4的结果是 false。越是貌似简单的面试题其中的玄机就
越多，需要面试者有相当深厚的功力。

## 11、解释内存中的栈（stack）、堆(heap)和静态存储区的用法。

答：通常我们定义一个基本数据类型的变量，一个对象的引用，还有就是函数调用的现场保存都使用内存中的栈空间；而通过`new` 关键字和构造器创建的对象放在堆空间；程序中的字面量（`literal`）如直接书写的`100`、`“hello”`和常量都是放在静态存储区中。栈空间操作最快但是也很小，通常大量的对象都是放在堆空间，**整个内存包括硬盘上的虚拟内存都可以被当成堆空间来使用**。

`String str = new String(“hello”);`

上面的语句中`str` 放在栈上，用`new`创建出来的字符串对象放在堆上，而“hel
lo”这个字面量放在静态存储区。

补充：较新版本的Java中使用了一项叫“逃逸分析“的技术，可以将一些局部
对象放在栈上以提升对象的操作性能。


## 12、Math.round(11.5) 等于多少? Math.round(-11.5)等于多少?
答：`Math.round(11.5)`的返回值是`12`，`Math.round(-11.5)`的返回值是`-11`。四
舍五入的原理是在参数上加0.5然后进行下取整。

>  除法的取整
>
> - **向上取整**：比自己大的最小整数。
>   两个数相除向上取整：**(a + b - 1) / b**。比如：(13 / 3) = 4。向上取整：(13 + 3 - 1) / 3 = 5。
> - **向下取整**：比自己小的最大整数。
>   两个数相除向下取整：**(a / b)**。
> - **四舍五入**：更接近自己的整数。
>   两个数相除四舍五入：**（a + b / 2）/ b**.