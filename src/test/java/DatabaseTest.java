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
