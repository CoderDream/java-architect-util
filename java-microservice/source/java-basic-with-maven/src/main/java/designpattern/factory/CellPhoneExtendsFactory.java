package designpattern.factory;

import common.StringConstant;
/**
* @description 工厂模式：继承抽象工厂的工厂
* @author xindaqi
* @since 2021-02-10 23:04:26
*/
public class CellPhoneExtendsFactory extends AbstractPhoneFactory {

    @Override
    public ICellPhone getCellPhone(String cellPhoneBrand) {
        if(null == cellPhoneBrand) {
            return null;
        }

        if(cellPhoneBrand.equalsIgnoreCase(StringConstant.APPLE)) {
            return new Apple();
        } else if(cellPhoneBrand.equalsIgnoreCase(StringConstant.SAMSUNG)) {
            return new Samsung();
        }
        return null;
    }
    
}