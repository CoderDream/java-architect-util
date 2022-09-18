package common;

/**
* @description 整型常量
* @author xindaqi
* @since 2021-02-11 08:00:05
*/
public class IntConstant {

    public static final int FIVE = 5;

    public static final int TWELEVE = 12;

    public static final int TWOHUNDREDTWENTY = 220;

    private IntConstant() {
        throw new AssertionError(StringConstant.class.getName() + StringConstant.PRIVATE_INSTANCE_ERROR);
    }
    
}