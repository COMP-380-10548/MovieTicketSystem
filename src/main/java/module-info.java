module org.ScrumLords {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires io.github.cdimascio.dotenv.java;

    opens org.ScrumLords to javafx.fxml;
    opens org.ScrumLords.controller to javafx.fxml;

    exports org.ScrumLords;
}
