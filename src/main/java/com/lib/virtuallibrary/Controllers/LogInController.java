package com.lib.virtuallibrary.Controllers;

import BLL.BookService;
import BLL.UserService;
import BLL_Abstractions.IBookService;
import BLL_Abstractions.IUserService;
import Core.Models.Book;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;
import com.lib.virtuallibrary.Models.MessageLabels;
import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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
    private final MessageLabels messageLabel;
    private final IUserService userService;
    public LogInController() {
        IBookService bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
        messageLabel = new MessageLabels();
        viewChanger = new ViewChanger();
    }

    public void onRegisterRedirectClick() throws IOException {
        ViewChanger sw = new ViewChanger();
        sw.switchScenes(logInAnchorPane, "registration.fxml");
    }

    public void onSignInClick() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Result<User> userResult = userService.loginUser(username, password);
        if (!userResult.getSuccess()) {
            messageLabel.showUnsuccessfulMessage(infoLabel, userResult.getMessage());
        }
        else {
            viewChanger.switchScenes(logInAnchorPane, "sample.fxml");
        }
    }
}
