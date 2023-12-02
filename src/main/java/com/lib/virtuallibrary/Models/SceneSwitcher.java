package com.lib.virtuallibrary.Models;

import com.lib.virtuallibrary.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitcher {
    public void switchScenes(AnchorPane currentAnchorPane, String newSceneFxmlPath) throws IOException {
        AnchorPane newAnchorPane = FXMLLoader
                .load(Objects.requireNonNull(LibraryApplication.class.getResource(newSceneFxmlPath)));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(newAnchorPane);
    }
}
