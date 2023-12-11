package com.lib.virtuallibrary.Controllers;

import BLL.BookService;
import BLL.UserService;
import BLL_Abstractions.IBookService;
import BLL_Abstractions.IUserService;
import Core.Models.Book;
import Core.Models.User;
import DAL.Repository.Repository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserCardController implements Initializable {

    @FXML
    private Button deleteUserButton;

    @FXML
    private Label emailLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label uidLabel;

    @FXML
    private Label usernameLabel;

    private IBookService bookService;

    private IUserService userService;

    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
    }

    @FXML
    void onDeleteUserClick(ActionEvent event) {
        if (userService.deleteUser(user.getId()).getSuccess()) {
            deleteUserButton.setDisable(true);
            nameLabel.setText("User was deleted");
            uidLabel.setText(" - ");
            usernameLabel.setText(" - ");
            emailLabel.setText(" - ");
        }
    }

    public void setUserInfo(User user) {
        this.user = user;
        nameLabel.setText(user.getName() + " " + user.getSurname());
        uidLabel.setText(user.getId().toString());
        usernameLabel.setText(user.getUsername());
        emailLabel.setText(user.getEmail());
        if (user.getUsername().equals("admin")) {
            hideDeleteUserButtonIfUserAdmin();
        }
    }

    private void hideDeleteUserButtonIfUserAdmin() {
        deleteUserButton.setDisable(true);
        deleteUserButton.setVisible(false);
    }
}
