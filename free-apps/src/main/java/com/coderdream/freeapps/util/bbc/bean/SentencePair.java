/**
 * Copyright 2023 lzltool.com
 */

package com.coderdream.freeapps.util.bbc.bean;

/**
 * Auto_generated: 2023_10_19 09:37:57
 *
 * @author lzltool.com
 * @website https://www.lzltool.com/JsonToJava
 */

public class SentencePair {

	private String sentence;
	private String sentence_eng;
	private String sentence_translation;
	private String speech_size;
	private AlignedWords aligned_words;
	private String source;
	private String url;
	private String sentence_speech;

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public String getSentence() {
		return this.sentence;
	}
	public void setSentence_eng(String sentence_eng) {
		this.sentence_eng = sentence_eng;
	}
	public String getSentence_eng() {
		return this.sentence_eng;
	}
	public void setSentence_translation(String sentence_translation) {
		this.sentence_translation = sentence_translation;
	}
	public String getSentence_translation() {
		return this.sentence_translation;
	}
	public void setSpeech_size(String speech_size) {
		this.speech_size = speech_size;
	}
	public String getSpeech_size() {
		return this.speech_size;
	}
	public void setAlignedWords(AlignedWords aligned_words) {
		this.aligned_words = aligned_words;
	}
	public AlignedWords getAligned_words() {
		return this.aligned_words;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSource() {
		return this.source;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return this.url;
	}
	public void setSentence_speech(String sentence_speech) {
		this.sentence_speech = sentence_speech;
	}
	public String getSentence_speech() {
		return this.sentence_speech;
	}
}
