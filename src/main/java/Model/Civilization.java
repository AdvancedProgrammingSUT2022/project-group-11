package Model;

import java.util.ArrayList;

import Model.Units.Unit;

public class Civilization {

  // private ArrayList<City> cities;
  private ArrayList<Tile> tiles;
  private ArrayList<Unit> units;
  private int gold;
  private int happiness;
  private String name;

  /*
   * public Unit findUnit(int x, int y) {
   * for (Unit unit : this.units) {
   * if (unit.getX() == x && unit.getY() == y) {
   * return unit;
   * }
   * }
   * return null;
   * }
   */
  public String getName() {
    return this.name;
  }
  public ArrayList<Tile> getTiles(){
    return this.tiles;
  }
}
