package cn.xupengzhuang.chapter08.observer;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject{

    private final List<Observer> lists = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        lists.add(o);
    }

    @Override
    public void notifyObserver(String tweet) {
        lists.forEach(o -> o.notify(tweet));
    }
}
