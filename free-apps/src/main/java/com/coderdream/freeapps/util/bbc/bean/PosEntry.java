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

public class PosEntry {

	private String pos;
	@JsonProperty("pos_tips")
	private String posTips;

	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getPos() {
		return this.pos;
	}
	public void setPosTips(String posTips) {
		this.posTips = posTips;
	}
	public String getPosTips() {
		return this.posTips;
	}
}
