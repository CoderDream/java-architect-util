package cn.xupengzhuang.chapter09.example03;

public interface B extends A{
    default void hello(){
        System.out.println("hello,from B");
    }
}
