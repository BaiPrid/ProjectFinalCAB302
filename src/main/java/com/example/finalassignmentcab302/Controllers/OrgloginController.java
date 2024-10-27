package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.CurrentUserGLOBAL;
import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.Tables.Organisation;
import com.example.finalassignmentcab302.Tables.OrganisationAnswers;
import com.example.finalassignmentcab302.dao.OrganisationAnswersDAO;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private Button NavToOrgAccount;





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
            OrganisationDAO currentUserDAO = new OrganisationDAO();
            CurrentUserGLOBAL.currentUser = currentUserDAO.getOrgID(username, password);
            //System.out.println(CurrentUserGLOBAL.currentUser); //<---- For testing
            handleOrgAccountPage();
        }


        else {
            welcomeText.setText("Invalid Member Credentials");




        }




    }

    /**
     * Sends the user to the user's account page. Should only be sent if login verification
     * is true.
     * @throws IOException if there is a problem loading the page.
     */
    @FXML
    protected void handleOpenOrgAccountPage() throws IOException {
        Stage stage = (Stage) NavToOrgAccount.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrgAccountPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }



    @FXML
    private void handleOpenHome() throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.start(stage); // Switch to the new page
    }
    @FXML
    protected void handleOrgAccountPage() throws IOException {
        Stage stage = (Stage) NavToOrgAccount.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrgAccountPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }


}
