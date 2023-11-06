/**
 * Copyright 2023 lzltool.com
 */

package com.coderdream.freeapps.util.bbc.bean;

import java.util.List;

/**
 * Auto-generated: 2023-10-19 09:37:57
 *
 * @author lzltool.com
 * @website https://www.lzltool.com/JsonToJava
 */

public class Content {

	private String detailPos;
	private List<ExamType> examType;
	private List<Sents> sents;

	public void setDetailPos(String detailPos) {
		this.detailPos = detailPos;
	}
	public String getDetailPos() {
		return this.detailPos;
	}
	public void setExamType(List<ExamType> examType) {
		this.examType = examType;
	}
	public List<ExamType> getExamType() {
		return this.examType;
	}
	public void setSents(List<Sents> sents) {
		this.sents = sents;
	}
	public List<Sents> getSents() {
		return this.sents;
	}
}
