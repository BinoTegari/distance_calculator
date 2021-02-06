package main.Calculate;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Calculate {
    static String nameA = "";
    static String nameB = "";
    static Double distance = null;

    public static void main(String[] args) {

        double latitudeA = 0;
        double latitudeB = 0;
        double longitudeA = 0;
        double longitudeB = 0;
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

        ConnectMySQL connectMySQL = new ConnectMySQL(nameA,nameB,distance);
        connectMySQL.Connect();

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
//    public static double distanceMatrix(double latitudeA, double latitudeB, double longitudeA, double longitudeB ){
//
//    }

}
class ConnectMySQL{
    private String nameA;
    private String nameB;
    private Double distance;

    ConnectMySQL(String nameA, String nameB, Double distance){
        this.nameA = nameA;
        this.nameB = nameB;
        this.distance = distance;
    }
    public void Connect(){
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "root";
        String sqlCommand = "INSERT Distance(From_City,To_City,Distances)VALUES("+"'"+nameA+"'"+","+"'"+nameB+"'"+","+"'"+distance+"'"+")";
        try(Connection con = DriverManager.getConnection(url,username,password)){
            System.out.println("Подключение збс");
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate(sqlCommand);
            System.out.println("Все записано! "+rows +" строк");
        } catch (SQLException throwables) {
            System.out.println("Подключение НЕ збс");
            throwables.printStackTrace();
        }
    }

}


//Врядли это вообще хоть кто-то прочитает))Но если ты читаешь это, дай знать, мой целеустремленный товарищ)))
