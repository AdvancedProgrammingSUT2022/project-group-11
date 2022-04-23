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
    private int ROW = 25;
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

    /// ADD SPACE FOR FIRST IN ROWS
    public void addSpace(int row, int col, int count) {
        for (int i = 0; i < count; i++) {
            Printmap[row][col] += " ";
        }
    }

    /// EMPTY PRINT
    public void EmptyFirstHalf(int i, int j, int l) {
        River river;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "/";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "/";
        }
        if (!Terrains[i][l].getType().equals("fog of war")) {

            Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
            for (int count = 0; count < size; count++) {
                Printmap[i][j] += " ";
            }
            Printmap[i][j] += Color.RESET;

        } else {
            addSpace(i, j, size);
        }
    }

    public void EmptySecondHalf(int i, int j, int l) {
        if (!Terrains[i][l + 1].getType().equals("fog of war")) {
            Printmap[i][j] += Terrains[i][l + 1].getTerrainTypes().getColor();
            for (int count = 0; count < size; count++) {
                Printmap[i][j] += " ";
            }
            Printmap[i][j] += Color.RESET;
        } else {
            addSpace(i, j, 5);
        }
    }

    /// BETWEENT TWO TAIL
    public void betweetTwoTailFirstHalf(int i, int j, int l) {

        River river;
        if ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l + 1])) != null) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "\\";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "\\";
        }

    }

    public void betweenTwoTailSecondHalf(int i, int j, int l) {

        River river;
        if ((river = hasRiver(Terrains[i][l], Terrains[i][l + 1])) != null) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "/";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "/";
        }

    }

    /// TWO COMBAT TYPE
    public void twoCombatFirstHalf(int i, int j, int l) {
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
            addSpace(i, j, 9);
        }
    }

    public void twoCombatSecondHalf(int i, int j, int l) {
        River river;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "\\";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "\\";
        }
        if (!Terrains[i][l].getType().equals("fog of war")) {

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
            addSpace(i, j, 9);
        }
    }

    /// CIVILIZATION
    public void CivilizationPrintFirstHalf(int i, int j, int l, Database database) {
        River river;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "/";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "/";
        }
        if (!Terrains[i][l].getType().equals("fog of war")) {

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
            addSpace(i, j, 7);
        }
    }

    public void CivilizationPrintSecondHalf(int i, int j, int l, Database database) {
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
            addSpace(i, j, 7);
        }
    }

    /// TERRAIN FEATURES
    public void TerrainFeaturesFirstHalf(int i, int j, int l) {

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
            addSpace(i, j, 7);
        }
    }

    public void TerrainFeaturesSecondHalf(int i, int j, int l) {
        River river;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "\\";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "\\";
        }
        if (!Terrains[i][l].getType().equals("fog of war")) {

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
            addSpace(i, j, 7);
        }
    }

    //// X AND Y
    public void XandYFirstHalf(int i, int j, int l) {
        River river;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "/";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "/";
        }
        if (!Terrains[i][l].getType().equals("fog of war")) {

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
            addSpace(i, j, 9);
        }
    }

    public void XandYSecondHalf(int i, int j, int l) {
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
            addSpace(i, j, 9);
        }
    }

    //// UNDELINE
    public void UnderlineFirstHalf(int i, int j, int l) {
        if (!Terrains[i - 1][l + 1].getType().equals("fog of war")) {
            Printmap[i][j] += Terrains[i - 1][l + 1].getTerrainTypes().getColor();
            for (int count = 0; count < size; count++) {
                Printmap[i][j] += "_";
            }
            Printmap[i][j] += Color.RESET;
        } else {
            addSpace(i, j, 5);
        }
    }

    public void UnderlineSecondHalf(int i, int j, int l) {
        River river;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "\\";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "\\";
        }
        if (!Terrains[i][l].getType().equals("fog of war")) {

            Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
            for (int count = 0; count < size; count++) {
                Printmap[i][j] += "_";
            }
            Printmap[i][j] += Color.RESET;

        } else {
            addSpace(i, j, 5);
        }
    }

    //// else Print of Rows
    public void elseFirstRow(int i, int j, int l) {
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

    public void elseSecondRow(int i, int j, int l, Database database) {
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

    public void elseThirdRow(int i, int j, int l) {
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

    public void elseFourthRow(int i, int j, int l) {
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

    public void elseFifthRow(int i, int j, int l) {
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

    public void elseSixthRow(int i, int j, int l) {
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

    /// RowsOfMap
    public void firstRow(int i, int j, int l, boolean check) {
        if (check == true) {
            EmptyFirstHalf(i, j, l);
            betweetTwoTailFirstHalf(i, j, l);
            twoCombatFirstHalf(i, j, l);
        } else {
            elseFirstRow(i, j, l);
        }
    }

    public void secondRow(int i, int j, int l, boolean check, Database database) {
        if (check == true) {
            CivilizationPrintFirstHalf(i, j, l, database);
            betweetTwoTailFirstHalf(i, j, l);
            TerrainFeaturesFirstHalf(i, j, l);
        } else {
            elseSecondRow(i, j, l, database);
        }

    }

    public void thirdRow(int i, int j, int l, boolean check) {

        if (check == true) {
            XandYFirstHalf(i, j, l);
            betweetTwoTailFirstHalf(i, j, l);
            UnderlineFirstHalf(i, j, l);
        } else {
            elseThirdRow(i, j, l);
        }
    }

    public void fourthRow(int i, int j, int l, boolean check) {

        if (check == true) {
            twoCombatSecondHalf(i, j, l);
            betweenTwoTailSecondHalf(i, j, l);
            EmptySecondHalf(i, j, l);
        } else {
            elseFourthRow(i, j, l);
        }
    }

    public void fifthRow(int i, int j, int l, boolean check, Database database) {
        if (check == true) {
            TerrainFeaturesSecondHalf(i, j, l);
            betweenTwoTailSecondHalf(i, j, l);
            CivilizationPrintSecondHalf(i, j, l, database);
        } else {
            elseFifthRow(i, j, l);
        }
    }

    public void sixthRow(int i, int j, int l, boolean check) {
        if (check == true) {
            UnderlineSecondHalf(i, j, l);
            betweenTwoTailSecondHalf(i, j, l);
            XandYSecondHalf(i, j, l);
        } else {
            elseSixthRow(i, j, l);
        }
    }

    // initialize Map Before Print For Speciall User
    public boolean isBlock(int i, int j) {

        if (Terrains[i][j].getTerrainTypes() == TerrainTypes.MOUNTAIN) {
            return false;
        } else if (Terrains[i][j].getTerrainTypes() == TerrainTypes.HILLS) {
            return false;
        } else if (Terrains[i][j].getTerrainFeatureTypes() == TerrainFeatureTypes.JUNGLE) {
            return false;
        }
        return true;
    }

    public void setVisible(int i, int j) {
        if (i > 0) {
            Terrains[i - 1][j].setType("visible");
        }
        if (i > 0 && j > 0) {
            Terrains[i - 1][j - 1].setType("visible");
        }
        if (j > 0) {
            Terrains[i][j - 1].setType("visible");
        }
        if (i < ROW - 1) {
            Terrains[i + 1][j].setType("visible");
        }
        if (j < COL - 1) {

            Terrains[i][j + 1].setType("visible");
        }
        if (i > 0 && j < COL - 1) {
            Terrains[i - 1][j + 1].setType("visible");
        }
    }

    public void initializeMapUser(User user) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                Terrains[i][j].setType("fog of war");
            }
        }

        ArrayList<Terrain> Tiles = user.getCivilization().getTerrains();
        for (Terrain tile : Tiles) {
            int i = tile.getX();
            int j = tile.getY();
            Terrains[i][j].setType("visible");
            if (isBlock(i, j)) {
                setVisible(i, j);
                if (isBlock(i - 1, j)) {
                    setVisible(i - 1, j);
                }
                if (isBlock(i - 1, j - 1)) {
                    setVisible(i - 1, j - 1);
                }
                if (isBlock(i, j - 1)) {
                    setVisible(i, j - 1);
                }
                if (isBlock(i + 1, j)) {
                    setVisible(i + 1, j);
                }
                if (isBlock(i, j + 1)) {
                    setVisible(i, j + 1);
                }
                if (isBlock(i - 1, j + 1)) {
                    setVisible(i - 1, j + 1);
                }
            }
        }

        for(int i = 0 ; i < ROW;i++){
            for(int j = 0; j < COL;j++){
                if(Terrains[i][j].getType().equals("fog of war")){
                    for (Revealed Reveals : Terrains[i][j].getReveals()) {
                        if(Reveals.getUser() == user){
                            Terrains[i][j].setType("revealed");
                            break;
                        }
                    }
                }
            }
        }
    }







    // print map
    public void SwichCaseFirstHalf(int i, int j, int l, Database database) {
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

    public void SwichCaseSecondHalf(int i, int j, int l, Database database) {
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

    public String[][] printMap(Database database) {

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < Iteration; j++) {
                Printmap[i][j] = "";
            }
        }
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < Iteration / 2; j++) {
                addSpace(i, j, Iteration / 2 - 1 - j);
                for (int l = 0; l < COL; l += 2) {
                    SwichCaseFirstHalf(i, j, l, database);
                }

            }
            for (int j = Iteration / 2; j < Iteration; j++) {
                addSpace(i, j, j - Iteration / 2);
                for (int l = 0; l < COL; l += 2) {
                    SwichCaseSecondHalf(i, j, l, database);
                }
            }
        }
        return this.Printmap;
    }






    public void initializeMap() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {

                switch ((i * j) % 3) {
                    case 0:
                        CombatUnit CombatUnit = new CombatUnit(i, j, 3, 12, false, UnitTypes.ARCHER);
                        NonCombatUnit nonCombatUnit = new NonCombatUnit(i, j, 3, 12, false, UnitTypes.SETTLER);
                        Terrain terrain = new Terrain(i, j, "clear", TerrainTypes.DESERT, TerrainFeatureTypes.FOREST,
                                CombatUnit, nonCombatUnit, null);
                        Terrains[i][j] = terrain;
                        break;
                    case 1:
                        CombatUnit CombatUnit1 = new CombatUnit(i, j, 3, 12, false, UnitTypes.CANNON);
                        NonCombatUnit nonCombatUnit1 = new NonCombatUnit(i, j, 3, 12, false, UnitTypes.SETTLER);
                        Terrain terrain1 = new Terrain(i, j, "fog of war", TerrainTypes.OCEAN,
                                TerrainFeatureTypes.OASIS,
                                CombatUnit1, nonCombatUnit1, null);
                        Terrains[i][j] = terrain1;
                        break;
                    case 2:
                        CombatUnit CombatUnit2 = new CombatUnit(i, j, 3, 12, false, UnitTypes.TANK);
                        NonCombatUnit nonCombatUnit2 = new NonCombatUnit(i, j, 3, 12, false, UnitTypes.SETTLER);
                        Terrain terrain2 = new Terrain(i, j, "clear", TerrainTypes.SNOW, TerrainFeatureTypes.JUNGLE,
                                CombatUnit2, nonCombatUnit2, null);
                        Terrains[i][j] = terrain2;
                        break;

                }
            }
        }
    }

}
