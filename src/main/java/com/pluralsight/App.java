package com.pluralsight;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = args[0];
        String password = args[1];

        displayProducts(url, username, password);
        displayCustomers(url, username, password);
    }


    private static void displayProducts(String url, String username, String password) {
        displayProduct(url, username, password);
    }

    private static void displayProduct(String url, String username, String password) {
        String query = """
                    SELECT ProductID, ProductName, UnitPrice, UnitsInStock
                    FROM products
                    Where ProductName Like ?
                    """;

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            String searchTerm = "%Cha%";
            statement.setString(1, searchTerm);

            try (ResultSet results = statement.executeQuery()) {

                while (results.next()) {
                    int productID = results.getInt("ProductID");
                    String productName = results.getString("ProductName");
                    double unitPrice = results.getDouble("UnitPrice");
                    int unitsInStock = results.getInt("UnitsInStock");

                    System.out.println(productID);
                    System.out.println(productName);
                    System.out.println(unitPrice);
                    System.out.println(unitsInStock);
                    System.out.println("-----------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an error retrieving the data. Please try again.");
            e.printStackTrace();
        }
    }

    private static void displayCustomers(String url, String username, String password) {
        String query = """
                SELECT ContactName, CompanyName, City, Country, Phone
                FROM customers
                Order by Country;
                """;

        try (Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet results = statement.executeQuery()) {

                while (results.next()) {
                    String contactName = results.getString("ContactName");
                    String companyName = results.getString("CompanyName");
                    String city = results.getString("City");
                    String country = results.getString("Country");
                    String phone = results.getString("Phone");

                    System.out.println(contactName);
                    System.out.println(companyName);
                    System.out.println(city);
                    System.out.println(country);
                    System.out.println(phone);
                    System.out.println("-----------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an error retrieving the data. Please try again.");
            e.printStackTrace();
        }
    }
}