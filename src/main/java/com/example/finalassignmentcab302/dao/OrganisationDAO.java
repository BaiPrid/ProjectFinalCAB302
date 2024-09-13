package com.example.finalassignmentcab302.dao;

import com.example.finalassignmentcab302.DatabaseConnection;
import com.example.finalassignmentcab302.Tables.Organisation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO for organisation main table

public class OrganisationDAO {
    private Connection connection;

    public OrganisationDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS organisations ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "name VARCHAR NOT NULL, "
                            + "description VARCHAR NOT NULL, "
                            + "imgPath VARCHAR NOT NULL, "
                            + "email VARCHAR NOT NULL, "
                            + "groupSupported VARCHAR NOT NULL, "
                            + "userName VARCHAR NOT NULL, "
                            + "passWord VARCHAR NOT NULL"
                            + ")"
            );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void insert(Organisation organisation) {
        try {
            PreparedStatement insertOrganisation = connection.prepareStatement(
                    "INSERT INTO organisations (name, description, imgPath, email, groupSupported, userName, passWord) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            insertOrganisation.setString(1, organisation.getName());
            insertOrganisation.setString(2, organisation.getDescription());
            insertOrganisation.setString(3, organisation.getImgPath());
            insertOrganisation.setString(4, organisation.getEmail());
            insertOrganisation.setString(5, organisation.getGroupSupported());
            insertOrganisation.setString(6, organisation.getUserName());
            insertOrganisation.setString(7, organisation.getPassWord());
            insertOrganisation.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void update(Organisation organisation) {
        try {
            PreparedStatement updateOrganisation = connection.prepareStatement(
                    "UPDATE users SET name = ?, description = ?, imgPath = ?, email = ?, groupSupported = ?, userName = ?, passWord = ? " +
                            "WHERE id = ?"
            );
            // note change where id = to username and password for forget password
            updateOrganisation.setString(1, organisation.getName());
            updateOrganisation.setString(2, organisation.getDescription());
            updateOrganisation.setString(3, organisation.getImgPath());
            updateOrganisation.setString(4, organisation.getEmail());
            updateOrganisation.setString(5, organisation.getGroupSupported());
            updateOrganisation.setString(6, organisation.getUserName());
            updateOrganisation.setString(7, organisation.getPassWord());
            updateOrganisation.setInt(8, organisation.getId());
            updateOrganisation.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement deleteOrganisation = connection.prepareStatement("DELETE FROM organisations WHERE id = ?");
            deleteOrganisation.setInt(1, id);
            deleteOrganisation.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Organisation> getAll() {
        List<Organisation> allOrganisation = new ArrayList<>();
        try{
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM organisations");
            while (rs.next()){
                allOrganisation.add(
                        new Organisation(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getString("imgPath"),
                                rs.getString("email"),
                                rs.getString("groupSupported"),
                                rs.getString("userName"),
                                rs.getString("passWord")
                        )
                );
            }
        }catch (SQLException ex) {
            System.err.println(ex);
        }
        return allOrganisation;
    }

    public Organisation getByLogin(int id) {
        try {
            PreparedStatement getOrganisation = connection.prepareStatement("SELECT * FROM organisations WHERE id = ?");
            getOrganisation.setInt(1, id);
            ResultSet rs = getOrganisation.executeQuery();
            if (rs.next()) {
                return new Organisation(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("imgPath"),
                        rs.getString("email"),
                        rs.getString("groupSupported"),
                        rs.getString("userName"),
                        rs.getString("passWord")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}