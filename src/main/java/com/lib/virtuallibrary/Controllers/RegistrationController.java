/**
 * Created by Ihor Rohatiuk on 12/5/23.
 */
package com.lib.virtuallibrary.Controllers;

import BLL.*;
import BLL_Abstractions.*;
import Core.Models.*;
import DAL.Repository.Repository;
import com.lib.virtuallibrary.Models.MessageLabel;
import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.UUID;

/**
 * RegistrationController class. Using to work with registration.fxml
 */
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
    private final MessageLabel messageLabel;
    private final IUserService userService;
    private final IBookService bookService;

    /**
     * RegistrationController constructor. Using to define services which will be used after initialization
     */
    public RegistrationController() {
        bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
        messageLabel = new MessageLabel();
        viewChanger = new ViewChanger();
    }

    /**
     *  onSignInRedirectClick method. Using to switch scene to sign-in.fxml
     * @param event is an object of class ActionEvent. Using to describe some event
     *  after signInRedirectButton was pressed
     * @throws IOException
     */
    @FXML
    public void onSignInRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(registrationAnchorPane, "log-in.fxml");
    }

    /**
     * onRegisterClick method. Using to register user
     * @param event is an object of class ActionEvent. Using to describe some event
     *   after signInRedirectButton was pressed
     */
    public void onRegisterClick(ActionEvent event) {
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
            viewChanger.switchSceneWithDelay(registrationAnchorPane, "log-in.fxml", 2);
        }
    }
}

