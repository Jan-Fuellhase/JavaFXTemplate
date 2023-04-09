package org.template;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.util.Scanner;

/**
 * @author Jan FÜllhase
 *
 * https://www.youtube.com/watch?v=3lNQhbg1y4Y das hier funzt mit jlink
 *
 * <p>
 * modify bat: https://stackoverflow.com/questions/54132781/how-to-run-jlink-generated-java-runtime-image-without-cmd-window
 * <p>
 * or do batch to exe converter
 * <p>
 * developmed in Java17 language level 17, IntelliJ
 * with Help from chatGPT
 */
//only works in folder which is asserted in pom -> javafx:compile,run
public class JavaFXApp extends Application {

    static boolean passwortmode = false;
    static boolean adminmode = false;
    static boolean AES256mode = true;
    static boolean AES128mode = false;


    Scene scene;
    //for modifying in methods
    static Button buttontropfinmg = new Button("Anzahl Milligramm berechnen");
    static TextArea text1 = new TextArea();
    static TextArea text2 = new TextArea();
    static TextArea text3 = new TextArea();
    static TextArea ergebnistext = new TextArea();

    static Text fragegeburtsort = null;
    static Text fragepasswort = null;

    static TextField textfeld = new TextField();
    static TextField textfeld2 = new TextField();
    static TextField textfeld3 = new TextField();

    ScrollPane scrollPane = new ScrollPane();

    @Override
    public void start(Stage stage) {

        //play musik sound.mp3 in bin folder
//        Extratools.playMusikInBackground();

        Button buttonverschlüsseln = new Button("\uD83D\uDD10 Button1");
        Button buttonentschlüsseln = new Button("\uD83D\uDD11 Button2");
        Button buttonsupport = new Button("❔ Support");
        Button buttonexit = new Button("❎ Exit App");
        Button buttonclipboard = new Button("\uD83D\uDCCB Copy Output Text");
        Button buttonclearinputtext = new Button("⌫ Clear Input Text");
        ToggleButton buttonpasswortmodus = new ToggleButton("\uD83D\uDD12 Activate Items");
        buttonpasswortmodus.setEffect(new DropShadow());


        buttonverschlüsseln.setOnAction(b -> {
            try {
                if (AES256mode)
                    buttonhandler(text1.getText(), textfeld.getText(), textfeld2.getText(), 1);
                if (AES128mode)
                    buttonhandler(text1.getText(), textfeld.getText(), textfeld2.getText(), 1128);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        buttonentschlüsseln.setOnAction(b -> {
            try {
                if (AES256mode)
                    buttonhandler(text1.getText(), textfeld.getText(), textfeld2.getText(), 2);
                if (AES128mode)
                    buttonhandler(text1.getText(), textfeld.getText(), textfeld2.getText(), 2128);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonsupport.setOnAction(i -> adminmode = true);
        buttonsupport.setOnAction(b -> ergebnistext.setText("please support me im stupid :("));
        buttonsupport.setOnContextMenuRequested(i -> text1.setText(""));

        buttonclearinputtext.setOnAction(i -> text1.setText(""));

        buttonexit.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        // copy to clipboard
        buttonclipboard.setOnAction(b -> {
            try {
                copyToClipboard();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Erstelle ein Dropdown-Menü mit verschiedenen Optionen für 128 und 256
        ComboBox<String> dropdown256 = new ComboBox<>();
        dropdown256.getItems().addAll(
                "Option1",
                "Option2",
                "Option3"
        );
        dropdown256.setValue("Option1");


        // Füge einen Event-Handler hinzu, um auf die Auswahl der Optionen zu reagieren
        dropdown256.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedOption = dropdown256.getValue();
                if (selectedOption.equals("Option1")) {
                    // Code für Option 1
                    System.out.println("Code für Option 1 wird ausgeführt.");
                    AES128mode = false;
                    AES256mode = true;
                } else if (selectedOption.equals("Option2")) {
                    // Code für Option 2
                    System.out.println("Code für Option 2 wird ausgeführt.");
                    AES128mode = true;
                    AES256mode = false;
                } else if (selectedOption.equals("Option3")) {
                    // Code für Option 3
                    System.out.println("Code für Option 3 wird ausgeführt.");
                    adminmode = true;
                    AES256mode = true;
                    buttonsupport.fire();
                }
            }
        });

        text1.setWrapText(true);
        ergebnistext.setEditable(false);
        ergebnistext.setWrapText(true);
        ergebnistext.setText("--- Willkommen bei dieser App! ---\n--- Hoffentlich hast du einen schönen Tag! ---\n--- Wie schön diese App doch aussieht, wer die wohl gemacht hat? ---       \n\n\n\n                                                       ¯\\_(ツ)_/¯");


        var copyright = new Text("");  //"                      © Jan Füllhase 2023"
        Text headline = new Text("Text eingeben:");
        fragegeburtsort = new Text("Jan's Geburtsort:");
        fragepasswort = new Text("Passwort:");
        Text emptytext = new Text("\n\n\n");  //7 times originally
        Text emptytext1 = new Text("\n\n\n\n\n\n");
        Text emptytext2 = new Text("");
        headline.setFont(new Font(16));
        fragegeburtsort.setFont(new Font(16));
        fragepasswort.setFont(new Font(16));
//        copyright.setTextAlignment(TextAlignment.JUSTIFY);

        passwortvisible(false, false);
        buttonpasswortmodus.setOnAction(b -> passwortvisible(true, true));

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            GameFX game = new GameFX();
            stage.getScene().setRoot(game.getRootPane());
        });


        //Buttons hinzufügen
        VBox box = new VBox(10);
        VBox box2 = new VBox(10);
        VBox boxpisscine = new VBox(10);
        HBox SupportExitBox = new HBox(10);
        SupportExitBox.getChildren().add(buttonexit);
        SupportExitBox.getChildren().add(buttonsupport);


        box.getChildren().add(fragegeburtsort);
        box.getChildren().add(textfeld);
        box.getChildren().add(fragepasswort);
        box.getChildren().add(textfeld2);
        box.getChildren().add(buttonverschlüsseln);
        box.getChildren().add(buttonentschlüsseln);


        boxpisscine.getChildren().add(emptytext);
        boxpisscine.getChildren().add(buttonclearinputtext);
        boxpisscine.getChildren().add(buttonclipboard);
        boxpisscine.getChildren().add(SupportExitBox);
        boxpisscine.getChildren().add(copyright);


        HBox HeadlingandDropbownBox = new HBox(10);
        HeadlingandDropbownBox.getChildren().add(headline);
        HeadlingandDropbownBox.getChildren().add(new Text("                  "));
        HeadlingandDropbownBox.getChildren().add(dropdown256);
        HeadlingandDropbownBox.getChildren().add(startButton);
        //am ende box und textarea hinzufügen
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setBackground(new Background(new BackgroundFill(Color.rgb(0, 90, 70), CornerRadii.EMPTY, Insets.EMPTY)));


        grid.setHgap(3);
        grid.setVgap(3);
        grid.add(HeadlingandDropbownBox, 0, 1);
        grid.add(buttonpasswortmodus, 1, 1);
        grid.add(text1, 0, 2);
        grid.add(box, 1, 2);
//        grid.add(box2,2,2);
        grid.add(ergebnistext, 0, 3);
        //column = spalte
        grid.add(boxpisscine, 1, 3);
//        grid.setAlignment(Pos.TOP_LEFT);
//        stage.setAlwaysOnTop(true);

        stage.setTitle("JavaFX Template");

        scene = new Scene(grid, 650, 430);

        //tastensteuerung global
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    //invalid type, does nothing if not used for adminmode as console
                    buttonhandler(text1.getText(), textfeld.getText(), textfeld2.getText(), 187);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (event.getCode() == KeyCode.F10) {
                try {
                    //invalid type, does nothing if not used for adminmode as console
                    buttonverschlüsseln.fire();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (event.getCode() == KeyCode.F11) {
                try {
                    //invalid type, does nothing if not used for adminmode as console
                    buttonentschlüsseln.fire();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        GameFX.slowMovingText(copyright,"                © Jan Füllhase 2023");

        //https://stackoverflow.com/questions/20049452/javafx-focusing-textfield-programmatically
        text1.requestFocus();

        stage.setResizable(false);


        stage.getIcons().add(new Image("file:enchro2.png"));
        stage.setScene(scene);
        stage.setHeight(476);
        stage.setWidth(670);
        stage.show();

    }

    private static void copyToClipboard() {
        System.out.println("debug: entering copyclipboard stage");

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(ergebnistext.getText());
//        content.putHtml("<b>Some</b> text");
        clipboard.setContent(content);
    }

    private static String getFromClipboard() {
        System.out.println("debug: entering GETclipboard stage");

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(ergebnistext.getText());
//        content.putHtml("<b>Some</b> text");
        var out = clipboard.getString();
        return out;
    }

    private void passwortvisible(boolean visible, boolean b) {
        if (b) {
            visible = passwortmode;
            ergebnistext.setText("Passwortmodus ist nichts für DAUs!\nNur Zahlen und Buchstaben!");
        }
        if (!visible) {
            textfeld.setText("");
            textfeld2.setText("");
        }
        textfeld.setVisible(visible);
        textfeld2.setVisible(visible);
        fragegeburtsort.setVisible(visible);
        fragepasswort.setVisible(visible);
        passwortmode = !passwortmode;
    }

    private static void passwortvisibleStatic(boolean visible, boolean b) {
        if (b) {
            visible = passwortmode;
            ergebnistext.setText("Passwortmodus ist nichts für DAUs!\nNur Zahlen und Buchstaben!");
        }
        if (!visible) {
            textfeld.setText("");
            textfeld2.setText("");
        }
        textfeld.setVisible(visible);
        textfeld2.setVisible(visible);
        fragegeburtsort.setVisible(visible);
        fragepasswort.setVisible(visible);
        passwortmode = !passwortmode;
    }

    /**
     * Simplified encryption
     *
     * @param type 1==versch, 2 = entsch
     * @throws Exception
     */
    public static String buttonhandler(String ttext1, String ttextfeld1, String ttextfeld2, int type) throws Exception {

        return null;
    }

    private static String replaceMalformedUmlaute(String output) {
        System.out.println("debug: replaceMalformedUmlaute called");
        String newString = output.replace("Ã¼", "ü")
                .replace("Ã¶", "ö")
                .replace("Ã¤", "ä")
                .replace("ÃŸ", "ß")
//                .replaceAll("\u00dc(?=[a-z\u00e4\u00f6\u00fc\u00df ])", "Ue")
//                .replaceAll("\u00d6(?=[a-z\u00e4\u00f6\u00fc\u00df ])", "Oe")
//                .replaceAll("\u00c4(?=[a-z\u00e4\u00f6\u00fc\u00df ])", "Ae")
                .replace("Ãœ", "Ü")
                .replace("Ã–", "Ö")
                .replace("Ã„", "Ä");
        return newString;
    }

    public static String scanner(String ausgabe) {
        Scanner scan = new Scanner(text1.getText());          //eingabe

//        System.out.print(ausgabe + ": ");
        String out = scan.nextLine();
        return out;
    }

    public static void main(String[] args) {
        //simply launches Enchro application
        launch();
    }

}