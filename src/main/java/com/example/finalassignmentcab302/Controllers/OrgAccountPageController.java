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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.Tables.Order;
import com.example.finalassignmentcab302.Tables.User;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.finalassignmentcab302.dao.UserDAO;
import com.example.finalassignmentcab302.dao.OrderDAO;

import static com.example.finalassignmentcab302.CurrentUserGLOBAL.currentUser;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;



public class OrgAccountPageController {

    private Stage primaryStage; // Add a Stage field
    //checkboxes to select default donation amounts
    @FXML
    private CheckBox five;
    @FXML
    private CheckBox ten;
    @FXML
    private CheckBox fifteen;
    @FXML
    private CheckBox twenty;
    @FXML
    private CheckBox fifty;
    @FXML
    private CheckBox hundred;
    @FXML
    private CheckBox twohundred;

    //Organisation Login details
    @FXML
    private TextField OrganisationUsername;
    @FXML
    private TextField OrganisationPassword;
    @FXML
    private TextField OrganisationEmail;


    //right side form
    //Category selections
    @FXML
    private ComboBox<String> CategorySupportedGroup;
    @FXML
    private ComboBox<String> CategoryOfOrganisation;
    @FXML
    private ComboBox<String> SizeOfOrganisation;

    //checkboxes for charitable processes of org
    @FXML
    private CheckBox monetaryDonationCheckBox;
    @FXML
    private CheckBox volunteerWorkCheckBox;
    @FXML
    private CheckBox payedEmployeesCheckBox;
    @FXML
    private CheckBox hiredCorporationsCheckBox;

    //2 radiobutton groups
    @FXML
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;

    @FXML
    private RadioButton radioButton3;
    @FXML
    private RadioButton radioButton4;

    private ToggleGroup group;
    private ToggleGroup group2;

    // submit button
    @FXML
    private Button SubmitRegistration;

    @FXML
    private Button btnDonate;
    @FXML
    private Button btnBack;



    /**
     * Sets the primary stage.
     * @param primaryStage the primary stage to set
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    /**
     * Initializes the controller and sets up the radio buttons.
     */
    @FXML
    public void initialize() {
        setupradiobuttons();
    }

    /**
     * Sets up the radio buttons by creating toggle groups and adding
     * the radio buttons to them.
     */
    @FXML
    public void setupradiobuttons() {
        // Create a ToggleGroup and add the radio buttons to it
        group = new ToggleGroup();
        radioButton1.setToggleGroup(group);
        radioButton2.setToggleGroup(group);

        group2 = new ToggleGroup();
        radioButton3.setToggleGroup(group2);
        radioButton4.setToggleGroup(group2);

    }

    /**
     * Sends user to the home page.
     * @throws IOException if there is a problem loading the page.
     */
    @FXML
    private void handleOpenHome() throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.start(stage); // Switch to the new page
    }


/*
    // Read combo boxes
    String categorySupportedGroup = CategorySupportedGroup.getValue();
    String categoryOfOrganisation = CategoryOfOrganisation.getValue();
    String sizeOfOrganisation = SizeOfOrganisation.getValue();


*/






}
