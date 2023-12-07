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
import com.lib.virtuallibrary.Models.MessageLabel;
import com.lib.virtuallibrary.Models.Session;
import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 *
 *  AddBookController class. Using to work with add-book.fxml
 *
 */
public class AddBookController implements Initializable {

    @FXML
    private AnchorPane addBookAnchorPain;

    @FXML
    private TextField authorField;

    @FXML
    private Button backRedirectButton;

    @FXML
    private TextField genreField;

    @FXML
    private Label infoLabel;

    @FXML
    private Label toUserLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField pagesField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField yearField;

    @FXML
    private Button addBookButton;

    private IBookService bookService;

    private IUserService userService;

    private ViewChanger viewChanger;

    private MessageLabel messageLabel;

    private User user;

    /**
     * Empty constructor
     */
    public AddBookController() {

    }


    /**
     * initialize method. Using to initialize objects in AddBookController after add-book.fxml was loaded
     * @param url address of fxml file, which initialize the controller
     * @param resourceBundle data which can be used by application
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewChanger = new ViewChanger();
        messageLabel = new MessageLabel();
        bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
        new Session();
        user = Session.getUser().getData();
        showAdminPanelIfAdmin();
    }

    /**
     * onBackRedirectClick method. Using to switch scene to account.fxml
     * @param event is an object of class ActionEvent. Using to describe some event
     *      after backRedirectButton was pressed
     * @throws IOException
     */
    @FXML
    private void onBackRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(addBookAnchorPain, "account.fxml");
    }

    /**
     * onAddBookClick method. Using to add a new book and switch scene to add-book.fxml
     * @param event is an object of class ActionEvent. Using to describe some event
     *     after onBackRedirectButton was pressed
     */
    @FXML
    private void onAddBookClick(ActionEvent event) {
        String title = titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();
        int year = tryParseInt(yearField.getText(), 0);
        int pages = tryParseInt(pagesField.getText(), 0);
        String username;

        Result<UUID> bookResult = bookService.addBook(title, genre, author, year, pages);

        if (!bookResult.getSuccess()) {
            messageLabel.showUnsuccessfulMessage(infoLabel, bookResult.getMessage());
        }
        else {
            messageLabel.showSuccessfulMessage(infoLabel, "Book was successfully added!");
        }

        if (!usernameField.getText().isEmpty()) {
            username = usernameField.getText();
            Result<User> userResult = userService.getByUsername(username);
            if (userResult.getSuccess()) {
                Result<String> bookRentResult = bookService.rentBook(title, userResult.getData().getId());
                if (!bookRentResult.getSuccess()) {
                    messageLabel.showUnsuccessfulMessage(infoLabel, bookRentResult.getMessage());
                }
            } else {
              messageLabel.showUnsuccessfulMessage(infoLabel, userResult.getMessage());
            }
        }
    }

    /**
     * tryParseInt method. Using to parse string value to int
     * @param value string value
     * @param defaultVal default int value. Setting if parsing failed
     * @return parsed value if parsing was succeed or default value if not
     */
    private int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    private void showAdminPanelIfAdmin() {
        if (user.getUsername().equals("admin")) {
            usernameField.setVisible(true);
            toUserLabel.setVisible(true);
        }
    }

}
