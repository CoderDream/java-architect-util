package com.coderdream;

/**
 * @author Hongten
 * @created 2014-5-21
 */
public class Common {

	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";

	public static final String EMPTY = "";
	public static final String POINT = ".";
	public static final String LIB_PATH = Class.class.getClass().getResource("/").getPath();;
	public static final String STUDENT_INFO_XLS_PATH = LIB_PATH + "/student_info" + POINT + OFFICE_EXCEL_2003_POSTFIX;
	public static final String STUDENT_INFO_XLSX_PATH = LIB_PATH + "/student_info" + POINT + OFFICE_EXCEL_2010_POSTFIX;
	public static final String STUDENT_INFO_XLS_OUT_PATH = LIB_PATH + "/student_info_2003-2007.xls";
	public static final String STUDENT_INFO_XLSX_OUT_PATH = LIB_PATH + "/student_info_2010.xlsx";
	public static final String NOT_EXCEL_FILE = " : Not the Excel file!";
	public static final String PROCESSING = "Processing...";
	public static final String WRITE_DATA = "write data to file : ";

}