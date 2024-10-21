package OntrollerTests;

import com.example.finalassignmentcab302.dao.UserAnswersDAO;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import java.util.List;
import java.util.Map;

public class MockCharitiesPageController {

    private UserAnswersDAO userAnswersDAO;
    private OrganisationDAO organisationDAO;

    private String lblCharity1Text;
    private String txtCharity1Text;
    private String lblCharity2Text;
    private String txtCharity2Text;
    private String lblCharity3Text;
    private String txtCharity3Text;

    public MockCharitiesPageController(UserAnswersDAO userAnswersDAO, OrganisationDAO organisationDAO) {
        this.userAnswersDAO = userAnswersDAO;
        this.organisationDAO = organisationDAO;
    }

    public void initialize() {
        // Simulate the logic from the actual CharitiesPageController
        int userId = 1; // Replace with current user logic

        List<String> userAnswers = userAnswersDAO.getUserAnswers(userId);

        if (userAnswers != null) {
            Map<Integer, Integer> matchingOrganisations = userAnswersDAO.getMatchingOrganisations(userAnswers);

            List<Map.Entry<Integer, Integer>> sortedMatches = matchingOrganisations.entrySet().stream()
                    .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                    .toList();

            int totalQuestions = 5;

            // Simulate charity 1
            int firstOrgId = sortedMatches.get(0).getKey();
            lblCharity1Text = organisationDAO.getName(firstOrgId);
            txtCharity1Text = organisationDAO.getDescription(firstOrgId) + "\nPercentage match: " +
                    String.format("%.2f", (sortedMatches.get(0).getValue() / (double) totalQuestions) * 100) + "%";

            // Simulate charity 2
            int secondOrgId = sortedMatches.get(1).getKey();
            lblCharity2Text = organisationDAO.getName(secondOrgId);
            txtCharity2Text = organisationDAO.getDescription(secondOrgId) + "\nPercentage match: " +
                    String.format("%.2f", (sortedMatches.get(1).getValue() / (double) totalQuestions) * 100) + "%";

            // Simulate charity 3
            int thirdOrgId = sortedMatches.get(2).getKey();
            lblCharity3Text = organisationDAO.getName(thirdOrgId);
            txtCharity3Text = organisationDAO.getDescription(thirdOrgId) + "\nPercentage match: " +
                    String.format("%.2f", (sortedMatches.get(2).getValue() / (double) totalQuestions) * 100) + "%";
        } else {
            lblCharity1Text = "No user answers found!";
            txtCharity1Text = "Please provide answers to match charities.";
            lblCharity2Text = "";
            txtCharity2Text = "";
            lblCharity3Text = "";
            txtCharity3Text = "";
        }
    }

    public String getLblCharity1Text() {
        return lblCharity1Text;
    }

    public String getTxtCharity1Text() {
        return txtCharity1Text;
    }

    public String getLblCharity2Text() {
        return lblCharity2Text;
    }

    public String getTxtCharity2Text() {
        return txtCharity2Text;
    }

    public String getLblCharity3Text() {
        return lblCharity3Text;
    }

    public String getTxtCharity3Text() {
        return txtCharity3Text;
    }
}
