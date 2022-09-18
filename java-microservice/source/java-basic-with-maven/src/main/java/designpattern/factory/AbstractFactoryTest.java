package designpattern.factory;

import java.util.logging.Logger;

/**
* @description 工厂模式：抽象工厂测试
* @author xindaqi
* @since 2021-02-10 23:26:54
*/

public class AbstractFactoryTest {
    private static final Logger logger = Logger.getLogger("AbstractFactoryTest");

    public static void main(String[] args) {
        String phone = "icellphone";
        String phoneBrand = "apple";
        AbstractPhoneFactory phoneFactory = FactoryGenerator.getFactory(phone);
        ICellPhone cellPhone = phoneFactory.getCellPhone(phoneBrand);
        logger.info("手机品牌：" + cellPhone.makePhone());
    }
    
}