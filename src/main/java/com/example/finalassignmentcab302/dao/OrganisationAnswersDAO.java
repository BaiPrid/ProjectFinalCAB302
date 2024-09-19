package com.example.finalassignmentcab302.dao;
// DAO for organisation answers from create org page questions table
import com.example.finalassignmentcab302.DatabaseConnection;
import com.example.finalassignmentcab302.Tables.OrganisationAnswers;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// DAO for organisation main table

public class OrganisationAnswersDAO {
    private Connection connection;

    public OrganisationAnswersDAO() {
        connection = DatabaseConnection.getInstance();
    }

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

    public void delete(int organisationId) {
        try {
            PreparedStatement deleteOrganisationAnswers = connection.prepareStatement("DELETE FROM organisationAnswersTable WHERE id = ?");
            deleteOrganisationAnswers.setInt(1, organisationId);
            deleteOrganisationAnswers.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

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

    public Map<Integer, String[]> getOrgAnswers() {
        Map<Integer, String[]> allOrganisationAnswers = new HashMap<>();
        int orgId = 1; // For testing purposes, will change once we introduce the orgId into orgAnswersTable

        try {
            PreparedStatement getOrgAnswersStmt = connection.prepareStatement(
                    "SELECT category, size, donationOptions FROM organisationAnswersTable"
            );
            ResultSet rs = getOrgAnswersStmt.executeQuery();

            while (rs.next() && orgId <= 6) {
                //int orgId = rs.getInt("organisationId");
                String[] orgDetails = new String[3];
                orgDetails[0] = rs.getString("category");
                orgDetails[1] = rs.getString("size");
                orgDetails[2] = rs.getString("donationOptions");

                allOrganisationAnswers.put(orgId, orgDetails);
                orgId++;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return allOrganisationAnswers;
    }


    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
