package designpattern.adapter;

import common.StringConstant;

/**
* @description 适配器模式：电源适配器
* @author xindaqi
* @since 2021-02-11 08:20:14
*/
public class PowerSupplyAdapter implements IPowerSupply {

    IPowerSupplyAdapter ipowerSupplyAdapter;

    public PowerSupplyAdapter(String deviceType) {
        if(deviceType.equalsIgnoreCase(StringConstant.FIVE_VOLTAGE_DEVICE)) {
            ipowerSupplyAdapter = new FiveVoltageDevice();
        } else if(deviceType.equalsIgnoreCase(StringConstant.TWELEVE_VOLTAGE_DEVICE)) {
            ipowerSupplyAdapter = new TweleveVoltageDevice();
        } else if(deviceType.equalsIgnoreCase(StringConstant.TWOHUNDREDTWENTY_VOLTAGE_DEVICE)) {
            ipowerSupplyAdapter = new TwoHundredTwentyVoltageDevice();
        }
        
    }

    @Override
    public int voltage(String deviceType) {
        if(deviceType.equalsIgnoreCase(StringConstant.FIVE_VOLTAGE_DEVICE)) {
            return ipowerSupplyAdapter.fiveVoltage();
        } else if(deviceType.equalsIgnoreCase(StringConstant.TWELEVE_VOLTAGE_DEVICE)) {
            return ipowerSupplyAdapter.tweleveVoltage();
        } else if(deviceType.equalsIgnoreCase(StringConstant.TWOHUNDREDTWENTY_VOLTAGE_DEVICE)) {
            return ipowerSupplyAdapter.twoHundredTwentyVoltage();
        }
        return 0;
    }

    
}