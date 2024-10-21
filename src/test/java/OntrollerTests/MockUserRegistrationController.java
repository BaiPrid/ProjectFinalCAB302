package OntrollerTests;

import com.example.finalassignmentcab302.Controllers.UserRegistrationController;
import com.example.finalassignmentcab302.dao.UserDAO;

public class MockUserRegistrationController extends UserRegistrationController {

    public String firstName;
    public String lastName;
    public String userName;
    public String passWord;
    public String email;
    public String phoneNumber;
    public String economicClass;

    private String mockAlertMessage;
    private UserDAO userDAO;

    // Constructor to initialize the fields
    public MockUserRegistrationController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setFieldsForTesting(String firstName, String lastName, String userName, String passWord, String email, String phoneNumber, String economicClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.economicClass = economicClass;
    }

    @Override
    public void handleUserButtonAction() {
        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() ||
                passWord.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() ||
                economicClass == null) {
            // Simulate alert message if any field is empty
            mockAlertMessage = "Mock alert: Fields are empty.";
        } else if (!isPhoneNumberValid(phoneNumber)) {
            // Simulate alert message if phone number is invalid
            mockAlertMessage = "Mock alert: Invalid phone number.";
        } else if (!isUsernameUnique(userName)) {
            // Simulate alert message if username is not unique
            mockAlertMessage = "Mock alert: Username is taken.";
        } else if (!isEmailUnique(email)) {
            // Simulate alert message if email is not unique
            mockAlertMessage = "Mock alert: Email already exists.";
        } else {
            // Simulate message for valid fields
            mockAlertMessage = "Fields are valid.";
        }
    }

    // Method to validate if the phone number is valid
    public boolean isPhoneNumberValid(String phoneNumber) {
        try {
            Integer.parseInt(phoneNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Method to validate if the username is unique
    public boolean isUsernameUnique(String username) {
        return !userDAO.CheckUsername(username);
    }

    // Method to validate if the email is unique
    public boolean isEmailUnique(String email) {
        return !userDAO.checkEmail(email);
    }

    public String getMockAlertMessage() {
        return mockAlertMessage;
    }
}
