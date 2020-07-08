```java
// 题目

public class test01 {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i="+i);
        System.out.println("j="+j);
        System.out.println("k="+k);
    }
}
```

## 分析

#### JVM内存结构

- 一个方法对应一个栈帧结构

<img src="./image/方法栈帧结构.jpg" style="zoom: 60%;" />

#### 1. `int i=1;`

<img src="image/i=1.jpg" style="zoom: 80%;" />

#### 2. `i = i++`

![](image/i=i++.jpg)

> i还是等于1；

#### 3. `j = i++`

![](image/j=i++.jpg)

> j为1；i为2

#### 4. `int k = i + ++i * i++`

![](image/k的赋值.jpg)

#### `i = ++i`

` i` 变量先在局部变量表中进行自增，然后再将` i `进栈，然后再把栈中的数据返回给我们的变量 `i `。