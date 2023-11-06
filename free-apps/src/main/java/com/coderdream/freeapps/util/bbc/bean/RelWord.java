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

public class RelWord {

	private String word;
	private String stem;
	private List<Rels> rels;

	public void setWord(String word) {
		this.word = word;
	}
	public String getWord() {
		return this.word;
	}
	public void setStem(String stem) {
		this.stem = stem;
	}
	public String getStem() {
		return this.stem;
	}
	public void setRels(List<Rels> rels) {
		this.rels = rels;
	}
	public List<Rels> getRels() {
		return this.rels;
	}
}
