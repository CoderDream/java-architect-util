package common;

/**
* @description 浮点常量
* @author xindaqi
* @since 2021-02-11 23:44:18
*/
public class FloatConstant {

    public static final float NINETY_PERCENT_OFF = 0.9f;

    public static final float FIFTY_PERCENT_OFF = 0.5f;

    private FloatConstant() {
        throw new AssertionError(StringConstant.class.getName() + StringConstant.PRIVATE_INSTANCE_ERROR);
    }
    
}