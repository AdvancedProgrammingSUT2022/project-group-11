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
}