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

public class Entry {

	@JsonProperty("tran_entry")
	private List<TranEntry> tranEntry;

	public void setTranEntry(List<TranEntry> tranEntry) {
		this.tranEntry = tranEntry;
	}
	public List<TranEntry> getTranEntry() {
		return this.tranEntry;
	}
}
