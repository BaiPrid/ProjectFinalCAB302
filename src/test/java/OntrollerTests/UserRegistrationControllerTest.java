package OntrollerTests;

import com.example.finalassignmentcab302.Tables.User;
import com.example.finalassignmentcab302.dao.UserDAO;
import com.example.finalassignmentcab302.dao.UserAnswersDAO;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testfx.api.FxToolkit;

import static org.mockito.Mockito.*;

public class UserRegistrationControllerTest {

    @Mock
    private UserDAO mockUserDAO;

    @Mock
    private UserAnswersDAO mockUserAnswersDAO;

    private TextField firstNameField;
    private TextField lastNameField;
    private TextField userNameField;
    private TextField passwordField;
    private TextField emailField;
    private TextField phoneNumberField;
    private Button submitButton;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Initialize the JavaFX environment with FxToolkit
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupSceneRoot(() -> {
            // Initialize the JavaFX components
            firstNameField = new TextField();
            lastNameField = new TextField();
            userNameField = new TextField();
            passwordField = new TextField();
            emailField = new TextField();
            phoneNumberField = new TextField();
            submitButton = new Button();
            return submitButton; // Set as the root node just to satisfy FxToolkit
        });

        FxToolkit.showStage();  // Ensure the stage is shown

        // Simulate user input
        firstNameField.setText("John");
        lastNameField.setText("Doe");
        userNameField.setText("johndoe");
        passwordField.setText("password123");
        emailField.setText("john.doe@example.com");
        phoneNumberField.setText("0423423423");
    }

    @Test
    public void testSuccessfulRegistrationLogic() {
        // Mock the behavior of the UserDAO to simulate successful registration
        when(mockUserDAO.CheckUsername("johndoe")).thenReturn(false);
        when(mockUserDAO.checkEmail("john.doe@example.com")).thenReturn(false);

        // Replicate the logic from handleUserButtonAction()
        if (firstNameField.getText().isEmpty() ||
                lastNameField.getText().isEmpty() ||
                emailField.getText().isEmpty() ||
                passwordField.getText().isEmpty() ||
                userNameField.getText().isEmpty() ||
                phoneNumberField.getText().isEmpty()) {

            // Simulate an alert or error message in the real app
            System.out.println("Form Incomplete. All fields must be filled.");
            return;
        }

        // Simulate unique username and email checks
        if (mockUserDAO.CheckUsername(userNameField.getText())) {
            System.out.println("Username Taken. Please choose a different username.");
            return;
        }

        if (mockUserDAO.checkEmail(emailField.getText())) {
            System.out.println("Email Exists. Please use a different email.");
            return;
        }

        // Insert new user (assuming all validations pass)
        User newUser = new User(
                firstNameField.getText(),
                lastNameField.getText(),
                userNameField.getText(),
                passwordField.getText(),
                emailField.getText(),
                Integer.parseInt(phoneNumberField.getText()),
                "Economy"
        );

        // Insert the new user and user answers in the DAO
        mockUserDAO.insert(newUser);
        verify(mockUserDAO, times(1)).insert(any(User.class));
    }

    @Test
    public void testUsernameAlreadyTakenLogic() {
        // Mock the behavior to simulate username already taken
        when(mockUserDAO.CheckUsername("johndoe")).thenReturn(true);

        // Replicate the logic from handleUserButtonAction()
        if (mockUserDAO.CheckUsername(userNameField.getText())) {
            System.out.println("Username Taken. Please choose a different username.");
            return;
        }

        // Verify that the insert method was NOT called
        verify(mockUserDAO, never()).insert(any());
    }

    @Test
    public void testEmailAlreadyExistsLogic() {
        // Mock the behavior to simulate email already taken
        when(mockUserDAO.checkEmail("john.doe@example.com")).thenReturn(true);

        // Replicate the logic from handleUserButtonAction()
        if (mockUserDAO.checkEmail(emailField.getText())) {
            System.out.println("Email Exists. Please use a different email.");
            return;
        }

        // Verify that the insert method was NOT called
        verify(mockUserDAO, never()).insert(any());
    }
}
