package com.lib.virtuallibrary.Controllers;

import BLL.BookService;
import BLL.UserService;
import BLL_Abstractions.IBookService;
import BLL_Abstractions.IUserService;
import Core.Models.BaseEntity;
import Core.Models.Book;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;
import com.lib.virtuallibrary.LibraryApplication;
import com.lib.virtuallibrary.Models.MessageLabels;
import com.lib.virtuallibrary.Models.Session;
import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AccountController implements Initializable {

    @FXML
    private AnchorPane accountAnchorPane;

    @FXML
    private Button logOutRedirectButton;

    @FXML
    private Button addUserButton;

    @FXML
    private Button backRedirectButton;

    @FXML
    private Label nameLabel;

    @FXML
    private VBox yourBooksVBox;

    @FXML
    private Label infoLabel;

    @FXML
    private Label yourBooksLabel;

    @FXML
    private ScrollPane yourBooksScrollPane;

    private IBookService bookService;

    private IUserService userService;

    private ViewChanger viewChanger;

    private MessageLabels messageLabel;

    private User user;

    public AccountController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
        messageLabel = new MessageLabels();
        viewChanger = new ViewChanger();
        new Session();
        hideScrollBar();
        disableAdminPanel();
        showUserInfo();
        showAdminPanelIfAdmin();
        loadRentedBooks();
    }
    @FXML
    void onBackRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(accountAnchorPane, "sample.fxml");
    }

    @FXML
    void onLogOutRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(accountAnchorPane, "log-in.fxml");
    }

    @FXML
    void onAddBookRedirectClick() throws IOException {
        viewChanger.switchScenes(accountAnchorPane, "add-book.fxml");
    }

    public void addUserClick(ActionEvent actionEvent) throws IOException {
        viewChanger.switchScenes(accountAnchorPane, "add-user.fxml");
    }

    private void showUserInfo() {
        Result<User> result = Session.getUser();
        if (result.getSuccess()) user = result.getData();
        nameLabel.setText(user.getName() + " " + user.getSurname());
    }

    private void disableAdminPanel() {
        addUserButton.setVisible(false);
        addUserButton.setDisable(true);
    }

    private void showAdminPanelIfAdmin() {
        if (user.getUsername().equals("admin")) {
            addUserButton.setVisible(true);
            addUserButton.setDisable(false);
        }
    }

    private void loadRentedBooks() {
        Result<List<Book>> rentedBooks = bookService.getAllByOwner(user.getId());
        if (rentedBooks.getSuccess()) {
            for (var i : rentedBooks.getData()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(LibraryApplication.class.getResource("rented-book-card.fxml"));
                    yourBooksVBox.getChildren().add(fxmlLoader.load());
                    RentedBookCardController rentedBookCardController = fxmlLoader.getController();
                    Result<Book> book = bookService.getByID(i.getId());
                    rentedBookCardController.setRentedBookCardInfo(book.getData());
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void onReturnAllBooksClick() throws IOException {
        Result<String> bookResult = bookService.returnAllByOwner(user.getId());
        if (!bookResult.getSuccess()) {
            messageLabel.showUnsuccessfulMessage(infoLabel, bookResult.getMessage());
        } else {
            viewChanger.switchScenes(accountAnchorPane, "account.fxml");
        }
    }

    private void hideScrollBar() {
        yourBooksScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        yourBooksScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
}