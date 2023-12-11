/**
 * Created by Ihor Rohatiuk on 12/5/23.
 */
package com.lib.virtuallibrary.Controllers;

import BLL.BookService;
import BLL.UserService;
import BLL_Abstractions.IBookService;
import BLL_Abstractions.IUserService;
import Core.Models.Book;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;
import com.lib.virtuallibrary.LibraryApplication;
import com.lib.virtuallibrary.Models.MessageLabel;
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

/**
 * AccountController class. Using to work with account.fxml
 */
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
    private Button returnAllBooksButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label noBooksFoundLabel;

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

    private MessageLabel messageLabel;

    private User user;

    /**
     * Empty AccountController constructor
     */
    public AccountController() {

    }

    /**
     * initialize method. Using to initialize objects in AccountController after account.fxml was loaded
     * @param url address of fxml file, which initialize the controller
     * @param resourceBundle data which can be used by application
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
        messageLabel = new MessageLabel();
        viewChanger = new ViewChanger();
        new Session();
        hideScrollBar();
        disableAdminPanel();
        showUserInfo();
        showAdminPanelIfAdmin();
        loadRentedBooks();
    }

    /**
     * onBackRedirectClick method. Using to switch scene to sample
     * @param event - object of class ActionEvent. Using to describe some event
     *             after backRedirectButton was pressed
     * @throws IOException
     */
    @FXML
    private void onBackRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(accountAnchorPane, "sample.fxml");
    }

    /**
     * onLogOutRedirectClick method. Using to log out user and switch scene to Sign in
     * @param event - object of class ActionEvent. Using to describe some event
     *             after logOutRedirectButton was pressed
     * @throws IOException
     */
    @FXML
    private void onLogOutRedirectClick(ActionEvent event) throws IOException {
        Session.logout();
        viewChanger.switchScenes(accountAnchorPane, "log-in.fxml");
    }

    /**
     * onAddBookRedirectClick method. Using to switch scene to Add book
     * @param event - object of class ActionEvent. Using to describe some event
     *             after addBookRedirectButton was pressed
     * @throws IOException
     */
    @FXML
    private void onAddBookRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(accountAnchorPane, "add-book.fxml");
    }

    /**
     * onReturnAllBooksClick method. Using to return all books which user had
     * @param event - object of class ActionEvent. Using to describe some event
     *             after returnAllBooksButton was pressed
     * @throws IOException
     */
    @FXML
    private void onReturnAllBooksClick(ActionEvent event) throws IOException {
        Result<String> bookResult = bookService.returnAllByOwner(user.getId());
        if (!bookResult.getSuccess()) {
            messageLabel.showUnsuccessfulMessage(infoLabel, bookResult.getMessage());
        } else {
            viewChanger.switchScenes(accountAnchorPane, "account.fxml");
        }
    }

    /**
     * addUserClick method. Using to add a new user (only by admin) and switch scene to Add user
     * @param event - object of class ActionEvent. Using to describe some event
     *             after addUserButton was pressed
     * @throws IOException
     */
    @FXML
    private void addUserClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(accountAnchorPane, "add-user.fxml");
    }

    /**
     * onRefreshClick method. Using to refresh current page
     * @param event - object of class ActionEvent. Using to describe some event
     *             after addUserButton was pressed
     * @throws IOException
     */
    @FXML
    private void onRefreshClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(accountAnchorPane, "account.fxml");
    }

    /**
     * showUserInfo method. Using to display user information in user's account
     */
    private void showUserInfo() {
        Result<User> result = Session.getUser();
        if (result.getSuccess()) user = result.getData();
        nameLabel.setText(user.getName() + " " + user.getSurname());
    }

    /**
     * disableAdminPanel method. Using to disable buttons which only admin can use
     */
    private void disableAdminPanel() {
        addUserButton.setVisible(false);
        addUserButton.setDisable(true);
    }

    /**
     * showAdminPanelIfAdmin method. Using to enable admin panel if user is admin
     */
    private void showAdminPanelIfAdmin() {
        if (user.getUsername().equals("admin")) {
            addUserButton.setVisible(true);
            addUserButton.setDisable(false);
        }
    }

    /**
     * loadRentedBooks method. Using to load from db all books which user rent
     */
    private void loadRentedBooks() {
        Result<List<Book>> rentedBooks = bookService.getAllByOwner(user.getId());
        if (rentedBooks.getData() != null) {
            showRentedBooks(rentedBooks);
        } else {
            returnAllBooksButton.setDisable(true);
            noBooksFoundLabel.setText("It looks like you haven't rented any books yet.");
        }
    }

    /**
     * hideScrollBar method. Using to hide the scroll-bar (only for better visual effect)
     */
    private void hideScrollBar() {
        yourBooksScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        yourBooksScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    /**
     * showRentedBooks method. Using to display all books which user rent
     * @param rentedBooks is using to define which books should be shown
     */
    private void showRentedBooks(Result<List<Book>> rentedBooks) {
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