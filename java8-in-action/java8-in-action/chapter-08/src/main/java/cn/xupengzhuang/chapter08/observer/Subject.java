package cn.xupengzhuang.chapter08.observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObserver(String tweet);
}
