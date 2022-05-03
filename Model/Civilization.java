package Model;

import java.util.ArrayList;

import Model.City.City;
import Model.Technologies.TechnologyTypes;
import Model.Units.Unit;

public class Civilization {
    private ArrayList<Unit> units;
    private ArrayList<City> cities;
    private ArrayList<Terrain> terrains;
    private int gold;
    private int happiness;
    private ArrayList<TechnologyTypes> technologies;
    private int science;

    public Civilization(ArrayList<Unit> units, ArrayList<City> cities, ArrayList<Terrain> terrains, int gold, int happiness) {
        this.units = units;
        this.cities = cities;
        this.terrains = terrains;
        this.gold = gold;
        this.happiness = happiness;
    }


    public ArrayList<Unit> getUnits() {
        return this.units;
    }

    public void setUnits(ArrayList<Unit> unit) {
        this.units = unit;
    }

    public void addUnit( Unit unit)
    {
        this.units.add(unit);
    }

    public ArrayList<City> getCities() {
        return this.cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<Terrain> getTerrains() {
        return this.terrains;
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

    public boolean containsUnit(Unit unit2)
    {
        for(Unit unit : units)
        {
            if(unit.equals(unit2))
            {
                return true;
            }
        }
        return false;
    }


    public ArrayList<TechnologyTypes> getTechnologies()
    {
        return technologies;
    }

    public void setTechnologies(ArrayList<TechnologyTypes> technologies)
    {
        this.technologies = technologies;
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
}
