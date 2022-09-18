package designpattern.adapter;

import common.IntConstant;

/**
* @description 适配器模式：220V设备
* @author xindaqi
* @since 2021-02-11 07:58:51
*/
public class TwoHundredTwentyVoltageDevice implements IPowerSupplyAdapter {

    @Override 
    public int twoHundredTwentyVoltage() {
        return IntConstant.TWOHUNDREDTWENTY;
    }
    
}