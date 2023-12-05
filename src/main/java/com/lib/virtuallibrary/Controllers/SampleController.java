package com.lib.virtuallibrary.Controllers;

import BLL.BookService;
import BLL_Abstractions.IBookService;
import Core.Models.BaseEntity;
import Core.Models.Book;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;
import com.lib.virtuallibrary.LibraryApplication;
import com.lib.virtuallibrary.Models.Session;
import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SampleController implements Initializable{

    @FXML
    private AnchorPane sampleAnchorPane;

    @FXML
    private Button accountRedirectButton;

    @FXML
    private VBox pageVBox;

    @FXML
    private FlowPane pageFlowPane;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    private ViewChanger viewChanger;

    private IBookService bookService;

    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewChanger = new ViewChanger();
        bookService = new BookService(new Repository(Book.class));
        new Session();

        loadAllBooks();
    }

    @FXML
    private void onAccountRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(sampleAnchorPane, "account.fxml");
    }

    @FXML
    private void onSearchClick() {
        removeAllBooksFromScreen();
        List<Book> searchedBooks = getBooksBySearch();
        if (searchedBooks != null) {
            displayBooksOnScreen(searchedBooks);
        } else {
            removeAllBooksFromScreen();
            loadAllBooks();
        }
        searchField.setText("");
    }

    private void loadAllBooks() {
        Result<List<BaseEntity>> allBooks = bookService.getAll();
        if (allBooks.getSuccess()) {
            displayBooksOnScreen(allBooks.getData());
        }
    }

    private List<Book> getBooksBySearch() {
        Result<List<Book>> searchedBooks = bookService.getAllByTitle(searchField.getText());
        if (!searchedBooks.getSuccess()) {
            searchedBooks = bookService.getAllByAuthor(searchField.getText());
        }
        if (!searchedBooks.getSuccess()) {
            searchedBooks = bookService.getAllByGenre(searchField.getText());
        }
        return searchedBooks.getData();
    }

    private void displayBooksOnScreen(List<? extends BaseEntity> books) {
        for (var i : books) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(LibraryApplication.class.getResource("book-card.fxml"));
                pageVBox.getChildren().add(fxmlLoader.load());
                BookCardController bookCardController = fxmlLoader.getController();
                Result<Book> book = bookService.getByID(i.getId());
                bookCardController.setBookCardInfo(book.getData());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void removeAllBooksFromScreen() {
        pageVBox.getChildren().clear();
    }
}