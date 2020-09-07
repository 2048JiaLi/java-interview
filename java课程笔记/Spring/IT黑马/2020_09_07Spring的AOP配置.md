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