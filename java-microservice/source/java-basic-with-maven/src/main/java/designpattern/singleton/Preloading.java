package designpattern.singleton;

/**
* @description 单例模式：预加载
* @author xindaqi
* @since 2021-02-10 17:04:04
*/
public class Preloading {

    private static Preloading instance = new Preloading();

    private Preloading() {}

    private Preloading getInstance() {
        return instance;
    }
    
}