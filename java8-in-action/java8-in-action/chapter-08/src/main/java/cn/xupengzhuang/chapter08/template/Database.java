package cn.xupengzhuang.chapter08.template;

import java.util.HashMap;
import java.util.Map;

class Database {
    private static Map<Long,Customer> users = new HashMap<>();
    static {
        users.put(1l,new Customer(1l,"张三","abc111"));
        users.put(2l,new Customer(2l,"李四","abc222"));
        users.put(3l,new Customer(3l,"王五","abc333"));
    }

    public static Customer getCustomById(Long id){
        Customer customer = users.get(id);
        return customer;
    }
}
