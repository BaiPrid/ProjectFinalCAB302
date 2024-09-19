package com.example.finalassignmentcab302;
import java.sql.Connection;

import com.example.finalassignmentcab302.dao.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 500;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LandingPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);

        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        stage.setTitle("Pandolla$!");
        stage.setScene(scene);
        stage.show();
    }

    /*

    // Method to switch to the new page
    public void switchToOrgLogin(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrgLogin.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 500);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        stage.setScene(scene);
    }



    // Method to switch to the new page
    public void switchToOrganisationRegistrationPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrganisationRegistrationPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 500);

        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    // Method to switch to the new page
    public void switchToLoginPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserLogin.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 500); // Adjust the size if necessary

        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }




    / Method to switch to the Organisation page
    public void switchToLoginPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrgLogin.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 500); // Adjust the size if necessary
        stage.setScene(scene);
    }

    // Method to switch to the new page
    public void switchToUserRegistrationPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserRegistrationPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 500); // Adjust the size if necessary

        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    // Method to switch to the new page
    public void switchToCharitiesPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CharitiesPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 500); // Adjust the size if necessary

        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    */

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



}

