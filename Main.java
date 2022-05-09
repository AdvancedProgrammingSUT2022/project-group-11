
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
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT IMPROVEMENT FARM 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }

        input = "SET CHEAT IMPROVEMENT MINE 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT IMPROVEMENT PLANTATION 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT IMPROVEMENT PASTURE 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT IMPROVEMENT LUMBERBILL 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT IMPROVEMENT MANUFACTURY 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT IMPROVEMENT QUARRY 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT IMPROVEMENT RAILROAD 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT IMPROVEMENT ROAD 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        input = "SET CHEAT IMPROVEMENT TRADINGPOST 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
            gamemenu.setCheatResource(matcher);
        }
        

    }
 */