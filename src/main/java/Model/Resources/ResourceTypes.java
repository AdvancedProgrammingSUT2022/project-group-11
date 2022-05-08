package Model.Resources;

import java.util.ArrayList;

import Model.Improvements.ImprovementTypes;
import Model.Technologies.TechnologyTypes;
import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;

public enum ResourceTypes {
    BANANAS(1, 0, 0, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.JUNGLE);
        }
    }, ImprovementTypes.PASTURE, null,"BANANAS"),

    CATTLE(1, 0, 0, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
        }
    }, ImprovementTypes.PASTURE, null,"CATTLE"),

    DEER(1, 0, 0, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    }, ImprovementTypes.CAMP, null,"DEER"),

    SHEEP(2, 0, 0, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.HILLS);
        }
    }, ImprovementTypes.PASTURE, null,"SHEEP"),

    WHEAT(1, 0, 0, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
        }
    }, ImprovementTypes.FARM, null,"WHEAT"),

    COAL(0, 1, 0, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.HILLS);
        }
    }, ImprovementTypes.MINE, TechnologyTypes.SCIENTIFIC_THEORY,"COAL"),

    HORSES(0, 1, 0, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.TUNDRA);
        }
    }, ImprovementTypes.PASTURE, TechnologyTypes.ANIMAL_HUSBANDRY,"HORSES"),

    IRON(0, 1, 0, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.SNOW);
            add(TerrainTypes.HILLS);
        }
    }, ImprovementTypes.MINE, TechnologyTypes.IRON_WORKING,"IRON"),

    COTTON(0, 0, 2, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
        }
    }, ImprovementTypes.PLANTATION, null,"COTTON"),

    DYES(0, 0, 2, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.JUNGLE);
            add(TerrainFeatureTypes.FOREST);
        }
    }, ImprovementTypes.PLANTATION, null,"DYES"),

    FURS(0, 0, 2, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainTypes.TUNDRA);
        }
    }, ImprovementTypes.CAMP, null,"FURS"),

    GEMS(0, 0, 3, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.JUNGLE);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.HILLS);
        }
    }, ImprovementTypes.MINE, null,"GEMS"),

    GOLD(0, 0, 2, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.HILLS);
        }
    }, ImprovementTypes.MINE, null,"GOLD"),

    INCENSE(0, 0, 2, new ArrayList<>() {
        {
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
        }
    }, ImprovementTypes.PLANTATION, null,"INCENSE"),

    IVORY(0, 0, 2, new ArrayList<>() {
        {
            add(TerrainTypes.PLAINS);
        }
    }, ImprovementTypes.CAMP, null,"IVORY"),

    MARBLE(0, 0, 2, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    }, ImprovementTypes.QUARRY, null,"MARBLE"),

    SILK(0, 0, 2, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.FOREST);
        }
    }, ImprovementTypes.PLANTATION, null,"SILK"),

    SILVER(0, 0, 2, new ArrayList<>() {
        {
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.HILLS);
        }
    }, ImprovementTypes.MINE, null,"SILVER"),

    SUGAR(0, 0, 2, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.FLOODPLAINS);
            add(TerrainFeatureTypes.MARSH);
        }
    }, ImprovementTypes.PLANTATION, null,"SUGAR");

     int food;
     int production;
     int gold;
     ArrayList<Object> canBeFoundOn;
     ImprovementTypes requiredImprovement;
     TechnologyTypes requiredTechnology;
     String ShowResourceMap;

    ResourceTypes(int food, int production, int gold, ArrayList<Object> canBeFoundOn, ImprovementTypes requiredImprovement,
            TechnologyTypes requiredTechnology,String ShowResourceMap) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.canBeFoundOn = canBeFoundOn;
        this.requiredImprovement = requiredImprovement;
        this.requiredTechnology = requiredTechnology;
        this.ShowResourceMap = ShowResourceMap;
    }
    public String getShowResourceMap(){
        return this.ShowResourceMap;
    }
    public  ArrayList<Object> getObject(){
        return this.canBeFoundOn;
    }
    public int getFood(){
        return this.food;
    }
    public int getGold(){
        return this.gold;
    }
    public int getProduction(){
        return this.production;
    }
    public ImprovementTypes getRequiredImprovements(){
        return this.requiredImprovement;
    }
}