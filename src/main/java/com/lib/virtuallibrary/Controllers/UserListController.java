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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserListController implements Initializable {

    @FXML
    private AnchorPane userListAnchorPane;

    @FXML
    private VBox pageVBox;

    private IBookService bookService;
    private IUserService userService;
    private ViewChanger viewChanger;
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

    }

    @FXML
    public void onBackRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(userListAnchorPane, "account.fxml");
    }

    private List<BaseEntity> loadUsersFromDb() {
        Result<List<BaseEntity>> userResult = userService.getAll();
        return userResult.getData();
    }

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
}
