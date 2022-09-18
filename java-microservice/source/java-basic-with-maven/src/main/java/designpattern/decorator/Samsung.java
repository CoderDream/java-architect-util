package designpattern.decorator;

import common.StringConstant;

/**
* @description SAMSUNG手机
* @author xindaqi
* @since 2021-02-10 22:20:24
*/
public class Samsung implements ICellPhone {

    @Override
    public String makePhone() {
        return StringConstant.SAMSUNG;
    }
    
}