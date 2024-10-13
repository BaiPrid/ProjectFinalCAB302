package com.example.finalassignmentcab302.dao;

import com.example.finalassignmentcab302.DatabaseConnection;
import com.example.finalassignmentcab302.Tables.Organisation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO for organisation main table
/**
 * Data Access Object class for managing Organisation objects in the database.
 * Provides methods to interact with the database for CRUD operations.
 */
public class OrganisationDAO {
    private Connection connection;

    /**
     * Constructor for the OrganisationDAO class
     * Initialises a connection to the database
     */
    public OrganisationDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     * Creates the organisations table in the database with columns for id, name, description, imgPath, email, groupSupported, userName, and passWord.
     */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS organisations ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "name VARCHAR NOT NULL, "
                            + "description VARCHAR NOT NULL, "
                            + "imgPath VARCHAR,"
                            + "email VARCHAR NOT NULL, "
                            + "groupSupported VARCHAR NOT NULL, "
                            + "userName VARCHAR NOT NULL, "
                            + "passWord VARCHAR NOT NULL"
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Inserts an Organisation record into the database.
     * @param organisation The Organisation object being inserted into the database.
     */
    public void insert(Organisation organisation) {
        try {
            PreparedStatement insertOrganisation = connection.prepareStatement(
                    "INSERT INTO organisations (name, description, imgPath, email, groupSupported, userName, passWord) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            insertOrganisation.setString(1, organisation.getName());
            insertOrganisation.setString(2, organisation.getDescription());
            insertOrganisation.setString(3, organisation.getImgPath());
            insertOrganisation.setString(4, organisation.getEmail());
            insertOrganisation.setString(5, organisation.getGroupSupported());
            insertOrganisation.setString(6, organisation.getUserName());
            insertOrganisation.setString(7, organisation.getPassWord());
            insertOrganisation.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Updates an Organisation record in the database.
     * @param organisation The Organisation object being updated in the database.
     */
    public void update(Organisation organisation) {
        try {
            PreparedStatement updateOrganisation = connection.prepareStatement(
                    "UPDATE users SET name = ?, description = ?, imgPath = ?, email = ?, groupSupported = ?, userName = ?, passWord = ? " +
                            "WHERE id = ?"
            );
            // note change where id = to username and password for forget password
            updateOrganisation.setString(1, organisation.getName());
            updateOrganisation.setString(2, organisation.getDescription());
            updateOrganisation.setString(3, organisation.getImgPath());
            updateOrganisation.setString(4, organisation.getEmail());
            updateOrganisation.setString(5, organisation.getGroupSupported());
            updateOrganisation.setString(6, organisation.getUserName());
            updateOrganisation.setString(7, organisation.getPassWord());
            updateOrganisation.setInt(8, organisation.getId());
            updateOrganisation.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Deletes an Organisation record from the database.
     * @param id The id of the Organisation record being deleted from the database.
     */
    public void delete(int id) {
        try {
            PreparedStatement deleteOrganisation = connection.prepareStatement("DELETE FROM organisations WHERE id = ?");
            deleteOrganisation.setInt(1, id);
            deleteOrganisation.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Retrieves all Organisation records from the database.
     * @return A list of all Organisation objects in the database.
     */
    public List<Organisation> getAll() {
        List<Organisation> allOrganisation = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM organisations");
            while (rs.next()) {
                allOrganisation.add(
                        new Organisation(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getString("imgPath"),
                                rs.getString("email"),
                                rs.getString("groupSupported"),
                                rs.getString("userName"),
                                rs.getString("passWord")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return allOrganisation;
    }

    /**
     * Retrieves an Organisation record by its name from the database.
     * @param name The name of the Organisation to retrieve.
     * @return A list containing the details of the Organisation.
     */
    // Method to retrieve organisation details by name and return them in a list
    public List<Object> getByName(String name) {
        List<Object> organisationDetails = new ArrayList<>();
        try {
            PreparedStatement getOrganisation = connection.prepareStatement(
                    "SELECT * FROM organisations WHERE name = ?"
            );
            getOrganisation.setString(1, name);
            ResultSet rs = getOrganisation.executeQuery();
            if (rs.next()) {
                // Add each field from the result set to the list
                organisationDetails.add(rs.getInt("id"));
                organisationDetails.add(rs.getString("name"));
                organisationDetails.add(rs.getString("description"));
                organisationDetails.add(rs.getString("imgPath"));
                organisationDetails.add(rs.getString("email"));
                organisationDetails.add(rs.getString("groupSupported"));
                organisationDetails.add(rs.getString("userName"));
                organisationDetails.add(rs.getString("passWord"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return organisationDetails;  // Return the list of organisation details
    }

    /**
     * Retrieves an Organisation record by its id from the database.
     * @param id The id of the Organisation to retrieve.
     * @return The Organisation object matching the specified id.
     */
    public Organisation getByLogin(int id) {
        try {
            PreparedStatement getOrganisation = connection.prepareStatement("SELECT * FROM organisations WHERE id = ?");
            getOrganisation.setInt(1, id);
            ResultSet rs = getOrganisation.executeQuery();
            if (rs.next()) {
                return new Organisation(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("imgPath"),
                        rs.getString("email"),
                        rs.getString("groupSupported"),
                        rs.getString("userName"),
                        rs.getString("passWord")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    /**
     * Retrieves the description of an Organisation based on its id.
     * @param id The id of the Organisation.
     * @return The description of the Organisation.
     */
    public String getDescription(int id) {
        try {
            PreparedStatement getOrganisation = connection.prepareStatement(
                    "SELECT description FROM organisations WHERE id = ?"
            );
            getOrganisation.setInt(1, id);
            ResultSet rs = getOrganisation.executeQuery();
            if (rs.next()) {
                return rs.getString("description");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null; // Return null if no description is found or if an exception occurs
    }


    /**
     * Retrieves the name of an Organisation based on its id.
     * @param id The id of the Organisation.
     * @return The name of the Organisation.
     */
    public String getName(int id) {
        try {
            PreparedStatement getOrganisation = connection.prepareStatement(
                    "SELECT name FROM organisations WHERE id = ?"
            );
            getOrganisation.setInt(1, id);
            ResultSet rs = getOrganisation.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null; // Return null if no description is found or if an exception occurs
    }


    /**
     * Validates the login credentials by checking the username and password in the database.
     * @param username The username to check.
     * @param password The password to check.
     * @return true if the login credentials are valid, otherwise false.
     */
    public boolean login(String username, String password) {


        try {
            PreparedStatement logincheck = connection.prepareStatement("SELECT * FROM organisations WHERE userName = ? AND password = ?");
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
     * Checks whether a given username is unique in the organisations table.
     * @param username The username to check.
     * @return true if the username exists, otherwise false.
     */
    // check that the username is unique
    public Boolean CheckOrganisationUserName(String username) {
        try {
            PreparedStatement getUserName = connection.prepareStatement("SELECT userName FROM organisations WHERE userName = ?");
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
     * Checks if the given email is unique in the database.
     * @param email The email to check.
     * @return True if the email exists.
     */
    public Boolean CheckOrganisationEmail(String email) {
        try {
            PreparedStatement getOrganisationEmail = connection.prepareStatement("SELECT email FROM organisations WHERE email = ?");
            getOrganisationEmail.setString(1, email);
            ResultSet rs = getOrganisationEmail.executeQuery();

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
     * Checks if the given organisation name is unique in the database.
     * @param name The organisation name to check.
     * @return True if the name exists.
     */
    public Boolean CheckOrganisationName(String name) {
        try {
            PreparedStatement getOrganisationName = connection.prepareStatement("SELECT name FROM organisations WHERE name = ?");
            getOrganisationName.setString(1, name);
            ResultSet rs = getOrganisationName.executeQuery();

            // Return true if the username exists
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return false; // Return false if the name does not exist
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


