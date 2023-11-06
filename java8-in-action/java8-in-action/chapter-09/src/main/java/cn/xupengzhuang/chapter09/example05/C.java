package cn.xupengzhuang.chapter09.example05;

/**
 * 此时C必须显示的声明
 */
public class C implements A, B {
    public static void main(String[] args) {
        new C().hello();
    }

    @Override
    public void hello() {
        B.super.hello();
    }
}
