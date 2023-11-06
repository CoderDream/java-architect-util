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

public class WikipediaDigest {

	private List<Summarys> summarys;
	private Source source;

	public void setSummarys(List<Summarys> summarys) {
		this.summarys = summarys;
	}
	public List<Summarys> getSummarys() {
		return this.summarys;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	public Source getSource() {
		return this.source;
	}
}
