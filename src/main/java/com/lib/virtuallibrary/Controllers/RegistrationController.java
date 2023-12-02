package com.lib.virtuallibrary.Controllers;

import BLL.*;
import BLL_Abstractions.*;
import Core.Models.*;
import DAL.Repository.Repository;
import com.lib.virtuallibrary.Models.SceneSwitcher;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private TextField usernameField;

    @FXML
    private Button registerButton;

    @FXML
    private Button signInRedirectButton;

    @FXML
    private Label infoLabel;

    private final IUserService userService;
    public RegistrationController() {
        IBookService bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
    }

    public void onSignInRedirectClick() throws IOException {
        SceneSwitcher sw = new SceneSwitcher();
        sw.switchScenes(registrationAnchorPane, "log-in.fxml");
    }

    public void onRegisterClick() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        Result<UUID> userResult = userService
                .registerUser(firstName, lastName, username, email, password);
        if (!userResult.getSuccess()) {
            showUnsuccessfulMessage(userResult.getMessage());
        }
        else {
            registerButton.setDisable(true);
            showSuccessfulMessage(userResult.getMessage());
            switchSceneToLogInWithDelay();
        }
    }

    private void switchSceneToLogInWithDelay() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            SceneSwitcher sw = new SceneSwitcher();
            try {
                sw.switchScenes(registrationAnchorPane, "log-in.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pause.play();
    }

    private void showUnsuccessfulMessage(String message) {
        infoLabel.setTextFill(Color.RED);
        infoLabel.setText(message);
    }

    private void showSuccessfulMessage(String message) {
        infoLabel.setTextFill(Color.valueOf("#007BFF"));
        infoLabel.setText("User was successfully added! Redirecting to sign in.....");
    }
}

