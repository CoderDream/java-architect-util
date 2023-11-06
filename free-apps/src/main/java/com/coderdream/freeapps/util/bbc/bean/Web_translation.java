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

public class Web_translation {

	private String same;
	private String key;
	private String key_speech;
	private List<Trans> trans;

	public void setSame(String same) {
		this.same = same;
	}
	public String getSame() {
		return this.same;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKey() {
		return this.key;
	}
	public void setKey_speech(String key_speech) {
		this.key_speech = key_speech;
	}
	public String getKey_speech() {
		return this.key_speech;
	}
	public void setTrans(List<Trans> trans) {
		this.trans = trans;
	}
	public List<Trans> getTrans() {
		return this.trans;
	}
}
