package main.Calculate;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;

public class ParseXML {
    static String nameA;
    static String nameB;
    static double latitudeA = 0;
    static double latitudeB = 0;
    static double longitudeA = 0;
    static double longitudeB = 0;

    static void parseFromXML() {
        String filepath = "distance_calculator/library/city.xml";
        File xmlFile = new File(filepath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList nodeListName = document.getElementsByTagName("name");
            NodeList nodeLongitude = document.getElementsByTagName("longitude");
            NodeList nodeLatitude = document.getElementsByTagName("latitude");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите название первого города: ");
            nameA = scanner.next();

            for (int i = 0; i < nodeListName.getLength(); i++) {
                String cityA = nodeListName.item(i).getTextContent();
                if (cityA.equals(nameA)) {
                    latitudeA = Double.parseDouble(nodeLatitude.item(i).getTextContent());
                    longitudeA = Double.parseDouble(nodeLongitude.item(i).getTextContent());
                }
            }

            System.out.println("Введите название второго города: ");
            nameB = scanner.next();

            for (int j = 0; j < nodeListName.getLength(); j++) {
                String cityB = nodeListName.item(j).getTextContent();
                if (cityB.equals(nameB)) {
                    latitudeB = Double.parseDouble(nodeLatitude.item(j).getTextContent());
                    longitudeB = Double.parseDouble((nodeLongitude.item(j).getTextContent()));
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
