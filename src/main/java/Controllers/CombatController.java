package Controllers;

import Model.City.City;
import Model.Terrain;
import Model.Units.CombatUnit;
import Model.Units.NonRangedCombatUnit;
import Model.Units.RangedCombatUnit;
import Model.User;

import java.util.regex.Matcher;

public class CombatController

{
    private DatabaseController databaseController;
    private CityController cityController;
    public CombatController ( DatabaseController databaseController, CityController cityController)
    {
        this.cityController = cityController;
        this.databaseController = databaseController;

    }

    public String unitAttackCity( Matcher matcher, User user)
    {
        int x = Integer.parseInt(matcher.group("X"));
        int y = Integer.parseInt(matcher.group("Y"));
        Terrain terrain = this.databaseController.getTerrainByCoordinates(x, y);
        City city = terrain.getCity();
        if ( city == null)
        {
            return "There is no city in the tile you selected";
        }

        if ( user.getCivilization().equals(city.getOwner()))
        {
            return "You cannot attack your own city";
        }

        CombatUnit combatUnit = this.databaseController.getSelectedCombatUnit();
        if ( combatUnit instanceof NonRangedCombatUnit && !city.getMainTerrains().contains(this.databaseController.getTerrainByCoordinates(combatUnit.getX(), combatUnit.getY())))
        {
            return "You have to enter the city first";
        }
        if ( combatUnit instanceof NonRangedCombatUnit)
        {
            if (cityController.oneCombatTurn(city, combatUnit))
            {
                return "You won.The city is yours. Do you wish to destroy it or make it yours?";
            }
            else
                return "You played one turn in combat. Please attack the city next turn again if you wish to continue.";
        }
        if ( combatUnit instanceof RangedCombatUnit)
        {
            return "This unit is a ranged combat unit. Please initialize a ranged attack.";
        }
        return "Error";
    }
}
