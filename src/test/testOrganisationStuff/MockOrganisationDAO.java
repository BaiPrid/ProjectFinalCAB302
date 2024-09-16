package testOrganisationStuff;

import com.example.finalassignmentcab302.Tables.Organisation;
import com.example.finalassignmentcab302.dao.OrganisationDAO;

import java.util.ArrayList;
import java.util.List;

public class MockOrganisationDAO extends OrganisationDAO {
    private final List<Organisation> organisationList = new ArrayList<>();
    private int autoIncrementedId = 1;

    @Override
    public void insert(Organisation organisation) {
        organisation = new Organisation(
                autoIncrementedId++,
                organisation.getName(),
                organisation.getGroupSupported(),
                organisation.getDescription(),
                organisation.getImgPath(),
                organisation.getEmail(),
                organisation.getUserName(),
                organisation.getPassWord()
        );
        organisationList.add(organisation);
    }

    @Override
    public void update(Organisation organisation) {
        for (int i = 0; i < organisationList.size(); i++) {
            if (organisationList.get(i).getId() == organisation.getId()) {
                organisationList.set(i, organisation);
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        organisationList.removeIf(organisation -> organisation.getId() == id);
    }

    @Override
    public List<Organisation> getAll() {
        return new ArrayList<>(organisationList);
    }
}