package com.coderdream;

public class Constants {
	

	public static final String ENCODING = "utf-8";
	
	public static String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
	
	/** yyyy-MM-dd HH:mm:ss:SSS */
	public static final String COMPLEX_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

	/** yyyyMMddHHmmssSSS */
	public static final String COMPLEX_DATE_FORMAT2 = "yyyyMMddHHmmssSSS";
	
	public static String TABLE_STRUCTURE_FILE_NAME = "TableStructure.xlsx";
	
//	public static String DATA_FILE_NAME = "AutoTest.xlsx";
	
	public static String DATA_FILE_NAME = "Data13.xlsx";
	
	public static String EXCAVATOR_FILE_NAME = "挖掘机.xlsx";
	
	public static String CATEGORY_OIL_FEE = "加油费";
	
	public static String CATEGORY_SERVICE = "维修保养";
	
	public static String CATEGORY_LOAD = "装车";
	
	public static String CATEGORY_LOAD_BIG = "大车";
	
	public static String CATEGORY_LOAD_SMALL = "小车";
	
	public static String CATEGORY_STAND_BY = "台班";
	
	public static String CATEGORY_START_FEE = "进场费";
	
	public static String CATEGORY_SETTLING_CHARGE = "结余";
	
	/** 四化建 */
	public static String LOCATION_ONE = "四化建";
	
	/** 步行街 */
	public static String LOCATION_TWO = "步行街";
	
	/** 南湖公园 */
	public static String LOCATION_THREE = "南湖公园";
	
	public static String DECIMAL_FORMAT="###################.###########";

	/** 项目统计开始时间 */
	public static String PROJECT_START_DATE = "2017-01-01";

	/** 项目统计结束时间 */
	public static String PROJECT_END_DATE = "2017-06-30";

	/** 项目周期最小天数 */
	public static int PROJECT_PERIOD_MIN = 7;

	/** 项目周期最大天数 */
	public static int PROJECT_PERIOD_MAX = 100;

	/** 项目周期最大天数是项目平均周期的倍数 */
	public static double PROJECT_PERIOD_AVG_TIMES = 2.1;

	/** 空闲随机数最小值 */
	public static int IDEL_RANDOM_MIN = 1;
	
	/** 空闲随机数最大值 */
	public static int IDEL_RANDOM_MAX = 100;

	/** 空闲最小天数 */
	public static int IDEL_RANDOM_DAYS_MIN = 2;
	
	/** 空闲最大天数 */
	public static int IDEL_RANDOM_DAYS_MAX = 7;

	/** BSM评价比率最小值的10倍 */
	public static Integer BSM_RATE_MIN = 8;

	/** BSM评价比率最大值的10倍 */
	public static Integer BSM_RATE_MAX = 15;

	/** BSM单价 */
	public static Integer PDRD_PRICE = 15000;

	/** 奖金 */
	public static Integer PDRD_BONUS = 7500;

	/** 未评价 */
	public static String BSM_STATE_DEFAULT = "1";
	
	/** 已分配 */
	public static String BSM_STATE_NOT_CONFIRM = "2";

	/** 已评价 */
	public static String BSM_STATE_CONFIRM = "3";

}
