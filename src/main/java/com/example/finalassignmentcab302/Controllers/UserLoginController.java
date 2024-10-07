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


// Registration Button for User Login Page Links to login page
    @FXML
    protected void handleOpenUserRegistration() throws IOException {
        Stage stage = (Stage) questNav.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserRegistrationPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }



    @FXML
    protected void handleMemberLogin() throws IOException {
        String username = memberUsername.getText();
        String password = memberPassword.getText();
        UserDAO userDAO = new UserDAO();

        boolean login = userDAO.login(username, password);

        if ( login == true)
        {
            welcomeText.setText("SUCCESS!");
            //////////////////////////////////NEW SECTION///////////////////////////////////
            UserDAO currentUserDAO = new UserDAO();
            CurrentUserGLOBAL.currentUser = currentUserDAO.getUserID(username, password);
            //System.out.println(CurrentUserGLOBAL.currentUser); <---- For testing
            loginSend();
        }
        else
        {
            welcomeText.setText("Invalid Member Credentials");

        }


    }

    @FXML
    protected void loginSend() throws IOException {
        Stage stage = (Stage) loginSend.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserAccounts.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }



    @FXML
    private Button btnLogout;

    @FXML
    private void handleOpenHome() throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.start(stage); // Switch to the new page
    }

   /* @FXML
    private TextField memberUsername;

    @FXML
    private PasswordField memberPassword;

    @FXML
    private TextField orgUsername;

    @FXML
    private PasswordField orgPassword;

    @FXML
    private Label welcomeText;*/

    /*@FXML
    protected void handleMemberLogin() {
        String username = memberUsername.getText();
        String password = memberPassword.getText();

        // Add your login logic here
        if (username.equals("member") && password.equals("password")) {
            welcomeText.setText("Welcome, Member!");
        } else {
            welcomeText.setText("Invalid Member Credentials");
        }
    }*/

   /* @FXML
    protected void handleOrgLogin() {
        String username = orgUsername.getText();
        String password = orgPassword.getText();

        // Add your login logic here
        if (username.equals("org") && password.equals("password")) {
            welcomeText.setText("Welcome, Organization!");
        } else {
            welcomeText.setText("Invalid Organization Credentials");
        }
    }*/

  /*  @FXML
    protected void handleButton1() {
        // Add your logic for Button 1 here
        welcomeText.setText("Button 1 clicked");
    }

    @FXML
    protected void handleButton2() {
        // Add your logic for Button 2 here
        welcomeText.setText("Button 2 clicked");
    }

    @FXML
    protected void handleButton3() {
        // Add your logic for Button 3 here
        welcomeText.setText("Button 3 clicked");
    }*/





}