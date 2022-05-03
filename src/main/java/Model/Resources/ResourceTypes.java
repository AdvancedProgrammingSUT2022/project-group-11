package Model.Resources;

import java.util.ArrayList;

import Model.Improvements.Improvements;
import Model.Technologies.TechnologyTypes;
import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;

public enum ResourceTypes {
    BANANAS(1, 0, 0, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.JUNGLE);
        }
    }, Improvements.PASTURE, null,"BANANAS"),

    CATTLE(1, 0, 0, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
        }
    }, Improvements.PASTURE, null,"CATTLE"),

    DEER(1, 0, 0, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.CAMP, null,"DEER"),

    SHEEP(1, 0, 0, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.PASTURE, null,"SHEEP"),

    WHEAT(1, 0, 0, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
        }
    }, Improvements.FARM, null,"WHEAT"),

    COAL(0, 1, 0, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.MINE, TechnologyTypes.SCIENTIFIC_THEORY,"COAL"),

    HORSES(0, 1, 0, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.TUNDRA);
        }
    }, Improvements.PASTURE, TechnologyTypes.ANIMAL_HUSBANDARY,"HORSES"),

    IRON(0, 1, 0, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.SNOW);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.MINE, TechnologyTypes.IRON_WORKING,"IRON"),

    COTTON(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
        }
    }, Improvements.PLANTATION, null,"COTTON"),

    DYES(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.JUNGLE);
            add(TerrainFeatureTypes.FOREST);
        }
    }, Improvements.PLANTATION, null,"DYES"),

    FURS(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainTypes.TUNDRA);
        }
    }, Improvements.CAMP, null,"FURS"),

    GEMS(0, 0, 3, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.JUNGLE);
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.MINE, null,"GEMS"),

    GOLD(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.MINE, null,"GOLD"),

    INCENSE(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
        }
    }, Improvements.PLANTATION, null,"INCENSE"),

    IVORY(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainTypes.PLAINS);
        }
    }, Improvements.CAMP, null,"IVORY"),

    MARBLE(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.QUARRY, null,"MARBLE"),

    SILK(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.FOREST);
        }
    }, Improvements.PLANTATION, null,"SILK"),

    SILVER(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.MINE, null,"SILVER"),

    SUGAR(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.FLOODPLAINS);
            add(TerrainFeatureTypes.MARSH);
        }
    }, Improvements.PLANTATION, null,"SUGAR");

     int food;
     int production;
     int gold;
     ArrayList<Object> canBeFoundOn;
     Improvements requiredImprovement;
     TechnologyTypes requiredTechnology;
     String ShowResourceMap;

    ResourceTypes(int food, int production, int gold, ArrayList<Object> canBeFoundOn, Improvements requiredImprovement,
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
    public Improvements getRequiredImprovements(){
        return this.requiredImprovement;
    }
}