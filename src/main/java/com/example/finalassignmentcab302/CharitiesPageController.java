package com.example.finalassignmentcab302;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class CharitiesPageController {

    private Stage primaryStage; // Add a Stage field


    @FXML
    private Label lblCharity1;

    @FXML
    private Text txtCharity1;

    @FXML
    private void initialize() {
        lblCharity1.setText("Charity 1 Name.");
        txtCharity1.setText("Charity 1 description! Very very long.");
    }

}


