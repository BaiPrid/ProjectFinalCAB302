package com.example.finalassignmentcab302;

import com.example.finalassignmentcab302.DatabaseConnection;
import com.example.finalassignmentcab302.Tables.Organisation;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import com.example.finalassignmentcab302.dao.UserAnswersDAO;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CharitiesPageController {

    private Stage primaryStage; // Add a Stage field

    @FXML
    private Label lblHeader;

    @FXML
    private Label lblCharity1;
    @FXML
    private Text txtCharity1;
    @FXML
    private Button btnCharity1;

    @FXML
    private Label lblCharity2;
    @FXML
    private Text txtCharity2;
    @FXML
    private Button btnCharity2;

    @FXML
    private Label lblCharity3;
    @FXML
    private Text txtCharity3;
    @FXML
    private Button btnCharity3;

    @FXML
    private Button btnLogout;

    //Test if user is logged in
    /*
    if (!userLogin) {
        lblHeader.setText("Please login to continue");
    }
    else {
        userID = userLogin;
    }

    private OrganisationDAO organisationDAO;
    private UserAnswersDAO userAnswersDAO;

    public void OrganisationMatchingService() {
        Connection connection = DatabaseConnection.getInstance();
        this.organisationDAO = new OrganisationDAO();
        this.userAnswersDAO = new UserAnswersDAO();
    }

    public List<Organisation> getMatchingOrganisations(int userID) {
        UserAnswers userAnswers = getUserAnswersByID(userID);

        List<Organisation> allOrganisations = organisationDAO.getAll();

        List<Organisation> matchingOrganisations = new ArrayList<>();

        for (Organisation organisation : allOrganisations) {
            if (isOrganisationMatching(userAnswers, organisation)) {
                matchingOrganisations.add(organisation);
            }
        }

        return matchingOrganisations;
    }

    private UserAnswers getUserAnswersByID(int userID) {
        return userAnswersDAO.getUserAnswersByID(userID);
    }

    private boolean isOrganisationMatching(UserAnswers userAnswers, Organisation organisation) {
        return userAnswers.getCategory().equalsIgnoreCase(organisation.getGroupSupported()) &&
                userAnswers.getSize().equalsIgnoreCase(organisation.getGroupSupported()) &&
                userAnswers.getDonationOptions().equalsIgnoreCase(organisation.getGroupSupported()) &&
                userAnswers.getTaxableCategory().equalsIgnoreCase(organisation.getGroupSupported());
    }

     */

    @FXML
    private void initialize() {

        if (true) {
            lblCharity1.setText("");
            txtCharity1.setText("");
        }
        else {
            lblCharity1.setText("Missing Charity.");
            txtCharity1.setText("Please wait for more charities to be added!.");
        }

        if (true) {
            lblCharity2.setText("");
            txtCharity2.setText("");
        }
        else {
            lblCharity2.setText("Missing Charity.");
            txtCharity2.setText("Please wait for more charities to be added!.");
        }

        if (true) {
            lblCharity3.setText("");
            txtCharity3.setText("");
        }
        else {
            lblCharity3.setText("Missing Charity.");
            txtCharity3.setText("Please wait for more charities to be added!.");
        }
    }

    @FXML
    private void handleOpenHome() throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.start(stage); // Switch to the new page
    }

}