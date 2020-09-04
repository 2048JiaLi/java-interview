package demo.ui;

import demo.dao.AccountDao;
import demo.service.AccountService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模拟一个表现层，用于调用业务层
 * @author Li W
 * @version 1.8
 * @date 2020/8/28 9:30
 */
public class Client {

    /**
     * ApplicationContext三个实现类
     *    ClassPathXmlApplicationContext:加载类路径下的配置文件，要求配置文件必须在类路径下。不在的话，加载不了
     *    FileSystemXmlApplicationContext:加载磁盘任意路径下的配置文件（必须有访问权限）
     *    AnnotationConfigApplicationContext：读取注解创建容器
     *
     * 核心容器的两个接口
     *   ApplicationContext:
     *       它在构建核心容器时，创建对象采用的策略是采用立即加载的方式。即，读取完配置文件就立即创建
     *
     *   BeanFactory:
     *       它在构建核心容器时，创建对象采用延迟加载的方式。
     *
     * 获取spring的IOC核心容器，并根据id获取对象
     * @param args
     */
    public static void main(String[] args) {
//        // 1.获取核心容器对象
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
////        ApplicationContext ac = new FileSystemXmlApplicationContext("F:\\Desktop\\JavaProjects\\Spring项目\\IT黑马\\spring基于XML的IOC环境\\src\\main\\resources\\applicationContext.xml");
//        // 2.根据id获取bean对象
//        AccountService accountService = (AccountService) ac.getBean("accountService");
//        AccountDao accountDao = ac.getBean("accountDao", AccountDao.class);
//
//        System.out.println(accountService);
//        System.out.println(accountDao);

//      ------BeanFactory-------
        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        AccountService accountService = factory.getBean("accountService",AccountService.class);
        System.out.println(accountService);
    }
}
