module com.example.finalassignmentcab302 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.finalassignmentcab302 to javafx.fxml;
    exports com.example.finalassignmentcab302;
}