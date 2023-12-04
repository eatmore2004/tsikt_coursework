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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.UUID;

public class AddUserController {

    @FXML
    private AnchorPane addUserAnchorPain;

    @FXML
    private Button addUserButton;

    @FXML
    private Button backRedirectButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    private IBookService bookService;
    private IUserService userService;
    private ViewChanger viewChanger;
    private MessageLabels messageLabel;

    public AddUserController() {
        bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
        viewChanger = new ViewChanger();
        messageLabel = new MessageLabels();
    }

    @FXML
    void onAddUserClick(ActionEvent event) {
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
            messageLabel.showSuccessfulMessage(infoLabel,"User was successfully added!");
        }
    }

    @FXML
    void onBackRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(addUserAnchorPain, "account.fxml");
    }

}
