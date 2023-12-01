package com.lib.virtuallibrary.Controllers;

import com.lib.virtuallibrary.Models.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LogInController {

    @FXML
    private AnchorPane logInAnchorPane;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerRedirectButton;

    @FXML
    private Button signInButton;

    public void onRegisterRedirectClick() throws IOException {
        SceneSwitcher sw = new SceneSwitcher();
        sw.switchScenes(logInAnchorPane, "registration.fxml");
    }
}
