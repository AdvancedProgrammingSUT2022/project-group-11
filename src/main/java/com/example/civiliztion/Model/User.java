package com.example.civiliztion.Model;

import com.example.civiliztion.Model.GlobalChats.Room;
import com.example.civiliztion.Model.GlobalChats.privateChat;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private Civilization civilization;
    private int score;
    private boolean online = false;

    private ArrayList<privateChat> privatechat = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();

    public User(String username, String password, String nickname, Civilization civil) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.civilization = civil;
    }

    public Civilization getCivilization() {
        return this.civilization;
    }

    public void setCivilization(Civilization civil) {
        this.civilization = civil;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public ArrayList<privateChat> getPrivateChats() {
        return privatechat;
    }

    public void addPrivateChats(privateChat privateChats) {
        this.privatechat.add(privateChats);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}
