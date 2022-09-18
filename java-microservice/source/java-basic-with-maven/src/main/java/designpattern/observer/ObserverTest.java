package designpattern.observer;

import java.util.logging.Logger;

/**
* @description 观察者模式：测试样例
* @author xindaqi
* @since 2021-02-12 13:20:20
*/
public class ObserverTest {

    private static final Logger logger = Logger.getLogger("ObserverTest");

    public static void main(String[] args) {
        ISubject subject = new Subject();
        IObserver observer = new Observer();
        IObserver observerAnother = new Observer();
        subject.subscribe(observer);
        subject.subscribe(observerAnother);
        subject.notifyChanged();
        logger.info("==============");
        subject.unsubscribe(observerAnother);
        subject.notifyChanged();
    }
    
}