package com.example.civilization.View;

import com.example.civilization.Controllers.DatabaseController;
import com.example.civilization.Requests.RequestUser;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ScannerMenu {
    private DatabaseController databaseController;
    public static Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    public ScannerMenu(){

        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DatabaseController.dataInputStream = dataInputStream;
            DatabaseController.dataOutputStream = dataOutputStream;
        }catch (IOException E){
            E.printStackTrace();
        }
        this.databaseController = DatabaseController.getInstance();
    }

    public void run(){
        while(true){
            new Thread(() ->{
                try {
                    while (true) {
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
                        }
                      databaseController.updateGame();
                    }
                }catch(IOException E){
                    E.printStackTrace();
                }

            }).start();
        }
    }
}
