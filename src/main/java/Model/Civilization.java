package Model;

import java.util.ArrayList;

import Model.Units.Unit;

public class Civilization {

  // private ArrayList<City> cities;
  private ArrayList<Terrain> Terrains;
  private ArrayList<Unit> units;
  private int gold;
  private int happiness;
  private String name;


  public Civilization(ArrayList<Terrain> Terrains, ArrayList<Unit> units, int gold, int happiness, String name) {
    this.Terrains = Terrains;
    this.units = units;
    this.gold = gold;
    this.happiness = happiness;
    this.name = name;
  }
 
  public String getName() {
    return this.name;
  }

  public ArrayList<Terrain> getTerrains() {
    return this.Terrains;
  }
}
