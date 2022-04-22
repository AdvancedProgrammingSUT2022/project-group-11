package Model.Units;

public class RangedCombatUnit extends CombatUnit{

    private boolean isSetUpForRangedAttack;


    public RangedCombatUnit(int x, int y, int number, int militaryPower, int life, int speed, boolean isAsleep,
            UnitTypes unitType, boolean isSelected, boolean isGarrisoned, boolean alert, boolean fortify,
            boolean fortifyUntilHeal, boolean isSetUpForRangedAttack) {
        super(x, y, number, militaryPower, life, speed, isAsleep, unitType, isSelected, isGarrisoned, alert, fortify,
                fortifyUntilHeal);
                this.isSetUpForRangedAttack = isSetUpForRangedAttack;
        //TODO Auto-generated constructor stub
    }


    public boolean isIsSetUpForRangedAttack() {
        return this.isSetUpForRangedAttack;
    }

    public boolean getIsSetUpForRangedAttack() {
        return this.isSetUpForRangedAttack;
    }

    public void setIsSetUpForRangedAttack(boolean isSetUpForRangedAttack) {
        this.isSetUpForRangedAttack = isSetUpForRangedAttack;
    }
    
}
