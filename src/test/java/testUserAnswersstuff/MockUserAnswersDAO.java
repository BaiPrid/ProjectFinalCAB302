package testUserAnswersstuff;

import com.example.finalassignmentcab302.Tables.UserAnswers;
import com.example.finalassignmentcab302.dao.UserAnswersDAO;

import java.util.ArrayList;
import java.util.List;

public class MockUserAnswersDAO extends UserAnswersDAO {
    private final List<UserAnswers> userAnswersList = new ArrayList<>();
    private int autoIncrementedId = 1;

    @Override
    public void insert(UserAnswers userAnswers) {
        userAnswers = new UserAnswers(
                autoIncrementedId++,
                userAnswers.getCategory(),
                userAnswers.getSize(),
                userAnswers.getDonationOptions(),
                userAnswers.getTaxableCategory(),
                userAnswers.getDonorSpecifies(),
                userAnswers.getUserAns1(),
                userAnswers.getUserAns2(),
                userAnswers.getUserAns3()
        );
        userAnswersList.add(userAnswers);
    }

    @Override
    public void update(UserAnswers userAnswers) {
        for (int i = 0; i < userAnswersList.size(); i++) {
            if (userAnswersList.get(i).getUserId() == userAnswers.getUserId()) {
                userAnswersList.set(i, userAnswers);
                break;
            }
        }
    }

    @Override
    public void delete(int userId) {
        userAnswersList.removeIf(userAnswers -> userAnswers.getUserId() == userId);
    }

    public List<UserAnswers> getAll() {
        return new ArrayList<>(userAnswersList);
    }
}
