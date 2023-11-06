package cn.xupengzhuang.chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MapUtil {
    public static <T,R> List<R> map(List<T> list, Function<T,R> f){
        List<R> result = new ArrayList<>();
        for (T t : list){
            R r = f.apply(t);
            result.add(r);
        }
        return result;
    }
}
