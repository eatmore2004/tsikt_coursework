package com.lib.virtuallibrary.Controllers;

import BLL.BookService;
import BLL.UserService;
import BLL_Abstractions.IBookService;
import BLL_Abstractions.IUserService;
import Core.Models.Book;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;
import com.lib.virtuallibrary.Models.MessageLabel;
import com.lib.virtuallibrary.Models.Session;
import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * LogInController class. Using to work with log-in.fxml
 */
public class LogInController {

    @FXML
    private AnchorPane logInAnchorPane;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerRedirectButton;

    @FXML
    private Button signInButton;

    @FXML
    private Label infoLabel;

    private final ViewChanger viewChanger;
    private final MessageLabel messageLabel;
    private final IUserService userService;
    private AccountController accountController;

    /**
     * LogInController constructor. Using to define services which will be used after initialization
     */
    public LogInController() {
        IBookService bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
        messageLabel = new MessageLabel();
        viewChanger = new ViewChanger();
    }

    /**
     * onRegisterRedirectClick method. Using to switch scene to registration.fxml
     * @param event is an object of class ActionEvent. Using to describe some event
     *     after registerRedirectButton was pressed
     * @throws IOException
     */
    @FXML
    public void onRegisterRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(logInAnchorPane, "registration.fxml");
    }

    /**
     * onSignInClick method. Using to sign in user and switch to sample.fxml
     * @param event is an object of class ActionEvent. Using to describe some event
     *  after signInButton was pressed
     * @throws IOException
     */
    @FXML
    public void onSignInClick(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Result<User> userResult = userService.loginUser(username, password);
        if (!userResult.getSuccess()) {
            messageLabel.showUnsuccessfulMessage(infoLabel, userResult.getMessage());
        }
        else {
            new Session();
            Session.login(userResult.getData().getId());
            viewChanger.switchScenes(logInAnchorPane, "sample.fxml");
        }
    }
}
