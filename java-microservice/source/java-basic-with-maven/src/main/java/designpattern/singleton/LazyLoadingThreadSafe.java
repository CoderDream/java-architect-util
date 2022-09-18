package designpattern.singleton;

/**
* @description 单例模式：懒加载，线程安全
* @author xindaqi
* @since 2021-02-10 20:40:43
*/
public class LazyLoadingThreadSafe {

    private static LazyLoadingThreadSafe instance;

    private LazyLoadingThreadSafe() {}

    public static synchronized LazyLoadingThreadSafe getInstance() {
        if(null == instance) {
            instance = new LazyLoadingThreadSafe();
        }
        return instance;
    }
    
}