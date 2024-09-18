package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;


public class OrgloginController
{
    @FXML
    private Label welcomeText;
    @FXML
    private TextField orgUsername;

    @FXML
    private PasswordField orgPassword;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private Button questNav;


    @FXML
    protected void handleOpenOrganisationRegistration() throws IOException {
        Stage stage = (Stage) questNav.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrganisationRegistrationPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }


    @FXML
    protected void handleOrgLogin() {
        String username = orgUsername.getText();
        String password = orgPassword.getText();

        // Add your login logic here
        if (username.equals("org") && password.equals("password")) {
            welcomeText.setText("Welcome, Organization!");
        } else {
            welcomeText.setText("Invalid Organization Credentials");
        }
    }

    @FXML
    private Button btnLogout;

    @FXML
    private void handleOpenHome() throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.start(stage); // Switch to the new page
    }


}
