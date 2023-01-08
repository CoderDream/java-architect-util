package com.demo.merchant.domain.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 辅助工具类
 *
 * @author zbk
 */
public class CommonUtils {
    private final static Logger logger = Logger.getLogger(CommonUtils.class);

    // 默认日期格式
    public static final String DATE_FMT = "yyyy-MM-dd"; // 日期

    public static final String TIME_FMT = "HH:mm:ss"; // 时间

    public static final String DATE_TIME_FMT = "yyyy-MM-dd HH:mm:ss"; // 日期时间

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm";

    public static final String MONTH_FMT = "yyyy-MM"; // 日期

    public static final String DATA_TIME_FMT2 = "yyyy年MM月dd日 HH:mm";

    //缓存前缀
    public final static String REDIS_IDENT = "O2O_FS:";
    //未验证粉丝编号 不要做修改，否则数据统计会出现错误
    public final static String NO_VALIDATION = "no-validation";
    // 验证的正则表达式
    private static final String REG_ALPHA = "^[a-zA-Z]+$";

    private static final String REG_ALPHANUM = "^[a-zA-Z0-9]+$";

    private static final String REG_NUMBER = "^\\d+$";

    private static final String REG_START_NUMBER = "^(\\d+)(.*)";

    private static final String REG_INTEGER = "^[-+]?[1-9]\\d*$|^0$/";

    private static final String REG_FLOAT = "[-\\+]?\\d+(\\.\\d+)?$";

    private static final String REG_PHONE = "^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$";

    private static final String REG_MOBILE = "^((\\+86)|(86))?(1)\\d{10}$";

    private static final String REG_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    private static final String REG_ZIP = "^[1-9]\\d{5}$";

    private static final String REG_IP = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final String REG_URL = "^(http|https|ftp):\\/\\/(([A-Z0-9][A-Z0-9_-]*)(\\.[A-Z0-9][A-Z0-9_-]*)+)(:(\\d+))?\\/?/i";

    private static final String REG_CHINESE = "^[\\u0391-\\uFFE5]+$";

    private static final String REG_MONEY = "[\\-\\+]?\\d+(\\.\\d+)?$";

    /**
     * 过滤关键字list
     */
    private static List<String> FILTER_STRINGS_LIST = null;

    /**
     * 把分转换为元，并且保留小数点2位
     *
     * @param fen
     * @return
     */
    public static Double moneyToYuan(String fen) {
        try {
            if (CommonUtils.isNull(fen) || "null".equals(fen)) {
                return 0d;
            }
            long amont = Long.parseLong(fen);
            return moneyToyuan(amont);
        } catch (Exception ex) {
            return 0d;
        }
    }

    /**
     * 把分转换为元，并且保留小数点2位
     *
     * @param fen
     * @return
     */
    public static Double moneyToyuan(Long fen) {
        if (CommonUtils.isNull(fen)) {
            return 0d;
        }
        return div(fen.doubleValue(), 100D, 2);
    }

    /**
     * 四舍五入保留指定位数的小数
     *
     * @param number
     * @param index
     * @return
     */
    public static double myRound(double number, int index) {
        double result = 0;
        double temp = Math.pow(10, index);
        result = Math.rint(number * temp) / temp;
        return result;
    }

    /**
     * 可以用于判断Object,String,Map,Collection,String,Array是否为空
     */
    public static boolean isNull(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            if (((String) value).trim().replaceAll("\\s", "").equals("")) {
                return true;
            }
        } else if (value instanceof Collection) {
            if (((Collection) value).isEmpty()) {
                return true;
            }
        } else if (value.getClass().isArray()) {
            if (Array.getLength(value) == 0) {
                return true;
            }
        } else if (value instanceof Map) {
            if (((Map) value).isEmpty()) {
                return true;
            }
        } else {
            return false;
        }
        return false;

    }

    public static boolean isNull(Object value, Object... items) {
        if (isNull(value) || isNull(items)) {
            return true;
        }
        for (Object item : items) {
            if (isNull(item)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotNull(Object value) {
        return !isNull(value);
    }

    public static boolean isNotNull(Object value, Object... items) {
        return !isNull(value, items);
    }

    /**
     * 将数组转换成List<Map<String,Object>>对象
     *
     * @param array
     * @return
     */
    public static List<Map<String, Object>> arrayToList(String[] array) {
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < array.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("value", i);
            item.put("text", array[i]);
            items.add(item);
        }
        return items;
    }

    public static Map<String, Object> arrayToMap(String[] array) {
        Map<String, Object> maps = new HashMap<String, Object>();
        for (int i = 0; i < array.length; i++) {
            maps.put(String.valueOf(i), array[i]);
        }
        return maps;
    }

    public static Map<String, Object> arrToMap(String[] array) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], array[i]);
        }
        return map;
    }

    public static boolean isAlpha(String value) {
        if (isNull(value)) return false;
        return Pattern.matches(REG_ALPHA, value);
    }

    public static boolean isAlphanum(String value) {
        if (isNull(value)) return false;
        return Pattern.matches(REG_ALPHANUM, value);
    }

    public static boolean isNumber(String value) {
        if (isNull(value)) return false;
        return Pattern.matches(REG_NUMBER, value);
    }

    public static boolean isStartNumber(String value) {
        if (isNull(value)) return false;
        return Pattern.matches(REG_START_NUMBER, value);
    }

    public static boolean isInteger(String value) {
        if (isNull(value)) return false;
        return Pattern.matches(REG_INTEGER, value);
    }

    public static boolean isFloat(String value) {
        if (isNull(value)) return false;
        return Pattern.matches(REG_FLOAT, value);
    }

    public static boolean isMoney(String value) {
        if (isNull(value)) return false;
        return Pattern.matches(REG_MONEY, value);
    }

    public static boolean isPhone(String value) {
        if (isNull(value)) return false;
        return Pattern.matches(REG_PHONE, value);
    }

    public static boolean isMobile(String value) {
        if (isNull(value)) return false;
        return Pattern.matches(REG_MOBILE, value);
    }

    public static boolean isEmail(String value) {
        if (isNull(value)) return false;
        return Pattern.matches(REG_EMAIL, value);
    }

    public static boolean isZip(String value) {

        return Pattern.matches(REG_ZIP, value);
    }

    public static boolean isIP(String value) {

        return Pattern.matches(REG_IP, value);
    }

    public static boolean isURL(String value) {

        return Pattern.matches(REG_URL, value);
    }

    public static boolean isChinese(String value) {

        return Pattern.matches(REG_CHINESE, value);
    }

    /**
     * 验证是否为合法身份证
     *
     * @param value
     * @return
     */
    public static boolean isIdcard(String value) {
        value = value.toUpperCase();
        if (!(Pattern.matches("^\\d{17}(\\d|X)$", value) || Pattern.matches("\\d{15}$", value))) {
            return false;
        }
        int provinceCode = Integer.parseInt(value.substring(0, 2));
        if (provinceCode < 11 || provinceCode > 91) {
            return false;
        }
        return true;
    }

    /**
     * 验证是否为日期
     *
     * @param value
     * @return
     */
    public static boolean isDate(String value) {
        if (value == null || value.isEmpty())
            return false;
        try {
            new SimpleDateFormat().parse(value);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 内容摘要： 检查邮件地址是否合法
     *
     * @param email
     * @return 如果合法返回true
     */
    public static boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }
        Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 内容摘要：取得小数的有效位数
     *
     * @return
     */
    public static String getDoubleEff(Double value) {
        if (value != null) {
            DecimalFormat formater = new DecimalFormat("#0.##");
            String rsValue = formater.format(value);
            return rsValue;
        } else {
            return "0";
        }
    }

    //构建一个随机数
    public static String buildRandom() {
        int r1 = (int) (Math.random() * 10);
        int r2 = (int) (Math.random() * 10);
        int r3 = (int) (Math.random() * 10);
        long now = System.currentTimeMillis();
        return String.valueOf(now) + String.valueOf(r1) + String.valueOf(r2) + String.valueOf(r3);
    }

    /**
     * 循环删除目录以及目录下的文件
     *
     * @param filePath
     */
    public static void delFiles(String filePath) {
        if (filePath == null)
            return;
        File f = new File(filePath);
        delFile(f);
        f.delete();
    }

    /**
     * 递归方法  delFiles 所调用
     *
     * @param f
     */
    private static void delFile(File f) {
        if (f == null)
            return;
        if (f.isDirectory()) {
            File[] fs = f.listFiles();
            for (File fstmp : fs)
                delFile(fstmp);
        } else {
            f.delete();
        }
    }


    /**
     * 把数字格式化返回  保留小数点后2位
     *
     * @param d
     * @return
     */
    public static Double DoubleFormat(Double d) {
        return DoubleFormat(d, null);
    }


    /**
     * 把数字格式化返回
     *
     * @param d
     * @param fmt 如果未设置格式，默认保留小数点后2位
     * @return
     */
    public static Double DoubleFormat(Double d, String fmt) {
        if (d == null)
            d = 0D;
        if (fmt == null)
            fmt = "#0.00";
        DecimalFormat df = new DecimalFormat(fmt);
        df.setRoundingMode(RoundingMode.DOWN);
        return Double.valueOf(df.format(d));
    }

    /**
     * 把数字字符串格式化后返回
     *
     * @param d
     * @param fmt 如果未设置格式，默认保留小数点后2位
     * @return
     */
    public static Double DoubleFormat(String d, String fmt) {
        if (!isNumber(d)) {
            return null;
        }
        return DoubleFormat(Double.valueOf(d), fmt);
    }

    /**
     * 格式化日期
     */
    public static String formatDateTime(Date date) {

        return formatDateTime(DATE_TIME_FMT, date);
    }

    public static String formatDateTime(String fmt, Date date) {
        if (isNull(fmt) || isNull(date)) {
            return null;
        }
        String temp = new SimpleDateFormat(fmt).format(date);

        return temp;
    }

    /**
     * 转换为日期对象
     */
    public static Date dateStrToDate(String date) {
        Date temp = null;
        try {
            temp = new SimpleDateFormat(DATE_FMT).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static Date dateStrToDate(String fmt, String date) {
        Date temp = null;
        try {
            temp = new SimpleDateFormat(fmt).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 处理特殊字符
     *
     * @param str
     * @return
     */
    public static String handleEmojiChaaracter(String str) {
        if (isNull(str)) {
            return "";
        }
        str = str.replace("\n", "").replace("\r", "").replace("\"", "").replace(",", "");
        StringBuilder sbStr = new StringBuilder();
        int l = str.length();
        for (int j = 0; j < l; j++) {
            char charAt = str.charAt(j);
            if (isEmojiCharacter(charAt)) {
                sbStr.append(charAt);
            }
        }
        return sbStr.toString();
    }

    /**
     * 是否属于特殊字符
     *
     * @param codePoint
     * @return
     */
    static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();

    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String divide(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 读取关键字
     *
     * @return
     */
    public static List<String> readKeyword() {
        if (isNotNull(FILTER_STRINGS_LIST))
            return FILTER_STRINGS_LIST;
        FILTER_STRINGS_LIST = Lists.newArrayList();
        InputStream in = null;
        InputStreamReader read = null;
        try {
//            List<String> list = Lists.newArrayList();
            in = new CommonUtils().getClass().getResourceAsStream("/keyword.txt");
            read = new InputStreamReader(in, "UTF-8");//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                FILTER_STRINGS_LIST.add(lineTxt);
            }
            return FILTER_STRINGS_LIST;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (CommonUtils.isNotNull(in) && CommonUtils.isNotNull(read)) {
                    in.close();
                    read.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 读取关键字
     *
     * @return
     */
    public static List<String> readKeywords() {
        if (isNotNull(FILTER_STRINGS_LIST))
            return FILTER_STRINGS_LIST;
        FILTER_STRINGS_LIST = Lists.newArrayList();
        InputStream in = null;
        InputStreamReader read = null;
        try {
            BufferedReader reader = null;
            File file = new File(new CommonUtils().getClass().getResource("/").getPath() + "keyword.txt");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = reader.readLine()) != null) {
                FILTER_STRINGS_LIST.add(lineTxt);
            }
            return FILTER_STRINGS_LIST;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (CommonUtils.isNotNull(in) && CommonUtils.isNotNull(read)) {
                    in.close();
                    read.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static String validateCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += random.nextInt(10);
        }
        return code;
    }

    /**
     * 获取异常消息
     *
     * @param ex
     * @return
     */
    public static String getExcetionMessage(Exception ex) {
        StringWriter writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer, true));
        return writer.toString();
    }

    public static void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();
        map.put("te", "11");
        if (map.containsKey("te")) {
            System.out.println("111111111111111111111111111111");
        }
    }
}
