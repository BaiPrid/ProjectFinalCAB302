package testOrganisationAnswersstuff;

import com.example.finalassignmentcab302.Tables.OrganisationAnswers;
import com.example.finalassignmentcab302.dao.OrganisationAnswersDAO;

import java.util.ArrayList;
import java.util.List;

public class MockOrganisationAnswersDAO extends OrganisationAnswersDAO {
    private final List<OrganisationAnswers> organisationAnswersList = new ArrayList<>();
    private int autoIncrementedId = 1;

    @Override
    public void insert(OrganisationAnswers organisationAnswers) {
        organisationAnswers = new OrganisationAnswers(
                autoIncrementedId++,
                organisationAnswers.getCategory(),
                organisationAnswers.getSize(),
                organisationAnswers.getDonationOptions(),
                organisationAnswers.getTaxableCategory(),
                organisationAnswers.getDonorSpecifies()
        );
        organisationAnswersList.add(organisationAnswers);
    }

    @Override
    public void update(OrganisationAnswers organisationAnswers) {
        for (int i = 0; i < organisationAnswersList.size(); i++) {
            if (organisationAnswersList.get(i).getOrganisationId() == organisationAnswers.getOrganisationId()) {
                organisationAnswersList.set(i, organisationAnswers);
                break;
            }
        }
    }

    @Override
    public void delete(int organisationId) {
        organisationAnswersList.removeIf(organisationAnswers -> organisationAnswers.getOrganisationId() == organisationId);
    }

    @Override
    public List<OrganisationAnswers> getAll() {
        return new ArrayList<>(organisationAnswersList);
    }
}
