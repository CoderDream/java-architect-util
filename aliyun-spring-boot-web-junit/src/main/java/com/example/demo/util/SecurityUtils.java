package com.example.demo.util;

/**
 * 安全服务工具类
 *
 * @author ruoyi
 */
public class SecurityUtils {
    /**
     * 返回md5
     *
     * @param val
     * @return
     */

    public static String md5(String val) {
        return Md5Utils.hash(val);
    }

    /**
     * 加密密码
     *
     * @param password
     * @return
     */
    public static String encryptPassword(String password) {
        return Md5Utils.hash(password);
    }

    /**
     * 检查密码是否匹配
     *
     * @param oldpass
     * @param newpass
     * @return
     */
    public static boolean matchesPassword(String oldpass, String newpass) {
        if (StringUtils.isEmpty(newpass)) {
            return false;
        }
        if (!oldpass.equals(newpass)) {
            return false;
        }
        return true;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    public static void main(String[] args) {
        //"21232f297a57a5a743894a0e4a801fc3"
        System.out.println(md5("cw123456CW!"));
    }
}
