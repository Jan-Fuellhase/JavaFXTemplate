package org.template;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;


public class Extratools {


    public static void main(String[] args) {
        //testing
    }

    public static void playMusikInBackground() {
        try {
            String musicFile = "sound.mp3";     // put in oberstes root enchro2 directory, bzw in zip in bin

            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                public void run() {
                    mediaPlayer.seek(Duration.ZERO);
                }
            });
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Sound skipped. Some error occurred. Please move the sound.mp3 file in folder");
//            Enchro2.ergebnistext.setText("Sound skipped. Some error occurred. Please move the sound.mp3 file in /bin folder");
        }
    }
}
