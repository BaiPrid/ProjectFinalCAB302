package com.example.finalassignmentcab302;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

public class OrganisationRegistrationController {

    private Stage primaryStage; // Add a Stage field


    //Task Bar Buttons
    @FXML
    private Button Account;
    @FXML
    private Button Favourites;
    @FXML
    private Button Login_Page;
    @FXML
    private Button Roulette;

    //left side form buttons
    //first 3 text entry areas
    @FXML
    private TextField OrganisationName;
    @FXML
    private TextField SupportedGroup;
    @FXML
    private TextArea OrganisationDescription;

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

    //organisation image
    @FXML
    private Button ImageSelect;

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

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void openImageFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {

        }
    }

    @FXML
    public void initialize() {
        setupradiobuttons();

    }
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


    @FXML
    private void handleOpenCharities() throws IOException {
        Stage stage = (Stage) Account.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.switchToCharitiesPage(stage); // Switch to the new page
    }
}