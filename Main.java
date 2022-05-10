
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
 */