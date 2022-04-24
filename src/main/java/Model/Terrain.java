package Model;

import java.util.ArrayList;

import Model.Improvements.Improvements;
import Model.Resources.ResourceTypes;
import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;
import Model.Units.CombatUnit;
import Model.Units.NonCombatUnit;

public class Terrain {

    private int x;
    private int y;
    private String Type;
    private TerrainTypes terrainTypes;
    private TerrainFeatureTypes terrainFeatureTypes;
    private CombatUnit combatUnit;
    private NonCombatUnit nonCombatUnit;
    private Improvements TerrrainImprovement;
    private ResourceTypes TerrainResource;
    private ArrayList<Revealed> reveals;

    public Terrain(int x, int y, String Type, TerrainTypes terrainTypes, TerrainFeatureTypes terrainFeatureTypes,
            CombatUnit combatUnit, NonCombatUnit nonCombatUnit, Improvements TerrrainImprovement,
            ResourceTypes TerrainResource, ArrayList<Revealed> reveals) {
        this.x = x;
        this.y = y;
        this.Type = Type;
        this.terrainTypes = terrainTypes;
        this.terrainFeatureTypes = terrainFeatureTypes;
        this.combatUnit = combatUnit;
        this.nonCombatUnit = nonCombatUnit;
        this.TerrrainImprovement = TerrrainImprovement;
        this.TerrainResource = TerrainResource;
        this.reveals = reveals;
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

    public TerrainFeatureTypes getTerrainFeatureTypes() {
        return this.terrainFeatureTypes;
    }

    public void setTerrainFeatureTypes(TerrainFeatureTypes terrainFeatureTypes) {
        this.terrainFeatureTypes = terrainFeatureTypes;
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

    public Improvements getTerrrainImprovement() {
        return this.TerrrainImprovement;
    }

    public void setTerrrainImprovement(Improvements TerrrainImprovement) {
        this.TerrrainImprovement = TerrrainImprovement;
    }

    public ResourceTypes getTerrainResource() {
        return this.TerrainResource;
    }

    public void setTerrainResource(ResourceTypes TerrainResource) {
        this.TerrainResource = TerrainResource;
    }

    public ArrayList<Revealed> getReveals() {
        return this.reveals;
    }

    public void setReveals(ArrayList<Revealed> reveals) {
        this.reveals = reveals;
    }

}