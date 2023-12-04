package com.lib.virtuallibrary.Controllers;

import BLL.*;
import BLL_Abstractions.*;
import Core.Models.*;
import DAL.Repository.Repository;
import com.lib.virtuallibrary.Models.MessageLabels;
import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
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

    private final ViewChanger viewChanger;
    private final MessageLabels messageLabel;
    private final IUserService userService;
    private final IBookService bookService;

    public RegistrationController() {
        bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
        messageLabel = new MessageLabels();
        viewChanger = new ViewChanger();
    }

    public void onSignInRedirectClick() throws IOException {
        viewChanger.switchScenes(registrationAnchorPane, "log-in.fxml");
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
            messageLabel.showUnsuccessfulMessage(infoLabel, userResult.getMessage());
        }
        else {
            registerButton.setDisable(true);
            messageLabel.showSuccessfulMessage(infoLabel,"User was successfully added! Redirecting to sign in...");
            switchSceneToLogInWithDelay();
        }
    }

    private void switchSceneToLogInWithDelay() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            try {
                viewChanger.switchScenes(registrationAnchorPane, "log-in.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pause.play();
    }
}

