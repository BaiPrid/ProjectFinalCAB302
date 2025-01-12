package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
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

import static com.example.finalassignmentcab302.CurrentUserGLOBAL.currentUser;

/**
 * Controller class for the CharitiesPage view.
 * This class handles the logic for matching charities to users based on their answers,
 * displaying charity names and descriptions, and navigating to the donation page.
 */
public class CharitiesPageController {

    /** Stores the name of the selected charity. */
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

    /**
     * Initializes the controller by fetching user answers and matching them to charities.
     * Populates the charity information in the UI based on the user's answers.
     */
    @FXML
    private void initialize() {
        UserAnswersDAO userAnswersDAO = new UserAnswersDAO();

        int userId = currentUser;
        // Gets the answers for the specific user id
        List<String> userAnswers = userAnswersDAO.getUserAnswers(userId);

        if (userAnswers != null) {
            // Get matching organizations based on user answers, returns as a hashmap of organisation id and the amount of matching answers
            Map<Integer, Integer> matchingOrganisations = userAnswersDAO.getMatchingOrganisations(userAnswers);

            // Apply some messy hashmap manipulation to sort it into descending order based on the values.
            List<Map.Entry<Integer, Integer>> sortedMatches = matchingOrganisations.entrySet().stream().sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())).toList();

            // Number of questions
            int totalQuestions = 5;

            // Number one spot ----------------------------------------------------------------------------------------------
            int firstOrgId = sortedMatches.get(0).getKey();
            nameDescription1 = generateNameDescription(firstOrgId, sortedMatches.get(0).getValue(), totalQuestions);
            lblCharity1.setText(nameDescription1[0]);
            txtCharity1.setText(nameDescription1[1]);


            // Number two spot ----------------------------------------------------------------------------------------------
            int secondOrgId = sortedMatches.get(1).getKey();
            nameDescription2 = generateNameDescription(secondOrgId, sortedMatches.get(1).getValue(), totalQuestions);
            lblCharity2.setText(nameDescription2[0]);
            txtCharity2.setText(nameDescription2[1]);

            // Number three spot --------------------------------------------------------------------------------------------
            int thirdOrgId = sortedMatches.get(2).getKey();
            nameDescription3 = generateNameDescription(thirdOrgId, sortedMatches.get(2).getValue(), totalQuestions);
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


    /**
     * Generates the name and description of a charity based on its organisation ID.
     *
     * @param orgId The organisation ID.
     * @param matches The number of matching answers between the user and the organisation.
     * @param totalQuestions The total number of questions answered by the user.
     * @return A String array containing the organisation name and description with the percentage match.
     */
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

    /**
     * Sets the selected charity name to the first charity's name.
     */
    @FXML
    private void setCharityNamefor1(){
        selectedCharityName = nameDescription1[0];
    }

    /**
     * Sets the selected charity name to the second charity's name.
     */
    @FXML
    private void setCharityNamefor2(){
        selectedCharityName = nameDescription2[0];
    }

    /**
     * Sets the selected charity name to the third charity's name.
     */
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

    /**
     * Navigates the user to the donation page for the first charity.
     *
     * @throws IOException If there is a problem loading the donation page.
     */
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

    /**
     * Navigates the user to the donation page for the second charity.
     *
     * @throws IOException If there is a problem loading the donation page.
     */
    @FXML
    private void handleDonationPagefor2() throws IOException {
        setCharityNamefor2();
        Stage stage = (Stage) btnCharity2.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Donate Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    /**
     * Navigates the user to the donation page for the third charity.
     *
     * @throws IOException If there is a problem loading the donation page.
     */
    @FXML
    private void handleDonationPagefor3() throws IOException {
        setCharityNamefor3();
        Stage stage = (Stage) btnCharity3.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Donate Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }
}