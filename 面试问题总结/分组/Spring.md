[TOC]

# 一、AOP与IOC

## 1.1、AOP

AOP（Aspect Orient Programming），即面向切面编程。是对OOP的一种补充。OOP将程序抽象成各个层次的对象，而AOP将程序抽象成各个切面。

传统OOP编程中，若存在重复代码，通常会将其抽象成一个方法，然后在需要的时候去调用该方法。而AOP要达到的效果是，保证开发者在不修改源码的前提下，去添加某种通用功能。

## 1.2、IoC(Inversion of Control)

**创建对象的控制权进行转移，以前创建对象的主动权和创建时机是由自己把控的，而现在这种权力转移到第三方**，比如转移交给了IoC容器

> 构造方法注入、stter方法注入

## 1.3、Spring Bean有几种配置方式？

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

## 1.4、@Autowired 与@Resource的区别

- @Autowired与@Resource都可以用来装配bean. 都可以写在字段上,或写在setter方法上。

- 

- @Autowired默认按类型装配（这个注解是属于spring的），**默认情况下必须要求依赖对象必须存在，如果要允许null值，可以设置它的required属性为false**，

  > 如：`@Autowired(required=false) `

- @Resource（注解属于J2EE的），默认**按照名称进行装配**，名称可以通过name属性进行指定，如果没有指定name属性，当注解写在字段上时，默认取字段名进行安装名称查找，如果注解写在setter方法上默认取属性名进行装配。**当找不到与名称匹配的bean时才按照类型进行装配**。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。

  > @Resource装配顺序
  >
  > 　　1. 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常
  >
  > 　　2. 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常
  >
  > 　　3. 如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常
  >
  > 　　4. 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配；

## 1.5、@Autowired 进行注入bean的过程

@Autowired 默认是按照byType进行注入的，如果发现找到多个bean，则，又按照byName方式比对，如果还有多个，则报出异常。





## 1.6、Spring Bean的作用域之间有什么区别？

Spring容器中的bean可以分为5个范围。

1. singleton（默认）：确保不管接受到多少个请求，每个容器中只有一个bean实例，即单例。由bean factory自身维护。
2. prototype：多例。即与单例相反，为每一个bean请求提供一个实例。
3. request：在请求bean范围内会每一个来自客户端的网络请求创建一个实例，在请求完成之后，bean会失效并被垃圾回收器回收。
4. Session：与请求范围类似，确保每个session中有一个bean的实例，在session过期后，bean会随之失效。
5. global-session

## 1.7、spring中BeanFactory和FactoryBean的区别

- 共同点：

​     都是接口

- 区别：
  - BeanFactory 以Factory结尾，表示它是一个工厂类，用于管理Bean的一个工厂

    > 在Spring中，所有的Bean都是由BeanFactory(也就是IOC容器)来进行管理的。

  - 但对FactoryBean而言，这个Bean不是简单的Bean，而是一个能生产或者修饰对象生成的工厂Bean

    > 它的实现与设计模式中的工厂模式和修饰器模式类似。

### a、BeanFactory

BeanFactory定义了IOC容器的最基本形式，并提供了IOC容器应遵守的的最基本的接口，也就是**Spring IOC所遵守的最底层和最基本的编程规范**。

**它的职责包括**：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。

在Spring代码中，**BeanFactory只是个接口**，并不是IOC容器的具体实现，

**但是Spring容器给出了很多种实现**，如 DefaultListableBeanFactory、XmlBeanFactory、ApplicationContext等，都是附加了某种功能的实现。

### b、FactoryBean

 一般情况下，Spring通过反射机制利用`<bean>`的class属性指定实现类实例化Bean，在某些情况下，实例化Bean过程比较复杂，

如果按照传统的方式，则需要在`<bean>`中提供大量的配置信息。配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。

Spring为此提供了一个`org.springframework.bean.factory.FactoryBean`的**工厂类接口**，用户可以**通过实现该接口定制实例化Bean的逻辑**。
FactoryBean接口对于Spring框架来说占用重要的地位，Spring自身就提供了70多个FactoryBean的实现。

它们**隐藏了实例化一些复杂Bean的细节，给上层应用带来了便利**。从Spring3.0开始，FactoryBean开始支持泛型，即接口声明改为FactoryBean<T>的形式

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

## 2.3、工作流程

1. 客户端发送请求到DispatcherServlet（前端控制器）
2. DispatcherServlet查询handlerMapping（处理器映射器）找到处理请求的Controller
3. Controller调用业务逻辑后，返回ModelAndView
4. DispatcherServlet查询ModelAndView，找到指定的视图
5. 将视图结果返回客户端

## 2.4、组件说明

- DispatcherServlet：前端控制器，也称为中央控制器，它是整个请求响应的控制中心，组件的调用由它统一调度。
- HandlerMapping：处理器映射器，它根据用户访问的 URL 映射到对应的后端处理器 Handler。也就是说它知道处理用户请求的后端处理器，但是它并不执行后端处理器，而是将处理器告诉给中央处理器。
- HandlerAdapter：处理器适配器，它调用后端处理器中的方法，返回逻辑视图 ModelAndView 对象。
- ViewResolver：视图解析器，将 ModelAndView 逻辑视图解析为具体的视图（如 JSP）。
- Handler：后端处理器，对用户具体请求进行处理，也就是我们编写的 Controller 类。

## 2.5、Spring MVC控制器是不是单例的？如果是，有什么问题，怎么解决？

是单例模式，因此在多线程访问的时候，存在线程安全问题。使用同步，会影响性能，解决方案是在控制器里面不写字段。

# 三、Spring boot

## 3.1、**为什么要用 spring boot**

```
Spring Boot使配置变简单

Spring Boot使部署变简单

Spring Boot使监控变简单
```

## 3.2、Spring boot 特性

Spring Boot 的优点快速开发，特别适合构建微服务系统，另外给我们封装了各种经常使用的套件，比如mybatis、hibernate、redis、mongodb等。

- 使用 Spring 项目引导页面可以在几秒构建一个项目
- 支持关系数据库和非关系数据库
- 支持运行期内嵌容器，如 Tomcat
- 自动管理依赖
- 自带应用监控：提供了基于http、ssh、telnet对运行时的项目进行监控；

## 3.3、**spring boot 核心配置文件**

Spring Boot提供了两种常用的配置文件：

- properties文件
- yml文件

# 四、Spring事务

## 4.1、**spring 事务实现方式有哪些？**

- **编程式事务管理**对基于 POJO 的应用来说是唯一选择。我们需要在代码中调用beginTransaction()、commit()、rollback()等事务管理相关的方法，这就是编程式事务管理。
- 基于 TransactionProxyFactoryBean 的**声明式事务管理**
- 基于 @Transactional 的**声明式事务管理**
- 基于 Aspectj AOP 配置事务

## 4.2、**spring 的事务隔离**

事务隔离级别指的是一个事务对数据的修改与另一个并行的事务的隔离程度，当多个事务同时访问相同数据时，如果没有采取必要的隔离机制，就可能发生以下问题：

- 脏读：一个事务读到另一个事务未提交的更新数据。
- 幻读：例如第一个事务对一个表中的数据进行了修改，比如这种修改涉及到表中的“全部数据行”。同时，第二个事务也修改这个表中的数据，这种修改是向表中插入“一行新数据”。那么，以后就会发生操作第一个事务的用户发现表中还存在没有修改的数据行，就好象发生了幻觉一样。
- 不可重复读：比方说在同一个事务中先后执行两条一模一样的select语句，期间在此次事务中没有执行过任何DDL语句，但先后得到的结果不一致，这就是不可重复读。

# 五、微服务架构

## 5.1、微服务架构简介

```
微服务架构的提出者：马丁福勒
```

简言之，微服务架构样式是一种将单个应用程序开发为一组小服务的方法，每个服务都在自己的进程中运行并与轻量级机制（通常是HTTP资源API）进行通信。这些服务围绕业务功能构建，并且可以由全自动部署机制独立部署。这些服务的集中管理几乎没有，它可以用不同的编程语言编写并使用不同的数据存储技术。

```
1、微服务架构只是一个样式，一个风格
2、将一个完整的项目，拆分成多个模块去分别开发
3、每一个模块都是单独的运行在自己的容器中
4、每个模块都是需要项目通讯的。HTTP、MQ
5、每一个模块之间是没有依赖关系的，单独的部署
6、可以使用多种语言去开发不同的模块
7、使用MySQL数据库，Redis去存储数据，也可以使用多个MySQL数据库

总结：将复杂臃肿的单体应用进行细粒度的划分，每个拆分出来的服务各自打包部署。
```

## 5.2、SpringCloud

> SpringCloud是微服务架构落地的一套技术栈。
>
> SpringCloud中的大多数技术都是基于NetFlix公司的技术进行二次研发

```
1、SpringCloud的中文社区网站：http://springcloud.cn
2、SpringCloud中文网：http://springcloud.cc
```

**八个技术点：**

> B站视频地址https://www.bilibili.com/video/BV19C4y187Ar?p=2

- Eureka：服务的注册与发现
- Robbin：服务之间的负载均衡
- Feign：服务之间的通讯
- Hystrix：服务的线程隔离以及断路器
- Zuul：服务网关
- Stream：实现MQ的使用
- Config：动态配置
- Sleuth：服务追踪