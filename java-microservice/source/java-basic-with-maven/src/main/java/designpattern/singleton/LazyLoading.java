package designpattern.singleton;

/**
* @description 单例：懒加载
* @author xindaqi
* @since 2021-02-10 15:24:43
*/
public class LazyLoading {

    private static LazyLoading instance;

    private LazyLoading() {}

    private static LazyLoading getInstance() {
        if(instance == null) {
            instance = new LazyLoading();
        }
        return instance;
    }
    
}