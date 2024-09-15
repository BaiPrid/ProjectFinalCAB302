module com.example.finalassignmentcab302 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.finalassignmentcab302 to javafx.fxml;
    exports com.example.finalassignmentcab302;
    exports com.example.finalassignmentcab302.dao;
    opens com.example.finalassignmentcab302.dao to javafx.fxml;
    exports com.example.finalassignmentcab302.Tables;
    opens com.example.finalassignmentcab302.Tables to javafx.fxml;
    exports com.example.finalassignmentcab302.Controllers;
    opens com.example.finalassignmentcab302.Controllers to javafx.fxml;
}