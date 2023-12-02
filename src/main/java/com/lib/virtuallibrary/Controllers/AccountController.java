package com.lib.virtuallibrary.Controllers;

import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AccountController {

    @FXML
    private AnchorPane accountAnchorPane;

    @FXML
    private Button logOutRedirectButton;

    @FXML
    private Button backRedirectButton;

    private ViewChanger viewChanger;

    public AccountController() {
        viewChanger = new ViewChanger();
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

}