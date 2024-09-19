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
        OrganisationDAO organisationDAO = new OrganisationDAO();

        int userId = CurrentUserGLOBAL.currentUser; // Replace with actual user ID
        List<String> userAnswers = userAnswersDAO.getUserAnswers(userId);

        if (userAnswers != null) {
            // Get matching organizations based on user answers
            Map<Integer, Integer> matchingOrganisations = userAnswersDAO.getMatchingOrganisations(userAnswers);

            List<Map.Entry<Integer, Integer>> sortedMatches = matchingOrganisations.entrySet().stream().sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())).toList();

            // Check if there are any matching organizations
            int firstOrgId = sortedMatches.get(0).getKey();
            String[] nameDescription1 = generateNameDescription(firstOrgId, sortedMatches.get(0).getValue(), 3);
            lblCharity1.setText(nameDescription1[0]);
            txtCharity1.setText(nameDescription1[1]);

            int secondOrgId = sortedMatches.get(1).getKey();
            String[] nameDescription2 = generateNameDescription(secondOrgId, sortedMatches.get(1).getValue(), 3);
            lblCharity1.setText(nameDescription2[0]);
            txtCharity1.setText(nameDescription2[1]);

            int thirdOrgId = sortedMatches.get(2).getKey();
            String[] nameDescription3 = generateNameDescription(thirdOrgId, sortedMatches.get(2).getValue(), 3);
            lblCharity1.setText(nameDescription3[0]);
            txtCharity1.setText(nameDescription3[1]);


        } else {
            lblCharity1.setText("No user answers found!");
            txtCharity1.setText("Please provide answers to match charities.");
            lblCharity2.setText("");
            txtCharity2.setText("");
            lblCharity3.setText("");
            txtCharity3.setText("");
        }
    }


    private String[] generateNameDescription(int orgId, int matches, int totalQuestions) {
        OrganisationDAO organisationDAO = new OrganisationDAO();

        String description = organisationDAO.getDescription(orgId);
        String name = organisationDAO.getName(orgId);

        double percentageMatch = ((double) matches / totalQuestions) * 100;

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


