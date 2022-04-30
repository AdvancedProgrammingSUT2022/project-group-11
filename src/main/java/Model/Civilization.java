package Model;

import java.util.ArrayList;

import Model.Technologies.Technology;

import Model.Units.Unit;

public class Civilization {

  private ArrayList<Unit> units;
  private ArrayList<City> cities;
  private ArrayList<Terrain> terrains;
  private ArrayList<Terrain> visibleTerrains;
  private ArrayList<Terrain> revealedTerrains;
  private int gold;
  private int happiness;
  private ArrayList<Technology> technologies;
  String name;
  

  public Civilization(ArrayList<Unit> units, ArrayList<City> cities, ArrayList<Terrain> terrains, int gold, int happiness, ArrayList<Technology> technologies, String name) {
    this.units = units;
    this.cities = cities;
    this.terrains = terrains;
    this.gold = gold;
    this.happiness = happiness;
    this.technologies = technologies;
    this.name = name;
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


public ArrayList<Technology> getTechnologies()
{
    return technologies;
}

public void setTechnologies(ArrayList<Technology> technologies)
{
    this.technologies = technologies;
}

public void setAvailablity()
{
    for(Technology technology : this.technologies)
    {
        if(technology.getIsAvailabe() == false)
        {
            technology.setCostsForResearch(technology.getCostsForResearch()+ 10);
            if(technology.getCostsForResearch() >= technology.getTechnologyType().getCost())
            {
                technology.setIsAvailabe(true);
            }
        }
    }
}


}
