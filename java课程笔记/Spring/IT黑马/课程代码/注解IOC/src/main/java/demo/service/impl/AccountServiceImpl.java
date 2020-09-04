package demo.service.impl;

import demo.dao.AccountDao;
import demo.dao.impl.AccountDaoImpl;
import demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 *     XML的配置方式
 *     <bean id="accountService" class="demo.service.impl.AccountServiceImpl"
 *          scope="" init-method="" destroy-method="">
 *          <property name="" value="" | ref=""></property>
 *     </bean>
 *
 *     注解方式：
 *      用于创建对象的
 *          作用和在XML配置文件中编写一个<bean>标签实现的功能一样
 *           @Component
 *              作用：把当前类存入spring容器
 *              属性：
 *                  value：指定bean的id。不写时，默认值是当前类名的首字母小写
 *
 *           @Controller：一般在表现层
 *           @Service：一般在业务层
 *           @Repository：一般用在持久层
 *           以上三个注解的作用和属性与Conmponent是一样的。是spring框架为我们提供明确的三层架构使用的。
 *
 *      用于注入数据的
 *          作用和在XML中bean标签的<property></property>标签是一样的
 *          @Autowired：
 *              作用：自动按照类型注入。只要容器中有唯一一个bean对象类型和要注入的变量类型匹配，就可以成功注入
 *              出现位置，可以是变量上，也可以是方法上
 *          在使用注解方式时，set方式不是必须的了
 *
 *          @Qualifier:
 *              作用：在按照类中注入的基础之上再按照名称注入。
 *              属性：
 *                  value：用于指定注入bean的id
 *
 *          @Resource:
 *              作用：直接按照bean的id注入。可以独立使用
 *              属性：
 *                  name：用于指定注入bean的id
 *
 *          以上三个注入都只能注入其他类型的数据，而基本类型和String类型无法使用上述注解实现。
 *          另外，集合类型的注入只能通过XML来实现
 *
 *          @Value:
 *              作用：用于注入基本类型和String类型的数据
 *              属性：
 *                  value：用于指定数据的值。可以使用spring的SpEL（即spring的el表达式）
 *                          SpEL写法：${表达式}
 *
 *      用于改变作用范围的
 *          作用和scope一样
 *          @Scope
 *              作用：指定bean的作用范围
 *              属性：
 *                  value：指定范围的取值。常用取值：singleton    prototype
 *
 *      和生命周期相关
 *          和init-method="" destroy-method=""作用一样
 *          PreDestroy
 *              作用：指定销毁方法
 *          PostConstruct
 *              作用：指定初始化方法
 *
 * @author Li W
 * @version 1.8
 * @date 2020/8/28 9:26
 */
@Service("accountService")
//@Scope("prototype")
public class AccountServiceImpl implements AccountService {
    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;

    @PostConstruct
    public void init() {
        System.out.println("初始化方法。。。");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁方法。。。");
    }

    public AccountServiceImpl() {
        System.out.println("对象被创建了。。。");
    }

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
