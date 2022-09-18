package com.company.microserviceuser.constant;

/**
 * 字符串常量
 * @author xindaqi
 * @since 2020-12-29
 */

public class StringConstant {

    /**日期：yyyy-MM-dd HH:mm:ss **/
    public static final String DATE_Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";

    /**密码脱敏. */
    public static final String DESENSITIZATION_PSD = "****************";

    /** 私钥. */
    public static final String PRIVATE_TOKEN_JWT = "maibjiajjvhrby39zx654aeqo32rjfl6jpyd";

    private StringConstant() {
        throw new AssertionError(StringConstant.class.getName() + "不能实例化");
    }
}
