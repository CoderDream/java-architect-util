package cn.xupengzhuang.chapter03;

import java.util.List;
import java.util.function.Consumer;

public class IteratorUtil {
    public static <T> void forEach(List<T> list, Consumer<T> c){
        for (T t : list){
            c.accept(t);
        }
    }
}
