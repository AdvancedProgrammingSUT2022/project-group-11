package com.example.civilization.View;

import com.example.civilization.Controllers.DatabaseController;
import com.example.civilization.Model.Units.Unit;
import com.example.civilization.Requests.RequestUser;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ScannerMenu {
    private DatabaseController databaseController;
    private ServerSocket serverSocket;
    public static Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    public ScannerMenu() throws IOException {
        serverSocket = new ServerSocket(7777);
        this.databaseController = DatabaseController.getInstance();
    }

    public void run() throws IOException {
        while(true){
            socket = serverSocket.accept();
            new Thread(() ->{
                try {
                    while (true) {
                        this.dataInputStream = new DataInputStream(socket.getInputStream());
                        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        DatabaseController.dataInputStream = dataInputStream;
                        DatabaseController.dataOutputStream = dataOutputStream;
                        String input = dataInputStream.readUTF();
                        Gson gson = new Gson();
                        RequestUser requestUser = gson.fromJson(input,RequestUser.class);
                        String action = requestUser.getAction();
                        if(action.equals("getUserByUsernameAndPassword")){
                            databaseController.userLogin(requestUser);
                        }else if(action.equals("createUser")){
                            databaseController.createUser(requestUser);
                        }else if(action.equals("activeUser")){
                            databaseController.activeUser(requestUser);
                        }else if(action.equals("changeUserNickname")){
                            databaseController.changeUserNickname(requestUser);
                        }else if(action.equals("changePassword")){
                            databaseController.changePassword(requestUser);
                        }else if(action.equals("generateMap")){
                            databaseController.generateMapFromServer(requestUser);
                        }else if(action.equals("setCivilizations")){
                            databaseController.setCivilizations();
                        }else if(action.equals("getAllUser")){
                            databaseController.getAllUsers();
                        }else if(action.equals("addRiverManually")){
                            databaseController.addRiverManually(requestUser);
                        }else if(action.equals("findTerrain")){
                            databaseController.findTerrain(requestUser);
                        }else if(action.equals("sendDataManuallyTerrain")){
                            databaseController.senDataTerrainManually(requestUser);
                        }else if(action.equals("allPublicMessage")){
                            databaseController.sendAllPublicMessage(requestUser);
                        }else if(action.equals("addPublicMessage")){
                            databaseController.addPublicMessage(requestUser);
                        }else if(action.equals("addPrivateMessage")){
                            databaseController.addPrivateMessage(requestUser);
                        }else if(action.equals("addRoomMessage")){
                            databaseController.addRoomMessage(requestUser);
                        }else if(action.equals("removePublicMessage")){
                            databaseController.removeMessagePublicChat(requestUser);
                        }else if(action.equals("removePrivateMessage")){
                            databaseController.removeMessagePrivateChat(requestUser);
                        }else if(action.equals("removeRoomMessage")){
                            databaseController.removeMessageRoomChat(requestUser);
                        }else if(action.equals("newPrivateChat")){
                            databaseController.addPrivateChatForUser(requestUser);
                        }else if(action.equals("getAllRooms")){
                           databaseController.getAllRoom(requestUser);
                        }else if(action.equals("getAllPrivateChats")){
                            databaseController.getAllPrivateChats(requestUser);
                        }else if(action.equals("newRoomChat")){
                            databaseController.newRoomChat(requestUser);
                        }else if(action.equals("getMap")){
                            databaseController.getMapServer();
                        }else if(action.equals("lastUnlockTechnology")){
                            databaseController.getLastUnlockTechnology();
                        }else if(action.equals("unlockableTechnologies")){
                            databaseController.unlockTechnology();
                        }else if(action.equals("choosingATechnologyToStudy")){
                            databaseController.choosingATechnologyToStudy(requestUser);
                        }else if(action.equals("addNewTechnology")){
                            databaseController.addNewTechnology(requestUser);
                        }else if(action.equals("getSelectedNonCombatUnit")){
                            databaseController.getNoncombatUnit();
                        }else if(action.equals("getSelectedCombatUnit")){
                            databaseController.getCombatUnit();
                        }else if(action.equals("improvementsThatCanBeBuiltInThisTerrain")){
                            databaseController.improvementsThatCanBeBuilt();
                        }else if(action.equals("routsThatCanBeDeletedInThisTerrain")){
                            databaseController.routsThatCanBeDeleted();
                        }else if(action.equals("featuresThatCanBeDeletedInThisTerrain")){
                            databaseController.featuresThatCanBeDeleted();
                        }else if(action.equals("improvementsThatCanBeRepairedInThisTerrain")){
                            databaseController.improvementsThatCanBeRepaired();
                        }else if(action.equals("increaseTurnCheat")){
                            databaseController.increaseTurn(requestUser);
                        }else if(action.equals("increaseGoldCheat")){
                            databaseController.increaseGold(requestUser);
                        }else if(action.equals("cheatMoveCombatUnit")){
                            databaseController.increaseCombatUnit(requestUser);
                        }else if(action.equals("cheatMoveNonCombatUnit")){
                            databaseController.increaseNonCombatUnit(requestUser);
                        }else if(action.equals("increaseHappinessCheat")){
                            databaseController.increaseHappiness(requestUser);
                        }else if(action.equals("increaseScienceCheat")){
                            databaseController.increaseScience(requestUser);
                        }else if(action.equals("buyTechnologyCheat")){
                            databaseController.addTechnologyType(requestUser);
                        }else if(action.equals("buyCheatTile")){
                            databaseController.increaseTile(requestUser);
                        }else if(action.equals("setCheatUnit")){
                            databaseController.addCheatUnit(requestUser);
                        }else if(action.equals("deleteCheatImprovement")){
                            databaseController.deleteImprovement(requestUser);
                        }else if(action.equals("repairCheatImprovement")){
                            databaseController.repairImprovement(requestUser);
                        }else if(action.equals("activateUnit")){
                            databaseController.unitActive(requestUser);
                        }else if(action.equals("getAllPlayerUser")){
                            databaseController.getAllPlayerUser();
                        }else if(action.equals("addUserToPlayerUsers")){
                            databaseController.addUserToPlayerUsers(requestUser);
                        }else if(action.equals("removeUserToPlayerUsers")){
                            databaseController.removeUserToPlayerUsers(requestUser);
                        }else if(action.equals("notificationHistory")){
                            databaseController.notification();
                        }else if(action.equals("changingTheStateOfAUnit")){
                            databaseController.changingTheStateUnit(requestUser);
                        }else if(action.equals("unitMovement")){
                            databaseController.unitMovement(requestUser);
                        }else if(action.equals("theWinnerCivilization")){
                            databaseController.theWinnerCivil(requestUser);
                        }else if(action.equals("getUserByCivilization")){
                            databaseController.getUserCivil(requestUser);
                        }else if(action.equals("setScore")){
                            databaseController.setScore(requestUser);
                        }else if(action.equals("getYear")){
                            databaseController.getYear();
                        }else if(action.equals("clear")){
                            databaseController.clear();
                        }else if(action.equals("setAllUnitsUnfinished")){
                            databaseController.setAllUnitunfinished(requestUser);
                        }else if(action.equals("setActiveUser")){
                            databaseController.setActiveUser();
                        }else if(action.equals("setUnitsParametersAfterEachTurn")){
                            databaseController.setUnitsParametersAfterEachTurn(requestUser);
                        }else if(action.equals("availability")){
                            databaseController.setAvailability();
                        }else if(action.equals("setScience")){
                            databaseController.science();
                        }else if(action.equals("setHappiness")){
                            databaseController.happiness();
                        }else if(action.equals("setTurn")){
                            databaseController.turn();
                        }else if(action.equals("increasingYearPerTurn")){
                            databaseController.increasingTurnYear();
                        }else if(action.equals("deselectAllUnits")){
                            databaseController.deselectAllUnits();
                        }else if(action.equals("getContainerCivilization")){
                            databaseController.getContainerCivilization(requestUser);
                        }else if(action.equals("isSelectedCombatUnit")){
                            databaseController.setIsSelectedCombatUnit(requestUser);
                        }else if(action.equals("isSelectedNonCombatUnit")){
                            databaseController.setIsSelectedNonCombatUnit(requestUser);
                        }else if(action.equals("setTerrainsOfEachCivilization")){
                            databaseController.setTerrainsOfEachCivilization(requestUser);
                        }else if(action.equals("movementOfAllUnits")){
                            databaseController.movementOfAllUnits(DatabaseController.getInstance().getDatabase().getActiveUser());
                        }else if(action.equals("startGame")){
                            databaseController.startGame();
                        }else if(action.equals("initializeMap")){
                            databaseController.initializeMap();
                        }else if(action.equals("removeDemandRequest")){
                            databaseController.removeDemandRequest(requestUser);
                        }else if(action.equals("removeDemandCityRequest")){
                            databaseController.removeDemandCityRequest(requestUser);
                        }else if (action.equals("citySetOwner")){
                            databaseController.citySetOwner(requestUser);
                        }else if(action.equals("setCityGold")){
                            databaseController.setCityGold(requestUser);
                        }else if(action.equals("putStatusCivilization")){
                            databaseController.putStatusCivilization(requestUser);
                        }else if(action.equals("putStatusSecondCivilization")){
                            databaseController.putStatusSecondCivilization(requestUser);
                        }else if(action.equals("putTradeRequest")){
                            databaseController.putTradeRequest(requestUser);
                        }else if(action.equals("removePutTradeRequest")){
                            databaseController.removePutTradeRequest(requestUser);
                        }else if(action.equals("removePutTradeRequestCivilization")){
                            databaseController.removePutTradeRequestCivilization(requestUser);
                        }else if(action.equals("addCity")){
                            databaseController.addCity(requestUser);
                        }else if(action.equals("removeCity")){
                            databaseController.removeCity(requestUser);
                        }else if (action.equals("addUserToStartPlayer")){
                            databaseController.addUserToStartPlayer(requestUser);
                        }else if (action.equals("removeUserToStartPlayer")){
                            databaseController.removeUserToStartPlayer(requestUser);
                        }else if(action.equals("update")){
                            databaseController.update(requestUser);
                        }else if(action.equals("accept")){
                            databaseController.accept(requestUser);
                        }else if(action.equals("getStartUsers")){
                            databaseController.getStartUsers();
                        }

                    }
                }catch(IOException E){
                    E.printStackTrace();
                }

            }).start();
        }
    }
}
