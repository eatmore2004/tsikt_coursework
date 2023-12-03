package com.lib.virtuallibrary.Controllers;

import BLL.BookService;
import BLL.UserService;
import BLL_Abstractions.IBookService;
import BLL_Abstractions.IUserService;
import Core.Models.Book;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    public BookCardController() {
    }

    @FXML
    void onRentThisBookClick(ActionEvent event) {

    }

    public void setBookInfo(Book book) {
        titleLabel.setText(book.getTitle());
        authorLabel.setText(book.getAuthor());
        genreLabel.setText(book.getGenre());
        pagesLabel.setText(Integer.toString(book.getPages()));
        yearLabel.setText(Integer.toString(book.getYear()));
        if (book.getRentedBy() != null && book.getRentedAt() != null) {
            //TODO - MUST BE CHANGED
            IBookService bookService = new BookService(new Repository(Book.class));
            IUserService userService = new UserService(new Repository(User.class), bookService);
            Result<User> user = userService.getByID(book.getRentedBy());
            rentedByLabel.setText(user.getData().getEmail());
            rentedAtLabel.setText(book.getRentedAt());
        }
    }
}
