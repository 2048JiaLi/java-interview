package demo.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

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
         *
         *      分类：
         *          基于子类的动态代理
         *              涉及的类：Enhancer
         *              提供这：第三方cglib库
         *
         *              如何创建代理对象：
         *                  使用Enhancer中的create方法
         *              创建代理的要求：
         *                  **被代理类不能是最终类**，
         *              create参数：
         *                  Class：字节码
         *                      指定被代理对象的字节码
         *                      eg：producer.getClass().getClassLoader()
         *                  Callback：提供增强的代码
         *                      如何代理。一般都是写该接口的实现类，通常情况下是匿名内部类，但不是必须的
         */

        Producer cglibProducer = (Producer) Enhancer.create(producer.getClass(),
                new MethodInterceptor() {
                    /**
                     * 执行被代理对象的任何方法都会经过该方法
                     * @param o
                     * @param method
                     * @param objects
                     * // 以上三个参数和基于接口的动态代理中invoke方法是一样的
                     * @param methodProxy：当前执行方法的代理对象
                     * @return
                     * @throws Throwable
                     */
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        // 提供增强的代码
                        // 1.获取方法执行的参数
                        Float money = (Float) objects[0];
                        // 2.判断当前方法是不是销售
                        if ("saleProduct".equals(method.getName())) {
                            return method.invoke(producer, money * 0.8f);
                        }
                        return null;
                    }
                });
        cglibProducer.saleProduct(1000f);
    }
}
