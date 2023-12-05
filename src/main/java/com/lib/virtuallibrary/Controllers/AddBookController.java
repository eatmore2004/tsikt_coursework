package com.lib.virtuallibrary.Controllers;

import BLL.BookService;
import BLL_Abstractions.IBookService;
import Core.Models.Book;
import Core.Models.Result;
import DAL.Repository.Repository;
import com.lib.virtuallibrary.Models.MessageLabel;
import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.UUID;

public class AddBookController {

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
    private TextField pagesField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField yearField;

    @FXML
    private Button addBookButton;

    private IBookService bookService;

    private final ViewChanger viewChanger;

    private final MessageLabel messageLabel;

    public AddBookController() {
        viewChanger = new ViewChanger();
        messageLabel = new MessageLabel();
        bookService = new BookService(new Repository(Book.class));
    }

    @FXML
    void onBackRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(addBookAnchorPain, "account.fxml");
    }

    @FXML
    void onAddBookClick(ActionEvent event) {
        String title = titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();
        int year = tryParseInt(yearField.getText(), 0);
        int pages = tryParseInt(pagesField.getText(), 0);

        Result<UUID> bookResult = bookService.addBook(title, genre, author, year, pages);

        if (!bookResult.getSuccess()) {
            messageLabel.showUnsuccessfulMessage(infoLabel, bookResult.getMessage());
        }
        else {
            messageLabel.showSuccessfulMessage(infoLabel, "Book was successfully added!");
        }
    }

    public int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }
}
