package Model.Units;

public class Unit {
    private UnitTypes unitType;
    private int x,y;
    private int number;
    private int militaryPower; 
    private boolean isSelected;

    public Unit(int x, int y, int number, int militaryPower, boolean isSelected,UnitTypes unittype) {
        this.unitType = unittype;
        this.x = x;
        this.y = y;
        this.number = number;
        this.militaryPower = militaryPower;
        this.isSelected = isSelected;
    }
         

    public boolean isIsSelected() {
        return this.isSelected;
    }

    public boolean getIsSelected() {
        return this.isSelected;
    }

    public UnitTypes getUnitType() {
        return this.unitType;
    }

    public void setUnitType(UnitTypes unitType) {
        this.unitType = unitType;
    }
   

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getX() {
        return this.x;
    }

    public void setXAndY(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return this.y;
    }
   
    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMilitaryPower() {
        return this.militaryPower;
    }

    public void setMilitaryPower(int militaryPower) {
        this.militaryPower = militaryPower;
    }

   
   
}
