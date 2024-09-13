package com.example.finalassignmentcab302;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button testbutton;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private Button navigateButton; // Reference to the button in FXML

    @FXML
    private Button questNav;

    @FXML
    private void handleOpenOrganisationRegistration() throws IOException {
        Stage stage = (Stage) navigateButton.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.switchToOrganisationRegistrationPage(stage); // Switch to the new page
    }
    /* //THIS IS OLD AND DOESN'T WORK
    @FXML
    private void handleSwitchToQuestionPage() throws IOException {
        Stage stage2 = (Stage) questNav.getScene().getWindow(); // Get the current stage
        System.out.println("THIS GET'S ACTIVATED IN CONT1");
        HelloApplication app2 = new HelloApplication();
        System.out.println("THIS GET'S ACTIVATED IN CONT2");
        app2.switchToQuestionPage(stage2); // Switch to the new page
    }

     */

    @FXML
    protected void handleSwitchToQuestionPage() throws IOException {
        Stage stage = (Stage) questNav.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }



}