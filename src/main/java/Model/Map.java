package Model;

import java.util.ArrayList;

public class Map {
    private int Iteration = 6;
    private int size = 5;
    private int ROW = 20;
    private int COL = 8;

    private Tile[][] Tiles = new Tile[ROW][COL];
    private ArrayList<River> rivers = new ArrayList<River>();
    private String[][] Printmap = new String[ROW][Iteration];

    public River hasRiver(Tile tileFirst, Tile tileSecond) {
        for (River river : this.rivers) {
            if (river.getFirstTile() == tileFirst && river.getSecondTile() == tileSecond) {
                return river;
            } else if (river.getSecondTile() == tileFirst && river.getFirstTile() == tileSecond) {
                return river;
            }
        }
        return null;
    }

    public void addSpace(int row, int col, int count) {
        for (int i = 0; i < count; i++) {
            Printmap[row][col] += " ";
        }
    }

    // first row of map

    public void firstRow(int i, int j, int l, boolean check) {
        if (check == true) {
            if (!Tiles[i][l].getType().equals("fog of war")) {
                River river;
                if (l > 0 && ((river = hasRiver(Tiles[i][l], Tiles[i - 1][l - 1])) != null)) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "/";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "/";
                }
                Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < size; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;

            }
            if (!Tiles[i - 1][l + 1].getType().equals("fog of war") || !Tiles[i][l].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Tiles[i][l], Tiles[i - 1][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }

            }

            if (!Tiles[i - 1][l + 1].getType().equals("fog of war")) {

                String AllUnit = "";
                if (Tiles[i - 1][l + 1].getNonCombatUnit().getUnitType() != null) {
                    AllUnit += Tiles[i - 1][l + 1].getNonCombatUnit().getUnitType().getShowMap();
                }
                AllUnit += " ";
                if (Tiles[i - 1][l + 1].getCombatUnit().getUnitType() != null) {
                    AllUnit += Tiles[i - 1][l + 1].getCombatUnit().getUnitType().getShowMap();
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
                Printmap[i][j] += Tiles[i - 1][l + 1].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                int index = AllUnit.indexOf(" ");
                Printmap[i][j] += Color.BLUE;
                Printmap[i][j] += AllUnit.substring(0, index);
                Printmap[i][j] += " ";
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += AllUnit.substring(index + 1);
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;

            }

        } else {
            if (!Tiles[i][l].getType().equals("fog of war")) {
                Printmap[i][j] += "/";
                Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < size; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
                Printmap[i][j] += "\\";
            }
        }
    }

    // second row of map
    public void secondRow(int i, int j, int l, boolean check, Database database) {
        if (check == true) {

            if (!Tiles[i][l].getType().equals("fog of war")) {
                River river;
                if (l > 0 && ((river = hasRiver(Tiles[i][l], Tiles[i - 1][l - 1])) != null)) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "/";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "/";
                }
                String Civilization = "";
                if (database.getCivilizationUser(Tiles[i][l]) != null) {
                    Civilization += database.getCivilizationUser(Tiles[i][l]).getCivilization().getName();
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
                Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += Civilization;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
            }
            if (!Tiles[i - 1][l + 1].getType().equals("fog of war") || !Tiles[i][l].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Tiles[i][l], Tiles[i - 1][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }

            }

            if (!Tiles[i - 1][l + 1].getType().equals("fog of war")) {
                String TerrainFeatureType = "";
                if (Tiles[i - 1][l + 1].getTerrainFeatureTypes() != null) {
                    TerrainFeatureType += Tiles[i - 1][l + 1].getTerrainFeatureTypes().getShowFeatures();
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
                Printmap[i][j] += Tiles[i - 1][l + 1].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += TerrainFeatureType;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;

            }

        } else {
            Printmap[i][j] += "/";
            String Civilization = "";
            if (database.getCivilizationUser(Tiles[i][l]) != null) {
                Civilization += database.getCivilizationUser(Tiles[i][l]).getCivilization().getName();
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
            Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
            for (int count = 0; count < HowManySpaceLeft; count++) {
                Printmap[i][j] += " ";
            }
            Printmap[i][j] += Color.MAGENTA;
            Printmap[i][j] += Civilization;
            for (int count = 0; count < HowManySpaceRight; count++) {
                Printmap[i][j] += " ";
            }
            Printmap[i][j] += Color.RESET;
            Printmap[i][j] += "\\";

        }
    }

    // third row of map

    public void thirdRow(int i, int j, int l, boolean check) {

        if (check == true) {
            if (!Tiles[i][l].getType().equals("fog of war")) {
                River river;
                if (l > 0 && ((river = hasRiver(Tiles[i][l], Tiles[i - 1][l - 1])) != null)) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "/";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "/";
                }
                Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
                String XcenterYcenter = "";
                XcenterYcenter += Tiles[i][l].getX() + "," + Tiles[i][l].getY();
                int HowManySpace = 9 - XcenterYcenter.length();
                for (int count = 0; count < HowManySpace / 2; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += XcenterYcenter;
                for (int count = 0; count < HowManySpace / 2; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
            }
            if (!Tiles[i - 1][l + 1].getType().equals("fog of war") || !Tiles[i][l].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Tiles[i][l], Tiles[i - 1][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }

            }
            if (!Tiles[i - 1][l + 1].getType().equals("fog of war")) {
                Printmap[i][j] += Tiles[i - 1][l + 1].getTerrainTypes().getColor();
                for (int count = 0; count < size; count++) {
                    Printmap[i][j] += "_";
                }
                Printmap[i][j] += Color.RESET;
            }
        } else {
            Printmap[i][j] += "/";
            Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
            String XcenterYcenter = "";
            XcenterYcenter += Tiles[i][l].getX() + "," + Tiles[i][l].getY();
            int HowManySpace = 9 - XcenterYcenter.length();
            for (int count = 0; count < HowManySpace / 2; count++) {
                Printmap[i][j] += " ";
            }
            Printmap[i][j] += XcenterYcenter;
            for (int count = 0; count < HowManySpace / 2; count++) {
                Printmap[i][j] += " ";
            }
            Printmap[i][j] += Color.RESET;
            Printmap[i][j] += "\\";

        }
    }

    // fourth row of map
    public void fourthRow(int i, int j, int l, boolean check) {

        if (check == true) {
            if (!Tiles[i][l].getType().equals("fog of war")) {
                River river;
                if (l > 0 && ((river = hasRiver(Tiles[i][l], Tiles[i][l - 1])) != null)) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }
                String AllUnit = "";
                if (Tiles[i][l].getNonCombatUnit().getUnitType() != null) {
                    AllUnit += Tiles[i][l].getNonCombatUnit().getUnitType().getShowMap();
                }
                AllUnit += " ";
                if (Tiles[i][l].getCombatUnit().getUnitType() != null) {
                    AllUnit += Tiles[i][l].getCombatUnit().getUnitType().getShowMap();
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
                Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                int index = AllUnit.indexOf(" ");
                Printmap[i][j] += Color.BLUE;
                Printmap[i][j] += AllUnit.substring(0, index);
                Printmap[i][j] += " ";
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += AllUnit.substring(index + 1);
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;

            }
            if (!Tiles[i][l + 1].getType().equals("fog of war") || !Tiles[i][l].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Tiles[i][l], Tiles[i][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "/";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "/";
                }

            }
            if (!Tiles[i][l + 1].getType().equals("fog of war")) {
                Printmap[i][j] += Tiles[i][l + 1].getTerrainTypes().getColor();
                for (int count = 0; count < size; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
            }
        } else {
            Printmap[i][j] += "\\";
            String AllUnit = "";
            if (Tiles[i][l].getNonCombatUnit().getUnitType() != null) {
                AllUnit += Tiles[i][l].getNonCombatUnit().getUnitType().getShowMap();
            }
            AllUnit += " ";
            if (Tiles[i][l].getCombatUnit().getUnitType() != null) {
                AllUnit += Tiles[i][l].getCombatUnit().getUnitType().getShowMap();
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
            Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
            for (int count = 0; count < HowManySpaceLeft; count++) {
                Printmap[i][j] += " ";
            }
            int index = AllUnit.indexOf(" ");
            Printmap[i][j] += Color.BLUE;
            Printmap[i][j] += AllUnit.substring(0, index);
            Printmap[i][j] += " ";
            Printmap[i][j] += Color.MAGENTA;
            Printmap[i][j] += AllUnit.substring(index + 1);
            for (int count = 0; count < HowManySpaceRight; count++) {
                Printmap[i][j] += " ";
            }
            Printmap[i][j] += Color.RESET;
            Printmap[i][j] += "/";

        }
    }

    // fifth row of map
    public void fifthRow(int i, int j, int l, boolean check, Database database) {
        if (check == true) {

            if (!Tiles[i][l].getType().equals("fog of war")) {
                River river;
                if (l > 0 && ((river = hasRiver(Tiles[i][l], Tiles[i][l - 1])) != null)) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }
                String TerrainFeatureType = "";
                if (Tiles[i][l].getTerrainFeatureTypes() != null) {
                    TerrainFeatureType += Tiles[i][l].getTerrainFeatureTypes().getShowFeatures();
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
                Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += TerrainFeatureType;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
            }
            if (!Tiles[i][l + 1].getType().equals("fog of war") || !Tiles[i][l].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Tiles[i][l], Tiles[i][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "/";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "/";
                }
            }
            if (!Tiles[i][l + 1].getType().equals("fog of war")) {
                String Civilization = "";
                if (database.getCivilizationUser(Tiles[i][l + 1]) != null) {
                    Civilization += database.getCivilizationUser(Tiles[i][l + 1]).getCivilization().getName();
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
                Printmap[i][j] += Tiles[i][l + 1].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpaceLeft; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += Civilization;
                for (int count = 0; count < HowManySpaceRight; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;
            }
        } else {
            Printmap[i][j] += "\\";
            String TerrainFeatureType = "";
            if (Tiles[i][l].getTerrainFeatureTypes() != null) {
                TerrainFeatureType += Tiles[i][l].getTerrainFeatureTypes().getShowFeatures();
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
            Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
            for (int count = 0; count < HowManySpaceLeft; count++) {
                Printmap[i][j] += " ";
            }
            Printmap[i][j] += TerrainFeatureType;
            for (int count = 0; count < HowManySpaceRight; count++) {
                Printmap[i][j] += " ";
            }
            Printmap[i][j] += Color.RESET;
            Printmap[i][j] += "/";

        }
    }

    // sixth row of map
    public void sixthRow(int i, int j, int l, boolean check) {
        if (check == true) {

            if (!Tiles[i][l].getType().equals("fog of war")) {
                River river;
                if (l > 0 && ((river = hasRiver(Tiles[i][l], Tiles[i][l - 1])) != null)) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }
                Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < size; count++) {
                    Printmap[i][j] += "_";
                }
                Printmap[i][j] += Color.RESET;

            }
            if (!Tiles[i][l + 1].getType().equals("fog of war") || !Tiles[i][l].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Tiles[i][l], Tiles[i][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "/";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "/";
                }
            }
            if (!Tiles[i][l + 1].getType().equals("fog of war")) {
                Printmap[i][j] += Tiles[i][l = 1].getTerrainTypes().getColor();
                String XcenterYcenter = "";
                XcenterYcenter += Tiles[i][l + 1].getX() + "," + Tiles[i][l + 1].getY();
                int HowManySpace = 9 - XcenterYcenter.length();
                for (int count = 0; count < HowManySpace / 2; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += XcenterYcenter;
                for (int count = 0; count < HowManySpace / 2; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;

            }
        } else {
            Printmap[i][j] += "\\";
            Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
            for (int count = 0; count < size; count++) {
                Printmap[i][j] += "_";
            }
            Printmap[i][j] += Color.RESET;
            Printmap[i][j] += "/";
        }
    }

    public void printMap(Database database) {

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
                            if (i > 0) {
                                fourthRow(i, j, l, true);
                            } else {
                                fourthRow(i, j, l, false);
                            }
                            break;
                        case 4:
                            if (i > 0) {
                                fifthRow(i, j, l, true, database);
                            } else {
                                fifthRow(i, j, l, false, database);
                            }
                            break;
                        case 5:
                            if (i > 0) {
                                sixthRow(i, j, l, true);
                            } else {
                                sixthRow(i, j, l, false);
                            }
                            break;
                    }
                }
            }

        }

    }

}
