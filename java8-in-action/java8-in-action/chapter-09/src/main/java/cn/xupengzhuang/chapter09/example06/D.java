package cn.xupengzhuang.chapter09.example06;

public class D implements B,C{
    public static void main(String[] args) {
        new D().hello();
    }

    @Override
    public void hello() {
        C.super.hello();
    }
}
