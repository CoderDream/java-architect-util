package designpattern.adapter;

import common.StringConstant;
import java.util.logging.Logger;

/**
* @description 适配器模式：电源适配器
* @author xindaqi
* @since 2021-02-11 08:38:26
*/
public class PowerSupply implements IPowerSupply {

    private static final Logger logger = Logger.getLogger("PowerSupply");

    PowerSupplyAdapter powerSupplyAdapter;

    @Override
    public int voltage(String deviceType) {
        if(deviceType.equalsIgnoreCase(StringConstant.FIVE_VOLTAGE_DEVICE) || deviceType.equalsIgnoreCase(StringConstant.TWELEVE_VOLTAGE_DEVICE) || deviceType.equalsIgnoreCase(StringConstant.TWOHUNDREDTWENTY_VOLTAGE_DEVICE)) {
            logger.info("电源适配器：" + deviceType);
            powerSupplyAdapter = new PowerSupplyAdapter(deviceType);
            return powerSupplyAdapter.voltage(deviceType);
        } else {
            logger.info("电源适配器不支持该设备: " + deviceType);
            return 0;
        } 
    }
    
}