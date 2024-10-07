package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.CurrentUserGLOBAL;
import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.Tables.Organisation;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import javafx.fxml.FXML;
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

public class CharitiesPageController {

    private Stage primaryStage; // Add a Stage field


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

    @FXML
    private void initialize() {
        UserAnswersDAO userAnswersDAO = new UserAnswersDAO();

        int userId = CurrentUserGLOBAL.currentUser;
        // Gets the answers for the specific user id
        List<String> userAnswers = userAnswersDAO.getUserAnswers(userId);

        if (userAnswers != null) {
            // Get matching organizations based on user answers, returns as a hashmap of organisation id and the amount of matching answers
            Map<Integer, Integer> matchingOrganisations = userAnswersDAO.getMatchingOrganisations(userAnswers);

            // Apply some fucky hashmap manipulation to sort it into descending order based on the values.
            List<Map.Entry<Integer, Integer>> sortedMatches = matchingOrganisations.entrySet().stream().sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())).toList();

            int totalQuestions = 3;

            // Number one spot ----------------------------------------------------------------------------------------------
            int firstOrgId = sortedMatches.get(0).getKey();
            String[] nameDescription1 = generateNameDescription(firstOrgId, sortedMatches.get(0).getValue(), totalQuestions);
            lblCharity1.setText(nameDescription1[0]);
            txtCharity1.setText(nameDescription1[1]);

            // Number two spot ----------------------------------------------------------------------------------------------
            int secondOrgId = sortedMatches.get(1).getKey();
            String[] nameDescription2 = generateNameDescription(secondOrgId, sortedMatches.get(1).getValue(), totalQuestions);
            lblCharity2.setText(nameDescription2[0]);
            txtCharity2.setText(nameDescription2[1]);

            // Number three spot --------------------------------------------------------------------------------------------
            int thirdOrgId = sortedMatches.get(2).getKey();
            String[] nameDescription3 = generateNameDescription(thirdOrgId, sortedMatches.get(2).getValue(), totalQuestions);
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
    private void handleOpenHome() throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.start(stage); // Switch to the new page
    }

}


