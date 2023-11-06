package cn.xupengzhuang.chapter09.example05;

public interface B {
    default void hello(){
        System.out.println("hello,from B");
    }
}
