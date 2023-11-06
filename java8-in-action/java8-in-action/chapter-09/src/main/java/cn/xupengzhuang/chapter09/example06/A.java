package cn.xupengzhuang.chapter09.example06;

public interface A {
    default void hello(){
        System.out.println("hello,from A");
    }
}
