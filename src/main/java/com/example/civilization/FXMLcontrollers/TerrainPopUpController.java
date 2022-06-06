package com.example.civilization.FXMLcontrollers;

import com.example.civilization.Model.Terrain;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TerrainPopUpController {

    @FXML
    private Label x = new Label();
    @FXML
    private Label y = new Label();

    @FXML
    private Label Type = new Label();
    @FXML
    private Label TerrainType = new Label();
    @FXML
    private Label FeatureType = new Label();
    @FXML
    private Label ResourceType = new Label();
    @FXML
    private Label ImprovementType = new Label();
    @FXML
    private Label TypeFood = new Label();
    @FXML
    private Label TypeProduction = new Label();
    @FXML
    private Label TypeGold = new Label();
    @FXML
    private Label FeatureFood = new Label();
    @FXML
    private Label FeatureProduction = new Label();
    @FXML
    private Label FeatureGold = new Label();
    @FXML
    private  Label ResourceFood = new Label();
    @FXML
    private  Label ResourceProduction = new Label();
    @FXML
    private  Label ResourceGold = new Label();
    @FXML
    private  Label ImprovementFood = new Label();
    @FXML
    private  Label ImprovementProduction = new Label();
    @FXML
    private  Label ImprovementGold = new Label();
    @FXML
    private  ImageView TerrainImage = new ImageView();
    @FXML
    private  ImageView FeatureImage = new ImageView();
    @FXML
    private  ImageView ResourceImage = new ImageView();
    @FXML
    private  ImageView ImprovementImage = new ImageView();

    public void setData(Terrain terrain) {

        x.setText("x : " + terrain.getX());
        y.setText("y : " + terrain.getY());
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
