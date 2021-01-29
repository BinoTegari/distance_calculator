package main.java;

import java.lang.*;

public class Calculate {
    public static void main(String[] args) {
        double latitudeA = 53.32055555555556;
        double latitudeB = 53.31861111111111;
        double longitudeA = -1.7297222222222221;
        double longitudeB = -1.6997222222222223;
        System.out.println(distanceCrowflight(latitudeA, latitudeB,
                longitudeA, longitudeB) + " K.M");
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

