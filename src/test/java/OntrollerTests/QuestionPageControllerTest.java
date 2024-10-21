package OntrollerTests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.finalassignmentcab302.Question;
import org.junit.jupiter.api.Test;
import com.example.finalassignmentcab302.dao.UserAnswersDAO;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


public class QuestionPageControllerTest {

    private MockQuestionPageController mockController;
    private UserAnswersDAO mockUserAnswersDAO;

    @BeforeEach
    public void setUp() {

        // Creates a new DAO and passes it to the fake controller
        mockUserAnswersDAO = Mockito.mock(UserAnswersDAO.class);
        mockController = new MockQuestionPageController(mockUserAnswersDAO);

    }

    @Test
    public void testWithValidAnswer() throws IOException {

        // Populates the mock questions
        List<Question> mockQuestions = createMockQuestions();
        mockController.setMockQuestions(mockQuestions);

        //SELECTS ANSWER
        mockController.onNextButtonClick("Answer1");


        assertEquals(1, mockController.getQuestionIncrement());
        //Clears message
        assertEquals("", mockController.getMockAlertMessage());

    }

    @Test
    public void testWithoutAnswer() throws IOException {
        // Same as above, populates the mock questions
        List<Question> mockQuestions = createMockQuestions();
        mockController.setMockQuestions(mockQuestions);

        // DOES NOT SELECT ANSWER
        mockController.onNextButtonClick(null);

        //Passes message
        assertEquals("Please Choose an Answer!", mockController.getMockAlertMessage());
    }

    //Checks if multiple answers are actually answered
    @Test
    public void testMultipleValidAnswers() throws IOException {
        // Same as above, populates the mock questions
        List<Question> mockQuestions = createMockQuestions();
        mockController.setMockQuestions(mockQuestions);

        //Answers multiple questions
        for (int i = 0; i <= 4; i++) {

            mockController.onNextButtonClick("Answer" + (i+1));

        }

        // Slightly adapted from above, makes sure that there are 5 questions
        assertEquals(5, mockController.getQuestionIncrement());
        assertEquals("", mockController.getMockAlertMessage());
    }

    // CREATES FAKE QUESTION LIST
    private List<Question> createMockQuestions() {

        List<Question> mockQuestions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            mockQuestions.add(new Question("Question" + (i + 1), "Answer1", "Answer2", null, null, null, null, null));

        }
        return mockQuestions;
    }
}
