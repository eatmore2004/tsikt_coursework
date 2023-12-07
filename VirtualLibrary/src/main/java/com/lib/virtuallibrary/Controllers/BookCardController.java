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
import com.lib.virtuallibrary.Models.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.Date;

/**
 * BookCardController class. Using to work with book-card.fxml
 */
public class BookCardController {

    @FXML
    private Label authorLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label pagesLabel;

    @FXML
    private Button rentThisBookButton;

    @FXML
    private Label rentedByLabel;

    @FXML
    private Label rentedAtLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label yearLabel;

    private IBookService bookService;
    private IUserService userService;
    private final User user;
    private Book book;

    /**
     * BookCardController constructor. Using to define services which will be used after initialization
     */
    public BookCardController() {
        bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
        new Session();
        user = Session.getUser().getData();
    }

    /**
     * onRentThisBookClick method. Using to rent book by user
     * @param event is an object of class ActionEvent. Using to describe some event
     *      after rentThisBookButton was pressed
     */
    @FXML
    void onRentThisBookClick(ActionEvent event) {
        rentThisBookButton.setDisable(true);
        rentedByLabel.setText(user.getEmail());
        rentedAtLabel.setText(new Date().toString());

        bookService.rentBook(book.getTitle(), user.getId());
    }

    /**
     * setBookCardInfo method. Using to set book information into a book-card.fxml
     * @param book using to specify which book information should be shown in a book-card
     */
    public void setBookCardInfo(Book book) {
        this.book = book;
        titleLabel.setText(book.getTitle());
        authorLabel.setText(book.getAuthor());
        genreLabel.setText(book.getGenre());
        pagesLabel.setText(Integer.toString(book.getPages()));
        yearLabel.setText(Integer.toString(book.getYear()));
        if (book.getRentedBy() != null && book.getRentedAt() != null) {
            rentThisBookButton.setDisable(true);
            Result<User> user = userService.getByID(book.getRentedBy());
            rentedByLabel.setText(user.getData().getEmail());
            rentedAtLabel.setText(book.getRentedAt());
        }
    }
}
