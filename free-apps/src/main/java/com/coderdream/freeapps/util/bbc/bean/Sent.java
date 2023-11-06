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

public class Sent {

	@JsonProperty("chn_sent")
	private String chnSent;
	@JsonProperty("eng_sent")
	private String engSent;

	public void setChnSent(String chnSent) {
		this.chnSent = chnSent;
	}
	public String getChnSent() {
		return this.chnSent;
	}
	public void setEngSent(String engSent) {
		this.engSent = engSent;
	}
	public String getEngSent() {
		return this.engSent;
	}
}
