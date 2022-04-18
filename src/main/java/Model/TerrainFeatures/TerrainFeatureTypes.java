package Model.TerrainFeatures;

import java.util.ArrayList;

import Model.Resources.ResourceTypes;

public enum TerrainFeatureTypes {
    FLOODPLAINS(2, 0, 0, -33, 1, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.WHEAT);
            add(ResourceTypes.SUGAR);
        }
    }),
    FOREST(1, 1, 0, 25, 2, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.DEER);
            add(ResourceTypes.FURS);
            add(ResourceTypes.DYES);
            add(ResourceTypes.SILK);
        }
    }),
    ICE(0, 0, 0, 0, 99999, null),
    JUNGLE(1, -1, 0, 25, 2, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.BANANAS);
            add(ResourceTypes.GEMS);
            add(ResourceTypes.DYES);
        }
    }),
    MARSH(-1, 0, 0, -33, 2, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.SUGAR);
        }
    }),
    OASIS(3, 0, 1, -33, 1, null),
    RIVER(0, 0, 1, 0, -1, null);

    TerrainFeatureTypes(int food, int product, int gold, int combatModifier, int movementCost,
            ArrayList<ResourceTypes> possibleResourcesFound) {
        this.food = food;
        this.product = product;
        this.gold = gold;
        this.combatModifier = combatModifier;
        this.movementCost = movementCost;
        this.possibleResourcesFound = possibleResourcesFound;
    }

    int food;
    int product;
    int gold;
    int combatModifier;
    int movementCost;
    ArrayList<ResourceTypes> possibleResourcesFound;
}