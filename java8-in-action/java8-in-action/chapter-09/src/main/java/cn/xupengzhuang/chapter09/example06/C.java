package cn.xupengzhuang.chapter09.example06;

public interface C extends A{
    default void hello(){
        System.out.println("hello,from C");
    }
}
