package com.example.finalassignmentcab302.dao;

import com.example.finalassignmentcab302.DatabaseConnection;
import com.example.finalassignmentcab302.Tables.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Data Access Object class for managing User objects in the database.
 * Provides methods to interact with the database for CRUD operations.
 */
public class UserDAO {
    private Connection connection;

    /**
     * Constructor for UserDAO.
     * Initializes the connection to the database.
     */
    public UserDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     * Creates the users table in the database with columns for id, firstName, lastName, userName,
     * password, phoneNumber, economicClass, and email.
     */
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
                            + "phoneNumber INTEGER NOT NULL, "
                            + "economicClass VARCHAR NOT NULL, "
                            + "email VARCHAR NOT NULL"
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Inserts a new user record into the database.
     * @param user The User object containing the user's details.
     */
    public void insert(User user) {
        try {
            PreparedStatement insertAccount = connection.prepareStatement(
                    "INSERT INTO users (firstName, lastName, userName, password, phoneNumber, economicClass, email) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            insertAccount.setString(1, user.getFirstName());
            insertAccount.setString(2, user.getLastName());
            insertAccount.setString(3, user.getUserName());
            insertAccount.setString(4, user.getPassword());
            insertAccount.setInt(5, user.getPhoneNumber());
            insertAccount.setString(6, user.getEconomicClass());
            insertAccount.setString(7, user.getEmail());
            insertAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    /**
     * Updates an existing user record in the database.
     * @param user The User object containing updated user details.
     */
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
            updateAccount.setInt(5, user.getPhoneNumber());
            updateAccount.setString(6, user.getEconomicClass());
            updateAccount.setString(7, user.getEmail());
            updateAccount.setInt(8, user.getId());
            updateAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Deletes a user record from the database based on the user ID.
     * @param id The ID of the user to delete.
     */
    public void delete(int id) {
        try {
            PreparedStatement deleteUser = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            deleteUser.setInt(1, id);
            deleteUser.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Retrieves all user records from the users table.
     * @return A list of User objects representing all users in the database.
     */
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
                                rs.getInt("phoneNumber"),
                                rs.getString("email")
                        )
                );
            }
        }catch (SQLException ex) {
            System.err.println(ex);
        }
        return allUsers;
    }

    /**
     * Retrieves the user ID based on the provided username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The ID of the user
     */
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


    /**
     * Validates user login by checking the username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if the username and password match an existing user, false otherwise.
     */
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

    /**
     * Retrieves a user record based on the user ID.
     * @param id The ID of the user to retrieve.
     * @return A User object representing the user if found, otherwise null.
     */
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
                        rs.getInt("phoneNumber"),
                        rs.getString("email")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    /**
     * Checks if a username is unique in the database.
     * @param username The username to check.
     * @return true if the username exists, false otherwise.
     */
    // check that the username is unique
    public Boolean CheckUsername(String username) {
        try {
            PreparedStatement getUserName = connection.prepareStatement("SELECT userName FROM users WHERE userName = ?");
            getUserName.setString(1, username);
            ResultSet rs = getUserName.executeQuery();

            // Return true if the username exists
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return false; // Return false if the username does not exist
    }

    /**
     * Checks if an email is already associated with an account.
     * @param email The email to check.
     * @return true if an account with the email exists, false otherwise.
     */
    // check is user already has acc with email
    public Boolean checkEmail(String email) {
        try {
            PreparedStatement getEmail = connection.prepareStatement("SELECT email FROM users WHERE email = ?");
            getEmail.setString(1, email);
            ResultSet rs = getEmail.executeQuery();
            return rs.next(); // Returns true if an account with the email exists
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return false; // Return false if there is no email found
    }

    /**
     * Closes the connection to the database.
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}