package Model;

import java.util.ArrayList;

import Model.City.City;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyTypes;
import Model.Units.CombatUnit;
import Model.Units.NonCombatUnit;
import Model.Units.Unit;

public class Civilization {

    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<City> cities = new ArrayList<>();
    private ArrayList<Terrain> ownedTerrains = new ArrayList<>();
    private ArrayList<Terrain> terrains = new ArrayList<>();
    private ArrayList<Terrain> visibleTerrains = new ArrayList<>();
    private ArrayList<Terrain> revealedTerrains = new ArrayList<>();
    private int gold;
    private int happiness;
    private ArrayList<Technology> technologies = new ArrayList<>();
    private ArrayList<TechnologyTypes> technologyTypes = new ArrayList<>();
    private String name;
    private int science;
    private boolean canBuySettler;

    public Civilization(int gold, int happiness, String name) {

        this.gold = gold;
        this.happiness = happiness;
        this.name = name;
    }

    public void setBooleanSettlerBuy(boolean bool){
        this.canBuySettler = bool;
    }
    public boolean getBooleanSettlerBuy(){
        return this.canBuySettler;
    }
    public void setOwnedTerrains( ArrayList<Terrain> ownedTerrains){
        this.ownedTerrains = ownedTerrains;
    }
    public  ArrayList<Terrain> getOwnedTerrains(){
        return this.ownedTerrains;
    }
    public ArrayList<Terrain> getVisibleTerrains() {
        return this.visibleTerrains;
    }

    public void setVisibleTerrains(ArrayList<Terrain> visibleTerrains) {
        this.visibleTerrains = visibleTerrains;
    }

    public ArrayList<Terrain> getRevealedTerrains() {
        return this.revealedTerrains;
    }

    public void setRevealedTerrains(ArrayList<Terrain> revealedTerrains) {
        this.revealedTerrains = revealedTerrains;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Terrain> getTerrains() {
        return this.terrains;
    }

    public ArrayList<Unit> getUnits() {
        return this.units;
    }

    public void setUnits(ArrayList<Unit> unit) {
        this.units = unit;
    }

    public ArrayList<City> getCities() {
        return this.cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public void setTerrains(ArrayList<Terrain> terrains) {
        this.terrains = terrains;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHappiness() {
        return this.happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public boolean containsUnit(Unit unit2) {
        for (Unit unit : units) {
            if (unit.equals(unit2)) {
                return true;
            }

        }
        return false;
    }

    public boolean containsCombatUnit(int x, int y) {
        for (Unit unit : units) {
            if (unit instanceof CombatUnit) {
                if (unit.getX() == x && unit.getY() == y) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean containsNonCombatUnit(int x, int y) {
        for (Unit unit : units) {
            if (unit instanceof NonCombatUnit) {
                if (unit.getX() == x && unit.getY() == y) {
                    return true;
                }
            }

        }
        return false;
    }

    public ArrayList<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(ArrayList<Technology> technologies) {
        this.technologies = technologies;
    }

    public void setAvailablity() {
        for (Technology technology : this.technologies) {
            if (!technology.getIsAvailable()) {
                technology.setCostsForResearch(technology.getCostsForResearch() + 10);
                if (technology.getCostsForResearch() >= technology.getTechnologyType().getCost()) {
                    technology.setIsAvailable(true);
                }
            }
        }
    }

    public void increaseGold(int gold){
        this.gold += gold;
    }



    public void addUnit( Unit unit)
    {
        this.units.add(unit);
    }





    public void addCity ( City city)
    {
        this.cities.add(city);
    }

    public void removeUnit( Unit unit)
    {
        for (Unit testUnit : this.units)
        {
            if (testUnit.equals(unit))
            {
                this.units.remove(testUnit);
                return;
            }

        }
    }

    public int getScience()
    {
        return science;
    }

    public void setScience(int science)
    {
        this.science = science;
    }

    public void removeCity(City city )
    {
        for ( City cityTest : this.cities)
        {
            if ( cityTest.equals(city))
            {
                this.cities.remove(city);
                return;
            }

        }
    }

    public ArrayList<TechnologyTypes> getTechnologyTypes()
    {
        return technologyTypes;
    }

    public void setTechnologyTypes(ArrayList<TechnologyTypes> technologyTypes)
    {
        this.technologyTypes = technologyTypes;
    }



}