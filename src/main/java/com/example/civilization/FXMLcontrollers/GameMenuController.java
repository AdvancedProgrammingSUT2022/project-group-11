package com.example.civilization.FXMLcontrollers;

import com.example.civilization.Controllers.DatabaseController;
import com.example.civilization.Main;
import com.example.civilization.Model.User;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class GameMenuController {


    public Label result;
    public TextField opponentsName;
    public TextField opponentsCount;
    public Button load;
    public Button newGame;
    @FXML
    ArrayList<Button> suggestions = new ArrayList<>();
    @FXML
    private AnchorPane anchorPane;

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    @FXML
    public void initialize() {
        if (!DatabaseController.getInstance().getDatabase().getUsers().contains(DatabaseController.getInstance().getDatabase().getActiveUser())) {
            DatabaseController.getInstance().getDatabase().addUser(DatabaseController.getInstance().getDatabase().getActiveUser());
        }

        Platform.runLater(this::setOpponentsName);

    }

    public void setOpponentsName() {
        newGame.setOnMouseEntered(mouseEvent -> showingTooltip("new game"));
        load.setOnMouseEntered(mouseEvent -> showingTooltip("load"));
        opponentsCount.setOnMouseEntered(mouseEvent -> showingTooltip("number of players"));
        opponentsName.setOnMouseEntered(mouseEvent -> showingTooltip("search name"));

        opponentsName.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                anchorPane.getChildren().removeAll(suggestions);
                suggestions.clear();
                int i = 0;
                for (User user : DatabaseController.getInstance().getDatabase().getAllUsers()) {
                    if (user.getUsername().equalsIgnoreCase(opponentsName.getText().toUpperCase()) && !user.equals(DatabaseController.getInstance().getDatabase().getActiveUser())) {
                        suggestions.add(new Button());
                        suggestions.get(i).setFont(Font.font("Copperplate", 15));
                        suggestions.get(i).setStyle("-fx-background-radius: 100em");
                        suggestions.get(i).setPrefSize(opponentsName.getPrefWidth(), opponentsName.getPrefHeight());
                        suggestions.get(i).setText(user.getUsername());
                        suggestions.get(i).setVisible(true);
                        suggestions.get(i).setLayoutX(opponentsName.getLayoutX());
                        suggestions.get(i).setLayoutY(opponentsName.getLayoutY() + (opponentsName.getPrefHeight() + 10) * (i + 1));
                        Button button = suggestions.get(i);
                        button.setTextFill(Color.RED);
                        suggestions.get(i).setOnMouseClicked(mouseEvent -> {
                            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                                if (mouseEvent.getClickCount() == 2) {
                                    if (button.getTextFill().equals(Color.BLUE)) {
                                        button.setTextFill(Color.RED);
                                        DatabaseController.getInstance().getDatabase().getUsers().remove(user);
                                        result.setText("user deselected");

                                    } else if (button.getTextFill().equals(Color.RED)) {
                                        button.setTextFill(Color.BLUE);
                                        if (!DatabaseController.getInstance().getDatabase().getUsers().contains(user)) {
                                            DatabaseController.getInstance().getDatabase().addUser(user);
                                        }
                                        result.setText("user selected");


                                    }

                                }
                            }
                        });
                        anchorPane.getChildren().add(suggestions.get(i));
                        i++;
                    }

                }

            }
        });

        opponentsCount.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                String number = opponentsCount.getText();
                if (isInteger(number)) {
                    int num = Integer.parseInt(number);
                    if (num < 2 || num > 5) {
                        result.setText("You must enter a number between 2 and 5");
                    } else {
                        result.setText("number selected");
                    }
                } else {
                    result.setText("You must enter a number between 2 and 5");
                }
            }
        });

    }

    public void startGame() {
        if (isInteger(opponentsCount.getText())) {
            if (DatabaseController.getInstance().getDatabase().getUsers().size() < Integer.parseInt(opponentsCount.getText())) {
                result.setText("you must select more users to start the game");

            } else if (DatabaseController.getInstance().getDatabase().getUsers().size() > Integer.parseInt(opponentsCount.getText())) {
                result.setText("you must select less users to start the game");

            } else {
                if (Integer.parseInt(opponentsCount.getText()) < 2 || Integer.parseInt(opponentsCount.getText()) > 5) {
                    result.setText("number of players must be between 2 and 5");
                } else {

                    DatabaseController.getInstance().getMap().generateMap();
                    DatabaseController.getInstance().setCivilizations(DatabaseController.getInstance().getDatabase().getUsers());
                    Main.changeMenu("gameMap");

                }
            }
        } else {
            result.setText("you have not selected number of players");
        }

    }

    public void showingTooltip(String result) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("FXML/tooltip.fxml"));
            Parent root = loader.load();

            tooltipController secController = loader.getController();
            secController.setData(result);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> stage.close());
            delay.play();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back() {
        Main.changeMenu("ProfileMenu");
    }
}