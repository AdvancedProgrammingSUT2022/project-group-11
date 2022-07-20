package com.example.civilization.Controllers;

import com.example.civilization.FXMLcontrollers.GameMapController;
import com.example.civilization.Model.Buildings.Building;
import com.example.civilization.Model.City.City;
import com.example.civilization.Model.*;
import com.example.civilization.Model.Improvements.Improvement;
import com.example.civilization.Model.Improvements.ImprovementTypes;
import com.example.civilization.Model.Map;
import com.example.civilization.Model.Resources.Resource;
import com.example.civilization.Model.Resources.ResourceTypes;
import com.example.civilization.Model.Technologies.Technology;
import com.example.civilization.Model.Technologies.TechnologyTypes;
import com.example.civilization.Model.TerrainFeatures.TerrainFeatureTypes;
import com.example.civilization.Model.Terrains.TerrainTypes;
import com.example.civilization.Model.Units.*;

import java.util.*;
import com.example.civilization.Requests.RequestUser;
import com.example.civilization.Response.ResponseUser;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static java.util.Comparator.naturalOrder;

public class DatabaseController {
    private static DatabaseController instance;
    public HashMap<User, String> notificationHistory = new HashMap<>();
    private Database database;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;


    public DataInputStream getDataInputStream(){
        return dataInputStream;
    }
    public DataOutputStream getDataOutputStream(){
        return dataOutputStream;
    }
    public DatabaseController() {
        this.database = Database.getInstance();
        try {
            this.socket = new Socket("localhost", 7777);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }catch(IOException E){
            E.printStackTrace();
        }

    }

    public static DatabaseController getInstance() {
        if (instance == null) {
            instance = new DatabaseController();
        }
        return instance;
    }

    public Database getDatabase() {
        return this.database;
    }

    public Map getMap() {

        return this.database.getMap();
    }



    public double getYear(){
        double year = 0 ;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("getYear",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            year = Integer.parseInt(gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getIJ());
        }catch(IOException E){
            E.printStackTrace();
        }
        return year;
    }

    public String notificationhistoryRequest(){
        String result = null;
        try{
            RequestUser requestUser = new RequestUser();
            Gson gson = new Gson();
            requestUser.addRequest("notificationHistory",null);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            result = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getAction();
        }catch(IOException E){
            E.printStackTrace();
        }
        return result;
    }
    public void addUserToPlayerUser(User user){
        Gson gson = new Gson();
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("addUserToPlayerUsers",user);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch(IOException E){
            E.printStackTrace();
        }
    }

    public void removeUserToPlayerUser(User user){
        Gson gson = new Gson();
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("removeUserToPlayerUsers",user);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch(IOException E){
            E.printStackTrace();
        }
    }

    public ArrayList<User> getAllPlayerUser(){
        ArrayList<User> users = new ArrayList<>();
        Gson gson = new Gson();
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("getAllPlayerUser",null);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            users = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getUsers();
        }catch(IOException E){
            E.printStackTrace();
        }
        return users;
    }
    public void addTechnology(TechnologyTypes technologyTypes){
        Gson gson = new Gson();
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("addNewTechnology",null);
            requestUser.setTechnologyTypes(technologyTypes);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch(IOException E){
            E.printStackTrace();
        }
    }

    public TechnologyTypes getUnlockTechnologyType(){
        TechnologyTypes technologyTypes = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("lastUnlockTechnology",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            technologyTypes = responseUser.getTechnologyTypes();

        }catch(IOException E){
            E.printStackTrace();
        }
        return technologyTypes;
    }

    public Terrain getTerrain(int i,int j){
        String IJ = i + " " + j;
        Terrain terrain = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.setIJ(IJ);
            requestUser.addRequest("findTerrain",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            terrain = responseUser.getTerrain();
        }catch(IOException E){
            E.printStackTrace();
        }
        return terrain;
    }

    public Map getMapFromServer(){
        Map map;
        RequestUser requestUser = new RequestUser();
        requestUser.addRequest("getMap",null);
        Gson gson = new Gson();

        try {
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            map = responseUser.getMap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return map;
    }


    public String createUser(String u, String p, String n) {
        String result = "user created successfully!";
        try {
            User user = new User(u, p, n, null);
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("createUser", user);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String userResponse = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(userResponse,ResponseUser.class);
            result = responseUser.getAction();

        }catch(IOException E){
            E.printStackTrace();
        }

        return result;
    }

    public User userLogin(String u, String p)  {
        User user = null;
        try {
           user = new User(u, p, null, null);
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("getUserByUsernameAndPassword",user);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String userResponse = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(userResponse, ResponseUser.class);
            user = responseUser.getUser();
        }catch(IOException E){
            E.printStackTrace();
        }
        return user;
    }
    public User activeUser(){
        User user = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("activeUser",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String userString = dataInputStream.readUTF();
            ResponseUser responseUser  = gson.fromJson(userString,ResponseUser.class);
            user = responseUser.getUser();
        }catch (IOException E){
            E.printStackTrace();
        }
        return user;
    }

    public String changeUserNickname(String n, User player) {
        String result = "nickname changed successfully!";
        try {

            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("changeUserNickname", player);
            requestUser.setNickname(n);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            result = responseUser.getAction();
        }catch(IOException E){
            E.printStackTrace();
        }

        return result;
    }

    public String changePassword(String p, User user) {
        String result = "password changed successfully! Please Login again with your new password";
        try {
            RequestUser requestUser = new RequestUser();
            Gson gson = new Gson();
            requestUser.addRequest("changePassword",user);
            requestUser.setPassword(p);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            result = responseUser.getAction();
        }catch (IOException E){
            E.printStackTrace();
        }
        return result;
    }


    public Map generateMapFromServer(User user){
        Map map = null;
       try{
           RequestUser requestUser = new RequestUser();
           requestUser.addRequest("generateMap",null);
           Gson gson = new Gson();
           dataOutputStream.writeUTF(gson.toJson(requestUser));
           dataOutputStream.flush();
           String res = dataInputStream.readUTF();
           ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
           map = responseUser.getMap();
       }catch(IOException E){
           E.printStackTrace();
       }
       return map;
    }

    public void clearPlayerUser(){
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("clear",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch (IOException E){
            E.printStackTrace();
        }
    }
    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("getAllUser",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();

            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            users = responseUser.getUsers();


        }catch(IOException E){
            E.printStackTrace();
        }
        return users;
    }

    public String selectAndDeselectCombatUnit(User user, int x, int y) {
        Map map = this.getMap();
        int mapRows = map.getROW();
        int mapColumns = map.getCOL();
        if (x > mapRows || x < 0 || y > mapColumns || y < 0) {
            return "there is no tile with these coordinates";
        }
        if (this.database.getMap().getTerrain()[x][y].getCombatUnit() != null && this.database.getMap().getTerrain()[x][y].getCombatUnit().getIsFinished()) {
            return "you have selected this unit before";
        }
        if (user.getCivilization().containsUnit(this.database.getMap().getTerrain()[x][y].getCombatUnit())) {
            boolean initialIsSelectedValue = this.database.getMap().getTerrain()[x][y].getCombatUnit().getIsSelected();
            this.database.getMap().getTerrain()[x][y].getCombatUnit().setIsSelected(!initialIsSelectedValue);

            return "Combat unit was selected";
        }
        return "you do not have access to this unit";
    }

    public void setIsCombatUnitSelected(int i,int j){
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("isSelectedCombatUnit",null);
            requestUser.setIJ( i + " " + j);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch (IOException E){
            E.printStackTrace();
        }
    }
    public void setIsNonCombatUnitSelected(int i,int j){
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("isSelectedNonCombatUnit",null);
            requestUser.setIJ( i + " " + j);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch (IOException E){
            E.printStackTrace();
        }
    }


    public void deselectAllUnits() {
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("deselectAllUnits",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();

        }catch (IOException E){
            E.printStackTrace();
        }

    }

    public String selectAndDeselectNonCombatUnit(User user, int x, int y) {
        Map map = this.getMap();
        int mapRows = map.getROW();
        int mapColumns = map.getCOL();
        if (x > mapRows || x < 0 || y > mapColumns || y < 0) {
            return "there is no tile with these coordinates";
        }
        if (this.database.getMap().getTerrain()[x][y].getNonCombatUnit() != null && this.database.getMap().getTerrain()[x][y].getNonCombatUnit().getIsFinished()) {
            return "you have selected this unit before";
        }
        if (user.getCivilization().containsUnit(this.database.getMap().getTerrain()[x][y].getNonCombatUnit())) {
            boolean initialIsSelectedValue = this.database.getMap().getTerrain()[x][y].getNonCombatUnit().getIsSelected();
            this.database.getMap().getTerrain()[x][y].getNonCombatUnit().setIsSelected(!initialIsSelectedValue);

            return "Noncombat unit was selected";
        }
        return "you do not have access to this unit";
    }

    public String changingTheStateOfACombatUnit(CombatUnit combatUnit, String action) {
        setAllParametersFalse(combatUnit);
        switch (action) {
            case "sleep":
                combatUnit.setIsAsleep(true);
                break;
            case "alert":
                combatUnit.setAlert(true);
                break;
            case "fortify":
                combatUnit.setFortify(true);
                break;
            case "fortify until heal":
                combatUnit.setFortifyUntilHeal(true);
                break;
            case "garrison":
                combatUnit.setIsGarrisoned(true);
                break;
            case "wake":
                combatUnit.setIsAsleep(false);
                break;
            case "delete":
                combatUnit = null;
                break;
            case "setup ranged":
                if (combatUnit.getUnitType().getCombatTypes().equals(CombatTypes.SIEGE)) {
                    ((RangedCombatUnit) combatUnit).setIsSetUpForRangedAttack(true);
                } else {
                    return "This unit doesn't need to be set up";
                }
                break;
        }
        if (!action.equals("delete")) {
            combatUnit.getNextTerrain().clear();
            combatUnit.setIsFinished(true);
            combatUnit.setIsSelected(false);
        }

        return "Unit will " + action;
    }

    public String changingTheStateOfANonCombatUnit(NonCombatUnit nonCombatUnit, String action) {
        setAllParametersFalse(nonCombatUnit);
        switch (action) {
            case "sleep" -> nonCombatUnit.setIsAsleep(true);
            case "wake" -> nonCombatUnit.setIsAsleep(false);
            case "delete" -> nonCombatUnit = null;
        }
        if (!action.equals("delete")) {
            nonCombatUnit.setIsFinished(true);
            nonCombatUnit.setIsSelected(false);
            nonCombatUnit.getNextTerrain().clear();
        }
        return "Unit will " + action;

    }

    public void setAllParametersFalse(Unit unit) {
        unit.setIsAsleep(false);
        if (unit instanceof CombatUnit) {
            ((CombatUnit) unit).setAlert(false);
            ((CombatUnit) unit).setFortify(false);
            ((CombatUnit) unit).setFortifyUntilHeal(false);
            ((CombatUnit) unit).setIsGarrisoned(false);

        }
        if (unit instanceof RangedCombatUnit) {
            ((RangedCombatUnit) unit).setIsSetUpForRangedAttack(false);
        }
    }

    public String changingTheStateOfAUnit(String action) {
        CombatUnit combatUnit = getSelectedCombatUnit();
        NonCombatUnit nonCombatUnit = getSelectedNonCombatUnit();

        if (combatUnit != null) {
            return changingTheStateOfACombatUnit(combatUnit, action);
        } else {
            return changingTheStateOfANonCombatUnit(nonCombatUnit, action);
        }

    }

    public boolean HasOneUnitBeenSelected() {
        boolean isSelected = false;
        int row = this.database.getMap().getROW();
        int column = this.database.getMap().getCOL();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if ((this.database.getMap().getTerrain()[i][j].getCombatUnit() != null && this.database.getMap().getTerrain()[i][j].getCombatUnit().isIsSelected()) || (this.database.getMap().getTerrain()[i][j].getNonCombatUnit() != null && this.database.getMap().getTerrain()[i][j].getNonCombatUnit().isIsSelected())) {
                    isSelected = true;
                    break;
                }
            }
        }
        return isSelected;
    }

    public CombatUnit getSelectedCombatUnit() {
        CombatUnit combatUnit = null;
        try{
            Gson gson = new Gson();
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("getSelectedCombatUnit",null);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            combatUnit = responseUser.getCombatUnit();
        }catch(IOException E){
            E.printStackTrace();
        }

        return combatUnit;
    }

    public NonCombatUnit getSelectedNonCombatUnit() {
        NonCombatUnit nonCombatUnit = null;
        try{
            Gson gson = new Gson();
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("getSelectedNonCombatUnit",null);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            nonCombatUnit = responseUser.getNonCombatUnit();

        }catch(IOException E){
            E.printStackTrace();
        }

        return nonCombatUnit;
    }

    public boolean isAllTasksFinished(User user) {
        for (Unit unit : user.getCivilization().getUnits()) {
            if (!unit.getIsFinished()) {
                return false;
            }
        }
        return true;
    }

    public void setActiveUser(){
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("setActiveUser",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch(IOException E){
            E.printStackTrace();
        }
    }
    public void setAllUnitsUnfinished(User user) {
            try{
                Gson gson = new Gson();
                RequestUser requestUser = new RequestUser();
                requestUser.addRequest("setAllUnitsUnfinished",user);
                dataOutputStream.writeUTF(gson.toJson(requestUser));
                dataOutputStream.flush();
            }catch(IOException E){
                E.printStackTrace();
            }
    }

    public String unitMovement(int x_final, int y_final, User user) {
        String result = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("unitMovement",user);
            requestUser.setIJ(x_final + " " + y_final);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            result = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getAction();

        }catch(IOException E){
            E.printStackTrace();
        }
        return result;
    }

    public String combatUnitMovement(CombatUnit combatUnit, int x_final, int y_final, User user, Map map) {
        if (user.getCivilization().containsCombatUnit(x_final, y_final)) {
            return "you have another combat unit in this tile";
        }
        if (combatUnit.getNextTerrain() != null) {
            combatUnit.getNextTerrain().clear();
        }
        ArrayList<Terrain> path = new ArrayList<>();
        ArrayList<ArrayList<Terrain>> allPaths = new ArrayList<>();
        addingAllPath(0, combatUnit.getX(), combatUnit.getY(), x_final, y_final, map, path, allPaths);
        combatUnit.setNextTerrain(findingTheShortestPath(combatUnit, allPaths));
        if (combatUnit.getNextTerrain().isEmpty()) {
            return "You're unable to go to your destination";
        }
        combatUnit.setIsSelected(false);
        combatUnit.setIsFinished(true);
        return "action completed";
    }

    public String nonCombatUnitMovement(NonCombatUnit nonCombatUnit, int x_final, int y_final, User user, Map map) {
        if (user.getCivilization().containsNonCombatUnit(x_final, y_final)) {
            return "you have another non combat unit in this tile";
        }
        if (nonCombatUnit.getNextTerrain() != null) {
            nonCombatUnit.getNextTerrain().clear();
        }
        ArrayList<Terrain> path = new ArrayList<>();
        ArrayList<ArrayList<Terrain>> allPaths = new ArrayList<>();
        addingAllPath(0, nonCombatUnit.getX(), nonCombatUnit.getY(), x_final, y_final, map, path, allPaths);
        nonCombatUnit.setNextTerrain(findingTheShortestPath(nonCombatUnit, allPaths));
        if (nonCombatUnit.getNextTerrain().isEmpty()) {
            return "You're unable to go to your destination.";
        }
        nonCombatUnit.setIsSelected(false);
        nonCombatUnit.setIsFinished(true);
        return "action completed";
    }

    public void movementOfAllUnits() {
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("movementOfAllUnits",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch(IOException E){
            E.printStackTrace();
        }


    }

    public void movementAsLongAsItHasMP(Unit unit) {
        int indexOfLastTerrain;
        int movementCost = 0;
        if (unit.getNextTerrain() == null) {
            return;
        }

        for (indexOfLastTerrain = 0; indexOfLastTerrain < unit.getNextTerrain().size(); indexOfLastTerrain++) {
            Terrain terrain = findingTheContainerTerrain(unit);
            if (unit instanceof CombatUnit) {
                terrain.setCombatUnit(null);
                unit.getNextTerrain().get(indexOfLastTerrain).setCombatUnit((CombatUnit) unit);
            } else if (unit instanceof NonCombatUnit) {
                terrain.setNonCombatUnit(null);
                unit.getNextTerrain().get(indexOfLastTerrain).setNonCombatUnit((NonCombatUnit) unit);
            }

            unit.setXAndY(unit.getNextTerrain().get(indexOfLastTerrain).getX(), unit.getNextTerrain().get(indexOfLastTerrain).getY());
            if (unit.getNextTerrain().get(indexOfLastTerrain).isRuin()) {
                Ruins ruins = new Ruins(unit.getNextTerrain().get(indexOfLastTerrain).getX(), unit.getNextTerrain().get(indexOfLastTerrain).getY(), getContainerCivilization(unit), getMap());
                GameMapController.showingRuinsPopUp(ruins);
                unit.getNextTerrain().get(indexOfLastTerrain).setRuin(false);
            }
            movementCost += unit.getNextTerrain().get(indexOfLastTerrain).getTerrainTypes().getMovementCost();
            if (movementCost > unit.getUnitType().getMovement()) {
                break;
            }
            if (containEnemyInNearTerrains(unit)) {
                break;
            }

        }

//        ArrayList<Terrain> needToRemove = new ArrayList<>();
//        for (int i = 0; i < indexOfLastTerrain; i++) {
//            needToRemove.add(unit.getNextTerrain().get(i));
//        }
        unit.getNextTerrain().clear();

    }

    public boolean containEnemyInNearTerrains(Unit unit) {
        for (Terrain terrain : getNeighborTerrainsOfOneTerrain(getTerrainByCoordinates(unit.getX(), unit.getY()), getMap())) {
            if (terrain.getCombatUnit() != null && !getContainerCivilization(terrain.getCombatUnit()).equals(getContainerCivilization(unit))) {
                return true;
            }
        }
        return false;
    }

    public Terrain findingTheContainerTerrain(Unit unit) {
        Map map = this.getMap();
        int mapRows = map.getROW();
        int mapColumns = map.getCOL();
        for (int i = 0; i < mapRows; i++) {
            for (int j = 0; j < mapColumns; j++) {
                if (map.getTerrain()[i][j].containsUnit(unit)) {
                    return map.getTerrain()[i][j];
                }
            }
        }
        return null;
    }

    public void addingAllPath(int turn, int x_beginning, int y_beginning, int x_final, int y_final, Map map, ArrayList<Terrain> path, ArrayList<ArrayList<Terrain>> allPaths) {
        Terrain[][] copy_map = map.getTerrain();
        if ((x_beginning == x_final && y_beginning == y_final)) {
            allPaths.add(path);
            return;
        }
        if ((turn >= 6 && turn <= 10) && allPaths.size() > 0) {
            return;
        }
        if (turn == 10) {
            return;
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x_beginning + i < 0 || x_beginning + i >= map.getROW() || y_beginning + j < 0 || y_beginning + j >= map.getCOL() || y_beginning % 2 == 0 && ((i == 0 && j == 0) || (i == 1 && j == -1) || (i == 1 && j == 1)) || y_beginning % 2 == 1 && ((i == 0 && j == 0) || (i == -1 && j == 1) || (i == -1 && j == -1))) {

                } else {
                    ArrayList<Terrain> path_copy = new ArrayList<>(path);
                    path_copy.add(copy_map[x_beginning + i][y_beginning + j]);
                    if (map.hasRiver(copy_map[x_beginning][y_beginning], copy_map[x_beginning + i][y_beginning + j]) != null) {
                        continue;
                    }
                    addingAllPath(turn + 1, x_beginning + i, y_beginning + j, x_final, y_final, map, path_copy, allPaths);
                }

            }
        }

    }

    public ArrayList<Terrain> findingTheShortestPath(Unit unit, ArrayList<ArrayList<Terrain>> allPaths) {
        int movementCostOfTheShortestPath = 9999999;
        ArrayList<Terrain> shortestPath = new ArrayList<>();
        if (unit.getUnitType().equals(UnitTypes.SCOUT)) {
            for (ArrayList<Terrain> path : allPaths) {
                if (calculatingTheMovementCostForScout(path) < movementCostOfTheShortestPath) {
                    movementCostOfTheShortestPath = calculatingTheMovementCostForScout(path);
                    shortestPath = path;
                }
            }
        } else {
            for (ArrayList<Terrain> path : allPaths) {
                if (calculatingTheMovementCost(path) < movementCostOfTheShortestPath) {
                    movementCostOfTheShortestPath = calculatingTheMovementCost(path);
                    shortestPath = path;
                }
            }
        }

        for (Terrain terrain : shortestPath) {
            System.out.println(terrain.getX() + " " + terrain.getY());
        }
        return shortestPath;
    }

    public int calculatingTheMovementCost(ArrayList<Terrain> path) {
        int movementCost = 0;
        for (Terrain terrain : path) {
            if (terrain.getTerrainImprovement() != null && (terrain.getTerrainImprovement().getImprovementType().equals(ImprovementTypes.ROAD) || terrain.getTerrainImprovement().getImprovementType().equals(ImprovementTypes.RAILROAD))) {

                movementCost += 0.5 * terrain.getTerrainTypes().getMovementCost();
                if (terrain.getTerrainFeatureTypes() != null && terrain.getTerrainFeatureTypes().size() > 0) {
                    movementCost += 0.5 * terrain.getTerrainFeatureTypes().get(0).getMovementCost();
                }
            } else {

                movementCost += terrain.getTerrainTypes().getMovementCost();
                if (terrain.getTerrainFeatureTypes() != null && terrain.getTerrainFeatureTypes().size() > 0) {
                    movementCost += terrain.getTerrainFeatureTypes().get(0).getMovementCost();
                }
            }

        }
        return movementCost;
    }

    public int calculatingTheMovementCostForScout(ArrayList<Terrain> path) {
        int movementCost = 0;
        for (Terrain terrain : path) {

            movementCost += 1;

        }
        return movementCost;
    }

    public void changingUnitsParameters(User user) {
        for (Unit unit : user.getCivilization().getUnits()) {
            if (unit instanceof CombatUnit) {
                changingCombatUnitsParameters((CombatUnit) unit);
            } else {
                changingNonCombatUnitParameters((NonCombatUnit) unit);
            }
        }
    }

    public void changingCombatUnitsParameters(CombatUnit combatUnit) {
        if (combatUnit.getIsAsleep()) {

        } else if (combatUnit.getAlert()) {
            wakeUpFromAlert(combatUnit);

        } else if (combatUnit.getIsGarrisoned()) {

        } else if (combatUnit.getFortify()) {
            combatUnit.setCombatStrength(combatUnit.getCombatStrength() + 1);
        } else if (combatUnit.getFortifyUntilHeal()) {
            combatUnit.setHP(combatUnit.getHP() + 1);

        }
    }

    public void changingNonCombatUnitParameters(NonCombatUnit nonCombatUnit) {
        if (nonCombatUnit.getIsAsleep()) {

        }
    }

    public Technology getUnderResearchTechnology(User user) {
        for (Technology technology : user.getCivilization().getTechnologies()) {
            if (technology.getUnderResearch()) {
                return technology;
            }
        }
        return null;
    }

    public Terrain getTerrainByCoordinates(int x, int y) {
        return this.database.getMap().getTerrain()[x][y];
    }

    public void setTerrainsOfEachCivilization() {

        try{
               RequestUser requestUser = new RequestUser();
               requestUser.addRequest("setTerrainsOfEachCivilization",null);
               Gson gson = new Gson();
               dataOutputStream.writeUTF(gson.toJson(requestUser));
               dataOutputStream.flush();
        }catch (IOException E){
            E.printStackTrace();
        }

    }

    public void setCivilizations() {
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("setCivilizations",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch(IOException E){
            E.printStackTrace();
        }

    }

    public ArrayList<Integer> setIndices(ArrayList<User> users) {
        Random rand = new Random();
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            int nextIndex = rand.nextInt(10);
            while (isContainInteger(indices, nextIndex)) {
                nextIndex = rand.nextInt(10);
            }

            indices.add(nextIndex);
        }

        return indices;

    }

    public boolean isContainInteger(ArrayList<Integer> indices, int random) {
        for (Integer integer : indices) {
            if (integer == random) {

                return true;
            }
        }
        return false;
    }


    public void createUnitForEachCivilization(User user) {
        ArrayList<Integer> unitsCoordinates = findingEmptyTiles();
        NonCombatUnit newSettler = new NonCombatUnit(unitsCoordinates.get(0), unitsCoordinates.get(1) + 1, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, false);

        NonRangedCombatUnit newWarrior = new NonRangedCombatUnit(unitsCoordinates.get(0), unitsCoordinates.get(1), 0, 0, 0, 0, false, false, UnitTypes.WARRIOR, false, false, false, false, false);
        getMap().getTerrain()[unitsCoordinates.get(0)][unitsCoordinates.get(1)].setCombatUnit(newWarrior);
        getMap().getTerrain()[unitsCoordinates.get(0)][unitsCoordinates.get(1) + 1].setNonCombatUnit(newSettler);
        //user.getCivilization().addCity(new City(user.getCivilization(),user.getCivilization(),getTerrainByCoordinates(10,12),10,null,10,10));
        //user.getCivilization().addCity(new City(user.getCivilization(),user.getCivilization(),getTerrainByCoordinates(10,12),10,null,10,10));


        user.getCivilization().getUnits().add(newSettler);
        user.getCivilization().getUnits().add(newWarrior);

    }

    public ArrayList<Integer> findingEmptyTiles() {
        Random rand = new Random();
        ArrayList<Integer> coordinates = new ArrayList<>();
        int x = rand.nextInt(5, 25);
        int y = rand.nextInt(5, 13);
        while (!isTerrainEmpty(x, y)) {
            x = rand.nextInt(5, 25);
            y = rand.nextInt(5, 13);
        }
        coordinates.add(x);
        coordinates.add(y);
        return coordinates;

    }

    public boolean isTerrainEmpty(int x, int y) {

        return this.getMap().getTerrain()[x][y].getCombatUnit() == null && this.getMap().getTerrain()[x][y + 1].getNonCombatUnit() == null && !this.getMap().getTerrain()[x][y].isRuin() && !this.getMap().getTerrain()[x][y + 1].isRuin();
    }

    public void addGoldToUser(User user) {
        /*
        for (Terrain allTerrains : user.getCivilization().getOwnedTerrains()) {
            int gold = allTerrains.getCity().getGold();
            if (allTerrains.getTerrainTypes() != null) {
                gold += allTerrains.getTerrainTypes().getGold();
            }
            if (allTerrains.getTerrainFeatureTypes() != null) {
                for (TerrainFeatureTypes allTerrainFeature : allTerrains.getTerrainFeatureTypes()) {

                    gold += allTerrainFeature.getGold();
                }
            }

            if (allTerrains.getTerrainResource() != null && allTerrains.getBooleanResource()) {
                gold += allTerrains.getTerrainResource().getGold();
            }
            if (allTerrains.getTerrainImprovement() != null) {
                gold += allTerrains.getTerrainImprovement().getImprovementType().getGold();
            }

            allTerrains.getCity().setGold(gold);
        }

         */

        for (City city : user.getCivilization().getCities()) {
            int gold = city.getGold();
            user.getCivilization().increaseGold(gold);
        }
        int numberOfUnits = user.getCivilization().getUnits().size();
        if (user.getCivilization().getGold() >= 0) {
            user.getCivilization().increaseGold(-numberOfUnits * database.getTurn());
            for (City city : user.getCivilization().getCities()) {
                for (Building cityBuildings : city.getBuildings()) {
                    user.getCivilization().increaseGold(-cityBuildings.getBuildingType().getMeintenance() * database.getTurn());
                }
            }
        } else {
            user.getCivilization().setScience(user.getCivilization().getScience() - numberOfUnits * database.getTurn());
            for (City city : user.getCivilization().getCities()) {
                for (Building cityBuildings : city.getBuildings()) {
                    user.getCivilization().setScience(user.getCivilization().getScience() - cityBuildings.getBuildingType().getMeintenance() * database.getTurn());
                }
            }
        }


        // gold == 0?

    }

    public void consumptFood(User user) {
        for (City city : user.getCivilization().getCities()) {
            int numberOfcitizen = city.getCitizens().size();
            int firstFood = city.getFood();
            city.setFood(firstFood - 2 * numberOfcitizen);
        }
        /*
         * citizens add food when work
         * tara complete city
         *
         */

        ArrayList<City> allCitiesHaveSettler = new ArrayList<>();
        for (Terrain allTerrain : user.getCivilization().getOwnedTerrains()) {
            if (allTerrain.getNonCombatUnit().getUnitType() == UnitTypes.SETTLER) {
                allCitiesHaveSettler.add(allTerrain.getCity());
            }
        }

        int divide = 1;
        if (user.getCivilization().getHappiness() < 0) {
            divide = 3;
        }

        for (Terrain allTerrain : user.getCivilization().getOwnedTerrains()) {
            if (!allCitiesHaveSettler.contains(allTerrain.getCity())) {
                int food = 0;
                if (allTerrain.getTerrainTypes() != null) {
                    food += allTerrain.getTerrainTypes().getFood();
                }
                if (allTerrain.getTerrainFeatureTypes() != null) {
                    for (TerrainFeatureTypes allTerrainFeature : allTerrain.getTerrainFeatureTypes()) {

                        food += allTerrainFeature.getFood();
                    }
                }

                if (allTerrain.getTerrainResource() != null && allTerrain.getBooleanResource()) {
                    food += allTerrain.getTerrainResource().getFood();
                }
                if (allTerrain.getTerrainImprovement() != null) {
                    food += allTerrain.getTerrainImprovement().getImprovementType().getFood();
                }

                allTerrain.getCity().setFood(allTerrain.getCity().getFood() + food / divide);
            }

        }

        // roshd shahr

    }

    public void setTurn(){
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("setTurn",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();

        }catch(IOException E){
            E.printStackTrace();
        }
    }

    public void setHappinessUser() {

        // without building
        // without technology


            try{
                RequestUser requestUser = new RequestUser();
                requestUser.addRequest("setHappiness",null);
                Gson gson = new Gson();
                dataOutputStream.writeUTF(gson.toJson(requestUser));
                dataOutputStream.flush();

            }catch(IOException E){
                E.printStackTrace();
            }
    }

    public void setScience() {
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("setScience",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();

        }catch(IOException E){
            E.printStackTrace();
        }

    }

    public String choosingATechnologyToStudy(User user, TechnologyTypes technologyType) {
        for (TechnologyTypes technologyType2 : technologyType.getRequirements()) {
            if (!isContainTechnology(user, technologyType2)) {
                if (notificationHistory.get(user) != null) {
                    notificationHistory.put(user, notificationHistory.get(user) + this.database.getTurn() + " you do not have required prerequisites" + "\n");
                } else {
                    notificationHistory.put(user, this.database.getTurn() + " you do not have required prerequisites" + "\n");
                }

                return "you do not have required prerequisites";
            } else if (isContainTechnology(user, technologyType2) && !getTechnologyByTechnologyType(user, technologyType2).getIsAvailable()) {
                if (notificationHistory.get(user) != null) {
                    notificationHistory.put(user, notificationHistory.get(user) + this.database.getTurn() + " you do not have required prerequisites" + "\n");
                } else {
                    notificationHistory.put(user, this.database.getTurn() + " you do not have required prerequisites" + "\n");
                }
                return "you do not have required prerequisites";
            }
        }
        for (Technology technology : user.getCivilization().getTechnologies()) {
            technology.setUnderResearch(false);
        }
        if (isContainTechnology(user, technologyType)) {
            getTechnologyByTechnologyType(user, technologyType).setUnderResearch(true);
            if (notificationHistory.get(user) != null) {
                notificationHistory.put(user, notificationHistory.get(user) + this.database.getTurn() + " Technology " + technologyType.name() + " is under research again" + "\n");
            } else {
                notificationHistory.put(user, this.database.getTurn() + " Technology " + technologyType.name() + " is under research again" + "\n");
            }

            return "Technology is under research again";
        } else {
            user.getCivilization().getTechnologies().add(new Technology(true, 0, technologyType, false));
        }
        if (notificationHistory.get(user) != null) {
            notificationHistory.put(user, notificationHistory.get(user) + this.database.getTurn() + " Technology " + technologyType.name() + " is under research" + "\n");
        } else {
            notificationHistory.put(user, this.database.getTurn() + " Technology " + technologyType.name() + " is under research" + "\n");
        }

        return "Technology is under research";
    }

    public boolean isContainTechnology(User user, TechnologyTypes technologyType) {
        for (Technology technology : user.getCivilization().getTechnologies()) {
            if (technology.getTechnologyType() != null && technology.getTechnologyType().equals(technologyType) && technology.getIsAvailable()) {
                return true;
            }
        }
        return false;
    }

    public boolean isContainTechnologyType(User user, TechnologyTypes technologyType) {
        for (Technology technology : user.getCivilization().getTechnologies()) {
            if (technology.getTechnologyType() != null && technology.getTechnologyType().equals(technologyType)) {
                return true;
            }
        }
        return false;
    }

    public Technology getTechnologyByTechnologyType(User user, TechnologyTypes technologyType) {
        for (Technology technology : user.getCivilization().getTechnologies()) {
            if (technology.getTechnologyType().equals(technologyType)) {
                return technology;
            }
        }
        return null;
    }

    public String researchInfo(User user) {
        if (getUnderResearchTechnology(user) != null) {
            return getUnderResearchTechnology(user).toString();
        }
        return "there is no under research technology";

    }

    public ArrayList<Terrain> getNeighborTerrainsOfOneTerrain(Terrain terrain, Map map) {
        ArrayList<Terrain> neighbors = new ArrayList<>();
        Terrain[][] copy_map = map.getTerrain();
        int x_beginning = terrain.getX();
        int y_beginning = terrain.getY();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x_beginning + i < 0 || x_beginning + i >= map.getROW() || y_beginning + j < 0 || y_beginning + j >= map.getCOL() || y_beginning % 2 == 0 && ((i == 0 && j == 0) || (i == 1 && j == -1) || (i == 1 && j == 1)) || y_beginning % 2 == 1 && ((i == 0 && j == 0) || (i == -1 && j == 1) || (i == -1 && j == -1))) {

                } else {
                    neighbors.add(copy_map[x_beginning + i][y_beginning + j]);
                }

            }

        }
        return neighbors;

    }

    public ArrayList<Terrain> NeighborsAtADistanceOfOneFromAnArraylistOfTerrains(ArrayList<Terrain> terrains, Map map) {

        ArrayList<Terrain> neighbors = new ArrayList<>();
        for (Terrain terrain : terrains) {
            for (Terrain terrain2 : getNeighborTerrainsOfOneTerrain(terrain, map)) {
                neighbors.addAll(getNeighborTerrainsOfOneTerrain(terrain2, map));
            }
        }

        neighbors.removeAll(terrains);

        return deleteExcessTerrain(neighbors);

    }

    public ArrayList<Terrain> NeighborsAtADistanceOfTwoFromAnArraylistOfTerrains(ArrayList<Terrain> terrains, Map map) {

        ArrayList<Terrain> neighbors = new ArrayList<>();
        ArrayList<Terrain> neighborsAtADistanceOfOne = NeighborsAtADistanceOfOneFromAnArraylistOfTerrains(terrains, map);

        neighbors.addAll(neighborsAtADistanceOfOne);
        neighbors.addAll(NeighborsAtADistanceOfOneFromAnArraylistOfTerrains(neighborsAtADistanceOfOne, map));

        neighbors.removeAll(terrains);

        return deleteExcessTerrain(neighbors);

    }

    public ArrayList<Terrain> deleteExcessTerrain(ArrayList<Terrain> terrains) {
        ArrayList<Terrain> finalTerrains = new ArrayList<>();
        for (Terrain terrain : terrains) {
            boolean isNew = true;
            for (Terrain terrain1 : finalTerrains) {
                if (terrain.equals(terrain1)) {
                    isNew = false;
                    break;
                }
            }

            if (isNew) {
                finalTerrains.add(terrain);
            }
        }

        return finalTerrains;
    }

    public void increaseTurnInConstructingUnit(ArrayList<User> users) {
        for (User user : users) {
            for (City city : user.getCivilization().getCities()) {
                ArrayList<Unit> needToRemove = new ArrayList<>();
                ArrayList<Building> needToRemoveBuildings = new ArrayList<>();
                for (Unit unit : city.getConstructionWaitList()) {
                    if (unit.getPassedTurns() < unit.getUnitType().getTurn()) {
                        unit.setPassedTurns(unit.getPassedTurns() + 1);
                    } else {
                        unit.setPassedTurns(0);
                        user.getCivilization().getUnits().add(unit);
                        Terrain terrain = getTerrainByCoordinates(unit.getX(), unit.getY());
                        if (unit instanceof CombatUnit) {
                            terrain.setCombatUnit((CombatUnit) unit);
                        } else {
                            terrain.setNonCombatUnit((NonCombatUnit) unit);
                        }
                        needToRemove.add(unit);

                    }
                }
                for (Building building : city.getBuildingWaitlist()) {
                    if (building.getPassedTurns() < building.getBuildingType().getTurn()) {
                        building.setPassedTurns(building.getPassedTurns() + 1);
                    } else {
                        building.setPassedTurns(0);
                        city.getBuildings().add(building);
                        needToRemoveBuildings.add(building);

                    }
                }
                city.getConstructionWaitList().removeAll(needToRemove);
                city.getBuildingWaitlist().removeAll(needToRemoveBuildings);
            }
        }
    }

    public void setAvailability(){
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("availability",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch(IOException E){
            E.printStackTrace();
        }
    }

    public void setUnitsParametersAfterEachTurn(ArrayList<User> users) {
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("setUnitsParametersAfterEachTurn",null);
            requestUser.setUsers(users);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch(IOException E){
            E.printStackTrace();
        }

    }

    public String increaseTurnCheat(int amount) {
        String result = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("increaseTurnCheat",null);
            requestUser.setIJ(Integer.toString(amount));
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            result = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getAction();
        }catch(IOException E){
            E.printStackTrace();
        }


        return result;
    }

    public String increaseGoldCheat(User user, int amount) {
        String result = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("increaseGoldCheat",user);
            requestUser.setIJ(Integer.toString(amount));
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch(IOException E){
            E.printStackTrace();
        }
        return result;
    }


    public String increaseHappinessCheat(User user, int amount) {
        String result = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("increaseHappinessCheat",user);
            requestUser.setIJ(Integer.toString(amount));
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch(IOException E){
            E.printStackTrace();
        }
        return result;
    }

    public String increaseScienceCheat(User user, int amount) {
        String result = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("increaseScienceCheat",user);
            requestUser.setIJ(Integer.toString(amount));
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            result = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getAction();

        }catch(IOException E){
            E.printStackTrace();
        }
       return result;
    }

    public String buyTechnologyCheat(User user, TechnologyTypes technologyType) {
        String result = null;
        try{
            Gson gson = new Gson();
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("buyTechnologyCheat",user);
            requestUser.setTechnologyTypes(technologyType);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            result = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getAction();
        }catch(IOException E){
            E.printStackTrace();
        }
      return result;
    }

    public String cheatMoveCombatUnit(int x, int y) {
        String result = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("cheatMoveCombatUnit",null);
            requestUser.setIJ(x + " " + y);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            result = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getAction();
        }catch(IOException E){
            E.printStackTrace();
        }

        return result;

    }

    public String cheatMoveNonCombatUnit(int x, int y) {
        String result = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("cheatMoveNonCombatUnit",null);
            requestUser.setIJ(x + " " + y);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            result = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getAction();
        }catch(IOException E){
            E.printStackTrace();
        }

        return result;


    }

    public String buyCheatTile(User user, int x, int y) {
        String result = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("buyCheatTile",user);
            requestUser.setIJ(x + " " + y);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            result = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getAction();
        }catch(IOException E){
            E.printStackTrace();
        }
       return result;
    }

    public String setCheatUnit(User user, String name, int x, int y) {
        String result = null;
        try{
               RequestUser requestUser = new RequestUser();
               requestUser.addRequest("setCheatUnit",user);
               requestUser.setNickname(name);
               requestUser.setIJ(x + " " + y);
               Gson gson = new Gson();
               dataOutputStream.writeUTF(gson.toJson(requestUser));
               dataOutputStream.flush();
               result = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getAction();
        }catch(IOException E){
            E.printStackTrace();
        }
        return result;


    }

    public String setCheatImprovement(ImprovementTypes improvementType, int x, int y) {

        Improvement improvement = new Improvement(x, y, improvementType);
        improvement.setAvailable(true);
        getMap().getTerrain()[x][y].setTerrainImprovement(improvement);
        return "Improvement was added illegally!";
    }

    public String setCheatResource(ResourceTypes resourceType, int x, int y) {
        Resource resource = new Resource(resourceType);
        getMap().getTerrain()[x][y].setTerrainResource(resource);
        return "Resource was added illegally!";
    }

    public String setCheatTerrainFeatureType(TerrainFeatureTypes terrainFeatureTypes, int x, int y) {
        getMap().getTerrain()[x][y].setTerrainFeatureTypes(terrainFeatureTypes);
        return "Terrain feature was added illegally!";
    }

    public String setCheatTerrainType(TerrainTypes terrainTypes, int x, int y) {
        getMap().getTerrain()[x][y].setTerrainTypes(terrainTypes);
        return "Terrain was added illegally!";
    }

    public String deleteCheatImprovement(int x, int y) {
        String result = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("deleteCheatImprovement",null);
            requestUser.setIJ(x + " " + y);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            result = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getAction();
        }catch(IOException E){
            E.printStackTrace();
        }
        return result;
    }

    public String repairCheatImprovement(int x, int y) {
        String result = null;
        try{
           RequestUser requestUser =  new RequestUser();
           requestUser.addRequest("repairCheatImprovement",null);
           requestUser.setIJ(x + " " + y);
           Gson gson = new Gson();
           dataOutputStream.writeUTF(gson.toJson(requestUser));
           dataOutputStream.flush();
           result = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getAction();
        }catch(IOException E){
            E.printStackTrace();
        }
       return result;
    }

    public void setTechnologyTypes(Civilization civilization) {
        ArrayList<Technology> technologies = civilization.getTechnologies();
        ArrayList<TechnologyTypes> technologyTypes = civilization.getTechnologyTypes();
        for (Technology technology : technologies) {
            technologyTypes.add(technology.getTechnologyType());

        }
        civilization.setTechnologyTypes(technologyTypes);
    }

    public String pillageImprovement(CombatUnit combatUnit, Terrain terrain) {
        if (terrain.getTerrainImprovement() == null) {
            return "There is no improvement in this tile";
        } else {
            terrain.getTerrainImprovement().setPillaged(true);
            combatUnit.getNextTerrain().clear();
            combatUnit.setIsFinished(true);
            combatUnit.setIsSelected(false);
            return "Improvement was pillaged";

        }
    }

    public ArrayList<Terrain> terrainsAtInputDistance(ArrayList<Terrain> terrains, int index, Map map) {
        if (index == 1) {
            return NeighborsAtADistanceOfOneFromAnArraylistOfTerrains(terrains, map);

        } else {
            ArrayList<Terrain> neighbors = new ArrayList<>();
            ArrayList<Terrain> neighborsAtADistanceIndexMinusOne = terrainsAtInputDistance(terrains, index - 1, map);

            neighbors.addAll(neighborsAtADistanceIndexMinusOne);
            neighbors.addAll(NeighborsAtADistanceOfOneFromAnArraylistOfTerrains(neighborsAtADistanceIndexMinusOne, map));
            neighbors.removeAll(terrains);

            return deleteExcessTerrain(neighbors);

        }

    }

    public void wakeUpFromAlert(CombatUnit combatUnit) {
        ArrayList<Terrain> terrainsAtADistanceFour = terrainsAtInputDistance(new ArrayList<>() {
            {
                add(getTerrainByCoordinates(combatUnit.getX(), combatUnit.getY()));
            }

        }, 4, this.getMap());

        for (Terrain terrain : terrainsAtADistanceFour) {
            if (terrain.getCombatUnit() != null && (getContainerCivilization(combatUnit) != (getContainerCivilization(terrain.getCombatUnit())))) {
                combatUnit.setAlert(false);
                return;
            }
        }

    }

    public Civilization getContainerCivilization(Unit unit) {
        Civilization civilization = null;
        try{
           RequestUser requestUser = new RequestUser();
           requestUser.addRequest("getContainerCivilization",null);
           requestUser.setUnit(unit);
           Gson gson = new Gson();
           dataOutputStream.writeUTF(gson.toJson(requestUser));
           dataOutputStream.flush();
        }catch (IOException E){
            E.printStackTrace();
        }
        return civilization;

    }

    public String demographicPanel() {
        StringBuilder output = new StringBuilder();

        for (User user : database.getUsers()) {
            Civilization civilization = user.getCivilization();
            int numberOfCitizens = 0;
            for (City city : civilization.getCities()) {
                numberOfCitizens += city.getCitizens().size();
            }
            output.append("Civilization ").append(civilization.getName()).append(" size : ").append(civilization.getOwnedTerrains().size()).append("\n");
            output.append("Civilization ").append(civilization.getName()).append(" Gold : ").append(civilization.getGold()).append("\n");
            output.append("Civilization ").append(civilization.getName()).append(" Science : ").append(civilization.getScience()).append("\n");
            output.append("Civilization ").append(civilization.getName()).append(" Happiness : ").append(civilization.getHappiness()).append("\n");
            output.append("Civilization ").append(civilization.getName()).append(" Number of Cities : ").append(civilization.getCities().size()).append("\n");
            output.append("Civilization ").append(civilization.getName()).append(" Number of Citizens : ").append(numberOfCitizens).append("\n\n\n");

        }
        return output.toString();
    }

    public String notificationHistory(User user) {
        if (notificationHistory.get(user) == null) {
            return "this user has no notification history";
        }
        return notificationHistory.get(user);
    }

    public String militaryOverview(User user) {
        StringBuilder unitsInformation = new StringBuilder();
        for (Unit unit : user.getCivilization().getUnits()) {
            unitsInformation.append(unit.toString()).append("\n");
        }
        return unitsInformation.toString();
    }

    public String unitsInfo(User user) {
        StringBuilder unitsInformation = new StringBuilder();
        for (Unit unit : user.getCivilization().getUnits()) {
            unitsInformation.append(unit.getX()).append(" ").append(unit.getY()).append(" ").append(unit.getUnitType().name()).append("\n");
        }
        return unitsInformation.toString();
    }


    public Unit getUnitByCoordinatesAndName(User user, String name, int x, int y) {
        for (Unit unit : user.getCivilization().getUnits()) {
            if (unit.getUnitType().name().equalsIgnoreCase(name) && unit.getX() == x && unit.getY() == y) {
                return unit;
            }
        }

        return null;
    }

    public String activateUnit(User user, String name, int x, int y) {
        String result = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("activateUnit",user);
            requestUser.setNickname(name);
            requestUser.setIJ(x + " " + y);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            result = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getAction();
        }catch(IOException E){
            E.printStackTrace();
        }
        return result;

    }

    public String economicOverview(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        for (City city : user.getCivilization().getCities()) {
            stringBuilder.append("City size ").append(city.getMainTerrains().size()).append("\n");
            stringBuilder.append("Population ").append(city.getCitizens().size()).append("\n");
            stringBuilder.append("HP ").append(city.getHP()).append("\n");
            stringBuilder.append("Gold ").append(city.getGold()).append("\n");
            stringBuilder.append("Science ").append(city.getScience()).append("\n");
            stringBuilder.append("Food Storage ").append(city.getFood()).append("\n");
            stringBuilder.append("Production ").append(city.getProduction()).append("\n");
            if (!city.getConstructionWaitList().isEmpty()) {
                stringBuilder.append(city.getConstructionWaitList().get(0).getUnitType().name()).append(" will be constructed in ").append(city.getConstructionWaitList().get(0).getUnitType().getTurn() - city.getConstructionWaitList().get(0).getPassedTurns()).append(" Turn").append("\n");
            } else {
                stringBuilder.append("You are not constructing any unit in this city").append("\n");
            }


        }
        return stringBuilder.toString();
    }

    public String cityPanel(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        for (City city : user.getCivilization().getCities()) {
            stringBuilder.append("X of Central Terrain: ").append(city.getCentralTerrain().getX()).append("\n");
            stringBuilder.append("Y of Central Terrain: ").append(city.getCentralTerrain().getY()).append("\n\n\n");

        }
        return stringBuilder.toString();
    }

    public City getCityByCoordinates(int x, int y, User user) {
        for (City city : user.getCivilization().getCities()) {
            if (x == city.getCentralTerrain().getX() && y == city.getCentralTerrain().getY()) {
                return city;
            }
        }
        return null;
    }


    public void choosingATechnologyToStudyForGraphic(TechnologyTypes technologyType) {
        try{
            Gson gson = new Gson();
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("choosingATechnologyToStudy",null);
            requestUser.setTechnologyTypes(technologyType);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();

        }catch(IOException E){
            E.printStackTrace();
        }

    }

    public void getNeededTechnologies(HashMap<TechnologyTypes, Integer> requiredTechnologies, int depth, User user, TechnologyTypes technologyTypes) {
        for (TechnologyTypes technologyTypes1 : technologyTypes.getRequirements()) {
            if (!isContainTechnology(user, technologyTypes1)) {
                requiredTechnologies.put(technologyTypes1, depth);
                getNeededTechnologies(requiredTechnologies, depth + 1, user, technologyTypes1);
            }
        }

    }

    public TechnologyTypes getFirstRequiredTechnology(User user, TechnologyTypes technologyTypes) {

        HashMap<TechnologyTypes, Integer> neededTechs = new HashMap<>();
        getNeededTechnologies(neededTechs, 1, user, technologyTypes);
        if (neededTechs.isEmpty()) {
            return technologyTypes;
        }
        List<Integer> list = new ArrayList<>(neededTechs.values());
        list.sort(naturalOrder());
        int max = list.get(list.size() - 1);
        List<TechnologyTypes> techs = neededTechs.entrySet().stream().filter(e -> e.getValue() == max).map(e -> e.getKey()).toList();
        return techs.get(0);

    }


    public ArrayList<TechnologyTypes> unlockableTechnologies() {
        ArrayList<TechnologyTypes> technologyTypes = new ArrayList<>();
        RequestUser requestUser = new RequestUser();
        requestUser.addRequest("unlockableTechnologies",null);
        Gson gson = new Gson();
        try {
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            technologyTypes = responseUser.getTechnologyTypesArrayList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return technologyTypes;
    }

    public boolean haveAllPrerequisiteTechnologies(User user, TechnologyTypes technologyTypes) {
        for (TechnologyTypes technologyTypes1 : technologyTypes.getRequirements()) {
            if (!isContainTechnology(user, technologyTypes1)) {
                return false;
            }
        }
        return true;
    }

    public void setScore(int score,User user){
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("setScore",user);
            requestUser.setIJ(Integer.toString(score));
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
        }catch(IOException E){
            E.printStackTrace();
        }
    }

    public User getUserByCivilization(Civilization civilization) {
        User user = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("getUserByCivilization",null);
            requestUser.setCivilization(civilization);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            user = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getUser();
        }catch(IOException E){
            E.printStackTrace();
        }
       return user;
    }

    public ArrayList<Object> removeExcessObjects(ArrayList<Object> objects) {
        ArrayList<Object> finalArrayList = new ArrayList<>();
        for (Object object : objects) {
            boolean isNew = true;
            for (Object object1 : finalArrayList) {
                if (object.equals(object1)) {
                    isNew = false;
                    break;
                }
            }
            if (isNew) {
                finalArrayList.add(object);
            }
        }

        return finalArrayList;
    }

    public TechnologyTypes getTechnologyTypeByName(String name) {
        for (TechnologyTypes technologyTypes : TechnologyTypes.values()) {
            if (technologyTypes.name().equalsIgnoreCase(name)) {
                return technologyTypes;
            }
        }
        return null;
    }

    public ArrayList<TechnologyTypes> unlockTechnologies(TechnologyTypes technologyTypes) {
        ArrayList<TechnologyTypes> unlockTechnologies = new ArrayList<>();
        for (TechnologyTypes technologyTypes1 : TechnologyTypes.values()) {
            if (technologyTypes1.getRequirements().contains(technologyTypes)) {
                unlockTechnologies.add(technologyTypes1);
            }
        }
        return unlockTechnologies;
    }

    public TechnologyTypes lastUnlockedTechnology() {
        for (int i = getDatabase().getActiveUser().getCivilization().getTechnologies().size() - 1; i >= 0; i--) {
            if (getDatabase().getActiveUser().getCivilization().getTechnologies().get(i).getIsAvailable()) {
                return getDatabase().getActiveUser().getCivilization().getTechnologies().get(i).getTechnologyType();
            }
        }
        return null;
    }

    public String buildingAnImprovement(User user, ImprovementTypes improvementType) {
        NonCombatUnit nonCombatUnit = getSelectedNonCombatUnit();
        if (nonCombatUnit != null) {
            if (nonCombatUnit.getUnitType().equals(UnitTypes.WORKER)) {
                Terrain workersTerrain = getTerrainByCoordinates(nonCombatUnit.getX(), nonCombatUnit.getY());
                if (buildingAnImprovementInATerrain(user, improvementType, workersTerrain).equals("improvement was built successfully")) {
                    nonCombatUnit.setIsSelected(false);
                    nonCombatUnit.setIsFinished(true);
                }
                return buildingAnImprovementInATerrain(user, improvementType, workersTerrain);

            } else {
                return "you have to select a worker group first";
            }
        } else {
            return "you have to select a worker group first";
        }
    }

    public String buildingAnImprovementInATerrain(User user, ImprovementTypes improvementType, Terrain workersTerrain) {
        if (!user.getCivilization().getOwnedTerrains().contains(workersTerrain)) {
            return "you are not in your owned tiles";
        }

        if (workersTerrain.getTerrainImprovement() != null) {
            return "you have created another improvement in this terrain";
        } else {
            if (!isContainTechnology(user, improvementType.getRequiredTechnology())) {
                return "you lack prerequisite technologies";
            } else if (!improvementType.getCanBeBuiltON().contains(workersTerrain.getTerrainTypes()) && !improvementType.getCanBeBuiltON().contains(workersTerrain.getTerrainFeatureTypes())) {
                return "you can not create this improvement in this type of terrain";
            } else {
                Improvement improvement = new Improvement(workersTerrain.getX(), workersTerrain.getY(), improvementType);
                workersTerrain.setTerrainImprovement(improvement);
            }

        }
        if (notificationHistory.get(user) != null) {
            notificationHistory.put(user, notificationHistory.get(user) + this.database.getTurn() + " improvement will be built successfully" + "\n");
        } else {
            notificationHistory.put(user, this.database.getTurn() + " improvement will be built successfully" + "\n");
        }

        return "improvement will be built successfully";
    }

    public String deleteFeatures(String hasToBeDeleted) {
        NonCombatUnit nonCombatUnit = getSelectedNonCombatUnit();
        if (nonCombatUnit != null) {
            if (nonCombatUnit.getUnitType().equals(UnitTypes.WORKER)) {
                Terrain workersTerrain = getTerrainByCoordinates(nonCombatUnit.getX(), nonCombatUnit.getY());
                if (hasToBeDeleted.equals("ROUTE")) {
                    if (workersTerrain.getTerrainImprovement() == null) {
                        return "you have no road or railroad in this tile";
                    } else if (!workersTerrain.getTerrainImprovement().getImprovementType().equals(ImprovementTypes.ROAD) && !workersTerrain.getTerrainImprovement().getImprovementType().equals(ImprovementTypes.RAILROAD)) {
                        return "you have no road or railroad in this tile";
                    } else {
                        workersTerrain.getTerrainImprovement().setHasToBeDeleted(true);
                        nonCombatUnit.setIsSelected(false);
                        nonCombatUnit.setIsFinished(true);
                        return "route will be deleted";
                    }

                } else if (hasToBeDeleted.equals("JUNGLE") || hasToBeDeleted.equals("FOREST") || hasToBeDeleted.equals("MARSH")) {
                    if (workersTerrain.getTerrainFeatureTypes().get(0) == null) {
                        return "you have no Jungle or Forest or Marsh in this tile";
                    } else if (!workersTerrain.getTerrainFeatureTypes().get(0).equals(TerrainFeatureTypes.FOREST) && !workersTerrain.getTerrainFeatureTypes().get(0).equals(TerrainFeatureTypes.JUNGLE) && !workersTerrain.getTerrainFeatureTypes().get(0).equals(TerrainFeatureTypes.MARSH)) {
                        return "you have no Jungle or Forest or Marsh in this tile";
                    } else {
                        workersTerrain.setHasToBeDeleted(true);
                        nonCombatUnit.setIsSelected(false);
                        nonCombatUnit.setIsFinished(true);

                        return "feature will be deleted";

                    }

                } else {
                    return "There is nothing to delete";
                }

            } else {
                return "you have to select a settler group first";
            }
        } else {
            return "you have to select a settler group first";
        }

    }

    public String repairImprovement() {
        NonCombatUnit nonCombatUnit = getSelectedNonCombatUnit();
        if (nonCombatUnit != null) {
            if (nonCombatUnit.getUnitType().equals(UnitTypes.WORKER)) {
                Terrain workersTerrain = getTerrainByCoordinates(nonCombatUnit.getX(), nonCombatUnit.getY());
                if (workersTerrain.getTerrainImprovement() == null) {
                    return "you have no improvement in this tile";
                } else if (workersTerrain.getTerrainImprovement().isBeingRepaired()) {
                    return "your workers are repairing this improvement";
                } else if (workersTerrain.getTerrainImprovement().isAvailable()) {
                    return "there is no problem with this improvement";
                } else if (workersTerrain.getTerrainImprovement().isPillaged()) {
                    workersTerrain.getTerrainImprovement().setBeingRepaired(true);
                    workersTerrain.getTerrainImprovement().setPillaged(false);
                    return "improvement will be repaired";
                } else {
                    return "improvement is not available yet";
                }

            } else {
                return "you have to select a worker group first";
            }
        } else {
            return "you have to select a worker group first";
        }
    }

    public void increasingTurnInWorkersActions() {
        for (int i = 0; i < getMap().getROW(); i++) {
            for (int j = 0; j < getMap().getCOL(); j++) {
                Terrain terrain = getMap().getTerrain()[i][j];
                if (terrain.getTerrainImprovement() != null) {
                    if (terrain.getTerrainImprovement().isBeingRepaired()) {

                        if (terrain.getTerrainImprovement().getPassedTurns() < terrain.getTerrainImprovement().getImprovementType().getTurn()) {
                            int passedTurns = terrain.getTerrainImprovement().getPassedTurns() + 1;
                            terrain.getTerrainImprovement().setPassedTurns(passedTurns);
                        } else {
                            terrain.getTerrainImprovement().setAvailable(true);
                            terrain.getTerrainImprovement().setBeingRepaired(false);
                            terrain.getTerrainImprovement().setPassedTurns(0);

                        }

                    } else if (terrain.getTerrainImprovement().isHasToBeDeleted()) {

                        if (terrain.getTerrainImprovement().getPassedTurns() < terrain.getTerrainImprovement().getImprovementType().getTurn()) {
                            int passedTurns = terrain.getTerrainImprovement().getPassedTurns() + 1;
                            terrain.getTerrainImprovement().setPassedTurns(passedTurns);
                        } else {
                            terrain.setTerrainImprovement(null);

                        }
                    } else if (!terrain.getTerrainImprovement().isAvailable() && !terrain.getTerrainImprovement().isPillaged()) {

                        if (terrain.getTerrainImprovement().getPassedTurns() < terrain.getTerrainImprovement().getImprovementType().getTurn()) {
                            int passedTurns = terrain.getTerrainImprovement().getPassedTurns() + 1;
                            terrain.getTerrainImprovement().setPassedTurns(passedTurns);
                        } else {
                            terrain.getTerrainImprovement().setAvailable(true);
                            terrain.getTerrainImprovement().setPassedTurns(0);
                        }
                    }
                } else if (!terrain.getTerrainFeatureTypes().isEmpty()) {
                    if (terrain.isHasToBeDeleted()) {
                        if (terrain.getPassedTurns() < 6) {
                            int turn = terrain.getPassedTurns() + 1;
                            terrain.setPassedTurns(turn);
                        } else {
                            terrain.getTerrainFeatureTypes().clear();
                        }
                    }

                }
            }
        }
    }


    public ArrayList<ImprovementTypes> improvementsThatCanBeBuiltInThisTerrain()  {
        ArrayList<ImprovementTypes> improvementTypesList = new ArrayList<>();
        try{
            Gson gson = new Gson();
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("improvementsThatCanBeBuiltInThisTerrain",null);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            improvementTypesList = responseUser.getImprovementTypes();
        }catch(IOException E){
            E.printStackTrace();
        }
        return improvementTypesList;


    }

    public ImprovementTypes routsThatCanBeDeletedInThisTerrain() {
        ImprovementTypes improvementTypes = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("routsThatCanBeDeletedInThisTerrain",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            improvementTypes = responseUser.getImprovementType();
        }catch(IOException E){
            E.printStackTrace();
        }
        return improvementTypes;
    }

    public TerrainFeatureTypes featuresThatCanBeDeletedInThisTerrain() {
        TerrainFeatureTypes terrainFeatureTypes = null;
        try{
            Gson gson = new Gson();
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("featuresThatCanBeDeletedInThisTerrain",null);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            terrainFeatureTypes = responseUser.getFeature().get(0);
        }catch(IOException E){
            E.printStackTrace();
        }
        return terrainFeatureTypes;
    }

    public ImprovementTypes improvementsThatCanBeRepairedInThisTerrain() {
        ImprovementTypes improvement = null;
        try{
            Gson gson = new Gson();
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("improvementsThatCanBeRepairedInThisTerrain",null);
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            String res = dataInputStream.readUTF();
            ResponseUser responseUser = gson.fromJson(res,ResponseUser.class);
            improvement = responseUser.getImprovementType();
        }catch(IOException E){
            E.printStackTrace();
        }
       return improvement;

    }





    public User getNextTurnUser() {
        int i = DatabaseController.getInstance().getDatabase().getUsers().indexOf(DatabaseController.getInstance().getDatabase().getActiveUser());
        if (i == DatabaseController.getInstance().getDatabase().getUsers().size() - 1) {
            return DatabaseController.getInstance().getDatabase().getUsers().get(0);
        } else {
            return DatabaseController.getInstance().getDatabase().getUsers().get(i + 1);
        }
    }

    public Civilization theWinnerCivilization(){
        Civilization civilization = null;
        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("theWinnerCivilization",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();
            civilization = gson.fromJson(dataInputStream.readUTF(),ResponseUser.class).getCivilization();
        }catch(IOException E){
            E.printStackTrace();
        }
       return civilization;
    }

    public Civilization theWinnerAfterYear2050(){
        int max = -1;
        for(User user : database.getUsers()){
            if(user.getCivilization().getScore()>max){
                max = user.getCivilization().getScore();
            }
        }
        for(User user : database.getUsers()){
            if(max ==user.getCivilization().getScore()){
                return user.getCivilization();
            }
        }
        return null;
    }

    public int calculatingScoreForEachCivilizationAfterEachRound(Civilization civilization){
        if(civilization.isHasEverHadCity() && civilization.getCities().size()==0){
            return 0;
        }
        return civilization.getTerrains().size()*50 + civilization.getTechnologies().size()*500+ civilization.getCities().size()*150;

    }

    public void increasingYearPerTurn(){

        try{
            RequestUser requestUser = new RequestUser();
            requestUser.addRequest("increasingYearPerTurn",null);
            Gson gson = new Gson();
            dataOutputStream.writeUTF(gson.toJson(requestUser));
            dataOutputStream.flush();

        }catch(IOException E){
            E.printStackTrace();
        }


    }




}