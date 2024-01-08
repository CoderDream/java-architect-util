package com.coderdream.freeapps.util.xml;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.coderdream.freeapps.util.other.CdFileUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * JsonUtil
 *
 * @author yx
 * @date 2010/11/20 12:44
 */
public class JsonUtil {
    /**
     * xml转json
     *
     * @param xml xml格式字串
     * @return JSONObject
     */
    public static JSONObject xml2Json(String xml) {
        JSONObject parent = new JSONObject();
        JSONObject json = new JSONObject();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            dom4j2Json(doc.getRootElement(), null, json);
        }
        parent.put(doc.getRootElement().getName(), json);
        return parent;
    }

    /**
     * 从文件中读取字串
     *
     * @param xmlFilePath 文件路径
     * @return 字串
     */
    public static String readXmlString(String xmlFilePath) {
        String xml = null;
        File file = new File(xmlFilePath);
        if (!file.exists()) {
            return null;
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(xmlFilePath));
            StringBuffer sb = new StringBuffer();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            xml = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return xml;
    }

    /**
     * xml转json
     *
     * @param element 解析的元素
     * @param json    待填充的json
     */
    private static void dom4j2Json(Element element, JSONObject parent, JSONObject json) {
        if (parent == null) {
            parent = new JSONObject();
        }
        System.out.println("name5:" + element.getName() + ",value:" + json);
        parent.put(element.getName(), json);
        // 如果是属性
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            if (isEmpty(attr.getValue())) {
                parent.put("@" + attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl = element.elements();
        if (chdEl.isEmpty() && isEmpty(element.getText())) {// 如果没有子元素,只有一个值
            System.out.println("name1:" + element.getName() + ",value:" + element.getText());
            parent.put(element.getName(), element.getText());
        } else {
            for (Element e : chdEl) {// 有子元素
                if (!e.elements().isEmpty()) {// 子元素也有子元素
                    JSONObject chdJson = new JSONObject();
                    dom4j2Json(e, json, chdJson);
                    Object o = parent.get(e.getName());
                    if (o != null) {
                        JSONArray jsona = null;
                        if (o instanceof JSONObject) {// 如果此元素已存在,则转为jsonArray
                            JSONObject jsono = (JSONObject) o;
                            json.remove(e.getName());
                            jsona = new JSONArray();
                            jsona.add(jsono);
                            jsona.add(chdJson);
                        } else if (o instanceof JSONArray) {
                            jsona = (JSONArray) o;
                            jsona.add(chdJson);
                        }
                        System.out.println("name2:" + e.getName() + ",value:" +jsona);
                        json.put(e.getName(), jsona);
                    } else {
                        if(!chdJson.isEmpty()){
                            System.out.println("name3:" + e.getName() + ",value:" + chdJson);
                            json.put(e.getName(), chdJson);
                        }
                    }
                } else {// 子元素没有子元素
                    for (Object o : element.attributes()) {
                        Attribute attr = (Attribute) o;
                        if (isEmpty(attr.getValue())) {
                            json.put("@" + attr.getName(), attr.getValue());
                        }
                    }
                    if (!e.getText().isEmpty()) {
                        System.out.println("name4:" + e.getName() + ",value:" +e.getText());
                        json.put(e.getName(), e.getText());
                    }
                }
            }
        }
    }

    private static boolean isEmpty(String str) {
        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return false;
        }
        return true;
    }

    // 测试
    public static void main(String[] args) {
//        String xmlStr = "<students type=\"middle\">" + "<student name=\"zhangsan\">\n" +
//                "   <sex>man</sex>\n" +
//                "    <age>18</age>\n" +
//                "</student>" + "<student name=\"lisi\">\n" +
//                "   <sex>man</sex>\n" +
//                "    <age>18</age>\n" +
//                "</student>" + "</students>\n";
//        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
//                "<students>\n" +
//                "\t<student>\n" +
//                "\t\t<name>zhangsan</name>\n" +
//                "\t\t<sex>man</sex>\n" +
//                "\t\t<age>18</age>\n" +
//                "\t</student>\n" +
//                "\t<student>\n" +
//                "\t\t<name>lisi</name>\n" +
//                "\t\t<sex>man</sex>\n" +
//                "\t\t<age>19</age>\n" +
//                "\t</student>\n" +
//                "  </students>";
//        String xmlStr = readFile("file\\activity_led.xml");
//        String xmlStr = readXmlString("file\\haarcascade_frontalface_alt.xml");

//        String xmlStr = "\t\t<student class=\"1\">\n" +
//                "\t\t\t<sex>man</sex>\n" +
//                "\t\t\t<name>zhangsan</name>\n" +
//                "\t\t\t<age>18</age>\n" +
//                "\t\t</student>";

//        String xmlStr = "\t\t<student >\n" +
//                "\t\t\t<sex>man</sex>\n" +
//                "\t\t\t<name>zhangsan</name>\n" +
//                "\t\t\t<age>18</age>\n" +
//                "\t\t</student>";

//        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<status>\n" +
//                "\t<flag>1</flag>\n" +
//                "\t<message>2</message>\n" +
//                "</status>";

//        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<status></status>";

//        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
//            "\t<students>\n" +
//            "\t\t<student>\n" +
//            "\t\t\t<sex>man</sex>\n" +
//            "\t\t\t<name>zhangsan</name>\n" +
//            "\t\t\t<age>18</age>\n" +
//            "\t\t</student>\n" +
//            "\t\t<student>\n" +
//            "\t\t\t<sex>man</sex>\n" +
//            "\t\t\t<name>lisi</name>\n" +
//            "\t\t\t<age>19</age>\n" +
//            "\t\t\t<class><grade>3</grade></class>\n" +
//            "\t\t</student>\n" +
//            "\t</students>\n" +
//            "\t\n";

        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "dict";
        String fileName = folderPath + File.separator + "rss.xml";
        String xmlString = readXmlString(fileName);

        System.out.println(JsonUtil.xml2Json(xmlString));
    }
}
