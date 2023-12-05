package com.lib.virtuallibrary.Models;

import com.lib.virtuallibrary.LibraryApplication;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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

    public void switchSceneWithDelay(AnchorPane currentAnchorPane, String scenePath, int delaySeconds) {
        PauseTransition pause = new PauseTransition(Duration.seconds(delaySeconds));
        pause.setOnFinished(event -> {
            try {
                switchScenes(currentAnchorPane, scenePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pause.play();
    }
}
