package com.example.finalassignmentcab302.dao;

import com.example.finalassignmentcab302.DatabaseConnection;
import com.example.finalassignmentcab302.Tables.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Data Access Object class for managing Order objects in the database.
 * Provides methods to interact with the database for CRUD operations.
  */
public class OrderDAO {
    private Connection connection;


    /**
     * Constructor for the OrderDAO class
     * Initialises a connection to the database
     */
    public OrderDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     * Method creates the orders table in the database with the columns orderId, userId, organisationId, orderDateTime, amount and billingAddress
     */
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

    /**
     *Inserts an order record into the database
     * @param order The order object being inserted into the database
     */
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

    /**
     * Updates an order record in the database
     * @param order The order object being updated in the database
     */
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

    /**
     * Deletes an order record from the database
     * @param orderId of the order record being deleted from the database
     */
    public void delete(int orderId) {
        try {
            PreparedStatement deleteOrder = connection.prepareStatement("DELETE FROM orders WHERE orderId = ?");
            deleteOrder.setInt(1, orderId);
            deleteOrder.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Retrieves all order records from the orders table
     */
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

    /**
     * Retrieves all orders placed by the same user given that usersId
     * @param userID of the user the orders were placed by
     */
    public List<Order> getUserOrders(int userID) {
        List<Order> allOrders = new ArrayList<Order>();
        try{
            PreparedStatement getOrders = connection.prepareStatement("SELECT * FROM orders WHERE userId = ?");
            getOrders.setInt(1, userID);

            ResultSet rs = getOrders.executeQuery();

            while (rs.next())
            {
                allOrders.add
                (
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

    public Integer getOrgID(int id) {
        try {
            PreparedStatement getOrganisation = connection.prepareStatement(
                    "SELECT organisationId FROM orders WHERE id = ?"
            );
            getOrganisation.setInt(1, id);
            ResultSet rs = getOrganisation.executeQuery();
            if (rs.next()) {
                return rs.getInt("organisationId");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null; // Return null if no description is found or if an exception occurs
    }

    /**
     * Closes the connection to the database
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
