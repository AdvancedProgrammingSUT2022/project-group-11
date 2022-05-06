package Model;

import java.util.ArrayList;

import Model.City.City;
import Model.Improvements.Improvements;
import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;
import Model.Units.CombatUnit;
import Model.Units.NonCombatUnit;
import Model.Units.Unit;

public class Terrain {

    private final ArrayList<TerrainFeatureTypes> terrainFeatureTypes;
    private boolean isBeingWorkedOn;
    private int x;
    private int y;
    private String Type;
    private TerrainTypes terrainTypes;
    private CombatUnit combatUnit;
    private NonCombatUnit nonCombatUnit;
    private Improvements TerrrainImprovement;
    private Resource TerrainResource;
    private boolean unlockResource;
    private ArrayList<Revealed> reveals;
    private City city;

    public Terrain(int x, int y, String Type, TerrainTypes terrainTypes,
            ArrayList<TerrainFeatureTypes> terrainFeatureTypes,
            CombatUnit combatUnit, NonCombatUnit nonCombatUnit, Improvements TerrrainImprovement,
            Resource TerrainResource, ArrayList<Revealed> reveals) {

        this.x = x;
        this.y = y;
        this.Type = Type;
        this.terrainTypes = terrainTypes;
        this.terrainFeatureTypes = terrainFeatureTypes;
        this.combatUnit = combatUnit;
        this.nonCombatUnit = nonCombatUnit;

        this.isBeingWorkedOn = false;

        this.TerrrainImprovement = TerrrainImprovement;
        this.TerrainResource = TerrainResource;
        this.reveals = reveals;
        // this.city = null;
    }

    public void setBooleanResource(boolean bool) {
        this.unlockResource = bool;
    }

    public boolean getBooleanResource() {
        return this.unlockResource;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getType() {
        return this.Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public TerrainTypes getTerrainTypes() {
        return this.terrainTypes;
    }

    public void setTerrainTypes(TerrainTypes terrainTypes) {
        this.terrainTypes = terrainTypes;
    }

    public ArrayList<TerrainFeatureTypes> getTerrainFeatureTypes() {
        return this.terrainFeatureTypes;
    }

    public void setTerrainFeatureTypes(TerrainFeatureTypes terrainFeatureTypes) {
        this.terrainFeatureTypes.add(terrainFeatureTypes);
    }

    public CombatUnit getCombatUnit() {
        return this.combatUnit;
    }

    public void setCombatUnit(CombatUnit combatUnit) {
        this.combatUnit = combatUnit;
    }

    public NonCombatUnit getNonCombatUnit() {
        return this.nonCombatUnit;
    }

    public void setNonCombatUnit(NonCombatUnit nonCombatUnit) {
        this.nonCombatUnit = nonCombatUnit;
    }

    public Resource getResource() {
        return TerrainResource;
    }

    public void setTerrrainImprovement(Improvements TerrrainImprovement) {
        this.TerrrainImprovement = TerrrainImprovement;
    }

    public Resource getTerrainResource() {
        return this.TerrainResource;
    }

    public void setTerrainResource(Resource TerrainResource) {
        this.TerrainResource = TerrainResource;
    }

    public ArrayList<Revealed> getReveals() {
        return this.reveals;
    }

    public void setReveals(Revealed reveals) {
        this.reveals.add(reveals);
    }

    public void setRevealedTest(ArrayList<Revealed> reveals) {
        this.reveals = reveals;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public boolean containsUnit(Unit unit2) {
        return unit2.equals(combatUnit) || unit2.equals(nonCombatUnit);
    }

    public boolean isBeingWorkedOn() {
        return isBeingWorkedOn;
    }

    public void setBeingWorkedOn(boolean beingWorkedOn) {
        isBeingWorkedOn = beingWorkedOn;
    }

    public int getGold() {
        if (!this.isBeingWorkedOn) {
            return 0;
        } else {

        }
        return 0;
    }

    public Improvements getTerrrainImprovement() {
        return this.TerrrainImprovement;
    }

}