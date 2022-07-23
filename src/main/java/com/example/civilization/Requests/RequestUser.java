package com.example.civilization.Requests;

import com.example.civilization.Model.*;
import com.example.civilization.Model.City.City;
import com.example.civilization.Model.GlobalChats.Message;
import com.example.civilization.Model.GlobalChats.Room;
import com.example.civilization.Model.GlobalChats.privateChat;
import com.example.civilization.Model.Improvements.ImprovementTypes;
import com.example.civilization.Model.Resources.Resource;
import com.example.civilization.Model.Technologies.TechnologyTypes;
import com.example.civilization.Model.TerrainFeatures.TerrainFeatureTypes;
import com.example.civilization.Model.Terrains.TerrainTypes;
import com.example.civilization.Model.Units.CombatUnit;
import com.example.civilization.Model.Units.NonCombatUnit;
import com.example.civilization.Model.Units.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestUser {
    public String action;
    public User user;
    public String nickname;
    public String password;
    public Map map;
    public ArrayList<User> users = new ArrayList<>();
    public River river;
    public String IJ;

    public Terrain terrain;
    public TerrainTypes typeTile;
    public ArrayList<TerrainFeatureTypes> feature;
    public Resource resource;

    public privateChat privateChat;
    public ArrayList<Room> rooms = new ArrayList<>();
    public ArrayList<privateChat> privateChats = new ArrayList<>();

    public TechnologyTypes technologyTypes;
    public ArrayList<TechnologyTypes> technologyTypesArrayList;

    public NonCombatUnit nonCombatUnit;
    public CombatUnit combatUnit;
    public Civilization civilization;
    private City city;
    private City secondCity;

    public City getSecondCity() {
        return secondCity;
    }

    public void setSecondCity(City secondCity) {
        this.secondCity = secondCity;
    }
    private Civilization secondCivilization;

    public Civilization getSecondCivilization() {
        return secondCivilization;
    }

    public void setSecondCivilization(Civilization secondCivilization) {
        this.secondCivilization = secondCivilization;
    }
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    private Unit unit;

    public Civilization getCivilization() {
        return civilization;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }

    private ArrayList<ImprovementTypes> improvementTypes;

    private ImprovementTypes improvementType;

    public ImprovementTypes getImprovementType() {
        return improvementType;
    }

    public void setImprovementType(ImprovementTypes improvementType) {
        this.improvementType = improvementType;
    }

    public ArrayList<ImprovementTypes> getImprovementTypes() {
        return improvementTypes;
    }

    public void setImprovementTypes(ArrayList<ImprovementTypes> improvementTypes) {
        this.improvementTypes = improvementTypes;
    }

    public NonCombatUnit getNonCombatUnit() {
        return nonCombatUnit;
    }

    public void setNonCombatUnit(NonCombatUnit nonCombatUnit) {
        this.nonCombatUnit = nonCombatUnit;
    }

    public CombatUnit getCombatUnit() {
        return combatUnit;
    }

    public void setCombatUnit(CombatUnit combatUnit) {
        this.combatUnit = combatUnit;
    }

    public ArrayList<TechnologyTypes> getTechnologyTypesArrayList() {
        return technologyTypesArrayList;
    }

    public void setTechnologyTypesArrayList(ArrayList<TechnologyTypes> technologyTypesArrayList) {
        this.technologyTypesArrayList = technologyTypesArrayList;
    }

    public TechnologyTypes getTechnologyTypes() {
        return technologyTypes;
    }

    public void setTechnologyTypes(TechnologyTypes technologyTypes) {
        this.technologyTypes = technologyTypes;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<privateChat> getPrivateChats() {
        return privateChats;
    }

    public void setPrivateChats(ArrayList<privateChat> privateChats) {
        this.privateChats = privateChats;
    }


    public privateChat getPrivateChat() {
        return privateChat;
    }

    public void setPrivateChat(com.example.civilization.Model.GlobalChats.privateChat privateChat) {
        this.privateChat = privateChat;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    private Room room;
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    private Message message;

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
    public void addRequest(String action,User user){
       this.action = action;
       this.user = user;
    }
    public void setNickname(String nick){
        this.nickname = nick;
    }

    public String getNickname(){
        return  this.nickname;
    }

    public void setPassword(String pass){
        this.password = pass;
    }

    public String getPassword(){
        return  this.password;
    }
    public String getAction(){
        return this.action;
    }
    public User getUser(){
        return this.user;
    }
}
