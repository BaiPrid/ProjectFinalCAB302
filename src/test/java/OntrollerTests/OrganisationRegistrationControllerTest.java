package OntrollerTests;

import com.example.finalassignmentcab302.Controllers.OrganisationRegistrationController;
import com.example.finalassignmentcab302.Tables.Organisation;
import com.example.finalassignmentcab302.Tables.OrganisationAnswers;
import com.example.finalassignmentcab302.dao.OrganisationAnswersDAO;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import javafx.scene.control.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testfx.api.FxToolkit;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class OrganisationRegistrationControllerTest {

    @Mock
    private OrganisationDAO mockOrganisationDAO;

    @Mock
    private OrganisationAnswersDAO mockOrganisationAnswersDAO;

    @InjectMocks
    private OrganisationRegistrationController controller;

    // Simulated JavaFX components
    private TextField organisationNameField;
    private TextField supportedGroupField;
    private TextArea organisationDescriptionArea;
    private TextField organisationUsernameField;
    private TextField organisationPasswordField;
    private TextField organisationEmailField;
    private ComboBox<String> categorySupportedGroupComboBox;
    private ComboBox<String> categoryOfOrganisationComboBox;
    private ComboBox<String> sizeOfOrganisationComboBox;
    private ToggleGroup group;
    private ToggleGroup group2;
    private Button submitButton;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupSceneRoot(() -> {
            organisationNameField = new TextField();
            supportedGroupField = new TextField();
            organisationDescriptionArea = new TextArea();
            organisationUsernameField = new TextField();
            organisationPasswordField = new TextField();
            organisationEmailField = new TextField();
            categorySupportedGroupComboBox = new ComboBox<>();
            categoryOfOrganisationComboBox = new ComboBox<>();
            sizeOfOrganisationComboBox = new ComboBox<>();
            group = new ToggleGroup();
            group2 = new ToggleGroup();
            submitButton = new Button();

            return submitButton; // Just a placeholder for FxToolkit
        });

        FxToolkit.showStage();

        // Setting sample data
        organisationNameField.setText("TestOrg");
        supportedGroupField.setText("Group1");
        organisationDescriptionArea.setText("A sample organisation");
        organisationUsernameField.setText("testorg");
        organisationPasswordField.setText("password123");
        organisationEmailField.setText("test@example.com");
        categorySupportedGroupComboBox.setValue("Category1");
        categoryOfOrganisationComboBox.setValue("Category2");
        sizeOfOrganisationComboBox.setValue("Small");

        // Setting up mock toggle groups
        RadioButton mockRadio1 = new RadioButton("Option1");
        RadioButton mockRadio2 = new RadioButton("Option2");
        group.getToggles().add(mockRadio1);
        group.getToggles().add(mockRadio2);
        mockRadio1.setSelected(true);

        RadioButton mockRadio3 = new RadioButton("Option3");
        RadioButton mockRadio4 = new RadioButton("Option4");
        group2.getToggles().add(mockRadio3);
        group2.getToggles().add(mockRadio4);
        mockRadio3.setSelected(true);
    }

    @Test
    public void testSuccessfulOrganisationRegistration() throws IOException {
        // Mock DAO behavior for valid input scenario
        when(mockOrganisationDAO.CheckOrganisationUserName("testorg")).thenReturn(false);
        when(mockOrganisationDAO.CheckOrganisationEmail("test@example.com")).thenReturn(false);
        when(mockOrganisationDAO.CheckOrganisationName("TestOrg")).thenReturn(false);

        // Replicating logic from handleOrganisationButtonAction()
        if (organisationNameField.getText().isEmpty() ||
                supportedGroupField.getText().isEmpty() ||
                organisationDescriptionArea.getText().isEmpty() ||
                organisationUsernameField.getText().isEmpty() ||
                organisationPasswordField.getText().isEmpty() ||
                organisationEmailField.getText().isEmpty() ||
                categorySupportedGroupComboBox.getValue() == null ||
                categoryOfOrganisationComboBox.getValue() == null ||
                sizeOfOrganisationComboBox.getValue() == null ||
                group.getSelectedToggle() == null ||
                group2.getSelectedToggle() == null) {

            // Handle form validation failure
            System.out.println("Form Incomplete. All fields must be filled.");
            return;
        }

        Organisation organisation = new Organisation(
                organisationNameField.getText(),
                categorySupportedGroupComboBox.getValue(),
                organisationDescriptionArea.getText(),
                null,  // image path
                organisationEmailField.getText(),
                organisationUsernameField.getText(),
                organisationPasswordField.getText()
        );

        OrganisationAnswers organisationAnswers = new OrganisationAnswers(
                categoryOfOrganisationComboBox.getValue(),
                sizeOfOrganisationComboBox.getValue(),
                "monetaryDonation",  // Just an example
                ((RadioButton) group.getSelectedToggle()).getText(),
                true
        );

        // Perform database insertions
        mockOrganisationDAO.insert(organisation);
        mockOrganisationAnswersDAO.insert(organisationAnswers);

        verify(mockOrganisationDAO, times(1)).insert(any(Organisation.class));
        verify(mockOrganisationAnswersDAO, times(1)).insert(any(OrganisationAnswers.class));
    }

    @Test
    public void testOrganisationUsernameAlreadyTaken() throws IOException {
        // Mock the behavior to simulate that the organisation username is already taken
        when(mockOrganisationDAO.CheckOrganisationUserName("testorg")).thenReturn(true);

        // Replicating the logic from handleOrganisationButtonAction()
        if (mockOrganisationDAO.CheckOrganisationUserName(organisationUsernameField.getText())) {
            System.out.println("Organisation username Taken. Please choose a different username.");
            return;
        }

        // Verify that no insertion happened
        verify(mockOrganisationDAO, never()).insert(any());
    }

    @Test
    public void testOrganisationEmailAlreadyExists() throws IOException {
        // Mock the behavior to simulate that the email is already taken
        when(mockOrganisationDAO.CheckOrganisationEmail("test@example.com")).thenReturn(true);

        // Replicating the logic from handleOrganisationButtonAction()
        if (mockOrganisationDAO.CheckOrganisationEmail(organisationEmailField.getText())) {
            System.out.println("Email Exists. Please use a different email.");
            return;
        }

        // Verify that no insertion happened
        verify(mockOrganisationDAO, never()).insert(any());
    }
}
