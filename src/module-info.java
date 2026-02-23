module com.citybookshop {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.citybookshop to javafx.graphics;
    opens com.citybookshop.controller to javafx.fxml;
    opens com.citybookshop.view to javafx.fxml;
    exports com.citybookshop;

}
