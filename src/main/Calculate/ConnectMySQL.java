package main.Calculate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
//            System.out.println("Подключение ок");
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate(sqlCommand);
            System.out.println("Все записано! "+rows +" строка");
        } catch (SQLException throwables) {
            System.out.println("С подключением что-то не задалось");
            throwables.printStackTrace();
        }
    }

}
