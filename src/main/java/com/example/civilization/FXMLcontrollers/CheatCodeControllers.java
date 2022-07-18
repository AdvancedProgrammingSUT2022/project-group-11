package com.example.civilization.FXMLcontrollers;

import com.example.civilization.Controllers.DatabaseController;
import com.example.civilization.Enums.GameEnums;
import com.example.civilization.Main;
import com.example.civilization.Model.Technologies.TechnologyTypes;
import com.example.civilization.Model.User;
import com.example.civilization.View.GameMenu;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.util.regex.Matcher;

public class CheatCodeControllers {

    @FXML
    AnchorPane anchorPane;

    @FXML
    Label resultText;


    @FXML
    public void initialize() {

        Platform.runLater(this::setCheatResult);

    }

    public void setCheatResult() {
        resultText.setPrefSize(470, 41);
        resultText.setFont(Font.font("Copperplate", 15));
        resultText.setLayoutX(450);
        resultText.setLayoutY(250);
        for (Node children : anchorPane.getChildren()) {

            if (children instanceof TextField) {
                ((TextField) children).setPrefSize(470, 41);
                children.setLayoutX(450);
                children.setLayoutY(200);
                children.setOnKeyPressed(e -> {
                    if (e.getCode().equals(KeyCode.ENTER)) {
                        String input = ((TextField) children).getText();
                        Matcher matcher;
                        User user = DatabaseController.getInstance().activeUser();
                        if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_TURN)) != null) {
                            int amount = Integer.parseInt(matcher.group("amount"));
                            resultText.setText(DatabaseController.getInstance().increaseTurnCheat(amount));
                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_GOLD)) != null) {
                            int amount = Integer.parseInt(matcher.group("amount"));
                            resultText.setText(DatabaseController.getInstance().increaseGoldCheat(user,amount));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.COMBAT_UNIT_CHEAT_MOVE)) != null) {
                            int x = Integer.parseInt(matcher.group("x"));
                            int y = Integer.parseInt(matcher.group("y"));
                            resultText.setText(DatabaseController.getInstance().cheatMoveCombatUnit(x,y));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.NON_COMBAT_UNIT_CHEAT_MOVE)) != null) {
                            int x = Integer.parseInt(matcher.group("x"));
                            int y = Integer.parseInt(matcher.group("y"));
                            resultText.setText(DatabaseController.getInstance().cheatMoveNonCombatUnit(x,y));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_HAPPINESS)) != null) {
                            int amount = Integer.parseInt(matcher.group("amount"));
                            resultText.setText(DatabaseController.getInstance().increaseHappinessCheat(user,amount));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_SCIENCE)) != null) {
                            int amount = Integer.parseInt(matcher.group("amount"));
                            resultText.setText(DatabaseController.getInstance().increaseScienceCheat(user,amount));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
                            resultText.setText(buyTechnologyCheat(matcher, user));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_CHEAT_TILE)) != null) {
                            int x = Integer.parseInt(matcher.group("x"));
                            int y = Integer.parseInt(matcher.group("y"));
                            resultText.setText(DatabaseController.getInstance().buyCheatTile(user, x,y));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
                            int x = Integer.parseInt(matcher.group("x"));
                            int y = Integer.parseInt(matcher.group("y"));
                            String name = matcher.group("name");
                            resultText.setText(DatabaseController.getInstance().setCheatUnit(user, name,x,y));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
                          //  resultText.setText(GameMenu.setCheatImprovement(matcher));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
                           // resultText.setText(GameMenu.setCheatResource(matcher));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
                           // resultText.setText(GameMenu.setCheatTerrainFeature(matcher));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_TYPE)) != null) {
                        //      resultText.setText(GameMenu.setCheatTerrainType(matcher));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.DELETE_CHEAT_IMPROVEMENT)) != null) {
                            int x = Integer.parseInt(matcher.group("x"));
                            int y = Integer.parseInt(matcher.group("y"));
                            resultText.setText(DatabaseController.getInstance().deleteCheatImprovement(x,y));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.REPAIR_CHEAT_IMPROVEMENT)) != null) {
                            int x = Integer.parseInt(matcher.group("x"));
                            int y = Integer.parseInt(matcher.group("y"));
                            resultText.setText(DatabaseController.getInstance().repairCheatImprovement(x,y));

                        } else {
                            resultText.setText("Invalid Input");
                        }
                    }
                });

            }


        }


    }

    public void backToMap() {
        Main.changeMenu("gameMap");
    }

    public String buyTechnologyCheat(Matcher matcher, User user) {

        String name = matcher.group("name");
        switch (name) {
            case "AGRICULTURE":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.AGRICULTURE));

            case "ANIMAL_HUSBANDRY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.ANIMAL_HUSBANDRY));


            case "ARCHERY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.ARCHERY));

            case "BRONZE_WORKING":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.BRONZE_WORKING));


            case "CALENDAR":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.CALENDAR));


            case "MASONRY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.MASONRY));


            case "MINING":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.MINING));


            case "POTTERY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.POTTERY));


            case "THE_WHEEL":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.THE_WHEEL));


            case "TRAPPING":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.TRAPPING));


            case "WRITING":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.WRITING));


            case "CONSTRUCTION":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.CONSTRUCTION));


            case "HORSEBACK_RIDING":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.HORSEBACK_RIDING));


            case "IRON_WORKING":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.IRON_WORKING));


            case "MATHEMATICS":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.MATHEMATICS));


            case "PHILOSOPHY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.PHILOSOPHY));


            case "CHIVALRY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.CHIVALRY));


            case "CIVIL_SERVICE":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.CIVIL_SERVICE));


            case "CURRENCY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.CURRENCY));


            case "EDUCATION":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.EDUCATION));


            case "ENGINEERING":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.ENGINEERING));


            case "MACHINERY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.MACHINERY));


            case "METAL_CASTING":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.METAL_CASTING));


            case "PHYSICS":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.PHYSICS));


            case "STEEL":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.STEEL));


            case "THEOLOGY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.THEOLOGY));


            case "ACOUSTICS":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.ACOUSTICS));


            case "ARCHAEOLOGY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.ARCHAEOLOGY));


            case "BANKING":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.BANKING));


            case "CHEMISTRY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.CHEMISTRY));


            case "ECONOMICS":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.ECONOMICS));


            case "FERTILIZER":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.FERTILIZER));


            case "GUNPOWDER":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.GUNPOWDER));


            case "METALLURGY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.METALLURGY));


            case "MILITARY_SCIENCE":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.MILITARY_SCIENCE));


            case "PRINTING_PRESS":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.PRINTING_PRESS));


            case "SCIENTIFIC_THEORY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.SCIENTIFIC_THEORY));


            case "BIOLOGY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.BIOLOGY));

            case "COMBUSTION":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.COMBUSTION));


            case "DYNAMITE":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.DYNAMITE));


            case "ELECTRICITY":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.ELECTRICITY));


            case "RADIO":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.RADIO));


            case "RAILROAD":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.RAILROAD));


            case "REPLACEABLE_PARTS":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.REPLACEABLE_PARTS));


            case "STEAM_POWER":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.STEAM_POWER));


            case "TELEGRAPH":
                return(DatabaseController.getInstance().buyTechnologyCheat(user, TechnologyTypes.TELEGRAPH));


        }

        return name;
    }

}
