package testingtablesfolder;

import com.example.finalassignmentcab302.Tables.UserAnswers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserAnswersTest {
    private UserAnswers userAnswers;

    @BeforeEach
    public void setUp() {
        // Initialize the UserAnswers object before each test
        userAnswers = new UserAnswers(1, "Charity", "Medium", "Money", "Non-Taxable", true, "Answer1", "Answer2", "Answer3");
    }

    @Test
    public void testGetUserId() {
        assertEquals(1, userAnswers.getUserId());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Charity", userAnswers.getCategory());
    }

    @Test
    public void testSetCategory() {
        userAnswers.setCategory("Homeless");
        assertEquals("Homeless", userAnswers.getCategory());
    }

    @Test
    public void testGetSize() {
        assertEquals("Medium", userAnswers.getSize());
    }

    @Test
    public void testSetSize() {
        userAnswers.setSize("Large");
        assertEquals("Large", userAnswers.getSize());
    }

    @Test
    public void testGetDonationOptions() {
        assertEquals("Money", userAnswers.getDonationOptions());
    }

    @Test
    public void testSetDonationOptions() {
        userAnswers.setDonationOptions("volunteer");
        assertEquals("volunteer", userAnswers.getDonationOptions());
    }

    @Test
    public void testGetTaxableCategory() {
        assertEquals("Non-Taxable", userAnswers.getTaxableCategory());
    }

    @Test
    public void testSetTaxableCategory() {
        userAnswers.setTaxableCategory("Taxable");
        assertEquals("Taxable", userAnswers.getTaxableCategory());
    }

    @Test
    public void testGetDonorSpecifies() {
        assertTrue(userAnswers.getDonorSpecifies());
    }

    @Test
    public void testSetDonorSpecifies() {
        userAnswers.setDonorSpecifies(false);
        assertEquals(false, userAnswers.getDonorSpecifies());
    }

    @Test
    public void testGetUserAns1() {
        assertEquals("Answer1", userAnswers.getUserAns1());
    }

    @Test
    public void testSetUserAns1() {
        userAnswers.setUserAns1("NewAnswer1");
        assertEquals("NewAnswer1", userAnswers.getUserAns1());
    }

    @Test
    public void testGetUserAns2() {
        assertEquals("Answer2", userAnswers.getUserAns2());
    }

    @Test
    public void testSetUserAns2() {
        userAnswers.setUserAns2("NewAnswer2");
        assertEquals("NewAnswer2", userAnswers.getUserAns2());
    }

    @Test
    public void testGetUserAns3() {
        assertEquals("Answer3", userAnswers.getUserAns3());
    }

    @Test
    public void testSetUserAns3() {
        userAnswers.setUserAns3("NewAnswer3");
        assertEquals("NewAnswer3", userAnswers.getUserAns3());
    }
}
