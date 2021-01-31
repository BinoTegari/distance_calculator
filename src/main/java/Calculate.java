package main.java;

import java.lang.*;
import java.util.Scanner;

public class Calculate {
    public static void main(String[] args) {
        double latitudeA = 53.32055555555556;
        double latitudeB = 53.31861111111111;
        double longitudeA = -1.7297222222222221;
        double longitudeB = -1.6997222222222223;
        System.out.println(distanceCrowflight(latitudeA, latitudeB,
                longitudeA, longitudeB) + " K.M");
        try(Scanner scan = new Scanner(System.in)) {
            String exit = "";
            String nameA = "";
            String nameB = "";
            do {
                System.out.println("Введите название первого города: ");
                nameA = scan.next();
                System.out.println("Введите название второго города: ");
                nameB = scan.next();


            } while (exit == "q");
        }catch (Exception e){
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

