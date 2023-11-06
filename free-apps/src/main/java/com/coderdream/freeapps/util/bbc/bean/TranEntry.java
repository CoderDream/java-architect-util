/**
 * Copyright 2023 lzltool.com
 */

package com.coderdream.freeapps.util.bbc.bean;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Auto-generated: 2023-10-19 09:37:57
 *
 * @author lzltool.com
 * @website https://www.lzltool.com/JsonToJava
 */

public class TranEntry {

	@JsonProperty("pos_entry")
	private PosEntry posEntry;
	@JsonProperty("exam_sents")
	private ExamSents examSents;
	private String tran;

	public void setPosEntry(PosEntry posEntry) {
		this.posEntry = posEntry;
	}
	public PosEntry getPosEntry() {
		return this.posEntry;
	}
	public void setExamSents(ExamSents examSents) {
		this.examSents = examSents;
	}
	public ExamSents getExamSents() {
		return this.examSents;
	}
	public void setTran(String tran) {
		this.tran = tran;
	}
	public String getTran() {
		return this.tran;
	}
}
