package com.example.finalassignmentcab302.Controllers;

import com.example.finalassignmentcab302.*;
import com.example.finalassignmentcab302.Tables.UserAnswers;
import com.example.finalassignmentcab302.dao.UserAnswersDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class QuestionPageController {


    private int questionIncrement = 0;
    private int questionVal = 0;
    List<Integer> answerList = new ArrayList<>();
    List<String> answerList2 = new ArrayList<>();

    List<Question> questions = StaticQuestionList.getQuestions();

    @FXML
    private Button btnDone;

    @FXML
    private Label txtText;

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
    private Label errorField;

    @FXML
    private ComboBox UserAnswersField;

    @FXML
    private void initialize() {

        questions = StaticQuestionList.getQuestions();

        displayQuestion(questions.get(questionIncrement));
    }

    //Stores the answers for each question inside an answer list
    private void displayQuestion(Question question) {
        questionField.setText(question.getQuestion());

        UserAnswersField.getSelectionModel().clearSelection();
        UserAnswersField.getItems().clear();

        List<String> answers = new ArrayList<>();

        if (question.getAnswer1() != null) {
            answers.add(question.getAnswer1());
        }
        if (question.getAnswer2() != null) {
            answers.add(question.getAnswer2());
        }
        if (question.getAnswer3() != null) {
            answers.add(question.getAnswer3());
        }
        if (question.getAnswer4() != null) {
            answers.add(question.getAnswer4());
        }
        if (question.getAnswer5() != null) {
            answers.add(question.getAnswer5());
        }
        if (question.getAnswer6() != null) {
            answers.add(question.getAnswer6());
        }
        if (question.getAnswer7() != null) {
            answers.add(question.getAnswer7());
        }

        UserAnswersField.getItems().addAll(answers);
        //System.out.println(answers.size());

    }

    @FXML
    private void handleCharitiesPage() throws IOException {
        Stage stage = (Stage) nextButton.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CharitiesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }


    @FXML
    private void onNextButtonClick() throws IOException {

        //USE THE INCREMENT TO INCREASE THE QUESTIONVAL
        /*
        if (radButton1.isSelected()){
            questionVal = 1 + questionIncrement * 2;

        }
        else if (radButton2.isSelected()){
            questionVal = 2 + questionIncrement * 2;
        }

        answerList.add(questionVal);
        */

        if (UserAnswersField.getSelectionModel().getSelectedItem() != null){
            answerList2.add((String) UserAnswersField.getSelectionModel().getSelectedItem());
            questionIncrement++;
            errorField.setText("");
        }

        else{
            errorField.setText("Please Choose an Answer!");
        }

        //System.out.println(CurrentUserGLOBAL.currentUser);
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

        /*
        UserAnswers userAnswers = new UserAnswers(1, "Please", "God", "Work");
        UserAnswersDAO userAnswersDAO = new UserAnswersDAO();
        userAnswersDAO.updateAnswersOnly(userAnswers);
        */


        //questionIncrement++;

        if (questionIncrement <= 4){
            displayQuestion(questions.get(questionIncrement));
        }
        else{


            boolean boolNum = false;
            if ("You Choose".equals(String.valueOf(answerList2.get(4)))){
                boolNum = true;
            }

            //System.out.println("We Made it this far");
            UserAnswers userAnswers = new UserAnswers(CurrentUserGLOBAL.currentUser, String.valueOf(answerList2.get(0)), String.valueOf(answerList2.get(1)), String.valueOf(answerList2.get(2)), String.valueOf(answerList2.get(3)), true);
            //UserAnswers userAnswers = new UserAnswers(CurrentUserGLOBAL.currentUser, "please", "just", "work");
            //System.out.println("We Made it this far2");
            UserAnswersDAO userAnswersDAO = new UserAnswersDAO();
            //System.out.println("We Made it this far3");
            userAnswersDAO.updateUserAnswers(userAnswers);
            //System.out.println("We Made it this far4");
            //PUT THE USER ANSWERS HERE
            //questionField.setText("There are no more questions. In a later build this will route to next page");
            handleCharitiesPage();
        }
    }



    @FXML
    private void activateNextButton(){
        boolean accepted = radButton1.isSelected() || radButton2.isSelected();
        nextButton.setDisable(!accepted);

    }

}