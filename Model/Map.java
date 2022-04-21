package Model;

import java.util.ArrayList;

public class Map {
    private int Iteration = 6;
    private int size = 5;
    private int ROW = 20;
    private int COL = 8;

    private Terrain[][] Tiles = new Terrain[ROW][COL];
    private ArrayList<River> rivers = new ArrayList<River>();
    private String[][] Printmap = new String[ROW][Iteration];

    public int getIteration() {
        return this.Iteration;
    }

    public void setIteration(int Iteration) {
        this.Iteration = Iteration;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getROW() {
        return this.ROW;
    }

    public void setROW(int ROW) {
        this.ROW = ROW;
    }

    public int getCOL() {
        return this.COL;
    }

    public void setCOL(int COL) {
        this.COL = COL;
    }

    public Terrain[][] getTiles() {
        return this.Tiles;
    }

    public void setTiles(Terrain[][] Tiles) {
        this.Tiles = Tiles;
    }

    public ArrayList<River> getRivers() {
        return this.rivers;
    }

    public void setRivers(ArrayList<River> rivers) {
        this.rivers = rivers;
    }

    public String[][] getPrintmap() {
        return this.Printmap;
    }

    public void setPrintmap(String[][] Printmap) {
        this.Printmap = Printmap;
    }

    public River hasRiver(Terrain tileFirst, Terrain tileSecond) {
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

    public void firstRow(int i, int j, int l, boolean check) {
        if (check == true) {
            if (!Tiles[i][l].getType().equals("fog of war")) {
                Printmap[i][j] += "/";
                Printmap[i][j] += Tiles[i][l].getTerrainTypes().getColor();
                for (int count = 0; count < size; count++) {
                    Printmap[i][j] += " ";
                }
                Printmap[i][j] += Color.RESET;

            }
            if (!Tiles[i - 1][l + 1].getType().equals("fog of war")) {
                River river;
                if ((river = hasRiver(Tiles[i][l], Tiles[i - 1][l + 1])) != null) {
                    Printmap[i][j] += river.getColor();
                    Printmap[i][j] += "\\";
                    Printmap[i][j] += Color.RESET;
                } else {
                    Printmap[i][j] += "\\";
                }

                String AllUnit = "";
                if (Tiles[i - 1][l + 1].getNonCombatUnit().getUnitType() != null) {
                    AllUnit += Tiles[i - 1][l + 1].getNonCombatUnit().getUnitType().getShowMap();
                }
                AllUnit += " ";
                if (Tiles[i - 1][l + 1].getCombatUnit().getUnitType() != null) {
                    AllUnit += Tiles[i - 1][l + 1].getCombatUnit().getUnitType().getShowMap();
                }

                int HowManySpace = 9 - AllUnit.length();
                Printmap[i][j] += Tiles[i - 1][l + 1].getTerrainTypes().getColor();
                for (int count = 0; count < HowManySpace / 2; count++) {
                    Printmap[i][j] += " ";
                }
                int index = AllUnit.indexOf(" ");
                Printmap[i][j] += Color.BLUE;
                Printmap[i][j] += AllUnit.substring(0, index);
                Printmap[i][j] += " ";
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += AllUnit.substring(index + 1);
                for (int count = 0; count < HowManySpace / 2; count++) {
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

    public void printMap() {

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

                            break;
                        case 2:

                            break;

                    }

                }

            }
            for (int j = Iteration / 2; j < Iteration; j++) {
                addSpace(i, j, j - Iteration / 2);
                switch (j) {

                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                }
            }

        }

    }

}
