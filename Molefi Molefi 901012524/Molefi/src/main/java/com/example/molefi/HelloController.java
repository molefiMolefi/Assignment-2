package com.example.molefi;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ImageView back,forward,next,play,pause,stop,playback;
    @FXML
    private AnchorPane pane;
    @FXML
    private Slider progress,slider;

    @FXML
    private MediaView view;
    private MediaPlayer player;
    @FXML
    private Media media;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String video = getClass().getResource("sot.mp4").toExternalForm();
        media = new Media(video);
        player = new MediaPlayer(media);
        view.setMediaPlayer(player);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                player.setVolume(slider.getValue() * 10);
            }
        });

        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                progress.setValue(t1.toSeconds());
            }
        });
        progress.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek(Duration.seconds(progress.getValue()));
            }
        });
        progress.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek(Duration.seconds(progress.getValue()));
            }
        });
        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration total= media.getDuration();
                progress.setMax(total.toSeconds());
            }
        });

    }
    public void play(){
        player.play();
    }
    public void pause(){
        player.pause();
    }
    public void stop() {player.stop();}
    public void playnext() {
        player.seek(player.getCurrentTime().add(Duration.seconds(10)));
        System.out.println("+10");
    }
    public void playback() {
        player.seek(player.getCurrentTime().add(Duration.seconds(-10)));
        System.out.println("-10");
    }

}