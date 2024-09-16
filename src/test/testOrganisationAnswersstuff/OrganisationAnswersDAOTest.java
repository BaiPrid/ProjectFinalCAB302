package testOrganisationAnswersstuff;

import com.example.finalassignmentcab302.Tables.OrganisationAnswers;
import com.example.finalassignmentcab302.dao.OrganisationAnswersDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrganisationAnswersDAOTest {
    private OrganisationAnswersDAO organisationAnswersDAO;
    private OrganisationAnswers[] organisationAnswersArray = {
            new OrganisationAnswers("Category1", "Size1", "Donation1", "Taxable1", true),
            new OrganisationAnswers("Category2", "Size2", "Donation2", "Taxable2", false),
            new OrganisationAnswers("Category3", "Size3", "Donation3", "Taxable3", true)
    };

    @BeforeEach
    public void setUp() {
        organisationAnswersDAO = new MockOrganisationAnswersDAO();
    }

    @Test
    public void testInsertAndGetAll() {
        for (OrganisationAnswers organisationAnswers : organisationAnswersArray) {
            organisationAnswersDAO.insert(organisationAnswers);
        }
        List<OrganisationAnswers> allOrganisationAnswers = organisationAnswersDAO.getAll();
        assertEquals(3, allOrganisationAnswers.size());
    }

    @Test
    public void testUpdate() {
        organisationAnswersDAO.insert(organisationAnswersArray[0]);
        OrganisationAnswers updatedOrganisationAnswers = new OrganisationAnswers(
                1, "UpdatedCategory", "UpdatedSize", "UpdatedDonation", "UpdatedTaxable", false
        );
        organisationAnswersDAO.update(updatedOrganisationAnswers);
        List<OrganisationAnswers> allOrganisationAnswers = organisationAnswersDAO.getAll();
        OrganisationAnswers fetchedOrganisationAnswers = allOrganisationAnswers.get(0);
        assertEquals("UpdatedCategory", fetchedOrganisationAnswers.getCategory());
        assertEquals("UpdatedSize", fetchedOrganisationAnswers.getSize());
        assertEquals("UpdatedDonation", fetchedOrganisationAnswers.getDonationOptions());
        assertEquals("UpdatedTaxable", fetchedOrganisationAnswers.getTaxableCategory());
        assertFalse(fetchedOrganisationAnswers.getDonorSpecifies());
    }

    @Test
    public void testDelete() {
        for (OrganisationAnswers organisationAnswers : organisationAnswersArray) {
            organisationAnswersDAO.insert(organisationAnswers);
        }
        organisationAnswersDAO.delete(1);
        List<OrganisationAnswers> allOrganisationAnswers = organisationAnswersDAO.getAll();
        assertEquals(2, allOrganisationAnswers.size());
    }
}
