/**
 * Copyright 2023 lzltool.com
 */

package com.coderdream.freeapps.util.bbc.bean;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Auto-generated: 2023-10-19 09:37:57
 *
 * @author lzltool.com
 * @website https://www.lzltool.com/JsonToJava
 */

public class CollinsEntries {

	private Entries entries;
	private String phonetic;
	@JsonProperty("basic_entries")
	private BasicEntries basicEntries;
	private String headword;
	private String star;

	public void setEntries(Entries entries) {
		this.entries = entries;
	}
	public Entries getEntries() {
		return this.entries;
	}
	public void setPhonetic(String phonetic) {
		this.phonetic = phonetic;
	}
	public String getPhonetic() {
		return this.phonetic;
	}
	public void setBasicEntries(BasicEntries basicEntries) {
		this.basicEntries = basicEntries;
	}
	public BasicEntries getBasicEntries() {
		return this.basicEntries;
	}
	public void setHeadword(String headword) {
		this.headword = headword;
	}
	public String getHeadword() {
		return this.headword;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getStar() {
		return this.star;
	}
}
