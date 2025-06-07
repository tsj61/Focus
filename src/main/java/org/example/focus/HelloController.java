package org.example.focus;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;


public class HelloController {
    @FXML
    Label timeLabel;

    @FXML
    Button startButton;

    @FXML
    ListView<String> taskList;

    @FXML
    TextField textField;

    private int seconds = 0;
    boolean isStarted = false;
    private Timeline timeline;

    @FXML
    public void initialize() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    @FXML
    private void onStartButtonClick() {
        if (isStarted) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    @FXML
    private void onAddButtonClick() {
        if (!textField.getText().isEmpty()) {
            taskList.getItems().add(textField.getText());
        }
    }

    private void startTimer() {
        isStarted = true;
        timeline.play();

        startButton.setText("Stop");
    }

    private void updateTimer() {
        seconds++;

        int minutes = seconds / 60;
        int secs = seconds % 60;

        timeLabel.setText(String.format("%02d:%02d", minutes, secs));
    }


    private void stopTimer() {
        isStarted = false;
        resetTime();

        startButton.setText("Start");
        timeLabel.setText("00:00");
    }

    private void resetTime() {
        timeline.stop();
        seconds = 0;
    }
}