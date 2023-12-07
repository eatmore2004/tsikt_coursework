/**
 * Created by Ihor Rohatiuk on 12/5/23.
 */
package com.lib.virtuallibrary.Models;

import com.lib.virtuallibrary.LibraryApplication;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

/**
 * ViewChanger class. Using to change scenes in application
 */
public class ViewChanger {

    /**
     * Empty constructor
     */
    public ViewChanger() {

    }

    /**
     * switchScenes method. Using to switch scene
     * @param currentAnchorPane
     * @param newSceneFxmlPath
     * @throws IOException
     */
    public void switchScenes(AnchorPane currentAnchorPane, String newSceneFxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(LibraryApplication.class.getResource(newSceneFxmlPath)));
        AnchorPane newAnchorPane = loader.load();
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(newAnchorPane);
    }

    /**
     * switchSceneWithDelay method. Using to switch scene with delay
     * @param currentAnchorPane
     * @param scenePath
     * @param delaySeconds
     */
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
