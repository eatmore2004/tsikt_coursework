package com.lib.virtuallibrary.Controllers;

import BLL.BookService;
import BLL_Abstractions.IBookService;
import Core.Models.BaseEntity;
import Core.Models.Book;
import Core.Models.Result;
import DAL.Repository.Repository;
import com.lib.virtuallibrary.LibraryApplication;
import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    private ViewChanger viewChanger;

    private IBookService bookService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewChanger = new ViewChanger();
        bookService = new BookService(new Repository(Book.class));

        loadAllBooks();
    }

    @FXML
    private void onAccountRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(sampleAnchorPane, "account.fxml");
    }

    private void loadAllBooks() {
        Result<List<BaseEntity>> allBooks = bookService.getAll();
        if (allBooks.getSuccess()) {
            for (var i : allBooks.getData()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/lib/virtuallibrary/book-card.fxml"));
                    pageFlowPane.getChildren().add(fxmlLoader.load());
                    BookCardController bookCardController = fxmlLoader.getController();
                    Result<Book> book = bookService.getByID(i.getId());
                    bookCardController.setBookInfo(book.getData());
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}