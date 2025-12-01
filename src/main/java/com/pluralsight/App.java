package com.pluralsight;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = "root";
        String password = "yearup";

        Connection myConnection = DriverManager.getConnection(url,username,password);

        Statement myStatement = myConnection.createStatement();

        String myQuery = "SELECT * FROM products";

        ResultSet resultName = myStatement.executeQuery(myQuery);

        while (resultName.next()) {
            String row = resultName.getString("ProductName");
            System.out.println(row);
        }
        myConnection.close();
    }
}
