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

public class AuthSentsPart {

	private int sentence_count;
	private String more;
	private List<Sent> sent;

	public void setSentence_count(int sentence_count) {
		this.sentence_count = sentence_count;
	}
	public int getSentence_count() {
		return this.sentence_count;
	}
	public void setMore(String more) {
		this.more = more;
	}
	public String getMore() {
		return this.more;
	}
	public void setSent(List<Sent> sent) {
		this.sent = sent;
	}
	public List<Sent> getSent() {
		return this.sent;
	}
}
