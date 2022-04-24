package Model;

import java.util.ArrayList;

import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;
import Model.Units.CombatUnit;
import Model.Units.NonCombatUnit;
import Model.Units.UnitTypes;

public class Map {
    private int Iteration = 6;
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

    public void addUnderline(int row,int col,int count){
        for(int i = 0; i < count;i++){
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

    public Revealed getRevealedFromArray(User user,int i,int l){
        for(int count = Terrains[i][l].getReveals().size() - 1;count >= 0;count--){
            if(Terrains[i][l].getReveals().get(count).getUser() == user){
                return Terrains[i][l].getReveals().get(count);
            }
        }
        return null;
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

   


   ///// first row of map
    public void CivilizationPrintFirstHalf(int i, int j, int l, Database database) {
        River river;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "/";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "/";
        }
        String Civilization = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        if (Terrains[i][l].getType().equals("revealed")) {
            Civilization = "REV";
            int HowManySpace = 5 - Civilization.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += Color.MAGENTA;
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            Printmap[i][j] += Civilization;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else if (Terrains[i][l].getType().equals("visible")) {
            if (database.getCivilizationUser(Terrains[i][l]) != null) {
                Civilization += database.getCivilizationUser(Terrains[i][l]).getCivilization().getName();
            }
                int HowManySpace = 5 - Civilization.length();
                HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
                HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                addSpace(i,j,HowManySpaceLeft);
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
                Printmap[i][j] += Civilization;
                addSpace(i,j,HowManySpaceRight);
                Printmap[i][j] += Color.RESET;
            
        } else {
            addSpace(i, j, 5);
        }
    }

    public void TerrainFeaturesFirstHalf(int i, int j, int l,User user) {
        String TerrainFeatureType = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        if (Terrains[i - 1][l + 1].getType().equals("revealed")) {
           if(getRevealedFromArray(user, i - 1, l + 1).getTerrainFeatureTypes() != null){
               TerrainFeatureType += getRevealedFromArray(user, i - 1, l + 1).getTerrainFeatureTypes().getShowFeatures();
           }
           int HowManySpace = 9 - TerrainFeatureType.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] +=  Color.MAGENTA_BACKGROUND_BRIGHT;
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += TerrainFeatureType;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else if(Terrains[i - 1][l + 1].getType().equals("visible")){
            if (Terrains[i - 1][l + 1].getTerrainFeatureTypes() != null) {
                TerrainFeatureType += Terrains[i - 1][l + 1].getTerrainFeatureTypes().getShowFeatures();
            }
            int HowManySpace = 9 - TerrainFeatureType.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Terrains[i - 1][l + 1].getTerrainTypes().getColor();
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += TerrainFeatureType;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        }
        else {
            addSpace(i, j, 9);
        }
    }

    public void firstRow(int i, int j, int l, boolean check,Database database,User user) {
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


    /////second row of map
    public void XandYFirstHalf(int i, int j, int l) {
        String XcenterYcenter = "";
        int HowManySpaceLeft = 0;
            int HowManySpaceRight = 0;
        River river;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "/";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "/";
        }
        if (Terrains[i][l].getType().equals("revealed")) {
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            XcenterYcenter += Terrains[i][l].getX() + "," + Terrains[i][l].getY();
            int HowManySpace = 7 - XcenterYcenter.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += XcenterYcenter;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } 
        else if(Terrains[i][l].getType().equals("visible")){
            Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
            XcenterYcenter += Terrains[i][l].getX() + "," + Terrains[i][l].getY();
            int HowManySpace = 7 - XcenterYcenter.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += XcenterYcenter;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        }
            else {
            addSpace(i, j, 7);
        }
    }

    public void ResourceFirstHalf(int i,int j,int l,User user){
        String Resource = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        if(Terrains[i - 1][l + 1].getType().equals("revealed")) {
            if(getRevealedFromArray(user, i - 1, l + 1).getTerrainResource() != null){
                Resource += getRevealedFromArray(user, i - 1, l + 1).getTerrainResource().getShowResourceMap();
            }
            Printmap[i][j] +=  Color.MAGENTA_BACKGROUND_BRIGHT;
            int HowManySpace = 7 - Resource.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += Resource;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else if(Terrains[i - 1][l + 1].getType().equals("visible")) {
            Resource += Terrains[i - 1][l + 1].getTerrainResource().getShowResourceMap();
            Printmap[i][j] += Terrains[i - 1][l + 1].getTerrainTypes().getColor();
            int HowManySpace = 7 - Resource.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += Resource;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else {
              addSpace(i, j, 7);
        }
    }

    public void secondRow(int i, int j, int l, boolean check, Database database,User user) {
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


    /// third row of map
    public void twoCombatFirstHalf(int i, int j, int l,User user) {
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        String AllUnit = "";
        River river;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i - 1][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "/";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "/";
        }
        if(Terrains[i][l].getType().equals("revealed")){
            if (getRevealedFromArray(user, i, l).getNonCombatUnit().getUnitType() != null) {
                AllUnit += getRevealedFromArray(user, i, l).getNonCombatUnit().getUnitType().getShowMap();
            }
            AllUnit += " ";
            if (getRevealedFromArray(user, i, l).getCombatUnit().getUnitType() != null) {
                AllUnit += getRevealedFromArray(user, i, l).getCombatUnit().getUnitType().getShowMap();
            }
            int HowManySpace = 9 - AllUnit.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] +=  Color.MAGENTA_BACKGROUND_BRIGHT;
            addSpace(i, j, HowManySpaceLeft);
            int index = AllUnit.indexOf(" ");
            Printmap[i][j] += Color.RED;
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            Printmap[i][j] += AllUnit.substring(0, index);
            Printmap[i][j] += " ";
            Printmap[i][j] += Color.RED;
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            Printmap[i][j] += AllUnit.substring(index + 1);
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        }
        else if(Terrains[i][l].getType().equals("visible")){
            if (Terrains[i][l].getNonCombatUnit().getUnitType() != null) {
                AllUnit += Terrains[i][l].getNonCombatUnit().getUnitType().getShowMap();
            }
            AllUnit += " ";
            if (Terrains[i][l].getCombatUnit().getUnitType() != null) {
                AllUnit += Terrains[i][l].getCombatUnit().getUnitType().getShowMap();
            }
            int HowManySpace = 9 - AllUnit.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
            addSpace(i, j, HowManySpaceLeft);
            int index = AllUnit.indexOf(" ");
            Printmap[i][j] += Color.BLUE;
            Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
            Printmap[i][j] += AllUnit.substring(0, index);
            Printmap[i][j] += " ";
            Printmap[i][j] += Color.MAGENTA;
            Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
            Printmap[i][j] += AllUnit.substring(index + 1);
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        }else{
            addSpace(i, j, 9);
        }
    }

    public void ImprovementFirstHalf(int i,int j,int l,User user){
        String Improvement = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
       if(Terrains[i - 1][l + 1].getType().equals("revealed")){
           if(getRevealedFromArray(user, i - 1, l + 1).getTerrrainImprovement() != null){
              Improvement += getRevealedFromArray(user, i - 1, l + 1).getTerrrainImprovement().getShowImprovement();
           }
           int HowManySpace = 5 - Improvement.length();
           HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
           HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
           Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
           addUnderline(i, j, HowManySpaceLeft);
           Printmap[i][j] += Improvement;
           addUnderline(i, j, HowManySpaceRight);
           Printmap[i][j] += Color.RESET;
       }else if(Terrains[i - 1][l + 1].getType().equals("visible")){
          if(Terrains[i - 1][l + 1].getTerrrainImprovement() != null){
              Improvement += Terrains[i - 1][l + 1].getTerrrainImprovement().getShowImprovement();
          }
          int HowManySpace = 5 - Improvement.length();
          HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
          HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
          Printmap[i][j] += Terrains[i - 1][l + 1].getTerrainTypes().getColor();
          addUnderline(i, j, HowManySpaceLeft);
          Printmap[i][j] += Improvement;
          addUnderline(i, j, HowManySpaceRight);
          Printmap[i][j] += Color.RESET;
       }else{
        addUnderline(i, j, 5);
       }
    }
  
    public void thirdRow(int i, int j, int l, boolean check,User user) {

        if (check == true) {
           twoCombatFirstHalf(i, j, l, user);
            betweetTwoTailFirstHalf(i, j, l);
            ImprovementFirstHalf(i, j, l,user);
        } else {
            twoCombatFirstHalf(i, j, l, user);
            betweetTwoTailFirstHalf(i, j, l);
            addUnderline(i, j, 5);
        }
    }



    /// fourth row of map
    public void TerrainFeaturesSecondHalf(int i, int j, int l,User user) {
        River river;
        String TerrainFeatureType = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "\\";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "\\";
        }
        if(Terrains[i][l].getType().equals("revealed")){
           if(getRevealedFromArray(user, i, l).getTerrainFeatureTypes() != null){
              TerrainFeatureType += getRevealedFromArray(user, i, l).getTerrainFeatureTypes().getShowFeatures();
           }
            int HowManySpace = 9 - TerrainFeatureType.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += TerrainFeatureType;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        }
        else if(Terrains[i][l].getType().equals("visible")){
            if (Terrains[i][l].getTerrainFeatureTypes() != null) {
                TerrainFeatureType += Terrains[i][l].getTerrainFeatureTypes().getShowFeatures();
            }
            int HowManySpace = 9 - TerrainFeatureType.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += TerrainFeatureType;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        }else{
            addSpace(i, j, 9);
        }
      
    }

    public void CivilizationPrintSecondHalf(int i, int j, int l, Database database){
        String Civilization = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        if(Terrains[i][l + 1].getType().equals("revealed")){
            Civilization = "REV";
            int HowManySpace = 5 - Civilization.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += Color.MAGENTA;
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            Printmap[i][j] += Civilization;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        }else if(Terrains[i][l + 1].getType().equals("visible")){
            if (database.getCivilizationUser(Terrains[i][l + 1]) != null) {
                Civilization += database.getCivilizationUser(Terrains[i][l + 1]).getCivilization().getName();
            }
                int HowManySpace = 5 - Civilization.length();
                HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
                HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
                Printmap[i][j] += Terrains[i][l + 1].getTerrainTypes().getColor();
                addSpace(i,j,HowManySpaceLeft);
                Printmap[i][j] += Color.MAGENTA;
                Printmap[i][j] += Terrains[i][l + 1].getTerrainTypes().getColor();
                Printmap[i][j] += Civilization;
                addSpace(i,j,HowManySpaceRight);
                Printmap[i][j] += Color.RESET;
        }else{
            addSpace(i, j, 5);
        }
    }

    public void fourthRow(int i, int j, int l, boolean check,Database database,User user) {

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


    // fifth row of map
    public void ResourceSecondHalf(int i,int j,int l,User user){
        River river;
        String Resource = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "\\";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "\\";
        }
        if(Terrains[i][l].getType().equals("revealed")) {
            if(getRevealedFromArray(user, i, l).getTerrainResource() != null){
                Resource += getRevealedFromArray(user, i, l).getTerrainResource().getShowResourceMap();
            }
            Printmap[i][j] +=  Color.MAGENTA_BACKGROUND_BRIGHT;
            int HowManySpace = 7 - Resource.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += Resource;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else if(Terrains[i][l].getType().equals("visible")) {
            Resource += Terrains[i][l].getTerrainResource().getShowResourceMap();
            Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
            int HowManySpace = 7 - Resource.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += Resource;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } else {
              addSpace(i, j, 7);
        }

    }

    public void XandYSecondHalf(int i, int j, int l){
        String XcenterYcenter = "";
        int HowManySpaceLeft = 0;
            int HowManySpaceRight = 0;
        if (Terrains[i][l + 1].getType().equals("revealed")) {
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            XcenterYcenter += Terrains[i][l + 1].getX() + "," + Terrains[i][l + 1].getY();
            int HowManySpace = 7 - XcenterYcenter.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += XcenterYcenter;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        } 
        else if(Terrains[i][l + 1].getType().equals("visible")){
            Printmap[i][j] += Terrains[i][l + 1].getTerrainTypes().getColor();
            XcenterYcenter += Terrains[i][l + 1].getX() + "," + Terrains[i][l + 1].getY();
            int HowManySpace = 7 - XcenterYcenter.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            addSpace(i,j,HowManySpaceLeft);
            Printmap[i][j] += XcenterYcenter;
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        }
            else {
            addSpace(i, j, 7);
        }
    }
  
    public void fifthRow(int i, int j, int l, boolean check, Database database,User user) {
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



    // sixth row of map

    public void ImprovementSecondHalf(int i,int j,int l,User user){
        River river;
        if (l > 0 && ((river = hasRiver(Terrains[i][l], Terrains[i][l - 1])) != null)) {
            Printmap[i][j] += river.getColor();
            Printmap[i][j] += "\\";
            Printmap[i][j] += Color.RESET;
        } else {
            Printmap[i][j] += "\\";
        }
        String Improvement = "";
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
       if(Terrains[i][l].getType().equals("revealed")){
           if(getRevealedFromArray(user, i, l).getTerrrainImprovement() != null){
              Improvement += getRevealedFromArray(user, i, l).getTerrrainImprovement().getShowImprovement();
           }
           int HowManySpace = 5 - Improvement.length();
           HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
           HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
           Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
           addUnderline(i, j, HowManySpaceLeft);
           Printmap[i][j] += Improvement;
           addUnderline(i, j, HowManySpaceRight);
           Printmap[i][j] += Color.RESET;
       }else if(Terrains[i][l].getType().equals("visible")){
          if(Terrains[i][l].getTerrrainImprovement() != null){
              Improvement += Terrains[i][l].getTerrrainImprovement().getShowImprovement();
          }
          int HowManySpace = 5 - Improvement.length();
          HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
          HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
          Printmap[i][j] += Terrains[i][l].getTerrainTypes().getColor();
          addUnderline(i, j, HowManySpaceLeft);
          Printmap[i][j] += Improvement;
          addUnderline(i, j, HowManySpaceRight);
          Printmap[i][j] += Color.RESET;
       }else{
        addUnderline(i, j, 5);
       }

    }
  
    public void twoCombatSecondHalf(int i,int j,int l,User user){
        int HowManySpaceLeft = 0;
        int HowManySpaceRight = 0;
        String AllUnit = "";
       
        if(Terrains[i][l + 1].getType().equals("revealed")){
            if (getRevealedFromArray(user, i, l + 1).getNonCombatUnit().getUnitType() != null) {
                AllUnit += getRevealedFromArray(user, i, l + 1).getNonCombatUnit().getUnitType().getShowMap();
            }
            AllUnit += " ";
            if (getRevealedFromArray(user, i, l + 1).getCombatUnit().getUnitType() != null) {
                AllUnit += getRevealedFromArray(user, i, l + 1).getCombatUnit().getUnitType().getShowMap();
            }
            int HowManySpace = 9 - AllUnit.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] +=  Color.MAGENTA_BACKGROUND_BRIGHT;
            addSpace(i, j, HowManySpaceLeft);
            int index = AllUnit.indexOf(" ");
            Printmap[i][j] += Color.RED;
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            Printmap[i][j] += AllUnit.substring(0, index);
            Printmap[i][j] += " ";
            Printmap[i][j] += Color.RED;
            Printmap[i][j] += Color.MAGENTA_BACKGROUND_BRIGHT;
            Printmap[i][j] += AllUnit.substring(index + 1);
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        }
        else if(Terrains[i][l + 1].getType().equals("visible")){
            if (Terrains[i][l + 1].getNonCombatUnit().getUnitType() != null) {
                AllUnit += Terrains[i][l].getNonCombatUnit().getUnitType().getShowMap();
            }
            AllUnit += " ";
            if (Terrains[i][l + 1].getCombatUnit().getUnitType() != null) {
                AllUnit += Terrains[i][l + 1].getCombatUnit().getUnitType().getShowMap();
            }
            int HowManySpace = 9 - AllUnit.length();
            HowManySpaceLeft = returnHowManySpaceInLeft(HowManySpace);
            HowManySpaceRight = returnHowManySpaceInRight(HowManySpace);
            Printmap[i][j] += Terrains[i][l + 1].getTerrainTypes().getColor();
            addSpace(i, j, HowManySpaceLeft);
            int index = AllUnit.indexOf(" ");
            Printmap[i][j] += Color.BLUE;
            Printmap[i][j] += Terrains[i][l + 1].getTerrainTypes().getColor();
            Printmap[i][j] += AllUnit.substring(0, index);
            Printmap[i][j] += " ";
            Printmap[i][j] += Color.MAGENTA;
            Printmap[i][j] += Terrains[i][l + 1].getTerrainTypes().getColor();
            Printmap[i][j] += AllUnit.substring(index + 1);
            addSpace(i,j,HowManySpaceRight);
            Printmap[i][j] += Color.RESET;
        }else{
            addSpace(i, j, 9);
        }
    }
  
    public void sixthRow(int i, int j, int l, boolean check,User user) {
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
    public void SwichCaseFirstHalf(int i, int j, int l, Database database,User user) {
        switch (j) {
            case 0:
                if (i > 0) {
                    firstRow(i, j, l, true,database,user);
                } else {
                    firstRow(i, j, l, false,database,user);
                }
                break;
            case 1:
                if (i > 0) {
                    secondRow(i, j, l, true, database,user);
                } else {
                    secondRow(i, j, l, false, database,user);
                }
                break;
            case 2:
                if (i > 0) {
                    thirdRow(i, j, l, true,user);
                } else {
                    thirdRow(i, j, l, false,user);
                }
                break;

        }
    }

    public void SwichCaseSecondHalf(int i, int j, int l, Database database,User user) {
        switch (j) {

            case 3:
                if (i != ROW - 1) {
                    fourthRow(i, j, l, true,database,user);
                } else {
                    fourthRow(i, j, l, false,database,user);
                }
                break;
            case 4:
                if (i != ROW - 1) {
                    fifthRow(i, j, l, true, database,user);
                } else {
                    fifthRow(i, j, l, false, database,user);
                }
                break;
            case 5:
                if (i != ROW - 1) {
                    sixthRow(i, j, l, true,user);
                } else {
                    sixthRow(i, j, l, false,user);
                }
                break;
        }
    }

    public String[][] printMap(Database database,User user) {

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < Iteration; j++) {
                Printmap[i][j] = "";
            }
        }
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < Iteration / 2; j++) {
                addSpace(i, j, Iteration / 2 - 1 - j);
                for (int l = 0; l < COL; l += 2) {
                    SwichCaseFirstHalf(i, j, l, database,user);
                }

            }
            for (int j = Iteration / 2; j < Iteration; j++) {
                addSpace(i, j, j - Iteration / 2);
                for (int l = 0; l < COL; l += 2) {
                    SwichCaseSecondHalf(i, j, l, database,user);
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
                                CombatUnit, nonCombatUnit, null, null, null);
                        Terrains[i][j] = terrain;
                        break;
                    case 1:
                        CombatUnit CombatUnit1 = new CombatUnit(i, j, 3, 12, false, UnitTypes.CANNON);
                        NonCombatUnit nonCombatUnit1 = new NonCombatUnit(i, j, 3, 12, false, UnitTypes.SETTLER);
                        Terrain terrain1 = new Terrain(i, j, "fog of war", TerrainTypes.OCEAN,
                                TerrainFeatureTypes.OASIS,
                                CombatUnit1, nonCombatUnit1, null, null, null);
                        Terrains[i][j] = terrain1;
                        break;
                    case 2:
                        CombatUnit CombatUnit2 = new CombatUnit(i, j, 3, 12, false, UnitTypes.TANK);
                        NonCombatUnit nonCombatUnit2 = new NonCombatUnit(i, j, 3, 12, false, UnitTypes.SETTLER);
                        Terrain terrain2 = new Terrain(i, j, "clear", TerrainTypes.SNOW, TerrainFeatureTypes.JUNGLE,
                                CombatUnit2, nonCombatUnit2, null, null, null);
                        Terrains[i][j] = terrain2;
                        break;

                }
            }
        }
    }

}
