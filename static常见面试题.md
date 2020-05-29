# static块
构造方法用于对象的初始化。静态初始化块，用于类的初始化操作。

在静态初始化块中不能直接访问非staic成员。

# static块的作用
静态初始化块的作用就是：提升程序性能
> 很多时候会将一些只需要进行一次的初始化操作都放在static代码块中进行
```java
class Person{
    private Date birthDate;
    private static Date startDate,endDate;
    static{
        startDate = Date.valueOf("1946");
        endDate = Date.valueOf("1964");
    }

    public Person(Date birthDate) {
        this.birthDate = birthDate;
    }

    boolean isBornBoomer() {
        return birthDate.compareTo(startDate)>=0 && birthDate.compareTo(endDate) < 0;
    }
}
```
> 静态初始化块可以置于类中的任何地方，类中可以有多个静态初始化块。
>
> 在类初次被加载时，会按照静态初始化块的顺序来执行每个块，并且只会执行一次。

# static是不允许用来修饰局部变量。(Java语法的规定)


# [试题](https://blog.csdn.net/kuangay/java/article/details/81485324)
## 1.下面这段代码的输出结果是什么？
```java
public class Test extends Base{

    static{
        System.out.println("test static");
    }

    public Test(){
        System.out.println("test constructor");
    }

    public static void main(String[] args) {
        new Test();
    }
}

class Base{

    static{
        System.out.println("base static");
    }

    public Base(){
        System.out.println("base constructor");
    }
}
```
输出结果为：
```java
base static
test static
base constructor
test constructor
```

>>> 执行过程
>>> 1.找到main方法入口，main方法是程序入口，但在执行main方法之前，要先加载Test类
>>> 
>>> 2.加载Test类的时候，发现Test类继承Base类，于是先去加载Base类
>>> 
>>> 3.加载Base类的时候，发现Base类有static块，而是先执行static块，输出base static结果
>>> 
>>> 4.Base类加载完成后，再去加载Test类，发现Test类也有static块，而是执行Test类中的static块，输出test static结果
>>> 
>>> 5.Base类和Test类加载完成后，然后执行main方法中的new Test()，调用子类构造器之前会先调用父类构造器
>>> 
>>> 6.调用父类构造器，输出base constructor结果
>>> 
>>> 7.然后再调用子类构造器，输出test constructor结果

## 2.这段代码的输出结果是什么？
```java
public class Test {
    Person person = new Person("Test");
    static{
        System.out.println("test static");
    }

    public Test() {
        System.out.println("test constructor");
    }

    public static void main(String[] args) {
        new MyClass();
    }
}

class Person{
    static{
        System.out.println("person static");
    }
    public Person(String str) {
        System.out.println("person "+str);
    }
}


class MyClass extends Test {
    Person person = new Person("MyClass");
    static{
        System.out.println("myclass static");
    }

    public MyClass() {
        System.out.println("myclass constructor");
    }
}
```
输出结果为：
```java
test static
myclass static
person static
person Test
test constructor
person MyClass
myclass constructor
```

>>> 1.找到main方法入口，main方法是程序入口，但在执行main方法之前，要先加载Test类
>>> 
>>> 2.加载Test类的时候，发现Test类有static块，而是先执行static块，输出test static结果
>>> 
>>> 3.然后执行new MyClass(),执行此代码之前，先加载MyClass类，发现MyClass类继承Test类，而是要先加载Test类，Test类之前已加载
>>> 
>>> 4.加载MyClass类，发现MyClass类有static块，而是先执行static块，输出myclass static结果
>>> 
>>> 5.然后调用MyClass类的构造器生成对象，在生成对象前，需要先初始化父类Test的成员变量，而是执行Person person = new Person("Test")代码，发现Person类没有加载
>>> 
>>> 6.加载Person类，发现Person类有static块，而是先执行static块，输出person static结果
>>> 
>>> 7.接着执行Person构造器，输出person Test结果
>>> 
>>> 8.然后调用父类Test构造器，输出test constructor结果，这样就完成了父类Test的初始化了
>>> 
>>> 9.再初始化MyClass类成员变量，执行Person构造器，输出person MyClass结果
>>> 
>>> 10.最后调用MyClass类构造器，输出myclass constructor结果，这样就完成了MyClass类的初始化了

## 3、这段代码的输出结果是什么？
```java
public class Test {

    static{
        System.out.println("test static 1");
    }
    public static void main(String[] args) {

    }

    static{
        System.out.println("test static 2");
    }
}
```

输出结果为：
```
test static 1
test static 2
```
> 虽然在main方法中没有任何语句，但是还是会输出，原因上面已经讲述过了。另外，static块可以出现类中的任何地方（只要不是方法内部，记住，任何方法内部都不行），并且执行是按照static块的顺序执行的。