package Model;

import java.util.ArrayList;

public class City{
    private final Civilization founder;
    private Civilization owner;
    private boolean isPuppet;
    private final Terrain centralTerrain;
    private int HP;
    private String type;
    private int combatStrength;
    private boolean iConstructingUnit;
    private int rangedCombatStrength;
    private boolean garrisoned;
    private boolean isCaptured;
    private ArrayList<Citizen> citizens;


    public City(Civilization founder, Civilization owner, boolean isPuppet, Terrain centralTerrain, int HP, String type, int combatStrength, boolean iConstructingUnit, int rangedCombatStrength, boolean garrisoned, boolean isCaptured, ArrayList<Citizen> citizens) {
        this.founder = founder;
        this.owner = owner;
        this.isPuppet = isPuppet;
        this.centralTerrain = centralTerrain;
        this.HP = HP;
        this.type = type;
        this.combatStrength = combatStrength;
        this.iConstructingUnit = iConstructingUnit;
        this.rangedCombatStrength = rangedCombatStrength;
        this.garrisoned = garrisoned;
        this.isCaptured = isCaptured;
        this.citizens = citizens;
    }


    public Civilization getFounder() {
        return this.founder;
    }


    public Civilization getOwner() {
        return this.owner;
    }

    public void setOwner(Civilization owner) {
        this.owner = owner;
    }

    public boolean isIsPuppet() {
        return this.isPuppet;
    }

    public boolean getIsPuppet() {
        return this.isPuppet;
    }

    public void setIsPuppet(boolean isPuppet) {
        this.isPuppet = isPuppet;
    }

    public Terrain getCentralTerrain() {
        return this.centralTerrain;
    }



    public int getHP() {
        return this.HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCombatStrength() {
        return this.combatStrength;
    }

    public void setCombatStrength(int combatStrength) {
        this.combatStrength = combatStrength;
    }

    public boolean isIConstructingUnit() {
        return this.iConstructingUnit;
    }

    public boolean getIConstructingUnit() {
        return this.iConstructingUnit;
    }

    public void setIConstructingUnit(boolean iConstructingUnit) {
        this.iConstructingUnit = iConstructingUnit;
    }

    public int getRangedCombatStrength() {
        return this.rangedCombatStrength;
    }

    public void setRangedCombatStrength(int rangedCombatStrength) {
        this.rangedCombatStrength = rangedCombatStrength;
    }

    public boolean isGarrisoned() {
        return this.garrisoned;
    }

    public boolean getGarrisoned() {
        return this.garrisoned;
    }

    public void setGarrisoned(boolean garrisoned) {
        this.garrisoned = garrisoned;
    }

    public boolean isIsCaptured() {
        return this.isCaptured;
    }

    public boolean getIsCaptured() {
        return this.isCaptured;
    }

    public void setIsCaptured(boolean isCaptured) {
        this.isCaptured = isCaptured;
    }

    public ArrayList<Citizen> getCitizens() {
        return this.citizens;
    }

    public void setCitizens(ArrayList<Citizen> citizens) {
        this.citizens = citizens;
    }

    public City HP(int HP) {
        setHP(HP);
        return this;
    }

    public City type(String type) {
        setType(type);
        return this;
    }

    public City combatStrength(int combatStrength) {
        setCombatStrength(combatStrength);
        return this;
    }

    public City iConstructingUnit(boolean iConstructingUnit) {
        setIConstructingUnit(iConstructingUnit);
        return this;
    }

    public City rangedCombatStrength(int rangedCombatStrength) {
        setRangedCombatStrength(rangedCombatStrength);
        return this;
    }

    public City garrisoned(boolean garrisoned) {
        setGarrisoned(garrisoned);
        return this;
    }

    public City isCaptured(boolean isCaptured) {
        setIsCaptured(isCaptured);
        return this;
    }

    public City citizens(ArrayList<Citizen> citizens) {
        setCitizens(citizens);
        return this;
    }

   
}
