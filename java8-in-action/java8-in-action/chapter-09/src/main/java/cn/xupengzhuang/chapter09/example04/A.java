package cn.xupengzhuang.chapter09.example04;

public interface A {
    default void hello(){
        System.out.println("hello,from A");
    }
}
