[TOC]

# 一、AOP与IOC

## 1.1、AOP

AOP（Aspect Orient Programming），即面向切面编程。是对OOP的一种补充。OOP将程序抽象成各个层次的对象，而AOP将程序抽象成各个切面。

传统OOP编程中，若存在重复代码，通常会将其抽象成一个方法，然后在需要的时候去调用该方法。而AOP要达到的效果是，保证开发者在不修改源码的前提下，去添加某种通用功能。

## 1.2、IoC(Inversion of Control)

**创建对象的控制权进行转移，以前创建对象的主动权和创建时机是由自己把控的，而现在这种权力转移到第三方**，比如转移交给了IoC容器

> 构造方法注入、stter方法注入

## 1.3、Spring有几种配置方式？

1. XML配置

   配置文件格式通常用`<beans>`开头，然后一系列的bean定义和专门的应用配置选项组成。

   SpringXML配置的主要目的是使所有的Spring组件都可以用xml文件的形式来进行配置。这意味着不会出现其他的Spring配置类型（比如声明的方式或基于Java Class配置）

   SpringXML配置方式是使用被Spring命名空间的所支持的一系列XML标签来实现的。Spring有以下主要的命名空间：context、beans、jdbc、tx、aop、mvc、aso

2. 注解配置

   Spring在2.5版本以后开始支持用注解的方式来配置依赖注入。可以用注解方式替代XML方式的bean描述，将bean描述转移到组件类的内部，只需要在相关类上、方法上或字段声明上使用相应注解。注解注入将被容器在XML注入之前处理，所以后者会覆盖掉前者对于同一个属性的处理。

   注解装配在Spring中是默认关闭的。所以需要在Spring文件中配置，才能使用基于注解的装配模式。

   ```xml
   <beans>
       <context:annotation-config/>
   </beans>
   ```

   在上述标签配置完成以后，就可以使用注解自动装配。

   - @Required
   - @Autowired
   - @Qualifier

3. Java配置

   Spring对Java配置的支持是由注解`@Configuration`和`@Bean`实现的。

   由`@Bean`注解的方法将会实例化、配置和初始化一个新对象，这个对象将由Spring的IOC容器管理。

   由`@Configuration`所注解的类则表示这个类的主要目的是作为bean定义的资源。被`@Configuration`声明的类可以通过在同一个类的内部调用`@Bean`方法来设置嵌入bean的依赖关系

   如下最简单的`@Configuration`声明类

   ```java
   @Configuration
   public class AppConfig{
       @Bean
       public MyService myService(){
           return new MyServiceImpl();
       }
   }
   ```

   对于上面的@Bean配置，等同于XML中配置

   ```xml
   <beans>
       <bean id="myService" class="com.services.MyServiceImpl"/>
   </beans>
   ```

## 1.4、Spring Bean的作用域之间有什么区别？

Spring容器中的bean可以分为5个范围。

1. singleton（默认）：确保不管接受到多少个请求，每个容器中只有一个bean实例，即单例。由bean factory自身维护。
2. prototype：多例。即与单例相反，为每一个bean请求提供一个实例。
3. request：在请求bean范围内会每一个来自客户端的网络请求创建一个实例，在请求完成之后，bean会失效并被垃圾回收器回收。
4. Session：与请求范围类似，确保每个session中有一个bean的实例，在session过期后，bean会随之失效。
5. global-session

# 二、Spring MVC

## 2.1、Spring MVC是什么？

SpringMVC是Spring的一个模块，基于MVC三层架构，且无需中间层来整合

- Spring MVC是Spring提供的一个强大而灵活的模块式web框架。
- Spring框架最核心的就是所谓的依赖注射和控制反转。
- SpringMVC是一种基于Java的以请求为驱动类型的轻量级Web框架，其目的是将Web层进行解耦

## 2.2、Spring MVC的优点

- 基于组件技术。全部的应用对象，无论控制器和视图，还是业务对象之类的都是Java组件。并且和Spring提供的其他基础结果紧密集成。
- 不依赖于Servlet API（目标虽然如此，但是在实现的时候确实是依赖于Servlet的）
- 可以任意使用各种视图技术，而不仅仅局限于JSP
- 支持各种请求资源的映射策略
- 易于扩展

## 2.3、工作原理

1. 客户端发送请求到DispatcherServlet（前端控制器）
2. DispatcherServlet查询handlerMapping（处理器映射器）找到处理请求的Controller
3. Controller调用业务逻辑后，返回ModelAndView
4. DispatcherServlet查询ModelAndView，找到指定的视图
5. 视图将结果返回客户端

## 2.4、Spring MVC控制器是不是单例的？如果是，有什么问题，怎么解决？

是单例模式，因此在多线程访问的时候，存在线程安全问题。使用同步，会影响性能，解决方案是在控制器里面不写字段。

# 三、Spring boot

## 3.1、Spring boot 特性

Spring Boot 的优点快速开发，特别适合构建微服务系统，另外给我们封装了各种经常使用的套件，比如mybatis、hibernate、redis、mongodb等。

- 使用 Spring 项目引导页面可以在几秒构建一个项目
- 支持关系数据库和非关系数据库
- 支持运行期内嵌容器，如 Tomcat
- 自动管理依赖
- 自带应用监控：提供了基于http、ssh、telnet对运行时的项目进行监控；