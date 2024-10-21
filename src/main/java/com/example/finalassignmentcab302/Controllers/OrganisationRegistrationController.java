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



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class responsible for handling the registration of organisations.
 * This class manages the user interface for registering a new organisation,
 * including collecting input data, validating the data, and saving the
 * organisation information to the database via DAO methods.
 */
public class OrganisationRegistrationController {

    private Stage primaryStage; // Add a Stage field
    private String imagePath;


    //left side form buttons
    //first 3 text entry areas
    @FXML
    private Text txtTitle;
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

    /**
     * Sets the primary stage.
     * @param primaryStage the primary stage to set
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Opens a file chooser to select an image file and copies it to
     * the designated image folder.
     */
    public void openImageFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {

            try {

                File imageFolder = new File("src/main/resources/images");


                File imageFile = new File(imageFolder, file.getName());


                Files.copy(file.toPath(), imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                imagePath = imageFile.getAbsolutePath();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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
     * Handles the organisation registration form submission. Validates the
     * input fields, checks for unique usernames and emails, and
     * inserts the organisation and its answers into the database.
     * If inputs don't pass validations alert is created to inform user of issue.
     * @throws IOException if there is an error loading the login page
     */
    @FXML
    public void handleOrganisationButtonAction() throws IOException {


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
        if (monetaryDonationCheckBox.isSelected()) donationTypes.add("Monetary Donation");
        if (volunteerWorkCheckBox.isSelected()) donationTypes.add("Volunteer Work");
        if (payedEmployeesCheckBox.isSelected()) donationTypes.add("Payed Employees");
        if (hiredCorporationsCheckBox.isSelected()) donationTypes.add("Hired Party");
        String donationTypesBuild = String.join(",", donationTypes);


        OrganisationDAO organisationDAO = new OrganisationDAO();

        if (organisationDAO.CheckOrganisationUserName(organisationUsername)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Organisation username Taken");
            alert.setHeaderText("This organisation username is already taken.");
            alert.setContentText("Please choose a different organisation username.");
            alert.showAndWait();
            return;
        }

        if (organisationDAO.CheckOrganisationEmail(organisationEmail)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Email Exists");
            alert.setHeaderText("This email is already associated with an organisation account.");
            alert.setContentText("Please choose a different email or try logging in with the existing email.");
            alert.showAndWait();
            return;
        }

        if (organisationDAO.CheckOrganisationName(organisationName)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Organisation name Taken");
            alert.setHeaderText("This organisation name is already taken.");
            alert.setContentText("Please choose a different organisation name.");
            alert.showAndWait();
            return;
        }

        Organisation organisation = new Organisation(organisationName, categorySupportedGroup, organisationDescription, imagePath, organisationEmail, organisationUsername, organisationPassword);
        OrganisationAnswers organisationAnswers = new OrganisationAnswers(categorySupportedGroup, sizeOfOrganisation, donationTypesBuild, selectedRadioGroup1, selectedRadioGroup2);
        OrganisationAnswersDAO dao = new OrganisationAnswersDAO();
        dao.insert(organisationAnswers);
        organisationDAO.insert(organisation);
        handleLoginPage();
    }


    /**
     * Navigates to the login page after successful registration.
     * @throws IOException if there is an error loading the login page
     */
    @FXML
    private void handleLoginPage() throws IOException {
        Stage stage = (Stage) SubmitRegistration.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrgLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }


}