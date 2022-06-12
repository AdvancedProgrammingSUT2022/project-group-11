package com.example.civilization.FXMLcontrollers;

import com.example.civilization.Controllers.DatabaseController;
import com.example.civilization.Model.Improvements.ImprovementTypes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;

public class WorkersOptionsPopUpController {
    @FXML
    AnchorPane anchorPane;

    @FXML
    Label remainingTurns;

    @FXML
    ProgressBar progressBar;


    public void setData(String text) throws FileNotFoundException {
        System.out.println(text.toUpperCase().startsWith("Build".toUpperCase()));
        if (text.toUpperCase().startsWith("Build".toUpperCase())) {
            if (DatabaseController.getInstance().getTerrainByCoordinates(DatabaseController.getInstance().getSelectedNonCombatUnit().getX(), DatabaseController.getInstance().getSelectedNonCombatUnit().getY()).getTerrainImprovement() == null) {
                for (ImprovementTypes improvementTypes : ImprovementTypes.values()) {

                    if (improvementTypes.name().equalsIgnoreCase(text.substring(8))) {
                        remainingTurns.setText(improvementTypes.name() + " will be constructed in " + improvementTypes.getTurn() + " turns");
                    }

                    progressBar.setVisible(false);
                }
            } else {
                int total = DatabaseController.getInstance().getTerrainByCoordinates(DatabaseController.getInstance().getSelectedNonCombatUnit().getX(), DatabaseController.getInstance().getSelectedNonCombatUnit().getY()).getTerrainImprovement().getImprovementType().getTurn();
                int passed = DatabaseController.getInstance().getTerrainByCoordinates(DatabaseController.getInstance().getSelectedNonCombatUnit().getX(), DatabaseController.getInstance().getSelectedNonCombatUnit().getY()).getTerrainImprovement().getPassedTurns();
                remainingTurns.setText(DatabaseController.getInstance().getTerrainByCoordinates(DatabaseController.getInstance().getSelectedNonCombatUnit().getX(), DatabaseController.getInstance().getSelectedNonCombatUnit().getY()).getTerrainImprovement().getImprovementType().name() + " will be constructed in " + (total - passed) + " turns");
                progressBar.setProgress(total / (double) passed);
            }


        } else if (text.toUpperCase().startsWith("Delete route".toUpperCase())) {


        } else if (text.toUpperCase().startsWith("Delete feature".toUpperCase())) {

        } else if (text.toUpperCase().startsWith("repair improvement".toUpperCase())) {

        }

    }
}
