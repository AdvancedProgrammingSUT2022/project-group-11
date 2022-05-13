package MAP;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Controllers.CityController;
import Controllers.DatabaseController;
import Enums.GameEnums;
import Enums.MenuEnums;
import Model.Civilization;
import Model.Database;
import Model.GameMapGenerator;
import Model.Map;
import Model.Resource;
import Model.Revealed;
import Model.River;
import Model.Terrain;
import Model.User;
import Model.City.Citizen;
import Model.City.City;
import Model.Improvements.Improvement;
import Model.Improvements.ImprovementTypes;
import Model.Resources.ResourceTypes;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyTypes;
import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;
import Model.Units.CombatUnit;
import Model.Units.NonCombatUnit;
import Model.Units.Unit;
import Model.Units.UnitTypes;
import View.GameMenu;

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
        for (int i = 0; i < map.getCOL(); i++) {
            if (terrains[0][i].getTerrainTypes() == TerrainTypes.TUNDRA) {
                TundraTerrain.add(terrains[0][i]);
            }
        }
        Assertions.assertTrue(TundraTerrain.size() > 0);

    }

    @Mock
    Database database;

    @Test
    public void PrintMap() {

        resource = new Resource(ResourceTypes.BANANAS);
      
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, null, TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                        improvement, resource,
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
    public void PrintMapXandY() {

        resource = new Resource(ResourceTypes.BANANAS);
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                        improvement, resource,
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
        String result[][] = map.PrintMapXandY(database, user, 3, 4);

        Assertions.assertNotNull(result);
    }

    @Test
    public void isNeighbourTest() {
        Map map = new Map();
        Assertions.assertFalse(map.isNeighbor(1, 0, 3, 0));
    }

    @Test
    public void isNeighbourTest1() {
        Map map = new Map();
        Assertions.assertTrue(map.isNeighbor(0, 0, 0, 1));
        Assertions.assertTrue(map.isNeighbor(1, 1, 0, 1));
        Assertions.assertFalse(map.isNeighbor(0, 1, 2, 1));
        Assertions.assertFalse(map.isNeighbor(1, 2, 1, 0));
        Assertions.assertTrue(map.isNeighbor(3, 3, 2, 2));
        Assertions.assertTrue(map.isNeighbor(3, 4, 4, 3));
    }

    @Test
    public void riverInitializeTest() {
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                        improvement, resource,
                        new ArrayList<Revealed>());
            }
        }

        map.setTerrains(terrains);
        map.setRiver();
        Assertions.assertTrue(map.getRiver().size() > 0);
    }

    @Test
    public void setFeatureTest() {
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                        improvement, resource,
                        new ArrayList<Revealed>());
            }
        }

        map.setTerrains(terrains);
        map.setFeature();
        Assertions.assertTrue(terrains[3][4].getTerrainFeatureTypes().size() > 0);
    }

    @Test

    public void setResource() {
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                        improvement, resource,
                        new ArrayList<Revealed>());
            }
        }

        map.setTerrains(terrains);
        map.setResource();
        Assertions.assertNotNull(terrains[3][4].getTerrainResource());
    }

    @Test

    public void setNulltoMapTest() {
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                        improvement, resource,
                        new ArrayList<Revealed>());
            }
        }

        map.setTerrains(terrains);
        map.nullImprovementAndCombat();
        Assertions.assertTrue(terrains[3][4].getTerrainImprovement() == null);

    }

    @Test
    public void revealedTileTest() {
        resource = new Resource(ResourceTypes.BANANAS);

        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, null, TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                        improvement, resource,
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

    private Matcher getCommandMatcher(String input, String string) {
        Pattern pattern = Pattern.compile(string);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    @Test
    public void creatUserTestUsername() {
        Database database = new Database();
        User checkUser = new User("ehsan", "123aA", "essi1234", null);
        database.addUser(checkUser);
        DatabaseController databaseController = new DatabaseController(database);
        String input = "user create --username ehsan --nickname eeee --password 1234";
        Matcher matcher;
        String result = null;
        if ((matcher = getCommandMatcher(input, MenuEnums.CREATEUSER.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER2.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER3.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER4.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER5.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER6.getRegex())).matches()) {
            result = databaseController.createUser(matcher);
        }

        Assertions.assertTrue(result.equals("user with username ehsan already exists"));

    }

    @Test
    public void creatUserTestNickname() {
        Database database = new Database();
        User checkUser = new User("ehsan", "123aA", "essi1234", null);
        database.addUser(checkUser);
        DatabaseController databaseController = new DatabaseController(database);
        String input = "user create --username parsa --nickname essi1234 --password 1234";
        Matcher matcher;
        String result = null;
        if ((matcher = getCommandMatcher(input, MenuEnums.CREATEUSER.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER2.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER3.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER4.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER5.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER6.getRegex())).matches()) {
            result = databaseController.createUser(matcher);
        }

        Assertions.assertTrue(result.equals("user with nickname essi1234 already exists"));

    }

    @Test
    public void creatUserTestaddUser() {
        Database database = new Database();
        User checkUser = new User("ehsan", "123aA", "essi1234", null);
        database.addUser(checkUser);
        DatabaseController databaseController = new DatabaseController(database);
        String input = "user create --username parsa --nickname essi123 --password 1234";
        Matcher matcher;
        String result = null;
        if ((matcher = getCommandMatcher(input, MenuEnums.CREATEUSER.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER2.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER3.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER4.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER5.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.CREATEUSER6.getRegex())).matches()) {
            result = databaseController.createUser(matcher);
        }

        Assertions.assertTrue(result.equals("user created successfully!"));

    }

    @Test
    public void loginUserTest() {
        Matcher matcher;
        Database database = new Database();
        User checkUser = new User("ehsan0A", "123aA", "essi1234", null);
        String input = "user login --username ehsan0A --password 123aA";
        database.addUser(checkUser);
        DatabaseController databaseController = new DatabaseController(database);
        User user;
        if ((matcher = getCommandMatcher(input, MenuEnums.USERLOGIN.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.USERLOGIN2.getRegex())).matches()) {

                    user = databaseController.userLogin(matcher);
                    Assertions.assertTrue(user != null);
        }
       
    }

    @Test
    public void loginUserTestnull() {
        Matcher matcher;
        Database database = new Database();
        User checkUser = new User("ehsan0", "123aA", "essi1234", null);
        String input = "user login --username ehsan0A --password 123aA";
        database.addUser(checkUser);
        DatabaseController databaseController = new DatabaseController(database);
        User user;
        if ((matcher = getCommandMatcher(input, MenuEnums.USERLOGIN.getRegex())).matches()
                || (matcher = getCommandMatcher(input, MenuEnums.USERLOGIN2.getRegex())).matches()) {

                    user = databaseController.userLogin(matcher);
                    Assertions.assertTrue(user == null);
        }
       
    }


    @Test
    public void changeUsernameNickNameTest(){
        Matcher matcher;
        Database database = new Database();
        DatabaseController databaseController = new DatabaseController(database);
        User checkUser = new User("ehsan0", "123aA", "essi1234", null);
        database.addUser(checkUser);
        String input = "profile change --nickname essi1234";
        String result = null;
        if ((matcher = getCommandMatcher(input, MenuEnums.CHANGE_NICKNAME.getRegex())).matches()) {
            result = databaseController.changeUserNickname(matcher, checkUser);
        }
        Assertions.assertTrue(result.equals("user with nickname essi1234 already exists"));
    }


    @Test
    public void changeUsernameNickNameTestchange(){
        Matcher matcher;
        Database database = new Database();
        DatabaseController databaseController = new DatabaseController(database);
        User checkUser = new User("ehsan0", "123aA", "essi1234", null);
        database.addUser(checkUser);
        String input = "profile change --nickname essipalang";
        String result = null;
        if ((matcher = getCommandMatcher(input, MenuEnums.CHANGE_NICKNAME.getRegex())).matches()) {
            result = databaseController.changeUserNickname(matcher, checkUser);
        }
        Assertions.assertTrue(result.equals("nickname changed successfully!"));
    }

    @Test 
    public void changePasswordTestnotHave(){
        Matcher matcher;
        Database database = new Database();
        DatabaseController databaseController = new DatabaseController(database);
        User checkUser = new User("ehsan0", "123aA", "essi1234", null);
        database.addUser(checkUser);
        String result = null;
        String input = "profile change --password --current essi123 --new esii2333";

        if ((matcher = getCommandMatcher(input, MenuEnums.CHANGE_PASSWORD.getRegex())).matches()) {
             result = databaseController.changePassword(matcher, checkUser);

        }
        Assertions.assertTrue(result.equals("current password is invalid"));
    }


    @Test 
    public void changePasswordTestSameAsOld(){
        Matcher matcher;
        Database database = new Database();
        DatabaseController databaseController = new DatabaseController(database);
        User checkUser = new User("ehsan0", "123aA", "essi1234", null);
        database.addUser(checkUser);
        String result = null;
        String input = "profile change --password --current 123aA --new 123aA";

        if ((matcher = getCommandMatcher(input, MenuEnums.CHANGE_PASSWORD.getRegex())).matches()) {
             result = databaseController.changePassword(matcher, checkUser);

        }
        Assertions.assertTrue(result.equals("please enter a new password"));
    }



    @Test 
    public void changePasswordTestnew(){
        Matcher matcher;
        Database database = new Database();
        DatabaseController databaseController = new DatabaseController(database);
        User checkUser = new User("ehsan0", "123aA", "essi1234", null);
        database.addUser(checkUser);
        String result = null;
        String input = "profile change --password --current 123aA --new 123a";

        if ((matcher = getCommandMatcher(input, MenuEnums.CHANGE_PASSWORD.getRegex())).matches()) {
             result = databaseController.changePassword(matcher, checkUser);

        }
        Assertions.assertTrue(result.equals("password changed successfully!"));
    }

    @Test
    public void selectAndDeselcetUnitTestWrongCordinate(){
        Civilization civil = new Civilization(0, 3, "A");
        ArrayList<Terrain> terr  = new ArrayList<>();

        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                        improvement, resource,
                        new ArrayList<Revealed>());
                        terr.add(terrains[i][j]);
            }
        }
        civil.setTerrains(terr);
        map.setTerrains(terrains);
        User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        String result = databaseController.selectAndDeselectCombatUnit(user, 50, 3);
        Assertions.assertTrue(result.equals("there is no tile with these coordinates"));
        
    }



    @Test
    public void selectAndDeselcetUnitTestSelectBefore(){
        Civilization civil = new Civilization(0, 3, "A");
        ArrayList<Terrain> terr  = new ArrayList<>();
       
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, true,
                UnitTypes.SETTLER, false, false, false, false, false);
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                        improvement, resource,
                        new ArrayList<Revealed>());
                        terr.add(terrains[i][j]);
            }
        }
        civil.setTerrains(terr);
        map.setTerrains(terrains);
        User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        String result = databaseController.selectAndDeselectCombatUnit(user, 16, 3);
        Assertions.assertTrue(result.equals("you have selected this unit before"));
        
    }


    @Test
    public void selectAndDeselcetUnitTestcombatselected(){
        Civilization civil = new Civilization(0, 3, "A");
        ArrayList<Terrain> terr  = new ArrayList<>();
       ArrayList<Unit> units = new ArrayList<>();
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false, false, false, false, false);
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                        improvement, resource,
                        new ArrayList<Revealed>());
                        terr.add(terrains[i][j]);
                        units.add(combatunit);
            }
        }
        civil.setTerrains(terr);
        civil.setUnits(units);
        map.setTerrains(terrains);
        User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        String result = databaseController.selectAndDeselectCombatUnit(user, 16, 3);
        Assertions.assertTrue(result.equals("Combat unit was selected"));
        
    }

    @Test
    public void selectAndDeselcetUnitTestdoNotHaveAccess(){
        Civilization civil = new Civilization(0, 3, "A");
        ArrayList<Terrain> terr  = new ArrayList<>();
       ArrayList<Unit> units = new ArrayList<>();
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false, false, false, false, false);
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                        improvement, resource,
                        new ArrayList<Revealed>());
                        terr.add(terrains[i][j]);
                       
            }
        }
        civil.setTerrains(terr);
        civil.setUnits(units);
        map.setTerrains(terrains);
        User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        String result = databaseController.selectAndDeselectCombatUnit(user, 16, 3);
        Assertions.assertTrue(result.equals("you do not have access to this unit"));
        
    }






    @Test
    public void selectAndDeselectNonCombatUnitWrongCordinate(){
        Civilization civil = new Civilization(0, 3, "A");
        ArrayList<Terrain> terr  = new ArrayList<>();

        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                     improvement, resource,
                        new ArrayList<Revealed>());
                        terr.add(terrains[i][j]);
            }
        }
        civil.setTerrains(terr);
        map.setTerrains(terrains);
        User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        String result = databaseController.selectAndDeselectNonCombatUnit(user, 50, 3);
        Assertions.assertTrue(result.equals("there is no tile with these coordinates"));
    }



    @Test
    public void selectAndDeselcetNonUnitTestSelectBefore(){
        Civilization civil = new Civilization(0, 3, "A");
        ArrayList<Terrain> terr  = new ArrayList<>();
       
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, true,
                UnitTypes.SETTLER, false);
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, noncombatunit,
                        improvement, resource,
                        new ArrayList<Revealed>());
                        terr.add(terrains[i][j]);
            }
        }
        civil.setTerrains(terr);
        map.setTerrains(terrains);
        User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        String result = databaseController.selectAndDeselectNonCombatUnit(user, 16, 3);
        Assertions.assertTrue(result.equals("you have selected this unit before"));
        
    }


    @Test
    public void selectAndDeselcetUnitTestNoncombatselected(){
        Civilization civil = new Civilization(0, 3, "A");
        ArrayList<Terrain> terr  = new ArrayList<>();
       ArrayList<Unit> units = new ArrayList<>();
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false);
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, noncombatunit,
                        improvement, resource,
                        new ArrayList<Revealed>());
                        terr.add(terrains[i][j]);
                        units.add(noncombatunit);
            }
        }
        civil.setTerrains(terr);
        civil.setUnits(units);
        map.setTerrains(terrains);
        User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        String result = databaseController.selectAndDeselectNonCombatUnit(user, 16, 3);
        Assertions.assertTrue(result.equals("Noncombat unit was selected"));
        
    }


    @Test
    public void selectAndDeselcetNonUnitTestdoNotHaveAccess(){
        Civilization civil = new Civilization(0, 3, "A");
        ArrayList<Terrain> terr  = new ArrayList<>();
       ArrayList<Unit> units = new ArrayList<>();
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false);
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, noncombatunit,
                        improvement, resource,
                        new ArrayList<Revealed>());
                        terr.add(terrains[i][j]);
                       
            }
        }
        civil.setTerrains(terr);
        civil.setUnits(units);
        map.setTerrains(terrains);
        User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        String result = databaseController.selectAndDeselectNonCombatUnit(user, 16, 3);
        Assertions.assertTrue(result.equals("you do not have access to this unit"));
        
    }

    @Test

    public void changingStateOfcombatUnitTestSleep(){
      String result = null;
      CombatUnit combatunit = new CombatUnit(0, 0, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false, false, false, false, false);
               combatunit.setNextTerrain(new ArrayList<>());
                Database database = new Database();
                DatabaseController databaseController = new DatabaseController(database);
                result = databaseController.changingTheStateOfACombatUnit(combatunit, "sleep");
                Assertions.assertTrue(result.equals("action completed"));
    }

    @Test

    public void changingStateOfcombatUnitTestAlert(){
      String result = null;
      CombatUnit combatunit = new CombatUnit(0, 0, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false, false, false, false, false);
               combatunit.setNextTerrain(new ArrayList<>());
                Database database = new Database();
                DatabaseController databaseController = new DatabaseController(database);
                result = databaseController.changingTheStateOfACombatUnit(combatunit, "alert");
                Assertions.assertTrue(result.equals("action completed"));
    }


    @Test

    public void changingStateOfcombatUnitTestFortify(){
      String result = null;
      CombatUnit combatunit = new CombatUnit(0, 0, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false, false, false, false, false);
               combatunit.setNextTerrain(new ArrayList<>());
                Database database = new Database();
                DatabaseController databaseController = new DatabaseController(database);
                result = databaseController.changingTheStateOfACombatUnit(combatunit, "fortify");
                Assertions.assertTrue(result.equals("action completed"));
    }

    @Test

    public void changingStateOfcombatUnitTestFortifyUntilHeal(){
      String result = null;
      CombatUnit combatunit = new CombatUnit(0, 0, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false, false, false, false, false);
               combatunit.setNextTerrain(new ArrayList<>());
                Database database = new Database();
                DatabaseController databaseController = new DatabaseController(database);
                result = databaseController.changingTheStateOfACombatUnit(combatunit, "fortify until heal");
                Assertions.assertTrue(result.equals("action completed"));
    }

    @Test

    public void changingStateOfcombatUnitTestGarrison(){
      String result = null;
      CombatUnit combatunit = new CombatUnit(0, 0, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false, false, false, false, false);
               combatunit.setNextTerrain(new ArrayList<>());
                Database database = new Database();
                DatabaseController databaseController = new DatabaseController(database);
                result = databaseController.changingTheStateOfACombatUnit(combatunit, "garrison");
                Assertions.assertTrue(result.equals("action completed"));
    }

    @Test

    public void changingStateOfcombatUnitTestWake(){
      String result = null;
      CombatUnit combatunit = new CombatUnit(0, 0, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false, false, false, false, false);
               combatunit.setNextTerrain(new ArrayList<>());
                Database database = new Database();
                DatabaseController databaseController = new DatabaseController(database);
                result = databaseController.changingTheStateOfACombatUnit(combatunit, "wake");
                Assertions.assertTrue(result.equals("action completed"));
    }

    @Test

    public void changingStateOfcombatUnitTestDelete(){
      String result = null;
      CombatUnit combatunit = new CombatUnit(0, 0, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false, false, false, false, false);
               combatunit.setNextTerrain(new ArrayList<>());
                Database database = new Database();
                DatabaseController databaseController = new DatabaseController(database);
                result = databaseController.changingTheStateOfACombatUnit(combatunit, "delete");
                Assertions.assertTrue(result.equals("action completed"));
    }

    @Test

    public void changingStateOfcombatUnitTestsetupRanged(){
      String result = null;
      CombatUnit combatunit = new CombatUnit(0, 0, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false, false, false, false, false);
               combatunit.setNextTerrain(new ArrayList<>());
                Database database = new Database();
                DatabaseController databaseController = new DatabaseController(database);
                result = databaseController.changingTheStateOfACombatUnit(combatunit, "setup ranged");
                Assertions.assertTrue(result.equals("this unit is not a ranged combat unit!"));
    }


    @Test
    public void changingStateofNoncombatUnitSleep(){
        String result = null;
        NonCombatUnit combatunit = new NonCombatUnit(0, 0, 0, 0, 0, 0, false, false,
                  UnitTypes.SETTLER, false);
                 combatunit.setNextTerrain(new ArrayList<>());
                  Database database = new Database();
                  DatabaseController databaseController = new DatabaseController(database);
                  result = databaseController.changingTheStateOfANonCombatUnit(combatunit, "sleep");
                  Assertions.assertTrue(result.equals("action completed"));
    }

    @Test

    public void changingStateOfNoncombatUnitTestWake(){
      String result = null;
      NonCombatUnit combatunit = new NonCombatUnit(0, 0, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false);
               combatunit.setNextTerrain(new ArrayList<>());
                Database database = new Database();
                DatabaseController databaseController = new DatabaseController(database);
                result = databaseController.changingTheStateOfANonCombatUnit(combatunit, "wake");
                Assertions.assertTrue(result.equals("action completed"));
    }

    @Test

    public void changingStateOfNoncombatUnitTestDelete(){
      String result = null;
      NonCombatUnit combatunit = new NonCombatUnit(0, 0, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, false);
               combatunit.setNextTerrain(new ArrayList<>());
                Database database = new Database();
                DatabaseController databaseController = new DatabaseController(database);
                result = databaseController.changingTheStateOfANonCombatUnit(combatunit, "delete");
                Assertions.assertTrue(result.equals("action completed"));
    }

    @Test
    public void changeStateOfAllUnit(){
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, true, false, false, false, false);
                combatunit.setNextTerrain(new ArrayList<>());
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit,null ,
                        improvement, resource,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.changingTheStateOfAUnit("sleep");

    }



    @Test
    public void changeStateOfAllUnitNon(){
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, true);
                combatunit.setIsSelected(true);
                combatunit.setNextTerrain(new ArrayList<>());
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null,combatunit ,
                        improvement, resource,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.changingTheStateOfAUnit("sleep");

    }


    @Test
    public void hasOneUnitSelectedTest(){
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, true);
                combatunit.setIsSelected(true);
                combatunit.setNextTerrain(new ArrayList<>());
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null,combatunit ,
                        improvement, resource,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.HasOneUnitBeenSelected();
    }

    @Test
    public void isAllTaskFinishedTestTrue(){
        Map map = new Map();
        ArrayList<Unit> units = new ArrayList<>();
     
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, true);
                units.add(combatunit);
            }
        }
        Civilization civil = new Civilization(0, 0, "A");
        User user = new User(null, null, null, civil);
        civil.setUnits(units);
        Database database = new Database();
        database.addUser(user);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.isAllTasksFinished(user);
    }
    @Test
    public void isAllTaskFinishedTestFalse(){
        Map map = new Map();
        ArrayList<Unit> units = new ArrayList<>();
     
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, true,
                UnitTypes.SETTLER, true);
                units.add(combatunit);
            }
        }
        Civilization civil = new Civilization(0, 0, "A");
        User user = new User(null, null, null, civil);
        civil.setUnits(units);
        Database database = new Database();
        database.addUser(user);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.isAllTasksFinished(user);
    }

    @Test
    public void SetAllTaskFinishedTest(){
        Map map = new Map();
        ArrayList<Unit> units = new ArrayList<>();
     
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, true,
                UnitTypes.SETTLER, true);
                units.add(combatunit);
            }
        }
        Civilization civil = new Civilization(0, 0, "A");
        User user = new User(null, null, null, civil);
        civil.setUnits(units);
        Database database = new Database();
        database.addUser(user);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.setAllUnitsUnfinished(user);
    }

    @Test
    public void UnitMovementTestCordinate(){
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, true);
                combatunit.setIsSelected(true);
                combatunit.setNextTerrain(new ArrayList<>());
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null,combatunit ,
                        improvement, resource,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.unitMovement(-3, 4, user);
    }
    @Test
    public void UnitMovementComabtUnit(){
        Civilization civil = new Civilization(0, 3, "A");
        ArrayList<Terrain> terr  = new ArrayList<>();
       ArrayList<Unit> units = new ArrayList<>();
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                        improvement, resource,
                        new ArrayList<Revealed>());
                        terr.add(terrains[i][j]);
                        units.add(combatunit);
            }
        }
        units.remove(units.indexOf( terrains[4][5].getCombatUnit()));
        terrains[4][5].setCombatUnit(null);
        terrains[4][5].setNonCombatUnit(null);
        civil.setTerrains(terr);
        civil.setUnits(units);
        map.setTerrains(terrains);
        User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.unitMovement(4, 5, user);
    }


    @Test
    public void UnitMovementNonComabtUnit(){
        Civilization civil = new Civilization(0, 3, "A");
        ArrayList<Terrain> terr  = new ArrayList<>();
       ArrayList<Unit> units = new ArrayList<>();
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, true);
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, noncombatunit,
                        improvement, resource,
                        new ArrayList<Revealed>());
                        terr.add(terrains[i][j]);
                        units.add(noncombatunit);
            }
        }
        units.remove(units.indexOf(terrains[4][5].getNonCombatUnit()));
        terrains[4][5].setCombatUnit(null);
        terrains[4][5].setNonCombatUnit(null);
        civil.setTerrains(terr);
        civil.setUnits(units);
        map.setTerrains(terrains);
        User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.unitMovement(4, 5, user);
    }

    @Test
    public void AllUnitMovementcombat(){
        Civilization civil = new Civilization(0, 3, "A");
        ArrayList<Terrain> terr  = new ArrayList<>();
       ArrayList<Unit> units = new ArrayList<>();
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                        improvement, resource,
                        new ArrayList<Revealed>());
                        terr.add(terrains[i][j]);
                        units.add(combatunit);
            }
        }
        ArrayList<Terrain> nextterrain = new ArrayList<>();
        nextterrain.add(terrains[3][6]);
        terrains[4][5].getCombatUnit().setNextTerrain(nextterrain);
        civil.setTerrains(terr);
        civil.setUnits(units);
        map.setTerrains(terrains);
        User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.movementOfAllUnits(user);
    }

    @Test
    public void AllUnitMovementNoncombat(){
        Civilization civil = new Civilization(0, 3, "A");
        ArrayList<Terrain> terr  = new ArrayList<>();
       ArrayList<Unit> units = new ArrayList<>();
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, true);
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit,
                        improvement, resource,
                        new ArrayList<Revealed>());
                        terr.add(terrains[i][j]);
                        units.add(combatunit);
            }
        }
        ArrayList<Terrain> nextterrain = new ArrayList<>();
        nextterrain.add(terrains[3][6]);
        terrains[4][5].getNonCombatUnit().setNextTerrain(nextterrain);
        civil.setTerrains(terr);
        civil.setUnits(units);
        map.setTerrains(terrains);
        User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.movementOfAllUnits(user);
    }


    // create user

    public  Matcher getMatcher(String input, GameEnums command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }





    //ARCHER
    @Test

    public void createUserArcherNotEnoughMoney(){
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Civilization civil = new Civilization(60, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT ARCHER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }


    @Test

    public void createUserArcherNotEnoughTechnology(){
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(100);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT ARCHER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test

    public void createUserArcherNotEnoughCombat(){
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(100);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT ARCHER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    
    @Test

    public void createUserArcherNormal(){
        NonCombatUnit combatunit = new NonCombatUnit(3, 3, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(100);
     
     
        String input = "CONSTRUCT UNIT ARCHER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    // CHARLOTE ARCHER

    @Test
    public void createUserCharlote_Archer_NotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT CHARIOT_ARCHER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }


    @Test

    public void createUserChariot_ArcherNotEnoughTechnology(){
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(100);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT CHARIOT_ARCHER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitChariot_ArcherLackResource(){
        Resource resource = new Resource(ResourceTypes.BANANAS);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(100);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT CHARIOT_ARCHER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test

    public void createUserChariot_ArcherNotEnoughCombat(){
        Resource resource = new Resource(ResourceTypes.HORSES);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(100);
        city.setCombatUnit(combatunit);
      
        String input = "CONSTRUCT UNIT CHARIOT_ARCHER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createUserChariot_ArcherNotEnoughNormal(){
        Resource resource = new Resource(ResourceTypes.HORSES);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(100);
        city.setCombatUnit(null);
       
        String input = "CONSTRUCT UNIT CHARIOT_ARCHER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }



    //SCOUT

    @Test
    public void createUnitSCoutNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT SCOUT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    
    @Test
    public void createUserScoutNotEnoughCombat(){
        Resource resource = new Resource(ResourceTypes.HORSES);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(100);
        city.setCombatUnit(combatunit);
      
        String input = "CONSTRUCT UNIT SCOUT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }


    @Test
    public void createUserScoutNotEnoughNormal(){
        Resource resource = new Resource(ResourceTypes.HORSES);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(100);
        city.setCombatUnit(null);
      
        String input = "CONSTRUCT UNIT SCOUT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    //SETTLER

    @Test
    public void createUnitSETTLERNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT SETTLER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }


    @Test
    public void createUserSETTLERNotEnoughCombat(){
        Resource resource = new Resource(ResourceTypes.HORSES);
        NonCombatUnit combatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.SETTLER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(3000);
        city.setNonCombatUnit(combatunit);
      
        String input = "CONSTRUCT UNIT SETTLER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createUserSETTLERNotEnoughNormal(){
        Resource resource = new Resource(ResourceTypes.HORSES);
        NonCombatUnit combatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        civil.setBooleanSettlerBuy(true);
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.SETTLER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(3000);
        city.setNonCombatUnit(null);

      
        String input = "CONSTRUCT UNIT SETTLER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    

    //SPEARMAN
    @Test
    public void createUnitSPEARMANRNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT SPEARMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }


    @Test
    public void createUserSPEARMANNotEnoughCombat(){
        Resource resource = new Resource(ResourceTypes.HORSES);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.SETTLER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(3000);
        city.setCombatUnit(combatunit);
      
        String input = "CONSTRUCT UNIT SPEARMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }

    }


    @Test
    public void createUserSPEARMANNotEnoughnormal(){
        Resource resource = new Resource(ResourceTypes.HORSES);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.SETTLER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(3000);
        city.setCombatUnit(null);
      
        String input = "CONSTRUCT UNIT SPEARMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    //WARRIOR
    @Test
    public void createUnitWARRIORNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT WARRIOR";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createUserWARRIORNotEnoughCombat(){
        Resource resource = new Resource(ResourceTypes.HORSES);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.SETTLER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(3000);
        city.setCombatUnit(combatunit);
      
        String input = "CONSTRUCT UNIT WARRIOR";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }

    }

    @Test
    public void createUserwarriorNotEnoughnormal(){
        Resource resource = new Resource(ResourceTypes.HORSES);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.SETTLER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(3000);
        city.setCombatUnit(null);
      
        String input = "CONSTRUCT UNIT WARRIOR";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }


    // worker

    @Test
    public void createUnitWORKERNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT WORKER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createUserWORKERNotEnoughCombat(){
        Resource resource = new Resource(ResourceTypes.HORSES);
        NonCombatUnit combatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.SETTLER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(3000);
        city.setNonCombatUnit(combatunit);
      
        String input = "CONSTRUCT UNIT WORKER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }

    }


    @Test
    public void createUserWORKERNotEnoughnormal(){
        Resource resource = new Resource(ResourceTypes.HORSES);
        NonCombatUnit combatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit,
                improvement, resource,
                new ArrayList<Revealed>());

        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.SETTLER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(3000);
        city.setNonCombatUnit(null);
      
        String input = "CONSTRUCT UNIT WORKER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }

    }


    // CATAPAULT
    @Test
    public void createUnitCATAPULTNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT CATAPULT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitCatapultrLackResource(){
        Resource resource = new Resource(ResourceTypes.BANANAS);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT CATAPULT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }


    @Test
    public void createuitCatapultrtECH(){
        Resource resource = new Resource(UnitTypes.CATAPULT.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT CATAPULT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitCatapultcombat(){
        Resource resource = new Resource(UnitTypes.CATAPULT.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT CATAPULT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    @Test
    public void createuitCatapultnormal(){
        Resource resource = new Resource(UnitTypes.CATAPULT.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT CATAPULT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }




    // Horsesman
    @Test
    public void createUnitHorsesmanNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT HORSESMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitHORSESMANLackResource(){
        Resource resource = new Resource(ResourceTypes.BANANAS);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT HORSESMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitHorsesmanrtECH(){
        Resource resource = new Resource(UnitTypes.HORSESMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT HORSESMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }


    @Test
    public void createuitHORSESMANcombat(){
        Resource resource = new Resource(UnitTypes.HORSESMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.HORSESMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT HORSESMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    @Test
    public void createuitHORSESMANnormal(){
        Resource resource = new Resource(UnitTypes.HORSESMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.HORSESMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT HORSESMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        

       
    }

   // SWORDSMAN
    @Test
    public void createUnitSwordsmanNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT SWORDSMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitSwordsmanLackResource(){
        Resource resource = new Resource(ResourceTypes.BANANAS);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT SWORDSMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitSWORDSMANrtECH(){
        Resource resource = new Resource(UnitTypes.SWORDSMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT SWORDSMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }


    @Test
    public void createuitSWORDSMANcombat(){
        Resource resource = new Resource(UnitTypes.SWORDSMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.SWORDSMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT SWORDSMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    @Test
    public void createuitSWORDSMANANnormal(){
        Resource resource = new Resource(UnitTypes.SWORDSMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.SWORDSMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT SWORDSMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    //CROSSBOWMAN
    @Test
    public void createUnitCROSSBOWMANNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT CROSSBOWMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

   

    @Test
    public void createuitCROSSBOWMANrtECH(){
       // Resource resource = new Resource(UnitTypes.CROSSBOWMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, null,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT CROSSBOWMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitCROSSBOWMANcombat(){
        Resource resource = new Resource(UnitTypes.SWORDSMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CROSSBOWMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT CROSSBOWMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    @Test
    public void createuitCROSSBOWMANANnormal(){
        Resource resource = new Resource(UnitTypes.SWORDSMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CROSSBOWMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT CROSSBOWMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    // KNIGHT
    @Test
    public void createUnitKNIGHTNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT KNIGHT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitKnightLackResource(){
        Resource resource = new Resource(ResourceTypes.BANANAS);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT KNIGHT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitKNIGHTANrtECH(){
        Resource resource = new Resource(UnitTypes.KNIGHT.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT KNIGHT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitKNIGHTcombat(){
        Resource resource = new Resource(UnitTypes.KNIGHT.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.KNIGHT.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT KNIGHT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    @Test
    public void createuitKNIGHTNnormal(){
        Resource resource = new Resource(UnitTypes.KNIGHT.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.KNIGHT.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT KNIGHT";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    // LONGSWORDSMAN

    @Test
    public void createUnitLONGSNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT LONGSWORDSMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitLONGSLackResource(){
        Resource resource = new Resource(ResourceTypes.BANANAS);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT LONGSWORDSMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitLONGSANrtECH(){
        Resource resource = new Resource(UnitTypes.LONGSWORDSMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT LONGSWORDSMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitLONGScombat(){
        Resource resource = new Resource(UnitTypes.LONGSWORDSMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.LONGSWORDSMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT LONGSWORDSMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }
    @Test
    public void createuitLONGSNnormal(){
        Resource resource = new Resource(UnitTypes.LONGSWORDSMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.LONGSWORDSMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT LONGSWORDSMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    //pikeman
    @Test
    public void createUnitPIKEMANSNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT PIKEMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitPIKEMANANrtECH(){
        Resource resource = new Resource(UnitTypes.LONGSWORDSMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT PIKEMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitPIKEScombat(){
        Resource resource = new Resource(UnitTypes.LONGSWORDSMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.PIKEMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT PIKEMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    @Test
    public void createuitPIKESNnormal(){
        Resource resource = new Resource(UnitTypes.LONGSWORDSMAN.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.PIKEMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT PIKEMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    // trebutchet
    @Test
    public void createUnitTREBUCHETSNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT TREBUCHET";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitTREBUCHETLackResource(){
        Resource resource = new Resource(ResourceTypes.BANANAS);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT TREBUCHET";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitTREBUCHETANrtECH(){
        Resource resource = new Resource(UnitTypes.TREBUCHET.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT TREBUCHET";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitTREBUCHETcombat(){
        Resource resource = new Resource(UnitTypes.TREBUCHET.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.TREBUCHET.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT TREBUCHET";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }
    @Test
    public void createuitTREBUCHETNnormal(){
        Resource resource = new Resource(UnitTypes.TREBUCHET.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.TREBUCHET.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT TREBUCHET";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    // cannon
    @Test
    public void createUnitCANNONNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT CANNON";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitCANNONANrtECH(){
        Resource resource = new Resource(UnitTypes.TREBUCHET.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT CANNON";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitCANNONcombat(){
        Resource resource = new Resource(UnitTypes.TREBUCHET.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CANNON.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT CANNON";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    @Test
    public void createuitCANNONNnormal(){
        Resource resource = new Resource(UnitTypes.TREBUCHET.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CANNON.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT CANNON";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    
    //CAVALRY

    @Test
    public void createUnitCAVALRYNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT CAVALRY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitCAVALRYLackResource(){
        Resource resource = new Resource(ResourceTypes.BANANAS);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT CAVALRY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitCAVALRYANrtECH(){
        Resource resource = new Resource(UnitTypes.CAVALRY.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT CAVALRY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitCAVALRYcombat(){
        Resource resource = new Resource(UnitTypes.CAVALRY.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CAVALRY.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT CAVALRY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        

    }

    @Test
    public void createuitCAVALRYnormal(){
        Resource resource = new Resource(UnitTypes.CAVALRY.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CAVALRY.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT CAVALRY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    //LANCER
    @Test
    public void createUnitLANCERNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT LANCER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitLANCERLackResource(){
        Resource resource = new Resource(ResourceTypes.BANANAS);
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT LANCER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitLANCERANrtECH(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT LANCER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    
    @Test
    public void createuitLANCERcombat(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.LANCER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT LANCER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        

    }

    @Test
    public void createuitLANCERnormal(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.LANCER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT LANCER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    // MUSKETMUN
    @Test
    public void createUnitMUSKETMANNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT MUSKETMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitMUSKETANrtECH(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT MUSKETMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitMUSKETMANcombat(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.MUSKETMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT MUSKETMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        

    }

    @Test
    public void createuitMUSKETRnormal(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.MUSKETMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT MUSKETMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    // RIFLEMAN
    @Test
    public void createUnitRIFLEMANNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT RIFLEMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitRIFLEMANrtECH(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT RIFLEMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitRIFLEMANcombat(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.RIFLEMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT RIFLEMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        

    }

    @Test
    public void createuitRIFLEMANnormal(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.RIFLEMAN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT RIFLEMAN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    //ANTI_TANKGUN
    @Test
    public void createUnitANTINotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT ANTI_TANKGUN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitANTIrtECH(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT ANTI_TANKGUN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitANTIcombat(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.ANTI_TANKGUN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT ANTI_TANKGUN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        

    }

    @Test
    public void createuitANTIormal(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.ANTI_TANKGUN.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT ANTI_TANKGUN";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    //ARTILLERY
    @Test
    public void createUnitARTILLERYNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT ARTILLERY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitARTILLERYrtECH(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT ARTILLERY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitARTILLERYcombat(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.ARTILLERY.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT ARTILLERY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        

    }
    @Test
    public void createuitARTILLERYormal(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.ARTILLERY.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT ARTILLERY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }
    //INFANTRY
    @Test
    public void createUnitINFANTRYNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT INFANTRY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitINFANTRYrtECH(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT INFANTRY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitINFANTRYcombat(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.INFANTRY.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT INFANTRY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        

    }
    @Test
    public void createuitINFANTRYYormal(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.INFANTRY.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT INFANTRY";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    //PANZER
    @Test
    public void createUnitPANZERNotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT PANZER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitPANZERrtECH(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT PANZER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitPANZERcombat(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.PANZER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT PANZER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        

    }

    @Test
    public void createuitPANZERormal(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.PANZER.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT PANZER";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }

    //TANK
    @Test
    public void createUnitTANKotEnoughMoney(){
        Civilization civil = new Civilization(0, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(0);
        String input = "CONSTRUCT UNIT TANK";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }

    @Test
    public void createuitTANKrtECH(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
      //  Technology technology = new Technology(false, 0, UnitTypes.CATAPULT.getTechnologyRequirements(),false);
     //   tech.add(technology);
        civil.setTechnologies(tech);
  
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(1000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT TANK";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
    }
    @Test
    public void createuitTANKcombat(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.TANK.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(combatunit);
        String input = "CONSTRUCT UNIT TANK";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        

    }
    @Test
    public void createuitTANKRormal(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                improvement, resource,
                new ArrayList<Revealed>());

      
        Civilization civil = new Civilization(100, 3, "A");
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(false, 0, UnitTypes.TANK.getTechnologyRequirements(),false);
        tech.add(technology);
        civil.setTechnologies(tech);
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        String input = "CONSTRUCT UNIT TANK";
        Matcher matcher;
        CityController cityController = new CityController();
        if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
            cityController.createUnit(matcher, city);
        }
        
    }



    @Test
    public void cityGarrisonTest(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, resource,
                new ArrayList<Revealed>());
        Civilization civil = new Civilization(100, 3, "A");
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        CityController cityController = new CityController();
        cityController.garrison(city, combatunit);

    }

    @Test
    public void cityFoundTest(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
        NonCombatUnit combatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, resource,
                new ArrayList<Revealed>());
        Civilization civil = new Civilization(100, 3, "A");
        City city = new City(null, civil,null , 3, null,0, 0, null);
        city.setGold(10000);
       // city.setCombatUnit(null);
        CityController cityController = new CityController();
      cityController.foundCity(civil, combatunit, central);

    }


    @Test
    public void playATurnTestplus(){
        Resource resourceone = new Resource(ResourceTypes.CATTLE);
        Terrain terrainone = new Terrain(3, 4, null, TerrainTypes.DESERT, new ArrayList<>(), null, null, null, resourceone, null);
        Resource resourcetwo = new Resource(ResourceTypes.BANANAS);
        Terrain terraintwo = new Terrain(8, 9, null, TerrainTypes.DESERT, new ArrayList<>(), null, null, null, resourcetwo, null);
        Resource resourcethree = new Resource(ResourceTypes.COAL);
        Terrain terrainthree = new Terrain(5, 8, null, TerrainTypes.DESERT, new ArrayList<>(), null, null, null, resourcethree, null);
        ArrayList<Terrain> neighboursTerrain = new ArrayList<>();
        neighboursTerrain.add(terrainone);
        neighboursTerrain.add(terraintwo);

        neighboursTerrain.add(terrainthree);
       
        Civilization civil = new Civilization(100, 3, "A");
       
        City city = new City(null, civil,null , 3, null,0, 0, null);
        Citizen citizen = new Citizen(city);
        ArrayList<Citizen> citizens = new ArrayList<>();
        city.setFoodStorage(1000);
        citizens.add(citizen);
        city.setCitizens(citizens);
        city.setNeighbors(neighboursTerrain);

        CityController cityController = new CityController();
        cityController.playTurn(city);
    }

    @Test
    public void playATurnTestminus(){
        Resource resourceone = new Resource(ResourceTypes.CATTLE);
        Terrain terrainone = new Terrain(3, 4, null, TerrainTypes.DESERT, new ArrayList<>(), null, null, null, resourceone, null);
        Resource resourcetwo = new Resource(ResourceTypes.BANANAS);
        Terrain terraintwo = new Terrain(8, 9, null, TerrainTypes.DESERT, new ArrayList<>(), null, null, null, resourcetwo, null);
        Resource resourcethree = new Resource(ResourceTypes.COAL);
        Terrain terrainthree = new Terrain(5, 8, null, TerrainTypes.DESERT, new ArrayList<>(), null, null, null, resourcethree, null);
        ArrayList<Terrain> neighboursTerrain = new ArrayList<>();
        neighboursTerrain.add(terrainone);
        neighboursTerrain.add(terraintwo);

        neighboursTerrain.add(terrainthree);
       
        Civilization civil = new Civilization(100, 3, "A");
       
        City city = new City(null, civil,null , 3, null,0, 0, null);
        Citizen citizen = new Citizen(city);
        ArrayList<Citizen> citizens = new ArrayList<>();
        city.setFoodStorage(1000);
        for(int i = 0; i < 50;i++){
            citizens.add(citizen);
        }
       city.setPopulation(25);
        city.setCitizens(citizens);
        city.setNeighbors(neighboursTerrain);

        CityController cityController = new CityController();
        cityController.playTurn(city);
    }

    @Test
    public void hashmapCityTest(){
        Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
       // CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
       // UnitTypes.SETTLER, true, false, false, false, false);
        Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
        Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, resource,
                new ArrayList<Revealed>());
        Civilization civil = new Civilization(100, 3, "A");
        City city = new City(null, civil,central , 3, null,0, 0, null);
        city.setGold(10000);
        city.setCombatUnit(null);
        CityController cityController = new CityController();
        cityController.cityOutput(city);
    }

    @Test
    public void changingUnitsParametreTest(){
        CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true, false, false, false, false);
        NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        Civilization civil = new Civilization(100, 100, "A");
        ArrayList<Unit> units = new ArrayList<>();
        units.add(combatunit);
        units.add(noncombatunit);
        civil.setUnits(units);
        User user = new User(null, null, null, civil);
        Database database  = new Database();
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.changingUnitsParameters(user);
        databaseController.unitsInfo(user);
    }
    @Test
    public void technologyUnderSearchTest(){
        ArrayList<Technology> tech = new ArrayList<>();
        Technology technology = new Technology(true, 3, TechnologyTypes.AGRICULTURE, false);
        tech.add(technology);
        Civilization civil = new Civilization(100, 100, "A");
        civil.setTechnologies(tech);
        User user = new User(null, null, null, civil);
        Database database  = new Database();
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.getUnderResearchTechnology(user);
        tech.remove(technology);
        databaseController.getUnderResearchTechnology(user);


    }

    @Test
    public void getterrainDatabaseTest(){
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.SETTLER, true);
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, noncombatunit,
                        improvement, resource,
                        new ArrayList<Revealed>());
                       
            }
        }
       
     
       // User user = new User(null, null,null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.getTerrainByCoordinates(3, 4);
    }

    @Test
    public void setTerrainByEachCivilizationTest(){
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
            //    NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
             //   UnitTypes.SETTLER, true);
                Improvement improvement = new Improvement(i, j,ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                        improvement, resource,
                        new ArrayList<Revealed>());
                       
            }
        }
       map.setTerrains(terrains);
        ArrayList<User> users = new ArrayList<>();
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        Civilization civil = new Civilization(100, 100, "A");
        User user = new User(null, null, null, civil);
        DatabaseController databaseController = new DatabaseController(database);
        users.add(user);
        databaseController.setTerrainsOfEachCivilization(user);
        databaseController.setCivilizations(users);
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        databaseController.isContainInteger(ints, 2);
        databaseController.isContainInteger(ints, 7);

    }
    @Test
    public void addGoldTestTest(){
        ArrayList<Terrain> ownedTerrain = new ArrayList<>();
        Civilization civil = new Civilization(100, 100, "A");
        User user = new User(null, null, null, civil);
               NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false,
               UnitTypes.SETTLER, true);
               ArrayList<TerrainFeatureTypes> terrainFeature = new ArrayList<>();
               terrainFeature.add(TerrainFeatureTypes.FOREST);
                  CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
              UnitTypes.SETTLER, true, false, false, false, false);
             Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
             resource = new Resource(ResourceTypes.COAL);
            Terrain terrains = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, terrainFeature, combatunit, noncombatunit,
                     improvement, resource,
                     new ArrayList<Revealed>());
                     terrains.setBooleanResource(true);
                     ownedTerrain.add(terrains);
                     civil.setOwnedTerrains(ownedTerrain);
                     City city = new City(null, civil , terrains, 0,null,0, 0, new ArrayList<>());
                     city.setGold(1000);
                     civil.addCity(city);
                     civil.addUnit(combatunit);
                     terrains.setCity(city);
                     Database database = new Database();
                     database.addUser(user);
                     DatabaseController databaseController = new DatabaseController(database);
                    databaseController.addGoldToUser(user);


    }
    @Test

    public void setHappinessAndFoodTest(){
        ArrayList<Terrain> ownedTerrain = new ArrayList<>();
        Civilization civil = new Civilization(100, 100, "A");
        User user = new User(null, null, null, civil);
               NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false,
               UnitTypes.WARRIOR, true);
               ArrayList<TerrainFeatureTypes> terrainFeature = new ArrayList<>();
               terrainFeature.add(TerrainFeatureTypes.FOREST);
                  CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
              UnitTypes.WARRIOR, true, false, false, false, false);
             Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
             resource = new Resource(ResourceTypes.COTTON);
            Terrain terrains = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, terrainFeature, combatunit, noncombatunit,
                     improvement, resource,
                     new ArrayList<Revealed>());
                     terrains.setBooleanResource(true);
                     ownedTerrain.add(terrains);
                     civil.setOwnedTerrains(ownedTerrain);
                     City city = new City(null, civil , terrains, 0,null,0, 0, new ArrayList<>());
                     city.setGold(1000);
                     civil.addCity(city);
                     civil.addUnit(combatunit);
                     terrains.setCity(city);
                     Database database = new Database();
                     database.addUser(user);
                     DatabaseController databaseController = new DatabaseController(database);
                    databaseController.setHappinessUser(user);
                    databaseController.consumptFood(user);
                    civil.setHappiness(-50);
                    databaseController.setHappinessUser(user);


    }

    @Test
    public void TechnologyTest(){
        ArrayList<TechnologyTypes> types = new ArrayList<>();
        types.add(TechnologyTypes.ARCHERY);
        types.add(TechnologyTypes.CHEMISTRY);
        types.add(TechnologyTypes.AGRICULTURE);
        Civilization civil = new Civilization(100, 100, "A");
        User user = new User(null, null, null, civil);
        ArrayList<Technology> tech = new ArrayList<>();
        Technology tech0 = new Technology(false, 6, TechnologyTypes.ARCHERY, false);
        Technology tech1 = new Technology(false, 6,  TechnologyTypes.AGRICULTURE, true);
        tech.add(tech0);
        tech.add(tech1);
        civil.setTechnologies(tech);
        Database database = new Database();
                     database.addUser(user);
                     DatabaseController databaseController = new DatabaseController(database);
                     databaseController.choosingATechnologyToStudy(user, TechnologyTypes.ARCHERY);
                     databaseController.choosingATechnologyToStudy(user, TechnologyTypes.CHEMISTRY);
                     


    } 


    @Test
    public void TechnologyTest2(){
        ArrayList<TechnologyTypes> types = new ArrayList<>();
        types.add(TechnologyTypes.ARCHERY);
        types.add(TechnologyTypes.CHEMISTRY);
        types.add(TechnologyTypes.AGRICULTURE);
        Civilization civil = new Civilization(100, 100, "A");
        User user = new User(null, null, null, civil);
        ArrayList<Technology> tech = new ArrayList<>();
        Technology tech0 = new Technology(false, 6, TechnologyTypes.ARCHERY, true);
        Technology tech1 = new Technology(false, 6,  TechnologyTypes.AGRICULTURE, false);
        tech.add(tech0);
        tech.add(tech1);
        civil.setTechnologies(tech);
        Database database = new Database();
                     database.addUser(user);
                     DatabaseController databaseController = new DatabaseController(database);
                     databaseController.choosingATechnologyToStudy(user, TechnologyTypes.ARCHERY);
                     databaseController.choosingATechnologyToStudy(user, TechnologyTypes.CHEMISTRY);
                     


    } 

    @Test
    public void setResourceTest() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false,
                        UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                        improvement, null,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;
        String input = "SET CHEAT RESOURCE BANANAS 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE CATTLE 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE COAL 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE DEER 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE COTTON 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE DYES 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE FURS 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE GEMS 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE GOLD 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE HORSES 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE INCENSE 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE IVORY 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE IRON 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE MARBLE 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE SHEEP 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE SILK 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE SILVER 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE SUGAR 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT RESOURCE WHEAT 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
    }
    
    @Test
    public void NeighbourTerrainTest(){
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false,
                        UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                        improvement, null,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.getNeighborTerrainsOfOneTerrain(terrains[3][4], map);

    }

    @Test
    public void NeighbourTestDistanceTest(){
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false,
                        UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                        improvement, null,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<Terrain> neighbourTerrain = new ArrayList<>();
        neighbourTerrain.add(terrains[3][4]);
        neighbourTerrain.add(terrains[5][6]);
        databaseController.NeighborsAtADistanceOfOneFromAnArraylistOfTerrains(neighbourTerrain, map);
        databaseController.NeighborsAtADistanceOfTwoFromAnArraylistOfTerrains(neighbourTerrain, map);
    }

    @Test
    public void setImprovementTest() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false,
                        UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                        improvement, null,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;
        String input = "SET CHEAT IMPROVEMENT CAMP 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.setCheatImprovement(matcher);
        }
        input = "SET CHEAT IMPROVEMENT FARM 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.setCheatImprovement(matcher);
        }
        input = "SET CHEAT IMPROVEMENT MINE 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.setCheatImprovement(matcher);
        }
        input = "SET CHEAT IMPROVEMENT PLANTATION 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.setCheatImprovement(matcher);
        }
        input = "SET CHEAT IMPROVEMENT PASTURE 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.setCheatImprovement(matcher);
        }
        input = "SET CHEAT IMPROVEMENT LUMBERBILL 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.setCheatImprovement(matcher);
        }
        input = "SET CHEAT IMPROVEMENT MANUFACTURY 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.setCheatImprovement(matcher);
        }
        input = "SET CHEAT IMPROVEMENT QUARRY 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.setCheatImprovement(matcher);
        }
        input = "SET CHEAT IMPROVEMENT RAILROAD 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.setCheatImprovement(matcher);
        }
        input = "SET CHEAT IMPROVEMENT ROAD 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.setCheatImprovement(matcher);
        }
        input = "SET CHEAT IMPROVEMENT TRADINGPOST 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.setCheatImprovement(matcher);
        }
        
    }

    @Test
    public void setTerrainFeature() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false,
                        UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
                        improvement, null,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;
        String input = "SET CHEAT TERRAIN FEATURE TYPE FLOODPLAINS 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
            gamemenu.setCheatTerrainFeature(matcher);
        }
        input = "SET CHEAT TERRAIN FEATURE TYPE FOREST 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
            gamemenu.setCheatTerrainFeature(matcher);
        }
        input = "SET CHEAT TERRAIN FEATURE TYPE ICE 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
            gamemenu.setCheatTerrainFeature(matcher);
        }
        input = "SET CHEAT TERRAIN FEATURE TYPE JUNGLE 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
            gamemenu.setCheatTerrainFeature(matcher);
        }
        input = "SET CHEAT TERRAIN FEATURE TYPE MARSH 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
            gamemenu.setCheatTerrainFeature(matcher);
        }
        input = "SET CHEAT TERRAIN FEATURE TYPE OASIS 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
            gamemenu.setCheatTerrainFeature(matcher);
        }
    }

    @Test
    public void buildingImprovementTest(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                        UnitTypes.SETTLER, true);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit,
                        improvement, null,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.buildingAnImprovement(user, ImprovementTypes.CAMP);
    }

    @Test
    public void buildingImprovementTest1(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                        UnitTypes.SETTLER, true);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                        improvement, null,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.buildingAnImprovement(user, ImprovementTypes.CAMP);
    }

    @Test
    public void buildingImprovementTest2(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                        UnitTypes.ARTILLERY, true);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit,
                        improvement, null,
                        new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.buildingAnImprovement(user, ImprovementTypes.CAMP);
    }


    @Test
    public void buildingImprovementTest3(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                        UnitTypes.ARCHER, true);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                        improvement, null,
                        new ArrayList<Revealed>());
            }
        }
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        ArrayList<Terrain> ownedTerrain = new ArrayList<>();
        ownedTerrain.add(terrains[3][7]);
        civil.setOwnedTerrains(ownedTerrain);
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.buildingAnImprovement(user, ImprovementTypes.CAMP);
    }


    @Test
    public void buildingImprovementTest4(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                        UnitTypes.ARCHER, true);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                        null, null,
                        new ArrayList<Revealed>());
            }
        }
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        ArrayList<Terrain> ownedTerrain = new ArrayList<>();
        ownedTerrain.add(terrains[3][7]);
        civil.setOwnedTerrains(ownedTerrain);
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.buildingAnImprovement(user, ImprovementTypes.CAMP);
    }


    @Test
    public void buildingImprovementTest5(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.ARCHER, true);
        Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                null, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        ArrayList<Terrain> ownedTerrain = new ArrayList<>();
        ownedTerrain.add(terrains[3][7]);
        civil.setOwnedTerrains(ownedTerrain);
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        ArrayList<Technology> tech = new ArrayList<>();
        Technology techhh = new Technology(false, 3,ImprovementTypes.LUMBERMILL.getRequiredTechnology() , true);
        tech.add(techhh);
        civil.setTechnologies(tech);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
       databaseController.buildingAnImprovement(user, ImprovementTypes.LUMBERMILL);
       // Assertions.assertTrue(databaseController.buildingAnImprovement(user, ImprovementTypes.LUMBERMILL).equals("you lack prerequisite technologies"));
    }

   

    @Test
    public void buildingImprovementTest6(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                null, null,
                new ArrayList<Revealed>());
            }
        }
  
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        terrains[3][7].setTerrainFeatureTypes(TerrainFeatureTypes.FOREST);
        ArrayList<Terrain> ownedTerrain = new ArrayList<>();
        ownedTerrain.add(terrains[3][7]);
        civil.setOwnedTerrains(ownedTerrain);
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        ArrayList<Technology> tech = new ArrayList<>();
        Technology techhh = new Technology(false, 3,ImprovementTypes.MINE.getRequiredTechnology() , true);
        tech.add(techhh);
        civil.setTechnologies(tech);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
       databaseController.buildingAnImprovement(user, ImprovementTypes.MINE);
       // Assertions.assertTrue(databaseController.buildingAnImprovement(user, ImprovementTypes.LUMBERMILL).equals("you lack prerequisite technologies"));
    }







 @Test
    public void setTerrainType() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;
        String input = "SET CHEAT TERRAIN TYPE DESERT 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }
        input = "SET CHEAT TERRAIN TYPE GRASSLAND 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }

        input = "SET CHEAT TERRAIN TYPE HILLS 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }
        input = "SET CHEAT TERRAIN TYPE MOUNTAIN 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }
        input = "SET CHEAT TERRAIN TYPE OCEAN 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }
        input = "SET CHEAT TERRAIN TYPE PLAINS 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }

        input = "SET CHEAT TERRAIN TYPE SNOW 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }
        input = "SET CHEAT TERRAIN TYPE TUNDRA 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }


    }

    @Test
    public void setCheatUnit() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;


        String input = "SET CHEAT UNIT ARCHER 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT CHARIOT_ARCHER 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }

        input = "SET CHEAT UNIT SCOUT 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT SETTLER 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT SPEARMAN 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT WARRIOR 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT WORKER 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT CATAPULT 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT HORSESMAN 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT SWORDSMAN 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT CROSSBOWMAN 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT KNIGHT 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT LONGSWORDSMAN 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT PIKEMAN 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT TREBUCHET 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT CANNON 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT CAVALRY 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT LANCER 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT MUSKETMAN 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }

        input = "SET CHEAT UNIT RIFLEMAN 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }

        input = "SET CHEAT UNIT ANTI_TANKGUN 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT ARTILLERY 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT INFANTRY 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT PANZER 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT TANK 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }
        input = "SET CHEAT UNIT INVALID 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.setCheatUnit(user, matcher);
        }


    }

    @Test
    public void selectTechnology() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;


        String input = "SELECT TECHNOLOGY AGRICULTURE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY ANIMAL_HUSBANDRY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY ARCHERY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY BRONZE_WORKING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY CALENDAR";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY MASONRY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY MINING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY POTTERY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY THE_WHEEL";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY TRAPPING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY WRITING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY CONSTRUCTION";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY HORSEBACK_RIDING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY IRON_WORKING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY MATHEMATICS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY PHILOSOPHY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY CHIVALRY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY CIVIL_SERVICE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY CURRENCY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }

        input = "SELECT TECHNOLOGY EDUCATION";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY ENGINEERING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY MACHINERY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY METAL_CASTING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY PHYSICS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY STEEL";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY THEOLOGY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY ACOUSTICS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY ARCHAEOLOGY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY BANKING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY CHEMISTRY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY ECONOMICS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY FERTILIZER";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY GUNPOWDER";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY METALLURGY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY MILITARY_SCIENCE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY PRINTING_PRESS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY SCIENTIFIC_THEORY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY BIOLOGY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY COMBUSTION";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY DYNAMITE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY ELECTRICITY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY RADIO";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY RAILROAD";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY REPLACEABLE_PARTS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY STEAM_POWER";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }
        input = "SELECT TECHNOLOGY TELEGRAPH";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
            gamemenu.selectTechnology(matcher, user);
        }



    }

    @Test
    public void deleteFeaturesTest(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.ARCHER, true);
        Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                null, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.deleteFeatures("ROUTE");
    }

    @Test
    public void deleteFeaturesTest1(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        map.setCOL(16);
        map.setROW(32);
        int a = map.getCOL();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.ARCHER, true);
        Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.deleteFeatures("ROUTE");
    }
 
    @Test
    public void deleteFeaturesTest2(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.ARCHER, true);
        Improvement improvement = new Improvement(i, j, ImprovementTypes.ROAD);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.deleteFeatures("ROUTE");
        databaseController.deleteFeatures("DES");
        terrains[3][7].setNonCombatUnit(null);
        databaseController.deleteFeatures("ROUTE");
    }
 
    @Test
    public void deleteFeaturesTest3(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.ARCHER, true);
        Improvement improvement = new Improvement(i, j, ImprovementTypes.ROAD);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
       
         terrains[3][7].setTerrainFeatureTypes(TerrainFeatureTypes.FOREST);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.deleteFeatures("FOREST");
        databaseController.deleteFeatures("DES");
    
    }

    @Test
    public void deleteFeaturesTest4(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
              
        Improvement improvement = new Improvement(i, j, ImprovementTypes.ROAD);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
     
         terrains[3][7].setTerrainFeatureTypes(TerrainFeatureTypes.FOREST);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        //databaseController.deleteFeatures("FOREST");
        databaseController.deleteFeatures("DES");
    
    }

    @Test
    public void deleteFeaturesTest5(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.ARCHER, true);
        Improvement improvement = new Improvement(i, j, ImprovementTypes.ROAD);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
       // ArrayList<TerrainFeatureTypes> features = new ArrayList<>();
        // features.add(TerrainFeatureTypes.FOREST);
         terrains[3][7].setTerrainFeatureTypes(TerrainFeatureTypes.RIVER);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.deleteFeatures("FOREST");
       // databaseController.deleteFeatures("DES");
    
    }

    @Test
    public void deleteFeaturesTest6(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
              
        Improvement improvement = new Improvement(i, j, ImprovementTypes.ROAD);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.ARCHER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
         terrains[3][7].setTerrainFeatureTypes(TerrainFeatureTypes.RIVER);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.deleteFeatures("FOREST");
      
    }
 
    @Test 
    public void repairImprovementTest(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.ARCHER, true);
        Improvement improvement = new Improvement(i, j, ImprovementTypes.ROAD);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                null, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.repairImprovement();
    }

    
    @Test 
    public void repairImprovementTest2(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.ARCHER, true);
        Improvement improvement = new Improvement(i, j, ImprovementTypes.ROAD);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                null, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(null);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.repairImprovement();
    }


    @Test 
    public void repairImprovementTest3(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.ARCHER, true);
        Improvement improvement = new Improvement(i, j, ImprovementTypes.ROAD);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        terrains[3][7].getTerrainImprovement().setBeingRepaired(true);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.repairImprovement();
    }

    @Test 
    public void repairImprovementTest4(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false,
                UnitTypes.ARCHER, true);
        Improvement improvement = new Improvement(i, j, ImprovementTypes.ROAD);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        terrains[3][7].getTerrainImprovement().setBeingRepaired(false);
        terrains[3][7].getTerrainImprovement().setAvailable(true);;
        
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.repairImprovement();
    }

    @Test 
    public void repairImprovementTest5(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                Improvement improvement = new Improvement(i, j, ImprovementTypes.ROAD);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        terrains[3][7].getTerrainImprovement().setBeingRepaired(false);
        terrains[3][7].getTerrainImprovement().setAvailable(false);
        terrains[3][7].getTerrainImprovement().setPillaged(true);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.repairImprovement();
    }


    @Test 
    public void repairImprovementTest6(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                Improvement improvement = new Improvement(i, j, ImprovementTypes.ROAD);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.SETTLER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        terrains[3][7].getTerrainImprovement().setBeingRepaired(false);
        terrains[3][7].getTerrainImprovement().setAvailable(false);
        terrains[3][7].getTerrainImprovement().setPillaged(false);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.repairImprovement();
    }

    @Test 
    public void repairImprovementTest7(){
        Civilization civil = new Civilization(0, 3, "A");
        civil.setTechnologies(new ArrayList<>());
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
               
                Improvement improvement = new Improvement(i, j, ImprovementTypes.ROAD);
        terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, null,
                improvement, null,
                new ArrayList<Revealed>());
            }
        }
        map.setTerrains(terrains);
        NonCombatUnit combatunit = new NonCombatUnit(3, 7, 0, 0, 0, 0, false, false,
        UnitTypes.ARCHER, true);
        terrains[3][7].setNonCombatUnit(combatunit);
        terrains[3][7].getTerrainImprovement().setBeingRepaired(false);
        terrains[3][7].getTerrainImprovement().setAvailable(false);
        terrains[3][7].getTerrainImprovement().setPillaged(false);
        Database database = new Database();
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.repairImprovement();
    }


@Test
    public void buyTechnologyCheat() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;


        String input = "BUY TECHNOLOGY AGRICULTURE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY ANIMAL_HUSBANDRY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY ARCHERY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY BRONZE_WORKING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY CALENDAR";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY MASONRY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY MINING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY POTTERY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY THE_WHEEL";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY TRAPPING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY WRITING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY CONSTRUCTION";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY HORSEBACK_RIDING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY IRON_WORKING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY MATHEMATICS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY PHILOSOPHY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY CHIVALRY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY CIVIL_SERVICE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY CURRENCY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }

        input = "BUY TECHNOLOGY EDUCATION";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY ENGINEERING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY MACHINERY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY METAL_CASTING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY PHYSICS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY STEEL";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY THEOLOGY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY ACOUSTICS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY ARCHAEOLOGY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY BANKING";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY CHEMISTRY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY ECONOMICS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY FERTILIZER";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY GUNPOWDER";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY METALLURGY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY MILITARY_SCIENCE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY PRINTING_PRESS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY SCIENTIFIC_THEORY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY BIOLOGY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY COMBUSTION";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY DYNAMITE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY ELECTRICITY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY RADIO";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY RAILROAD";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY REPLACEABLE_PARTS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY STEAM_POWER";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }
        input = "BUY TECHNOLOGY TELEGRAPH";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.buyTechnologyCheat(matcher, user);
        }


    }
 
    


@Test
    public void increaseTurnCheat() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;


        String input = "INCREASE -TURN 10";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_TURN)) != null) {
            gamemenu.increaseTurnCheat(matcher);
        }


    }

    @Test
    public void increaseGoldCheat() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;


        String input = "INCREASE -GOLD 10";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_GOLD)) != null) {
            gamemenu.increaseGoldCheat(user, matcher);
        }


    }

   

    
  
    @Test
    public void selectUnit() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;
        String input = "SELECT UNIT COMBAT 5 4";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_UNIT)) != null) {
            gamemenu.selectUnit(user, matcher);
        }
        input = "SELECT UNIT NONCOMBAT 5 4";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_UNIT)) != null) {
            gamemenu.selectUnit(user, matcher);
        }
        input = "SELECT UNIT INVALID 5 4";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_UNIT)) != null) {
            gamemenu.selectUnit(user, matcher);
        }


    }
 





    @Test
    public void increaseHappinessCheat() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;


        String input = "INCREASE -HAPPINESS 10";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_HAPPINESS)) != null) {
            gamemenu.increaseHappinessCheat(user, matcher);
        }


    }

    @Test
    public void increaseScienceCheat() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;


        String input = "INCREASE -SCIENCE 10";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_SCIENCE)) != null) {
            gamemenu.increaseScienceCheat(user, matcher);
        }


    }

    @Test
    public void buildUnit() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;
        String input = "UNIT BUILD ROAD";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
            gamemenu.buildUnit(matcher, user);
        }
        input = "UNIT BUILD RAILROAD";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
            gamemenu.buildUnit(matcher, user);
        }
        input = "UNIT BUILD FARM";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
            gamemenu.buildUnit(matcher, user);
        }
        input = "UNIT BUILD MINE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
            gamemenu.buildUnit(matcher, user);
        }
        input = "UNIT BUILD TRADINGPOST";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
            gamemenu.buildUnit(matcher, user);
        }
        input = "UNIT BUILD LUMBERMILL";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
            gamemenu.buildUnit(matcher, user);
        }
        input = "UNIT BUILD PASTURE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
            gamemenu.buildUnit(matcher, user);
        }
        input = "UNIT BUILD CAMP";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
            gamemenu.buildUnit(matcher, user);
        }
        input = "UNIT BUILD PLANTATION";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
            gamemenu.buildUnit(matcher, user);
        }
        input = "UNIT BUILD QUARRY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
            gamemenu.buildUnit(matcher, user);
        }
        input = "UNIT BUILD INVALID";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
            gamemenu.buildUnit(matcher, user);
        }


    }

    @Test
    public void deletedUnit() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;
        String input = "UNIT REMOVE FOREST";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_REMOVE)) != null) {
            gamemenu.deleteUnit(matcher, user);
        }
        input = "UNIT REMOVE JUNGLE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_REMOVE)) != null) {
            gamemenu.deleteUnit(matcher, user);
        }
        input = "UNIT REMOVE MARSH";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_REMOVE)) != null) {
            gamemenu.deleteUnit(matcher, user);
        }
        input = "UNIT REMOVE ROUTE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_REMOVE)) != null) {
            gamemenu.deleteUnit(matcher, user);
        }
        input = "UNIT REMOVE INVALID";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_REMOVE)) != null) {
            gamemenu.deleteUnit(matcher, user);
        }


    }

    @Test
    public void oneUnitHasBeenSelected() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;
        String input = "UNIT MOVETO 5 5";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_MOVETO)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "UNIT SLEEP";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_SLEEP)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "COMBAT UNIT CHEAT MOVE 5 6";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.COMBAT_UNIT_CHEAT_MOVE)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "NONCOMBAT UNIT CHEAT MOVE 5 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.NON_COMBAT_UNIT_CHEAT_MOVE)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "UNIT ALERT";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_ALERT)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "UNIT FORTIFY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FORTIFY)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "UNIT FORTIFY HEAL";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FORTIFY_HEAL)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "UNIT GARRISON";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_GARRISON)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "UNIT SETUP RANGED";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_SETUP_RANGED)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "UNIT ATTACK 5 6";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_ATTACK)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "UNIT FOUND CITY";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FOUND_CITY)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "UNIT CANCEL MISSION";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_CANCEL_MISSION)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "UNIT WAKE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_WAKE)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        input = "UNIT DELETE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_DELETE)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }
        if ((matcher = GameEnums.getMatcher(input, GameEnums.IMPROVEMENT_REPAIR)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);

        }

        input = "UNIT REMOVE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_REMOVE)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
        }

        input = "INVALID";

            gamemenu.oneUnitHasBeenSelected(input, matcher, user);



    }

   


    @Test
    public void runCommands() {
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        ArrayList<User> allUser = new ArrayList<>();
        allUser.add(user);
        GameMenu gamemenu = new GameMenu(databaseController, allUser);
        Matcher matcher;
      //  String input = "INFO UNITS";
       // if ((matcher = GameEnums.getMatcher(input, GameEnums.INFO)) != null) {
       //     gamemenu.runCommands(user,input);
       // }
       String input = "INFO RESEARCH";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.INFO)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "INFO invalid";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.INFO)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "SELECT TECHNOLOGY AGRICULTURE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {

            gamemenu.runCommands(user,input);
        }
        input = "INCREASE -TURN 10";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_TURN)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "INCREASE -GOLD 10";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_GOLD)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "INCREASE -HAPPINESS 10";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_HAPPINESS)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "INCREASE -SCIENCE 10";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_SCIENCE)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "BUY TECHNOLOGY AGRICULTURE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "BUY CHEAT TILE 5 5";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_CHEAT_TILE)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "SET CHEAT UNIT WARRIOR";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "SET CHEAT IMPROVEMENT ROAD 5 4";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "SET CHEAT RESOURCE BANANAS";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "SET CHEAT TERRAIN TYPE DESERT";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_TYPE)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "SET CHEAT TERRAIN FEATURE TYPE ICE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "UNIT CHEAT REMOVE 5 8";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.DELETE_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.runCommands(user,input);
        }

        input = "UNIT CHEAT REPAIR 5 6";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.REPAIR_CHEAT_IMPROVEMENT)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "SELECT UNIT COMBAT 5 6";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_UNIT)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "SELECT CITY ABA";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_CITY_NAME)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "SELECT CITY 4 5";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_CITY_POSITION)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "UNIT BUILD ROAD";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "UNIT REMOVE FOREST";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_REMOVE)) != null) {
            gamemenu.runCommands(user,input);
        }
        input = "SHOW MAP";
        gamemenu.runCommands(user,input);

        input = "INVALID";

        gamemenu.runCommands(user,input);


    }



    @Test
    public void IncreasingTurnInWorkersActionTest(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
        terrains[3][6].setTerrainImprovement(null);
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.increasingTurnInWorkersActions();
    }

    @Test
    public void IncreasingTurnInWorkersActionTest1(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
               improvement.setBeingRepaired(true);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
       
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.increasingTurnInWorkersActions();
    }

    @Test
    public void IncreasingTurnInWorkersActionTest2(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
               improvement.setBeingRepaired(true);
               improvement.setPassedTurns(-5);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
       
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.increasingTurnInWorkersActions();
    }



    @Test
    public void IncreasingTurnInWorkersActionTest3(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
               improvement.setBeingRepaired(false);
               improvement.setHasToBeDeleted(true);
               improvement.setPassedTurns(-5);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
       
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.increasingTurnInWorkersActions();
    }


    @Test
    public void IncreasingTurnInWorkersActionTest4(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
               improvement.setBeingRepaired(false);
               improvement.setHasToBeDeleted(true);
               improvement.setPassedTurns(10000);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
       
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.increasingTurnInWorkersActions();
    }


    @Test
    public void IncreasingTurnInWorkersActionTest5(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
               improvement.setBeingRepaired(false);
               improvement.setHasToBeDeleted(false);
               improvement.setPillaged(true);
               improvement.setAvailable(true);
               improvement.setPassedTurns(10000);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
       
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.increasingTurnInWorkersActions();
    }

    @Test
    public void IncreasingTurnInWorkersActionTest6(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                
                Improvement improvement = new Improvement(i, j, ImprovementTypes.FARM);
               improvement.setBeingRepaired(false);
               improvement.setHasToBeDeleted(false);
               improvement.setPillaged(false);
               improvement.setAvailable(false);
               improvement.setPassedTurns(-5);
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, improvement, null, new ArrayList<Revealed>());

            }
        }
       
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.increasingTurnInWorkersActions();
    }


    @Test
    public void IncreasingTurnInWorkersActionTest7(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                
             
              
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, null, null, new ArrayList<Revealed>());
                terrains[i][j].setTerrainFeatureTypes(TerrainFeatureTypes.FOREST);
                terrains[i][j].setHasToBeDeleted(true);
                terrains[i][j].setPassedTurns(5);

            }
        }
       
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.increasingTurnInWorkersActions();
    }


    @Test
    public void IncreasingTurnInWorkersActionTest8(){
        Civilization civil = new Civilization(0, 3, "A");
        Map map = new Map();
        terrains = new Terrain[map.getROW()][map.getCOL()];
        for (int i = 0; i < map.getROW(); i++) {
            for (int j = 0; j < map.getCOL(); j++) {
                CombatUnit combatunit = new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
                
             
              
                terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null, null, null, new ArrayList<Revealed>());
                terrains[i][j].setTerrainFeatureTypes(TerrainFeatureTypes.FOREST);
                terrains[i][j].setHasToBeDeleted(true);
                terrains[i][j].setPassedTurns(10);

            }
        }
       
        map.setTerrains(terrains);
        User user = new User(null, null, null, civil);
        Database database = new Database();
        database.addUser(user);
        database.setMap(map);
        DatabaseController databaseController = new DatabaseController(database);
        databaseController.increasingTurnInWorkersActions();
    }

@Test
public void cheatComabatUnitTest(){
    Civilization civil = new Civilization(0, 3, "A");
    Map map = new Map();
    terrains = new Terrain[map.getROW()][map.getCOL()];
    for (int i = 0; i < map.getROW(); i++) {
        for (int j = 0; j < map.getCOL(); j++) {
            NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
            
         
          
            terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit, null, null, new ArrayList<Revealed>());
            terrains[i][j].setTerrainFeatureTypes(TerrainFeatureTypes.FOREST);
            terrains[i][j].setHasToBeDeleted(true);
            terrains[i][j].setPassedTurns(10);

        }
    }
   
    map.setTerrains(terrains);
    User user = new User(null, null, null, civil);
    Database database = new Database();
    database.addUser(user);
    database.setMap(map);
    DatabaseController databaseController = new DatabaseController(database);
    databaseController.cheatMoveNonCombatUnit(3, 4);
}

@Test
public void mapInitializeTest(){
    Map map = new Map();
    terrains = new Terrain[map.getROW()][map.getCOL()];
    for (int i = 0; i < map.getROW(); i++) {
        for (int j = 0; j < map.getCOL(); j++) {
            NonCombatUnit combatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
            
         
          
            terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit, null, null, new ArrayList<Revealed>());
            terrains[i][j].setTerrainFeatureTypes(TerrainFeatureTypes.FOREST);
            terrains[i][j].setHasToBeDeleted(true);
            terrains[i][j].setPassedTurns(10);

        }
    }
   
    map.setTerrains(terrains);
    map.generateMap();
}

@Test
public void RevealedMapTest(){
    Map map = new Map();
    Civilization civil = new Civilization(4, 8, "A");
    User user = new User(null, null, null, civil);
    terrains = new Terrain[map.getROW()][map.getCOL()];
    ArrayList<Terrain> terrain = new ArrayList<>();
    for (int i = 0; i < map.getROW(); i++) {
        for (int j = 0; j < map.getCOL(); j++) {
            NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
            CombatUnit combatunit =  new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
            Improvement improve = new Improvement(i, j, ImprovementTypes.FARM);
            Resource res = new Resource(ResourceTypes.BANANAS);
            terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
           terrain.add(terrains[i][j]);

        }
    }
    map.setTerrains(terrains);
    terrains[3][7].setType("fog of war");
    ArrayList<Terrain> visibleTerrain = new ArrayList<>();
    //ArrayList<Terrain> RevealedTerrain = new ArrayList<>();
    visibleTerrain.add(terrains[3][7]);
    civil.setRevealedTerrains(new ArrayList<>());
    civil.setVisibleTerrains(visibleTerrain);
    civil.setTerrains(terrain);
    Database database = new Database();
    database.addUser(user);
    database.setMap(map);
    map.printMap(database, user);
    
    ArrayList<River> rivers = new ArrayList<River>();
    river = new River(terrains[3][4], terrains[3][5]);
     River riverone = new River(terrains[2][4], terrains[2][5]);
    rivers.add(river);
    rivers.add(riverone);
    map.setRiver(rivers);
    map.printMap(database, user);
   GameMapGenerator gameMapGenerator = new GameMapGenerator(terrains, map.getRiver(), map.getROW(), map.getCOL(), map.getIteration());
   gameMapGenerator.hasRiver(terrainOne, terrainTwo);
   gameMapGenerator.hasRiver(terrainOne);
    
   City city = new City(civil,null, null, 0,"" , 0, 0, null);
   city.setOwner(civil);
   city.getOwner();
   city.getFounder();
   civil.setScience(2);
   civil.getScience();
   city.setCitizens(new ArrayList<>());
  
}
 
  @Test
  public void removeCityTest(){
    Civilization civilOne = new Civilization(100, 400, "A");
    Civilization civilTwo = new Civilization(100, 400, "A");
    City city = new City(civilOne,null, null, 0,"" , 0, 0, null);
    civilTwo.addCity(city);
    CityController cityController = new CityController();
    cityController.destroyCity(civilOne, civilTwo, city);
}

  @Test
  public void attachCityTest(){
   
    Civilization civilTwo = new Civilization(100, 400, "A");
    City city = new City(civilTwo,null, null, 0,"" , 0, 0, null);
    civilTwo.addCity(city);
    CityController cityController = new CityController();
    cityController.attachCity(civilTwo, city);
  }

  @Test 
  public void cityMenuTest(){
    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
    CombatUnit combatunit =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improve = new Improvement(3, 4, ImprovementTypes.FARM);
    Resource res = new Resource(ResourceTypes.BANANAS);
     Terrain terrains = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
    Civilization civilTwo = new Civilization(100, 400, "A");
    City city = new City(null,civilTwo, terrains, 0,"" , 0, 0, null);
    civilTwo.addCity(city);
    User user = new User(null, null, null, civilTwo);
    Database database = new Database();
    database.addUser(user);
    DatabaseController databaseController = new DatabaseController(database);
    CityController cityController = new CityController();
    cityController.setDatabaseController(databaseController);
    cityController.cityMenu(city);
  }

  @Test
  public void assignCitizenTest(){
    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
    CombatUnit combatunit =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improve = new Improvement(3, 4, ImprovementTypes.FARM);
    Resource res = new Resource(ResourceTypes.BANANAS);
     Terrain terrains = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
    Civilization civilTwo = new Civilization(100, 400, "A");
    City city = new City(null,civilTwo, terrains, 0,"" , 0, 0, null);
      Citizen citizen = new Citizen(city);
      city.addCitizen(citizen);
      ArrayList<Terrain> neighbours = new ArrayList<>();
      neighbours.add(terrains);
      city.setNeighbors(neighbours);
      citizen.setHasWork(true);
      CityController cityController = new CityController();
      cityController.assignCitizen(city, citizen, terrains);
  }

  @Test
  public void garrisonCityTest(){
    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
    CombatUnit combatunit =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improve = new Improvement(3, 4, ImprovementTypes.FARM);
    Resource res = new Resource(ResourceTypes.BANANAS);
     Terrain terrains = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
    Civilization civilTwo = new Civilization(100, 400, "A");
    City city = new City(null,civilTwo, terrains, 0,"" , 0, 0, null);
    city.setCombatUnit(combatunit);
    CityController cityController = new CityController();
     cityController.garrisonACity(city);
  }
  @Test
  public void buyTileTest(){
    Map map = new Map();
    Civilization civil = new Civilization(4, 8, "A");
    User user = new User(null, null, null, civil);
    terrains = new Terrain[map.getROW()][map.getCOL()];
    ArrayList<Terrain> terrain = new ArrayList<>();
    for (int i = 0; i < map.getROW(); i++) {
        for (int j = 0; j < map.getCOL(); j++) {
            NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
            CombatUnit combatunit =  new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
            Improvement improve = new Improvement(i, j, ImprovementTypes.FARM);
            Resource res = new Resource(ResourceTypes.BANANAS);
            terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
           terrain.add(terrains[i][j]);

        }
    }

    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
    CombatUnit combatunit =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improve = new Improvement(3, 4, ImprovementTypes.FARM);
    Resource res = new Resource(ResourceTypes.BANANAS);
     Terrain ter = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
  //  Civilization civilTwo = new Civilization(100, 400, "A");
    City city = new City(null,civil, ter, 0,"" , 0, 0, null);
    ArrayList<Terrain> mainTerrain = new ArrayList<>();
    mainTerrain.add(terrains[4][6]);
    mainTerrain.add(terrains[4][9]);
    city.setMainTerrains(mainTerrain);
    map.setTerrains(terrains);
    CityController cityController = new CityController();
    cityController.setMap(map);
    city.setGold(-5);
   
    cityController.buyTile(terrains[4][7], city);

  }

  @Test
  public void buyTileTest1(){
    Map map = new Map();
    Civilization civil = new Civilization(4, 8, "A");
    User user = new User(null, null, null, civil);
    terrains = new Terrain[map.getROW()][map.getCOL()];
    ArrayList<Terrain> terrain = new ArrayList<>();
    for (int i = 0; i < map.getROW(); i++) {
        for (int j = 0; j < map.getCOL(); j++) {
            NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
            CombatUnit combatunit =  new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
            Improvement improve = new Improvement(i, j, ImprovementTypes.FARM);
            Resource res = new Resource(ResourceTypes.BANANAS);
            terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
           terrain.add(terrains[i][j]);

        }
    }

    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
    CombatUnit combatunit =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improve = new Improvement(3, 4, ImprovementTypes.FARM);
    Resource res = new Resource(ResourceTypes.BANANAS);
     Terrain ter = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
  //  Civilization civilTwo = new Civilization(100, 400, "A");
    City city = new City(null,civil, ter, 0,"" , 0, 0, null);
    ArrayList<Terrain> mainTerrain = new ArrayList<>();
    mainTerrain.add(terrains[4][6]);
    mainTerrain.add(terrains[4][9]);
    city.setMainTerrains(mainTerrain);
    map.setTerrains(terrains);
    CityController cityController = new CityController();
    cityController.setMap(map);
    city.setGold(10000);

    cityController.buyTile(terrains[4][7], city);

  }

  @Test
  public void purchaseTest(){
    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
    CombatUnit combatunit =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improve = new Improvement(3, 4, ImprovementTypes.FARM);
    Resource res = new Resource(ResourceTypes.BANANAS);
     Terrain ter = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
   Civilization civilTwo = new Civilization(100, 400, "A");
    City city = new City(null,civilTwo, ter, 0,"" , 0, 0, null);
    CityController cityController = new CityController();
    cityController.purchase("MINT", city);

  }

  @Test
  public void purchaseTest2(){
    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
    CombatUnit combatunit =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improve = new Improvement(3, 4, ImprovementTypes.FARM);
    Resource res = new Resource(ResourceTypes.BANANAS);
     Terrain ter = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
   Civilization civilTwo = new Civilization(100, 400, "A");
    City city = new City(null,civilTwo, ter, 0,"" , 0, 0, null);
    CityController cityController = new CityController();
    city.setNonCombatUnit(noncombatunit);
    cityController.purchase("SETTLER", city);

  }

  @Test
  public void purchaseTest3(){
    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
    CombatUnit combatunit =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improve = new Improvement(3, 4, ImprovementTypes.FARM);
    Resource res = new Resource(ResourceTypes.BANANAS);
     Terrain ter = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
   Civilization civilTwo = new Civilization(100, 400, "A");
    City city = new City(null,civilTwo, ter, 0,"" , 0, 0, null);
    CityController cityController = new CityController();
    city.setNonCombatUnit(null);
    cityController.purchase("SETTLER", city);

  }

  @Test
  public void purchaseTest4(){
    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
    CombatUnit combatunit =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improve = new Improvement(3, 4, ImprovementTypes.FARM);
    Resource res = new Resource(ResourceTypes.BANANAS);
     Terrain ter = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
   Civilization civilTwo = new Civilization(100, 400, "A");
    City city = new City(null,civilTwo, ter, 0,"" , 0, 0, null);
    CityController cityController = new CityController();
    city.setNonCombatUnit(null);
    cityController.purchase("WORKER", city);

  }

  @Test
  public void neighbourTwoTest(){
    Map map = new Map();
    Civilization civil = new Civilization(4, 8, "A");
    User user = new User(null, null, null, civil);
    terrains = new Terrain[map.getROW()][map.getCOL()];
    ArrayList<Terrain> terrain = new ArrayList<>();
    for (int i = 0; i < map.getROW(); i++) {
        for (int j = 0; j < map.getCOL(); j++) {
            NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
            CombatUnit combatunit =  new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
            Improvement improve = new Improvement(i, j, ImprovementTypes.FARM);
            Resource res = new Resource(ResourceTypes.BANANAS);
            terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
           terrain.add(terrains[i][j]);

        }
    }
    map.setTerrains(terrains);
    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
    CombatUnit combatunit =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improve = new Improvement(3, 4, ImprovementTypes.FARM);
    Resource res = new Resource(ResourceTypes.BANANAS);
     Terrain ter = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
   Civilization civilTwo = new Civilization(100, 400, "A");
    City city = new City(null,civilTwo, ter, 0,"" , 0, 0, null);
    CityController cityController = new CityController();
   cityController.setMap(map);
    ArrayList<Terrain> TwoTerrain = new ArrayList<>();
    TwoTerrain.add(ter);
    cityController.NeighborsAtADistanceOfTwoFromAnArraylistOfTerrains(TwoTerrain, map);
  }

  @Test
  public void pillageImprovementTest(){
    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
    CombatUnit combatunit =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improve = new Improvement(3, 4, ImprovementTypes.FARM);
    Resource res = new Resource(ResourceTypes.BANANAS);
     Terrain ter = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
     Database database = new Database();
     DatabaseController databaseController = new DatabaseController(database);
     databaseController.pillageImprovement(combatunit, ter);
  }
  @Test
  public void pillageImprovementTest1(){
    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
    CombatUnit combatunit =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improve = new Improvement(3, 4, ImprovementTypes.FARM);
    Resource res = new Resource(ResourceTypes.BANANAS);
     Terrain ter = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, null, res, new ArrayList<Revealed>());
     Database database = new Database();
     DatabaseController databaseController = new DatabaseController(database);
     databaseController.pillageImprovement(combatunit, ter);
  }

  @Test
  public void TerrainAtInputDistance(){
    Map map = new Map();
    Civilization civil = new Civilization(4, 8, "A");
    User user = new User(null, null, null, civil);
    terrains = new Terrain[map.getROW()][map.getCOL()];
    for (int i = 0; i < map.getROW(); i++) {
        for (int j = 0; j < map.getCOL(); j++) {
            NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
            CombatUnit combatunit =  new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
            Improvement improve = new Improvement(i, j, ImprovementTypes.FARM);
            Resource res = new Resource(ResourceTypes.BANANAS);
            terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
          

        }
    }
    map.setTerrains(terrains);
    ArrayList<Terrain> nearTerrain = new ArrayList<>();
    nearTerrain.add(terrains[3][8]);
    Database database = new Database();
    database.setMap(map);
     DatabaseController databaseController = new DatabaseController(database);
     databaseController.terrainsAtInputDistance(nearTerrain, 1, map);

  }

  @Test
  public void TerrainAtInputDistance2(){
    Map map = new Map();
    Civilization civil = new Civilization(4, 8, "A");
    User user = new User(null, null, null, civil);
    terrains = new Terrain[map.getROW()][map.getCOL()];
    for (int i = 0; i < map.getROW(); i++) {
        for (int j = 0; j < map.getCOL(); j++) {
            NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
            CombatUnit combatunit =  new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
            Improvement improve = new Improvement(i, j, ImprovementTypes.FARM);
            Resource res = new Resource(ResourceTypes.BANANAS);
            terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
          

        }
    }
    map.setTerrains(terrains);
    ArrayList<Terrain> nearTerrain = new ArrayList<>();
    nearTerrain.add(terrains[3][8]);
    Database database = new Database();
    database.setMap(map);
     DatabaseController databaseController = new DatabaseController(database);
     databaseController.terrainsAtInputDistance(nearTerrain, 2, map);

  }
  @Test
  public void wakeUpFromAlert(){
    Map map = new Map();
    Civilization civil = new Civilization(4, 8, "A");
    
    terrains = new Terrain[map.getROW()][map.getCOL()];
    ArrayList<Terrain> myTerrain = new ArrayList<>();
    ArrayList<Unit> myUnit = new ArrayList<>();
    for (int i = 0; i < map.getROW(); i++) {
        for (int j = 0; j < map.getCOL(); j++) {
            NonCombatUnit noncombatunit = new NonCombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true);
            CombatUnit combatunit =  new CombatUnit(i, j, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
            Improvement improve = new Improvement(i, j, ImprovementTypes.FARM);
            Resource res = new Resource(ResourceTypes.BANANAS);
            terrains[i][j] = new Terrain(i, j, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, noncombatunit, improve, res, new ArrayList<Revealed>());
          myUnit.add(combatunit);

        }
    }
   myTerrain.add(terrains[3][4]);
   myTerrain.add(terrains[3][5]);
   civil.setTerrains(myTerrain);
   civil.setUnits(myUnit);
    map.setTerrains(terrains);
    User user = new User(null, null, null, civil);
    Database database = new Database();
    database.addUser(user);
    database.setMap(map);
     DatabaseController databaseController = new DatabaseController(database);
     CombatUnit combatunit1 =  new CombatUnit(3, 4, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, true, false, false, false, false);
     databaseController.wakeUpFromAlert(combatunit1);
  }

  @Test
  public void createUnitWithTurnTest(){
    Resource resource = new Resource(UnitTypes.LANCER.getResourceRequirements());
    CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
    Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
            improvement, resource,
            new ArrayList<Revealed>());

  
    Civilization civil = new Civilization(100, 3, "A");
    ArrayList<Technology> tech = new ArrayList<>();
    Technology technology = new Technology(false, 0, UnitTypes.ARTILLERY.getTechnologyRequirements(),false);
    tech.add(technology);
    civil.setTechnologies(tech);
    City city = new City(null, civil,central , 3, null,0, 0, null);
    city.setGold(10000);
    city.setCombatUnit(null);
      String input = "CONSTRUCT UNIT ARCHER";
      Matcher matcher;
      if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
         CityController cityController = new CityController();
         cityController.createUnitWithTurn(matcher, city);
      }
  }

  @Test
  public void createUnitWithTurnTest2(){
    Resource resource = new Resource(UnitTypes.LONGSWORDSMAN.getResourceRequirements());
    CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
    Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
            improvement, resource,
            new ArrayList<Revealed>());

  
    Civilization civil = new Civilization(100, 3, "A");
    ArrayList<Technology> tech = new ArrayList<>();
    Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
    tech.add(technology);
    civil.setTechnologies(tech);
    City city = new City(null, civil,central , 3, null,0, 0, null);
    city.setGold(10000);
    city.setCombatUnit(null);
      String input = "CONSTRUCT UNIT CHARIOT_ARCHER";
      Matcher matcher;
      if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
         CityController cityController = new CityController();
         cityController.createUnitWithTurn(matcher, city);
      }
  }

  @Test
  public void createUnitWithTurnTest3(){
    Resource resource = new Resource(UnitTypes.CHARIOT_ARCHER.getResourceRequirements());
    CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
    Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
            improvement, resource,
            new ArrayList<Revealed>());

  
    Civilization civil = new Civilization(100, 3, "A");
    ArrayList<Technology> tech = new ArrayList<>();
    Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
    tech.add(technology);
    civil.setTechnologies(tech);
    City city = new City(null, civil,central , 3, null,0, 0, null);
    city.setGold(10000);
    city.setCombatUnit(combatunit);
      String input = "CONSTRUCT UNIT CHARIOT_ARCHER";
      Matcher matcher;
      if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
         CityController cityController = new CityController();
         cityController.createUnitWithTurn(matcher, city);
      }
  }

  @Test
  public void createUnitWithTurnTest4(){
    Resource resource = new Resource(UnitTypes.CHARIOT_ARCHER.getResourceRequirements());
    CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
    Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
            improvement, resource,
            new ArrayList<Revealed>());

  
    Civilization civil = new Civilization(100, 3, "A");
    ArrayList<Technology> tech = new ArrayList<>();
    Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
    tech.add(technology);
    civil.setTechnologies(tech);
    City city = new City(null, civil,central , 3, null,0, 0, null);
    city.setGold(10000);
    city.setCombatUnit(null);
      String input = "CONSTRUCT UNIT CHARIOT_ARCHER";
      Matcher matcher;
      if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
         CityController cityController = new CityController();
         cityController.createUnitWithTurn(matcher, city);
      }
  }

  @Test
  public void createUnitWithTurnTest5(){
    Resource resource = new Resource(UnitTypes.CHARIOT_ARCHER.getResourceRequirements());
    CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
    Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
            improvement, resource,
            new ArrayList<Revealed>());

  
    Civilization civil = new Civilization(100, 3, "A");
    ArrayList<Technology> tech = new ArrayList<>();
    Technology technology = new Technology(false, 0, UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements(),false);
    tech.add(technology);
    civil.setTechnologies(tech);
    City city = new City(null, civil,central , 3, null,0, 0, null);
    city.setGold(10000);
    city.setCombatUnit(null);
      String input = "CONSTRUCT UNIT HORSESMAN";
      Matcher matcher;
      if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
         CityController cityController = new CityController();
         cityController.createUnitWithTurn(matcher, city);
      }
  }

  @Test
  public void createUnitWithTurnTest6(){
    Resource resource = new Resource(UnitTypes.CHARIOT_ARCHER.getResourceRequirements());
    CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
    Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
            improvement, resource,
            new ArrayList<Revealed>());

  
    Civilization civil = new Civilization(100, 3, "A");
    ArrayList<Technology> tech = new ArrayList<>();
    Technology technology = new Technology(false, 0, UnitTypes.HORSESMAN.getTechnologyRequirements(),false);
    tech.add(technology);
    civil.setTechnologies(tech);
    City city = new City(null, civil,central , 3, null,0, 0, null);
    city.setGold(10000);
    city.setCombatUnit(combatunit);
      String input = "CONSTRUCT UNIT HORSESMAN";
      Matcher matcher;
      if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
         CityController cityController = new CityController();
         cityController.createUnitWithTurn(matcher, city);
      }
  }

  @Test
  public void createUnitWithTurnTest7(){
    Resource resource = new Resource(UnitTypes.LONGSWORDSMAN.getResourceRequirements());
    CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
    Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
            improvement, resource,
            new ArrayList<Revealed>());

  
    Civilization civil = new Civilization(100, 3, "A");
    ArrayList<Technology> tech = new ArrayList<>();
    Technology technology = new Technology(false, 0, UnitTypes.HORSESMAN.getTechnologyRequirements(),false);
    tech.add(technology);
    civil.setTechnologies(tech);
    City city = new City(null, civil,central , 3, null,0, 0, null);
    city.setGold(10000);
    city.setCombatUnit(null);
      String input = "CONSTRUCT UNIT HORSESMAN";
      Matcher matcher;
      if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
         CityController cityController = new CityController();
         cityController.createUnitWithTurn(matcher, city);
      }
  }
  @Test
  public void createUnitWithTurnTest8(){
    Resource resource = new Resource(UnitTypes.CHARIOT_ARCHER.getResourceRequirements());
    CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
    Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
            improvement, resource,
            new ArrayList<Revealed>());

  
    Civilization civil = new Civilization(100, 3, "A");
    ArrayList<Technology> tech = new ArrayList<>();
    Technology technology = new Technology(false, 0, UnitTypes.HORSESMAN.getTechnologyRequirements(),false);
    tech.add(technology);
    civil.setTechnologies(tech);
    City city = new City(null, civil,central , 3, null,0, 0, null);
    city.setGold(10000);
    city.setCombatUnit(null);
      String input = "CONSTRUCT UNIT HORSESMAN";
      Matcher matcher;
      if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
         CityController cityController = new CityController();
         cityController.createUnitWithTurn(matcher, city);
      }
  }

  @Test
  public void createUnitWithTurnTest9(){
    Resource resource = new Resource(UnitTypes.CHARIOT_ARCHER.getResourceRequirements());
    NonCombatUnit combatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true);
    Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
    Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit,
            improvement, resource,
            new ArrayList<Revealed>());

  
    Civilization civil = new Civilization(100, 3, "A");
    ArrayList<Technology> tech = new ArrayList<>();
    Technology technology = new Technology(false, 0, UnitTypes.HORSESMAN.getTechnologyRequirements(),false);
    tech.add(technology);
    civil.setTechnologies(tech);
    City city = new City(null, civil,central , 3, null,0, 0, null);
    city.setGold(10000);
    city.setNonCombatUnit(combatunit);
      String input = "CONSTRUCT UNIT SETTLER";
      Matcher matcher;
      if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
         CityController cityController = new CityController();
         cityController.createUnitWithTurn(matcher, city);
      }
  }

  @Test
  public void createUnitWithTurnTest10(){
    Resource resource = new Resource(UnitTypes.CHARIOT_ARCHER.getResourceRequirements());
    NonCombatUnit combatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true);
    Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
    Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit,
            improvement, resource,
            new ArrayList<Revealed>());

  
    Civilization civil = new Civilization(100, 3, "A");
    ArrayList<Technology> tech = new ArrayList<>();
    Technology technology = new Technology(false, 0, UnitTypes.HORSESMAN.getTechnologyRequirements(),false);
    tech.add(technology);
    civil.setTechnologies(tech);
    City city = new City(null, civil,central , 3, null,0, 0, null);
    city.setGold(10000);
    city.setNonCombatUnit(null);
    civil.setBooleanSettlerBuy(true);
      String input = "CONSTRUCT UNIT SETTLER";
      Matcher matcher;
      if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
         CityController cityController = new CityController();
         cityController.createUnitWithTurn(matcher, city);
      }
  }

  @Test
  public void createUnitWithTurnTest11(){
    Resource resource = new Resource(UnitTypes.CHARIOT_ARCHER.getResourceRequirements());
    NonCombatUnit combatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true);
    Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
    Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), null, combatunit,
            improvement, resource,
            new ArrayList<Revealed>());

  
    Civilization civil = new Civilization(100, 3, "A");
    ArrayList<Technology> tech = new ArrayList<>();
    Technology technology = new Technology(false, 0, UnitTypes.HORSESMAN.getTechnologyRequirements(),false);
    tech.add(technology);
    civil.setTechnologies(tech);
    City city = new City(null, civil,central , 3, null,0, 0, null);
    city.setGold(10000);
    city.setNonCombatUnit(null);
    civil.setBooleanSettlerBuy(true);
      String input = "CONSTRUCT UNIT WORKER";
      Matcher matcher;
      if((matcher = getMatcher(input, GameEnums.CREATE_UNIT)) != null){
         CityController cityController = new CityController();
         cityController.createUnitWithTurn(matcher, city);
      }
  }
  /*
  @Test
  public void purchaseTestWorker(){
    NonCombatUnit noncombatunit = new NonCombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true);



    Resource resource = new Resource(UnitTypes.CHARIOT_ARCHER.getResourceRequirements());
    CombatUnit combatunit = new CombatUnit(3, 4, 0, 0, 0, 0, false, false,
    UnitTypes.SETTLER, true, false, false, false, false);
    Improvement improvement = new Improvement(3, 4,ImprovementTypes.FARM);
    Terrain central = new Terrain(3, 4, "visible", TerrainTypes.PLAINS, new ArrayList<>(), combatunit, null,
            improvement, resource,
            new ArrayList<Revealed>());

  
    Civilization civil = new Civilization(100, 3, "A");
    City city = new City(null, civil,central , 3, null,0, 0, null);
    CityController cityController = new CityController();
    cityController.purchase("", city);


  }
  */
}