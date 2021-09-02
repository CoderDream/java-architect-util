package com.coderdream;

public class TableCompareBean {

	private String tableName;

	private String leftColumnName;
	
	private String leftOthers;

	private Boolean compareResult;

	private String rightColumnName;

	private String rigthOthers;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getLeftColumnName() {
		return leftColumnName;
	}

	public void setLeftColumnName(String leftColumnName) {
		this.leftColumnName = leftColumnName;
	}

	public String getRightColumnName() {
		return rightColumnName;
	}

	public void setRightColumnName(String rightColumnName) {
		this.rightColumnName = rightColumnName;
	}

	public String getLeftOthers() {
		return leftOthers;
	}

	public void setLeftOthers(String leftOthers) {
		this.leftOthers = leftOthers;
	}

	public String getRigthOthers() {
		return rigthOthers;
	}

	public void setRigthOthers(String rigthOthers) {
		this.rigthOthers = rigthOthers;
	}

	public Boolean getCompareResult() {
		return compareResult;
	}

	public void setCompareResult(Boolean compareResult) {
		this.compareResult = compareResult;
	}

	@Override
	public String toString() {
		return "leftColumnName\t" + leftColumnName + ", leftOthers\t"
				+ leftOthers + ", compareResult\t" + compareResult
				+ ", rightColumnName\t" + rightColumnName + ", rigthOthers\t"
				+ rigthOthers + "]";
	}

}
