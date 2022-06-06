package com.example.civilization.FXMLcontrollers;

import com.example.civilization.Model.Terrain;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TerrainPopUpController {

    @FXML
    private final Label x = new Label();
    @FXML
    private final Label y = new Label();

    @FXML
    private final Label Type = new Label();
    @FXML
    private final Label TerrainType = new Label();
    @FXML
    private final Label FeatureType = new Label();
    @FXML
    private final Label ResourceType = new Label();
    @FXML
    private final Label ImprovementType = new Label();
    @FXML
    private final Label TypeFood = new Label();
    @FXML
    private final Label TypeProduction = new Label();
    @FXML
    private final Label TypeGold = new Label();
    @FXML
    private final Label FeatureFood = new Label();
    @FXML
    private final Label FeatureProduction = new Label();
    @FXML
    private final Label FeatureGold = new Label();
    @FXML
    private final Label ResourceFood = new Label();
    @FXML
    private final Label ResourceProduction = new Label();
    @FXML
    private final Label ResourceGold = new Label();
    @FXML
    private final Label ImprovementFood = new Label();
    @FXML
    private final Label ImprovementProduction = new Label();
    @FXML
    private final Label ImprovementGold = new Label();
    @FXML
    private final ImageView TerrainImage = new ImageView();
    @FXML
    private final ImageView FeatureImage = new ImageView();
    @FXML
    private final ImageView ResourceImage = new ImageView();
    @FXML
    private final ImageView ImprovementImage = new ImageView();

    public void setData(Terrain terrain) {

        x.setText("x : " + terrain.getX());
        y.setText("y : " + terrain.getY());
        System.out.println(terrain.getX() + " " + terrain.getY() + "ehsan" + terrain.getTerrainTypes().name());
        TerrainType.setText(terrain.getTerrainTypes().name());
        TypeFood.setText(Integer.toString(terrain.getTerrainTypes().getFood()));
        TypeProduction.setText(Integer.toString(terrain.getTerrainTypes().getProduct()));
        TypeGold.setText(Integer.toString(terrain.getTerrainTypes().getGold()));
        if (terrain.getType() != null) {
            Type.setText(terrain.getType());
        }
        if (terrain.getTerrainFeatureTypes() != null && !terrain.getTerrainFeatureTypes().isEmpty()) {
            FeatureType.setText(terrain.getTerrainFeatureTypes().get(0).name());
            FeatureFood.setText(Integer.toString(terrain.getTerrainFeatureTypes().get(0).getFood()));
            FeatureProduction.setText(Integer.toString(terrain.getTerrainFeatureTypes().get(0).getProduct()));
            FeatureGold.setText(Integer.toString(terrain.getTerrainFeatureTypes().get(0).getGold()));
        }
        if (terrain.getResource() != null) {
            ResourceType.setText(terrain.getResource().getResourceType().name());
            ResourceFood.setText(Integer.toString(terrain.getResource().getResourceType().getFood()));
            ResourceProduction.setText(Integer.toString(terrain.getResource().getResourceType().getProduction()));
            ResourceGold.setText(Integer.toString(terrain.getResource().getResourceType().getGold()));
        }
        if (terrain.getTerrainImprovement() != null) {
            ImprovementType.setText(terrain.getTerrainImprovement().getImprovementType().name());
            ImprovementFood.setText(Integer.toString(terrain.getTerrainImprovement().getImprovementType().getFood()));
            ImprovementProduction.setText(Integer.toString(terrain.getTerrainImprovement().getImprovementType().getProduction()));
            ImprovementGold.setText(Integer.toString(terrain.getTerrainImprovement().getImprovementType().getGold()));
        }


    }


}
