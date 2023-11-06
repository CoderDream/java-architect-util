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

public class Derivatives {

	private String audiourl;
	private String pronunciation;
	private String partofspeech;
	private String labelgrammar;
	private List<Sense> sense;
	private String audio;
	private String word;

	public void setAudiourl(String audiourl) {
		this.audiourl = audiourl;
	}
	public String getAudiourl() {
		return this.audiourl;
	}
	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}
	public String getPronunciation() {
		return this.pronunciation;
	}
	public void setPartofspeech(String partofspeech) {
		this.partofspeech = partofspeech;
	}
	public String getPartofspeech() {
		return this.partofspeech;
	}
	public void setLabelgrammar(String labelgrammar) {
		this.labelgrammar = labelgrammar;
	}
	public String getLabelgrammar() {
		return this.labelgrammar;
	}
	public void setSense(List<Sense> sense) {
		this.sense = sense;
	}
	public List<Sense> getSense() {
		return this.sense;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public String getAudio() {
		return this.audio;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getWord() {
		return this.word;
	}
}
