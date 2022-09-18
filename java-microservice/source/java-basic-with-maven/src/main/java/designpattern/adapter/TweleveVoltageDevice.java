package designpattern.adapter;

import common.IntConstant;

/**
* @description 适配器模式：12V设备
* @author xindaqi
* @since 2021-02-11 07:56:16
*/
public class TweleveVoltageDevice implements IPowerSupplyAdapter {

    @Override
    public int tweleveVoltage() {
        return IntConstant.TWELEVE;
    }
    
}