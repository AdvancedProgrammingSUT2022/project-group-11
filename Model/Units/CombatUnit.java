package Model.Units;

public class CombatUnit extends Unit {

    public CombatUnit(int x, int y, int number, int militaryPower, int life, int speed, boolean isAsleep,
            boolean isFinished, UnitTypes unitType, boolean isSelected, boolean isGarrisoned, boolean alert,
            boolean fortify, boolean fortifyUntilHeal) {
        super(x, y, number, militaryPower, life, speed, isAsleep, isFinished, unitType, isSelected);
        // TODO Auto-generated constructor stub
        this.isGarrisoned = isGarrisoned;
        this.alert = alert;
        this.fortify = fortify;
        this.fortifyUntilHeal = fortifyUntilHeal;
    }

    private boolean isGarrisoned;
    private boolean alert;
    private boolean fortify;
    private boolean fortifyUntilHeal;

    public boolean isIsGarrisoned() {
        return this.isGarrisoned;
    }

    public boolean getIsGarrisoned() {
        return this.isGarrisoned;
    }

    public void setIsGarrisoned(boolean isGarrisoned) {
        this.isGarrisoned = isGarrisoned;
    }

    public boolean isAlert() {
        return this.alert;
    }

    public boolean getAlert() {
        return this.alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public boolean isFortify() {
        return this.fortify;
    }

    public boolean getFortify() {
        return this.fortify;
    }

    public void setFortify(boolean fortify) {
        this.fortify = fortify;
    }

    public boolean isFortifyUntilHeal() {
        return this.fortifyUntilHeal;
    }

    public boolean getFortifyUntilHeal() {
        return this.fortifyUntilHeal;
    }

    public void setFortifyUntilHeal(boolean fortifyUntilHeal) {
        this.fortifyUntilHeal = fortifyUntilHeal;
    }

}
