package com.example.finalassignmentcab302.dao;
// DAO for organisation answers from create org page questions table
import com.example.finalassignmentcab302.DatabaseConnection;
import com.example.finalassignmentcab302.Tables.OrganisationAnswers;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object class for managing OrganisationAnswer objects in the database.
 * Provides methods to interact with the database for CRUD operations.
 */
// DAO for organisation main table
public class OrganisationAnswersDAO {
    private Connection connection;

    /**
     * Constructor for the OrganisationAnswersDAO class
     * Initialises a connection to the database
     */
    public OrganisationAnswersDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     * Method creates the organisationAnswersTable  in the database with the columns organisationId, category, size, donationOptions, taxableCategory and donorSpecifies
     */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS organisationAnswersTable (" +
                            "organisationId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "category VARCHAR, " +
                            "size VARCHAR, " +
                            "donationOptions VARCHAR, " +
                            "taxableCategory VARCHAR, " +
                            "donorSpecifies BOOLEAN, " +
                            "FOREIGN KEY (organisationId) REFERENCES organisations(id)" +
                            ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     *Inserts an OrganisationAnswers record into the database
     * @param organisationAnswers The OrganisationAnswers object being inserted into the database
     */
    public void insert(OrganisationAnswers organisationAnswers) {
        try {
            PreparedStatement insertOrganisationAnswers = connection.prepareStatement(
                    "INSERT INTO  organisationAnswersTable(category, size, donationOptions, taxableCategory, donorSpecifies) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );
            insertOrganisationAnswers.setString(1, organisationAnswers.getCategory());
            insertOrganisationAnswers.setString(2, organisationAnswers.getSize());
            insertOrganisationAnswers.setString(3, organisationAnswers.getDonationOptions());
            insertOrganisationAnswers.setString(4, organisationAnswers.getTaxableCategory());
            insertOrganisationAnswers.setBoolean(5, organisationAnswers.getDonorSpecifies());
            insertOrganisationAnswers.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Updates an OrganisationAnswers record in the database
     * @param organisationAnswers The organisationAnswers object being updated in the database
     */
    public void update(OrganisationAnswers organisationAnswers) {
        try {
            PreparedStatement updateOrganisationAnswers = connection.prepareStatement(
                    "UPDATE organisationAnswersTable SET category = ?, size = ?, donationOptions = ?, taxableCategory = ?," +
                            " donorSpecifies = ? " + "WHERE organisationId = ?"
            );
            // note change where id = to username and password for forget password
            updateOrganisationAnswers.setString(1, organisationAnswers.getCategory());
            updateOrganisationAnswers.setString(2, organisationAnswers.getSize());
            updateOrganisationAnswers.setString(3, organisationAnswers.getDonationOptions());
            updateOrganisationAnswers.setString(4, organisationAnswers.getTaxableCategory());
            updateOrganisationAnswers.setBoolean(5, organisationAnswers.getDonorSpecifies());
            updateOrganisationAnswers.setInt(6, organisationAnswers.getOrganisationId());
            updateOrganisationAnswers.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Deletes an organisationAnswers record from the database
     * @param organisationId of the organisationAnswers record being deleted from the database
     */
    public void delete(int organisationId) {
        try {
            PreparedStatement deleteOrganisationAnswers = connection.prepareStatement("DELETE FROM organisationAnswersTable WHERE id = ?");
            deleteOrganisationAnswers.setInt(1, organisationId);
            deleteOrganisationAnswers.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Retrieves all order records from the organisationAnswersTable
     */
    public List<OrganisationAnswers> getAll() {
        List<OrganisationAnswers> allOrgAnswers = new ArrayList<>();
        try{
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM organisationAnswersTable");
            while (rs.next()){
                allOrgAnswers.add(
                        new OrganisationAnswers(
                                rs.getInt("organisationId"),
                                rs.getString("category"),
                                rs.getString("size"),
                                rs.getString("donationOptions"),
                                rs.getString("taxableCategory"),
                                rs.getBoolean("donorSpecifies")
                        )
                );
            }
        }catch (SQLException ex) {
            System.err.println(ex);
        }
        return allOrgAnswers;
    }

    /**
     * Gets the organisation answers and puts them into a hashmap with the orgId as the key and the answer values in a string array
     */
    public Map<Integer, String[]> getOrgAnswers() {
        Map<Integer, String[]> allOrganisationAnswers = new HashMap<>();

        try {
            PreparedStatement getOrgAnswersStmt = connection.prepareStatement(
                    "SELECT organisationId, category, size, donationOptions, taxableCategory, donorSpecifies FROM organisationAnswersTable"
            );
            ResultSet rs = getOrgAnswersStmt.executeQuery();

            while (rs.next()) {
                int organisationId = rs.getInt("organisationId");
                String[] orgDetails = new String[5];
                orgDetails[0] = rs.getString("category");
                orgDetails[1] = rs.getString("size");
                orgDetails[2] = rs.getString("donationOptions");
                orgDetails[3] = rs.getString("taxableCategory");
                orgDetails[4] = rs.getString("donorSpecifies");

                allOrganisationAnswers.put(organisationId, orgDetails);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return allOrganisationAnswers;
    }

    /**
     * Retrieves all organisationAnswers in the database with the specified organisation id
     * @param id of the organisation the organisationAnswers are for
     */
    public List<Object> getByid(int id) {
        List<Object> organisationAnswersDetails = new ArrayList<>();
        try {
            PreparedStatement getOrganisation = connection.prepareStatement(
                    "SELECT * FROM organisationAnswersTable WHERE organisationId = ?"
            );
            getOrganisation.setInt(1, id);
            ResultSet rs = getOrganisation.executeQuery();
            if (rs.next()) {
                organisationAnswersDetails.add(rs.getInt("organisationId"));
                organisationAnswersDetails.add(rs.getString("category"));
                organisationAnswersDetails.add(rs.getString("size"));
                organisationAnswersDetails.add(rs.getString("donationOptions"));
                organisationAnswersDetails.add(rs.getString("taxableCategory"));
                organisationAnswersDetails.add(rs.getBoolean("donorSpecifies"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return organisationAnswersDetails;  // Return the list of organisation details
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
