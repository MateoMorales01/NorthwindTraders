package com.pluralsight;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = args[0];
        String password = args [1];

        Connection connection = DriverManager.getConnection(url,username,password);


        // define your query
        String query = """
                        SELECT ProductID, ProductName, UnitPrice, UnitsInStock
                        FROM products
                        Where ProductName Like ?
                        """;

        // create statement
        // the statement is tied to the open connection
        PreparedStatement statement = connection.prepareStatement(query);

        String searchTerm = "%Cha%";
        //set parameters
        statement.setString(1,searchTerm);

        // 2. Execute your query
        ResultSet results = statement.executeQuery();

        // process the results
        while (results.next()) {
            int ProductID = results.getInt("ProductID");
            String ProductName = results.getString("ProductName");
            double UnitPrice = results.getDouble("UnitPrice");
            int UnitsInStock = results.getInt("UnitsInStock");

            System.out.println(ProductID);
            System.out.println(ProductName);
            System.out.println(UnitPrice);
            System.out.println(UnitsInStock);
            System.out.println("-----------------------");
        }

        results.close();
        statement.close();
        connection.close();

    }
}
