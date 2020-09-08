package demo.cglib;

/**
 * 一个生产者
 *
 * @author Li W
 * @version 1.8
 * @date 2020/9/4 10:45
 */
public class Producer{

    /**
     * 销售
     * @param money
     */
    public void saleProduct(float money) {
        System.out.println("销售产品，并拿到钱："+money);
    }

    /**
     * 售后
     * @param money
     */
    public void afterService(float money) {
        System.out.println("售后服务，并拿到钱："+money);
    }
}
