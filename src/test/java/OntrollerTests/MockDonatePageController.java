package OntrollerTests;
import com.example.finalassignmentcab302.Controllers.DonatePageController;
import com.example.finalassignmentcab302.Tables.Order;
import com.example.finalassignmentcab302.dao.OrderDAO;
import com.example.finalassignmentcab302.dao.OrganisationAnswersDAO;
import com.example.finalassignmentcab302.dao.OrganisationDAO;

import java.util.List;

public class MockDonatePageController extends DonatePageController {

    private String mockAlertMessage;
    private OrganisationDAO mockOrganisationDAO;
    private OrganisationAnswersDAO mockOrganisationAnswersDAO;
    private OrderDAO mockOrderDAO;

    private String mockLblCharityName;
    private String mockCharityDescription;
    private String mockCharityGroupSupported;
    private String mockTaxableCategory;
    private String mockCharitySize;

    private String billingAddress;
    private Integer selectedDonation;

    // Constructor to initialize the mocked DAOs
    public MockDonatePageController(OrganisationDAO organisationDAO, OrganisationAnswersDAO organisationAnswersDAO, OrderDAO orderDAO) {
        this.mockOrganisationDAO = organisationDAO;
        this.mockOrganisationAnswersDAO = organisationAnswersDAO;
        this.mockOrderDAO = orderDAO;
    }

    // Method to simulate setting fields for testing
    public void setFieldsForTesting(String billingAddress, Integer selectedDonation) {
        this.billingAddress = billingAddress;
        this.selectedDonation = selectedDonation;
    }

    // Method to simulate alerts and successful donations via field checks
    @Override
    public void OnDonatePress() {
        if (billingAddress == null || billingAddress.isEmpty() || selectedDonation == null) {
            mockAlertMessage = "Mock alert: Form incomplete. Please fill in all required fields.";
        } else {
            // Simulate creating an order if the form is valid
            mockAlertMessage = "Donation successful.";
            Order order = new Order(1, Orgid, "2024-10-18T10:00:00", selectedDonation.floatValue(), billingAddress);
            mockOrderDAO.insert(order);
        }
    }

    //// To mock the creation of charity inro on page attempt to retrieve each piece of data and stoe in a list
    @Override
    public void setCharityInfo(String charityName) {
        List<Object> organisationDetails = mockOrganisationDAO.getByName(charityName);
        List<Object> organisationAnswersDetails = mockOrganisationAnswersDAO.getByid((Integer) organisationDetails.get(0));

        // Simulate setting charity information
        mockLblCharityName = (String) organisationDetails.get(1);
        mockCharityDescription = (String) organisationDetails.get(2);
        mockCharityGroupSupported = (String) organisationDetails.get(5);
        mockTaxableCategory = (String) organisationAnswersDetails.get(4);
        mockCharitySize = (String) organisationAnswersDetails.get(2);
    }

    // Getter methods for testing purposes
    public String getMockLblCharityName() {
        return mockLblCharityName;
    }

    public String getMockCharityDescription() {
        return mockCharityDescription;
    }

    public String getMockCharityGroupSupported() {
        return mockCharityGroupSupported;
    }

    public String getMockTaxableCategory() {
        return mockTaxableCategory;
    }

    public String getMockCharitySize() {
        return mockCharitySize;
    }

    public String getMockAlertMessage() {
        return mockAlertMessage;
    }
}