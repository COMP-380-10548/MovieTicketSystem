module org.ScrumLords {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens org.ScrumLords to javafx.fxml;

    exports org.ScrumLords;
}
