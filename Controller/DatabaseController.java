package Controller;

import Model.Database;
import Model.Map;
import Model.Terrain;
import Model.User;
import Model.Terrains.TerrainTypes;
import Model.Units.CombatUnit;
import Model.Units.NonCombatUnit;
import Model.Units.RangedCombatUnit;
import Model.Units.Unit;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class DatabaseController {
    private Database database;

    public DatabaseController(Database database) {
        this.database = database;
    }

    public void addUser(User user) {
        this.database.addUser(user);
    }

    public Database getDatabase() {
        return this.database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Map getMap() {

        return this.database.getMap();
    }

    public void createUser(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String nickname = matcher.group("nickname");

        ArrayList<User> users = this.database.getUsers();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("user with username " + username + " already exists");
                return;
            }
            if (user.getNickname().equals(nickname)) {
                System.out.println("user with nickname " + nickname + " already exists");
                return;
            }
        }

        User newUser = new User(username, password, nickname);
        this.database.addUser(newUser);
        System.out.println("user created successfully!");
        return;
    }

    public User userLogin(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        User user = this.database.getUserByUsernameAndPassword(username, password);
        if (user != null) {
            System.out.println("user logged in successfully!");
            return user;
        }
        System.out.println("Username and password didn't match!");
        return null;
    }

    public User getUserByUsername(String username) {
        return this.database.getUserByUsername(username);
    }

    public String selectAndDeslectCombatUnit(User user, int x, int y) {
        if (user.getCivilization().containsUnit((Unit) this.database.getMap().getTiles()[x][y].getCombatUnit())) {
            boolean initialIsSelectedValue = this.database.getMap().getTiles()[x][y].getCombatUnit().getIsSelected();
            this.database.getMap().getTiles()[x][y].getCombatUnit().setIsSelected(!initialIsSelectedValue);
            return "Combat unit was selected";
        }
        return "you do not have access to this unit";
    }

    public String selectAndDeslectNonCombatUnit(User user, int x, int y) {
        if (user.getCivilization().containsUnit((Unit) this.database.getMap().getTiles()[x][y].getNonCombatUnit())) {
            boolean initialIsSelectedValue = this.database.getMap().getTiles()[x][y].getNonCombatUnit().getIsSelected();
            this.database.getMap().getTiles()[x][y].getNonCombatUnit().setIsSelected(!initialIsSelectedValue);
            return "Noncombat unit was selected";
        }
        return "you do not have access to this unit";
    }

    public String changingTheStateOfAUnit(String action) {
        CombatUnit combatUnit = getSelectedCombatUnit();
        NonCombatUnit nonCombatUnit = getSelectedNonCombatUnit();

        if (combatUnit != null) {

            if (action.equals("sleep")) {
                combatUnit.setIsAsleep(true);
                combatUnit.setIsSelected(false);
            } else if (action.equals("alert")) {
                combatUnit.setAlert(true);
            } else if (action.equals("fortify")) {
                combatUnit.setFortify(true);
            } else if (action.equals("fortify until heal")) {
                combatUnit.setFortifyUntilHeal(true);
            } else if (action.equals("garrison")) {
                combatUnit.setIsGarrisoned(true);
            } else if (action.equals("wake")) {
                combatUnit.setIsAsleep(false);
            } else if (action.equals("delete")) {
                combatUnit = null;
            } else if (action.equals("setup ranged")) {
                if (combatUnit instanceof RangedCombatUnit) {
                    RangedCombatUnit rangedCombatUnit = (RangedCombatUnit) combatUnit;
                    rangedCombatUnit.setIsSetUpForRangedAttack(true);
                } else {
                    return "this unit is not a ranged combat unit!";
                }
            }

        } else if (nonCombatUnit != null) {

            if (action.equals("sleep")) {
                nonCombatUnit.setIsAsleep(true);
            } else if (action.equals("wake")) {
                nonCombatUnit.setIsAsleep(false);
            } else if (action.equals("delete")) {
                nonCombatUnit = null;
            }

        }

        return "action completed";

    }

    public boolean HasoneUnitBeenSelected() {
        boolean isSelected = false;
        int row = this.database.getMap().getROW();
        int column = this.database.getMap().getCOL();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (this.database.getMap().getTiles()[i][j].getCombatUnit().isIsSelected() == true
                        || this.database.getMap().getTiles()[i][j].getNonCombatUnit().isIsSelected() == true) {
                    isSelected = true;
                    break;
                }
            }
        }
        return isSelected;
    }

    public CombatUnit getSelectedCombatUnit() {
        int row = this.database.getMap().getROW();
        int column = this.database.getMap().getCOL();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (this.database.getMap().getTiles()[i][j].getCombatUnit().isIsSelected() == true) {
                    return this.database.getMap().getTiles()[i][j].getCombatUnit();
                }
            }
        }
        return null;
    }

    public NonCombatUnit getSelectedNonCombatUnit() {
        int row = this.database.getMap().getROW();
        int column = this.database.getMap().getCOL();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (this.database.getMap().getTiles()[i][j].getNonCombatUnit().isIsSelected() == true) {
                    return this.database.getMap().getTiles()[i][j].getNonCombatUnit();
                }
            }
        }
        return null;
    }

    public boolean isAllTasksFinished(User user) {
        for (Unit unit : user.getCivilization().getUnits()) {
            if (unit.getIsFinished() == false) {
                return false;
            }
        }
        return true;
    }

    public void addingAllPath(int turn, int x_beginning, int y_beginning, int x_final, int y_final,
            Map map, ArrayList<Terrain> path, ArrayList<ArrayList<Terrain>> allPaths) {
        Terrain[][] copy_map = map.getTiles();
        if (turn == 8 && (x_beginning == x_final && y_beginning == y_final)) {
            allPaths.add(path);

        } else if (turn == 8) {
            return;
        } else {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == j || x_beginning + i < 0 || x_beginning + i >= map.getROW() || y_beginning + j < 0
                            || y_beginning + j >= map.getCOL()) {
                        continue;
                    } else {
                        ArrayList<Terrain> path_copy = new ArrayList<Terrain>();
                        for (Terrain terrain : path) {
                            path_copy.add(terrain);
                        }
                        path_copy.add(copy_map[x_beginning + i][y_beginning + j]);
                        addingAllPath(
                                turn + 1, x_beginning + i, y_beginning + j, x_final, y_final, map, path_copy, allPaths);
                    }

                }
            }
        }
    }

    public ArrayList<Terrain> findingTheShortestPath(ArrayList<ArrayList<Terrain>> allPaths)
    {
        int movementCostOfTheShortestPath = 999999;
        ArrayList<Terrain> shortestPath = new ArrayList<>();
        for(ArrayList<Terrain> path : allPaths)
        {
            if(calculatingTheMovementCost(path) < movementCostOfTheShortestPath)
            {
                movementCostOfTheShortestPath = calculatingTheMovementCost(path);
                shortestPath = path;
            }
        }
        return shortestPath;
    }

    public int calculatingTheMovementCost(ArrayList<Terrain> path)
    {
        int movementCost = 0;
        for(Terrain terrain : path)
        {
            movementCost += terrain.getTerrainTypes().getMovementCost();
        }
        return movementCost;
    }
}
