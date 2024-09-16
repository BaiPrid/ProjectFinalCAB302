package com.example.finalassignmentcab302;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

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
        OrganisationAnswersDAO organisationAnswersDAO = new OrganisationAnswersDAO();

        // Assuming you have the current user's ID somehow (e.g., from session or login)
        int userId = 1; // Replace with actual user ID
        UserAnswers userAnswers = userAnswersDAO.getUserAnswers(userId);

        if (userAnswers != null) {
            // Get matching organizations based on user answers
            List<OrganisationAnswers> matchingOrganisations = userAnswersDAO.getMatchingOrganisations(userAnswers);

            // Check if there are any matching organizations
            if (matchingOrganisations.size() > 0) {
                lblCharity1.setText(matchingOrganisations.get(0).getCategory());
                txtCharity1.setText(generateDescription(matchingOrganisations.get(0)));
            } else {
                lblCharity1.setText("Missing Charity!");
                txtCharity1.setText("Please wait for more charities to be added!");
            }

            if (matchingOrganisations.size() > 1) {
                lblCharity2.setText(matchingOrganisations.get(1).getCategory());
                txtCharity2.setText(generateDescription(matchingOrganisations.get(1)));
            } else {
                lblCharity2.setText("Missing Charity!");
                txtCharity2.setText("Please wait for more charities to be added!");
            }

            if (matchingOrganisations.size() > 2) {
                lblCharity3.setText(matchingOrganisations.get(2).getCategory());
                txtCharity3.setText(generateDescription(matchingOrganisations.get(2)));
            } else {
                lblCharity3.setText("Missing Charity!");
                txtCharity3.setText("Please wait for more charities to be added!");
            }
        } else {
            lblCharity1.setText("No user answers found!");
            txtCharity1.setText("Please provide answers to match charities.");
            lblCharity2.setText("");
            txtCharity2.setText("");
            lblCharity3.setText("");
            txtCharity3.setText("");
        }

        // Close DAO connections if needed
        userAnswersDAO.close();
        organisationAnswersDAO.close();
    }


    private String generateDescription(OrganisationAnswers organisation) {
        StringBuilder description = new StringBuilder();

        description.append("Category: ").append(organisation.getCategory()).append("\n");
        description.append("Size: ").append(organisation.getSize()).append("\n");
        description.append("Donation Options: ").append(organisation.getDonationOptions()).append("\n");
        description.append("Taxable Category: ").append(organisation.getTaxableCategory()).append("\n");

        if (organisation.getDonorSpecifies()) {
            description.append("Donor can specify how donations are used.");
        } else {
            description.append("Donor cannot specify how donations are used.");
        }

        return description.toString();
    }



    @FXML
    private void handleOpenHome() throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.start(stage); // Switch to the new page
    }

}


