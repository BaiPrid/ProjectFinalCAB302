package testUserStuff;

import com.example.finalassignmentcab302.Tables.User;
import com.example.finalassignmentcab302.dao.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserDAOTest {
    private UserDAO userDAO;
    private User[] users = {
            new User("John", "Doe", "johndoe", "password123", "john.doe@example.com", 0423423423, "Economy"),
            new User("Jane", "Doe", "janedoe", "password456", "jane.doe@example.com", 0423423424, "Business"),
            new User("Jay", "Doe", "jaydoe", "password789", "jay.doe@example.com", 0423423425, "Economy")
    };

    @BeforeEach
    public void setUp() {
        userDAO = new MockUserDAO();
    }

    @Test
    public void testInsertAndGetAll() {
        for (User user : users) {
            userDAO.insert(user);
        }
        List<User> allUsers = userDAO.getAll();
        assertEquals(3, allUsers.size());
    }

    @Test
    public void testUpdate() {
        userDAO.insert(users[0]);
        User updatedUser = new User(
                1, "John", "Smith", "johnsmith", "newpassword", "john.smith@example.com", 0423423426, "Premium"
        );
        userDAO.update(updatedUser);
        User fetchedUser = userDAO.getByLogin(1);
        assertEquals("John", fetchedUser.getFirstName());
        assertEquals("Smith", fetchedUser.getLastName());
        assertEquals("newpassword", fetchedUser.getPassword());
    }

    @Test
    public void testDelete() {
        for (User user : users) {
            userDAO.insert(user);
        }
        userDAO.delete(1);
        User fetchedUser = userDAO.getByLogin(1);
        assertNull(fetchedUser);
    }

    @Test
    public void testGetByLogin() {
        userDAO.insert(users[0]);
        User fetchedUser = userDAO.getByLogin(1);
        assertEquals("John", fetchedUser.getFirstName());
    }

    @Test
    public void testGetUserID() {
        userDAO.insert(users[0]);  // Insert a user
        int userId = userDAO.getUserID("johndoe", "password123");
        assertEquals(1, userId);  // Expect the ID of the first user

        // Test case when username and password do not match
        int invalidUserId = userDAO.getUserID("wronguser", "wrongpassword");
        assertEquals(-1, invalidUserId);  // Expect -1 for invalid credentials
    }

    @Test
    public void testLoginSuccess() {
        userDAO.insert(users[0]);  // Insert a user
        boolean loginSuccess = userDAO.login("johndoe", "password123");
        assertTrue(loginSuccess);  // Login should succeed with correct credentials
    }

    @Test
    public void testLoginFailure() {
        userDAO.insert(users[0]);  // Insert a user
        boolean loginFailure = userDAO.login("wronguser", "wrongpassword");
        assertFalse(loginFailure);  // Login should fail with incorrect credentials
    }
}
