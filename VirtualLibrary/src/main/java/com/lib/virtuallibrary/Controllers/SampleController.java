/**
 * Created by Ihor Rohatiuk on 12/5/23.
 */
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

/**
 *  SampleController class. Using to work with sample.fxml
 */
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

    /**
     * initialize method. Using to initialize objects in SampleController after sample.fxml was loaded
     * @param url address of fxml file, which initialize the controller
     * @param resourceBundle data which can be used by application
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewChanger = new ViewChanger();
        bookService = new BookService(new Repository(Book.class));
        new Session();

        loadAllBooks();
    }

    /**
     * onAccountRedirectClick method. Using to switch scene to account.fxml
     * @param event is an object of class ActionEvent. Using to describe some event
     *    after accountRedirectButton was pressed
     * @throws IOException
     */
    @FXML
    private void onAccountRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(sampleAnchorPane, "account.fxml");
    }

    /**
     * onSearchClick method. Using to show books which were found after searchButton was pressed
     * @param event is an object of class ActionEvent. Using to describe some event
     *   after backRedirectButton was pressed
     */
    @FXML
    private void onSearchClick(ActionEvent event) {
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

    /**
     * loadAllBooks method. Using to load books from database
     */
    private void loadAllBooks() {
        Result<List<BaseEntity>> allBooks = bookService.getAll();
        if (allBooks.getSuccess()) {
            displayBooksOnScreen(allBooks.getData());
        }
    }

    /**
     * getBooksBySearch method. Using to find books from user input
     * @return List<Book> if books were found and null if not
     */
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

    /**
     * displayBooksOnScreen method. Using to display books
     * @param books using to specify which books should be displayed
     */
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

    /**
     * removeAllBooksFromScreen method. Using to remove all books from the screen
     */
    private void removeAllBooksFromScreen() {
        pageVBox.getChildren().clear();
    }
}