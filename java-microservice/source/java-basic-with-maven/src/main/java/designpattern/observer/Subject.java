package designpattern.observer;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
* @description 观察者模式：主题实现
* @author xindaqi
* @since 2021-02-12 12:39:09
*/
public class Subject implements ISubject {
    
    private List<IObserver>  iObserverList = new ArrayList<>();

    @Override 
    public void subscribe(IObserver observer) {
        iObserverList.add(observer);
    }

    @Override 
    public void unsubscribe(IObserver observer) {
        iObserverList.remove(observer);
    }

    @Override 
    public void notifyChanged() {
        iObserverList.stream().forEach(s -> s.update());
    }
}