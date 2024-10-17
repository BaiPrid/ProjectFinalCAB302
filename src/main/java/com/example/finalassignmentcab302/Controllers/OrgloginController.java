package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.CurrentUserGLOBAL;
import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;


public class OrgloginController
{
    @FXML
    public Text txtTitle;
    @FXML
    public Text txtQuote;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField orgUsername;

    @FXML
    private PasswordField orgPassword;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private Button questNav;

    @FXML
    private Button btnBack;


    @FXML
    protected void handleOpenOrganisationRegistration() throws IOException {
        Stage stage = (Stage) questNav.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrganisationRegistrationPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }


    @FXML
    protected void handleOrgLogin() throws IOException {
        String username = orgUsername.getText();
        String password = orgPassword.getText();
        OrganisationDAO organisationDAO = new OrganisationDAO();

        boolean login = organisationDAO.login(username, password);

        if (login == true) {
            welcomeText.setText("SUCCESS!");
            //////////////////////////////////NEW SECTION///////////////////////////////////
            //UserDAO currentUserDAO = new UserDAO();
            //CurrentUserGLOBAL.currentUser = currentUserDAO.getUserID(username, password);
            //System.out.println(CurrentUserGLOBAL.currentUser); <---- For testing
        } else {
            welcomeText.setText("Invalid Member Credentials");

        }
    }

    @FXML
    private void handleOpenHome() throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.start(stage); // Switch to the new page
    }


}
