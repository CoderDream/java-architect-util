package designpattern.decorator;

import common.StringConstant;

/**
* @description Apple手机
* @author xindaqi
* @since 2021-02-10 22:08:28
*/
public class Apple implements ICellPhone {

    @Override
    public String makePhone() {
        return StringConstant.APPLE;
    }
    
}