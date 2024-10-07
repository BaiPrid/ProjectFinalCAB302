package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.Tables.Order;
import com.example.finalassignmentcab302.Tables.User;
import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import com.example.finalassignmentcab302.dao.UserDAO;
import com.example.finalassignmentcab302.dao.OrderDAO;

import static com.example.finalassignmentcab302.CurrentUserGLOBAL.currentUser;


public class UserAccountsController
{
    @FXML
    private Button btnDonate;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnQuestions;
    @FXML
    private Label txtTitle;
    @FXML
    public ListView<Order> orderListView;


    //---------- BUTTONS TO DIFFERENT PAGES !! -------------

    @FXML
    private void handleOpenHome() throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.start(stage); // Switch to the new page
    }

    @FXML
    private void handleCharitiesPage() throws IOException {
        Stage stage = (Stage) btnDonate.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CharitiesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    @FXML
    private void handleQuestionsPage() throws IOException {
        Stage stage = (Stage) btnQuestions.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("questionsSS.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }




    @FXML
    private void initialize()
    {
        // ------------- DATA INTERACTION (yay.) ------------
        UserDAO userNameDAO = new UserDAO();
        OrderDAO userOrdersDAO = new OrderDAO();

        int userId = currentUser; // Replace with actual user ID
        // Gets the answers for the specific user id

        User userName = userNameDAO.getByLogin(userId);
        String name = userName.getFirstName();

        List<Order> userOrders = userOrdersDAO.getUserOrders(userId);

        boolean hasOrders = !userOrders.isEmpty();
        if (hasOrders) {
            orderListView.getItems().addAll(userOrders);
            txtTitle.setText("Welcome " + name);
        }
    }



}
