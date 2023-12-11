package com.lib.virtuallibrary.Controllers;

import BLL.BookService;
import BLL.UserService;
import BLL_Abstractions.*;
import Core.Models.BaseEntity;
import Core.Models.Book;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;
import com.lib.virtuallibrary.LibraryApplication;
import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * UserListController class. Using to interact with user-list.fxml
 */
public class UserListController implements Initializable {

    @FXML
    private AnchorPane userListAnchorPane;

    @FXML
    private VBox pageVBox;

    @FXML
    private TextField searchField;

    private IBookService bookService;
    private IUserService userService;
    private ViewChanger viewChanger;

    /**
     * initialize method. Using to initialize objects in SampleController after sample.fxml was loaded
     * @param url address of fxml file, which initialize the controller
     * @param resourceBundle data which can be used by application
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
        viewChanger = new ViewChanger();
        List<BaseEntity> users = loadUsersFromDb();
        displayUsers(users);
    }
    
    @FXML
    public void onSearchClick(ActionEvent event) {
        removeAllBooksFromScreen();
        List<User> searchedUsers = getUsersBySearch();
        if (searchedUsers != null) {
            displayUsers(searchedUsers);
        } else {
            removeAllBooksFromScreen();
            List<BaseEntity> users = loadUsersFromDb();
            displayUsers(users);
        }
        searchField.setText("");
    }

    @FXML
    public void onBackRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(userListAnchorPane, "account.fxml");
    }

    /**
     * getBooksBySearch method. Using to find books from user input
     * @return List<Book> if books were found and null if not
     */
    private List<User> getUsersBySearch() {
        Result<List<User>> searchedUsers = userService.getAllByFirstName(searchField.getText());
        if (!searchedUsers.getSuccess()) {
            searchedUsers = userService.getAllByLastName(searchField.getText());
        }
        if (!searchedUsers.getSuccess()) {
            searchedUsers = userService.getAllByEmail(searchField.getText());
        }

        return searchedUsers.getData();
    }

    /**
     * loadUsersFromDb method. Using to load users from database
     * @return List<User> with users
     */
    private List<BaseEntity> loadUsersFromDb() {
        Result<List<BaseEntity>> userResult = userService.getAll();
        return userResult.getData();
    }

    /**
     * displayUsers method. Using to display users on screen 
     * @param users
     */
    private void displayUsers(List<? extends BaseEntity> users) {
        for (var i : users) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(LibraryApplication.class.getResource("user-card.fxml"));
                pageVBox.getChildren().add(fxmlLoader.load());
                UserCardController userCardController = fxmlLoader.getController();
                Result<User> user = userService.getByID(i.getId());
                userCardController.setUserInfo(user.getData());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * removeAllBooksFromScreen method. Using to remove all books from the screen
     */
    private void removeAllBooksFromScreen() {
        pageVBox.getChildren().clear();
    }
}
