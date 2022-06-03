package com.example.civiliztion;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import com.example.civiliztion.Controllers.DatabaseController;

import com.example.civiliztion.Model.Database;
import com.example.civiliztion.View.LoginMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  private static Scene scene;
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
    URL address_login_page = new URL(Main.class.getResource("GlobalChat.fxml").toString());
    Parent root = FXMLLoader.load(address_login_page);
    Scene scene = new Scene(root);
    Main.scene = scene;
    stage.setScene(scene);
    stage.show();

  }

  private static Parent getFxml(String name){
    try {
      URL addressMain = new URL(Main.class.getResource(name).toExternalForm());
      return FXMLLoader.load(addressMain);
    }  catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}