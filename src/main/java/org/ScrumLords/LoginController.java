package org.ScrumLords;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class LoginController {
    
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label statusLabel;

    public void submitLogin(ActionEvent e) {
        
        String username = usernameField.getText();
        String password = passwordField.getText();

        loginButton.setDisable(true);
        statusLabel.setText("Status: validating login...");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));

        // Kind of pointless login delay but added cus whynot
        pause.setOnFinished(event -> {
            // TODO: add actual login validation
            if (username.equals("admin") && password.equals("admin")) {
                SceneManager.switchToScene("MainPage.fxml", (MainPageController controller) -> {
                    controller.setUsername(username);
                });
            } else {
                loginButton.setDisable(false);
                statusLabel.setText("Status: invalid login.");
            }
        });
        
       pause.play();
    }
}