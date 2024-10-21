package OntrollerTests;

import com.example.finalassignmentcab302.Controllers.QuestionPageController;

import com.example.finalassignmentcab302.CurrentUserGLOBAL;
import com.example.finalassignmentcab302.Question;
import com.example.finalassignmentcab302.Tables.UserAnswers;
import com.example.finalassignmentcab302.dao.UserAnswersDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MockQuestionPageController extends QuestionPageController {

    //Initialises mock variables
    private List<String> answerList = new ArrayList<>();
    private List<Question> questions = new ArrayList<>();

    private UserAnswersDAO userAnswersDAO;

    private String mockMessage;
    private int questionIncrement = 0;

    public MockQuestionPageController(UserAnswersDAO userAnswersDAO) {
        this.userAnswersDAO = userAnswersDAO;
    }

    public void setMockQuestions(List<Question> mockQuestions) {
        this.questions = mockQuestions;
        // Set increment to 0 for new questions
        this.questionIncrement = 0;
        // get rid of previous questions
        this.answerList.clear();
    }

    public void displayQuestion(Question question) {

        mockMessage = "Displaying: " + question.getQuestion();
    }

    public void onNextButtonClick(String selectedAnswer) throws IOException {
        if (selectedAnswer != null) {
            answerList.add(selectedAnswer);
            questionIncrement++;

            //Go through questions until there are none left, then submit
            if (questionIncrement < questions.size()) {

                displayQuestion(questions.get(questionIncrement));

            } else {

                submitAnswers();

            }
            //Clears Message
            mockMessage = "";

        } else {

            mockMessage = "Please Choose an Answer!";

        }
    }


    private void submitAnswers() throws IOException {

        // New UserAnswers instance
        UserAnswers userAnswers = new UserAnswers("MockUser",  answerList.get(0), answerList.get(1),  answerList.get(2), answerList.get(3), true);

        userAnswersDAO.updateUserAnswers(userAnswers);
        mockMessage = "Answers submitted!";
    }

    public String getMockAlertMessage() {
        return mockMessage;
    }

    public int getQuestionIncrement() {
        return questionIncrement;
    }
}
