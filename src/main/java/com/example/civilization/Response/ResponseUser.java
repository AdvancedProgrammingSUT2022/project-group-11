package com.example.civilization.Response;

import com.example.civilization.Model.*;
import com.example.civilization.Model.Buildings.BuildingTypes;
import com.example.civilization.Model.City.Citizen;
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
    private privateChat privateChat;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<privateChat> privateChats = new ArrayList<>();
    private TechnologyTypes technologyTypes;
    private ArrayList<TechnologyTypes> technologyTypesArrayList;
    private NonCombatUnit nonCombatUnit;
    private CombatUnit combatUnit;
    private ArrayList<ImprovementTypes> improvementTypes;
    private ImprovementTypes improvementType;
    private Civilization civilization;
    private City city;
    private Civilization secondCivilization;
    private City secondCity;

    private Citizen citizen;

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }
    private ArrayList<BuildingTypes> buildingTypes = new ArrayList<>();

    public ArrayList<BuildingTypes> getBuildingTypes() {
        return buildingTypes;
    }

    public void setBuildingTypes(ArrayList<BuildingTypes> buildingTypes) {
        this.buildingTypes = buildingTypes;
    }
    public City getSecondCity() {
        return secondCity;
    }

    public void setSecondCity(City secondCity) {
        this.secondCity = secondCity;
    }
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
    private ArrayList<Message> allMessagePublic = new ArrayList<>();

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
