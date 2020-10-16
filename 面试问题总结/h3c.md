## 一、DAO层的具体实现方式

德鲁伊连接池配置数据源

```xml
    <!--注意定位依赖时的数据库版本匹配问题-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://127.0.0.1:3306/data?serverTimezone=GMT"/>
        <property name="username" value="root"/>
        <property name="password" value="root147"/>
    </bean>
```

> 这里也可以使用c3p0配置数据源

再将其配置注入QueryRunner，利用QueryRunner可以其封装的query、update方法，结合sql语句实现对数据库的操作

```xml
    <!-- 配置dao对象 -->
    <bean id="accountDao" class="demo.dao.impl.AccountDaoImpl">
        <!-- 注入QueryRunner -->
        <property name="runner" ref="runner"></property>
    </bean>

    <!--QueryRunner-->
    <bean id="runner" class="org.apache.commons.dbutils.QueryRunner" scope="prototype">
        <!--注入数据源,QueryRunner只能选择构造函数注入-->
        <constructor-arg name="ds" ref="dataSource"></constructor-arg>
    </bean>
```

## 二、数据库事务的作用

### 2.1 什么是事务

**数据库事务**通常**指对数据库进行读或写的一个操作序列**。

- 它的存在包含有以下两个目的：
  - 为数据库操作提供了一个从失败中恢复到正常状态的方法，同时提供了数据库即使在异常状态下仍能保持一致性的方法。
  - 当多个应用程序在并发访问数据库时，可以在这些应用程序之间提供一个隔离方法，以防止彼此的操作互相干扰。

**系统中的事务**: 处理一系列业务处理的执行逻辑单元,该单元里的一系列类操作**要不全部成功要不全部失败。**

### 2.2 为什么使用事务

可以保证数据的一致性和完整性(避免异常和错误等导致的数据信息异常) 

### 2.3 事务的特性ACID

- 原子性（Atomicity）：事务包含的操作全部成功或者全部失败
- 一致性（Consistency）：数据库从一个一致性状态变到另一个一致性状态

​    (一系列操作后,所有的操作和更新全部提交成功,数据库只包含全部成功后的数据就是数据的一致性)

​    (由于系统异常或数据库系统出现故障导致只有部分数据更新成功,但是这不是我们需要的最终数据,这就是数据的不一致)

- 隔离性（Isolation）：事务互相隔离互不干扰

​     (事务内部操作的数据对其它事务是隔离的,在一个事务执行完之前不会被其他事务影响和操作)

- 持久性（Durability）：事务提交后数据应该被永久的保存下来，出现宕机等故障后可以恢复数据

### 2.4 **事务并发处理可能引起的问题**

- **脏读(dirty read)**：一个事务读取了另一个事务尚未提交的数据，
- **不可重复读(non-repeatable read)** ：一个事务的操作导致另一个事务前后两次读取到不同的数据
- **幻读(phantom read)** ：一个事务的操作导致另一个事务前后两次查询的结果数据量不同。

## 三、Java事务

实际上，一个Java应用系统，如果要操作数据库，则通过JDBC来实现的。增加、修改、删除都是通过相应方法间接来实现的，事务的控制也相应转移到Java程序代码中。因此，数据库操作的事务习惯上就称为Java事务。

### 3.1 为什么需要Java事务?

事务是为解决数据安全操作提出的，事务控制实际上就是控制数据的安全访问。

```
一个简单例子：比如银行转帐业务，账户A要将自己账户上的1000元转到B账 户下面，A账户余额首先要减去1000元，然后B账户要增加1000元。假如在中间网络出现了问题，A账户减去1000元已经结束，B因为网络中断而操作 失败，那么整个业务失败，必须做出控制，要求A账户转帐业务撤销。这才能保证业务的正确性，完成这个操作就需要事务，将A账户资金减少和B账户资金增加方 到一个事务里面，要么全部执行成功，要么操作全部撤销，这样就保持了数据的安全性。
```

### 3.2 Java事务的类型

Java事务的类型有三种：**JDBC事务、JTA（Java Transaction API）事务、容器事务。**

#### 3.2.1、**JDBC事务**

JDBC 事务是用 Connection 对象控制的。JDBC Connection 接口（ java.sql.Connection ）提供了两种事务模式：自动提交和手工提交。

> 在连接数据库时，会通过Connection 对象，获得对数据库的连接。

```java
// java.sql.Connection 提供了以下控制事务的方法：

public void setAutoCommit(boolean)
public boolean getAutoCommit()
public void commit()
public void rollback()
```

使用 JDBC 事务界定时，您可以将多个 SQL 语句结合到一个事务中。
JDBC 事务的一个**缺点是事务的范围局限于一个数据库连接**。一个 JDBC 事务不能跨越多个数据库。

```java
private Connection conn = null;  

private PreparedStatement ps = null;  

try {  
    conn.setAutoCommit(false);  //将自动提交设置为false  

    ps.executeUpdate("修改SQL"); //执行修改操作  

    ps.executeQuery("查询SQL");  //执行查询操作                 

    conn.commit();      //当两个操作成功后手动提交  

} catch (Exception e) {  

    conn.rollback();    //一旦其中一个操作出错都将回滚，使两个操作都不成功  

    e.printStackTrace();  

} 
```

#### 3.2.2、**JTA（Java Transaction API）事务**

JTA是一种高层的，**与实现无关的，与协议无关的API**，应用程序和应用服务器可以使用JTA来访问事务。

JTA允许应用程序执行**分布式事务处理**——**在两个或多个网络计算机资源上访问并且更新数据，这些数据可以分布在多个数据库上**。JDBC驱动程序的JTA支持极大地增强了数据访问能力。

#### 3.2.3、**容器事务**

容器事务主要是J2EE应用服务器提供的，容器事务大多是基于JTA完成，这是一个基于JNDI的，相当复杂的API实现。

### 3.3 三种Java事务差异?

**1、JDBC事务控制的局限性在一个数据库连接内，但是其使用简单。**
**2、JTA事务的功能强大，事务可以跨越多个数据库或多个DAO，使用也比较复杂。**
**3、容器事务，主要指的是J2EE应用服务器提供的事务管理，局限于EJB应用使用。**

### 3.4 JDBC的事务支持

JDBC对事务的支持体现在三个方面：

#### 3.4.1、**自动提交模式(Auto-commit mode)**

Connection提供了一个auto-commit的属性来指定事务何时结束。

- **当auto-commit为true时（默认）**，当每个独立SQL操作的执行完毕，事务立即自动提交，也就是说每个SQL操作都是一个事务。

  ```
  一个独立SQL操作什么时候算执行完毕。
  
  1.JDBC规范是这样规定的：对数据操作语言(DML，如insert,update,delete)和数据定义语言(如create,drop)，语句一执行完就视为执行完毕。
  2.对select语句，当与它关联的ResultSet对象关闭时，视为执行完毕。
  3.对存储过程或其他返回多个结果的语句，当与它关联的所有ResultSet对象全部关闭，所有update count(update,delete等语句操作影响的行数)和output parameter(存储过程的输出参数)都已经获取之后，视为执行完毕。
  ```

- **当auto-commit为false时**，每个事务都**必须显示调用commit方法**进行提交，**或者显示调用rollback方法**进行回滚。

#### 3.4.2、**事务隔离级别(Transaction Isolation Levels)**

JDBC提供了5种不同的事务隔离级别，在Connection中进行了定义。
JDBC定义了五种事务隔离级别：

```
TRANSACTION_NONE JDBC驱动不支持事务
TRANSACTION_READ_UNCOMMITTED 允许脏读、不可重复读和幻读。
TRANSACTION_READ_COMMITTED 禁止脏读，但允许不可重复读和幻读。
TRANSACTION_REPEATABLE_READ 禁止脏读和不可重复读，单运行幻读。
TRANSACTION_SERIALIZABLE 禁止脏读、不可重复读和幻读。
```

#### 3.4.3、**保存点(SavePoint)**

JDBC定义了SavePoint接口，提供在一个更细粒度的事务控制机制。**当设置了一个保存点后，可以rollback到该保存点处的状态，而不是rollback整个事务。**

**Connection接口的setSavepoint和releaseSavepoint方法可以设置和释放保存点。**

## 四、进程间的通信方式

### 4.1 管道

速度慢，容量有限，只有父子进程能通讯  

### 4.2 消息队列

消息队列，就是一个消息的链表，是一系列保存在内核中消息的列表。用户进程可以向消息队列添加消息，也可以向消息队列读取消息。

消息队列与管道通信相比，其优势是对每个消息指定特定的消息类型，接收的时候不需要按照队列次序，而是可以根据自定义条件接收特定类型的消息。

可以把消息看做一个记录，具有特定的格式以及特定的优先级。对消息队列有写权限的进程可以向消息队列中按照一定的规则添加新消息，对消息队列有读权限的进程可以从消息队列中读取消息。

**进程间通过消息队列通信，主要是：创建或打开消息队列，添加消息，读取消息和控制消息队列。**

### 4.3 共享内存

共享内存允许**两个或多个进程共享一个给定的存储区**，这一段存储区可以被两个或两个以上的进程映射至自身的地址空间中，**一个进程写入共享内存的信息，可以被其他使用这个共享内存的进程，通过一个简单的内存读取错做读出**，从而实现了进程间的通信。

>  采用共享内存进行通信的一个主要好处是**效率高**，因为进程可以直接读写内存，而不需要任何数据的拷贝，对于像管道和消息队里等通信方式，则需要再内核和用户空间进行四次的数据拷贝，而共享内存则只拷贝两次：一次从输入文件到共享内存区，另一次从共享内存到输出文件。

共享内存有两种实现方式：1、内存映射 2、共享内存机制

### 4.4 Socket

## 五、抽象类的作用

- 抽象类不能被实例化(初学者很容易犯的错)，如果被实例化，就会报错，编译无法通过。只有抽象类的非抽象子类可以创建对象。
- 抽象类中不一定包含抽象方法，但是有抽象方法的类必定是抽象类。
- 抽象类中的抽象方法只是声明，不包含方法体，就是不给出方法的具体实现也就是方法的具体功能。
- 构造方法，类方法（用 static 修饰的方法）不能声明为抽象方法。
- 抽象类的子类必须给出抽象类中的抽象方法的具体实现，除非该子类也是抽象类。

> 抽象类的作用应该是实现多态，

**抽象类除了不能实例化对象之外，类的其它功能依然存在，成员变量、成员方法和构造方法的访问方式和普通类一样**。由于抽象类不能实例化对象，所以**抽象类必须被继承，才能被使用**。也是因为这个原因，通常在设计阶段决定要不要设计抽象类。