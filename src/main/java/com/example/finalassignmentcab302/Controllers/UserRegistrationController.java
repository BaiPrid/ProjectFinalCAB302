package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.CurrentUserGLOBAL;
import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.Tables.Organisation;
import com.example.finalassignmentcab302.Tables.OrganisationAnswers;
import com.example.finalassignmentcab302.Tables.User;
import com.example.finalassignmentcab302.Tables.UserAnswers;
import com.example.finalassignmentcab302.dao.OrganisationAnswersDAO;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import com.example.finalassignmentcab302.dao.UserAnswersDAO;
import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class UserRegistrationController {

    private Stage primaryStage;


    @FXML
    private Text txtTitle;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;


    @FXML
    private TextField UserName;
    @FXML
    private TextField PassWord;

    @FXML
    private TextField Email;
    @FXML
    private TextField PhoneNumber;




    @FXML
    private ComboBox<String> EconomicClass;




    // submit button
    @FXML
    private Button SubmitUserRegistration;


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    @FXML
    public void initialize() {

    }


    @FXML
    private void handleUserButtonAction() throws IOException {


        if (FirstName.getText().isEmpty() ||
                LastName.getText().isEmpty() ||
                Email.getText().isEmpty() ||
                PassWord.getText().isEmpty() ||
                UserName.getText().isEmpty() ||
                PhoneNumber.getText().isEmpty() ||
                EconomicClass.getValue() == null)
                 {

            // If any field is empty, show an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Form Incomplete");
            alert.setHeaderText("Please fill in all required fields.");
            alert.setContentText("Ensure all fields are filled before submitting.");
            alert.showAndWait();
            return; // Prevent form submission
        }



        // Read text fields
        String firstName = FirstName.getText();
        String lastName = LastName.getText();
        String email = Email.getText();
        String phoneNumber = PhoneNumber.getText();
        String userName = UserName.getText();
        String password = PassWord.getText();
        Integer realphonenumber;

        // Read combo boxes
        String economicClass = EconomicClass.getValue();

        try {
            realphonenumber = Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Phone Number Error");
            alert.setContentText("Please enter a valid phone number (numeric only).");
            alert.showAndWait();
            return;
        }
        //check phone number entered is an integer


        //ensures that the username is unique as can't have multiple users with same username
        UserDAO userdao = new UserDAO();
        if (userdao.CheckUsername(userName)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Username Taken");
            alert.setHeaderText("This username is already taken.");
            alert.setContentText("Please choose a different username.");
            alert.showAndWait();
            return;
        }

        if (userdao.checkEmail(email)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Email Exists");
            alert.setHeaderText("This Email is Already Associated with an Account.");
            alert.setContentText("Please choose a different Email or Try login with existing Email.");
            alert.showAndWait();
            return;
        }


        User newuser = new User(firstName, lastName, userName, password, email, realphonenumber, economicClass);
        userdao.insert(newuser);
        //////////////NEW SECTION/////////////
        //This retrieves the user id before sending them to the questions page
        //handleLoginPage(); <--- Old
        //User currentUser = new User(userName, password);
        UserDAO currentUserDAO = new UserDAO();
        CurrentUserGLOBAL.currentUser = currentUserDAO.getUserID(userName, password);
        //System.out.println(CurrentUserGLOBAL.currentUser); <--- for testing

        UserAnswersDAO userAnswersDAO = new UserAnswersDAO();
        UserAnswers userAnswers = new UserAnswers(CurrentUserGLOBAL.currentUser, "Not Needed Yet", "Not Needed Yet", "Not Needed Yet", "Not Needed Yet", true, "TempAns", "TempAns", "TempAns");
        userAnswersDAO.insert(userAnswers);

        handleQuestionPage();

    }

    private void handleQuestionPage() throws IOException {
        Stage stage = (Stage) SubmitUserRegistration.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("questionsSS.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    /* Was this old code for button?
    private void handleLoginPage() throws IOException {
        Stage stage = (Stage) SubmitUserRegistration.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

     */
    @FXML
    private Button btnLogout;


    @FXML
    protected void handleOpenUserRegistration() throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }


}