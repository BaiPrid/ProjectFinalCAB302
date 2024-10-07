<<<<<<<< HEAD:src/test/java/DBTest/DatabaseTest.java
package DBTest;
========
package TestDB;
>>>>>>>> refs/heads/final-touch-ups-before-11-actual-:src/test/java/TestDB/DatabaseTest.java

import com.example.finalassignmentcab302.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {

    @Test
    public void testConnection() {
        Connection conn = DatabaseConnection.getInstance();
        assertEquals(true, conn != null);
    }
}
