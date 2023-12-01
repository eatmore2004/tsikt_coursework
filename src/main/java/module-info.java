module com.lib.virtuallibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.lib.virtuallibrary to javafx.fxml;
    exports com.lib.virtuallibrary;
    exports com.lib.virtuallibrary.Controllers;
    opens com.lib.virtuallibrary.Controllers to javafx.fxml;
}