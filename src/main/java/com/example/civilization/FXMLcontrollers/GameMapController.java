package com.example.civilization.FXMLcontrollers;


import com.example.civilization.Controllers.DatabaseController;
import com.example.civilization.Main;
import com.example.civilization.Model.TerrainFeatures.TerrainFeatureTypes;
import com.example.civilization.Model.Terrains.TerrainTypes;
import com.example.civilization.Model.Units.UnitTypes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameMapController {

    @FXML
    private final ArrayList<Polygon> terrainHexagons = new ArrayList<>();
    private final TerrainPopUpController terrainPopUpController = new TerrainPopUpController();


    DatabaseController databaseController = DatabaseController.getInstance();

    @FXML
    private Pane pane;

    @FXML
    public void initialize() throws IOException {


        this.databaseController.getMap().generateMap();


        pane.setMaxSize(1300, 700);

//Adding coordinates to the polygon
        setHexagons(0, 0, 280, 130, 50);

        for (Polygon polygon : terrainHexagons) {
            pane.getChildren().add(polygon);
        }


    }

    public Double[] drawingPolygonWithCenterAndRadius(double x, double y, double radius) {
        return new Double[]{x + radius * Math.cos(Math.PI / 3), y + radius * Math.sin(Math.PI / 3), x + radius, y, x + radius * Math.cos(Math.PI / 3), y - radius * Math.sin(Math.PI / 3), x - radius * Math.cos(Math.PI / 3), y - radius * Math.sin(Math.PI / 3), x - radius, y, x - radius * Math.cos(Math.PI / 3), y + radius * Math.sin(Math.PI / 3),};

    }

    public void setHexagons(int start_x, int start_y, double x0, double y0, int radius) throws FileNotFoundException {
        //   System.out.println(pane.getMaxWidth());
        int i = start_x, j = start_y;
        // System.out.println(databaseController.getTerrainByCoordinates(1, 3).getTerrainTypes().name());
        for (double x = x0; x < pane.getMaxWidth() - radius; x += 3 * radius) {
            i = start_x;

            for (double y = y0; y < pane.getMaxHeight() - radius; y += 2 * radius * Math.sin(Math.PI / 3)) {
                i = hexagonsType(radius, i, j, x, y);

            }
            j += 2;

        }
        i = start_x;
        j = start_y + 1;

        for (double x = x0 + 1.5 * radius; x < pane.getMaxWidth() - radius; x += 3 * radius) {
            i = start_x;
            for (double y = y0 + radius * Math.sin(Math.PI / 3); y < pane.getMaxHeight() - radius; y += 2 * radius * Math.sin(Math.PI / 3)) {
                i = hexagonsType(radius, i, j, x, y);
            }
            j += 2;
        }
    }

    private int hexagonsType(int radius, int i, int j, double x, double y) throws FileNotFoundException {
        Polygon polygonTerrainType = new Polygon();
        Polygon polygonTerrainFeatureType = new Polygon();
        Polygon polygonCombatUnit = new Polygon();
        Polygon polygonNonCombatUnit = new Polygon();

        polygonTerrainType.getPoints().addAll(drawingPolygonWithCenterAndRadius(x, y, radius));
        polygonTerrainFeatureType.getPoints().addAll(drawingPolygonWithCenterAndRadius(x, y, radius));
        polygonCombatUnit.getPoints().addAll(drawingPolygonWithCenterAndRadius(x, y, radius));
        polygonNonCombatUnit.getPoints().addAll(drawingPolygonWithCenterAndRadius(x, y, radius));
        // System.out.println(i + " " + j + " " + databaseController.getTerrainByCoordinates(i, j).getTerrainTypes().name());
        polygonTerrainType.setFill(new ImagePattern(new Image(new FileInputStream(getImagePatternOfTiles(databaseController.getTerrainByCoordinates(i, j).getTerrainTypes().name())))));
        terrainHexagons.add(polygonTerrainType);
        if (!databaseController.getTerrainByCoordinates(i, j).getTerrainFeatureTypes().isEmpty() && databaseController.getTerrainByCoordinates(i, j).getTerrainFeatureTypes().get(0) != null) {
            //  System.out.println("fdf");
            //  System.out.println(i + " " + j + " " + databaseController.getTerrainByCoordinates(i, j).getTerrainFeatureTypes().get(0).name());// System.out.println("hello");
            polygonTerrainFeatureType.setFill(new ImagePattern(new Image(new FileInputStream(getImagePatternOfTiles(databaseController.getTerrainByCoordinates(i, j).getTerrainFeatureTypes().get(0).name())))));
            terrainHexagons.add(polygonTerrainFeatureType);

        }
        if (databaseController.getTerrainByCoordinates(i, j).getCombatUnit() != null) {

        }
        if (databaseController.getTerrainByCoordinates(i, j).getNonCombatUnit() != null) {

        }
        Polygon rivers = addingRivers(radius, i, j, x, y);
        terrainHexagons.add(rivers);

                /*if(databaseController.getTerrainByCoordinates(i, j).getType().equals("revealed")){
            polygonTerrainType.setOpacity(0.2);
            polygonTerrainFeatureType.setOpacity(0.2);
        }
        else if(databaseController.getTerrainByCoordinates(i, j).getType().equals("fog of war"){


            polygonTerrainFeatureType.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/civilization/PNG/civAsset/icons/OtherIcons/whiteDot.png"))));
            polygonTerrainType.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/civilization/PNG/civAsset/icons/OtherIcons/whiteDot.png"))));
       }

         */
       /* terrainTypeHexagons.add(polygonCombatUnit);
        terrainTypeHexagons.add(polygonNonCombatUnit);

*/

        showingPopUp(new ArrayList<>(Arrays.asList(rivers, polygonTerrainType, polygonTerrainFeatureType)), i, j);


        i++;
        return i;
    }

    public void showingPopUp(ArrayList<Polygon> polygons, int i, int j) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("FXML/terrainsPopUp.fxml"));
            Parent root = loader.load();
            TerrainPopUpController secController = loader.getController();
            secController.setData(databaseController.getTerrainByCoordinates(i, j));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            for (Polygon polygon : polygons) {
                polygon.setOnMousePressed(e ->{stage.show();});
                polygon.setOnMouseReleased(e -> stage.close());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Polygon addingRivers(int radius, int i, int j, double x, double y) throws FileNotFoundException {
        Polygon rivers = new Polygon();
        for (int a = -1; a < 2; a++) {
            for (int b = -1; b < 2; b++) {
                if (i + a >= 0 && i + a < databaseController.getMap().getCOL() && j + b >= 0 && j + b < databaseController.getMap().getROW()) {
                    if (databaseController.getMap().hasRiver(databaseController.getMap().getTerrain()[i + a][j + b], databaseController.getMap().getTerrain()[i][j]) != null) {

                        if (a == 1 && b == 0) {
                            //       System.out.println(i + " " + j + " " + a + " " + b);
                            rivers.getPoints().addAll(drawingPolygonWithCenterAndRadius(x, y, radius));
                            rivers.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/civilization/PNG/civAsset/map/Tiles/River-Bottom.png"))));
                        } else {
                            if ((j % 2 == 0 && a == 0 && b == 1) || (j % 2 == 1 && a == 1 && b == 1)) {
                                rivers.getPoints().addAll(drawingPolygonWithCenterAndRadius(x, y, radius));
                                rivers.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/civilization/PNG/civAsset/map/Tiles/River-BottomRight.png"))));


                            }
                            if ((j % 2 == 0 && a == 0 && b == 1) || (j % 2 == 1 && a == 1 && b == -1)) {
                                rivers.getPoints().addAll(drawingPolygonWithCenterAndRadius(x, y, radius));
                                rivers.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/civilization/PNG/civAsset/map/Tiles/River-BottomLeft.png"))));
                            }
                        }
                    }
                }
            }
        }
                /*if(databaseController.getTerrainByCoordinates(i, j).getType().equals("revealed")){
            rivers.setOpacity(0.2);

        }
        else if(databaseController.getTerrainByCoordinates(i, j).getType().equals("fog of war"){


            rivers.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/civilization/PNG/civAsset/icons/OtherIcons/whiteDot.png"))));
       }

         */
        return rivers;
    }

    public String getImagePatternOfTiles(String name) throws FileNotFoundException {
        for (TerrainTypes terrainTypes : TerrainTypes.values()) {
            if (terrainTypes.name().equalsIgnoreCase(name)) {
                // src/main/resources/com/example/civilization/PNG/civAsset/map/Tiles/DESERT.png
                return "src/main/resources/com/example/civilization/PNG/civAsset/map/Tiles/" + name + ".png";
            }
        }

        for (TerrainFeatureTypes terrainFeatureTypes : TerrainFeatureTypes.values()) {
            if (terrainFeatureTypes.name().equalsIgnoreCase(name)) {
                // src/main/resources/com/example/civilization/PNG/civAsset/map/Tiles/DESERT.png
                return "src/main/resources/com/example/civilization/PNG/civAsset/map/Tiles/" + name + ".png";
            }
        }

        for (UnitTypes unitTypes : UnitTypes.values()) {
            if (unitTypes.name().equalsIgnoreCase(name)) {
                return " src/main/resources/com/example/civilization/PNG/civAsset/units/Units/" + name + ".png";
            }
        }
        return null;
    }


}


