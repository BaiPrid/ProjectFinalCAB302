package com.example.finalassignmentcab302;

import com.example.finalassignmentcab302.Tables.User;
import com.example.finalassignmentcab302.Tables.UserAnswers;
import com.example.finalassignmentcab302.dao.UserAnswersDAO;
import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.example.finalassignmentcab302.DatabaseConnection;
import com.example.finalassignmentcab302.CustomQuery;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class QuestionPageController {

    private int questionIncrement = 0;
    private int questionVal = 0;

    List<Question> questions = StaticQuestionList.getQuestions();

    @FXML
    private Button btnDone;

    @FXML
    private Button nextButton;

    @FXML
    private RadioButton radButton1;

    @FXML
    private RadioButton radButton2;

    @FXML
    private ToggleGroup answerGroup;

    @FXML
    private Label questionField;

    @FXML
    private void initialize() {

        questions = StaticQuestionList.getQuestions();

        displayQuestion(questions.get(questionIncrement));
    }

    //sets the text for the question and radio buttons
    private void displayQuestion(Question question) {
        questionField.setText(question.getQuestion());
        radButton1.setText(question.getAnswer1());
        radButton2.setText(question.getAnswer2());
    }

    @FXML
    private void onDoneButtonClick() throws IOException {
        Stage stage = (Stage) btnDone.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CharitiesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }


    @FXML
    private void onNextButtonClick() {

        //USE THE INCREMENT TO INCREASE THE QUESTIONVAL
        if (radButton1.isSelected()){
            questionVal = 1;

        }
        else if (radButton2.isSelected()){
            questionVal = 2;
        }

        System.out.println(CurrentUserGLOBAL.currentUser);
        /*
        public void runCustomSQLQuery() {
            CustomQuery customQuery = new CustomQuery();
            String sql = "";
            customQuery.executeQuery(sql);  // Now this will correctly refer to the method in CustomQuery
        }

         */
        //Answers will be uploaded to database here too with an sql query

        /* MAKING NEW USER ENTRY
        User user = new User("organisationName", "organisationName", "organisationName", "organisationName", "organisationName");
        UserDAO userdao = new UserDAO();
        userdao.insert(user);
        */

        /* //MAKING NEW USER ANSWERS ENTRY
        boolean tempvar = true;
        //(category, size, donationOptions, taxableCategory, donorSpecifies, userAns1, userAns2, userAns3
        UserAnswers userAnswers = new UserAnswers("test2", "test3","test4", "test5", tempvar, "ans1", "ans2", "ans3");
        UserAnswersDAO userAnswersDAO = new UserAnswersDAO();
        userAnswersDAO.insert(userAnswers);
        */


        UserAnswers userAnswers = new UserAnswers(1, "Please", "God", "Work");
        UserAnswersDAO userAnswersDAO = new UserAnswersDAO();
        userAnswersDAO.updateAnswersOnly(userAnswers);



        questionIncrement++;

        if (questionIncrement <= 2){
            displayQuestion(questions.get(questionIncrement));
        }
        else{
            //PUT THE USER ANSWERS HERE
            questionField.setText("There are no more questions. In a later build this will route to next page");
        }
    }

    @FXML
    private void activateNextButton(){
        boolean accepted = radButton1.isSelected() || radButton2.isSelected();
        nextButton.setDisable(!accepted);

    }

}