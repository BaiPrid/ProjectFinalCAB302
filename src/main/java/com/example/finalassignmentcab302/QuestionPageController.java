package com.example.finalassignmentcab302;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;
import java.util.List;

public class QuestionPageController {

    private int questionIncrement = 0;

    List<Question> questions = StaticQuestionList.getQuestions();

    @FXML
    private Label welcomeText;

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
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void onNextButtonClick() {

        if (radButton1.isSelected()){
            int answer = 1;
        }
        else if (radButton2.isSelected()){
            int answer =2;
        }

        //Answers will be uploaded to database here too with an sql query

        questionIncrement++;

        if (questionIncrement <= 2){
            displayQuestion(questions.get(questionIncrement));
        }
        else{
            questionField.setText("There are no more questions. In a later build this will route to next page");
        }
    }

    @FXML
    private void activateNextButton(){
        boolean accepted = radButton1.isSelected() || radButton2.isSelected();
        nextButton.setDisable(!accepted);

    }


}