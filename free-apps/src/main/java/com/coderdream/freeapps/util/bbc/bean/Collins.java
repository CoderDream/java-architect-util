/**
 * Copyright 2023 lzltool.com
 */

package com.coderdream.freeapps.util.bbc.bean;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Auto-generated: 2023-10-19 09:37:57
 *
 * @author lzltool.com
 * @website https://www.lzltool.com/JsonToJava
 */

public class Collins {

	@JsonProperty("collins_entries")
	private List<CollinsEntries> collinsEntries;

	public void setCollinsEntries(List<CollinsEntries> collinsEntries) {
		this.collinsEntries = collinsEntries;
	}
	public List<CollinsEntries> getCollinsEntries() {
		return this.collinsEntries;
	}
}
