package Model;

import java.util.ArrayList;
import java.util.Random;

import Model.Resources.ResourceTypes;
import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;

public class Map {
    private int Iteration = 6;
    private int ROW = 32;
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
    public River hasRiver(Terrain TerrainFirst) {
        for (River river : this.rivers) {
            if (river.getFirstTerrain() == TerrainFirst) {
                return river;
            } else if (river.getSecondTerrain() == TerrainFirst) {
                return river;
            }
        }
        return null;
    }

    public int getIteration() {
        return this.Iteration;
    }

    public void setIteration(int Iteration) {
        this.Iteration = Iteration;
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

    public Terrain[][] getTerrain() {
        return this.Terrains;
    }




    
    /// ADD SPACE FOR FIRST IN ROWS
    public void addSpace(int row, int col, int count) {
        for (int i = 0; i < count; i++) {
            Printmap[row][col] += " ";
        }
    }

    public void addUnderline(int row, int col, int count) {
        for (int i = 0; i < count; i++) {
            Printmap[row][col] += "_";
        }
    }

    public int returnHowManySpaceInLeft(int HowManySpace) {
        return HowManySpace / 2;
    }

    public int returnHowManySpaceInRight(int HowManySpace) {
        int HowManySpaceRight = 0;
        if (HowManySpace % 2 == 0) {
            HowManySpaceRight = HowManySpace / 2;
        } else {
            HowManySpaceRight = HowManySpace / 2 + 1;
        }
        return HowManySpaceRight;
    }

    public Revealed getRevealedFromArray(User user, int i, int l) {
        for (int count = Terrains[i][l].getReveals().size() - 1; count >= 0; count--) {
            if (Terrains[i][l].getReveals().get(count).getUser() == user) {
                return Terrains[i][l].getReveals().get(count);
            }
        }
        return null;
    }







    /// BETWEENT TWO TAIL
    private void betweetTwoTailFirstHalf(int i, int j, int l) {

        River river;
        if (i > 0 && l < COL - 1 && (river = hasRiver(Terrains[i][l], Terrains[i - 1][l + 1])) != null) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "\\";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "\\";
        }

    }

    private void betweenTwoTailSecondHalf(int i, int j, int l) {
        River river;
        if (l < COL - 1 && (river = hasRiver(Terrains[i][l], Terrains[i][l + 1])) != null) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "/";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "/";
        }
    }


    // ALGHORITMS
    private void CivilizationAlghoritm(int iTerrain, int i, int j, int l, Database database) {
        String Civilization = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        if (Terrains[iTerrain][l].getType().equals("revealed")) {
            Civilization = "REV";
            int HowManySpace = 5 - Civilization.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            addSpace(i, j, HowManySpaceLeft);
            Printmap[i][j] += Color.MAGENTA;
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            Printmap[i][j] += Civilization;
            addSpace(i, j, HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else if (Terrains[iTerrain][l].getType().equals("visible")) {
            if (database.getCivilizationUser(Terrains[iTerrain][l]) != null) {
                Civilization += database.getCivilizationUser(Terrains[iTerrain][l]).getCivilization().getName();
            }
            int HowManySpace = 5 - Civilization.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Terrains[iTerrain][l].getTerrainTypes().getColor();
            addSpace(i, j, HowManySpaceLeft);
            Printmap[i][j] += Color.MAGENTA;
            Printmap[i][j] += Terrains[iTerrain][l].getTerrainTypes().getColor();
            Printmap[i][j] += Civilization;
            addSpace(i, j, HowManySpaceRight);
            Printmap[i][j] += Color.RESET;

        } else {
            addSpace(i, j, 5);
        }
    }

    private void TerrainFeaturesAlghoritm(int iTerrain, int i, int j, int l, User user) {
        String TerrainFeatureType = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        if (Terrains[iTerrain][l].getType().equals("revealed")) {
            if (getRevealedFromArray(user, iTerrain, l).getTerrainFeatureTypes() != null) {
                TerrainFeatureType += getRevealedFromArray(user, iTerrain, l).getTerrainFeatureTypes()
                        .getShowFeatures();
            }
            int HowManySpace = 9 - TerrainFeatureType.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            addSpace(i, j, HowManySpaceLeft);
            Printmap[i][j] += TerrainFeatureType;
            addSpace(i, j, HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else if (Terrains[iTerrain][l].getType().equals("visible")) {
            if (Terrains[iTerrain][l].getTerrainFeatureTypes() != null && Terrains[iTerrain][l].getTerrainFeatureTypes().size() > 0) {
                TerrainFeatureType += Terrains[iTerrain][l].getTerrainFeatureTypes().get(0).getShowFeatures();
            }
            int HowManySpace = 9 - TerrainFeatureType.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Terrains[iTerrain][l].getTerrainTypes().getColor();
            addSpace(i, j, HowManySpaceLeft);
            Printmap[i][j] += TerrainFeatureType;
            addSpace(i, j, HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else {
            addSpace(i, j, 9);
        }
    }

    private void XandYAlghoritm(int iTerrain, int i, int j, int l) {
        String XcenterYcenter = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        if (Terrains[iTerrain][l].getType().equals("revealed")) {
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            XcenterYcenter += Terrains[iTerrain][l].getX() + "," + Terrains[iTerrain][l].getY();
            int HowManySpace = 7 - XcenterYcenter.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            addSpace(i, j, HowManySpaceLeft);
            Printmap[i][j] += XcenterYcenter;
            addSpace(i, j, HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else if (Terrains[iTerrain][l].getType().equals("visible")) {
            Printmap[i][j] += Terrains[iTerrain][l].getTerrainTypes().getColor();
            XcenterYcenter += Terrains[iTerrain][l].getX() + "," + Terrains[iTerrain][l].getY();
            int HowManySpace = 7 - XcenterYcenter.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            addSpace(i, j, HowManySpaceLeft);
            Printmap[i][j] += XcenterYcenter;
            addSpace(i, j, HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else {
            addSpace(i, j, 7);
        }
    }

    private void twocombatAlghoritm(int iTerrain, int i, int j, int l, User user) {
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        String AllUnit = "";
        if (Terrains[iTerrain][l].getType().equals("revealed")) {
            if (getRevealedFromArray(user, iTerrain, l).getNonCombatUnit() != null) {
                AllUnit += getRevealedFromArray(user, iTerrain, l).getNonCombatUnit().getUnitType().getShowMap();
            }
            AllUnit += " ";
            if (getRevealedFromArray(user, iTerrain, l).getCombatUnit() != null) {
                AllUnit += getRevealedFromArray(user, iTerrain, l).getCombatUnit().getUnitType().getShowMap();
            }
            int HowManySpace = 9 - AllUnit.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            addSpace(i, j, HowManySpaceLeft);
            int index = AllUnit.indexOf(" ");
            Printmap[i][j] += Color.RED;
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            Printmap[i][j] += AllUnit.substring(0, index);
            Printmap[i][j] += " ";
            Printmap[i][j] += Color.RED;
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            Printmap[i][j] += AllUnit.substring(index + 1);
            addSpace(i, j, HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else if (Terrains[iTerrain][l].getType().equals("visible")) {
            if (Terrains[iTerrain][l].getNonCombatUnit() != null) {
                AllUnit += Terrains[iTerrain][l].getNonCombatUnit().getUnitType().getShowMap();
            }
            AllUnit += " ";
            if (Terrains[i][l].getCombatUnit() != null) {
                AllUnit += Terrains[iTerrain][l].getCombatUnit().getUnitType().getShowMap();
            }
            int HowManySpace = 9 - AllUnit.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Terrains[iTerrain][l].getTerrainTypes().getColor();
            addSpace(i, j, HowManySpaceLeft);
            int index = AllUnit.indexOf(" ");
            Printmap[i][j] += Color.BLUE;
            Printmap[i][j] += Terrains[iTerrain][l].getTerrainTypes().getColor();
            Printmap[i][j] += AllUnit.substring(0, index);
            Printmap[i][j] += " ";
            Printmap[i][j] += Color.MAGENTA;
            Printmap[i][j] += Terrains[iTerrain][l].getTerrainTypes().getColor();
            Printmap[i][j] += AllUnit.substring(index + 1);
            addSpace(i, j, HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else {
            addSpace(i, j, 9);
        }
    }

    private void ResourceAlghoritm(int iTerrain, int i, int j, int l, User user) {
        String Resource = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        if (Terrains[iTerrain][l].getType().equals("revealed")) {
            if (getRevealedFromArray(user, iTerrain, l).getTerrainResource() != null) {
                Resource += getRevealedFromArray(user, iTerrain, l).getTerrainResource().getResourceType().getShowResourceMap();
            }
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            int HowManySpace = 7 - Resource.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            addSpace(i, j, HowManySpaceLeft);
            Printmap[i][j] += Resource;
            addSpace(i, j, HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else if (Terrains[iTerrain][l].getType().equals("visible")) {
            if (Terrains[iTerrain][l].getTerrainResource() != null) {
                Resource += Terrains[iTerrain][l].getTerrainResource().getResourceType().getShowResourceMap();
            }
            Printmap[i][j] += Terrains[iTerrain][l].getTerrainTypes().getColor();
            int HowManySpace = 7 - Resource.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            addSpace(i, j, HowManySpaceLeft);
            Printmap[i][j] += Resource;
            addSpace(i, j, HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else {
            addSpace(i, j, 7);
        }

    }

    private void ImprovementAlghoritm(int iTerrain, int i, int j, int l, User user) {
        String Improvement = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        if (Terrains[iTerrain][l].getType().equals("revealed")) {
            if (getRevealedFromArray(user, iTerrain, l).getTerrrainImprovement() != null) {
                Improvement += getRevealedFromArray(user, iTerrain, l).getTerrrainImprovement().getShowImprovement();
            }
            int HowManySpace = 5 - Improvement.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            addUnderline(i, j, HowManySpaceLeft);
            Printmap[i][j] += Improvement;
            addUnderline(i, j, HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else if (Terrains[iTerrain][l].getType().equals("visible")) {
            if (Terrains[iTerrain][l].getTerrrainImprovement() != null) {
                Improvement += Terrains[iTerrain][l].getTerrrainImprovement().getShowImprovement();
            }
            int HowManySpace = 5 - Improvement.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Terrains[iTerrain][l].getTerrainTypes().getColor();
            addUnderline(i, j, HowManySpaceLeft);
            Printmap[i][j] += Improvement;
            addUnderline(i, j, HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else {
            addUnderline(i, j, 5);
        }

    }



















//  call the alghoritm finction

    private void CivilizationPrintFirstHalf(int i, int j, int l, Database database) {
        River river;
        if (i > 0 && l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "/";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "/";
        }
        CivilizationAlghoritm(i, i, j, l, database);
    }

    private void CivilizationPrintSecondHalf(int i, int j, int l, Database database) {
        CivilizationAlghoritm(i, i, j, l + 1, database);
    }

    private void TerrainFeaturesFirstHalf(int i, int j, int l, User user) {
        TerrainFeaturesAlghoritm(i - 1, i, j, l + 1, user);
    }

    private void TerrainFeaturesSecondHalf(int i, int j, int l, User user) {
        River river;

        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "\\";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "\\";
        }
        TerrainFeaturesAlghoritm(i, i, j, l, user);

    }

    private void XandYFirstHalf(int i, int j, int l) {
        River river;
        if (i > 0 && l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "/";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "/";
        }
        XandYAlghoritm(i, i, j, l);
    }

    private void XandYSecondHalf(int i, int j, int l) {
        XandYAlghoritm(i, i, j, l + 1);
    }

    private void ResourceFirstHalf(int i, int j, int l, User user) {
        ResourceAlghoritm(i - 1, i, j, l + 1, user);
    }

    private void ResourceSecondHalf(int i, int j, int l, User user) {
        River river;

        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "\\";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "\\";
        }
        ResourceAlghoritm(i, i, j, l, user);
    }

    private void twoCombatFirstHalf(int i, int j, int l, User user) {

        River river;
        if (i > 0 && l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "/";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "/";
        }
        twocombatAlghoritm(i, i, j, l, user);
    }

    private void twoCombatSecondHalf(int i, int j, int l, User user) {
        twocombatAlghoritm(i, i, j, l + 1, user);
    }

    private void ImprovementFirstHalf(int i, int j, int l, User user) {
        ImprovementAlghoritm(i - 1, i, j, l + 1, user);
    }

    private void ImprovementSecondHalf(int i, int j, int l, User user) {
        River river;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "\\";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "\\";
        }
        ImprovementAlghoritm(i, i, j, l, user);
    }
















    // Rows Of Map
    private void firstRow(int i, int j, int l, boolean check, Database database, User user) {
        if (check == true) {
            CivilizationPrintFirstHalf(i, j, l, database);
            betweetTwoTailFirstHalf(i, j, l);
            TerrainFeaturesFirstHalf(i, j, l, user);
        } else {
            CivilizationPrintFirstHalf(i, j, l, database);
            betweetTwoTailFirstHalf(i, j, l);
            addSpace(i, j, 9);
        }
    }

    private void secondRow(int i, int j, int l, boolean check, Database database, User user) {
        if (check == true) {
            XandYFirstHalf(i, j, l);
            betweetTwoTailFirstHalf(i, j, l);
            ResourceFirstHalf(i, j, l, user);
        } else {
            XandYFirstHalf(i, j, l);
            betweetTwoTailFirstHalf(i, j, l);
            addSpace(i, j, 7);
        }

    }

    private void thirdRow(int i, int j, int l, boolean check, User user) {

        if (check == true) {
            twoCombatFirstHalf(i, j, l, user);
            betweetTwoTailFirstHalf(i, j, l);
            ImprovementFirstHalf(i, j, l, user);
        } else {
            twoCombatFirstHalf(i, j, l, user);
            betweetTwoTailFirstHalf(i, j, l);
            addUnderline(i, j, 5);
        }
    }

    private void fourthRow(int i, int j, int l, boolean check, Database database, User user) {

        if (check == true) {
            TerrainFeaturesSecondHalf(i, j, l, user);
            betweenTwoTailSecondHalf(i, j, l);
            CivilizationPrintSecondHalf(i, j, l, database);
        } else {
            TerrainFeaturesSecondHalf(i, j, l, user);
            betweenTwoTailSecondHalf(i, j, l);
            addSpace(i, j, 5);
        }
    }

    private void fifthRow(int i, int j, int l, boolean check, Database database, User user) {
        if (check == true) {
            ResourceSecondHalf(i, j, l, user);
            betweenTwoTailSecondHalf(i, j, l);
            XandYSecondHalf(i, j, l);
        } else {
            ResourceSecondHalf(i, j, l, user);
            betweenTwoTailSecondHalf(i, j, l);
            addSpace(i, j, 7);
        }
    }

    private void sixthRow(int i, int j, int l, boolean check, User user) {
        if (check == true) {
            ImprovementSecondHalf(i, j, l, user);
            betweenTwoTailSecondHalf(i, j, l);
            twoCombatSecondHalf(i, j, l, user);
        } else {
            ImprovementSecondHalf(i, j, l, user);
            betweenTwoTailSecondHalf(i, j, l);
            addSpace(i, j, 9);
        }
    }










    // initialize Map Before Print For Speciall User
    private boolean isBlock(int i, int j) {
        if(i < 0 || i >= ROW){
            return false;
        }
        if(j < 0 || j >= COL){
            return false;
        }
        if (Terrains[i][j].getTerrainTypes() == TerrainTypes.MOUNTAIN) {
            return false;
        } else if (Terrains[i][j].getTerrainTypes() == TerrainTypes.HILLS) {
            return false;
        } else if (Terrains[i][j].getTerrainFeatureTypes().indexOf(TerrainFeatureTypes.JUNGLE) != -1) {
            return false;
        }
        return true;
    }

    private void setVisibleEven(int i, int j) {
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
 
    private void setVisibleOdd(int i,int j){
        if (i > 0) {
            Terrains[i - 1][j].setType("visible");
        }
        if (j > 0) {
            Terrains[i][j - 1].setType("visible");
        }
        if(i < ROW - 1 && j > 0){
            Terrains[i + 1][j - 1].setType("visible");
        }
        if (i < ROW - 1) {
            Terrains[i + 1][j].setType("visible");
        }
        if(i < ROW - 1 && j < COL - 1){
            Terrains[i + 1][j + 1].setType("visible");
        }
        if (j < COL - 1) {
            Terrains[i][j + 1].setType("visible");
        }


    }

    private void makeVisibleNearEven(int i,int j){
        if (isBlock(i - 1, j)) {
            if(j % 2 == 0){
                setVisibleEven(i - 1, j);
            }else{
                setVisibleOdd(i - 1, j);
            }
        }
        if (isBlock(i - 1, j - 1)) {
            if((j - 1) % 2 == 0){

                setVisibleEven(i - 1, j - 1);
            }else{
                setVisibleOdd(i - 1, j - 1);
            }
        }
        if (isBlock(i, j - 1)) {
            if((j - 1) % 2 == 0){
                setVisibleEven(i, j - 1);
            }else{

                setVisibleOdd(i, j - 1);
            }
            
        }
        if (isBlock(i + 1, j)) {
            if(j % 2 == 0){
              setVisibleEven(i + 1, j);
            }else{
              setVisibleOdd(i + 1, j);
            }
        }
        if (isBlock(i, j + 1)) {
           if((j + 1) % 2 == 0){

            setVisibleEven(i, j + 1);
           }else{
           setVisibleOdd(i, j + 1);
           }
        }
        if (isBlock(i - 1, j + 1)) {
            if((j + 1) % 2 == 0){

                setVisibleEven(i - 1, j+ 1);
            }else{
                setVisibleOdd(i - 1, j + 1);
            }
        }

    }

    private void makeVisibleNearOdd(int i,int j){
        if (isBlock(i - 1, j)) {
            if(j % 2 == 0){
                setVisibleEven(i - 1, j);
            }else{
                setVisibleOdd(i - 1, j);
            }
        }
        if (isBlock(i + 1, j - 1)) {
            if((j - 1) % 2 == 0){

                setVisibleEven(i + 1, j - 1);
            }else{
                setVisibleOdd(i + 1, j - 1);
            }
        }
        if (isBlock(i, j - 1)) {
            if((j - 1) % 2 == 0){
                setVisibleEven(i, j - 1);
            }else{

                setVisibleOdd(i, j - 1);
            }
            
        }


        if (isBlock(i + 1, j)) {
            if(j % 2 == 0){
              setVisibleEven(i + 1, j);
            }else{
              setVisibleOdd(i + 1, j);
            }
        }
        if (isBlock(i, j + 1)) {
           if((j + 1) % 2 == 0){

            setVisibleEven(i, j + 1);
           }else{
           setVisibleOdd(i, j + 1);
           }
        }
        if (isBlock(i + 1, j + 1)) {
            if((j + 1) % 2 == 0){

                setVisibleEven(i + 1, j+ 1);
            }else{
                setVisibleOdd(i + 1, j + 1);
            }
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
                if(j % 2 == 0){
                    setVisibleEven(i, j);
                    makeVisibleNearEven(i, j);
                }else{
                    setVisibleOdd(i, j);
                    makeVisibleNearOdd(i, j);
                }
              
            }
        }

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (Terrains[i][j].getType().equals("fog of war")) {
                    for (int l = Terrains[i][j].getReveals().size() - 1; l >= 0; l--) {
                        if (Terrains[i][j].getReveals().get(l).getUser() == user) {
                            Terrains[i][j].setType("revealed");
                            break;
                        }
                    }
                }
            }
        }
    }










    // print map
    public void SwitchCaseFirstHalf(int i, int j, int l, Database database, User user,int count) {
        switch (j) {
            case 0:
                if (i - count > 0) {
                    firstRow(i, j, l, true, database, user);
                } else {
                    firstRow(i, j, l, false, database, user);
                }
                break;
            case 1:
                if (i - count > 0) {
                    secondRow(i, j, l, true, database, user);
                } else {
                    secondRow(i, j, l, false, database, user);
                }
                break;
            case 2:
                if (i  - count > 0) {
                    thirdRow(i, j, l, true, user);
                } else {
                    thirdRow(i, j, l, false, user);
                }
                break;

        }
    }

    public void SwitchCaseSecondHalf(int i, int j, int l, Database database, User user,int count) {
        switch (j) {
        
            case 3:
                if (i != count) {
                    fourthRow(i, j, l, true, database, user);
                } else {
                    fourthRow(i, j, l, false, database, user);
                }
                break;
            case 4:
                if (i != count) {
                    fifthRow(i, j, l, true, database, user);
                } else {
                    fifthRow(i, j, l, false, database, user);
                }
                break;
            case 5:
                if (i != count) {
                    sixthRow(i, j, l, true, user);
                } else {
                    sixthRow(i, j, l, false, user);
                }
                break;
        }
    }

    public String[][] printMap(Database database, User user) {
        initializeMapUser(user);
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < Iteration; j++) {
                Printmap[i][j] = "";
            }
        }
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < Iteration / 2; j++) {
                addSpace(i, j, Iteration / 2 - 1 - j);
                for (int l = 0; l < COL; l += 2) {
                    SwitchCaseFirstHalf(i, j, l, database, user,0);
                }

            }
            for (int j = Iteration / 2; j < Iteration; j++) {
                addSpace(i, j, j - Iteration / 2);
                for (int l = 0; l < COL; l += 2) {
                    SwitchCaseSecondHalf(i, j, l, database, user,ROW - 1);
                }
            }
        }
        return this.Printmap;
    }

    public String[][] PrintMapXandY(Database database ,User user,int x,int y){
       initializeMapUser(user);
       for(int i = 0 ; i < 3;i++){
           for(int j = 0; j < Iteration;j++){
               Printmap[i][j] = "";
           }
       }
       for(int i = x ; i < x + 3;i++){

        for (int j = 0; j < Iteration / 2; j++) {
            addSpace(i, j, Iteration / 2 - 1 - j);
            for (int l = 0; l < y + 6; l += 2) {
              SwitchCaseFirstHalf(i, j, l, database, user,x);
            }

        }
        for (int j = Iteration / 2; j < Iteration; j++) {
            addSpace(i, j, j - Iteration / 2);
            for (int l = 0; l < y + 6; l += 2) {
                SwitchCaseSecondHalf(i, j, l, database, user,x + 2);
            }
        }
    }
       return Printmap;
}














    // generate map
    public void generateMap(){
        Initializemap();
        randomTerrainAdd();
        setRiver();
        setFeature();
        setResource();
        nullImprovementAndCombat();
    }
   
    private void Initializemap(){
        for(int i = 0; i < ROW;i++){
            for(int j = 0; j < COL;j++){
               // Resource resource = new Resource(ResourceTypes.COAL);
                Terrains[i][j] = new Terrain(i, j, null, TerrainTypes.GRASSLLAND,  new ArrayList<TerrainFeatureTypes>(), null, null, null, null, null);
             
                if(i <= 2 || i >= 29 || j <= 1 || j >= 14){
                    Terrains[i][j].setTerrainTypes(TerrainTypes.OCEAN);
                }else if(j >= 13){
                    Terrains[i][j].setTerrainTypes(TerrainTypes.PLAINS);
                }else if(i <= 4){
                    Terrains[i][j].setTerrainTypes(TerrainTypes.PLAINS);
                }else if(i <= 7 && j >= 11){
                    Terrains[i][j].setTerrainTypes(TerrainTypes.PLAINS);
                }else if(i >= 25 && j >= 3 && j <= 5){
                    Terrains[i][j].setTerrainTypes(TerrainTypes.SNOW);
                }
            }
        }
    }
    
    private void randomTerrainAdd(){
        Random random = new Random();
        for(int count = 0;count < 200;count++){
            int randNum = Math.abs(random.nextInt()) % 4;
            int i = Math.abs(random.nextInt()) % 25 + 3;
            int j = Math.abs(random.nextInt()) % 11 + 2;
            switch(randNum){
                case 0:
                Terrains[i][j].setTerrainTypes(TerrainTypes.DESERT);
                break;
                case 1:
                Terrains[i][j].setTerrainTypes(TerrainTypes.HILLS);
                break;
                case 2:
                Terrains[i][j].setTerrainTypes(TerrainTypes.MOUNTAIN);
                break;
                case 3:
                Terrains[i][j].setTerrainTypes(TerrainTypes.PLAINS);
                break;
            }
        }
        Terrains[0][Math.abs(random.nextInt() % COL)].setTerrainTypes(TerrainTypes.TUNDRA);
        Terrains[1][Math.abs(random.nextInt() % COL)].setTerrainTypes(TerrainTypes.TUNDRA);
        Terrains[30][Math.abs(random.nextInt() % COL)].setTerrainTypes(TerrainTypes.TUNDRA);
        Terrains[31][Math.abs(random.nextInt() % COL)].setTerrainTypes(TerrainTypes.TUNDRA);
        Terrains[Math.abs(random.nextInt() % ROW)][0].setTerrainTypes(TerrainTypes.TUNDRA);
        Terrains[Math.abs(random.nextInt() % ROW)][1].setTerrainTypes(TerrainTypes.TUNDRA);
        Terrains[Math.abs(random.nextInt() % ROW)][14].setTerrainTypes(TerrainTypes.TUNDRA);;
        Terrains[Math.abs(random.nextInt() % ROW)][15].setTerrainTypes(TerrainTypes.TUNDRA);;
    }

    private  boolean isNeighbor(int firstI, int firstJ, int secondI, int secondJ) {
        if (firstI == secondI && firstJ == secondJ) return false;
        if (Math.abs(firstI - secondI) >= 2 || Math.abs(firstJ - secondJ) >= 2) return false;
        if (firstJ == secondJ) {
            if (Math.abs(firstI - secondI) <= 1) return true;
            else return false;
        }
        if (firstI == secondI) {
            if (Math.abs(firstJ - secondJ) <= 1) return true;
            else return false;
        }
        if (firstJ % 2 == 1) {
            if (secondI == firstI - 1) return true;
            else return false;
        }
        if (firstJ % 2 == 0) {
            if (secondI == firstI + 1) return true;
            else return false;
        }
        return false;
    }

    private void setRiver(){
        Random random = new Random();
         for(int i = 0 ; i < ROW;i++){
            for(int j = 0 ; j < COL;j++){
                for(int iCordinate = 0;iCordinate < ROW;iCordinate++){
                      for(int jCordinate = 0; jCordinate < COL;jCordinate++){
                            if(isNeighbor(i, j, iCordinate, jCordinate)){
                                if(Terrains[i][j].getTerrainTypes() != TerrainTypes.DESERT && Terrains[iCordinate][jCordinate].getTerrainTypes() != TerrainTypes.DESERT){
                                    if (random.nextInt() % 3 == 0 && Terrains[i][j].getTerrainTypes() != TerrainTypes.OCEAN && Terrains[iCordinate][jCordinate].getTerrainTypes() != TerrainTypes.OCEAN && hasRiver(Terrains[i][j],Terrains[iCordinate][jCordinate]) == null) {
                                        River river = new River(Terrains[i][j],Terrains[iCordinate][jCordinate]);
                                        rivers.add(river);
                                    }
                                }
                            }
                      }
                }
            }
         }
    }

    private void setFeature(){
        Random random = new Random();
       
        for(int i = 0 ; i< ROW;i++){
            for(int j = 0; j < COL;j++){
                ArrayList <TerrainFeatureTypes> possibleTerrainFeature = Terrains[i][j].getTerrainTypes().getPossibleFeatures();
                if(possibleTerrainFeature != null) 
                Terrains[i][j].setTerrainFeatureTypes(possibleTerrainFeature.get(Math.abs(random.nextInt()) % possibleTerrainFeature.size()));
            }
        }

    }

    private ArrayList<Resource> findAllPossiblResource(Terrain terrain){
        ArrayList<Resource> possibleResource = new ArrayList<Resource>();
        possibleResource.clear();
        ResourceTypes[] AllpossibleResource = {ResourceTypes.BANANAS,
            ResourceTypes.CATTLE,ResourceTypes.DEER,ResourceTypes.SHEEP,ResourceTypes.WHEAT,ResourceTypes.COAL
            ,ResourceTypes.HORSES,ResourceTypes.IRON,ResourceTypes.COTTON,ResourceTypes.DYES,ResourceTypes.FURS,
            ResourceTypes.GEMS,ResourceTypes.GOLD,ResourceTypes.INCENSE,ResourceTypes.IVORY,ResourceTypes.MARBLE,
            ResourceTypes.SILK,ResourceTypes.SILVER,ResourceTypes.SUGAR};
      
        for(int i = 0 ; i < AllpossibleResource.length;i++){
          
            if( AllpossibleResource[i].getObject() != null || AllpossibleResource[i].getObject().indexOf(terrain.getTerrainTypes()) != -1){
                Resource resource = new Resource(AllpossibleResource[i]);
                possibleResource.add(resource);
            }
            for(int j = 0; j < terrain.getTerrainFeatureTypes().size();j++){

                if( AllpossibleResource[i].getObject() != null || AllpossibleResource[i].getObject().indexOf(terrain.getTerrainFeatureTypes().get(j)) != -1){
                    Resource resource = new Resource(AllpossibleResource[i]);
                    possibleResource.add(resource);
                }
            }
        }
        return possibleResource;
    }

    private void setResource(){
        Random random = new Random();
        for(int i = 0 ; i < ROW;i++){
            for(int j = 0; j < COL;j++){
                 ArrayList<Resource> possibleResource = findAllPossiblResource(Terrains[i][j]);
                 if (possibleResource.size() > 0 && Math.abs(random.nextInt()) % 2 == 0)
                    Terrains[i][j].setTerrainResource(possibleResource.get(Math.abs(random.nextInt()) % possibleResource.size()));
            }
        }
    }

    private void nullImprovementAndCombat(){
        for(int i = 0 ; i < ROW;i++){
            for(int j = 0; j < COL;j++){
              Terrains[i][j].setTerrrainImprovement(null);
              Terrains[i][j].setCombatUnit(null);
              Terrains[i][j].setNonCombatUnit(null);
            }
        }
    }

}
