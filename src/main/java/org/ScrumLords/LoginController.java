package org.ScrumLords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    public void submitLogin(ActionEvent e) {
        System.out.println("LOGIN!\nusername: " + usernameField.getText() + "\npassword: " + passwordField.getText());
        statusLabel.setText("omg something happened...");
    }
}
