package Model;

import java.util.ArrayList;

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
    private ArrayList<Revealed> reveals;
    

    Terrain(int x, int y, String Type, TerrainTypes terrainTypes, TerrainFeatureTypes terrainFeatureTypes, CombatUnit combatUnit,NonCombatUnit nonCombatUnit,ArrayList<Revealed> reveals) {
        this.x = x;
        this.y = y;
        this.Type = Type;
        this.terrainTypes = terrainTypes;
        this.terrainFeatureTypes = terrainFeatureTypes;
        this.combatUnit = combatUnit;
        this.nonCombatUnit = nonCombatUnit;
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
   
    

   
}