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

public class BasicEntries {

	@JsonProperty("basic_entry")
	private List<BasicEntry> basicEntry;

	public void setBasicEntry(List<BasicEntry> basicEntry) {
		this.basicEntry = basicEntry;
	}
	public List<BasicEntry> getBasicEntry() {
		return this.basicEntry;
	}
}
