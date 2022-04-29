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
    public ArrayList<River> getRiver(){
        return this.rivers;
    }





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


    public String[][] printMap(Database database, User user) {
        GameMapGenerator newGameMapGenerator = new GameMapGenerator(Terrains, rivers,ROW,COL,Iteration);
        Printmap = newGameMapGenerator.printMap(database, user);
        return Printmap;
    }


    public String[][] PrintMapXandY(Database database ,User user,int x,int y){
        GameMapGenerator newGameMapGenerator = new GameMapGenerator(Terrains, rivers,ROW,COL,Iteration);
        Printmap = newGameMapGenerator.PrintMapXandY(database, user, x, y);
        return Printmap;
    }



    // set Revealed
    public void setRevealed(User user,int i,int j){

        int index = -1;
        for(int count = 0 ; count < Terrains[i][j].getReveals().size();count++){
            if(Terrains[i][j].getReveals().get(count).getUser() == user){
               index = count;
               break;
            }
        }
        if(index != -1){
            Terrains[i][j].getReveals().remove(index);
        }

        Revealed reveal = new Revealed(user, Terrains[i][j].getTerrainTypes(), Terrains[i][j].getTerrainFeatureTypes(), Terrains[i][j].getCombatUnit().clone(), Terrains[i][j].getNonCombatUnit().clone(), Terrains[i][j].getTerrrainImprovement(), Terrains[i][j].getTerrainResource().clone());
        Terrains[i][j].setReveals(reveal);

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
