package designpattern.observer;

/**
* @description 观察者模式：主题
* @author xindaqi
* @since 2021-02-12 12:31:52
*/
public interface ISubject {

    /**
    * description: 订阅
    * @since 2021-02-12 12:32:37
    * @param observer 观察者
    * @return 
    */
    void subscribe(IObserver observer);

    /**
    * description: 取消订阅
    * @since 2021-02-12 12:33:20
    * @param observer 观察者
    * @return 
    */
    void unsubscribe(IObserver observer);

    /**
    * description: 通知变更
    * @since 2021-02-12 12:35:02
    * @param 
    * @return 
    */
    void notifyChanged();
    
}