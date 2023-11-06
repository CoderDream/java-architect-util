package cn.xupengzhuang.chapter09.example03;

public interface A {
    default void hello(){
        System.out.println("hello,from A");
    }
}
