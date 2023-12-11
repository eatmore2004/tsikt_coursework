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
import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.Date;

/**
 * RentedBookCardController class. Using to work with rented-book-card.fxml
 */
public class RentedBookCardController {

    @FXML
    private Label authorLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label pagesLabel;

    @FXML
    private Button returnBookButton;

    @FXML
    private Label rentedAtLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label yearLabel;
    private final IBookService bookService;
    private final IUserService userService;
    private final User user;
    private Book book;

    /**
     * RentedBookCardController constructor. Using to define services which will be used after initialization
     */
    public RentedBookCardController() {
        bookService = new BookService(new Repository(Book.class));
        userService = new UserService(new Repository(User.class), bookService);
        new Session();
        user = Session.getUser().getData();
    }

    /**
     * onReturnBookClick method. Using to return book which was rented by user
     * @param event is an object of class ActionEvent. Using to describe some event
     *    after returnBookButton was pressed
     */
    @FXML
    private void onReturnBookClick(ActionEvent event) {
        returnBookButton.setDisable(true);
        returnBookButton.setText("Book returned");
        bookService.returnBook(book.getId(), user.getId());
    }

    /**
     * setRentedBookCardInfo method. Using to set book information into a rented-book-card.fxml
     * @param book using to specify which book information should be shown in a book-card
     */
    public void setRentedBookCardInfo(Book book) {
        this.book = book;
        titleLabel.setText(book.getTitle());
        authorLabel.setText(book.getAuthor());
        genreLabel.setText(book.getGenre());
        pagesLabel.setText(Integer.toString(book.getPages()));
        yearLabel.setText(Integer.toString(book.getYear()));
        rentedAtLabel.setText(book.getRentedAt());
    }
}
