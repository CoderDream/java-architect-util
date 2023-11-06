/**
 * Copyright 2023 lzltool.com
 */

package com.coderdream.freeapps.util.bbc.bean;

import java.util.List;

/**
 * Auto_generated: 2023_10_19 09:37:57
 *
 * @author lzltool.com
 * @website https://www.lzltool.com/JsonToJava
 */

public class ExpandEc {

	private String return_phrase;
	private Source source;
	private List<Word> word;

	public void setReturn_phrase(String return_phrase) {
		this.return_phrase = return_phrase;
	}
	public String getReturn_phrase() {
		return this.return_phrase;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	public Source getSource() {
		return this.source;
	}
	public void setWord(List<Word> word) {
		this.word = word;
	}
	public List<Word> getWord() {
		return this.word;
	}
}
