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

public class OrganisationRegistrationController {

    private Stage primaryStage; // Add a Stage field
    private String imagePath;


    //Task Bar Buttons
    @FXML
    private Button Charities;
    @FXML
    private Button Favourites;
    @FXML
    private Button Login_Page;
    @FXML
    private Button Roulette;
    @FXML
    private Button Home;

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
    @FXML
    private TextField OrganisationEmail;

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

            try {

                File imageFolder = new File("C:\\Users\\toxic\\IdeaProjects\\ProjectFinalCAB302\\src\\main\\resources\\images");


                File imageFile = new File(imageFolder, file.getName());


                Files.copy(file.toPath(), imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                imagePath = imageFile.getAbsolutePath();


            } catch (IOException e) {
                e.printStackTrace();
            }

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
    private void handleOrganisationButtonAction() throws IOException {


        if (OrganisationName.getText().isEmpty() ||
                SupportedGroup.getText().isEmpty() ||
                OrganisationDescription.getText().isEmpty() ||
                OrganisationUsername.getText().isEmpty() ||
                OrganisationPassword.getText().isEmpty() ||
                OrganisationEmail.getText().isEmpty() ||
                CategorySupportedGroup.getValue() == null ||
                CategoryOfOrganisation.getValue() == null ||
                SizeOfOrganisation.getValue() == null ||
                group.getSelectedToggle() == null ||
                group2.getSelectedToggle() == null) {

            // If any field is empty, show an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Form Incomplete");
            alert.setHeaderText("Please fill in all required fields.");
            alert.setContentText("Ensure all fields are filled before submitting.");
            alert.showAndWait();
            return; // Prevent form submission
        }

        // Read text fields
        String organisationName = OrganisationName.getText();
        String supportedGroup = SupportedGroup.getText();
        String organisationDescription = OrganisationDescription.getText();


        List<String> donationOptions = new ArrayList<>();
        if (five.isSelected()) donationOptions.add("5, ");
        if (ten.isSelected()) donationOptions.add("10, ");
        if (fifteen.isSelected()) donationOptions.add("15, ");
        if (twenty.isSelected()) donationOptions.add("20, ");
        if (fifty.isSelected()) donationOptions.add("50, ");
        if (hundred.isSelected()) donationOptions.add("100, ");
        if (twohundred.isSelected()) donationOptions.add("200, ");
        String donationOptionsBuild = String.join(", ", donationOptions);

        //login details
        String organisationUsername = OrganisationUsername.getText();
        String organisationPassword = OrganisationPassword.getText();
        String organisationEmail = OrganisationEmail.getText();


        // Read combo boxes
        String categorySupportedGroup = CategorySupportedGroup.getValue();
        String categoryOfOrganisation = CategoryOfOrganisation.getValue();
        String sizeOfOrganisation = SizeOfOrganisation.getValue();

        // Read radio button selections
        String selectedRadioGroup1 = ((RadioButton) group.getSelectedToggle()).getText();
        Boolean selectedRadioGroup2 = ((RadioButton) group2.getSelectedToggle()).isSelected();

        // Read checkboxes
        List<String> donationTypes = new ArrayList<>();
        if (monetaryDonationCheckBox.isSelected()) donationTypes.add("monetaryDonation, ");
        if (volunteerWorkCheckBox.isSelected()) donationTypes.add("volunteerWork, ");
        if (payedEmployeesCheckBox.isSelected()) donationTypes.add("payedEmployees, ");
        if (hiredCorporationsCheckBox.isSelected()) donationTypes.add("HiredParty, ");
        String donationTypesBuild = String.join(", ", donationTypes);


        Organisation organisation = new Organisation(organisationName, categorySupportedGroup, organisationDescription, imagePath, organisationEmail, organisationUsername, organisationPassword);
        OrganisationAnswers organisationAnswers = new OrganisationAnswers(categoryOfOrganisation, sizeOfOrganisation, donationTypesBuild, selectedRadioGroup1, selectedRadioGroup2);
        OrganisationAnswersDAO dao = new OrganisationAnswersDAO();
        dao.insert(organisationAnswers);
        OrganisationDAO organisationDAO = new OrganisationDAO();
        organisationDAO.insert(organisation);
        handleLoginPage();


    }




    @FXML
    private void handleLoginPage() throws IOException {
        Stage stage = (Stage) SubmitRegistration.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    private void handleHomePage() throws IOException {
        Stage stage = (Stage) Home.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    private void handleCharitiesPage() throws IOException {
        Stage stage = (Stage) Charities.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CharitiesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }







}