## 如何理解`System.out.println()` ?

面向对象编程即创建了对象，所有的事情让对象帮亲力亲为（即对象调用方法）

```java
System.out.println("hello world");
```

```java
hello world

Process finished with exit code 0
```

### System源码：java自定义的类, java.lang
```java
package java.lang;

/**
 * The <code>System</code> class contains several useful class fields
 * and methods. It cannot be instantiated.
 *
 * <p>Among the facilities provided by the <code>System</code> class
 * are standard input, standard output, and error output streams;
 * access to externally defined properties and environment
 * variables; a means of loading files and libraries; and a utility
 * method for quickly copying a portion of an array.
 *
 * @author  unascribed
 * @since   JDK1.0
 */
public final class System {
    //
}
```

### out源码分析
- `out`是`System`里面的一个静态数据成员，而且这个成员是`java.io.PrintStream`类的引用
```java
public final static PrintStream out = null;
```
> out是对象,不更更改（`final static`）。其真实类型是一个静态`PrintStream`对象，静态`static`，所以不需要创建任何东西
> 

- `out`已经存在了且用`Static`修饰了，所以可以直接使用类名+属性名的方式调用，也就是`System.out`。

### println分析
- `println()`就是java.io.PrintStream类的一个方法，用作控制台输出信息
```java
    /**
     * Prints an Object and then terminate the line.  This method calls
     * at first String.valueOf(x) to get the printed object's string value,
     * then behaves as
     * though it invokes <code>{@link #print(String)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>Object</code> to be printed.
     */
    public void println(Object x) {
        String s = String.valueOf(x);
        synchronized (this) {
            print(s);
            newLine();
        }
    }
```
> 将我给你的数据打印到控制台，然后换行

- 很多重载的方法，保证了任意的东西都可以输出
```java
    /**
     * Prints a boolean and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(boolean)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>boolean</code> to be printed
     */
    public void println(boolean x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints a character and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(char)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>char</code> to be printed.
     */
    public void println(char x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints an integer and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(int)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>int</code> to be printed.
     */
    public void println(int x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints a long and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(long)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  a The <code>long</code> to be printed.
     */
    public void println(long x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints a float and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(float)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>float</code> to be printed.
     */
    public void println(float x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints a double and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(double)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>double</code> to be printed.
     */
    public void println(double x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints an array of characters and then terminate the line.  This method
     * behaves as though it invokes <code>{@link #print(char[])}</code> and
     * then <code>{@link #println()}</code>.
     *
     * @param x  an array of chars to print.
     */
    public void println(char x[]) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }

    /**
     * Prints a String and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(String)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>String</code> to be printed.
     */
    public void println(String x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    }
```

> 类调用对象，对象调用方法

## 拓展
- `System.out.print();`与`System.out.println();` 的区别

- 字符数组输出面试案例
```java
public class Demo {

    public static void main(String[] args) {
        char[] ch=new char[]{'x','y'};
        System.out.println(ch);

        char[] ch1=new char[]{'x','y'};
        System.out.println("ch1="+ch1);
    }
}
```
```java
xy
ch1=[C@74a14482
```

这是`println()`方法的重载，java打印输出`System.out.println`会自动调用输入参数的`toString`方法，输出内容时`toString`方法的返回值。

> System.out.println(ch), `println()`自动调用`println(char[] )` 也就是Object类型 所以输出xy
>
> 然而System.out.println(“ch=”+ch) "+"是字符串连接符,自动调用`println(String )`，也就是String类型 输出的是`xxx@xxxx`的形式。