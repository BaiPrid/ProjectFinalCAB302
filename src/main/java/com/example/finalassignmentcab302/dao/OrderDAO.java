package com.example.finalassignmentcab302.dao;

import com.example.finalassignmentcab302.DatabaseConnection;
import com.example.finalassignmentcab302.Tables.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection connection;

    public OrderDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS orders ("
                            + "orderId INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "userId INTEGER NOT NULL, "
                            + "organisationId INTEGER NOT NULL, "
                            + "orderDateTime DATETIME NOT NULL, "
                            + "amount FLOAT NOT NULL, "
                            + "billingAddress VARCHAR NOT NULL, "
                            + "FOREIGN KEY (userId) REFERENCES users(id), "
                            + "FOREIGN KEY (organisationId) REFERENCES organisations(id)"
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void insert(Order order) {
        try {
            PreparedStatement insertOrder = connection.prepareStatement(
                    "INSERT INTO orders (userId, organisationId, orderDateTime, amount, billingAddress) VALUES (?, ?, ?, ?, ?)"
            );
            insertOrder.setInt(1, order.getUserId());
            insertOrder.setInt(2, order.getOrganisationId());
            insertOrder.setString(3, order.getOrderDateTime());
            insertOrder.setFloat(4, order.getAmount());
            insertOrder.setString(5, order.getBillingAddress());
            insertOrder.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void update(Order order) {
        try {
            PreparedStatement updateOrder = connection.prepareStatement(
                    "UPDATE orders SET userId = ?, organisation = ?, orderDateTime = ?, amount = ?, billingAddress = ? WHERE orderId = ?"
            );
            updateOrder.setInt(1, order.getUserId());
            updateOrder.setInt(2, order.getOrganisationId());
            updateOrder.setString(3, order.getOrderDateTime());
            updateOrder.setFloat(4, order.getAmount());
            updateOrder.setString(5, order.getBillingAddress());
            updateOrder.setInt(6, order.getOrderId());
            updateOrder.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void delete(int orderId) {
        try {
            PreparedStatement deleteOrder = connection.prepareStatement("DELETE FROM orders WHERE orderId = ?");
            deleteOrder.setInt(1, orderId);
            deleteOrder.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Order> getAll() {
        List<Order> allOrders = new ArrayList<>();
        try{
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM orders");
            while (rs.next()){
                allOrders.add(
                        new Order(
                                rs.getInt("orderId"),
                                rs.getInt("userId"),
                                rs.getInt("organisationId"),
                                rs.getString("orderDateTime"),
                                rs.getFloat("amount"),
                                rs.getString("billingAddress")
                        )
                );
            }
        }catch (SQLException ex) {
            System.err.println(ex);
        }
        return allOrders;
    }


    public List<Order> getUserOrders(int userID) {
        List<Order> allOrders = new ArrayList<>();
        try{
            PreparedStatement getOrders = connection.prepareStatement("SELECT * FROM orders WHERE userID = ?");
            getOrders.setInt(1, userID);

            ResultSet rs = getOrders.executeQuery();

            while (rs.next()){
                allOrders.add(
                        new Order(
                                rs.getInt("orderId"),
                                rs.getInt("userId"),
                                rs.getInt("organisationId"),
                                rs.getString("orderDateTime"),
                                rs.getFloat("amount"),
                                rs.getString("billingAddress")
                        )
                );
            }
        }catch (SQLException ex) {
            System.err.println(ex);
        }
        return allOrders;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
