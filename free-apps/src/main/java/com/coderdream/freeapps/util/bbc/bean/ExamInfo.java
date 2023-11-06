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

public class ExamInfo {

	private int year;
	private List<QuestionTypeInfo> questionTypeInfo;
	private int recommendationRate;
	private int frequency;

	public void setYear(int year) {
		this.year = year;
	}
	public int getYear() {
		return this.year;
	}
	public void setQuestionTypeInfo(List<QuestionTypeInfo> questionTypeInfo) {
		this.questionTypeInfo = questionTypeInfo;
	}
	public List<QuestionTypeInfo> getQuestionTypeInfo() {
		return this.questionTypeInfo;
	}
	public void setRecommendationRate(int recommendationRate) {
		this.recommendationRate = recommendationRate;
	}
	public int getRecommendationRate() {
		return this.recommendationRate;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public int getFrequency() {
		return this.frequency;
	}
}
