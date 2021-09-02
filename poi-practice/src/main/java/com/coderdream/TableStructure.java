package com.coderdream;

/**
 */
public class TableStructure {

	/** 表名 */
	private String tableName;
	/** 表说明 */
	private String tableComment;
	/** 字段名 */
	private String columnName;
	/** 标识 */
	private String identity;
	/** 主键 */
	private String primaryKey;
	/** 类型 */
	private String type;
	/** 占用字节数 */
	private String occupancyByteSize;
	/** 长度 */
	private String length;
	/** 小数位数 */
	private String decimalDigits;
	/** 允许空 */
	private String nullFlag;
	/** 默认值 */
	private String defaultValue;
	/** 字段说明 */
	private String columnComment;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOccupancyByteSize() {
		return occupancyByteSize;
	}

	public void setOccupancyByteSize(String occupancyByteSize) {
		this.occupancyByteSize = occupancyByteSize;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDecimalDigits() {
		return decimalDigits;
	}

	public void setDecimalDigits(String decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	public String getNullFlag() {
		return nullFlag;
	}

	public void setNullFlag(String nullFlag) {
		this.nullFlag = nullFlag;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

}