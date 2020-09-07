## Spring中AOP的XML配置

### 业务层接口与实现类

```java
package demo.service;

/**
 * 账户的业务接口
 */
public interface IAccountService {
    /**
     * 模拟
     *
     * 无参+无返回值
     * 有参+无返回值
     * 无参+有返回值
     */
    void saveAccount();

    void updateAccount(int id);

    int deleteAccount();
}
```

```java
package demo.service.impl;

import demo.service.IAccountService;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/9/5 14:49
 */
public class AccountServiceImpl implements IAccountService {

    public void saveAccount() {
        System.out.println("保存账户。。。");
    }

    public void updateAccount(int id) {
        System.out.println("更新账户。。。"+id);
    }

    public int deleteAccount() {
        System.out.println("删除账户。。。");
        return 0;
    }
}
```

### 增强类：使用AOP对基本业务层的增强

> 模拟日志打印

```java
package demo.utils;

/**
 * 记录日志的工具类，提供公共的代码
 */
public class Logger {
    /**
     * 打印日志：在切入点方法执行之前执行（切入点方法就是业务层方法）
     */
    public void printLog(){
        System.out.println("Logger类中开始记录日志。。。");
    }
}
```

### pom.xml依赖定位

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.0.2.RELEASE</version>
    </dependency>

    <!--&lt;!&ndash;解析切入点表达式&ndash;&gt;-->
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.8.2</version>
    </dependency>
</dependencies>
```

### XML配置

```xml
<?xml version = "1.0" encoding = "UTF-8"?>
<beans xmlns = "http://www.springframework.org/schema/beans"
       xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop = "http://www.springframework.org/schema/aop"
       xsi:schemaLocation = "http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans.xsd
   http://www.springframework.org/schema/aop
   http://www.springframework.org/schema/aop/spring-aop.xsd ">

    <!-- 配置spring的IOC,把service对象配置 -->
    <bean id="accountService" class="demo.service.impl.AccountServiceImpl"/>

    <!--spring中基于XML的AOP配置步骤
        1.把通知bean也交给spring管理
        2.使用aop:config标签表明开始AOP的配置
        3.使用aop:aspect标签表明配置切面
                id属性: 给切面提供一个唯一标识
                ref属性:  指定通知类bean的id
        4.在aop:aspect内部使用对应的标签配置通知的类型
                示例是printLog方法在切入点方法之前, 所以是前置通知
                aop:before: 前置通知
                        method属性: 指定Logger类中哪个方法是前置通知
                        pointcut属性: 指定切入点表达式, 该表达式的含义指的是对业务层中哪些方法增强

             切入点表达式的写法:
                关键字: execution(表达式)
                表达式:
                    访问修饰符  返回值  包名.包名....类名.方法名(参数列表)
                标准的表达式写法:
                    public void demo.service.impl.AccountServiceImpl.saveAccount()

                  访问修饰符可以省略 void demo.service.impl.AccountServiceImpl.saveAccount()
                  返回值可以使用通配符,表示任意返回值 * demo.service.impl.AccountServiceImpl.saveAccount()
                  包名可以使用通配符. 但是有几个包,就需要写几个  * *.*.*.AccountServiceImpl.saveAccount()
                  包名可以使用..表示当前包及其子包  * *..AccountServiceImpl.saveAccount()
                  类名和方法名都可以使用通配符      * *..*.saveAccount()
                                                    * *..*.*()
                  参数列表: 可以直接写数据类型, 基本类型写名称(int), 引用类型写包名.类名(java.lang.String)
                        或使用通配表示任意类型,但是必须有参数
                        或..表示有无参数都可, 有参数可以是任意类型
                全通配写法:
                    * *..*.*(..)

                实际开发中,切入点表达式的通常写法:
                    业务层实现类下的所有写法: * demo.service.impl.*.*(..)
        5.
    -->

    <!--配置Logger类-->
    <bean id="logger" class="demo.utils.Logger"/>

    <!--配置AOP-->
    <aop:config>
        <!--配置切面-->
        <aop:aspect id="logAdvice" ref="logger">
            <!--配置通知类型, 并且建立通知方法和切入点方法的关联-->
            <!--<aop:before method="printLog" pointcut="execution(public void demo.service.impl.AccountServiceImpl.saveAccount())"></aop:before>-->
            <!--<aop:before method="printLog" pointcut="execution(* *..*.*(..))"/>-->
            <aop:before method="printLog" pointcut="execution(* demo.service.impl.*.*(..))"/>
        </aop:aspect>
    </aop:config>
</beans>
```

### 测试

```java
package demo.test;

import demo.service.IAccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试AOP配置
 */
public class AOPtest {
    public static void main(String[] args) {
        //1.获取容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.获取对象
        IAccountService accountService = (IAccountService) context.getBean("accountService");
        //3.执行方法
        accountService.saveAccount();
        accountService.updateAccount(1);
        accountService.deleteAccount();
    }
}
```

## Spring中的通知类型

### 业务层接口与实现类

```java
package demo.service;

/**
 * 账户的业务接口
 */
public interface IAccountService {
    /**
     * 模拟
     *
     * 无参+无返回值
     * 有参+无返回值
     * 无参+有返回值
     */
    void saveAccount();

    void updateAccount(int id);

    int deleteAccount();
}
```

```java
package demo.service.impl;

import demo.service.IAccountService;

/**
 * @date 2020/9/5 14:49
 */
public class AccountServiceImpl implements IAccountService {

    public void saveAccount() {
        System.out.println("保存账户。。。");
//        int a = 1/0;
    }

    public void updateAccount(int id) {
        System.out.println("更新账户。。。"+id);
    }

    public int deleteAccount() {
        System.out.println("删除账户。。。");
        return 0;
    }
}
```

### 增强类

```java
package demo.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 记录日志的工具类，提供公共的代码
 */
public class Logger {
    /**
     * 前置通知：
     * 打印日志：在切入点方法执行之前执行（切入点方法就是业务层方法）
     */
    public void beforePrintLog(){
        System.out.println("前置通知：Logger类中beforePrintLog开始记录日志。。。");
    }

    /**
     * 后置通知：
     */
    public void afterReturningPrintLog(){
        System.out.println("后置通知：Logger类中afterReturningPrintLog开始记录日志。。。");
    }

    /**
     * 异常通知：
     */
    public void afterThrowingPrintLog(){
        System.out.println("异常通知：Logger类中afterThrowingPrintLog开始记录日志。。。");
    }

    /**
     * 最终通知：
     */
    public void afterPrintLog(){
        System.out.println("最终通知：Logger类中afterPrintLog开始记录日志。。。");
    }
}
```

### pom.xml依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.0.2.RELEASE</version>
    </dependency>

    <!--&lt;!&ndash;解析切入点表达式&ndash;&gt;-->
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.8.2</version>
    </dependency>
</dependencies>
```

### XML配置

```xml
<?xml version = "1.0" encoding = "UTF-8"?>
<beans xmlns = "http://www.springframework.org/schema/beans"
       xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop = "http://www.springframework.org/schema/aop"
       xsi:schemaLocation = "http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans.xsd
   http://www.springframework.org/schema/aop
   http://www.springframework.org/schema/aop/spring-aop.xsd ">

    <!-- 配置spring的IOC,把service对象配置 -->
    <bean id="accountService" class="demo.service.impl.AccountServiceImpl"/>

    <!--配置Logger类-->
    <bean id="logger" class="demo.utils.Logger"/>

    <!--配置AOP-->
    <aop:config>
        <!--写在aop:aspect标签外面，此时所有切面可用-->
        <aop:pointcut id="pt" expression="execution(* demo.service.impl.*.*(..))"/>

        <!--配置切面-->
        <aop:aspect id="logAdvice" ref="logger">
            <!--配置通知类型, 并且建立通知方法和切入点方法的关联-->

            <!--前置通知：在切入点方法执行之前-->
            <aop:before method="beforePrintLog" pointcut-ref="pt"/>
            <!--后置通知：在切入点方法正常执行之后-->
            <aop:after-returning method="afterReturningPrintLog" pointcut-ref="pt"/>
            <!--异常通知：在切入点方法产生异常后-->
            <aop:after-throwing method="afterThrowingPrintLog" pointcut-ref="pt"/>
            <!--最终通知：无论切入点方法是否执行，都会执行-->
            <aop:after method="afterPrintLog" pointcut-ref="pt"/>

            <!--环绕通知  详细注释看Logger类aroundPrintLog-->
            <aop:around method="aroundPrintLog" pointcut-ref="pt"/>

            <!--配置切入点表达式：id指定表达式唯一标识。expression指定表达式内容
                此标签写在aop:aspect标签内部只能用于当前切面
                或写在aop:aspect标签外面，此时所有切面可用
            -->
            <!--<aop:pointcut id="pt" expression="execution(* demo.service.impl.*.*(..))"/>-->
        </aop:aspect>
    </aop:config>
</beans>
```

### 测试

四种基本的通知类型

- 前置通知
- 后置通知
- 异常通知
- 最终通知

```java
package demo.test;

import demo.service.IAccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试AOP配置
 */
public class AOPtest {
    public static void main(String[] args) {
        //1.获取容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.获取对象
        IAccountService accountService = (IAccountService) context.getBean("accountService");
        //3.执行方法
        accountService.saveAccount();
    }
}
```

### 环绕通知

```java
/**
 * 环绕通知
 * 问题：
 *      当配置了环绕通知之后，切入点方法没有执行，而通知方法被执行了。
 * 分析：
 *      对比动态代理的环绕通知，发现动态代理中的环绕通知有明确的切入点方法调用。而我们的代码没有
 * 解决：
 *      Spring框架提供了一个接口：ProceedingJointPoint。该接口方法proceed()相当于明确调用切入点方法。
 *      该接口作为环绕通知的方法参数，在程序执行时，Spring框架会为我们提供该接口的实现类
 *
 * Spring的环绕通知：
 *      是Spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式
 */
public Object aroundPrintLog(ProceedingJoinPoint joinPoint){
    Object returnValue = null;
    try {
        // 方法所需参数
        Object[] args = joinPoint.getArgs();

        System.out.println("环绕通知：Logger类中aroundPrintLog开始记录日志。。。前置");

        // 明确调用业务层切入点方法
        returnValue = joinPoint.proceed();

        System.out.println("环绕通知：Logger类中aroundPrintLog开始记录日志。。。后置");

        return returnValue;
    } catch (Throwable throwable) {
        System.out.println("环绕通知：Logger类中aroundPrintLog开始记录日志。。。异常");
        throw new RuntimeException(throwable);
    } finally {
        System.out.println("环绕通知：Logger类中aroundPrintLog开始记录日志。。。最终");
    }
}
```

