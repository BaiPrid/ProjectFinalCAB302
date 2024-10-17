package OntrollerTests;

import com.example.finalassignmentcab302.Controllers.UserRegistrationController;
import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class MockUserRegistrationController extends UserRegistrationController {

    public TextField firstName;
    public TextField lastName;
    public TextField userName;
    public TextField passWord;
    public TextField email;
    public TextField phoneNumber;
    public ComboBox<String> economicClass;

    private String mockAlertMessage;
    private UserDAO userDAO;

    // Constructor to initialize the fields
    public MockUserRegistrationController(TextField firstName, TextField lastName, TextField userName, TextField passWord,
                                          TextField email, TextField phoneNumber, ComboBox<String> economicClass,
                                          UserDAO userDAO) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.economicClass = economicClass;
        this.userDAO = userDAO;
    }

    @Override
    public void handleUserButtonAction() {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || userName.getText().isEmpty() ||
                passWord.getText().isEmpty() || email.getText().isEmpty() || phoneNumber.getText().isEmpty() ||
                economicClass.getValue() == null) {
            // Simulate alert message if any field is empty
            mockAlertMessage = "Mock alert: Fields are empty.";
        } else if (!isPhoneNumberValid(phoneNumber.getText())) {
            // Simulate alert message if phone number is invalid
            mockAlertMessage = "Mock alert: Invalid phone number.";
        } else if (!isUsernameUnique(userName.getText())) {
            // Simulate alert message if username is not unique
            mockAlertMessage = "Mock alert: Username is taken.";
        } else if (!isEmailUnique(email.getText())) {
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
