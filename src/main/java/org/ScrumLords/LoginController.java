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
        
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        System.out.println("LOGIN!\nusername: " + username + "\npassword: " + password); 
        statusLabel.setText("omg something happened...");

        // TODO: add actual login validation
        if (username.equals("admin") && password.equals("admin")) {
            // this should switch to the homepage but I dont want to reimplement the movie details stuff yet
            SceneManager.switchToScene("MovieGallery.fxml", (MovieGalleryController controller) -> {
                controller.setUsername(username);
            });
        }
    }
}
