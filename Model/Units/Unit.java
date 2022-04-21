package Model.Units;

import Model.Terrain;

public class Unit {
    private int x,y;
    private int number;
    private int militaryPower; 
    private int life; 
    private int speed; 
    private boolean isAsleep;
    private boolean alert;
    private boolean fortify;
    private boolean fortifyUntilHeal;
    public void move(){

    }
    public void combat(Terrain destination){

    }
    public void fortify(){

    }
    public void fortifyUntilHeal(){

    }
    public void garrison(){

    }
    public void setUpForRangedAttack(){

    }

    public void rangedAttack(){

    }
    public void pillage(Terrain terrain){

    }
    public void foundCity(){

    }
    public void cancelCommand(){

    }
    public void wakeUp(){

    }
    public void deleteUnit(){
        
    }

    private UnitTypes unitType;
    private boolean isSelected;


    public Unit(int x, int y, int number, int militaryPower, int life, int speed, boolean isAsleep, boolean alert, boolean fortify, boolean fortifyUntilHeal, UnitTypes unitType, boolean isSelected) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.militaryPower = militaryPower;
        this.life = life;
        this.speed = speed;
        this.isAsleep = isAsleep;
        this.alert = alert;
        this.fortify = fortify;
        this.fortifyUntilHeal = fortifyUntilHeal;
        this.unitType = unitType;
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
