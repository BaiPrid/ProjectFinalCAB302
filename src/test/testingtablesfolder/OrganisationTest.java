package testingtablesfolder;

import com.example.finalassignmentcab302.Tables.Organisation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrganisationTest {
    private Organisation organisation;

    @BeforeEach
    public void setUp() {

        organisation = new Organisation(1, "Rosies", "Homeless", "We help People",
                "/images/testingimage.png", "rosies@gmail.com", "rose", "password");
    }

    @Test
    public void testGetId() {
        assertEquals(1, organisation.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Rosies", organisation.getName());
    }

    @Test
    public void testSetName() {
        organisation.setName("New Org Name");
        assertEquals("New Org Name", organisation.getName());
    }

    @Test
    public void testGetGroupSupported() {
        assertEquals("Homeless", organisation.getGroupSupported());
    }

    @Test
    public void testSetGroupSupported() {
        organisation.setGroupSupported("Social Welfare");
        assertEquals("Social Welfare", organisation.getGroupSupported());
    }

    @Test
    public void testGetDescription() {
        assertEquals("We help People", organisation.getDescription());
    }

    @Test
    public void testSetDescription() {
        organisation.setDescription("A new description for the organisation");
        assertEquals("A new description for the organisation", organisation.getDescription());
    }

    @Test
    public void testGetImgPath() {
        assertEquals("/images/testingimage.png", organisation.getImgPath());
    }

    @Test
    public void testSetImgPath() {
        organisation.setImgPath("/new/path/to/test.png");
        assertEquals("/new/path/to/test.png", organisation.getImgPath());
    }

    @Test
    public void testGetEmail() {
        assertEquals("rosies@gmail.com", organisation.getEmail());
    }

    @Test
    public void testSetEmail() {
        organisation.setEmail("rosies@hotmail.com");
        assertEquals("rosies@hotmail.com", organisation.getEmail());
    }

    @Test
    public void testGetUserName() {
        assertEquals("rose", organisation.getUserName());
    }

    @Test
    public void testSetUserName() {
        organisation.setUserName("newuser");
        assertEquals("newuser", organisation.getUserName());
    }

    @Test
    public void testGetPassWord() {
        assertEquals("password", organisation.getPassWord());
    }

    @Test
    public void testSetPassWord() {
        organisation.setPassWord("newPassword");
        assertEquals("newPassword", organisation.getPassWord());
    }
}

