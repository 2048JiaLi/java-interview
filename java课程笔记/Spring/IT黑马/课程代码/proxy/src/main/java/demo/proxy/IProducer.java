package demo.proxy;

/**
 * 对生成厂家要求的接口
 */
public interface IProducer {
    void saleProduct(float money);

    void afterService(float money);
}
