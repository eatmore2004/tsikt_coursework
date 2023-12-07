/**
 * Created by Ihor Rohatiuk on 12/5/23.
 */
package com.lib.virtuallibrary.Models;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *  MessageLabel class. Using to display information inside application
 */
public class MessageLabel {

    /**
     * Empty constructor
     */
    public MessageLabel() {

    }

    /**
     * showUnsuccessfulMessage method. Using to show information after failure operation
     * @param label using to specify on which Label information will be shown
     * @param message using do specify which message will be shown
     */
    public void showUnsuccessfulMessage(Label label, String message) {
        label.setTextFill(Color.RED);
        label.setText(message);
    }

    /**
     * showSuccessfulMessage method. Using to show information after successful operation
     * @param label using to specify on which Label information will be shown
     * @param message using do specify which message will be shown
     */
    public void showSuccessfulMessage(Label label, String message) {
        label.setTextFill(Color.valueOf("#007BFF"));
        label.setText(message);
    }
}
