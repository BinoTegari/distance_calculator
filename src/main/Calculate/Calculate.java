package Calculate;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;

public class Calculate {
    public static void main(String[] args) {
        double latitudeA = 0;
        double latitudeB = 0;
        double longitudeA = 0;
        double longitudeB = 0;
        double distance = 0;
        String filepath = "library\\city.xml";
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
            String nameA = scanner.next();

            for (int i = 0; i < nodeListName.getLength(); i++) {
                String cityA = nodeListName.item(i).getTextContent();
                if (cityA.equals(nameA)) {
                    latitudeA = Double.parseDouble(nodeLatitude.item(i).getTextContent());
                    longitudeA = Double.parseDouble(nodeLongitude.item(i).getTextContent());
                }
            }

            System.out.println("Введите название второго города: ");
            String nameB = scanner.next();

            for (int j=0;j<nodeListName.getLength();j++){
                String cityB = nodeListName.item(j).getTextContent();
                if(cityB.equals(nameB)){
                    latitudeB = Double.parseDouble(nodeLatitude.item(j).getTextContent());
                    longitudeB = Double.parseDouble((nodeLongitude.item(j).getTextContent()));
                }
            }
            distance = distanceCrowflight(latitudeA,latitudeB,longitudeA,longitudeB);
            System.out.println("Расстояние между городами "+ nameA+" и "+nameB+" равно: "+ distance);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static double distanceCrowflight(double latitudeA, double latitudeB, double longitudeA, double longitudeB) {

        longitudeA = Math.toRadians(longitudeA);
        longitudeB = Math.toRadians(longitudeB);
        latitudeA = Math.toRadians(latitudeA);
        latitudeB = Math.toRadians(latitudeB);

        double dlongitude = longitudeB - longitudeA;
        double dlatitude = latitudeB - latitudeA;
        double a = Math.pow(Math.sin(dlatitude / 2), 2) + Math.cos(latitudeA) * Math.cos(latitudeB) * Math.pow(Math.sin(dlongitude / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));


        double r = 6371;

        return (c * r);
    }
}

//Врядли это вообще хоть кто-то прочитает))Но если ты читаешь это, дай знать, мой целеустремленный товарищ)))
