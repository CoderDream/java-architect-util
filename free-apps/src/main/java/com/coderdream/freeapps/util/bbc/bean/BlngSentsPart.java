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

public class BlngSentsPart {

	private int sentence_count;
	private List<SentencePair> sentence_pair;
	private String more;
	private List<Trs_classify> trs_classify;

	public void setSentence_count(int sentence_count) {
		this.sentence_count = sentence_count;
	}
	public int getSentence_count() {
		return this.sentence_count;
	}
	public void setSentence_pair(List<SentencePair> sentence_pair) {
		this.sentence_pair = sentence_pair;
	}
	public List<SentencePair> getSentence_pair() {
		return this.sentence_pair;
	}
	public void setMore(String more) {
		this.more = more;
	}
	public String getMore() {
		return this.more;
	}
	public void setTrs_classify(List<Trs_classify> trs_classify) {
		this.trs_classify = trs_classify;
	}
	public List<Trs_classify> getTrs_classify() {
		return this.trs_classify;
	}
}
