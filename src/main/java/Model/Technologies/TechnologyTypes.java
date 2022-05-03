package Model.Technologies;

import java.util.ArrayList;

import Model.Buildings.BuildingTypes;
import Model.Improvements.Improvements;
import Model.Resources.ResourceTypes;
import Model.Units.UnitTypes;

public enum TechnologyTypes {
    AGRICULTURE(20, null, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.POTTERY);
            add(TechnologyTypes.ANIMAL_HUSBANDARY);
            add(TechnologyTypes.ARCHERY);
            add(TechnologyTypes.MINING);
        }
    }, new ArrayList<Object>() {
        {
            add(Improvements.FARM);
        }
    }), ANIMAL_HUSBANDARY(35, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.AGRICULTURE);

        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.TRAPPING);
            add(TechnologyTypes.THE_WHEEL);
        }
    }, new ArrayList<Object>() {
        {
            add(ResourceTypes.HORSES);
            add(Improvements.PASTURE);
        }
    }), ARCHERY(35, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.AGRICULTURE);

        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.MATHEMATICS);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.ARCHER);
        }
    }), BRONZE_WORKING(55, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.MINING);

        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.IRON_WORKING);

        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.SPEARMAN);
            add(BuildingTypes.BARRACKS);
        }
    }), CALENDAR(70, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.POTTERY);

        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.THEOLOGY);
        }
    }, new ArrayList<Object>() {
        {
            add(Improvements.PLANTATION);
        }
    }), MASONRY(55, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.MINING);

        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.CONSTRUCTION);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.WALLS);
            add(Improvements.QUARRY);
            // TODO remove marsh
        }
    }), MINING(35, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.AGRICULTURE);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.MASONRY);
            add(TechnologyTypes.BRONZE_WORKING);
        }
    }, new ArrayList<Object>() {
        {
            add(Improvements.MINE);
            // todo remove jungles
        }
    }), POTTERY(35, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.AGRICULTURE);

        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.CALENDAR);
            add(TechnologyTypes.WRITING);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.GRANARY);
        }
    }), THE_WHEEL(55, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ANIMAL_HUSBANDARY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.HORSESBACK_RIDING);
            add(TechnologyTypes.MATHEMATICS);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.CHARIOT_ARCHER);
            add(BuildingTypes.WATERMILL);
            // TODO build a road
        }
    }), TRAPPING(55, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ANIMAL_HUSBANDARY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.CIVIL_SERVICE);
        }
    }, new ArrayList<Object>() {
        {
            add(Improvements.TRADINGPOST);
            add(Improvements.CAMP);
        }
    }), WRITING(55, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.POTTERY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.PHILOSOPHY);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.LIBRARY);
        }
    }), CONSTRUCTION(100, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.MASONRY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ENGINEERING);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.COLOSSEUM);
            // TODO bridges over rivers
        }
    }), HORSESBACK_RIDING(100, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.THE_WHEEL);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.CHIVALRY);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.CIRCUS);
            add(BuildingTypes.STABLE);
        }
    }), IRON_WORKING(150, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.BRONZE_WORKING);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.METAL_CASTING);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.SWORDSMAN);
            add(BuildingTypes.ARMORY);
        }
    }), MATHEMATICS(100, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.THE_WHEEL);
            add(TechnologyTypes.ARCHERY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.CURRENCY);
            add(TechnologyTypes.ENGINEERING);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.CATAPULT);
            add(BuildingTypes.COURTHOUSE);
        }
    }), PHILOSOPHY(100, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.WRITING);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.THEOLOGY);
            add(TechnologyTypes.CIVIL_SERVICE);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.BURIAL_TOMB);
            add(BuildingTypes.TEMPLE);
        }
    }), CHIVALRY(440, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.CIVIL_SERVICE);
            add(TechnologyTypes.HORSESBACK_RIDING);
            add(TechnologyTypes.CURRENCY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.BANKING);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.KNIGHT);
            add(BuildingTypes.CASTLE);
        }
    }), CIVIL_SERVICE(400, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.PHILOSOPHY);
            add(TechnologyTypes.TRAPPING);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.CHIVALRY);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.PIKEMAN);
        }
    }), CURRENCY(250, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.MATHEMATICS);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.CHIVALRY);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.MARKET);
        }
    }), EDUCATION(440, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.THEOLOGY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ACOUSTICS);
            add(TechnologyTypes.BANKING);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.UNIVERSITY);
        }
    }), ENGINEERING(250, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.MATHEMATICS);
            add(TechnologyTypes.CONSTRUCTION);

        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.MACHINERY);
            add(TechnologyTypes.PHYSICS);
        }
    }, new ArrayList<Object>() {
        {

        }
    }), MACHINERY(440, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ENGINEERING);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.PRINTINGPRESS);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.CROSSBOWMAN);
            // TODO 1.2 faster road movement
        }
    }), METAL_CASTING(240, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.IRON_WORKING);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.PHYSICS);
            add(TechnologyTypes.STEEL);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.FORGE);
            add(BuildingTypes.WORKSHOP);
        }
    }), PHYSICS(440, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ENGINEERING);
            add(TechnologyTypes.METAL_CASTING);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.PRINTINGPRESS);
            add(TechnologyTypes.GUNPOWDER);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.TREBUCHET);
        }
    }), STEEL(440, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.METAL_CASTING);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.GUNPOWDER);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.LONGSWORDSMAN);
        }
    }), THEOLOGY(250, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.CALENDAR);
            add(TechnologyTypes.PHILOSOPHY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.EDUCATION);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.MONASTERY);
            add(BuildingTypes.GARDEN);
        }
    }), ACOUSTICS(650, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.EDUCATION);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.SCIENTIFIC_THEORY);
        }
    }, new ArrayList<Object>() {
        {

        }
    }), ARCHAEOLOGY(1300, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ACOUSTICS);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.BIOLOGY);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.MUSEUM);
        }
    }), BANKING(650, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.EDUCATION);
            add(TechnologyTypes.CHIVALRY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ECONOMICS);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.SATRAPS_COURT);
            add(BuildingTypes.BANK);

        }
    }), CHEMISTRY(900, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.GUNPOWDER);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.MILITARYSCIENCE);
            add(TechnologyTypes.FERTILIIZER);
        }
    }, new ArrayList<Object>() {
        {
            // todo
        }
    }), ECONOMICS(900, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.BANKING);
            add(TechnologyTypes.PRINTINGPRESS);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.MILITARYSCIENCE);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.WINDMILL);
        }
    }), FERTILIIZER(1300, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.CHEMISTRY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.DYNAMITE);
        }
    }, new ArrayList<Object>() {
        {
            // todo
        }
    }), GUNPOWDER(680, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.PHYSICS);
            add(TechnologyTypes.STEEL);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.CHEMISTRY);
            add(TechnologyTypes.METALLURGY);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.MUSKETMAN);
        }
    }), METALLURGY(900, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.GUNPOWDER);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.RIFLING);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.LANCER);
        }
    }), MILITARYSCIENCE(1300, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ECONOMICS);
            add(TechnologyTypes.CHEMISTRY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.STEAM_POWER);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.CAVALRY);
            add(BuildingTypes.MILITARY_ACADEMY);
        }
    }), PRINTINGPRESS(650, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.MACHINERY);
            add(TechnologyTypes.PHYSICS);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ECONOMICS);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.THEATER);
        }
    }), RIFLING(1425, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.METALLURGY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.DYNAMITE);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.RIFLEMAN);
        }
    }), SCIENTIFIC_THEORY(1300, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ACOUSTICS);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.BIOLOGY);
            add(TechnologyTypes.STEAM_POWER);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.PUBLIC_SCHOOL);
            add(ResourceTypes.COAL);
        }
    }), BIOLOGY(1680, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ARCHAEOLOGY);
            add(TechnologyTypes.SCIENTIFIC_THEORY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ELECTRICITY);
        }
    }, new ArrayList<Object>() {
        {

        }
    }), COMBUSTION(2200, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.REPLACEABLE_PARTS);
            add(TechnologyTypes.RAILROAD);
            add(TechnologyTypes.DYNAMITE);
        }
    }, new ArrayList<TechnologyTypes>() {
        {

        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.TANK);
            add(UnitTypes.PANZER);
        }
    }), DYNAMITE(1900, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.FERTILIIZER);
            add(TechnologyTypes.RIFLING);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.COMBUSTION);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.ARTILLERY);
        }
    }), ELECTRICITY(1900, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.BIOLOGY);
            add(TechnologyTypes.STEAM_POWER);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.TELEGRAPH);
            add(TechnologyTypes.RADIO);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.STOCK_EXCHANGE);
        }
    }), RADIO(2200, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ELECTRICITY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {

        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.BROADCAST_TOWER);
        }
    }), RAILROAD(1900, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.STEAM_POWER);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.COMBUSTION);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.ARSENAL);
        }
    }), REPLACEABLE_PARTS(1900, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.STEAM_POWER);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.COMBUSTION);
        }
    }, new ArrayList<Object>() {
        {
            add(UnitTypes.ANTI_TANKGUN);
            add(UnitTypes.INFANTRY);
        }
    }), STEAM_POWER(1680, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.SCIENTIFIC_THEORY);
            add(TechnologyTypes.MILITARYSCIENCE);
        }
    }, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ELECTRICITY);
            add(TechnologyTypes.REPLACEABLE_PARTS);
        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.FACTORY);
        }
    }), TELEGRAPH(2200, new ArrayList<TechnologyTypes>() {
        {
            add(TechnologyTypes.ELECTRICITY);
        }
    }, new ArrayList<TechnologyTypes>() {
        {

        }
    }, new ArrayList<Object>() {
        {
            add(BuildingTypes.MILITARY_BASE);
        }
    });

    TechnologyTypes(int cost, ArrayList<TechnologyTypes> requirement, ArrayList<TechnologyTypes> technologyUnlcoks,
            ArrayList<Object> unlocks) {
        this.cost = cost;
        this.requirement = requirement;
        this.technologyUnlocks = technologyUnlcoks;
        this.unlocks = unlocks;
    }

    int cost;
    ArrayList<TechnologyTypes> requirement;
    ArrayList<TechnologyTypes> technologyUnlocks;
    ArrayList<Object> unlocks;

    public ArrayList<TechnologyTypes> getRequirements()
    {
        return requirement;
    }
    public ArrayList<TechnologyTypes> getTechnologyUnlocks()
    {
        return technologyUnlocks;
    }

    public int getCost() {
        return cost;
    }
}