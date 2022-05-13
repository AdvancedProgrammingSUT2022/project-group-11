package Model.Improvements;

import Model.Resources.ResourceTypes;
import Model.Technologies.TechnologyTypes;
import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;

import java.util.ArrayList;
import java.util.List;

public enum ImprovementTypes {
    ROAD(3, 0, 0, 0, new ArrayList<>() {
    }, TechnologyTypes.THE_WHEEL, new ArrayList<>() {
        {

            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.HILLS);
            add(TerrainTypes.SNOW);
            add(TerrainFeatureTypes.JUNGLE);
            add(TerrainFeatureTypes.FOREST);
            add(TerrainFeatureTypes.MARSH);
            add(TerrainFeatureTypes.FLOODPLAINS);
            add(TerrainFeatureTypes.OASIS);
            add(TerrainFeatureTypes.RIVER);

        }
    }, "RO"),
    RAILROAD(3, 0, 0, 0, new ArrayList<>() {
    }, TechnologyTypes.RAILROAD, new ArrayList<>() {
        {
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.HILLS);
            add(TerrainTypes.SNOW);
            add(TerrainFeatureTypes.JUNGLE);
            add(TerrainFeatureTypes.FOREST);
            add(TerrainFeatureTypes.MARSH);
            add(TerrainFeatureTypes.FLOODPLAINS);
            add(TerrainFeatureTypes.OASIS);
            add(TerrainFeatureTypes.RIVER);
        }

    }, "RRO"),
    CAMP(6, 0, 0, 0, new ArrayList<>() {
        {
            add(ResourceTypes.FURS);
            add(ResourceTypes.IVORY);
            add(ResourceTypes.DEER);
        }
    }, TechnologyTypes.AGRICULTURE, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.HILLS);
        }
    }, "CAM"),

    FARM(10, 1, 0, 0, new ArrayList<>() {
        {
            add(ResourceTypes.WHEAT);
        }
    }, TechnologyTypes.AGRICULTURE, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
        }
    }, "FAR"),
    LUMBERMILL(6, 0, 1, 0, null, TechnologyTypes.ENGINEERING, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.FOREST);
        }
    }, "LUM"),
    MINE(12, 0, 1, 0, new ArrayList<>() {
        {
            add(ResourceTypes.WHEAT);
            add(ResourceTypes.IRON);
            add(ResourceTypes.COAL);
            add(ResourceTypes.GEMS);
            add(ResourceTypes.GOLD);
            add(ResourceTypes.SILVER);
        }
    }, TechnologyTypes.MINING, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.FOREST);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainFeatureTypes.JUNGLE);
            add(TerrainTypes.SNOW);
            add(TerrainTypes.HILLS);
        }
    }, "MIN"),
    PASTURE(7, 0, 0, 0, new ArrayList<>() {
        {
            add(ResourceTypes.HORSES);
            add(ResourceTypes.CATTLE);
            add(ResourceTypes.SHEEP);
        }
    }, TechnologyTypes.ANIMAL_HUSBANDRY, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    }, "PAS"),
    PLANTATION(5, 0, 0, 0, new ArrayList<>() {
        {
            add(ResourceTypes.BANANAS);
            add(ResourceTypes.DYES);
            add(ResourceTypes.SILK);
            add(ResourceTypes.SUGAR);
            add(ResourceTypes.COTTON);
            add(ResourceTypes.INCENSE);
        }
    }, TechnologyTypes.CALENDAR, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainFeatureTypes.FOREST);
            add(TerrainFeatureTypes.MARSH);
            add(TerrainFeatureTypes.FLOODPLAINS);
            add(TerrainFeatureTypes.JUNGLE);
        }
    }, "PLA"),
    QUARRY(7, 0, 0, 0, new ArrayList<>() {
        {
            add(ResourceTypes.MARBLE);
        }
    }, TechnologyTypes.MASONRY, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    }, "QUA"),
    TRADINGPOST(8, 0, 0, 1, null, TechnologyTypes.TRAPPING, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
        }
    }, "TRA"),
    MANUFACTORY(4, 0, 2, 0, null, TechnologyTypes.ENGINEERING, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.SNOW);
        }
    }, "MAN");

    int food;
    int production;
    int gold;
    int turn;
    ArrayList<ResourceTypes> resourcesAccessed;
    TechnologyTypes requiredTechnology;
    ArrayList<Object> canBeBuiltON;
    String ShowImprovement;


    ImprovementTypes(int turn, int food, int production, int gold, ArrayList<ResourceTypes> resourcesAccessed,
                     TechnologyTypes requiredTechnology, ArrayList<Object> canBeBuiltOn, String ShowImprovement) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.resourcesAccessed = resourcesAccessed;
        this.requiredTechnology = requiredTechnology;
        this.canBeBuiltON = canBeBuiltOn;
        this.ShowImprovement = ShowImprovement;
    }

    public String getShowImprovement() {
        return this.ShowImprovement;
    }

    public int getGold() {
        return this.gold;
    }

    public int getFood() {
        return this.food;
    }

    public ArrayList<Object> getCanBeBuiltON() {
        return canBeBuiltON;
    }

    public void setCanBeBuiltON(ArrayList<Object> canBeBuiltON) {
        this.canBeBuiltON = canBeBuiltON;
    }

    public ArrayList<ResourceTypes> getResourcesAccessed() {
        return resourcesAccessed;
    }

    public void setResourcesAccessed(ArrayList<ResourceTypes> resourcesAccessed) {
        this.resourcesAccessed = resourcesAccessed;
    }

    public TechnologyTypes getRequiredTechnology() {
        return requiredTechnology;
    }

    public void setRequiredTechnology(TechnologyTypes requiredTechnology) {
        this.requiredTechnology = requiredTechnology;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}