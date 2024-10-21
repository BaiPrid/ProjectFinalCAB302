package OntrollerTests;

import com.example.finalassignmentcab302.Controllers.OrganisationRegistrationController;
import com.example.finalassignmentcab302.dao.OrganisationDAO;

public class MockOrganisationRegistrationController extends OrganisationRegistrationController {

    public String organisationName;
    public String supportedGroup;
    public String organisationDescription;
    public String organisationUsername;
    public String organisationPassword;
    public String organisationEmail;

    private String mockAlertMessage;
    private OrganisationDAO organisationDAO;

    // Constructor to initialize the fields
    public MockOrganisationRegistrationController(OrganisationDAO organisationDAO) {
        this.organisationDAO = organisationDAO;
    }

    // Method to set fields for testing
    public void setFieldsForTesting(String organisationName, String supportedGroup, String organisationDescription,
                                    String organisationUsername, String organisationPassword, String organisationEmail) {
        this.organisationName = organisationName;
        this.supportedGroup = supportedGroup;
        this.organisationDescription = organisationDescription;
        this.organisationUsername = organisationUsername;
        this.organisationPassword = organisationPassword;
        this.organisationEmail = organisationEmail;
    }

    @Override
    public void handleOrganisationButtonAction() {
        if (organisationName.isEmpty() || supportedGroup.isEmpty() || organisationDescription.isEmpty() ||
                organisationUsername.isEmpty() || organisationPassword.isEmpty() || organisationEmail.isEmpty()) {
            // Simulate alert message if any field is empty
            mockAlertMessage = "Mock alert: Fields are empty.";
        } else if (!isUsernameUnique(organisationUsername)) {
            // Simulate alert message if username is not unique
            mockAlertMessage = "Mock alert: Username is taken.";
        } else if (!isEmailUnique(organisationEmail)) {
            // Simulate alert message if email is not unique
            mockAlertMessage = "Mock alert: Email already exists.";
        } else if (!isOrganisationNameUnique(organisationName)) {
            // Simulate alert message if organisation name is not unique
            mockAlertMessage = "Mock alert: Organisation name is taken.";
        } else {
            // Simulate message for valid fields
            mockAlertMessage = "Fields are valid.";
        }
    }

    // Method to validate if the username is unique
    public boolean isUsernameUnique(String username) {
        return !organisationDAO.CheckOrganisationUserName(username);
    }

    // Method to validate if the email is unique
    public boolean isEmailUnique(String email) {
        return !organisationDAO.CheckOrganisationEmail(email);
    }

    // Method to validate if the organisation name is unique
    public boolean isOrganisationNameUnique(String organisationName) {
        return !organisationDAO.CheckOrganisationName(organisationName);
    }

    public String getMockAlertMessage() {
        return mockAlertMessage;
    }
}
