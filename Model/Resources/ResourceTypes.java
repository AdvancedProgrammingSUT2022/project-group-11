package Model.Resources;

import java.util.ArrayList;

import Model.Improvements.Improvements;
import Model.Technologies.TechnologyTypes;
import Model.Terrain.TerrainTypes;
import Model.TerrainFeatures.TerrainFeatureTypes;

public enum ResourceTypes {
    BANANAS(1, 0, 0, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.JUNGLE);
        }
    }, Improvements.PASTURE, null),

    CATTLE(1, 0, 0, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
        }
    }, Improvements.PASTURE, null),

    DEER(1, 0, 0, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.CAMP, null),

    SHEEP(1, 0, 0, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.PASTURE, null),

    WHEAT(1, 0, 0, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
        }
    }, Improvements.FARM, null),

    COAL(0, 1, 0, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.MINE, TechnologyTypes.SCIENTIFIC_THEORY),

    HORSES(0, 1, 0, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.TUNDRA);
        }
    }, Improvements.PASTURE, TechnologyTypes.ANIMAL_HUSBANDARY),

    IRON(0, 1, 0, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.SNOW);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.MINE, TechnologyTypes.IRON_WORKING),

    COTTON(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
        }
    }, Improvements.PLANTATION, null),

    DYES(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.JUNGLE);
            add(TerrainFeatureTypes.FOREST);
        }
    }, Improvements.PLANTATION, null),

    FURS(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainTypes.TUNDRA);
        }
    }, Improvements.CAMP, null),

    GEMS(0, 0, 3, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.JUNGLE);
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.MINE, null),

    GOLD(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.MINE, null),

    INCENSE(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
        }
    }, Improvements.PLANTATION, null),

    IVORY(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainTypes.PLAINS);
        }
    }, Improvements.CAMP, null),

    MARBLE(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.QUARRY, null),

    SILK(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.FOREST);
        }
    }, Improvements.PLANTATION, null),

    SILVER(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.HILLS);
        }
    }, Improvements.MINE, null),

    SUGAR(0, 0, 2, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.FLOODPLAINS);
            add(TerrainFeatureTypes.MARSH);
        }
    }, Improvements.PLANTATION, null);

    final int food;
    final int production;
    final int gold;
    final ArrayList<Object> canBeFoundOn;
    final Improvements requiredImprovement;
    final TechnologyTypes requiredTechnology;

    ResourceTypes(int food, int production, int gold, ArrayList<Object> canBeFoundOn, Improvements requiredImprovement,
            TechnologyTypes requiredTechnology) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.canBeFoundOn = canBeFoundOn;
        this.requiredImprovement = requiredImprovement;
        this.requiredTechnology = requiredTechnology;
    }
}