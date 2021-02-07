package main.Calculate;

import java.sql.SQLOutput;

public class Calculate {
    static Double distance;
    public static void main(String[] args) {

        ParseXML.parseFromXML();
        distance = distanceCrowflight(ParseXML.latitudeA,ParseXML.latitudeB,ParseXML.longitudeA,ParseXML.longitudeB);
        System.out.println("Расстояние между городами "+ ParseXML.nameA +" и "+ParseXML.nameB+" равно "+distance);
        ConnectMySQL connectMySQL = new ConnectMySQL(ParseXML.nameA,ParseXML.nameB,distance);
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
}


//Врядли это вообще хоть кто-то прочитает))Но если ты читаешь это, дай знать, мой целеустремленный товарищ)))
