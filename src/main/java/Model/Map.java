package Model;

import java.util.ArrayList;

import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;
import Model.Units.CombatUnit;
import Model.Units.NonCombatUnit;
import Model.Units.UnitTypes;

public class Map {
    private int Iteration = 6;
    private int size = 5;
    private int ROW = 20;
    private int COL = 16;

    private Terrain[][] Terrains = new Terrain[ROW][COL];
    private ArrayList<River> rivers = new ArrayList<River>();
    private String[][] Printmap = new String[ROW][Iteration];

    public River hasRiver(Terrain TerrainFirst, Terrain TerrainSecond) {
        for (River river : this.rivers) {
            if (river.getFirstTerrain() == TerrainFirst && river.getSecondTerrain() == TerrainSecond) {
                return river;
            } else if (river.getSecondTerrain() == TerrainFirst && river.getFirstTerrain() == TerrainSecond) {
                return river;
            }
        }
        return null;
    }

    public Terrain[][] getTerrain() {
        return this.Terrains;
    }

    public void addSpace(int row, int col, int count) {
        for (int i = 0; i < count; i++) {
            Printmap[row][col] += " ";
        }
    }

    // first row of map
    public void firstRow(int i, int j, int l, boolean check) {
        if (check == true) {
            if (!Terrains[i][l].getType().equals("fog of war")) {
                River river;
                if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l - 1])) != null)) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "/";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "/";
                }
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < size; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;

            } else {
                addSpace(i, j, size + 1);
            }
            if (!Terrains[i - 1][l + 1].getType().equals("fog of war")
                    || !Terrains[i][l].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }

            }

            if (!Terrains[i - 1][l + 1].getType().equals("fog of war")) {

                String AllUnit = "";
                if (Terrains[i - 1][l + 1].getNonCombatUnit().getUnitType() != null) {
                    AllUnit += Terrains[i - 1][l + 1].getNonCombatUnit().getUnitType().getShowMap();
                }
                AllUnit += " ";
                if (Terrains[i - 1][l + 1].getCombatUnit().getUnitType() != null) {
                    AllUnit += Terrains[i - 1][l + 1].getCombatUnit().getUnitType().getShowMap();
                }

                int HowManySpace = 9 - AllUnit.length();
                int HowManySpaceLeft = 0;
                int HowManySpaceRight = 0;
                if (HowManySpace % 2 == 0) {
                    HowManySpaceRight = HowManySpace / 2;
                    HowManySpaceLeft = HowManySpace / 2;
                } else {
                    HowManySpaceLeft = HowManySpace / 2;
                    HowManySpaceRight = HowManySpace / 2 + 1;
                }
                Printmap[i][j] += Terrains[i - 1][l + 1].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                int index = AllUnit.indexOf(" ");
                Printmap[i][j] += Color.BLUE;
                Printmap[i][j] += Terrains[i - 1][l + 1].getTerrainTypes().getColor();
                Printmap[i][j] += AllUnit.substring(0, index);
                Printmap[i][j] += " ";
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += Terrains[i - 1][l + 1].getTerrainTypes().getColor();
                Printmap[i][j] += AllUnit.substring(index + 1);
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;

            } else {
                addSpace(i, j, 10);
            }

        } else {
            if (!Terrains[i][l].getType().equals("fog of war")) {
                Printmap[i][j] += "/";
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < size; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
                Printmap[i][j] += "\\";
            } else {
                addSpace(i, j, 7);
            }
            addSpace(i, j, 9);
        }
    }

    // second row of map
    public void secondRow(int i, int j, int l, boolean check, Database database) {
        if (check == true) {

            if (!Terrains[i][l].getType().equals("fog of war")) {
                River river;
                if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l - 1])) != null)) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "/";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "/";
                }
                String Civilization = "";
                if (database.getCivilizationUser(Terrains[i][l]) != null) {
                    Civilization += database.getCivilizationUser(Terrains[i][l]).getCivilization().getName();
                }
                int HowManySpace = 7 - Civilization.length();
                int HowManySpaceLeft = 0;
                int HowManySpaceRight = 0;
                if (HowManySpace % 2 == 0) {
                    HowManySpaceRight = HowManySpace / 2;
                    HowManySpaceLeft = HowManySpace / 2;
                } else {
                    HowManySpaceLeft = HowManySpace / 2;
                    HowManySpaceRight = HowManySpace / 2 + 1;
                }
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                Printmap[i][j] += Civilization;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
            } else {
                addSpace(i, j, 8);
            }
            if (!Terrains[i - 1][l + 1].getType().equals("fog of war")
                    || !Terrains[i][l].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }

            }

            if (!Terrains[i - 1][l + 1].getType().equals("fog of war")) {
                String TerrainFeatureType = "";
                if (Terrains[i - 1][l + 1].getTerrainFeatureTypes() != null) {
                    TerrainFeatureType += Terrains[i - 1][l + 1].getTerrainFeatureTypes().getShowFeatures();
                }
                int HowManySpace = 7 - TerrainFeatureType.length();
                int HowManySpaceLeft = 0;
                int HowManySpaceRight = 0;
                if (HowManySpace % 2 == 0) {
                    HowManySpaceRight = HowManySpace / 2;
                    HowManySpaceLeft = HowManySpace / 2;
                } else {
                    HowManySpaceLeft = HowManySpace / 2;
                    HowManySpaceRight = HowManySpace / 2 + 1;
                }
                Printmap[i][j] += Terrains[i - 1][l + 1].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += TerrainFeatureType;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;

            } else {
                addSpace(i, j, 8);
            }

        } else {
            if (!Terrains[i][l].getType().equals("fog of war")) {
                Printmap[i][j] += "/";
                String Civilization = "";
                if (database.getCivilizationUser(Terrains[i][l]) != null) {
                    Civilization += database.getCivilizationUser(Terrains[i][l]).getCivilization().getName();
                }
                int HowManySpace = 7 - Civilization.length();
                int HowManySpaceLeft = 0;
                int HowManySpaceRight = 0;
                if (HowManySpace % 2 == 0) {
                    HowManySpaceRight = HowManySpace / 2;
                    HowManySpaceLeft = HowManySpace / 2;
                } else {
                    HowManySpaceLeft = HowManySpace / 2;
                    HowManySpaceRight = HowManySpace / 2 + 1;
                }
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                Printmap[i][j] += Civilization;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
                Printmap[i][j] += "\\";

            } else {
                addSpace(i, j, 9);
            }
            addSpace(i, j, 7);
        }

    }

    // third row of map
    public void thirdRow(int i, int j, int l, boolean check) {

        if (check == true) {
            if (!Terrains[i][l].getType().equals("fog of war")) {
                River river;
                if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l - 1])) != null)) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "/";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "/";
                }
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                String XcenterYcenter = "";
                XcenterYcenter += Terrains[i][l].getX() + "," + Terrains[i][l].getY();
                int HowManySpace = 9 - XcenterYcenter.length();
                int HowManySpaceLeft = 0;
                int HowManySpaceRight = 0;
                if (HowManySpace % 2 == 0) {
                    HowManySpaceLeft = HowManySpace / 2;
                    HowManySpaceRight = HowManySpace / 2;
                } else {
                    HowManySpaceLeft = HowManySpace / 2 + 1;
                    HowManySpaceRight = HowManySpace / 2;
                }
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += XcenterYcenter;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
            } else {
                addSpace(i, j, 10);
            }
            if (!Terrains[i - 1][l + 1].getType().equals("fog of war")
                    || !Terrains[i][l].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }

            }
            if (!Terrains[i - 1][l + 1].getType().equals("fog of war")) {
                Printmap[i][j] += Terrains[i - 1][l + 1].getTerrainTypes().getColor();
                for (int count = 0; count < size; count++) {
                    Printmap[i][j] += "_";
                }
                Printmap[i][j] += Color.RESET;
            } else {
                addSpace(i, j, 6);
            }
        } else {
            if (!Terrains[i][l].getType().equals("fog of war")) {
                Printmap[i][j] += "/";
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                String XcenterYcenter = "";
                XcenterYcenter += Terrains[i][l].getX() + "," + Terrains[i][l].getY();
                int HowManySpace = 9 - XcenterYcenter.length();
                int HowManySpaceLeft = 0;
                int HowManySpaceRight = 0;
                if (HowManySpace % 2 == 0) {
                    HowManySpaceLeft = HowManySpace / 2;
                    HowManySpaceRight = HowManySpace / 2;
                } else {
                    HowManySpaceLeft = HowManySpace / 2 + 1;
                    HowManySpaceRight = HowManySpace / 2;
                }
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += XcenterYcenter;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
                Printmap[i][j] += "\\";
            } else {
                addSpace(i, j, 11);
            }
            addSpace(i, j, 5);
        }
    }

    // fourth row of map
    public void fourthRow(int i, int j, int l, boolean check) {

        if (check == true) {
            if (!Terrains[i][l].getType().equals("fog of war")) {
                River river;
                if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i][l - 1])) != null)) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }
                String AllUnit = "";
                if (Terrains[i][l].getNonCombatUnit().getUnitType() != null) {
                    AllUnit += Terrains[i][l].getNonCombatUnit().getUnitType().getShowMap();
                }
                AllUnit += " ";
                if (Terrains[i][l].getCombatUnit().getUnitType() != null) {
                    AllUnit += Terrains[i][l].getCombatUnit().getUnitType().getShowMap();
                }

                int HowManySpace = 9 - AllUnit.length();
                int HowManySpaceLeft = 0;
                int HowManySpaceRight = 0;
                if (HowManySpace % 2 == 0) {
                    HowManySpaceRight = HowManySpace / 2;
                    HowManySpaceLeft = HowManySpace / 2;
                } else {
                    HowManySpaceLeft = HowManySpace / 2;
                    HowManySpaceRight = HowManySpace / 2 + 1;
                }
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                int index = AllUnit.indexOf(" ");
                Printmap[i][j] += Color.BLUE;
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                Printmap[i][j] += AllUnit.substring(0, index);
                Printmap[i][j] += " ";
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                Printmap[i][j] += AllUnit.substring(index + 1);
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;

            } else {
                addSpace(i, j, 10);
            }
            if (!Terrains[i][l + 1].getType().equals("fog of war") || !Terrains[i][l].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Terrains[i][l], Terrains[i][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "/";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "/";
                }

            }
            if (!Terrains[i][l + 1].getType().equals("fog of war")) {
                Printmap[i][j] += Terrains[i][l + 1].getTerrainTypes().getColor();
                for (int count = 0; count < size; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
            } else {
                addSpace(i, j, 6);
            }
        } else {

            if (!Terrains[i][l].getType().equals("fog of war")) {
                Printmap[i][j] += "\\";
                String AllUnit = "";
                if (Terrains[i][l].getNonCombatUnit().getUnitType() != null) {
                    AllUnit += Terrains[i][l].getNonCombatUnit().getUnitType().getShowMap();
                }
                AllUnit += " ";
                if (Terrains[i][l].getCombatUnit().getUnitType() != null) {
                    AllUnit += Terrains[i][l].getCombatUnit().getUnitType().getShowMap();
                }

                int HowManySpace = 9 - AllUnit.length();
                int HowManySpaceLeft = 0;
                int HowManySpaceRight = 0;
                if (HowManySpace % 2 == 0) {
                    HowManySpaceRight = HowManySpace / 2;
                    HowManySpaceLeft = HowManySpace / 2;
                } else {
                    HowManySpaceLeft = HowManySpace / 2;
                    HowManySpaceRight = HowManySpace / 2 + 1;
                }
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                int index = AllUnit.indexOf(" ");
                Printmap[i][j] += Color.BLUE;
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                Printmap[i][j] += AllUnit.substring(0, index);
                Printmap[i][j] += " ";
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                Printmap[i][j] += AllUnit.substring(index + 1);
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
                Printmap[i][j] += "/";
            } else {
                addSpace(i, j, 11);
            }
            addSpace(i, j, 5);
        }
    }

    // fifth row of map
    public void fifthRow(int i, int j, int l, boolean check, Database database) {
        if (check == true) {

            if (!Terrains[i][l].getType().equals("fog of war")) {
                River river;
                if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i][l - 1])) != null)) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }
                String TerrainFeatureType = "";
                if (Terrains[i][l].getTerrainFeatureTypes() != null) {
                    TerrainFeatureType += Terrains[i][l].getTerrainFeatureTypes().getShowFeatures();
                }
                int HowManySpace = 7 - TerrainFeatureType.length();
                int HowManySpaceLeft = 0;
                int HowManySpaceRight = 0;
                if (HowManySpace % 2 == 0) {
                    HowManySpaceRight = HowManySpace / 2;
                    HowManySpaceLeft = HowManySpace / 2;
                } else {
                    HowManySpaceLeft = HowManySpace / 2;
                    HowManySpaceRight = HowManySpace / 2 + 1;
                }
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += TerrainFeatureType;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
            } else {
                addSpace(i, j, 8);
            }
            if (!Terrains[i][l + 1].getType().equals("fog of war") || !Terrains[i][l].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Terrains[i][l], Terrains[i][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "/";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "/";
                }
            }
            if (!Terrains[i][l + 1].getType().equals("fog of war")) {
                String Civilization = "";
                if (database.getCivilizationUser(Terrains[i][l + 1]) != null) {
                    Civilization += database.getCivilizationUser(Terrains[i][l + 1]).getCivilization().getName();
                }
                int HowManySpace = 7 - Civilization.length();
                int HowManySpaceLeft = 0;
                int HowManySpaceRight = 0;
                if (HowManySpace % 2 == 0) {
                    HowManySpaceRight = HowManySpace / 2;
                    HowManySpaceLeft = HowManySpace / 2;
                } else {
                    HowManySpaceLeft = HowManySpace / 2;
                    HowManySpaceRight = HowManySpace / 2 + 1;
                }
                Printmap[i][j] += Terrains[i][l + 1].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += Terrains[i][l + 1].getTerrainTypes().getColor();
                Printmap[i][j] += Civilization;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
            } else {
                addSpace(i, j, 8);
            }
        } else {
            if (!Terrains[i][l].getType().equals("fog of war")) {
                Printmap[i][j] += "\\";
                String TerrainFeatureType = "";
                if (Terrains[i][l].getTerrainFeatureTypes() != null) {
                    TerrainFeatureType += Terrains[i][l].getTerrainFeatureTypes().getShowFeatures();
                }
                int HowManySpace = 7 - TerrainFeatureType.length();
                int HowManySpaceLeft = 0;
                int HowManySpaceRight = 0;
                if (HowManySpace % 2 == 0) {
                    HowManySpaceRight = HowManySpace / 2;
                    HowManySpaceLeft = HowManySpace / 2;
                } else {
                    HowManySpaceLeft = HowManySpace / 2;
                    HowManySpaceRight = HowManySpace / 2 + 1;
                }
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += TerrainFeatureType;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
                Printmap[i][j] += "/";
            } else {
                addSpace(i, j, 9);
            }
            addSpace(i, j, 7);
        }
    }

    // sixth row of map
    public void sixthRow(int i, int j, int l, boolean check) {
        if (check == true) {

            if (!Terrains[i][l].getType().equals("fog of war")) {
                River river;
                if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i][l - 1])) != null)) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < size; count++) {
                    Printmap[i][j] += "_";
                }
                Printmap[i][j] += Color.RESET;

            } else {
                addSpace(i, j, 6);
            }
            if (!Terrains[i][l + 1].getType().equals("fog of war") || !Terrains[i][l].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Terrains[i][l], Terrains[i][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "/";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "/";
                }
            }
            if (!Terrains[i][l + 1].getType().equals("fog of war")) {
                Printmap[i][j] += Terrains[i][l + 1].getTerrainTypes().getColor();
                String XcenterYcenter = "";
                XcenterYcenter += Terrains[i][l + 1].getX() + "," + Terrains[i][l + 1].getY();
                int HowManySpace = 9 - XcenterYcenter.length();
                int HowManySpaceLeft = 0;
                int HowManySpaceRight = 0;
                if (HowManySpace % 2 == 0) {
                    HowManySpaceLeft = HowManySpace / 2;
                    HowManySpaceRight = HowManySpace / 2;
                } else {
                    HowManySpaceLeft = HowManySpace / 2 + 1;
                    HowManySpaceRight = HowManySpace / 2;
                }
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += XcenterYcenter;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;

            } else {
                addSpace(i, j, 10);
            }
        } else {
            if (!Terrains[i][l].getType().equals("fog of war")) {
                Printmap[i][j] += "\\";
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < size; count++) {
                    Printmap[i][j] += "_";
                }
                Printmap[i][j] += Color.RESET;
                Printmap[i][j] += "/";
            } else {
                addSpace(i, j, 7);
            }
            addSpace(i, j, 9);
        }
    }

    public void printMap(Database database) {

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < Iteration; j++) {
                Printmap[i][j] = "";
            }
        }
        for (int i = 0; i < ROW; i++) {

            for (int j = 0; j < Iteration / 2; j++) {
                addSpace(i, j, Iteration / 2 - 1 - j);

                for (int l = 0; l < COL; l += 2) {

                    switch (j) {
                        case 0:
                            if (i > 0) {
                                firstRow(i, j, l, true);
                            } else {
                                firstRow(i, j, l, false);
                            }
                            break;
                        case 1:
                            if (i > 0) {
                                secondRow(i, j, l, true, database);
                            } else {
                                secondRow(i, j, l, false, database);
                            }
                            break;
                        case 2:
                            if (i > 0) {
                                thirdRow(i, j, l, true);
                            } else {
                                thirdRow(i, j, l, false);
                            }
                            break;

                    }

                }

            }
            for (int j = Iteration / 2; j < Iteration; j++) {
                addSpace(i, j, j - Iteration / 2);
                for (int l = 0; l < COL; l += 2) {
                    switch (j) {

                        case 3:
                            if (i != ROW - 1) {
                                fourthRow(i, j, l, true);
                            } else {
                                fourthRow(i, j, l, false);
                            }
                            break;
                        case 4:
                            if (i != ROW - 1) {
                                fifthRow(i, j, l, true, database);
                            } else {
                                fifthRow(i, j, l, false, database);
                            }
                            break;
                        case 5:
                            if (i != ROW - 1) {
                                sixthRow(i, j, l, true);
                            } else {
                                sixthRow(i, j, l, false);
                            }
                            break;
                    }
                }
            }

        }

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < Iteration; j++) {
                System.out.println(Printmap[i][j]);
            }
        }
    }

    public void initializeMap() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {

                switch ((i * j) % 3) {
                    case 0:
                        CombatUnit CombatUnit = new CombatUnit(i, j, 3, 12, false, UnitTypes.ARCHER);
                        NonCombatUnit nonCombatUnit = new NonCombatUnit(i, j, 3, 12, false, UnitTypes.SETTLER);
                        Terrain terrain = new Terrain(i, j, "clear", TerrainTypes.DESERT, TerrainFeatureTypes.FOREST,
                                CombatUnit, nonCombatUnit);
                        Terrains[i][j] = terrain;
                        break;
                    case 1:
                        CombatUnit CombatUnit1 = new CombatUnit(i, j, 3, 12, false, UnitTypes.CANNON);
                        NonCombatUnit nonCombatUnit1 = new NonCombatUnit(i, j, 3, 12, false, UnitTypes.SETTLER);
                        Terrain terrain1 = new Terrain(i, j, "clear", TerrainTypes.OCEAN, TerrainFeatureTypes.OASIS,
                                CombatUnit1, nonCombatUnit1);
                        Terrains[i][j] = terrain1;
                        break;
                    case 2:
                        CombatUnit CombatUnit2 = new CombatUnit(i, j, 3, 12, false, UnitTypes.TANK);
                        NonCombatUnit nonCombatUnit2 = new NonCombatUnit(i, j, 3, 12, false, UnitTypes.SETTLER);
                        Terrain terrain2 = new Terrain(i, j, "clear", TerrainTypes.SNOW, TerrainFeatureTypes.JUNGLE,
                                CombatUnit2, nonCombatUnit2);
                        Terrains[i][j] = terrain2;
                        break;

                }
            }
        }
    }

}
