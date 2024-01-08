package com.coderdream.freeapps.util.xml;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultDocument;
import org.dom4j.tree.DefaultElement;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * xml解析帮组类
 * @author asen
 * @date 2022/1/10 15:17
 */
public class XmlUtilV2 {

    /**
     * 标签属性
     */
    private final static String TAG_ATTR = "attr";

    /**
     * 创建的map类型
     */
    private XmlSort xmlSort = XmlSort.NO_SORT;

    /**
     * map to xml
     * @param map map对象
     * @return xml 字符串
     */
    public String mapToXml(Map<String,Object> map) {
        if(map.size() != 1){
            throw new RuntimeException("map根节点长度不为1");
        }
        String key = "";
        for (String str : map.keySet()) {
            key = str ;
        }
        //  创建根节点
        Element rootElement = new DefaultElement(key);
        Document document = new DefaultDocument(rootElement);
        Element node = document.getRootElement();
        Object obj = map.get(key);
        // 断言
        Assert.isAssignable(Map.class,obj.getClass());
        mapNodes(node,(Map<String, Object>)obj);
        return document.asXML();
    }

    /**
     * 父类节点已经创建， map 包含父类
     * @param node node
     * @param map map
     */
    public void mapNodes(Element node, Map<String, Object> map) {
        map.forEach((k,v)->{
            Object obj = map.get(k);
            // 给当前父类添加属性
            if(TAG_ATTR.equals(k)){
                Assert.isAssignable(Map.class,obj.getClass());
                Map<String,Object> tagMap = (Map<String,Object>) obj;
                tagMap.forEach((tagKey,tagValue)->{
                    node.addAttribute(tagKey, (String) tagValue);
                });
                return ;
            }
            if(obj instanceof Map){
                Element newElement = node.addElement(k);
                // map 处理
                Map<String,Object> childMap = (Map<String,Object>) obj;
                mapNodes(newElement,childMap);
            }else if (obj instanceof String){
                Element newElement = node.addElement(k);
                newElement.setText((String) v);
            } else if (obj instanceof List) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) obj;
                list.forEach(itemMap->{
                    Element newElement = node.addElement(k);
                    mapNodes(newElement,itemMap);
                });
            }
        });
    }


    /**
     * 读取xml文件，返回json字符串
     *
     * @param fileName 文件路径
     * @return json字符串
     * @throws DocumentException 异常
     */
    public String xmlToJson(String fileName) throws DocumentException {
        Map<String, Object> xmlMap = xmlToMap(fileName);
        return JSONUtil.toJsonStr(xmlMap);
    }

    /**
     * 读取xml文件，返回map对象
     *
     * @param fileName 文件路径
     * @return map对象
     * @throws DocumentException 异常
     */
    public Map<String, Object> xmlToMap(String fileName) throws DocumentException {
        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象
        Document document = reader.read(new File(fileName));
        //获取根节点元素对象
        Element node = document.getRootElement();
        //遍历所有的元素节点
        Map<String, Object> map = getNewMap();
        // 处理节点
        listNodes(node, map);
        return map;
    }


    /**
     * 遍历当前节点元素下面的所有(元素的)子节点
     *
     * @param node node
     */
    public void listNodes(Element node, Map<String, Object> map) {
        Map<String, Object> xiaoMap = getNewMap();
        String nodeKey = node.getName();
        // 获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        // 遍历属性节点
        Map<String, Object> attrMap = getNewMap();
        for (Attribute attr : list) {
            attrMap.put(attr.getName(), attr.getValue());
        }
        if (ObjectUtil.isNotEmpty(attrMap)) {
            xiaoMap.put(TAG_ATTR, attrMap);
        }

        // 当前节点下面子节点迭代器
        Iterator<Element> it = node.elementIterator();

        if (!("".equals(node.getTextTrim())) || !it.hasNext()) {
            map.put(nodeKey, node.getTextTrim());
        }else{
            // 不为空
            if (ObjectUtil.isEmpty(map.get(nodeKey))) {
                map.put(nodeKey, xiaoMap);
            } else {
                List<Map<String, Object>> childList = null;
                // 获取原来的
                Object obj = map.get(nodeKey);
                if (obj instanceof Iterable) {
                    // 非第一个
                    childList = (List<Map<String, Object>>) obj;
                    childList.add(xiaoMap);
                } else if (obj instanceof Map) {
                    // 第一个
                    Map<String, Object> childMap = (Map<String, Object>) obj;
                    childList = new ArrayList<>();
                    childList.add(childMap);
                    childList.add(xiaoMap);
                }
                // 添加新的
                map.put(nodeKey, childList);
            }
        }

        // 遍历
        while (it.hasNext()) {
            // 获取某个子节点对象
            Element e = it.next();
            // 对子节点进行遍历
            listNodes(e, xiaoMap);
        }
    }

    /**
     * 获取一个新的map对象
     *
     * @return map对象
     */
    private Map<String, Object> getNewMap() {
        Object obj = null;
        try {
            obj = xmlSort.getMapClass().newInstance();
            if (obj instanceof Map) {
                return (Map<String, Object>) obj;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置是否排序
     *
     * @param xmlSort 是否排序对象
     */
    public void setXmlSort(XmlSort xmlSort) {
        this.xmlSort = xmlSort;
    }
}
