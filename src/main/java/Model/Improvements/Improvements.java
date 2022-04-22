package Model.Improvements;

import java.util.ArrayList;

import Model.Resources.ResourceTypes;
import Model.Technologies.TechnologyTypes;
import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;

public enum Improvements {
    CAMP(0, 0, 0, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.FURS);
            add(ResourceTypes.IVORY);
            add(ResourceTypes.DEER);
        }
    }, TechnologyTypes.AGRICULTURE, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.HILLS);
        }
    }),

    FARM(1, 0, 0, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.WHEAT);
        }
    }, TechnologyTypes.AGRICULTURE, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
        }
    }),
    LUMBERMILL(0, 1, 0, null, TechnologyTypes.ENGINEERING, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.FOREST);
        }
    }),
    MINE(0, 1, 0, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.WHEAT);
            add(ResourceTypes.IRON);
            add(ResourceTypes.COAL);
            add(ResourceTypes.GEMS);
            add(ResourceTypes.GOLD);
            add(ResourceTypes.SILVER);
        }
    }, TechnologyTypes.MINING, new ArrayList<Object>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainFeatureTypes.JUNGLE);
            add(TerrainTypes.SNOW);
            add(TerrainTypes.HILLS);
        }
    }),
    PASTURE(0, 0, 0, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.HORSES);
            add(ResourceTypes.CATTLE);
            add(ResourceTypes.SHEEP);
        }
    }, TechnologyTypes.ANIMAL_HUSBANDARY, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    }),
    PLANTATION(0, 0, 0, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.BANANAS);
            add(ResourceTypes.DYES);
            add(ResourceTypes.SILK);
            add(ResourceTypes.SUGAR);
            add(ResourceTypes.COTTON);
            add(ResourceTypes.INCENSE);
        }
    }, TechnologyTypes.CALENDAR, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainFeatureTypes.FOREST);
            add(TerrainFeatureTypes.MARSH);
            add(TerrainFeatureTypes.FLOODPLAINS);
            add(TerrainFeatureTypes.JUNGLE);
        }
    }),
    QUARRY(0, 0, 0, new ArrayList<ResourceTypes>() {
        {
            add(ResourceTypes.MARBLE);
        }
    }, TechnologyTypes.MASONRY, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    }),
    TRADINGPOST(0, 0, 1, null, TechnologyTypes.TRAPPING, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
        }
    }),
    MANUFACTORY(0, 2, 0, null, TechnologyTypes.ENGINEERING, new ArrayList<Object>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.SNOW);
        }
    });

    final int food;
    final int production;
    final int gold;
    final ArrayList<ResourceTypes> improvesResources;
    final TechnologyTypes requiredTechnology;
    final ArrayList<Object> canBeBuiltON;

    Improvements(int food, int production, int gold, ArrayList<ResourceTypes> improvesResources,
            TechnologyTypes requiredTechnology, ArrayList<Object> canBeBuiltOn) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.improvesResources = improvesResources;
        this.requiredTechnology = requiredTechnology;
        this.canBeBuiltON = canBeBuiltOn;
    }

}
