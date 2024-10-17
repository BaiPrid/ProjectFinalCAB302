package OntrollerTests;

import com.example.finalassignmentcab302.Controllers.OrganisationRegistrationController;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class MockOrganisationRegistrationController extends OrganisationRegistrationController {

    public TextField organisationName;
    public TextField supportedGroup;
    public TextArea organisationDescription;
    public TextField organisationUsername;
    public TextField organisationPassword;
    public TextField organisationEmail;

    private String mockAlertMessage;
    private OrganisationDAO organisationDAO;

    // Constructor to initialize the fields
    public MockOrganisationRegistrationController(TextField organisationName, TextField supportedGroup, TextArea organisationDescription,
                                                  TextField organisationUsername, TextField organisationPassword, TextField organisationEmail,
                                                  OrganisationDAO organisationDAO) {
        this.organisationName = organisationName;
        this.supportedGroup = supportedGroup;
        this.organisationDescription = organisationDescription;
        this.organisationUsername = organisationUsername;
        this.organisationPassword = organisationPassword;
        this.organisationEmail = organisationEmail;
        this.organisationDAO = organisationDAO;
    }

    @Override
    public void handleOrganisationButtonAction() {
        // Simulate field checks (e.g., if fields are empty)
        if (organisationName.getText().isEmpty() ||
                supportedGroup.getText().isEmpty() ||
                organisationDescription.getText().isEmpty() ||
                organisationUsername.getText().isEmpty() ||
                organisationPassword.getText().isEmpty() ||
                organisationEmail.getText().isEmpty()) {

            // Simulate alert message if any field is empty
            mockAlertMessage = "Mock alert: Fields are empty.";
        } else {
            // Simulate message for valid fields
            mockAlertMessage = "Fields are valid.";
        }
    }

    // Method to validate if the email is unique
    public boolean isEmailUnique(String email) {
        return !organisationDAO.CheckOrganisationEmail(email);
    }

    // Method to validate if the username is unique
    public boolean isUsernameUnique(String username) {
        return !organisationDAO.CheckOrganisationUserName(username);
    }

    // Method to validate if the organisation name is unique
    public boolean isOrganisationNameUnique(String organisationName) {
        return !organisationDAO.CheckOrganisationName(organisationName);
    }

    public String getMockAlertMessage() {
        return mockAlertMessage;
    }
}
