package designpattern.singleton;

/**
* @description 单例模式：双检锁
* @author xindaqi
* @since 2021-02-10 17:54:03
*/
public class DoubleCheckLocking {

    private volatile static DoubleCheckLocking instance;

    private DoubleCheckLocking() {}

    public static DoubleCheckLocking getInstance() {
        if(null == instance) {
            synchronized(DoubleCheckLocking.class) {
                if(null == instance) {
                    instance = new DoubleCheckLocking();
                }
            }
        }
        return instance;
    }
}