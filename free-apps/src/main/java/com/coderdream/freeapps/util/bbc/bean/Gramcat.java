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

public class Gramcat {

	private String audiourl;
	private String pronunciation;
	private List<Senses> senses;
	private String partofspeech;
	private String audio;
	private List<String> forms;

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
	public void setSenses(List<Senses> senses) {
		this.senses = senses;
	}
	public List<Senses> getSenses() {
		return this.senses;
	}
	public void setPartofspeech(String partofspeech) {
		this.partofspeech = partofspeech;
	}
	public String getPartofspeech() {
		return this.partofspeech;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public String getAudio() {
		return this.audio;
	}
	public void setForms(List<String> forms) {
		this.forms = forms;
	}
	public List<String> getForms() {
		return this.forms;
	}
}
