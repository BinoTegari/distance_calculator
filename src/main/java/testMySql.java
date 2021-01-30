package main.java;

//
//import java.lang.reflect.InvocationTargetException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class testMySql {
//    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        final String port = "3306";
//        final String url = "jdbc:mysql://localhost:3306/test";
//        final String user = "root";
//        final String password = "root";
//
//        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//        try {
//            Connection conn = DriverManager.getConnection(url, user, password);
//            Statement statement = conn.createStatement();
//            System.out.println("Connection successful");
//        } catch (SQLException throwables) {
//            System.out.println("Нихера не работает");
//            throwables.printStackTrace();
//        }
//    }
//}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class testMySql {
    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()){

                System.out.println("Connection to Store DB succesfull!");
                Statement statement = conn.createStatement();
                int rows = statement.executeUpdate("INSERT City(name, latitude, longitude) VALUES('Samara', 75.6677, 46.875),"+"('Moscow', 23.66, 66.666)");
                System.out.println(rows + " добавлены в базу данных");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
    public static Connection getConnection() throws SQLException, IOException{

        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("/Users/user/IdeaProjects/distance_calculator/distance_calculator/library/Database.properties"))){
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}
