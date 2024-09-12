package com.example.finalassignmentcab302;
import java.sql.Connection;

import com.example.finalassignmentcab302.Tables.OrganisationAnswers;
import com.example.finalassignmentcab302.dao.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1250, 750);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        //creates tables for each neccesary class by creating a connection to API and creating instance of each object
        launch();
        Connection connection = DatabaseConnection.getInstance();
        UserDAO userDAO = new UserDAO();
        userDAO.createTable();
        OrganisationDAO organisationDao = new OrganisationDAO();
        organisationDao.createTable();
        OrganisationAnswersDAO organisationAnswersDAO = new OrganisationAnswersDAO();
        organisationAnswersDAO.createTable();
        UserAnswersDAO userAnswersDAO = new UserAnswersDAO();
        userAnswersDAO.createTable();
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.createTable();

        orderDAO.close();
        organisationAnswersDAO.close();
        userDAO.close();
        organisationDao.close();
    }

    // Method to switch to the new page
    public void switchToOrganisationRegistrationPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrganisationRegistrationPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 500); // Adjust the size if necessary
        stage.setScene(scene);
    }


}

