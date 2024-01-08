package com.coderdream.freeapps.util.xml;

import com.coderdream.freeapps.util.other.CdFileUtils;
import java.io.File;
import org.dom4j.DocumentException;

/**
 * @author CoderDream
 */
public class Test {

    public static void main(String[] args) throws DocumentException {
        m2();
    }

    public static void m1() throws DocumentException {
        XmlUtilV2 xmlUtil = new XmlUtilV2();
        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "dict";
        String fileName = folderPath + File.separator + "rss.xml";

        String json = xmlUtil.xmlToJson(fileName);
        System.out.println(json);
    }

    public static void m2()    {

//        String xml =
//            "<itemsPreviousRespondHeader>"
//                +      "<language>zh-cn</language>"
//                +     "<respondTime>2010-04-28T18:36:00</respondTime>"
//                +      "<respondStatus>"
//                +           "<respondCode>0000</respondCode>"
//                +          "<respondInfo>整批交易全部成功！</respondInfo>"
//                +     "</respondStatus>"
//                +      "<userID></userID>"
//                +     "<batchID>2050278995200910236000001</batchID>"
//                +     "<transPatches>1</transPatches>"
//                +   "</itemsPreviousRespondHeader>";
//
//        JSONObject object = XML.toJSONObject(xml);
//
//        System.out.println(object);
    }



}
