module com.example.civiliztion {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.civiliztion.View.MenuControllers to javafx.fxml;
    opens com.example.civiliztion.Model to com.google.gson;
    opens com.example.civiliztion.Model.Technologies to com.google.gson;
    opens com.example.civiliztion.Model.Units to com.google.gson;
    opens com.example.civiliztion.Model.Terrains to com.google.gson;
    opens com.example.civiliztion.Model.TerrainFeatures to com.google.gson;
    opens com.example.civiliztion.Model.Improvements to com.google.gson;
    opens com.example.civiliztion.Model.Resources to com.google.gson;
    opens com.example.civiliztion.Model.City to com.google.gson;
    opens com.example.civiliztion.Model.Buildings to com.google.gson;
    opens com.example.civiliztion.Controllers to com.google.gson;
    exports com.example.civiliztion.View.MenuControllers to javafx.fxml;
    exports com.example.civiliztion to javafx.graphics;
}