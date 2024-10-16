package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.Tables.Order;
import com.example.finalassignmentcab302.dao.OrderDAO;
import com.example.finalassignmentcab302.dao.OrganisationAnswersDAO;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import com.example.finalassignmentcab302.dao.UserAnswersDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.example.finalassignmentcab302.Controllers.CharitiesPageController.selectedCharityName;
import static com.example.finalassignmentcab302.CurrentUserGLOBAL.currentUser;

/**
 * Controller for the Donate Page of the application, which manages the donation process of each organisation in the database.
 */
public class DonatePageController {

    private String organisationNameforInfo;

    @FXML
    int id;
    @FXML
    int Orgid;
    @FXML
    String groupSupported;
    @FXML
    String taxableCategory;
    @FXML
    String size;

    @FXML
    private Label lblCharityName;

    @FXML
    private Label txtCharityDescription;

    @FXML
    private Label CharityGroupSupported;

    @FXML
    private Label TaxableCategory;


    @FXML
    private Label CharitySize;

    private ToggleGroup group;

    @FXML
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;
    @FXML
    private RadioButton radioButton3;
    @FXML
    private RadioButton radioButton4;
    @FXML
    private RadioButton radioButton5;
    @FXML
    private RadioButton radioButton6;

    @FXML
    private TextField BillingAdress;



    @FXML
    private Button Donate;


    OrganisationDAO organisationDAO = new OrganisationDAO();
    OrganisationAnswersDAO organisationAnswersDAO = new OrganisationAnswersDAO();
    OrderDAO orderDAO = new OrderDAO();


    /**
     * Initializes the controller, sets charity info and radio buttons.
     */
    @FXML
    private void initialize() {
        setCharityInfo(selectedCharityName);
        setupradiobuttons();
    }

    /**
     * Sets up the radio buttons for donation amounts.
     */
    @FXML
    public void setupradiobuttons() {
        // Create a ToggleGroup and add the radio buttons to it
        group = new ToggleGroup();
        radioButton1.setToggleGroup(group);
        radioButton2.setToggleGroup(group);
        radioButton3.setToggleGroup(group);
        radioButton4.setToggleGroup(group);
        radioButton5.setToggleGroup(group);
        radioButton6.setToggleGroup(group);

        radioButton1.setUserData(10);
        radioButton2.setUserData(25);
        radioButton3.setUserData(50);
        radioButton4.setUserData(100);
        radioButton5.setUserData(200);
        radioButton6.setUserData(500);

    }

    /**
     * Sets charity information based on the provided charity name.
     * @param charityName The name of the charity to retrieve information for.
     */
    public void setCharityInfo(String charityName) {

        List<Object> organisationDetails = organisationDAO.getByName(charityName);

        id = (Integer) organisationDetails.get(0);
        String name = (String) organisationDetails.get(1);
        String description = (String) organisationDetails.get(2);
        String imgPath = (String) organisationDetails.get(3);
        String email = (String) organisationDetails.get(4);
        groupSupported = (String) organisationDetails.get(5);


        List<Object> organisationAnswersDetails = organisationAnswersDAO.getByid(id);

        int organisationId = (Integer) organisationAnswersDetails.get(0);
        String category = (String) organisationAnswersDetails.get(1);
        size = (String) organisationAnswersDetails.get(2);
        String donationOptions = (String) organisationAnswersDetails.get(3);
        taxableCategory = (String) organisationAnswersDetails.get(4);
        lblCharityName.setText(name);
        txtCharityDescription.setText(description);
        CharityGroupSupported.setText(groupSupported);
        TaxableCategory.setText(taxableCategory);
        CharitySize.setText(size);

    }

    /**
     * Handles the action of pressing the Donate button.
     * If donation not valid create an alert on the interface.
     * Creates and order if Donation is valid.
     */
    @FXML
    private void OnDonatePress() {
        if (BillingAdress.getText().isEmpty() ||
                group.getSelectedToggle() == null) {

            // If any field is empty, show an alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Form Incomplete");
            alert.setHeaderText("Please fill in all required fields.");
            alert.setContentText("Ensure all fields are filled before submitting.");
            alert.showAndWait();
            return; // Prevent form submission
        }


        LocalDateTime orderDateTime = LocalDateTime.now();
        String DateTimeasString = orderDateTime.toString();
        int selectedValue = getSelectedRadioButtonValue();
        float selectedValueFloat = (float) selectedValue;
        String billingaddress = BillingAdress.getText();


        Order order = new Order(id, Orgid, DateTimeasString, selectedValueFloat, billingaddress);
        orderDAO.insert(order);


        try {
            handleHomeSend();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the value of the selected radio button for donation amount.
     * @return The donation amount associated with the selected radio button.
     */
    public int getSelectedRadioButtonValue() {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        // Retrieve the integer value assigned to the selected RadioButton
        return (int) selectedRadioButton.getUserData();
    }

    /**
     * Handles the transition to the home page after a successful donation.
     * @throws IOException If an error occurs while loading the home page.
     */
    private void handleHomeSend() throws IOException {
        Stage stage = (Stage) Donate.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserAccounts.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }




}