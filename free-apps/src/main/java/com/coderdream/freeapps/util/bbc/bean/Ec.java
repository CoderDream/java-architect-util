/**
 * Copyright 2023 lzltool.com
 */

package com.coderdream.freeapps.util.bbc.bean;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Auto-generated: 2023-10-19 09:37:57
 *
 * @author lzltool.com
 * @website https://www.lzltool.com/JsonToJava
 */

public class Ec {

	@JsonProperty("web_trans")
	private List<String> webTrans;
	private List<Special> special;
	@JsonProperty("exam_type")
	private List<String> examType;
	private Source source;
	private Word word;

	public void setWebTrans(List<String> webTrans) {
		this.webTrans = webTrans;
	}
	public List<String> getWebTrans() {
		return this.webTrans;
	}
	public void setSpecial(List<Special> special) {
		this.special = special;
	}
	public List<Special> getSpecial() {
		return this.special;
	}
	public void setExamType(List<String> examType) {
		this.examType = examType;
	}
	public List<String> getExamType() {
		return this.examType;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	public Source getSource() {
		return this.source;
	}
	public void setWord(Word word) {
		this.word = word;
	}
	public Word getWord() {
		return this.word;
	}
}
