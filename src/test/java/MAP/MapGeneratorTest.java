package MAP;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Controllers.DatabaseController;
import Enums.MenuEnums;
import Model.Civilization;
import Model.Database;
import Model.Map;
import Model.Resource;
import Model.Revealed;
import Model.River;
import Model.Terrain;
import Model.User;
import Model.Improvements.Improvements;
import Model.Resources.ResourceTypes;
import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;
import Model.Units.CombatUnit;
import Model.Units.NonCombatUnit;
import View.LoginMenu;

@ExtendWith(MockitoExtension.class)
public class MapGeneratorTest {

    @Mock
    River river;
    @Mock
    Terrain terrainOne;
    @Mock
    Terrain terrainTwo;
    @Mock
    ArrayList<River> rivers;

    @Test
    public void hasRiverTest() {
        Map map = new Map();
        ArrayList<River> rivers = new ArrayList<River>();
        rivers.add(river);
        map.setRiver(rivers);
        when(river.getFirstTerrain()).thenReturn(terrainOne);
        when(river.getSecondTerrain()).thenReturn(terrainTwo);
        River checkRiver = map.hasRiver(terrainOne, terrainTwo);
        Assertions.assertNotNull(checkRiver);
    }

    @Test
    public void hasNotRiverTest() {
        Map map = new Map();
        ArrayList<River> rivers = new ArrayList<River>();
        rivers.add(river);
        map.setRiver(rivers);
        when(river.getFirstTerrain()).thenReturn(null);
        when(river.getSecondTerrain()).thenReturn(terrainOne);
        River checkRiver = map.hasRiver(terrainOne, terrainTwo);
        Assertions.assertNull(checkRiver);
    }

    @Test
    public void hasRiverSecondTest() {
        Map map = new Map();
        rivers = new ArrayList<River>();
        rivers.add(river);
        map.setRiver(rivers);
        when(river.getFirstTerrain()).thenReturn(terrainOne);
        River checkRiver = map.hasRiver(terrainOne);
        Assertions.assertNotNull(checkRiver);
    }

    @Test
    public void hasNotRiverSecondTest() {
        Map map = new Map();
        rivers = new ArrayList<River>();
        rivers.add(river);
        map.setRiver(rivers);
        when(river.getFirstTerrain()).thenReturn(terrainOne);
        when(river.getSecondTerrain()).thenReturn(null);
        River checkRiver = map.hasRiver(terrainTwo);
        Assertions.assertNull(checkRiver);
    }

    @Mock
    Terrain terrainRevealed;
    @Mock
    User user;
    @Mock
    Revealed reveal;
    @Mock
    Revealed revealTwo;
    @Mock
    ArrayList<Revealed> reveals;
    @Mock
    CombatUnit combatunit;
    @Mock
    NonCombatUnit noncombatunit;
    @Mock
    Resource resource;

    @Test
    public void setRevealedTest() {
        reveals = new ArrayList<Revealed>();
        reveal.setUser(user);
        reveals.add(reveal);
        reveals.add(revealTwo);
        Map map = new Map();
        map.setTerrainTest(terrainRevealed, 3, 4);
        when(terrainRevealed.getReveals()).thenReturn(reveals);
        when(reveal.getUser()).thenReturn(user);
        map.setRevealed(user, 3, 4);
        Assertions.assertFalse(terrainRevealed.getReveals().contains(reveal));
    }

    @Mock
    User userOne;
    @Mock
    User userTwo;

    @Test
    public void setRevealedTestHasDelete() {
        reveals = new ArrayList<Revealed>();
        reveal.setUser(user);
        reveals.add(reveal);
        reveals.add(revealTwo);
        Map map = new Map();
        map.setTerrainTest(terrainRevealed, 3, 4);
        when(terrainRevealed.getReveals()).thenReturn(reveals);
        when(reveal.getUser()).thenReturn(userOne);
        map.setRevealed(user, 3, 4);
        Assertions.assertTrue(terrainRevealed.getReveals().contains(reveal));
    }

    @Test
    public void setRevealedTestHasAddSize() {
        terrainRevealed = new Terrain(3, 4, null, null, null, combatunit, noncombatunit, null, resource,
                new ArrayList<Revealed>());
        reveals = new ArrayList<>();

        reveals.add(reveal);
        reveals.add(revealTwo);
        Map map = new Map();
        terrainRevealed.setRevealedTest(reveals);
        map.setTerrainTest(terrainRevealed, 3, 4);
        when(reveal.getUser()).thenReturn(userOne);
        when(revealTwo.getUser()).thenReturn(userTwo);
        map.setRevealed(user, 3, 4);
        Assertions.assertTrue(reveals.size() == 3);
    }

    @Test
    public void setRevealedTestHasAddUser() {
        terrainRevealed = new Terrain(3, 4, null, null, null, combatunit, noncombatunit, null, resource,
                new ArrayList<Revealed>());
        reveals = new ArrayList<>();

        reveals.add(reveal);
        reveals.add(revealTwo);
        Map map = new Map();
        terrainRevealed.setRevealedTest(reveals);
        map.setTerrainTest(terrainRevealed, 3, 4);
        when(reveal.getUser()).thenReturn(userOne);
        when(revealTwo.getUser()).thenReturn(userTwo);
        map.setRevealed(user, 3, 4);
        Assertions.assertTrue(reveals.get(2).getUser() == user);
    }

    @Test
    public void checkIteration() {
        Map map = new Map();
        Assertions.assertTrue(map.getIteration() == 6);
    }

    @Mock
    Terrain terrainInitialize;

    Terrain terrains[][];

    @Test
    public void InitializeMapTestPlains() {
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                terrains[i][j] = new Terrain(i, j, null, null, null, combatunit, noncombatunit, null, resource,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        map.Initializemap();
        ;
        Assertions.assertTrue(terrains[3][4].getTerrainTypes() == TerrainTypes.PLAINS);
    }

    @Test
    public void InitializeMapTestOceans() {
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                terrains[i][j] = new Terrain(i, j, null, null, null, combatunit, noncombatunit, null, resource,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        map.Initializemap();
        ;
        Assertions.assertTrue(terrains[0][0].getTerrainTypes() == TerrainTypes.OCEAN);
    }

    @Test
    public void InitializeMapTestGrassland() {
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                terrains[i][j] = new Terrain(i, j, null, null, null, combatunit, noncombatunit, null, resource,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        map.Initializemap();
        ;
        Assertions.assertTrue(terrains[12][7].getTerrainTypes() == TerrainTypes.GRASSLLAND);
    }

    @Test
    public void InitializeMapTestSnow() {
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                terrains[i][j] = new Terrain(i, j, null, null, null, combatunit, noncombatunit, null, resource,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        map.Initializemap();
        ;
        Assertions.assertTrue(terrains[27][4].getTerrainTypes() == TerrainTypes.SNOW);

    }

    @Test
    public void addRandomTerrain() {
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                terrains[i][j] = new Terrain(i, j, null, null, null, combatunit, noncombatunit, null, resource,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        map.randomTerrainAdd();

        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {

                if (terrains[i][j].getTerrainTypes() == TerrainTypes.DESERT) {
                    Assertions.assertTrue(terrains[i][j].getTerrainTypes() == TerrainTypes.DESERT);
                } else if (terrains[i][j].getTerrainTypes() == TerrainTypes.HILLS) {
                    Assertions.assertTrue(terrains[i][j].getTerrainTypes() == TerrainTypes.HILLS);
                } else if (terrains[i][j].getTerrainTypes() == TerrainTypes.MOUNTAIN) {
                    Assertions.assertTrue(terrains[i][j].getTerrainTypes() == TerrainTypes.MOUNTAIN);
                } else if (terrains[i][j].getTerrainTypes() == TerrainTypes.PLAINS) {
                    Assertions.assertTrue(terrains[i][j].getTerrainTypes() == TerrainTypes.PLAINS);
                }

            }
        }

    }



    @Test
    public void addRandomTerrainCorner() {
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                terrains[i][j] = new Terrain(i, j, null, null, null, combatunit, noncombatunit, null, resource,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        map.randomTerrainAdd();

        ArrayList<Terrain> TundraTerrain = new ArrayList<>();
        for(int i = 0; i < map.getCOL();i++){
            if(terrains[0][i].getTerrainTypes() == TerrainTypes.TUNDRA){
                TundraTerrain.add(terrains[0][i]);
            }
        }
       Assertions.assertTrue(TundraTerrain.size() > 0);

    }
    @Mock
    Database database;
    @Test
    public void PrintMap(){
        
        resource = new Resource(ResourceTypes.BANANAS);
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                terrains[i][j] = new Terrain(i, j, null, TerrainTypes.PLAINS, new ArrayList<>(), null, null, Improvements.FARM, resource,
                        new ArrayList<Revealed>());
            }
        }
        Civilization civilization = new Civilization(0, 100, "A");
        ArrayList<Terrain> myterrain = new ArrayList<>();
        myterrain.add(terrains[3][4]);
        myterrain.add(terrains[3][7]);
        myterrain.add(terrains[6][4]);
        myterrain.add(terrains[10][4]);
        civilization.setTerrains(myterrain);
        user = new User(null, null, null, civilization);
        database = new Database();
        database.addUser(user);
        map.setTerrains(terrains);
        String result[][] = map.printMap(database, user);
        
        Assertions.assertNotNull(result);
    }



    @Test
    public void PrintMapXandY(){
        
        resource = new Resource(ResourceTypes.BANANAS);
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null, Improvements.FARM, resource,
                        new ArrayList<Revealed>());
            }
        }
        Civilization civilization = new Civilization(0, 100, "A");
        ArrayList<Terrain> myterrain = new ArrayList<>();
        myterrain.add(terrains[3][4]);
        myterrain.add(terrains[3][7]);
        myterrain.add(terrains[6][4]);
        myterrain.add(terrains[10][4]);
        civilization.setTerrains(myterrain);
        user = new User(null, null, null, civilization);
        database = new Database();
        database.addUser(user);
        map.setTerrains(terrains);
        String result[][] = map.PrintMapXandY(database, user,3,4);
        
        Assertions.assertNotNull(result);
    }






    @Test
    public void isNeighbourTest(){
        Map map = new Map();
        Assertions.assertFalse(map.isNeighbor(1, 0, 3, 0));
    }
    @Test
    public void isNeighbourTest1(){
        Map map = new Map();
        Assertions.assertTrue(map.isNeighbor(0, 0, 0, 1));
        Assertions.assertTrue(map.isNeighbor(1, 1, 0, 1));
        Assertions.assertFalse(map.isNeighbor(0, 1, 2, 1));
        Assertions.assertFalse(map.isNeighbor(1, 2, 1, 0));
        Assertions.assertTrue(map.isNeighbor(3, 3, 2, 2));
        Assertions.assertTrue(map.isNeighbor(3, 4, 4, 3));
    }

    @Test
    public void riverInitializeTest(){
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null, Improvements.FARM, resource,
                        new ArrayList<Revealed>());
            }
        }

        map.setTerrains(terrains);
        map.setRiver();
        Assertions.assertTrue(map.getRiver().size() > 0);
    }

    @Test 
    public void setFeatureTest(){
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null, Improvements.FARM, resource,
                        new ArrayList<Revealed>());
            }
        }

        map.setTerrains(terrains);
        map.setFeature();
        Assertions.assertTrue(terrains[3][4].getTerrainFeatureTypes().size() > 0);
    }

   @Test

   public void setResource(){
    Map map = new Map();
    terrains = new Terrain[map.getROW()][map.getCOL()];
    for (int i = 0; i < map.getROW(); i++) {
        for (int j = 0; j < map.getCOL(); j++) {
            terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null, Improvements.FARM, resource,
                    new ArrayList<Revealed>());
        }
    }

    map.setTerrains(terrains);
    map.setResource();
    Assertions.assertNotNull(terrains[3][4].getTerrainResource());
   }


   @Test

   public void setNulltoMapTest(){
    Map map = new Map();
    terrains = new Terrain[map.getROW()][map.getCOL()];
    for (int i = 0; i < map.getROW(); i++) {
        for (int j = 0; j < map.getCOL(); j++) {
            terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null, Improvements.FARM, resource,
                    new ArrayList<Revealed>());
        }
    }

    map.setTerrains(terrains);
    map.nullImprovementAndCombat();
    Assertions.assertTrue(terrains[3][4].getTerrrainImprovement() == null);

   }


   @Test
   public void revealedTileTest(){
    resource = new Resource(ResourceTypes.BANANAS);

    Map map = new Map();
    terrains = new Terrain[map.getROW()][map.getCOL()];
    for (int i = 0; i < map.getROW(); i++) {
        for (int j = 0; j < map.getCOL(); j++) {
            terrains[i][j] = new Terrain(i, j, null, TerrainTypes.PLAINS, new ArrayList<>(), null, null, Improvements.FARM, resource,
                    new ArrayList<Revealed>());
                    terrains[i][j].setTerrainFeatureTypes(TerrainFeatureTypes.FOREST);
        }
    }
   

    Civilization civilization = new Civilization(0, 100, "A");
    ArrayList<Terrain> myterrain = new ArrayList<>();
    ArrayList<Terrain> myterrainrevealed = new ArrayList<>();
    myterrain.add(terrains[3][7]);
    myterrain.add(terrains[6][4]);
    myterrain.add(terrains[10][4]);
    myterrainrevealed.add(terrains[3][4]);
    civilization.setTerrains(myterrain);
    civilization.setRevealedTerrains(myterrainrevealed);
    user = new User(null, null, null, civilization);
    database = new Database();
    database.addUser(user);
    map.setTerrains(terrains);
    String result[][] = map.printMap(database, user);
    Assertions.assertNotNull(result);

   }


   private Matcher getCommandMatcher(String input, String regex)
  {
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(input);
      return matcher;
  }

   @Test
   public void creatUserTest(){
       Database database = new Database();
       User checkUser = new User("ehsan", "123aA","essi1234", null);
       database.addUser(checkUser);
       DatabaseController databaseController = new DatabaseController(database);
       String input = "user create --username ehsan --nickname eeee --password 1234";
       Matcher matcher;
       String result = null;
       if ( (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER.getRegex())).matches()
       || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER2.getRegex())).matches()
       || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER3.getRegex())).matches()
       || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER4.getRegex())).matches()
       || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER5.getRegex())).matches()
       || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER6.getRegex())).matches() )
       {
        result =  databaseController.createUser(matcher);
       }
      
       Assertions.assertTrue(result.equals("user with username ehsan already exists"));

   }





}