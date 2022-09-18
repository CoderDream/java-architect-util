package designpattern.factory;

import java.util.logging.Logger;

/**
* @description 工厂模式：普通工厂测试
* @author xindaqi
* @since 2021-02-10 22:33:22
*/
public class NormalFactoryTest {

    private static final Logger logger = Logger.getLogger("NormalFactoryTest");

    public static void main(String[] args) {
        CellPhoneFactory cellPhoneFactory = new CellPhoneFactory();

        String apple = "apple";

        String samsung = "samsung";

        ICellPhone applePhone = cellPhoneFactory.getCellPhone(apple);
        logger.info("手机品牌：" + applePhone.makePhone());

        ICellPhone samsungPhone = cellPhoneFactory.getCellPhone(samsung);
        logger.info("手机品牌： " + samsungPhone.makePhone());

    }
    
}