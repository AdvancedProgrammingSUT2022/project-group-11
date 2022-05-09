
import java.util.Scanner;

import Controllers.DatabaseController;

import Model.Database;
import View.LoginMenu;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Database database = new Database();
    DatabaseController databaseController = new DatabaseController(database);
    LoginMenu loginMenu = new LoginMenu(databaseController);
    loginMenu.run(scanner);
  }
}

/*
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
 */