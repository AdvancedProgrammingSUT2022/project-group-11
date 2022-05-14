package Controllers;

import Model.City.Citizen;
import Model.City.City;
import Model.Civilization;
import Model.Map;
import Model.Resources.ResourceTypes;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyTypes;
import Model.Terrain;
import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Units.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

public class CityController {

    private DatabaseController databaseController;
    private Map map;

    public void setDatabaseController(DatabaseController databaseController){
        this.databaseController = databaseController;
    }
    public void setMap(Map map){
        this.map = map;
    }
    public void garrison(City city, CombatUnit combatUnit) {
        if (city.getCombatUnit() == null) {
            city.setCombatUnit(combatUnit);
            city.setCombatStrength(100); // adding combatStrength

        } else {
            // this city already contains a combat unit
        }

    }

    public void foundCity(Civilization civilization, NonCombatUnit unit, Terrain tile)
    {
        if ( unit == null)
        {
            System.out.println("please select a unit first");
            return;
        }
        if (unit.getUnitType().equals(UnitTypes.SETTLER)) {
            if (tile.getCity() != null)
            {
                System.out.println("error : city already exists");
                return;
            }
            City newCity = new City(civilization, civilization, tile, 20, "none", 0, 0);
            tile.setCity(newCity);
            civilization.addCity(newCity);
            civilization.removeUnit((Unit) unit);
            tile.setNonCombatUnit(null);
        }
    }

    public void playTurn(City city) {

        // update food
        city.setNeighbors( NeighborsAtADistanceOfOneFromAnArraylistOfTerrains(city.getMainTerrains(), databaseController.getMap()));
        int foodIncrease = 0;
        int goldIncrease = 0;
        int productionIncrease = 0;
        for (Terrain tile : city.getNeighbors()) {
            if (tile.getResource() != null) {foodIncrease += tile.getResource().getFood();
            goldIncrease += tile.getResource().getGold();
                productionIncrease += tile.getResource().getProduction(); }
            goldIncrease += tile.getGold();
        }
        goldIncrease += calculateCityGold(city);
        foodIncrease += calculateCityFood(city);
        for (Citizen citizen : city.getCitizens()) {
            productionIncrease += citizen.getProduction();
        }
        foodIncrease -= city.getPopulation() * 2;
        if (foodIncrease > 0) // creating Citizens
        {
            city.setFood(city.getFood() + foodIncrease);
            if (city.getFood() > 20) {
                Citizen newCitizen = new Citizen(city);
                city.addCitizen(newCitizen);
                city.setFood(city.getFoodStorage() - 20);
            }
        } else if (foodIncrease < 0) // Killing Citizens
        {
            foodIncrease = -foodIncrease;
            int numberOfDyingCitizens = foodIncrease / 2;
            for (int i = 0; i < numberOfDyingCitizens; i++) {
                city.removeCitiZen(i);
            }

        }
        city.setGold( city.getGold() + goldIncrease);
        city.setProduction(city.getProduction() + productionIncrease);
        // update constructions

    }

    private int calculateCityFood( City city)
    {
        int foodIncrease = 0;
        for (Terrain terrain : city.getMainTerrains())
        {
            foodIncrease += terrain.getTerrainTypes().getFood();
            if ( terrain.getTerrainFeatureTypes() != null)
            {
                for ( TerrainFeatureTypes terrainFeatureTypes : terrain.getTerrainFeatureTypes())
                {
                    foodIncrease += terrainFeatureTypes.getFood();

                }
            }
        }
        return foodIncrease;
    }

    private int calculateCityGold( City city)
    {
        int goldIncrease = 0;
        for (Terrain terrain : city.getMainTerrains())
        {
            goldIncrease += terrain.getTerrainTypes().getGold();
            if ( terrain.getTerrainFeatureTypes() != null)
            {
                for ( TerrainFeatureTypes terrainFeatureTypes : terrain.getTerrainFeatureTypes())
                {
                    goldIncrease += terrainFeatureTypes.getGold();

                }
            }
        }
        return goldIncrease;
    }

    public void destroyCity(Civilization destroyer, Civilization loser, City city) {
        loser.removeCity(city);
        destroyer.setHappiness(destroyer.getHappiness() + 100);
        // handle the tile itself


    }


    public void attachCity(Civilization civilization, City city) {
        civilization.addCity(city);
        city.setOwner(civilization);
        civilization.setHappiness(civilization.getHappiness() - 10000);

    }

    public HashMap<String, String> cityOutput(City city) {

        HashMap<String, String> output = new HashMap<>();
        output.put("food", String.valueOf(city.getFood()));
        output.put("production", String.valueOf(city.getProduction()));
        output.put("gold", String.valueOf(city.getGold()));
        output.put("turns remaining until population increase", String.valueOf(city.getTurnsRemainingUntilPopulationIncrease()));
        return output;

    }



    public boolean containUnit(ArrayList<Technology> tech,TechnologyTypes technologyType){
        for(int i = 0; i < tech.size();i++){
            if(tech.get(i).getTechnologyType() == technologyType){
                return true;
            }
        }
        return false;
    }


    public HashMap<String, ArrayList<String>> cityMenu(City city)
    {
        HashMap<String, ArrayList<String>> ans = new HashMap<>();
        Civilization civilization = city.getOwner();
        ArrayList<String > units = new ArrayList<>();
        this.databaseController.setTechnologyTypes(civilization);
        ArrayList<TechnologyTypes> technologyTypes = civilization.getTechnologyTypes();
        for ( UnitTypes unitType : UnitTypes.values() )
        {
            if (technologyTypes.contains( unitType.getTechnologyRequirements() ))
            {
                units.add("name: " + unitType.name() + " turn: " + String.valueOf(unitType.getTurn()));
            }
        }
        ans.put("Units ", units);

        return ans;
    }


    public String createUnitWithTurn(Matcher matcher, City city) {
        Civilization civilization = city.getOwner();
        String unitName = matcher.group("unitName");
        String notEnoughMoney = "You do not have enough gold to construct this unit";
        String lackTechnology = "You lack the required technology to construct this unit";
        String lackResources = "You lack the required resources to construct this unit";
        String unitAlreadyExists = "There is already a unit in this city";
        ArrayList<UnitTypes> rangedCombat = new ArrayList<>(List.of(UnitTypes.ARCHER, UnitTypes.CHARIOT_ARCHER, UnitTypes.CATAPULT, UnitTypes.CROSSBOWMAN, UnitTypes.TREBUCHET, UnitTypes.CANNON, UnitTypes.ARTILLERY));
        ArrayList<UnitTypes> nonRangedCombat = new ArrayList<>(List.of(UnitTypes.SCOUT, UnitTypes.SPEARMAN, UnitTypes.WARRIOR, UnitTypes.HORSESMAN, UnitTypes.SWORDSMAN, UnitTypes.KNIGHT, UnitTypes.LONGSWORDSMAN, UnitTypes.PIKEMAN, UnitTypes.CAVALRY, UnitTypes.LANCER, UnitTypes.MUSKETMAN, UnitTypes.RIFLEMAN, UnitTypes.ANTI_TANKGUN, UnitTypes.INFANTRY, UnitTypes.PANZER, UnitTypes.TANK));
        ArrayList<UnitTypes> nonCombat = new ArrayList<>(List.of(UnitTypes.SETTLER, UnitTypes.WORKER));

        for (UnitTypes unitTypes : rangedCombat) {
            if (getUnitTypeByName(unitName).equals(unitTypes)) {
                if (unitTypes.getTechnologyRequirements() != null && !containUnit(civilization.getTechnologies(), unitTypes.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCentralTerrain().getResource() != null && unitTypes.getResourceRequirements() != null && !city.getCentralTerrain().getResource().getResourceType().equals(unitTypes.getResourceRequirements())) {
                    return lackResources;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                }
                RangedCombatUnit newUnit = new RangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, unitTypes, false, false, false, false, false, false);
                city.getConstructionWaitList().add(newUnit);
            }
        }

        for (UnitTypes unitTypes : nonRangedCombat) {
            if (getUnitTypeByName(unitName).equals(unitTypes)) {
                if (unitTypes.getTechnologyRequirements() != null && !containUnit(civilization.getTechnologies(), unitTypes.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else if (city.getCentralTerrain().getResource() != null && unitTypes.getResourceRequirements() != null && !city.getCentralTerrain().getResource().getResourceType().equals(unitTypes.getResourceRequirements())) {
                    return lackResources;
                }
                NonRangedCombatUnit newUnit = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, unitTypes, false, false, false, false, false);
                city.getConstructionWaitList().add(newUnit);
            }

        }

        for (UnitTypes unitTypes : nonCombat) {
            if (getUnitTypeByName(unitName).equals(unitTypes)) {
                if (unitTypes.getTechnologyRequirements() != null && !containUnit(civilization.getTechnologies(), unitTypes.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getNonCombatUnit() != null) {
                    return unitAlreadyExists;
                } else if (city.getCentralTerrain().getResource() != null && unitTypes.getResourceRequirements() != null && !city.getCentralTerrain().getResource().getResourceType().equals(unitTypes.getResourceRequirements())) {
                    return lackResources;
                }
                if (unitTypes.equals(UnitTypes.SETTLER)) {
                    if (civilization.getBooleanSettlerBuy()) {
                        NonCombatUnit newSettler = new NonCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.SETTLER, false);
                        city.getConstructionWaitList().add(newSettler);
                    }

                } else {
                    NonCombatUnit newUnit = new NonCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, unitTypes, false);
                    city.getConstructionWaitList().add(newUnit);
                }
            }


        }


        return "invalid unit name";
    }

    public String createUnit(Matcher matcher, City city) {
        Civilization civilization = city.getOwner();
        String unitName = matcher.group("unitName");
        int money = city.getGold();
        String notEnoughMoney = "You do not have enough gold to construct this unit";
        String lackTechnology = "You lack the required technology to construct this unit";
        String lackResources = "You lack the required resources to construct this unit";
        String unitAlreadyExists = "There is already a unit in this city";
        String unitPurchasedSuccessfully = "Unit purchase was successful";

        switch (unitName) {
            case "ARCHER":
                if (money < UnitTypes.ARCHER.getCost()) {
                    return notEnoughMoney;
                } else if (!containUnit(civilization.getTechnologies(),UnitTypes.ARCHER.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    RangedCombatUnit newArcher = new RangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.ARCHER, false, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.ARCHER.getCost());
                    civilization.addUnit(newArcher);
                    city.setCombatUnit(newArcher);
                    return unitPurchasedSuccessfully;
                }

            case "CHARIOT_ARCHER":
                if (money < UnitTypes.CHARIOT_ARCHER.getCost()) {
                    return notEnoughMoney;
                } else if (!containUnit(civilization.getTechnologies(),UnitTypes.CHARIOT_ARCHER.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (!city.getCentralTerrain().getResource().getResourceType().equals(ResourceTypes.HORSES)) {
                    return lackResources;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    RangedCombatUnit newChariotArcher = new RangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.CHARIOT_ARCHER, false, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.CHARIOT_ARCHER.getCost());
                    civilization.addUnit(newChariotArcher);
                    city.setCombatUnit(newChariotArcher);
                    return unitPurchasedSuccessfully;
                }



            case "SCOUT":
                if (money < UnitTypes.SCOUT.getCost()) {
                    return notEnoughMoney;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newScout = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.SCOUT, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.SCOUT.getCost());
                    civilization.addUnit(newScout);
                    city.setCombatUnit(newScout);
                    return unitPurchasedSuccessfully;
                }


            case "SETTLER":
                if (money < UnitTypes.SETTLER.getCost()) {
                    return notEnoughMoney;
                } else if (city.getNonCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    if (civilization.getBooleanSettlerBuy() == true) {
                        NonCombatUnit newSettler = new NonCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.SETTLER, false);
                        civilization.setGold(money - UnitTypes.SETTLER.getCost());
                        civilization.addUnit(newSettler);
                        city.setNonCombatUnit(newSettler);
                        return unitPurchasedSuccessfully;
                    }

                }


            case "SPEARMAN":
                if (money < UnitTypes.SPEARMAN.getCost()) {
                    return notEnoughMoney;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newScout = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.SPEARMAN, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.SPEARMAN.getCost());
                    civilization.addUnit(newScout);
                    city.setCombatUnit(newScout);
                    return unitPurchasedSuccessfully;
                }


            case "WARRIOR":
                if (money < UnitTypes.WARRIOR.getCost()) {
                    return notEnoughMoney;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newWarrior = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.WARRIOR, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.WARRIOR.getCost());
                    civilization.addUnit(newWarrior);
                    city.setCombatUnit(newWarrior);
                    return unitPurchasedSuccessfully;
                }


            case "WORKER":
                if (money < UnitTypes.WORKER.getCost()) {
                    return notEnoughMoney;
                } else if (city.getNonCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonCombatUnit newWorker = new NonCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.WORKER, false);
                    civilization.setGold(money - UnitTypes.WORKER.getCost());
                    civilization.addUnit(newWorker);
                    city.setNonCombatUnit(newWorker);
                    return unitPurchasedSuccessfully;
                }


            case "CATAPULT":
                if (money < UnitTypes.CATAPULT.getCost()) {
                    return notEnoughMoney;
                } else if (!city.getCentralTerrain().getResource().getResourceType().equals(UnitTypes.CATAPULT.getResourceRequirements())) {
                    return lackResources;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.CATAPULT.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    RangedCombatUnit newCatapult = new RangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.CATAPULT, false, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.CATAPULT.getCost());
                    civilization.addUnit(newCatapult);
                    city.setCombatUnit(newCatapult);
                    return unitPurchasedSuccessfully;
                }


            case "HORSESMAN":
                if (money < UnitTypes.HORSESMAN.getCost()) {
                    return notEnoughMoney;
                } else if (!city.getCentralTerrain().getResource().getResourceType().equals(UnitTypes.HORSESMAN.getResourceRequirements())) {
                    return lackResources;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.HORSESMAN.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newHorsesman = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.HORSESMAN, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.HORSESMAN.getCost());
                    civilization.addUnit(newHorsesman);
                    city.setCombatUnit(newHorsesman);
                    return unitPurchasedSuccessfully;
                }


            case "SWORDSMAN":
                if (money < UnitTypes.SWORDSMAN.getCost()) {
                    return notEnoughMoney;
                } else if (!city.getCentralTerrain().getResource().getResourceType().equals(UnitTypes.SWORDSMAN.getResourceRequirements())) {
                    return lackResources;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.SWORDSMAN.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newSwordsman = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.SWORDSMAN, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.SWORDSMAN.getCost());
                    civilization.addUnit(newSwordsman);
                    city.setCombatUnit(newSwordsman);
                    return unitPurchasedSuccessfully;
                }


            case "CROSSBOWMAN":
                if (money < UnitTypes.CROSSBOWMAN.getCost()) {
                    return notEnoughMoney;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.CROSSBOWMAN.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    RangedCombatUnit newCrossbowman = new RangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.CROSSBOWMAN, false, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.CROSSBOWMAN.getCost());
                    civilization.addUnit(newCrossbowman);
                    city.setCombatUnit(newCrossbowman);
                    return unitPurchasedSuccessfully;
                }


            case "KNIGHT":
                if (money < UnitTypes.KNIGHT.getCost()) {
                    return notEnoughMoney;
                } else if (!city.getCentralTerrain().getResource().getResourceType().equals(UnitTypes.KNIGHT.getResourceRequirements())) {
                    return lackResources;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.KNIGHT.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newKnight = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.KNIGHT, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.KNIGHT.getCost());
                    civilization.addUnit(newKnight);
                    city.setCombatUnit(newKnight);
                    return unitPurchasedSuccessfully;

                }


            case "LONGSWORDSMAN":
                if (money < UnitTypes.LONGSWORDSMAN.getCost()) {
                    return notEnoughMoney;
                } else if (!city.getCentralTerrain().getResource().getResourceType().equals(UnitTypes.LONGSWORDSMAN.getResourceRequirements())) {
                    return lackResources;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.LONGSWORDSMAN.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newLong = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.LONGSWORDSMAN, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.LONGSWORDSMAN.getCost());
                    civilization.addUnit(newLong);
                    city.setCombatUnit(newLong);
                    return unitPurchasedSuccessfully;
                }


            case "PIKEMAN":
                if (money < UnitTypes.PIKEMAN.getCost()) {
                    return notEnoughMoney;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.PIKEMAN.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newPikeman = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.PIKEMAN, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.PIKEMAN.getCost());
                    civilization.addUnit(newPikeman);
                    city.setCombatUnit(newPikeman);
                    return unitPurchasedSuccessfully;
                }


            case "TREBUCHET":
                if (money < UnitTypes.TREBUCHET.getCost()) {
                    return notEnoughMoney;
                } else if (!city.getCentralTerrain().getResource().getResourceType().equals(UnitTypes.TREBUCHET.getResourceRequirements())) {
                    return lackResources;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.TREBUCHET.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    RangedCombatUnit newTrebuchet = new RangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.TREBUCHET, false, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.TREBUCHET.getCost());
                    civilization.addUnit(newTrebuchet);
                    city.setCombatUnit(newTrebuchet);
                    return unitPurchasedSuccessfully;

                }


            case "CANNON":
                if (money < UnitTypes.CANNON.getCost()) {
                    return notEnoughMoney;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.CANNON.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    RangedCombatUnit newCannon = new RangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.CANNON, false, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.CANNON.getCost());
                    civilization.addUnit(newCannon);
                    city.setCombatUnit(newCannon);
                    return unitPurchasedSuccessfully;

                }


            case "CAVALRY":
                if (money < UnitTypes.CAVALRY.getCost()) {
                    return notEnoughMoney;
                } else if (!city.getCentralTerrain().getResource().getResourceType().equals(UnitTypes.CAVALRY.getResourceRequirements())) {
                    return lackResources;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.CAVALRY.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newUnit = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.CAVALRY, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.CAVALRY.getCost());
                    civilization.addUnit(newUnit);
                    city.setCombatUnit(newUnit);
                    return unitPurchasedSuccessfully;

                }


            case "LANCER":
                if (money < UnitTypes.LANCER.getCost()) {
                    return notEnoughMoney;
                } else if (!city.getCentralTerrain().getResource().getResourceType().equals(UnitTypes.LANCER.getResourceRequirements())) {
                    return lackResources;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.LANCER.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newUnit = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.LANCER, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.LANCER.getCost());
                    civilization.addUnit(newUnit);
                    city.setCombatUnit(newUnit);
                    return unitPurchasedSuccessfully;
                }


            case "MUSKETMAN":
                if (money < UnitTypes.MUSKETMAN.getCost()) {
                    return notEnoughMoney;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.MUSKETMAN.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newUnit = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.MUSKETMAN, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.MUSKETMAN.getCost());
                    civilization.addUnit(newUnit);
                    city.setCombatUnit(newUnit);
                    return unitPurchasedSuccessfully;
                }


            case "RIFLEMAN":
                if (money < UnitTypes.RIFLEMAN.getCost()) {
                    return notEnoughMoney;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.RIFLEMAN.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newUnit = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.RIFLEMAN, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.RIFLEMAN.getCost());
                    civilization.addUnit(newUnit);
                    city.setCombatUnit(newUnit);
                    return unitPurchasedSuccessfully;

                }


            case "ANTI_TANKGUN":
                if (money < UnitTypes.ANTI_TANKGUN.getCost()) {
                    return notEnoughMoney;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.ANTI_TANKGUN.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newUnit = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.ANTI_TANKGUN, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.ANTI_TANKGUN.getCost());
                    civilization.addUnit(newUnit);
                    city.setCombatUnit(newUnit);
                    return unitPurchasedSuccessfully;
                }


            case "ARTILLERY":
                if (money < UnitTypes.ARTILLERY.getCost()) {
                    return notEnoughMoney;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.ARTILLERY.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    RangedCombatUnit newUnit = new RangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.ARTILLERY, false, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.ARTILLERY.getCost());
                    civilization.addUnit(newUnit);
                    city.setCombatUnit(newUnit);
                    return unitPurchasedSuccessfully;
                }


            case "INFANTRY":
                if (money < UnitTypes.INFANTRY.getCost()) {
                    return notEnoughMoney;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.INFANTRY.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newUnit = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.INFANTRY, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.INFANTRY.getCost());
                    civilization.addUnit(newUnit);
                    city.setCombatUnit(newUnit);
                    return unitPurchasedSuccessfully;
                }


            case "PANZER":
                if (money < UnitTypes.PANZER.getCost()) {
                    return notEnoughMoney;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.PANZER.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newUnit = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.PANZER, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.PANZER.getCost());
                    civilization.addUnit(newUnit);
                    city.setCombatUnit(newUnit);
                    return unitPurchasedSuccessfully;
                }


            case "TANK":
                if (money < UnitTypes.TANK.getCost()) {
                    return notEnoughMoney;
                } else if (!containUnit(civilization.getTechnologies(), UnitTypes.TANK.getTechnologyRequirements())) {
                    return lackTechnology;
                } else if (city.getCombatUnit() != null) {
                    return unitAlreadyExists;
                } else {
                    NonRangedCombatUnit newUnit = new NonRangedCombatUnit(city.getCentralTerrain().getX(), city.getCentralTerrain().getY(), 0, 0, 0, 0, false, false, UnitTypes.TANK, false, false, false, false, false);
                    civilization.setGold(money - UnitTypes.TANK.getCost());
                    civilization.addUnit(newUnit);
                    city.setCombatUnit(newUnit);
                    return unitPurchasedSuccessfully;
                }


        }
        return "invalid unit name";
    }


    public void assignCitizen(City city, Citizen citizen, Terrain tile) {
        if (city.getCitizens().contains(citizen)) {
            if (city.getNeighbors().contains(tile) && citizen.getHasWork()) {
                citizen.assignWork(tile);
                System.out.println("Citizen assigned successfully");
                return;
            }
        }
        System.out.println("error");

    }

    public void garrisonACity(City city) {
        if (city.getCombatUnit() != null) {
            city.setGarrisoned(true);
            city.setCombatStrength(city.getCombatStrength() + city.getCombatUnit().getCombatStrength());

        }
    }

    public void playATurnInCombat(City city, Unit AttackingUnit) {

    }

    public void buyTile( int x, int y, City city)
    {
        Terrain tile = this.databaseController.getTerrainByCoordinates(x, y);
        ArrayList< Terrain> mainTerrains = city.getMainTerrains();
        if ( NeighborsAtADistanceOfOneFromAnArraylistOfTerrains(mainTerrains, this.map).contains(tile))
        {
            if ( city.getGold()< tile.getGold())
            {
                System.out.println("Not enough money");
                return;
            }
            city.setGold( city.getGold() - tile.getGold());
            mainTerrains.add(tile);
            city.setMainTerrains(mainTerrains);
            return;
        }
        System.out.println("You cannot buy this tile");

    }



    public ArrayList<Terrain> getNeighborTerrainsOfOneTerrain(Terrain terrain, Map map) {
        ArrayList<Terrain> neighbors = new ArrayList<>();
        Terrain[][] copy_map = map.getTerrain();
        int x_beginning = terrain.getX();
        int y_beginning = terrain.getY();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x_beginning + i < 0 || x_beginning + i >= map.getROW() || y_beginning + j < 0 || y_beginning + j >= map.getCOL()) {

                } else if (y_beginning % 2 == 0 && ((i == 0 && j == 0) || (i == 1 && j == 1))) {

                } else if (y_beginning % 2 == 1 && ((i == 0 && j == 0) || (i == -1 && j == -1))) {


                } else {
                    neighbors.add(copy_map[x_beginning + i][y_beginning + j]);
                }


            }

        }
        return neighbors;

    }

    public ArrayList<Terrain> NeighborsAtADistanceOfOneFromAnArraylistOfTerrains(ArrayList<Terrain> terrains, Map map) {

        ArrayList<Terrain> neighbors = new ArrayList<>();
        for (Terrain terrain : terrains) {
            for (Terrain terrain2 : getNeighborTerrainsOfOneTerrain(terrain, map)) {
                neighbors.addAll(getNeighborTerrainsOfOneTerrain(terrain2, map));
            }
        }

        neighbors.removeAll(terrains);

        return deleteExcessTerrain(neighbors);

    }

    public ArrayList<Terrain> NeighborsAtADistanceOfTwoFromAnArraylistOfTerrains(ArrayList<Terrain> terrains, Map map) {

        ArrayList<Terrain> neighbors = new ArrayList<>();
        ArrayList<Terrain> neighborsAtADistanceOfOne = NeighborsAtADistanceOfOneFromAnArraylistOfTerrains(terrains, map);

        neighbors.addAll(neighborsAtADistanceOfOne);
        neighbors.addAll(NeighborsAtADistanceOfOneFromAnArraylistOfTerrains(neighborsAtADistanceOfOne, map));

        neighbors.removeAll(terrains);

        return deleteExcessTerrain(neighbors);

    }

    public ArrayList<Terrain> deleteExcessTerrain(ArrayList<Terrain> terrains) {
        ArrayList<Terrain> finalTerrains = new ArrayList<>();
        for (Terrain terrain : terrains) {
            boolean isNew = true;
            for (Terrain terrain1 : finalTerrains) {
                if (terrain.equals(terrain1)) {
                    isNew = false;
                    break;
                }
            }

            if (isNew) {
                finalTerrains.add(terrain);
            }
        }

        return finalTerrains;
    }

    public UnitTypes getUnitTypeByName(String name) {
        for (UnitTypes unitTypes : UnitTypes.values()) {
            if (unitTypes.name().equals(name)) {
                return unitTypes;
            }
        }
        return null;
    }

    public void removeCitizenFromWork( Citizen citizen)
    {
        if ( citizen != null && citizen.getHasWork())
        {
            citizen.setHasWork(false);
            citizen.deleteWork();
        }
        System.out.println("error");
    }

    public String oneCombatTurn (City city, CombatUnit attacker, Scanner scanner)
    {
        int cityCombatStrength = city.getCombatStrength();
        int attackerCombatStrength = attacker.getCombatStrength();
        city.setHP( city.getHP() - attackerCombatStrength + 1);
        if (city.getGarrisoned())
        {
            attacker.setHP( attacker.getHP() - cityCombatStrength);
            if ( attacker.getHP() <= 0)
            {

                Civilization unitOwner = this.databaseController.getContainerCivilization((Unit) attacker);
                unitOwner.removeUnit( (Unit) attacker);
                Terrain tile = this.databaseController.getTerrainByCoordinates(attacker.getX(), attacker.getY());
                tile.setCombatUnit(null);
                return "The city won.";
            }
        }
        if ( city.getHP() <= 0)
        {
            /*Civilization civilization = city.getOwner();
            civilization.removeCity(city);*/
            //Unit bayad bere tush
            return "The city lost.";
            // bayad bebinim turn kie

        }
        return null;
    }

    public void whatToDoWithTheCity( String input, City city, Civilization civilization)
    {
        if ( civilization.getUnits().contains( (Unit) city.getCentralTerrain().getCombatUnit()) && city.getHP() <= 0)
        {
            if (input.equals("ATTACH CITY"))
            {
                this.attachCity(civilization, city);
            } else
            {
                this.destroyCity(civilization, city.getOwner(), city);
            }
        }
    }

    public void rangedAttackToCityForOneTurn( RangedCombatUnit attacker, City city)
    {
        int combatStrengh = attacker.getUnitType().getRangedCombatStrengh();
        int combatRange = attacker.getUnitType().getRange();
        if ( combatRange != 0)
        {
            Terrain tile = this.databaseController.getTerrainByCoordinates(attacker.getX(), attacker.getY());
            if ( NeighborsAtADistanceOfTwoFromAnArraylistOfTerrains(city.getMainTerrains(), map).contains(tile))
            {
                city.setHP( city.getHP() - combatStrengh + 1);
                return;
            }
            else
            {
                System.out.println("The unit is not close enough");
                return;
            }
        }
        System.out.println("This unit is not a ranged combat unit");
    }



}
