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
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) {
    /*
    Scanner scanner = new Scanner(System.in);
    Database database = new Database();
    DatabaseController databaseController = new DatabaseController(database);
    LoginMenu loginMenu = new LoginMenu(databaseController);
    loginMenu.run(scanner);

     */
  }

  @Override
  public void start(Stage stage) throws Exception {

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