package testUserAnswersstuff;

import com.example.finalassignmentcab302.Tables.UserAnswers;
import com.example.finalassignmentcab302.dao.UserAnswersDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserAnswersDAOTest {
    private UserAnswersDAO userAnswersDAO;
    private UserAnswers[] userAnswersArray = {
            new UserAnswers(1, "Category1", "Size1", "Donation1", "Taxable1", true, "Ans1_1", "Ans1_2", "Ans1_3"),
            new UserAnswers(2, "Category2", "Size2", "Donation2", "Taxable2", false, "Ans2_1", "Ans2_2", "Ans2_3"),
            new UserAnswers(3, "Category3", "Size3", "Donation3", "Taxable3", true, "Ans3_1", "Ans3_2", "Ans3_3")
    };

    @BeforeEach
    public void setUp() {
        userAnswersDAO = new MockUserAnswersDAO();
    }

    @Test
    public void testInsertAndGetAll() {
        for (UserAnswers userAnswers : userAnswersArray) {
            userAnswersDAO.insert(userAnswers);
        }
        List<UserAnswers> allUserAnswers = userAnswersDAO.getAll();
        assertEquals(3, allUserAnswers.size());
    }

    @Test
    public void testUpdate() {
        userAnswersDAO.insert(userAnswersArray[0]);
        UserAnswers updatedUserAnswers = new UserAnswers(
                1, "UpdatedCategory", "UpdatedSize", "UpdatedDonation", "UpdatedTaxable", false, "UpdatedAns1", "UpdatedAns2", "UpdatedAns3"
        );
        userAnswersDAO.update(updatedUserAnswers);
        List<UserAnswers> allUserAnswers = userAnswersDAO.getAll();
        UserAnswers fetchedUserAnswers = allUserAnswers.get(0);
        assertEquals("UpdatedCategory", fetchedUserAnswers.getCategory());
        assertEquals("UpdatedSize", fetchedUserAnswers.getSize());
        assertEquals("UpdatedDonation", fetchedUserAnswers.getDonationOptions());
        assertEquals("UpdatedTaxable", fetchedUserAnswers.getTaxableCategory());
        assertEquals("UpdatedAns1", fetchedUserAnswers.getUserAns1());
        assertEquals("UpdatedAns2", fetchedUserAnswers.getUserAns2());
        assertEquals("UpdatedAns3", fetchedUserAnswers.getUserAns3());
    }

    @Test
    public void testDelete() {
        for (UserAnswers userAnswers : userAnswersArray) {
            userAnswersDAO.insert(userAnswers);
        }
        userAnswersDAO.delete(1);
        List<UserAnswers> allUserAnswers = userAnswersDAO.getAll();
        assertEquals(2, allUserAnswers.size());
    }
}
