module com.example.civiliztion {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.civiliztion to javafx.fxml;
    opens com.example.civiliztion.View to javafx.fxml;
    opens com.example.civiliztion.Model to javafx.fxml;
    exports com.example.civiliztion;
    exports com.example.civiliztion.View;
    exports com.example.civiliztion.Model;

}