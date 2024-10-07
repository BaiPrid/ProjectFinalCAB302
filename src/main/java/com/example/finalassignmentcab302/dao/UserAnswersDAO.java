package com.example.finalassignmentcab302.dao;
// DAO for organisation answers from create org page questions table
import com.example.finalassignmentcab302.DatabaseConnection;
import com.example.finalassignmentcab302.Tables.Organisation;
import com.example.finalassignmentcab302.Tables.OrganisationAnswers;
import com.example.finalassignmentcab302.Tables.UserAnswers;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    "UPDATE userAnswersTable SET category = ?, size = ?, donationOptions = ?, taxableCategory = ?, userAns1 = ?, userAns2 = ?, userAns3 = ?," +
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


    public void updateUserAnswers(UserAnswers userAnswers) {
        try {
            PreparedStatement updateUserAnswers = connection.prepareStatement(
                    "UPDATE userAnswersTable SET category = ?, size = ?, donationOptions = ?, taxableCategory = ?," +
                            " donorSpecifies = ? " + "WHERE userId = ?"
            );
            // note change where id = to username and password for forget password
            updateUserAnswers.setString(1, userAnswers.getCategory());
            updateUserAnswers.setString(2, userAnswers.getSize());
            updateUserAnswers.setString(3, userAnswers.getDonationOptions());
            updateUserAnswers.setString(4, userAnswers.getTaxableCategory());
            updateUserAnswers.setBoolean(5, userAnswers.getDonorSpecifies());
            updateUserAnswers.setInt(6, userAnswers.getUserId());
            updateUserAnswers.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void delete(int userId) {
        try {
            PreparedStatement deleteOrganisationAnswers = connection.prepareStatement("DELETE FROM userAnswersTable WHERE id = ?");
            deleteOrganisationAnswers.setInt(1, userId);
            deleteOrganisationAnswers.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }


    public List<UserAnswers> getAll() {
        List<UserAnswers> alluserAnswers = new ArrayList<>();
        try{
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM userAnswersTable");
            while (rs.next()){
                alluserAnswers.add(
                        new UserAnswers(
                                rs.getInt("userId"),
                                rs.getString("category"),
                                rs.getString("size"),
                                rs.getString("donationOptions"),
                                rs.getString("taxableCategory"),
                                rs.getBoolean("donorSpecifies"),
                                rs.getString("userAns1"),
                                rs.getString("userAns2"),
                                rs.getString("userAns3")
                        )
                );
            }
        }catch (SQLException ex) {
            System.err.println(ex);
        }
        return alluserAnswers;
    }


    public Map<Integer, Integer> getMatchingOrganisations(List<String> userAnswers) {
        OrganisationAnswersDAO organisationAnswersDAO = new OrganisationAnswersDAO();
        Map<Integer, String[]> organisationAnswers = organisationAnswersDAO.getOrgAnswers();

        Map<Integer, Integer> matchingOrganisations = new HashMap<>();

        for (Map.Entry<Integer, String[]> entry : organisationAnswers.entrySet()) {
            int matches = 0;
            String[] orgAnswers = entry.getValue();

            for (int i = 0; i < orgAnswers.length && i < userAnswers.size(); i++) {
                String[] orgOptions = orgAnswers[i].split(",\\s*");

                for (String option : orgOptions) {
                    if (option.equals(userAnswers.get(i))) {
                        matches++;
                        break;
                    }
                }
            }

            matchingOrganisations.put(entry.getKey(), matches);
        }

        return matchingOrganisations;
    }


    // For current implementation, gets the user answers from the table
    public List<String> getUserAnswers(int userId) {
        List<String> userAnswersList = new ArrayList<>();

        try {
            PreparedStatement getUserAnswersStmt = connection.prepareStatement(
                    "SELECT category, size, donationOptions, taxableCategory, donorSpecifies FROM userAnswersTable WHERE userId = ?"
            );
            getUserAnswersStmt.setInt(1, userId);
            ResultSet rs = getUserAnswersStmt.executeQuery();

            if (rs.next()) {
                userAnswersList.add(rs.getString("category"));
                userAnswersList.add(rs.getString("size"));
                userAnswersList.add(rs.getString("donationOptions"));
                userAnswersList.add(rs.getString("taxableCategory"));
                userAnswersList.add(rs.getString("donorSpecifies"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return userAnswersList;
    }


    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}

