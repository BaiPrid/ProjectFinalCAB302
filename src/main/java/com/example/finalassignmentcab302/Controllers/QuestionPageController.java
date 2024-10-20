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

/**
 * Controller for the question page. Displays 5 questions to the user and stores their answers in a database
 */
public class QuestionPageController {


    /**
     * Keeps track of the current question the user is completing
     */
    private int questionIncrement = 0;
    /**
     * Holds the list of answers the user enters before uploading it to the database
     */
    List<String> answerList2 = new ArrayList<>();

    /**
     * Contains the list of questions and their respective answers
     */
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
    public ComboBox UserAnswersField;

    /**
     * Grabs the list of questions and displays the current question to the user
     * in accordance with the questionIncrement value which updates after every
     * user entry
     */
    @FXML
    private void initialize() {

        questions = StaticQuestionList.getQuestions();

        displayQuestion(questions.get(questionIncrement));
    }

    /**
     * Method for clearing the users current selection and replacing all values in the combobox.
     * Once values are cleared, this method changes the question shown to the user and inserts
     * all of that questions answers into the combobox
     * @param question the object containing the question itself and its answers
     */
    //Stores the answers for each question inside an answer list
    private void displayQuestion(Question question) {
        questionField.setText(question.getQuestion());

        UserAnswersField.getSelectionModel().clearSelection(); //clears the user's selection
        UserAnswersField.getItems().clear(); //clears all items from the answerfield

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

    /**
     * Sends the user to the charities page which displays charities that would be of the highest interest
     * to the user
     * @throws IOException if there is a problem loading the page
     */
    @FXML
    private void handleCharitiesPage() throws IOException {
        Stage stage = (Stage) nextButton.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CharitiesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    /**
     * This method is responsible for the core functionality of the page.
     * Once the user clicks the next button, they are presented with the next question in the list of
     * questions so long as they have provided an answer, otherwise they are shown an error.
     * @throws IOException if there is a problem loading the page
     */
    @FXML
    public void onNextButtonClick() throws IOException {

        if (UserAnswersField.getSelectionModel().getSelectedItem() != null){
            answerList2.add((String) UserAnswersField.getSelectionModel().getSelectedItem());
            questionIncrement++;
            errorField.setText("");
        }

        else{
            errorField.setText("Please Choose an Answer!");
        }

        /**
         * Goes through the questions shown to the user until there are no questions remaining in the list.
         * After the final question, all stored values are sent to the database with an UPDATE query.
         * This query is UPDATE rather than INSERT as some users may be redoing their answers and this ensures
         * that no duplicate entries are entered.
          */
        if (questionIncrement <= 4){
            displayQuestion(questions.get(questionIncrement));
        }
        else{


            boolean boolNum = false;
            if ("You Choose".equals(String.valueOf(answerList2.get(4)))){
                boolNum = true;
            }


            UserAnswers userAnswers = new UserAnswers(CurrentUserGLOBAL.currentUser, String.valueOf(answerList2.get(0)), String.valueOf(answerList2.get(1)), String.valueOf(answerList2.get(2)), String.valueOf(answerList2.get(3)), true);
            UserAnswersDAO userAnswersDAO = new UserAnswersDAO();
            userAnswersDAO.updateUserAnswers(userAnswers);
            handleCharitiesPage();
        }
    }


    /**
     * Disables the next button by default until an option is selected
     */
    @FXML
    private void activateNextButton(){
        boolean accepted = radButton1.isSelected() || radButton2.isSelected();
        nextButton.setDisable(!accepted);

    }

}