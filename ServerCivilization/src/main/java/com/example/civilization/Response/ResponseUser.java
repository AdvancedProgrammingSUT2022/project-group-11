package com.example.civilization.Response;

import com.example.civilization.Model.GlobalChats.Message;
import com.example.civilization.Model.Map;
import com.example.civilization.Model.Resources.Resource;
import com.example.civilization.Model.River;
import com.example.civilization.Model.Terrain;
import com.example.civilization.Model.TerrainFeatures.TerrainFeatureTypes;
import com.example.civilization.Model.Terrains.TerrainTypes;
import com.example.civilization.Model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseUser {
    private String action;
    private User user;
    private Map map;
    private ArrayList<User> users = new ArrayList<>();
    private River river;

    private String IJ;
    private Terrain terrain;
    private TerrainTypes typeTile;
    private ArrayList<TerrainFeatureTypes> feature;
    private Resource resource;
    private ArrayList<Message> allMessagePublic = new ArrayList<>();

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    private Message message;
    public ArrayList<Message> getAllMessagePublic() {
        return allMessagePublic;
    }

    public void setAllMessagePublic(ArrayList<Message> allMessagePublic) {
        this.allMessagePublic = allMessagePublic;
    }
    public void setResource(Resource resource){
        this.resource = resource;
    }
    public Resource getResource(){
        return this.resource;
    }

    public void setType(TerrainTypes TYPE){
        this.typeTile = TYPE;
    }
    public TerrainTypes getType(){
        return this.typeTile;
    }


    public void setFeatures(ArrayList<TerrainFeatureTypes> feature){
        this.feature = feature;
    }

    public ArrayList<TerrainFeatureTypes>  getFeature(){
        return this.feature;
    }

    public void setTerrain(Terrain terrain){
        this.terrain = terrain;
    }

    public Terrain getTerrain(){
        return this.terrain;
    }
    public void setIJ(String IJ){
        this.IJ = IJ;
    }

    public String getIJ(){
        return this.IJ;
    }
    public void setRiver(River river){
        this.river = river;
    }
    public River getRiver(){
        return this.river;
    }
    public void setUsers(ArrayList<User> users){
        this.users = users;
    }
    public ArrayList<User> getUsers(){
        return this.users;
    }
    public void setMap(Map map) {
        this.map = map;
    }
    public Map getMap(){
        return this.map;
    }
    public void addResponse(String action,User user){
        this.action = action;
        this.user = user;
    }

    public String getAction(){
        return this.action;
    }
    public User getUser(){
        return this.user;
    }
}
