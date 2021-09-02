package com.coderdream;

import freemarker.template.TemplateException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮件工具测试类
 * 
 * @date 2014年4月26日 下午3:37:11
 * @author 曹志飞
 * @Description:
 * @project mailUtil
 */
public class MailUtilTest {
	private static Logger log = LoggerFactory.getLogger(MailUtilTest.class);

	@Test
	public void testMailTemplate() {
		String templateName = "template_1.ftl";
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", "test");
		try {
			MailUtil.sendMailByTemplate("xulin.wh@qq.com", "test", map,
					templateName);
		} catch (IOException e) {
			log.error(e.toString(), e);
		} catch (TemplateException e) {
			log.error(e.toString(), e);
		} catch (MessagingException e) {
			System.out.printf("e" + e.getMessage());
			log.error(e.toString(), e);
		}
	}

	@Test
	public void testMailTemplate_02() {
		String templateName = "template_2.ftl";
		Map<String, String> map = new HashMap<String, String>();
		map.put("test00", "test00-value");
		map.put("test01", "test01-value");
		map.put("test02", "test02-value");
		map.put("test03", "test03");
		map.put("test04", "test04");
		map.put("test05", "test05");
		map.put("test06", "test06");
		map.put("test07", "test07");
		map.put("test08", "test08");
		map.put("test09", "test09-value");
		map.put("test10", "test10");
		map.put("test11", "test11");
		map.put("test12", "test12");
		map.put("test13", "test13");
		map.put("test14", "test14");
		map.put("test15", "test15");
		map.put("test16", "test16");
		map.put("test17", "test17");
		map.put("test18", "test18");
		map.put("test19", "test19");
		map.put("test20", "test20");

		try {
			MailUtil.sendMailByTemplate("xulin.wh@qq.com", "test", map,
					templateName);
		} catch (IOException e) {
			log.error(e.toString(), e);
		} catch (TemplateException e) {
			log.error(e.toString(), e);
		} catch (MessagingException e) {
			log.error(e.toString(), e);
		}
	}

	@Test
	public void testMailTemplate_03() {
		String templateName = "template_3.html";
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "何三妹");
		map.put("password", "123456");
		map.put("age", "27");
		map.put("sex", "女");

		try {
			MailUtil.sendMailByTemplate("xulin.wh@qq.com", "test", map,
					templateName);
		} catch (IOException e) {
			log.error(e.toString(), e);
		} catch (TemplateException e) {
			log.error(e.toString(), e);
		} catch (MessagingException e) {
			log.error(e.toString(), e);
		}
	}

	@Test
	public void testMailTemplate_04() {
		String templateName = "template_4.html";
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "何三妹");
		map.put("password", "123456");
		map.put("age", "27");
		map.put("sex", "女");

		try {
			MailUtil.sendMailByTemplate("xulin.wh@qq.com", "test", map,
					templateName);
		} catch (IOException e) {
			log.error(e.toString(), e);
		} catch (TemplateException e) {
			log.error(e.toString(), e);
		} catch (MessagingException e) {
			log.error(e.toString(), e);
		}
	}
	
//	@Test
//	public void testMailTemplate_05() {
//		String templateName = "template_5.html";
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("username", "何三妹");
//		map.put("password", "123456");
//		map.put("age", "27");
//		map.put("sex", "女");
//
//		List<TableCompareBean> tableCompareBeanListTotal = new ArrayList<TableCompareBean>();
//		String databaseName1 = "BJC_Finance";
//		String databaseName2 = "BJC_Finance_Test";
//		Map<String, List<TableCompareBean>> totalTableCompareBeanListMap = MssqlService
//				.compareTableConstructDiff(databaseName1, databaseName2);
//		int i = 0;
//		for (String tableName : totalTableCompareBeanListMap.keySet()) {
//			List<TableCompareBean> tableCompareBeanList = totalTableCompareBeanListMap
//					.get(tableName);
//			System.out.println("###tableName\t" + tableName);
//			for (TableCompareBean tableCompareBean : tableCompareBeanList) {
//				System.out.println(tableCompareBean);
//			}
//			i++;
//			if (i < 5) {
//				tableCompareBeanListTotal.addAll(tableCompareBeanList);
//			}
//		}
//
//		try {
//			MailUtil.sendMailByTemplate("xulin.wh@qq.com", "test",
//					tableCompareBeanListTotal, templateName);
//		} catch (IOException e) {
//			log.error(e.toString(), e);
//		} catch (TemplateException e) {
//			log.error(e.toString(), e);
//		} catch (MessagingException e) {
//			log.error(e.toString(), e);
//		}
//	}

//	@Test
//	public void testMailTemplate_06() {
//		String templateName = "template_6.html";
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("username", "何三妹");
//		map.put("password", "123456");
//		map.put("age", "27");
//		map.put("sex", "女");
//
//		List<TableCompareBean> tableCompareBeanListTotal = new ArrayList<TableCompareBean>();
//		String databaseName1 = "BJC_Finance";
//		String databaseName2 = "BJC_Finance_Test";
//		Map<String, List<TableCompareBean>> totalTableCompareBeanListMap = MssqlService
//				.compareTableConstructDiff(databaseName1, databaseName2);
//		int i = 0;
//		for (String tableName : totalTableCompareBeanListMap.keySet()) {
//			List<TableCompareBean> tableCompareBeanList = totalTableCompareBeanListMap
//					.get(tableName);
//			System.out.println("###tableName\t" + tableName);
//			for (TableCompareBean tableCompareBean : tableCompareBeanList) {
//				System.out.println(tableCompareBean);
//			}
//			i++;
//			if (i < 5) {
//				tableCompareBeanListTotal.addAll(tableCompareBeanList);
//			}
//		}
//
//		try {
//			MailUtil.sendMailByTemplateWithListObject("xulin.wh@qq.com", "test",
//					tableCompareBeanListTotal, templateName);
//		} catch (IOException e) {
//			log.error(e.toString(), e);
//		} catch (TemplateException e) {
//			log.error(e.toString(), e);
//		} catch (MessagingException e) {
//			log.error(e.toString(), e);
//		}
//	}

	@Test
	public void testMail() {
		try {
			MailUtil.sendMail("xulin.wh@qq.com", "test", "普通邮件");
		} catch (IOException e) {
			log.error(e.toString(), e);
		} catch (MessagingException e) {
			log.error(e.toString(), e);
		}
	}

	@Test
	public void testMailAndFile() {
		try {
			String filePath = "C:/d2f42068.ini";
			MailUtil.sendMailAndFile("xulin.wh@qq.com", "test", filePath,
					"ted");
		} catch (IOException e) {
			log.error(e.toString(), e);
		} catch (MessagingException e) {
			log.error(e.toString(), e);
		}
	}
}
