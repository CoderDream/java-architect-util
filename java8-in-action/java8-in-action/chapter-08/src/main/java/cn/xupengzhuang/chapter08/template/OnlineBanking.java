package cn.xupengzhuang.chapter08.template;

/**
 * processCustomer方法搭建了在线银行算法的框架：获取客户提供的ID，然后提供服务让用户满意。
 * 不同的支行可以通过继承OnlineBanking类，对该方法提供差异化的实现
 */
public abstract class OnlineBanking {
    public void processCustom(Long id){
        Customer customer = Database.getCustomById(id);
        makeCustomHappy(customer);
    }
    public abstract void makeCustomHappy(Customer customer);
}

