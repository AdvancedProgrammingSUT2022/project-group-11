package com.example.civilization.FXMLcontrollers;

import com.example.civilization.Controllers.DatabaseController;
import com.example.civilization.Main;
import com.example.civilization.Model.Civilization;
import com.example.civilization.Model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WinnerMenuController {

    public Label result;

    @FXML
    public void initialize() {

        Platform.runLater(this::setTexts);

    }

    public void setTexts() {
        if (DatabaseController.getInstance().theWinnerCivilization() != null) {
            result.setText("Civilization " + DatabaseController.getInstance().theWinnerCivilization().getName() + " Won By " + DatabaseController.getInstance().theWinnerCivilization().getScore() + " points");
            for (User user : DatabaseController.getInstance().getAllPlayerUser()) {
                if (user.equals(DatabaseController.getInstance().getUserByCivilization(DatabaseController.getInstance().theWinnerCivilization()))) {
                    DatabaseController.getInstance().setScore((int) (user.getScore() + DatabaseController.getInstance().calculatingScoreForEachCivilizationAfterEachRound(user.getCivilization()) * 5000 / DatabaseController.getInstance().getYear()),user);
                } else {
                    DatabaseController.getInstance().setScore(user.getScore() + DatabaseController.getInstance().calculatingScoreForEachCivilizationAfterEachRound(user.getCivilization()),user);
                }

            }
            DatabaseController.getInstance().clearPlayerUser();
        } else {
            Civilization winner = DatabaseController.getInstance().theWinnerAfterYear2050();
            result.setText("Civilization " + winner.getName() + " Won By " + winner.getScore() + " points");
            for (User user : DatabaseController.getInstance().getAllPlayerUser()) {

                DatabaseController.getInstance().setScore(user.getScore() + DatabaseController.getInstance().calculatingScoreForEachCivilizationAfterEachRound(user.getCivilization()),user);
            }
            DatabaseController.getInstance().clearPlayerUser();

        }

    }


    public void backToMap() {
        Main.changeMenu("LoginMenu");
    }
}