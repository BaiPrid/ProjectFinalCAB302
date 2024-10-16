package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the landing page. The first page to be displayed to the user.
 * User will have a choice of signing in as a donating user or an organisation.
 */
public class LandingPageController {

    @FXML
    public Text txtQuote;
    @FXML
    public Text txtTitle;
    @FXML
    private Button btnUserLogin;
    @FXML
    private Button btnOrgLogin;

    /**
     * Sends user to the user login page where the user may log in as returning user or
     * sign up as a new user.
     * @throws IOException if there is a problem loading the page.
     */
    @FXML
    private void handleUserLoginPage() throws IOException {
        Stage stage = (Stage) btnUserLogin.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);

        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    /**
     * Sends user to the organisation login page where the user may log in as a returning
     * organisation or sign up as a new organisation.
     * @throws IOException
     */
    @FXML
    private void handleOrgLoginPage() throws IOException {
        Stage stage = (Stage) btnOrgLogin.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrgLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);

        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }


}
