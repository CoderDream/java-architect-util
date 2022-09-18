package designpattern.template;

/**
* @description 模板模式：洗衣机
* @author xindaqi
* @since 2021-02-12 14:54:45
*/
public abstract class BaseWashingMachine {

    /**
    * description: 开机
    * @since 2021-02-12 15:06:26
    * @param 
    * @return 
    */
    abstract void powerOn();

    /**
    * description: 运行
    * @since 2021-02-12 15:06:42
    * @param 
    * @return 
    */
    abstract void work();

    /**
    * description: 关机
    * @since 2021-02-12 15:06:57
    * @param 
    * @return 
    */
    abstract void powerOff();

    /**
    * description: 模板
    * @since 2021-02-12 15:07:16
    * @param 
    * @return 
    */
    public void operation() {
        powerOn();
        work();
        powerOff();
    }
    
}