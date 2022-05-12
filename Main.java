import Controllers.DatabaseController;
import Model.Database;
import View.LoginMenu;

import java.util.Scanner;

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
        input = "NONCOMBAT UNIT CHEAT MOVE 5 6";
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

        input = "UNIT REMOVE";
        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_REMOVE)) != null) {
            gamemenu.oneUnitHasBeenSelected(input, matcher, user);
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
 */