package designpattern.decorator;

import common.StringConstant;

/**
* @description 装饰器模式：扩展手机装饰器功能,为手机充电
* @author xindaqi
* @since 2021-02-11 19:17:42
*/
public class AdapterCellPhoneDecorator extends CellPhoneDecorator {

    public AdapterCellPhoneDecorator(ICellPhone cellPhone) {
        super(cellPhone);
    }

    @Override
    public String makePhone() {
        StringBuilder result = new StringBuilder();
        result.append(cellPhone.makePhone());
        result.append(" and ");
        result.append(chargingForPhone());
        return  result.toString();

    }

    public String chargingForPhone() {
        return StringConstant.CHARGING;
    }
    
}