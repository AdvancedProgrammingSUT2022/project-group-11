package Model.Improvements;

import java.util.ArrayList;

import Model.Resources.ResourceTypes;
import Model.Technologies.TechnologyTypes;
import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;

public enum Improvements {
    CAMP(0, 0, 0, new ArrayList<>() {
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
    },"CAM"),

    FARM(1, 0, 0, new ArrayList<>() {
        {
            add(ResourceTypes.WHEAT);
        }
    }, TechnologyTypes.AGRICULTURE, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
        }
    },"FAR"),
    LUMBERMILL(0, 1, 0, null, TechnologyTypes.ENGINEERING, new ArrayList<>() {
        {
            add(TerrainFeatureTypes.FOREST);
        }
    },"LUM"),
    MINE(0, 1, 0, new ArrayList<>() {
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
    },"MIN"),
    PASTURE(0, 0, 0, new ArrayList<>() {
        {
            add(ResourceTypes.HORSES);
            add(ResourceTypes.CATTLE);
            add(ResourceTypes.SHEEP);
        }
    }, TechnologyTypes.ANIMAL_HUSBANDARY, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.HILLS);
        }
    },"PAS"),
    PLANTATION(0, 0, 0, new ArrayList<>() {
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
    },"PLA"),
    QUARRY(0, 0, 0, new ArrayList<>() {
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
    },"QUA"),
    TRADINGPOST(0, 0, 1, null, TechnologyTypes.TRAPPING, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
        }
    },"TRA"),
    MANUFACTORY(0, 2, 0, null, TechnologyTypes.ENGINEERING, new ArrayList<>() {
        {
            add(TerrainTypes.GRASSLLAND);
            add(TerrainTypes.PLAINS);
            add(TerrainTypes.DESERT);
            add(TerrainTypes.TUNDRA);
            add(TerrainTypes.SNOW);
        }
    },"MAN");

     int food;
     int production;
     int gold;
     ArrayList<ResourceTypes> improvesResources;
     TechnologyTypes requiredTechnology;
     ArrayList<Object> canBeBuiltON;
     String ShowImprovement;


    Improvements(int food, int production, int gold, ArrayList<ResourceTypes> improvesResources,
            TechnologyTypes requiredTechnology, ArrayList<Object> canBeBuiltOn,String ShowImprovement) {
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.improvesResources = improvesResources;
        this.requiredTechnology = requiredTechnology;
        this.canBeBuiltON = canBeBuiltOn;
        this.ShowImprovement = ShowImprovement;
    }
    public String getShowImprovement(){
        return this.ShowImprovement;
    }

}
