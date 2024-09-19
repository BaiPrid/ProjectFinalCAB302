package com.example.finalassignmentcab302.dao;

import com.example.finalassignmentcab302.DatabaseConnection;
import com.example.finalassignmentcab302.Tables.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS users ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "firstName VARCHAR NOT NULL, "
                            + "lastName VARCHAR NOT NULL, "
                            + "userName VARCHAR UNIQUE NOT NULL, "
                            + "password VARCHAR NOT NULL, "
                            + "phoneNumber VARCHAR NOT NULL, "
                            + "economicClass VARCHAR NOT NULL, "
                            + "email VARCHAR NOT NULL"
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void insert(User user) {
        try {
            PreparedStatement insertAccount = connection.prepareStatement(
                    "INSERT INTO users (firstName, lastName, userName, password, phoneNumber, economicClass, email) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            insertAccount.setString(1, user.getFirstName());
            insertAccount.setString(2, user.getLastName());
            insertAccount.setString(3, user.getUserName());
            insertAccount.setString(4, user.getPassword());
            insertAccount.setString(5, user.getPhoneNumber());
            insertAccount.setString(6, user.getEconomicClass());
            insertAccount.setString(7, user.getEmail());
            insertAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void update(User user) {
        try {
            PreparedStatement updateAccount = connection.prepareStatement(
                    "UPDATE users SET firstName = ?, lastName = ?, userName = ?, password = ?, phoneNumber = ?, economicClass = ?, email = ? WHERE id = ?"
            );
            // note change where id = to username and password for forget password
            updateAccount.setString(1, user.getFirstName());
            updateAccount.setString(2, user.getLastName());
            updateAccount.setString(3, user.getUserName());
            updateAccount.setString(4, user.getPassword());
            updateAccount.setString(5, user.getPhoneNumber());
            updateAccount.setString(6, user.getEconomicClass());
            updateAccount.setString(7, user.getEmail());
            updateAccount.setInt(8, user.getId());
            updateAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement deleteUser = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            deleteUser.setInt(1, id);
            deleteUser.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        try{
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM users");
            while (rs.next()){
                allUsers.add(
                        new User(
                                rs.getInt("id"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("userName"),
                                rs.getString("password"),
                                rs.getString("economicClass"),
                                rs.getString("phoneNumber"),
                                rs.getString("email")
                        )
                );
            }
        }catch (SQLException ex) {
            System.err.println(ex);
        }
        return allUsers;
    }

    ////////////////////////NEW SECTION////////////////////////////
    //This gets the user id based on username and password
    public int getUserID(String username, String password) {

        int userID = -1;
        try {
            PreparedStatement getUserID = connection.prepareStatement("SELECT id FROM users WHERE userName = ? AND password = ?");
            getUserID.setString(1, username);
            getUserID.setString(2, password);

            ResultSet rs = getUserID.executeQuery();

            if (rs.next()){

                 userID = rs.getInt("id");

            }


        } catch (SQLException e) {
            e.printStackTrace();  // Handle SQL exceptions
        }

        return userID;
    }
    //////////////////////////////////////////////////////////////////////////////////
    public boolean login(String username, String password) {


        try {
            PreparedStatement logincheck = connection.prepareStatement("SELECT * FROM users WHERE userName = ? AND password = ?");
            logincheck.setString(1, username);  // Bind the username
            logincheck.setString(2, password);  // Bind the password

            ResultSet rs = logincheck.executeQuery();

            if (rs.next()) {
                // User found with matching username and password
                return true;  // Login successful
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Handle SQL exceptions
        }

        return false;  // Login failed (user not found or SQL error)
    }


    public User getByLogin(int id) {
        try {
            PreparedStatement getUser = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            getUser.setInt(1, id);
            ResultSet rs = getUser.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("economicClass"),
                        rs.getString("phoneNumber"),
                        rs.getString("email")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}