package com.coderdream.freeapps.component;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@Slf4j
public class XmlDom {
//    public static String createDom(String locale, String genderName, String voiceName, String textToSynthesize){
//        Document doc = null;
//        Element speak, voice;
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = dbf.newDocumentBuilder();
//            doc = builder.newDocument();
//            if (doc != null){
//                speak = doc.createElement("speak");
//                speak.setAttribute("version", "1.0");
//                speak.setAttribute("xml:lang", "en-us");
//                voice = doc.createElement("voice");
//                voice.setAttribute("xml:lang", locale);
//                voice.setAttribute("xml:gender", genderName);
//                voice.setAttribute("name", voiceName);
//
//                voice.appendChild(doc.createTextNode(textToSynthesize));
//                speak.appendChild(voice);
//                doc.appendChild(speak);
//            }
//        } catch (ParserConfigurationException e) {
//            log.error("Create ssml document failed: {}",e.getMessage());
//            return null;
//        }
//        return transformDom(doc);
//    }
//
//    private static String transformDom(Document doc){
//        StringWriter writer = new StringWriter();
//        try {
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer transformer;
//            transformer = tf.newTransformer();
//            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//            transformer.transform(new DOMSource(doc), new StreamResult(writer));
//        } catch (TransformerException e) {
//            log.error("Transform ssml document failed: {}",e.getMessage());
//            return null;
//        }
//        return writer.getBuffer().toString().replaceAll("\n|\r", "");
//    }
}
