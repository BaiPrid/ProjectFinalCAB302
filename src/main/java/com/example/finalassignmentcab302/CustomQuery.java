package com.example.finalassignmentcab302;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomQuery {

    public void ExecuteQuery(String sql){
        Connection connection = DatabaseConnection.getInstance();

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("SQL EXECUTED");
        }
        catch (SQLException exception) {
            System.err.println("SQL NOT EXECUTED");
        }


    }
}
