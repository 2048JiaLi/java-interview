## JdbcTemplate概述

它是Spring框架中提供的一个对象，士是对原始Jdbc API对象的简单封装。Spring框架为我们提供了很多的操作模板类。

- 操作关系型数据的
  - JdbcTemplate
  - HibernateTemplate
- 操作nosql数据库的
  - RedisTemplate
- 操作消息队列的
  - JmsTemplate

> JdbcTemplate在 spring-jdbc-5.0.2.RELEASE.jar中，在导包时，还需要导入一个spring-tx-5.0.2.RELEASE.jar（是和事务相关的）

### JdbcTemplate作用

### JdbcTemplate的CRUD

#### 数据库中Account实体类

```java
package demo.entity;

import java.io.Serializable;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/9/9 13:45
 */
public class Account implements Serializable {

    private Integer id;
    private String name;
    private Float money;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }
}
```

#### JdbcTemplate最基本的用法

```java
package demo.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * JdbcTemplate最基本的用法
 */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        // 准备数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/data?serverTimezone=GMT");
        ds.setUsername("root");
        ds.setPassword("root147");

        // 1. 创建对象
        JdbcTemplate jt = new JdbcTemplate();
        // 设置数据源
        jt.setDataSource(ds);
        // 2. 执行操作
        jt.execute("insert into account(name, money) values ('ddd',100)");
    }
}
```

#### Spring配置用法

```java
package demo.jdbctemplate;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * JdbcTemplate的XML配置
 */
public class JdbcTemplateDemo2 {

    public static void main(String[] args) {
        // 1. 获取容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2. 获取对象
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        // 3. 执行操作
        jt.execute("insert into account(name, money) values ('fff',222)");
    }
}
```

- 配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--配置JdbcTemplate-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!--配置数据源-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/data?serverTimezone=GMT"/>
        <property name="username" value="root"/>
        <property name="password" value="root147"/>
    </bean>
</beans>
```

#### CRUD操作

```java
package demo.jdbctemplate;

import demo.entity.Account;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JdbcTemplate的XML配置
 *
 * CRUD操作
 */
public class JdbcTemplateDemo3 {

    public static void main(String[] args) {
        // 1. 获取容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2. 获取对象
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        // 3. 执行操作
        // 保存
//        jt.update("insert into account(name, money) VALUES(?,?)","fff",333f);
        // 更新
//        jt.update("update account set name=?,money=? where id=?","update",457,7);
        // 删除
//        jt.update("delete from account where id=?",8);
        // 查询所有
        // 自己定义封装
//        List<Account> querys = jt.query("select * from account where money>?", new AccountRowMapper(), 1000f);
        // spring的封装
//        List<Account> querys = jt.query("select * from account where money>?", new BeanPropertyRowMapper<Account>(Account.class), 1000f);
//        for (Account acc : querys) {
//            System.out.println(acc);
//        }
        // 查询一个，直接使用查多个的方法
//        List<Account> querys = jt.query("select * from account where id=?", new BeanPropertyRowMapper<Account>(Account.class), 7);
//        if (!querys.isEmpty()) {
//            // 根据id查一个，只存在有/没有
//            System.out.println(querys.get(0));
//        }
        // 查询返回一行一列（使用聚合函数，但不加group by）
        Integer integer = jt.queryForObject("select count(*) from account where money>?", Integer.class, 1000f);
        System.out.println(integer);

    }
}

/**
 * 定义Account的封装策略
 */
class AccountRowMapper implements RowMapper<Account> {

    /**
     * 把结果集的数据封装到Account中，然后spring把每个Account加到集合
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}
```

## JdbcTemplate在Dao的使用

```java
package demo.dao.impl;

import demo.dao.IAccountDao;
import demo.entity.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/9/10 9:16
 */
public class AccountImpl implements IAccountDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account findAccountById(Integer id) {
        List<Account> accounts = jdbcTemplate.query("select * from account where id=?", new BeanPropertyRowMapper<Account>(Account.class), id);

        return accounts.isEmpty()? null:accounts.get(0);
    }

    public Account findAccountByName(String name) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name=?", new BeanPropertyRowMapper<Account>(Account.class), name);
        if (accounts.isEmpty()){
            return null;
        }

        if (accounts.size()>1){
            throw new RuntimeException("结果不唯一");
        }
        return accounts.get(0);
    }

    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());

    }
}
```