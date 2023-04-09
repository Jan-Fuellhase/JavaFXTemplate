package org.template;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Jan Füllhase
 *
 * A Game or an App could be here!
 * A small set of Features are in this second window
 */
public class GameFX {
    private final BorderPane rootPane ; // or any other kind of pane, or  Group...

    private String text = "Hallo Welt! Ich bin eine langsam schreibende Textbox. Das finde ich ein sehr cooles Feature.\n Es trägt zum Flair der App bei!";
//    private static int index = 0;
    private Text textBoxLabel = new Text("");

    public GameFX() {  //treat like start method

        rootPane = new BorderPane();

        // build UI, register event handlers, etc etc

        // Erstellen Sie eine HBox, um die Buttons anzuordnen
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        buttonBox.getChildren().addAll(button1, button2);

        // Erstellen Sie eine VBox, um die Textblöcke anzuordnen
        VBox textVBox = new VBox();
        textVBox.setSpacing(10);
        Text text1 = new Text("Text 1");
        Text text2 = new Text("Text 2");
        textVBox.getChildren().addAll(text1, text2);



        // Fügen Sie die TextBox Label zur BorderPane hinzu
//        rootPane.setCenter(textBoxLabel);
//        BorderPane.setMargin(textBoxLabel, new Insets(10));
//
//        // Erstellen Sie die Animation, um den Text langsam anzuzeigen
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> {
//            if (index < text.length()) {
//                textBoxLabel.setText(textBoxLabel.getText() + text.charAt(index));
//                index++;
//            }
//        }));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();


        // Fügen Sie die TextBox Label zur BorderPane hinzu
        rootPane.setCenter(textBoxLabel);
        BorderPane.setMargin(textBoxLabel, new Insets(10));

        slowMovingText(textBoxLabel,text);

        // Fügen Sie die HBox und VBox zur BorderPane hinzu
        rootPane.setTop(buttonBox);
        rootPane.setLeft(textVBox);

        // Setzen Sie den Abstand zwischen den Elementen und den Rand der BorderPane
        BorderPane.setMargin(buttonBox, new Insets(10));
        BorderPane.setMargin(textVBox, new Insets(10));

    }

    public Pane getRootPane() {
        return rootPane ;
    }

    public static void slowMovingText(Text label, String text1){  //geht auch mit label statt text
        final int[] index = {0};
        // Erstellen Sie die Animation, um den Text langsam anzuzeigen
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            if (index[0] < text1.length()) {
                label.setText(label.getText() + text1.charAt(index[0]));
                index[0]++;
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public static void slowMovingText(TextArea label, String text1){  //geht auch mit label statt text
        final int[] index = {0};
        // Erstellen Sie die Animation, um den Text langsam anzuzeigen
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            if (index[0] < text1.length()) {
                label.setText(label.getText() + text1.charAt(index[0]));
                index[0]++;
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    // other methods you may need to access, etc...
}
