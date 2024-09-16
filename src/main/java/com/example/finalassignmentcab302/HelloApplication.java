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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1250, 750);
        stage.setTitle("Home Page!");
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
        Scene scene = new Scene(root, 900, 500);
        stage.setScene(scene);
    }
    /* //THIS IS OLD AND DOESN'T WORK
    public void switchToQuestionPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("QuestionPageController.fxml"));
        System.out.println("THIS GET'S ACTIVATED IN APP1");
        Parent root = fxmlLoader2.load();
        System.out.println("THIS GET'S ACTIVATED IN APP2");
        Scene scene2 = new Scene(root, 900, 500);
        System.out.println("THIS GET'S ACTIVATED IN APP3");
        stage.setScene(scene2);
    }

     */




    public void switchToLoginPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 500); // Adjust the size if necessary
        stage.setScene(scene);
    }

    public void switchToUserRegistrationPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserRegistrationPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 500); // Adjust the size if necessary
        stage.setScene(scene);
    }

    // Method to switch to the new page
    public void switchToCharitiesPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CharitiesPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 500); // Adjust the size if necessary
        stage.setScene(scene);
    }



}

