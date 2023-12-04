package com.lib.virtuallibrary.Models;

import com.lib.virtuallibrary.Controllers.AccountController;
import com.lib.virtuallibrary.Controllers.SampleController;
import com.lib.virtuallibrary.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class ViewChanger {
    public ViewChanger() {

    }

    public void switchScenes(AnchorPane currentAnchorPane, String newSceneFxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(LibraryApplication.class.getResource(newSceneFxmlPath)));
        AnchorPane newAnchorPane = loader.load();
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(newAnchorPane);
    }
}
