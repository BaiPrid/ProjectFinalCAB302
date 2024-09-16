package testingtablesfolder;

import com.example.finalassignmentcab302.Tables.OrganisationAnswers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrganisationAnswersTest {
    private OrganisationAnswers organisationAnswers;

    @BeforeEach
    public void setUp() {
        // Initialize the OrganisationAnswers object before each test
        organisationAnswers = new OrganisationAnswers(1, "Homeless", "Large", "20, 50", "Non-Taxable", true);
    }

    @Test
    public void testGetOrganisationId() {
        assertEquals(1, organisationAnswers.getOrganisationId());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Homeless", organisationAnswers.getCategory());
    }

    @Test
    public void testSetCategory() {
        organisationAnswers.setCategory("animal");
        assertEquals("animal", organisationAnswers.getCategory());
    }

    @Test
    public void testGetSize() {
        assertEquals("Large", organisationAnswers.getSize());
    }

    @Test
    public void testSetSize() {
        organisationAnswers.setSize("Medium");
        assertEquals("Medium", organisationAnswers.getSize());
    }

    @Test
    public void testGetDonationOptions() {
        assertEquals("20, 50", organisationAnswers.getDonationOptions());
    }

    @Test
    public void testSetDonationOptions() {
        organisationAnswers.setDonationOptions("20, 70");
        assertEquals("20, 70", organisationAnswers.getDonationOptions());
    }

    @Test
    public void testGetTaxableCategory() {
        assertEquals("Non-Taxable", organisationAnswers.getTaxableCategory());
    }

    @Test
    public void testSetTaxableCategory() {
        organisationAnswers.setTaxableCategory("Taxable");
        assertEquals("Taxable", organisationAnswers.getTaxableCategory());
    }

    @Test
    public void testGetDonorSpecifies() {
        assertTrue(organisationAnswers.getDonorSpecifies());
    }

    @Test
    public void testSetDonorSpecifies() {
        organisationAnswers.setDonorSpecifies(false);
        assertEquals(false, organisationAnswers.getDonorSpecifies());
    }
}
