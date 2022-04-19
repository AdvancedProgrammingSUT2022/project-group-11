
package Model.Terrains;

import java.util.ArrayList;

import Model.Resources.ResourceTypes;
import Model.TerrainFeatures.TerrainFeatureTypes;

public enum TerrainTypes {
    DESERT(0, 0, 0, -33, 1, new ArrayList<TerrainFeatureTypes>() {
        {
            add(TerrainFeatureTypes.OASIS);
            add(TerrainFeatureTypes.FLOODPLAINS);
        }
    }, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.IRON);
            add(ResourceTypes.GOLD);
            add(ResourceTypes.SILVER);
            add(ResourceTypes.GEMS);
            add(ResourceTypes.MARBLE);
            add(ResourceTypes.COTTON);
            add(ResourceTypes.INCENSE);
            add(ResourceTypes.SHEEP);
        }
    }),
    GRASSLLAND(2, 0, 0, -33, 1, new ArrayList<TerrainFeatureTypes>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainFeatureTypes.MARSH);
        }
    }, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.IRON);
            add(ResourceTypes.HORSES);
            add(ResourceTypes.COAL);
            add(ResourceTypes.CATTLE);
            add(ResourceTypes.GOLD);
            add(ResourceTypes.GEMS);
            add(ResourceTypes.MARBLE);
            add(ResourceTypes.COTTON);
            add(ResourceTypes.SHEEP);
        }
    }),
    HILLS(0, 2, 0, 25, 2, new ArrayList<TerrainFeatureTypes>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainFeatureTypes.JUNGLE);
        }
    }, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.IRON);
            add(ResourceTypes.COAL);
            add(ResourceTypes.DEER);
            add(ResourceTypes.GOLD);
            add(ResourceTypes.SILVER);
            add(ResourceTypes.GEMS);
            add(ResourceTypes.MARBLE);
            add(ResourceTypes.SHEEP);
        }
    }),
    MOUNTAIN(0, 0, 0, 25, 9999999, null, null),
    OCEAN(1, 0, 1, 0, 1, new ArrayList<TerrainFeatureTypes>() {
        {
            add(TerrainFeatureTypes.ICE);
        }
    }, new ArrayList<ResourceTypes>() {
        {

        }
    }),
    PLAINS(1, 1, 0, -33, 1, new ArrayList<TerrainFeatureTypes>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainFeatureTypes.JUNGLE);
        }
    }, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.IRON);
            add(ResourceTypes.HORSES);
            add(ResourceTypes.COAL);
            add(ResourceTypes.WHEAT);
            add(ResourceTypes.GOLD);
            add(ResourceTypes.GEMS);
            add(ResourceTypes.MARBLE);
            add(ResourceTypes.IVORY);
            add(ResourceTypes.COTTON);
            add(ResourceTypes.INCENSE);
            add(ResourceTypes.SHEEP);
        }
    }),
    SNOW(0, 0, 0, -33, 1, null, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.IRON);
        }
    }),
    TUNDRA(1, 0, 0, -33, 1, new ArrayList<TerrainFeatureTypes>() {
        {
            add(TerrainFeatureTypes.FOREST);
        }
    }, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.IRON);
            add(ResourceTypes.HORSES);
            add(ResourceTypes.DEER);
            add(ResourceTypes.SILVER);
            add(ResourceTypes.GEMS);
            add(ResourceTypes.MARBLE);
            add(ResourceTypes.FURS);
        }
    });

    TerrainTypes(int food, int product, int gold, int combatModifier, int MP,
            ArrayList<TerrainFeatureTypes> possibleFeatures, ArrayList<ResourceTypes> possibleResources) {
        this.food = food;
        this.product = product;
        this.gold = gold;
        this.movementCost = movementCost;
        this.combatModifier = combatModifier;
        this.possibleFeatures = possibleFeatures;
        this.possibleResources = possibleResources;
    }

    int food;
    int product;
    int gold;
    int movementCost;
    int combatModifier;
    ArrayList<TerrainFeatureTypes> possibleFeatures;
    ArrayList<ResourceTypes> possibleResources;
}