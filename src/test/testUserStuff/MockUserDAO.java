package testUserStuff;

import com.example.finalassignmentcab302.Tables.User;
import com.example.finalassignmentcab302.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class MockUserDAO extends UserDAO {
    private final List<User> userList = new ArrayList<>();
    private int autoIncrementedId = 1;

    @Override
    public void insert(User user) {
        user = new User(
                autoIncrementedId++,
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getEconomicClass()
        );
        userList.add(user);
    }

    @Override
    public void update(User user) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == user.getId()) {
                userList.set(i, user);
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        userList.removeIf(user -> user.getId() == id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(userList);
    }

    @Override
    public User getByLogin(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
