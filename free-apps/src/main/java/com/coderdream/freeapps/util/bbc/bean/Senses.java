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

public class Senses {

	private List<Derivatives> derivatives;
	private List<Examples> examples;
	private String definition;
	private String lang;
	private String word;

	public void setDerivatives(List<Derivatives> derivatives) {
		this.derivatives = derivatives;
	}
	public List<Derivatives> getDerivatives() {
		return this.derivatives;
	}
	public void setExamples(List<Examples> examples) {
		this.examples = examples;
	}
	public List<Examples> getExamples() {
		return this.examples;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getDefinition() {
		return this.definition;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getLang() {
		return this.lang;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getWord() {
		return this.word;
	}
}
