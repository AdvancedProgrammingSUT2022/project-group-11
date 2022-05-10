package Model.Units;

import java.util.ArrayList;

import Model.Terrain;

public class Unit {
    private int x,y;
    private int number;
    private int militaryPower; 
    private int life; 

    private int speed; 
    private boolean isAsleep;
    private boolean isFinished;
    private ArrayList<Terrain> nextTerrain = new ArrayList<>();
    private UnitTypes unitType;
    private boolean isSelected;


    public Unit(int x, int y, int number, int militaryPower, int life, int speed, boolean isAsleep, boolean isFinished, UnitTypes unitType, boolean isSelected) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.militaryPower = militaryPower;
        this.life = life;
        this.speed = speed;
        this.isAsleep = isAsleep;
        this.isFinished = isFinished;
        this.unitType = unitType;
        this.isSelected = isSelected;
    }


    public ArrayList<Terrain> getNextTerrain() {
        return this.nextTerrain;
    }

    public void setNextTerrain(ArrayList<Terrain> nextTiles) {
        this.nextTerrain = nextTiles;
    }



    public boolean isIsFinished() {
        return this.isFinished;
    }

    public boolean getIsFinished() {
        return this.isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
    




    public void move(){

    }
    public void combat(Terrain destination){

    }
    public void fortify(){

    }
    public void sleep()
    {

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



    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }


    public int getLife() {
        return this.life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isIsAsleep() {
        return this.isAsleep;
    }

    public boolean getIsAsleep() {
        return this.isAsleep;
    }

    public void setIsAsleep(boolean isAsleep) {
        this.isAsleep = isAsleep;
    }

   

    @Override
    public String toString() {
        return "{" +
            " x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            ", number='" + getNumber() + "'" +
            ", militaryPower='" + getMilitaryPower() + "'" +
            ", life='" + getLife() + "'" +
            ", speed='" + getSpeed() + "'" +
            ", isAsleep='" + isIsAsleep() + "'" +
            ", unitType='" + getUnitType() + "'" +
            "}";
    }

    

}