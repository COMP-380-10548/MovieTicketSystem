module org.ScrumLords {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.ScrumLords to java.fxml;

    exports org.ScrumLords;
}
