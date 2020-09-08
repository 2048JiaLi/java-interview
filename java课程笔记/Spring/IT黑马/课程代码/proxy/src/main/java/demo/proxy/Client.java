package demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模拟一个消费者
 */
public class Client {
    public static void main(String[] args) {
        final Producer producer = new Producer();

        /**
         * 动态代理：
         *      特点：字节码随用随创建，随用随加载
         *      作用：不修改源码的基础上对方法增强
         *      分类：
         *          基于接口的动态代理
         *              涉及的类：Proxy
         *              提供这：JDK官方
         *
         *              如何创建代理对象：
         *                  使用Proxy中的newProxyInstance方法
         *              创建代理的要求：
         *                  **被代理类最少实现一个接口**，若没有则无法运行
         *              newProxyInstance参数：
         *                  ClassLoader：类加载器
         *                      加载代理对象字节码。和被代理对象使用相同的类加载器
         *                      eg：producer.getClass().getClassLoader()
         *                  Class[]：字节码数组
         *                      让代理对象和被代理对象有相同的方法
         *                      eg：producer.getClass().getInterfaces()
         *                  InvocationHandler：提供增强的代码
         *                      如何代理。一般都是写该接口的实现类，通常情况下是匿名内部类，但不是必须的
         *
         *          基于子类的动态代理：见demo.cglib.Client类
         *
         */
        IProducer proxyInstance = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 作用：执行被代理对象的任何接口方法都会经过该方法
                     * @param proxy：代理对象的引用
                     * @param method：当前执行的方法
                     * @param args：当前执行方法所需的参数
                     * @return      和被代理对象有相同的返回值
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 提供增强的代码
                        // 1.获取方法执行的参数
                        Float money =(Float) args[0];
                        // 2.判断当前方法是不是销售
                        if("saleProduct".equals(method.getName())){
                            return method.invoke(producer, money*0.8f);
                        }
                        return null;
                    }
                });

        proxyInstance.saleProduct(1000f);
    }
}
