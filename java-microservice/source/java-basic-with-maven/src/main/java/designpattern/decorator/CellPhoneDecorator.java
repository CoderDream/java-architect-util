package designpattern.decorator;

/**
* @description 装饰器模式：手机装饰器
* @author xindaqi
* @since 2021-02-11 15:11:58
*/
public abstract class CellPhoneDecorator implements ICellPhone {

    ICellPhone cellPhone;

    public CellPhoneDecorator(ICellPhone cellPhone) {
        this.cellPhone = cellPhone;
    }

    @Override
    public String makePhone() {
        return cellPhone.makePhone();
    }
    
}