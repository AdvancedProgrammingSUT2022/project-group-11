package com.example.civiliztion;

import com.example.civiliztion.Controllers.saveData;
import com.example.civiliztion.Model.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static saveData saveData = new saveData();
    public static Database database = new Database();

    public static void main(String[] args) {
    /*
    Scanner scanner = new Scanner(System.in);
    Database database = new Database();
    DatabaseController databaseController = new DatabaseController(database);
    LoginMenu loginMenu = new LoginMenu(databaseController);
    loginMenu.run(scanner);

     */

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
      saveData.loadUsers(database);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("leaderboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setScene(scene);
        stage.setTitle("Civlization");
        stage.setResizable(false);
        stage.show();
    }
}