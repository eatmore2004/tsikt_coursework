package com.lib.virtuallibrary.Controllers;

import com.lib.virtuallibrary.Models.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private AnchorPane registrationAnchorPane;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button registerButton;

    @FXML
    private Button signInRedirectButton;

    public void onSignInRedirectClick() throws IOException {
        SceneSwitcher sw = new SceneSwitcher();
        sw.switchScenes(registrationAnchorPane, "log-in.fxml");
    }
}

