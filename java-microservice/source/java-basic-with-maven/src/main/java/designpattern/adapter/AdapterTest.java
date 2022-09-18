package designpattern.adapter;

import java.util.logging.Logger;

/**
* @description 适配器模式：测试用例
* @author xindaqi
* @since 2021-02-11 09:08:09
*/
public class AdapterTest {

    private static final Logger logger = Logger.getLogger("AdapterTest");

    public static void main(String[] args) {
        PowerSupply powerSupply = new PowerSupply();
        String fiveDevice = "5v-device";

        String tweleveDevice = "12v-device";

        String tenDevice = "10v-device";

        int five = powerSupply.voltage(fiveDevice);
        logger.info("电源适配器输出电压：" + five + "V");
        int tweleve = powerSupply.voltage(tweleveDevice);
        logger.info("电源适配器输出电压：" + tweleve + "V");
        int zero = powerSupply.voltage(tenDevice);
        logger.info("电源适配器输出电压：" + zero + "V");
    }
    
}