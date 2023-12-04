package com.lib.virtuallibrary.Controllers;

import Core.Models.Result;
import Core.Models.User;
import com.lib.virtuallibrary.Models.Session;
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
    private Button addUserButton;

    @FXML
    private Button backRedirectButton;

    @FXML
    private Label nameLabel;

    private ViewChanger viewChanger;

    private User user;

    public AccountController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewChanger = new ViewChanger();
        addUserButton.setVisible(false);
        addUserButton.setDisable(true);
        new Session();
        Result<User> result = Session.getUser();
        if (result.getSuccess()) user = result.getData();
        nameLabel.setText(user.getName() + " " + user.getSurname());

        if (user.getUsername().equals("admin")) {
            addUserButton.setVisible(true);
            addUserButton.setDisable(false);
        }
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

    public void addUserClick(ActionEvent actionEvent) throws IOException {
        viewChanger.switchScenes(accountAnchorPane, "add-user.fxml");
    }
}