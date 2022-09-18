package designpattern.observer;

import java.util.logging.Logger;

/**
* @description 观察者模式：观察者实现
* @author xindaqi
* @since 2021-02-12 12:59:49
*/
public class Observer implements IObserver {

    private static final Logger logger = Logger.getLogger("Observer");

    @Override
    public void update() {
        logger.info("消息：新增/取消订阅");
    }
    
}