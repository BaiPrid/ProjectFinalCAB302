package com.example.finalassignmentcab302;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.IOException;

public class CharitiesPageController {

    private Stage primaryStage; // Add a Stage field


    @FXML
    private Label lblCharity1;
    @FXML
    private Text txtCharity1;
    @FXML
    private Button btnCharity1;

    @FXML
    private Label lblCharity2;
    @FXML
    private Text txtCharity2;
    @FXML
    private Button btnCharity2;

    @FXML
    private Label lblCharity3;
    @FXML
    private Text txtCharity3;
    @FXML
    private Button btnCharity3;

    @FXML
    private Button btnLogout;

    @FXML
    private void initialize() {
        lblCharity1.setText("Charity 1 Name.");
        txtCharity1.setText("Charity 1 description! Very very long.");

        lblCharity2.setText("Charity 2 Name.");
        txtCharity2.setText("Charity 2 description! Very very long.");

        lblCharity3.setText("Charity 3 Name.");
        txtCharity3.setText("Charity 3 description! Very very long.");
    }

    @FXML
    private void handleOpenHome() throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.start(stage); // Switch to the new page
    }

}


