package main.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParser {
    public static void main(String[] args) {
        String filepath = "/Users/user/IdeaProjects/My_School_Lessons/src/Class_Work/city.xml";
        File xmlFile = new File(filepath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            System.out.println("корневой элемент: "+ document.getDocumentElement().getNodeName());

            //получаем узлы с именем record, теперь xml полностью загружен в память в виде объекта Document
            NodeList nodeList = document.getElementsByTagName("record");

            //создадим из него список объектов record
            List <Record> rList = new ArrayList<Record>();
            for(int i=0;i<nodeList.getLength();i++){
                rList.add(getRecord(nodeList.item(i)));
            }
            //Напечатаем в консоли информацию по каждому объекту Record
            for (Record r: rList) {
                System.out.println(r.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //создаем из узла документ объекта record
    private static Record getRecord(Node node) {
        Record r = new Record();
        if (node.getNodeType() == node.ELEMENT_NODE) {
            Element element = (Element) node;
            r.setName(getTagValue("name", element));
            r.setId(Integer.parseInt(getTagValue("id", element)));
            r.setLongitude(Double.parseDouble(getTagValue("longitude", element)));
            r.setLatitude(Double.parseDouble(getTagValue("latitude", element)));
        }
        return r;
    }

    //получает значение элемента по указанному тегу
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}


