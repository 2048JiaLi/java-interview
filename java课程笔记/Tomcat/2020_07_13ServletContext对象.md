## ServletContext对象

ServletContext代表当前整个应用程序

#### 1. 什么是ServletContext

当服务器启动时，会为每一个web应用程序（webapps下的每个目录就是一个应用程序）创建一个共享的存储区域ServletContext也叫做“公共区域”，也就是同一个web应用程序中，所有的Servlet和JSP都可以共享同一个区域。

ServletContext在web服务器启动时创建，服务器关闭时销毁

#### 2. 如何获取

```java
// 1. 通过servletConfig获取
ServletContext servletContext = this.getServletConfig().getServletContext();
// 2. 通过genericServlet提供的方法获取
ServletContext servletContext1 = this.getServletContext();
// 3. 通过request获取
ServletContext servletContext2 = request.getServletContext();
// 4. 通过session获取
ServletContext servletContext3 = request.getSession().getServletContext();
```

> PS：四种方式获取的都是同一个ServletContext（地址相同）

#### 3. 作用及特点

- 作用

  - 获取真实路径（当前项目的发布路径）

  `request.getServletContext().getRealPath("/");`

  - 获取容器附加信息

  ```java
  // 获取servlet信息
  request.getServletContext().getServerInfo();
  // 获取context路径
  request.getServletContext().getContextPath();
  ```

  

  - 全局容器

- 特点
  - 唯一性：一个应用对应一个ServletContext
  - 一直存在：只要容器不关闭或者应用不卸载，就会一直存在

#### 3. web.xml配置servletContext参数

```xml
<!--配置应用程序的初始化参数-->
<context-param>
    <param-name>appname</param-name>
    <param-value>xxx管理系统</param-value>
</context-param>
```

