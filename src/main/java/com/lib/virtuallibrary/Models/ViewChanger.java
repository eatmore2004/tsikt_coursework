package com.lib.virtuallibrary.Models;

import com.lib.virtuallibrary.Controllers.AccountController;
import com.lib.virtuallibrary.Controllers.SampleController;
import com.lib.virtuallibrary.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class ViewChanger<T> {
    public ViewChanger() {

    }

    public void switchScenes(AnchorPane currentAnchorPane, String newSceneFxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(LibraryApplication.class.getResource(newSceneFxmlPath)));
        AnchorPane newAnchorPane = loader.load();
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(newAnchorPane);
    }

    public void switchScenesWithUserInformationToSample(AnchorPane currentAnchorPane, String newSceneFxmlPath, String userInformation) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(LibraryApplication.class.getResource(newSceneFxmlPath)));
        AnchorPane newAnchorPane = loader.load();
        SampleController sampleController = loader.getController();
        sampleController.setUsername(userInformation);
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(newAnchorPane);
    }

    public void switchScenesWithUserInformationToAccount(AnchorPane currentAnchorPane, String newSceneFxmlPath, String userInformation) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(LibraryApplication.class.getResource(newSceneFxmlPath)));
        AnchorPane newAnchorPane = loader.load();
        AccountController accountController = loader.getController();
        accountController.setNameLabel(userInformation);
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(newAnchorPane);
    }
}
