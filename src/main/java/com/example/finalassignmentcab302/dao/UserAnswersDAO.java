package com.example.finalassignmentcab302.dao;
// DAO for organisation answers from create org page questions table
import com.example.finalassignmentcab302.DatabaseConnection;
import com.example.finalassignmentcab302.Tables.OrganisationAnswers;
import com.example.finalassignmentcab302.Tables.UserAnswers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO for organisation main table

public class UserAnswersDAO {
    private Connection connection;

    public UserAnswersDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS userAnswersTable ("
                            + "userId INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "category VARCHAR, "
                            + "size VARCHAR, "
                            + "donationOptions VARCHAR, "
                            + "taxableCategory VARCHAR, "
                            + "donorSpecifies BOOLEAN, "
                            + "userAns1 VARCHAR, "
                            + "userAns2 VARCHAR, "
                            + "userAns3 VARCHAR, "
                            + "FOREIGN KEY (userId) REFERENCES users(id)"
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void insert(UserAnswers userAnswers) {
        try {
            PreparedStatement insertUserAnswers = connection.prepareStatement(
                    "INSERT INTO  userAnswersTable(category, size, donationOptions, taxableCategory, donorSpecifies, userAns1, userAns2, userAns3) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            insertUserAnswers.setString(1, userAnswers.getCategory());
            insertUserAnswers.setString(2, userAnswers.getSize());
            insertUserAnswers.setString(3, userAnswers.getDonationOptions());
            insertUserAnswers.setString(4, userAnswers.getTaxableCategory());
            insertUserAnswers.setBoolean(5, userAnswers.getDonorSpecifies());
            insertUserAnswers.setString(6, userAnswers.getUserAns1());
            insertUserAnswers.setString(7, userAnswers.getUserAns2());
            insertUserAnswers.setString(8, userAnswers.getUserAns3());
            insertUserAnswers.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void update(UserAnswers userAnswers) {
        try {
            PreparedStatement updateUserAnswers = connection.prepareStatement(
                    "UPDATE userAnswersTable SET category = ?, size = ?, donationOptions = ?, taxableCategory = ?, userAns1 = ?, userAns2 = ?, userAns3 = ?" +
                            " donorSpecifies = ? " + "WHERE userId = ?"
            );
            // note change where id = to username and password for forget password
            updateUserAnswers.setString(1, userAnswers.getCategory());
            updateUserAnswers.setString(2, userAnswers.getSize());
            updateUserAnswers.setString(3, userAnswers.getDonationOptions());
            updateUserAnswers.setString(4, userAnswers.getTaxableCategory());
            updateUserAnswers.setString(5, userAnswers.getUserAns1());
            updateUserAnswers.setString(6, userAnswers.getUserAns2());
            updateUserAnswers.setString(7, userAnswers.getUserAns3());
            updateUserAnswers.setBoolean(8, userAnswers.getDonorSpecifies());
            updateUserAnswers.setInt(9, userAnswers.getUserId());
            updateUserAnswers.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    ///////////////////////////// NEW SECTION ////////////////////////////////////////
    public void updateAnswersOnly(UserAnswers userAnswers) {
        try {
            PreparedStatement updateUserAnswers = connection.prepareStatement(
                    "UPDATE userAnswersTable SET  userAns1 = ?, userAns2 = ?, userAns3 = ?" +
                            "WHERE userId = ?"
            );
            // note change where id = to username and password for forget password

            updateUserAnswers.setString(1, userAnswers.getUserAns1());
            updateUserAnswers.setString(2, userAnswers.getUserAns2());
            updateUserAnswers.setString(3, userAnswers.getUserAns3());
            updateUserAnswers.setInt(4, userAnswers.getUserId());
            updateUserAnswers.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    ///////////////////////////// NEW SECTION ////////////////////////////////////////




    public void delete(int userId) {
        try {
            PreparedStatement deleteOrganisationAnswers = connection.prepareStatement("DELETE FROM userAnswersTable WHERE id = ?");
            deleteOrganisationAnswers.setInt(1, userId);
            deleteOrganisationAnswers.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<OrganisationAnswers> getAll() {
        List<OrganisationAnswers> alluserAnswers = new ArrayList<>();
        try{
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM organisationAnswersTable");
            while (rs.next()){
                alluserAnswers.add(
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
        return alluserAnswers;
    }


    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}

