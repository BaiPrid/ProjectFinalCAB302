package com.example.finalassignmentcab302.Controllers;


import com.example.finalassignmentcab302.CurrentUserGLOBAL;
import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.Console;
import java.io.IOException;

public class UserLoginController
{

    @FXML
    public Text txtQuote;
    @FXML
    public Text txtTitle;
    @FXML
    private Label welcomeText;

    @FXML
    private Button questNav;
    @FXML
    private TextField memberUsername;
    @FXML
    private PasswordField memberPassword;

    @FXML
    private Button loginSend;
    @FXML
    private Button btnBack;


    /**
     * Sends user to the registration page where user may register and complete the questionnaire before
     * being able to log in.
     * @throws IOException if there is a problem loading the page.
     */
    @FXML
    protected void handleOpenUserRegistration() throws IOException {
        Stage stage = (Stage) questNav.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserRegistrationPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }


    /**
     * Verifies if the credentials the user is logging in with are correct or not.
     * Sends user to the accounts page if correct, otherwise output error line.
     * @throws IOException if there is a problem regarding the button this function
     * is linked to.
     */
    @FXML
    protected void handleMemberLogin() throws IOException {
        String username = memberUsername.getText();
        String password = memberPassword.getText();
        UserDAO userDAO = new UserDAO();

        boolean login = userDAO.login(username, password);

        if ( login == true)
        {
            welcomeText.setText("SUCCESS!");
            UserDAO currentUserDAO = new UserDAO();
            CurrentUserGLOBAL.currentUser = currentUserDAO.getUserID(username, password);
            loginSend();
        }
        else
        {
            welcomeText.setText("Invalid Member Credentials");
        }
    }

    /**
     * Sends the user to the user's account page. Should only be sent if login verification
     * is true.
     * @throws IOException if there is a problem loading the page.
     */
    @FXML
    protected void loginSend() throws IOException {
        Stage stage = (Stage) loginSend.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserAccounts.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    /**
     * Sends the user back to the landing page.
     * @throws IOException if there is a problem loading the page.
     */
    @FXML
    private void handleOpenHome() throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.start(stage); // Switch to the new page
    }








}