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

public class Individual {

	private List<Synonym> synonym;
	private List<Antonym> antonym;
	private Anagram anagram;
	private List<Idiomatic> idiomatic;
	private List<Trs> trs;
	private String level;
	private ExamInfo examInfo;
	private String return_phrase;
	private List<PastExamSents> pastExamSents;
	private Mnemonic mnemonic;
	private List<Derivative> derivative;
	private List<HighExpression> highExpression;

	public void setSynonym(List<Synonym> synonym) {
		this.synonym = synonym;
	}
	public List<Synonym> getSynonym() {
		return this.synonym;
	}
	public void setAntonym(List<Antonym> antonym) {
		this.antonym = antonym;
	}
	public List<Antonym> getAntonym() {
		return this.antonym;
	}
	public void setAnagram(Anagram anagram) {
		this.anagram = anagram;
	}
	public Anagram getAnagram() {
		return this.anagram;
	}
	public void setIdiomatic(List<Idiomatic> idiomatic) {
		this.idiomatic = idiomatic;
	}
	public List<Idiomatic> getIdiomatic() {
		return this.idiomatic;
	}
	public void setTrs(List<Trs> trs) {
		this.trs = trs;
	}
	public List<Trs> getTrs() {
		return this.trs;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLevel() {
		return this.level;
	}
	public void setExamInfo(ExamInfo examInfo) {
		this.examInfo = examInfo;
	}
	public ExamInfo getExamInfo() {
		return this.examInfo;
	}
	public void setReturn_phrase(String return_phrase) {
		this.return_phrase = return_phrase;
	}
	public String getReturn_phrase() {
		return this.return_phrase;
	}
	public void setPastExamSents(List<PastExamSents> pastExamSents) {
		this.pastExamSents = pastExamSents;
	}
	public List<PastExamSents> getPastExamSents() {
		return this.pastExamSents;
	}
	public void setMnemonic(Mnemonic mnemonic) {
		this.mnemonic = mnemonic;
	}
	public Mnemonic getMnemonic() {
		return this.mnemonic;
	}
	public void setDerivative(List<Derivative> derivative) {
		this.derivative = derivative;
	}
	public List<Derivative> getDerivative() {
		return this.derivative;
	}
	public void setHighExpression(List<HighExpression> highExpression) {
		this.highExpression = highExpression;
	}
	public List<HighExpression> getHighExpression() {
		return this.highExpression;
	}
}
