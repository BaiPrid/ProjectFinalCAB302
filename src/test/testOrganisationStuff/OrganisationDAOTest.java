package testOrganisationStuff;

import com.example.finalassignmentcab302.Tables.Organisation;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testOrganisationStuff.MockOrganisationDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrganisationDAOTest {
    private OrganisationDAO organisationDAO;
    private Organisation[] organisations = {
            new Organisation(1, "Org 1", "Group 1", "Description 1", "Path 1", "email1@example.com", "user1", "pass1"),
            new Organisation(2, "Org 2", "Group 2", "Description 2", "Path 2", "email2@example.com", "user2", "pass2"),
            new Organisation(3, "Org 3", "Group 3", "Description 3", "Path 3", "email3@example.com", "user3", "pass3")
    };

    @BeforeEach
    public void setUp() {
        organisationDAO = new MockOrganisationDAO();
    }

    @Test
    public void testInsertAndGetAll() {
        for (Organisation organisation : organisations) {
            organisationDAO.insert(organisation);
        }
        List<Organisation> allOrganisations = organisationDAO.getAll();
        assertEquals(3, allOrganisations.size());
    }

    @Test
    public void testUpdate() {
        organisationDAO.insert(organisations[0]);
        Organisation updatedOrganisation = new Organisation(
                1, "Updated Org 1", "Updated Group", "Updated Description", "Updated Path", "updatedemail@example.com", "updatedUser", "updatedPass"
        );
        organisationDAO.update(updatedOrganisation);
        List<Organisation> allOrganisations = organisationDAO.getAll();
        Organisation fetchedOrganisation = allOrganisations.get(0);
        assertEquals("Updated Org 1", fetchedOrganisation.getName());
        assertEquals("Updated Description", fetchedOrganisation.getDescription());
    }

    @Test
    public void testDelete() {
        for (Organisation organisation : organisations) {
            organisationDAO.insert(organisation);
        }
        organisationDAO.delete(1);
        List<Organisation> allOrganisations = organisationDAO.getAll();
        assertEquals(2, allOrganisations.size());
    }
}
