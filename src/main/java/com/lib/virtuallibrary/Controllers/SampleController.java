package com.lib.virtuallibrary.Controllers;

import com.lib.virtuallibrary.Models.ViewChanger;
import Core.Models.*;
import BLL.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SampleController {

    @FXML
    private AnchorPane sampleAnchorPane;

    @FXML
    private Button accountRedirectButton;

    private ViewChanger viewChanger;

    public SampleController() {
        viewChanger = new ViewChanger();
    }
/*    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        User user = UserService.class.;
    }*/

    @FXML
    void onAccountRedirectClick(ActionEvent event) throws IOException {
        viewChanger.switchScenes(sampleAnchorPane, "account.fxml");
    }
}