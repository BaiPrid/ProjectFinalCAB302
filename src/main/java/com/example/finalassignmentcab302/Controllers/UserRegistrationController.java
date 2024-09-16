package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.Tables.Organisation;
import com.example.finalassignmentcab302.Tables.OrganisationAnswers;
import com.example.finalassignmentcab302.Tables.User;
import com.example.finalassignmentcab302.dao.OrganisationAnswersDAO;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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


    //Task Bar Buttons
    @FXML
    private Button Account;
    @FXML
    private Button Favourites;
    @FXML
    private Button Login_Page;
    @FXML
    private Button Roulette;


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

        // Read combo boxes
        String economicClass = EconomicClass.getValue();


        User newuser = new User(firstName, lastName, userName, password, email, phoneNumber, economicClass);
        UserDAO userdao = new UserDAO();
        userdao.insert(newuser);
        handleLoginPage();

    }

    private void handleLoginPage() throws IOException {
        Stage stage = (Stage) SubmitUserRegistration.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

}