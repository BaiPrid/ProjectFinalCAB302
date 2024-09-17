package com.example.finalassignmentcab302.Controllers;


import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class HelloController
{
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
    private Button registerButton; // Reference to the button in FXML

    @FXML
    private Button questNav;

    @FXML
    private Button HomeP;


    @FXML
    protected void handleOpenOrganisationRegistration() throws IOException {
        Stage stage = (Stage) questNav.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrganisationRegistrationPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void handleOpenHomePage() throws IOException {
        Stage stage = (Stage) HomeP.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CharitiesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void handleOpenUserRegistration() throws IOException {
        Stage stage = (Stage) questNav.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserRegistrationPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
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
    @FXML
    private TextField memberUsername;

    @FXML
    private PasswordField memberPassword;

    @FXML
    private TextField orgUsername;

    @FXML
    private PasswordField orgPassword;



    @FXML
    protected void handleMemberLogin() {
        String username = memberUsername.getText();
        String password = memberPassword.getText();
        UserDAO userDAO = new UserDAO();

        boolean login = userDAO.login(username, password);

        if ( login == true)
        {
            welcomeText.setText("SUCCESS!");
        }
        else
        {
            welcomeText.setText("Invalid Member Credentials");

        }


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