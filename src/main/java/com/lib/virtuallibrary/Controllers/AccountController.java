package com.lib.virtuallibrary.Controllers;

import com.lib.virtuallibrary.Models.ViewChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {

    @FXML
    private AnchorPane accountAnchorPane;

    @FXML
    private Button logOutRedirectButton;

    @FXML
    private Button addUser;

    @FXML
    private Button backRedirectButton;

    @FXML
    private Label nameLabel;

    private ViewChanger viewChanger;

    private String username;

    public AccountController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewChanger = new ViewChanger();
    }
    @FXML
    void onBackRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenesWithUserInformationToSample(accountAnchorPane, "sample.fxml", username);
    }

    @FXML
    void onLogOutRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(accountAnchorPane, "log-in.fxml");
    }

    @FXML
    void onAddBookRedirectClick() throws IOException {
        viewChanger.switchScenes(accountAnchorPane, "add-book.fxml");
    }

    public void setNameLabel(String name) {
        nameLabel.setText(name);
    }

    public void addUserClick(ActionEvent actionEvent) {

    }
}