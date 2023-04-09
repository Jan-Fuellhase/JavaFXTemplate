module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens org.template to javafx.fxml;
    exports org.template;
}
