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

/**
 * UserCardController class. Using to interact with user-card.fxml
 */
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

    /**
     * initialize method. Using to initialize objects in UserCardController after user-card.fxml was loaded
     * @param url address of fxml file, which initialize the controller
     * @param resourceBundle data which can be used by application
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
    }

    /**
     * onDeleteUserClick method. Using to delete user from database deleteUserButton was pressed
     * @param event is an object of class ActionEvent. Using to describe some event
     *   after backRedirectButton was pressed
     */
    @FXML
    private void onDeleteUserClick(ActionEvent event) {
        if (userService.deleteUser(user.getId()).getSuccess()) {
            deleteUserButton.setDisable(true);
            nameLabel.setText("User was deleted");
            uidLabel.setText(" - ");
            usernameLabel.setText(" - ");
            emailLabel.setText(" - ");
        }
    }

    /**
     * setUserInfo method. Using to put in user information into user-card
     * @param user using to specify which user info should be passed in
     */
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

    /**
     * hideDeleteUserButtonIfUserAdmin method. Using to hide deleteUserButton if user is not admin
     */
    private void hideDeleteUserButtonIfUserAdmin() {
        deleteUserButton.setDisable(true);
        deleteUserButton.setVisible(false);
    }
}
