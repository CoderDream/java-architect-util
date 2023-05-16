package com.junit;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.junit.Test;

public class TestCode {

	@Test
	public void test() throws IOException {
		Document doc=Jsoup.connect("https://www.kanshugee.com/files/article/html/146/146776/38504352.html").get();
		Elements elements=doc.select("h1");
		System.out.println((elements.text()).substring(elements.text().indexOf("��")));
	}

}
