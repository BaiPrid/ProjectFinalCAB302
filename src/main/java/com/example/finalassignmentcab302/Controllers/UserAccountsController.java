package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.HelloApplication;
import com.example.finalassignmentcab302.Tables.Order;
import com.example.finalassignmentcab302.Tables.User;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.finalassignmentcab302.dao.UserDAO;
import com.example.finalassignmentcab302.dao.OrderDAO;

import static com.example.finalassignmentcab302.CurrentUserGLOBAL.currentUser;

/**
 * Controller for user's accounts. Displays the user's past orders and allows
 * user to retake the questionnaire, donate or sign out.
 */
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
    @FXML
    private TextField orderIDField;
    @FXML
    private TextField organisationField;
    @FXML
    private TextField orderDateField;
    @FXML
    private TextField amountField;


    //---------- BUTTONS TO DIFFERENT PAGES !! -------------

    /**
     * Sends user to the home page.
     * @throws IOException if there is a problem loading the page.
     */
    @FXML
    private void handleOpenHome() throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow(); // Get the current stage
        HelloApplication app = new HelloApplication();
        app.start(stage); // Switch to the new page
    }

    /**
     * Sends user to the charities page where it will load the top three charities
     * most suited to the user based on likeness to the user's questionnaire answers.
     * @throws IOException if there is a problem loading the page.
     */
    @FXML
    private void handleCharitiesPage() throws IOException {
        Stage stage = (Stage) btnDonate.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CharitiesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    /**
     * Sends user back to the questionnaire page where they may redo their answers.
     * @throws IOException if there is a problem loading the page.
     */
    @FXML
    private void handleQuestionsPage() throws IOException {
        Stage stage = (Stage) btnQuestions.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("questionsSS.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    // ------------- DATA INTERACTION -------------
    UserDAO userNameDAO = new UserDAO();
    OrderDAO userOrdersDAO = new OrderDAO();
    OrganisationDAO orgDAO = new OrganisationDAO();

    int userId = currentUser; // Replace with actual user ID
    // Gets the answers for the specific user id
    String userName = userNameDAO.getName(userId);

    @FXML
    private void initialize()
    {
//        List<String> orgNames = new ArrayList<String>();
//
//        for (Order order: userOrders)
//        {
//            orgNames.add(orgDAO.getName(userOrdersDAO.getOrgID(order.getOrganisationId())));
//        }

        txtTitle.setText("Welcome " + userName + "!");

        orderListView.setCellFactory(this::renderCell);
        syncOrders();
        orderListView.getSelectionModel().selectFirst();
        Order firstorder = orderListView.getSelectionModel().getSelectedItem();
        if (firstorder != null) {
            selectOrder(firstorder);
        }

        List<Order> userOrders = userOrdersDAO.getUserOrders(userId);

        for (Order order : userOrders)
        {
            System.out.println((order));
        }
        
    }

    /**
     * Synchronizes the orders list view with the orders in the database.
     */
    private void syncOrders() {
        orderListView.getItems().clear();
        List<Order> userOrders = userOrdersDAO.getUserOrders(userId);
        boolean hasOrders = !userOrders.isEmpty();
        if (hasOrders) {
            orderListView.getItems().addAll(userOrders);
        }
    }

    /**
     * Programmatically selects a order in the list view and
     * updates the text fields with the order's information.
     * @param order The order to select.
     */
    private void selectOrder(Order order) {
        orderListView.getSelectionModel().select(order);
        orderIDField.setText(Integer.toString(order.getOrderId()));
        organisationField.setText(Integer.toString(order.getOrganisationId()));
        orderDateField.setText(order.getOrderDateTime());
        amountField.setText(Float.toString(order.getAmount()));
    }

    private ListCell<Order> renderCell(ListView<Order> orderListView) {
        return new ListCell<>() {
            /**
             * Handles the event when an order is selected in the list view.
             * @param mouseEvent The event to handle.
             */
            private void onOrderSelected(MouseEvent mouseEvent) {
                ListCell<Order> clickedCell = (ListCell<Order>) mouseEvent.getSource();
                // Get the selected order from the list view
                Order selectedOrder = clickedCell.getItem();
                if (selectedOrder != null) selectOrder(selectedOrder);
            }

            protected void updateItem(Order order, boolean empty) {
                super.updateItem(order, empty);
                // If the cell is empty, set the text to null, otherwise set it to the contact's full name
                if (empty || order == null || order.getAmount() == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onOrderSelected);
                } else {
                    setText(Float.toString(order.getAmount()));
                }
            }

        };
    }



}
