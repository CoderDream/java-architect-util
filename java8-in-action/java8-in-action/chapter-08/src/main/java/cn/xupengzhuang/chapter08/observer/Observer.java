package cn.xupengzhuang.chapter08.observer;

/**
 * 用于将不同的观察者聚合在一起
 */
public interface Observer {
    void notify(String tweet);
}
