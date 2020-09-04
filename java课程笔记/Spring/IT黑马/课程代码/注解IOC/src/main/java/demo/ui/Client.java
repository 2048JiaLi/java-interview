package demo.ui;

import demo.service.AccountService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
     * @param args
     */
    public static void main(String[] args) {
        // 1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ApplicationContext ac = new FileSystemXmlApplicationContext("F:\\Desktop\\JavaProjects\\Spring项目\\IT黑马\\spring基于XML的IOC环境\\src\\main\\resources\\applicationContext.xml");
        // 2.根据id获取bean对象
        AccountService accountService = (AccountService) ac.getBean("accountService");
        System.out.println(accountService);

        ((ClassPathXmlApplicationContext) ac).close();
    }
}
