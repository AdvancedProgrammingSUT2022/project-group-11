package Model;

import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;
import Model.Units.CombatUnit;
import Model.Units.NonCombatUnit;

public class Revealed{

    private String userName;
    private TerrainTypes terrainTypes;
    private TerrainFeatureTypes terrainFeatureTypes;
    private CombatUnit combatUnit;
    private NonCombatUnit nonCombatUnit;

    

    public Revealed(String userName, TerrainTypes terrainTypes, TerrainFeatureTypes terrainFeatureTypes, CombatUnit combatUnit, NonCombatUnit nonCombatUnit) {
        this.userName = userName;
        this.terrainTypes = terrainTypes;
        this.terrainFeatureTypes = terrainFeatureTypes;
        this.combatUnit = combatUnit;
        this.nonCombatUnit = nonCombatUnit;
    }

    

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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