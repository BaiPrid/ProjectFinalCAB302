package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.CurrentUserGLOBAL;
import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.Tables.Organisation;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.example.finalassignmentcab302.dao.UserAnswersDAO;
import com.example.finalassignmentcab302.dao.OrganisationAnswersDAO;
import com.example.finalassignmentcab302.Tables.UserAnswers;
import com.example.finalassignmentcab302.Tables.OrganisationAnswers;

import static com.example.finalassignmentcab302.CurrentUserGLOBAL.currentUser;

public class CharitiesPageController {

    public static String selectedCharityName;

    @FXML
    private String[] nameDescription1;
    @FXML
    private String[] nameDescription2;
    @FXML
    private String[] nameDescription3;

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
    private Button btnAccount;

    @FXML
    private void initialize() {
        UserAnswersDAO userAnswersDAO = new UserAnswersDAO();
        OrganisationDAO organisationDAO = new OrganisationDAO();

        int userId = currentUser; // Replace with actual user ID
        // Gets the answers for the specific user id
        List<String> userAnswers = userAnswersDAO.getUserAnswers(userId);

        if (userAnswers != null) {
            // Get matching organizations based on user answers, returns as a hashmap of organisation id and the amount of matching answers
            Map<Integer, Integer> matchingOrganisations = userAnswersDAO.getMatchingOrganisations(userAnswers);

            // Apply some fucky hashmap manipulation to sort it into descending order based on the values.
            List<Map.Entry<Integer, Integer>> sortedMatches = matchingOrganisations.entrySet().stream().sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())).toList();

            // Number one spot ----------------------------------------------------------------------------------------------
            int firstOrgId = sortedMatches.get(0).getKey();
            nameDescription1 = generateNameDescription(firstOrgId, sortedMatches.get(0).getValue(), 3);
            lblCharity1.setText(nameDescription1[0]);
            txtCharity1.setText(nameDescription1[1]);


            // Number two spot ----------------------------------------------------------------------------------------------
            int secondOrgId = sortedMatches.get(1).getKey();
            nameDescription2 = generateNameDescription(secondOrgId, sortedMatches.get(1).getValue(), 3);
            lblCharity2.setText(nameDescription2[0]);
            txtCharity2.setText(nameDescription2[1]);

            // Number three spot --------------------------------------------------------------------------------------------
            int thirdOrgId = sortedMatches.get(2).getKey();
            nameDescription3 = generateNameDescription(thirdOrgId, sortedMatches.get(2).getValue(), 3);
            lblCharity3.setText(nameDescription3[0]);
            txtCharity3.setText(nameDescription3[1]);

        // If somehow the userid doesn't match user answer
        } else {
            lblCharity1.setText("No user answers found!");
            txtCharity1.setText("Please provide answers to match charities.");
            lblCharity2.setText("");
            txtCharity2.setText("");
            lblCharity3.setText("");
            txtCharity3.setText("");
        }
    }


    // Generates the name and description based on the organisation id
    // NOTE: the total questions are hard coded at the moment, needs to be variable
    private String[] generateNameDescription(int orgId, int matches, int totalQuestions) {
        OrganisationDAO organisationDAO = new OrganisationDAO();

        String description = organisationDAO.getDescription(orgId);
        String name = organisationDAO.getName(orgId);

        // Calculates the percentage match by dividing the amount of matches by the amount of questions asked
        double percentageMatch = ((double) matches / totalQuestions) * 100;

        // Creates a string array for both the name and the description
        String[] result = {name, description + "\nPercentage match: " + String.format("%.2f", percentageMatch) + "%"};

        return result;
    }

    @FXML
    private void setCharityNamefor1(){
        selectedCharityName = nameDescription1[0];
    }

    @FXML
    private void setCharityNamefor2(){
        selectedCharityName = nameDescription2[0];
    }

    @FXML
    private void setCharityNamefor3(){
        selectedCharityName = nameDescription3[0];
    }


    /**
     * Sends the user to the account page corresponding to the current user's ID.
     * @throws IOException if there is a problem loading the page.
     */

    @FXML
    private void handleOpenAccount() throws IOException {
        Stage stage = (Stage) btnAccount.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserAccounts.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    @FXML
    private void handleDonationPagefor1() throws IOException {
        setCharityNamefor1();
        Stage stage = (Stage) btnCharity1.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Donate Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    @FXML
    private void handleDonationPagefor2() throws IOException {
        setCharityNamefor2();
        Stage stage = (Stage) btnCharity1.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Donate Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    @FXML
    private void handleDonationPagefor3() throws IOException {
        setCharityNamefor3();
        Stage stage = (Stage) btnCharity1.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Donate Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }



}


