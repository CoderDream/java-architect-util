package common;

/**
* @description 字符串常量
* @author xindaqi
* @since 2021-02-10 22:09:24
*/
public class StringConstant {

    /**苹果手机 */
    public static final String APPLE = "apple";

    /**三星手机 */
    public static final String SAMSUNG = "samsung";

    /**手机工厂接口 */
    public static final String CELLPHONE_FACTORY = "ICellPhone";

    /**不能实例化 */
    public static final String PRIVATE_INSTANCE_ERROR = "不能实例化";

    public static final String FIVE_VOLTAGE_DEVICE = "5V-device";

    public static final String TWELEVE_VOLTAGE_DEVICE = "12V-device";

    public static final String TWOHUNDREDTWENTY_VOLTAGE_DEVICE = "220V-device";
    

    public static final String CHARGING = "charging";

    /**Try.**/
    public static final String TRY = "try->";

    /**Catch.**/
    public static final String CATCH = "catch->";

    /**Finally.**/
    public static final String FINALLY = "finally!";

    /**From.**/
    public static final String FROM = "from:";

    /**Result.**/
    public static final String RESULT = "result:";

    /**End.**/
    public static final String END = "end!";

    private StringConstant() {
        throw new AssertionError(StringConstant.class.getName() + PRIVATE_INSTANCE_ERROR);
    }
    
}