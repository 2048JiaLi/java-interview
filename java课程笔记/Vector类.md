**2020 / 05 / 29**

## Vector使用详解
Vector类可实现自动增长的对象数组。

> java.util.vector提供了向量类(vector)以实现类似动态数组的功能。在Java语言中没有指针的概念，但如果正确灵活地使用指针又确实可以大大提高程序的质量。比如在c,c++中所谓的“动态数组”一般都由指针来实现。为了弥补这个缺点，Java提供了丰富的类库来方便编程者使用，vector类便是其中之一。事实上，灵活使用数组也可以完成向量类的功能，但向量类中提供大量的方法大大方便了用户的使用。
> 
> 创建了一个向量类的对象后，可以往其中**随意插入不同类的对象**，即**不需顾及类型也不需预先选定向量的容量**，并可以方便地进行查找。

### Vector 主要用在事先不知道数组的大小，或者只是需要一个可以改变大小的数组的情况。支持 4 种构造方法。

- 1.创建一个默认的向量，默认大小为 10：
```java
Vector();
```

- 2.创建指定大小的向量。
```java
Vector(int size);
```

- 3.创建指定大小的向量，并且增量用 incr 指定。增量表示向量每次增加的元素数目。
```java
Vector(int size,int incr);
```

- 4.创建一个包含集合c元素的向量：
```java
Vector(Collection c);
```


### Vector 还定义了以下方法
```java
import java.util.*;
public class VetorDemo {
    public static void main(String[] args){
        Vector v = new Vector(initialCapacity:3,capacityIncrement:2);
        System.out.println("Initial size :"+v.size());  // Initial size :0
        System.out.println("Initial capacity :"+v.capacity());  //Initial capacity :3
        
        //添加元素
        v.addElement(1); 
        v.addElement(2);
        v.addElement(5);
        System.out.println("Current capacity :"+v.capacity());  // Current capacity :3
        v.addElement(6);
        // 向量空间不足，自动增加参数capacityIncrement大小
        System.out.println("Current capacity :"+v.capacity());  // Current capacity :5

        System.out.println("First element: " + v.firstElement()); // First element: 1
        System.out.println("Last element: " + v.lastElement());  // Last element: 6

        // 输出打印方式 -- 枚举
        Enumeration vEnum = v.elements();
        while (vEnum.hasMoreElements()){
            System.out.println(vEnum.nextElement());
        }

        // 输出打印方式 -- 迭代
        Iterator it = v.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

}
```


