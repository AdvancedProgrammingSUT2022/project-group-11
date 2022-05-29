module com.example.civiliztion {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.civiliztion to javafx.fxml;
    exports com.example.civiliztion;
}