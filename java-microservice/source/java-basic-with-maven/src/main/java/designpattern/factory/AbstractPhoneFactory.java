package designpattern.factory;

/**
* @description 工厂模式：抽象工厂
* @author xindaqi
* @since 2021-02-10 23:01:23
*/
public abstract class AbstractPhoneFactory {

    /**
    * description: 获取手机工厂
    * @since 2021-02-10 23:02:40
    * @param cellPhoneBrand 手机品牌
    * @return 手机工厂接口
    */
    public abstract ICellPhone getCellPhone(String cellPhoneBrand);
    
}