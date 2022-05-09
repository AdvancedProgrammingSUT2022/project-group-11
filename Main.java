
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
            gamemenu.setCheatTerrainType(matcher);
        }
        input = "SET CHEAT TERRAIN FEATURE TYPE FOREST 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }

        input = "SET CHEAT TERRAIN FEATURE TYPE ICE 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }
        input = "SET CHEAT TERRAIN FEATURE TYPE JUNGLE 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }
        input = "SET CHEAT TERRAIN FEATURE TYPE MARSH 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }
        input = "SET CHEAT TERRAIN FEATURE TYPE OASIS 4 3";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
            gamemenu.setCheatTerrainType(matcher);
        }


    }


    }
 */