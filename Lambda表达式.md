## 为什么要引入Lambda
先回顾一下Java8以前，如果想把某个接口的实现类作为参数传递给一个方法会怎么做？要么创建一个类实现该接口，然后new出一个对象，在调用方法时传递进去，要么使用匿名类，可以精简一些代码。以创建一个线程并打印一行日志为例，使用匿名函数写法如下：

```java
new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("执行");
    }
}).start();
```

在java8以前，使用匿名函数已经算是很简洁的写法了，再来看看使用Lambda表达式，上面的代码会变成什么样子。

```java
new Thread (() -> System.out.println("执行") ).start();
```

我们都知道java是面向对象的编程语言，除了部分简单数据类型，万物皆对象。因此，在Java中定义函数或方法都离不开对象，也就意味着很难直接将方法或函数像参数一样传递，而Java8中的Lambda表达式的出现解决了这个问题。

Lambda表达式使得Java拥有了函数式编程的能力，但在Java中Lambda表达式是对象，它必须依附于一类特别的对象类型——函数式接口(functional interface)，后面详细讲解。



## 什么是lambda表达式

Lambda表达式是Java SE 8中一个重要的新特性。lambda表达式允许你通过表达式来代替功能接口。lambda表达式就和方法一样,它提供了一个正常的参数列表和一个使用这些参数的主体(body,可以是一个表达式或一个代码块)。Lambda表达式还增强了集合库。

Lambda表达式是一种匿名函数(对Java而言这并不完全准确)，通俗的说，它是没有声明的方法，即没有访问修饰符、返回值声明和名字的方法。使用Lambda表达式的好处很明显就是可以使代码变的更加简洁紧凑。

Lambda表达式的使用场景与匿名类的使用场景几乎一致，都是在某个功能（方法）只使用一次的时候。

## 表达式语法

Lambda 表达式在 Java 语言中引入了一个新的语法元素和操作符。这个操作符为 `->`，该操作符被称 为 Lambda 操作符或箭头操作符。它将 Lambda 分为两个部分：

- 左侧：指定了 Lambda 表达式需要的所有参数
- 右侧：指定了 Lambda 体，即 Lambda 表达式要执行的功能。

```java
//没有参数
() -> body

// 1个参数
(param) -> body
// 或
(param) ->{ body; }

// 多个参数
(param1, param2...) -> { body }
// 或
(type1 param1, type2 param2...) -> { body }
```

常见的Lambda表达式如下
```java
// 无参数,返回值为字符串
() -> "abc";

// 1个String参数，直接打印结果
(System.out::println);
// 或
(String s) -> System.out.print(s)

// 1个参数(数字),返回2倍值  
x -> 2 * x;

// 2个参数(数字),返回差值
(x, y) -> x – y
  
// 2个int型整数,返回和值  
(int x, int y) -> x + y
```

总结
- Lambda表达式可以有多个参数
- 参数类型可以显示声明，也可以让编译器从上下文自动推断类型，如`(int)x`与`x`等价
- 多个参数用小括号括起来，逗号分隔，一个参数可以不使用括号
- 没有参数用空括号表示
- 表达式的正文可以包含0条，一条或多条语句，如果有返回值则必须包含返回值语句。如果只有一条可以省略大括号。如果有一条以上则必须包含在大括号中。


## 函数式接口
函数式接口(Functional Interface)是Java8对一类特殊类型的接口的称呼。这类接口只定义了唯一的抽象方法的接口（除了隐含的Object对象的公共方法），因此最开始也就做SAM类型的接口（Single Abstract Method）。

比如上面示例中的java.lang.Runnable就是一种函数式接口，在其内部只定义了一个void run()的抽象方法，同时在该接口上注解了`@FunctionalInterface`。
```java
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
```
`@FunctionalInterface`注解是用来表示该接口要符合函数式接口的规范，除了隐含的Object对象的公共方法以外只可有一个抽象方法。当然，如果某个接口只定义一个抽象方法，不使用该注解也是可以使用Lambda表达式的，但是没有该注解的约束，后期可能会新增其他的抽象方法，导致已经使用Lambda表达式的地方出错。使用`@FunctionalInterface`从编译层面解决了可能的错误。

比如当注解@FunctionalInterface之后，写两个抽象方法在接口内，会出现以下提示：
```java
Multiple non-overriding abstract methods found in interface com.secbro2.lambda.NoParamInterface
```

> 可使用Lambda表达式的接口，只能有一个抽象方法（除了隐含的Object对象的公共方法）。
> 
> 注意此处的方法限制为抽象方法，如果接口内有其他静态方法则不会受限制。


## 方法引用，双冒号操作`类名::方法名`。
如`ClassName::methodName`或者`objectName::methodNam`e的表达式，我们把它叫做方法引用（Method Reference）,通常用在Lambda表达中。

示例：
```java
// 无参数情况
NoParamInterface paramInterface2 = ()-> new HashMap<>();
// 可替换为
NoParamInterface paramInterface1 = HashMap::new;

// 一个参数情况
OneParamInterface oneParamInterface1 = (String string) -> System.out.print(string);
// 可替换为
OneParamInterface oneParamInterface2 = (System.out::println);

// 两个参数情况
Comparator c = (Computer c1, Computer c2) -> c1.getAge().compareTo(c2.getAge());
// 可替换为
Comparator c = (c1, c2) -> c1.getAge().compareTo(c2.getAge());
// 进一步可替换为
Comparator c = Comparator.comparing(Computer::getAge);
```

再如用函数式接口`java.util.function.Function`来实现一个`String`转`Integer`的功能：
```java
Function<String, Integer> function = Integer::parseInt;
Integer num = function.apply("1");
```
根据`Function`接口的定义`Function<T,R>`，其中T表示传入类型，R表示返回类型。具体就是实现了Function的apply方法，在其方法内调用了`Integer.parseInt`方法。


## 1、Runnable线程初始化实例
三种写法：
```java
// 匿名函类写法
new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("正在执行");
    }
}).start();

// lambda表达式写法
new Thread(() -> System.out.println("正在执行")).start();

// lambda表达式 如果方法体内有多行代码需要带大括号
new Thread(() -> {
    System.out.println("正在");
    System.out.println("执行");
}).start();
```

## 2、列表遍历输出示例
传统遍历一个List，基本上都使用for循环来遍历，Java8之后List拥有了forEach方法，可配合lambda表达式写出更加简洁的方法。
```java
List<String> list = Arrays.asList("a","bb","ccc");

// 传统遍历
for(String str : list){
    System.out.println(str);
}

// lambda表达式写法
list.forEach(str -> System.out.println(str));
// lambda表达式写法
list.forEach(System.out::println);
```

## 3、用lambda表达式实现map
```java
    public void mapTest() {
        List<Double> cost = Arrays.asList(10.0, 20.0,30.0);
        cost.stream().map(x -> x + x*0.05).forEach(x -> System.out.println(x));
    }
```
最后的输出结果：

10.5
21.0
31.5

## Java内置四大核心函数式接口

|函数式接口|参数类型|返回类型|用途|
|-|-|-|-|
|消费型接口：`Consumer<T\>`|T|void|对类型为T的对象应用操作，包含方法`void accept(T t);`|
|供给型接口：`Supplier<T\>`|无|T|返回类型为T的对象，包含方法`T get();`|
|函数型接口：`Function<T, R\>`|T|R|对类型为T的对象应用操作，并返回R类型对象结果。`R apply(T t);`|
|断定型接口：`Predicate<T\>`|T|boolean|确定类型T的对象是否满足某约束。`boolean test(T t);`|

### Consumer<T> 消费型接口
```java
void accept(T t);
```

```java
    @Test
    public void test1(){
        hello("张三", (m) -> System.out.println("你好：" + m));
    }
    public void hello(String st, Consumer<String> con){
        con.accept(st);
    }
```

### Function<T, R> 函数型接口
```java
R apply(T t);
```
```java
    //Function<T, R> 函数型接口：
    @Test
    public void test3(){
        String newStr = strHandler("\t\t\t 这是一个函数型接口 ", (str) -> str.trim());
        System.out.println(newStr);
        String subStr = strHandler("这是一个函数型接口", (str) -> str.substring(4, 7));
        System.out.println(subStr);
    }
    //需求：用于处理字符串
    public String strHandler(String str, Function<String, String> fun){
        return fun.apply(str);
    }
```

