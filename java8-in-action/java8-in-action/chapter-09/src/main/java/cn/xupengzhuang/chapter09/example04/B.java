package cn.xupengzhuang.chapter09.example04;

public interface B extends A {
    default void hello(){
        System.out.println("hello,from B");
    }
}
