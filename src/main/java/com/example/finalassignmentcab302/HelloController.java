package com.example.finalassignmentcab302;

import javafx.fxml.FXML;
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
    private void handleOpenOrganisationRegistration() throws IOException {
        Stage stage = (Stage) navigateButton.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.switchToOrganisationRegistrationPage(stage); // Switch to the new page
    }
}