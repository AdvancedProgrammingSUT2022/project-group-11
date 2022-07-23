package com.example.civilization.FXMLcontrollers;

import com.example.civilization.Controllers.DatabaseController;
import com.example.civilization.Main;
import com.example.civilization.Model.City.City;
import com.example.civilization.Model.Civilization;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.util.ArrayList;

public class TradePanelController {

    @FXML
    public ArrayList<Pair<ChoiceBox, ChoiceBox>> choiceBoxes = new ArrayList<>();
    public ArrayList<Pair<Label, Button>> civilizationsNameAndButtonRequest = new ArrayList<>();
    @FXML
    public AnchorPane anchorPane;
    @FXML
    Label resultText;
    @FXML
    ArrayList<Pair<TextField, TextField>> choiceBoxesValues = new ArrayList<>();

    @FXML
    ArrayList<Pair<Button, Button>> acceptAndDecline = new ArrayList<>();

    @FXML
    ArrayList<Label> requests = new ArrayList<>();

    @FXML
    public void initialize() {

        Platform.runLater(this::setTexts);
        Platform.runLater(this::setRequestTexts);

    }

    public void setTexts() {
        int i = 0;
        for (Civilization civilization : DatabaseController.getInstance().activeUser().getCivilization().getStatusWithOtherCivilizations().keySet()) {
            if (!DatabaseController.getInstance().activeUser().getCivilization().getStatusWithOtherCivilizations().get(civilization)) {
                choiceBoxes.add(new Pair<>(new ChoiceBox<>(), new ChoiceBox<>()));
                civilizationsNameAndButtonRequest.add(new Pair<>(new Label(civilization.getName()), new Button("Send Request")));
                choiceBoxesValues.add(new Pair<>(new TextField(), new TextField()));
                DemandPanelController.setCivilizationNameAndButton(i, civilizationsNameAndButtonRequest);
                choiceBoxesValues.get(i).getKey().setStyle("-fx-background-radius: 100em");
                choiceBoxesValues.get(i).getValue().setStyle("-fx-background-radius: 100em");
                choiceBoxes.get(i).getKey().setStyle("-fx-background-radius: 100em");
                choiceBoxes.get(i).getValue().setStyle("-fx-background-radius: 100em");
                choiceBoxes.get(i).getKey().getItems().add("City");
                choiceBoxes.get(i).getKey().getItems().add("Gold");
                choiceBoxes.get(i).getValue().getItems().add("City");
                choiceBoxes.get(i).getValue().getItems().add("Gold");
            }


            Button button = civilizationsNameAndButtonRequest.get(i).getValue();
            int j = i;
            civilizationsNameAndButtonRequest.get(i).getValue().setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        if (isRequestValid(j)) {
                            resultText.setText("request sent");
                            String request = choiceBoxesValues.get(j).getKey().getText() + " " + choiceBoxes.get(j).getKey().getSelectionModel().getSelectedItem() + " " + choiceBoxesValues.get(j).getValue().getText() + " " + choiceBoxes.get(j).getValue().getSelectionModel().getSelectedItem();
                            DatabaseController.getInstance().putTradeRequest(civilization,DatabaseController.getInstance().activeUser().getCivilization(),request);
                           // civilization.getTradeRequests().put(DatabaseController.getInstance().activeUser().getCivilization(), request);
                        } else {
                            resultText.setText("request is invalid");
                        }

                    }
                }
            });

            civilizationsNameAndButtonRequest.get(i).getKey().setLayoutX(300);
            civilizationsNameAndButtonRequest.get(i).getValue().setLayoutX(850);
            civilizationsNameAndButtonRequest.get(i).getKey().setLayoutY(100 + 65 * (i + 1));
            civilizationsNameAndButtonRequest.get(i).getValue().setLayoutY(100 + 65 * (i + 1));
            choiceBoxes.get(i).getKey().setLayoutX(150 + 400);
            choiceBoxes.get(i).getKey().setLayoutY(100 + 65 * (i + 1));
            choiceBoxes.get(i).getValue().setLayoutX(350 + 400);
            choiceBoxes.get(i).getValue().setLayoutY(100 + 65 * (i + 1));
            choiceBoxesValues.get(i).getKey().setLayoutX(50 + 400);
            choiceBoxesValues.get(i).getKey().setLayoutY(100 + 65 * (i + 1));
            choiceBoxesValues.get(i).getValue().setLayoutX(250 + 400);
            choiceBoxesValues.get(i).getValue().setLayoutY(100 + 65 * (i + 1));
            choiceBoxesValues.get(i).getKey().setPrefSize(75, 25);
            choiceBoxesValues.get(i).getValue().setPrefSize(75, 25);
            anchorPane.getChildren().addAll(choiceBoxes.get(i).getKey(), choiceBoxes.get(i).getValue(), civilizationsNameAndButtonRequest.get(i).getKey(), civilizationsNameAndButtonRequest.get(i).getValue(), choiceBoxesValues.get(i).getKey(), choiceBoxesValues.get(i).getValue());
            i++;
        }
    }

    public boolean isRequestValid(int i) {
        return choiceBoxes.get(i).getKey().getSelectionModel().getSelectedItem() != null && choiceBoxes.get(i).getValue().getSelectionModel().getSelectedItem() != null && GameMenuController.isInteger(choiceBoxesValues.get(i).getKey().getText()) && GameMenuController.isInteger(choiceBoxesValues.get(i).getValue().getText());
    }

    public void setRequestTexts() {
        int i = 0;
        for (Civilization civilization : DatabaseController.getInstance().getDatabase().getActiveUser().getCivilization().getTradeRequests().keySet()) {
            requests.add(new Label(civilization.getName() + " " + DatabaseController.getInstance().getDatabase().getActiveUser().getCivilization().getTradeRequests().get(civilization)));
            DemandPanelController.setRequestText(i, acceptAndDecline, requests, anchorPane);
            acceptAndDecline.get(i).getValue().setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        DatabaseController.getInstance().removePutTradeRequest(civilization);
                        Main.changeMenu("TradePanel");

                    }
                }
            });
            acceptAndDecline.get(i).getKey().setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        tradeCheck(requests.get(i).getText());
                        DatabaseController.getInstance().removePutTradeRequest(civilization);
                    }
                }
            });

        }
    }

    public void tradeCheck(String request) {
        String[] parts = request.split(" ");
        Civilization first = DatabaseController.getInstance().getCivilizationByName(parts[0]);
        int firstValue = Integer.parseInt(parts[1]);
        int secondValue = Integer.parseInt(parts[3]);
        Civilization second = DatabaseController.getInstance().activeUser().getCivilization();
        if (parts[2].equalsIgnoreCase("gold") && parts[4].equalsIgnoreCase("gold")) {
            if (first.getGold() >= firstValue && second.getGold() >= secondValue) {
                DatabaseController.getInstance().setCivilizationGold(first,first.getGold() - firstValue + secondValue);
               // first.setGold(first.getGold() - firstValue + secondValue);
                DatabaseController.getInstance().setCivilizationGold(second,second.getGold() + firstValue - secondValue);
               // second.setGold(second.getGold() + firstValue - secondValue);
                DatabaseController.getInstance().removePutTradeRequestCivilization(second,first);
             //   second.getTradeRequests().remove(first);
                Main.changeMenu("TradePanel");
            }

        } else if (parts[2].equalsIgnoreCase("gold") && parts[4].equalsIgnoreCase("city")) {
            if (first.getGold() >= firstValue && second.getCities().size() >= secondValue + 1) {
                DatabaseController.getInstance().setCivilizationGold(first,first.getGold() - firstValue);
              //  first.setGold(first.getGold() - firstValue);
                DatabaseController.getInstance().setCivilizationGold(second,second.getGold() + firstValue);
             //   second.setGold(second.getGold() + firstValue);
                ArrayList<City> cities = getCitiesFromCivilization(second, secondValue);
                for (City city : cities) {
                //    first.addCity(city);
                //    city.setOwner(first);
                    DatabaseController.getInstance().citySetOwner(city,first);
                    DatabaseController.getInstance().removeCity(second,city);
                  //  second.getCities().remove(city);

                }
                DatabaseController.getInstance().removePutTradeRequestCivilization(second,first);
              //  second.getTradeRequests().remove(first);
                Main.changeMenu("TradePanel");
            }

        } else if (parts[2].equalsIgnoreCase("city") && parts[4].equalsIgnoreCase("gold")) {
            if (first.getCities().size() >= firstValue + 1 && second.getGold() >= secondValue) {
                DatabaseController.getInstance().setCivilizationGold(first,first.getGold() + secondValue);
             //   first.setGold(first.getGold() + secondValue);
                DatabaseController.getInstance().setCivilizationGold(second,second.getGold() - secondValue);
                //second.setGold(second.getGold() - secondValue);
                ArrayList<City> cities = getCitiesFromCivilization(first, firstValue);
                for (City city : cities) {
                    DatabaseController.getInstance().removeCity(first,city);
                  //  first.getCities().remove(city);
                    DatabaseController.getInstance().addCity(city,second,"yes");
                  //  second.addCity(city);
                  //  city.setOwner(second);
                }
                second.getTradeRequests().remove(first);
                Main.changeMenu("TradePanel");

            }


        } else if (parts[2].equalsIgnoreCase("city") && parts[4].equalsIgnoreCase("city")) {
            if (first.getCities().size() >= firstValue + 1 && second.getCities().size() >= secondValue + 1) {
                ArrayList<City> cities = getCitiesFromCivilization(first, firstValue);
                for (City city : cities) {
                    DatabaseController.getInstance().removeCity(first,city);
                 //   first.getCities().remove(city);
                    DatabaseController.getInstance().addCity(city,second,"yes");
                 //   second.addCity(city);
                //    city.setOwner(second);
                }
                ArrayList<City> cities2 = getCitiesFromCivilization(second, secondValue);
                for (City city : cities2) {
             //       first.addCity(city);
             //       city.setOwner(first);
                    DatabaseController.getInstance().addCity(city,first,"yes");
                    DatabaseController.getInstance().removeCity(second,city);
                //    second.getCities().remove(city);

                    DatabaseController.getInstance().removePutTradeRequestCivilization(second,first);
                //    second.getTradeRequests().remove(first);
                    Main.changeMenu("TradePanel");
                }


            }
        }
        resultText.setText("Trade is Invalid");
    }

    public void backToGameMap() {
        Main.changeMenu("DiplomacyPanel");
    }

    public static ArrayList<City> getCitiesFromCivilization(Civilization civilization, int i) {
        int numberOfAdded = 0;
        ArrayList<City> cities = new ArrayList<>();
        for (City city : civilization.getCities()) {
            if (!city.equals(civilization.getCurrentCapital())) {
                cities.add(city);
                numberOfAdded++;
            }
            if (numberOfAdded == i) {
                break;
            }
        }
        return cities;
    }
}

