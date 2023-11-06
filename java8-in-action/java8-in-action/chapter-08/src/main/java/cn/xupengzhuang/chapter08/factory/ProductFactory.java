package cn.xupengzhuang.chapter08.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductFactory {

    final static Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan",Loan::new);
        map.put("stock",Stock::new);
        map.put("bond",Bond::new);
    }

    public static Product createProductByLambda(String name){
        Supplier<Product> supplier = map.get(name);
        if (null != supplier){
            return supplier.get();
        }
        throw new IllegalArgumentException("No such product " + name);
    }


    public static Product createProduct(String name){
        switch (name){
            case "bond":
                return new Bond();
            case "loan":
                return new Loan();
            case "stock":
                return new Stock();
            default:throw new RuntimeException("No such Product:" + name);
        }
    }
}
