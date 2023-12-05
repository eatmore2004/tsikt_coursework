package com.lib.virtuallibrary.Models;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MessageLabel {
    public MessageLabel() {

    }

    public void showUnsuccessfulMessage(Label label, String message) {
        label.setTextFill(Color.RED);
        label.setText(message);
    }

    public void showSuccessfulMessage(Label label, String message) {
        label.setTextFill(Color.valueOf("#007BFF"));
        label.setText(message);
    }
}
