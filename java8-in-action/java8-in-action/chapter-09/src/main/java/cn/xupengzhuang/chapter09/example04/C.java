package cn.xupengzhuang.chapter09.example04;

/**
 * 类中声明的方法具有更高的优先级。D并未覆盖hello方法，可是它实现了接口A。所以它就拥有了接口A的默认方法
 * 如果类或者父类没有对应的方法，那么就应该选择提供了最具体实现的接口中的方法。
 */
public class C extends D implements A, B {
    public static void main(String[] args) {
        new C().hello();
    }
}
