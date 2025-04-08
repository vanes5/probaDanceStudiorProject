module com.example.dancestudiodesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.dancestudiodesktop to javafx.fxml;
    exports com.example.dancestudiodesktop;
}