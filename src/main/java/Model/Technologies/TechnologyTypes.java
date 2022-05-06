package Model.Technologies;

import java.util.ArrayList;

import Model.Buildings.BuildingTypes;
import Model.Improvements.Improvements;
import Model.Resources.ResourceTypes;
import Model.Units.UnitTypes;

public enum TechnologyTypes {
    AGRICULTURE(20, null, new ArrayList<>() {
        {
            add(TechnologyTypes.POTTERY);
            add(TechnologyTypes.ANIMAL_HUSBANDRY);
            add(TechnologyTypes.ARCHERY);
            add(TechnologyTypes.MINING);
        }
    }, new ArrayList<>() {
        {
            add(Improvements.FARM);
        }
    }), ANIMAL_HUSBANDRY(35, new ArrayList<>() {
        {
            add(TechnologyTypes.AGRICULTURE);

        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.TRAPPING);
            add(TechnologyTypes.THE_WHEEL);
        }
    }, new ArrayList<>() {
        {
            add(ResourceTypes.HORSES);
            add(Improvements.PASTURE);
        }
    }), ARCHERY(35, new ArrayList<>() {
        {
            add(TechnologyTypes.AGRICULTURE);

        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.MATHEMATICS);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.ARCHER);
        }
    }), BRONZE_WORKING(55, new ArrayList<>() {
        {
            add(TechnologyTypes.MINING);

        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.IRON_WORKING);

        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.SPEARMAN);
            add(BuildingTypes.BARRACKS);
            // TODO REMOVING JUNGLE
        }
    }), CALENDAR(70, new ArrayList<>() {
        {
            add(TechnologyTypes.POTTERY);

        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.THEOLOGY);
        }
    }, new ArrayList<>() {
        {
            add(Improvements.PLANTATION);
        }
    }), MASONRY(55, new ArrayList<>() {
        {
            add(TechnologyTypes.MINING);

        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.CONSTRUCTION);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.WALLS);
            add(Improvements.QUARRY);
            // TODO remove marsh
        }
    }), MINING(35, new ArrayList<>() {
        {
            add(TechnologyTypes.AGRICULTURE);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.MASONRY);
            add(TechnologyTypes.BRONZE_WORKING);
        }
    }, new ArrayList<>() {
        {
            add(Improvements.MINE);
            // TODO remove FOREST
        }
    }), POTTERY(35, new ArrayList<>() {
        {
            add(TechnologyTypes.AGRICULTURE);

        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.CALENDAR);
            add(TechnologyTypes.WRITING);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.GRANARY);
        }
    }), THE_WHEEL(55, new ArrayList<>() {
        {
            add(TechnologyTypes.ANIMAL_HUSBANDRY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.HORSEBACK_RIDING);
            add(TechnologyTypes.MATHEMATICS);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.CHARIOT_ARCHER);
            add(BuildingTypes.WATERMILL);
            // TODO build a road
        }
    }), TRAPPING(55, new ArrayList<>() {
        {
            add(TechnologyTypes.ANIMAL_HUSBANDRY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.CIVIL_SERVICE);
        }
    }, new ArrayList<>() {
        {
            add(Improvements.TRADINGPOST);
            add(Improvements.CAMP);
        }
    }), WRITING(55, new ArrayList<>() {
        {
            add(TechnologyTypes.POTTERY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.PHILOSOPHY);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.LIBRARY);
        }
    }), CONSTRUCTION(100, new ArrayList<>() {
        {
            add(TechnologyTypes.MASONRY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.ENGINEERING);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.COLOSSEUM);
            // TODO bridges over rivers
        }
    }), HORSEBACK_RIDING(100, new ArrayList<>() {
        {
            add(TechnologyTypes.THE_WHEEL);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.CHIVALRY);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.STABLE);
            add(BuildingTypes.CIRCUS);
        }
    }), IRON_WORKING(150, new ArrayList<>() {
        {
            add(TechnologyTypes.BRONZE_WORKING);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.METAL_CASTING);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.SWORDSMAN);
            add(BuildingTypes.ARMORY);
        }
    }), MATHEMATICS(100, new ArrayList<>() {
        {
            add(TechnologyTypes.THE_WHEEL);
            add(TechnologyTypes.ARCHERY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.CURRENCY);
            add(TechnologyTypes.ENGINEERING);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.CATAPULT);
            add(BuildingTypes.COURTHOUSE);
        }
    }), PHILOSOPHY(100, new ArrayList<>() {
        {
            add(TechnologyTypes.WRITING);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.THEOLOGY);
            add(TechnologyTypes.CIVIL_SERVICE);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.BURIAL_TOMB);
            add(BuildingTypes.TEMPLE);
        }
    }), CHIVALRY(440, new ArrayList<>() {
        {
            add(TechnologyTypes.CIVIL_SERVICE);
            add(TechnologyTypes.HORSEBACK_RIDING);
            add(TechnologyTypes.CURRENCY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.BANKING);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.KNIGHT);
            add(BuildingTypes.CASTLE);
        }
    }), CIVIL_SERVICE(400, new ArrayList<>() {
        {
            add(TechnologyTypes.PHILOSOPHY);
            add(TechnologyTypes.TRAPPING);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.CHIVALRY);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.PIKEMAN);
        }
    }), CURRENCY(250, new ArrayList<>() {
        {
            add(TechnologyTypes.MATHEMATICS);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.CHIVALRY);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.MARKET);
        }
    }), EDUCATION(440, new ArrayList<>() {
        {
            add(TechnologyTypes.THEOLOGY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.ACOUSTICS);
            add(TechnologyTypes.BANKING);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.UNIVERSITY);
        }
    }), ENGINEERING(250, new ArrayList<>() {
        {
            add(TechnologyTypes.MATHEMATICS);
            add(TechnologyTypes.CONSTRUCTION);

        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.MACHINERY);
            add(TechnologyTypes.PHYSICS);
        }
    }, new ArrayList<>() {
    }), MACHINERY(440, new ArrayList<>() {
        {
            add(TechnologyTypes.ENGINEERING);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.PRINTING_PRESS);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.CROSSBOWMAN);
            // TODO 1.2 faster road movement
        }
    }), METAL_CASTING(240, new ArrayList<>() {
        {
            add(TechnologyTypes.IRON_WORKING);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.PHYSICS);
            add(TechnologyTypes.STEEL);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.FORGE);
            add(BuildingTypes.WORKSHOP);
        }
    }), PHYSICS(440, new ArrayList<>() {
        {
            add(TechnologyTypes.ENGINEERING);
            add(TechnologyTypes.METAL_CASTING);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.PRINTING_PRESS);
            add(TechnologyTypes.GUNPOWDER);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.TREBUCHET);
        }
    }), STEEL(440, new ArrayList<>() {
        {
            add(TechnologyTypes.METAL_CASTING);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.GUNPOWDER);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.LONGSWORDSMAN);
        }
    }), THEOLOGY(250, new ArrayList<>() {
        {
            add(TechnologyTypes.CALENDAR);
            add(TechnologyTypes.PHILOSOPHY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.EDUCATION);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.MONASTERY);
            add(BuildingTypes.GARDEN);
        }
    }), ACOUSTICS(650, new ArrayList<>() {
        {
            add(TechnologyTypes.EDUCATION);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.SCIENTIFIC_THEORY);
        }
    }, new ArrayList<>() {
    }), ARCHAEOLOGY(1300, new ArrayList<>() {
        {
            add(TechnologyTypes.ACOUSTICS);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.BIOLOGY);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.MUSEUM);
        }
    }), BANKING(650, new ArrayList<>() {
        {
            add(TechnologyTypes.EDUCATION);
            add(TechnologyTypes.CHIVALRY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.ECONOMICS);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.SATRAPS_COURT);
            add(BuildingTypes.BANK);

        }
    }), CHEMISTRY(900, new ArrayList<>() {
        {
            add(TechnologyTypes.GUNPOWDER);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.MILITARY_SCIENCE);
            add(TechnologyTypes.FERTILIZER);
        }
    }, new ArrayList<>() {
    }), ECONOMICS(900, new ArrayList<>() {
        {
            add(TechnologyTypes.BANKING);
            add(TechnologyTypes.PRINTING_PRESS);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.MILITARY_SCIENCE);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.WINDMILL);
        }
    }), FERTILIZER(1300, new ArrayList<>() {
        {
            add(TechnologyTypes.CHEMISTRY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.DYNAMITE);
        }
    }, new ArrayList<>() {
        {
            // todo Farms without Fresh Water yield increased by 1
        }
    }), GUNPOWDER(680, new ArrayList<>() {
        {
            add(TechnologyTypes.PHYSICS);
            add(TechnologyTypes.STEEL);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.CHEMISTRY);
            add(TechnologyTypes.METALLURGY);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.MUSKETMAN);
        }
    }), METALLURGY(900, new ArrayList<>() {
        {
            add(TechnologyTypes.GUNPOWDER);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.RIFLING);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.LANCER);
        }
    }), MILITARY_SCIENCE(1300, new ArrayList<>() {
        {
            add(TechnologyTypes.ECONOMICS);
            add(TechnologyTypes.CHEMISTRY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.STEAM_POWER);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.CAVALRY);
            add(BuildingTypes.MILITARY_ACADEMY);
        }
    }), PRINTING_PRESS(650, new ArrayList<>() {
        {
            add(TechnologyTypes.MACHINERY);
            add(TechnologyTypes.PHYSICS);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.ECONOMICS);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.THEATER);
        }
    }), RIFLING(1425, new ArrayList<>() {
        {
            add(TechnologyTypes.METALLURGY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.DYNAMITE);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.RIFLEMAN);
        }
    }), SCIENTIFIC_THEORY(1300, new ArrayList<>() {
        {
            add(TechnologyTypes.ACOUSTICS);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.BIOLOGY);
            add(TechnologyTypes.STEAM_POWER);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.PUBLIC_SCHOOL);
            add(ResourceTypes.COAL);
        }
    }), BIOLOGY(1680, new ArrayList<>() {
        {
            add(TechnologyTypes.ARCHAEOLOGY);
            add(TechnologyTypes.SCIENTIFIC_THEORY);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.ELECTRICITY);
        }
    }, new ArrayList<>() {
    }), COMBUSTION(2200, new ArrayList<>() {
        {
            add(TechnologyTypes.REPLACEABLE_PARTS);
            add(TechnologyTypes.RAILROAD);
            add(TechnologyTypes.DYNAMITE);
        }
    }, new ArrayList<>() {
    }, new ArrayList<>() {
        {
            add(UnitTypes.TANK);
            add(UnitTypes.PANZER);
        }
    }), DYNAMITE(1900, new ArrayList<>() {
        {
            add(TechnologyTypes.FERTILIZER);
            add(TechnologyTypes.RIFLING);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.COMBUSTION);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.ARTILLERY);
        }
    }), ELECTRICITY(1900, new ArrayList<>() {
        {
            add(TechnologyTypes.BIOLOGY);
            add(TechnologyTypes.STEAM_POWER);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.TELEGRAPH);
            add(TechnologyTypes.RADIO);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.STOCK_EXCHANGE);
        }
    }), RADIO(2200, new ArrayList<>() {
        {
            add(TechnologyTypes.ELECTRICITY);
        }
    }, new ArrayList<>() {
    }, new ArrayList<>() {
        {
            add(BuildingTypes.BROADCAST_TOWER);
        }
    }), RAILROAD(1900, new ArrayList<>() {
        {
            add(TechnologyTypes.STEAM_POWER);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.COMBUSTION);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.ARSENAL);
        }
    }), REPLACEABLE_PARTS(1900, new ArrayList<>() {
        {
            add(TechnologyTypes.STEAM_POWER);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.COMBUSTION);
        }
    }, new ArrayList<>() {
        {
            add(UnitTypes.ANTI_TANKGUN);
            add(UnitTypes.INFANTRY);
        }
    }), STEAM_POWER(1680, new ArrayList<>() {
        {
            add(TechnologyTypes.SCIENTIFIC_THEORY);
            add(TechnologyTypes.MILITARY_SCIENCE);
        }
    }, new ArrayList<>() {
        {
            add(TechnologyTypes.ELECTRICITY);
            add(TechnologyTypes.REPLACEABLE_PARTS);
        }
    }, new ArrayList<>() {
        {
            add(BuildingTypes.FACTORY);
        }
    }), TELEGRAPH(2200, new ArrayList<>() {
        {
            add(TechnologyTypes.ELECTRICITY);
        }
    }, new ArrayList<>() {
    }, new ArrayList<>() {
        {
            add(BuildingTypes.MILITARY_BASE);
        }
    });

    TechnologyTypes(int cost, ArrayList<TechnologyTypes> requirement, ArrayList<TechnologyTypes> technologyUnlocks,
            ArrayList<Object> unlocks) {
        this.cost = cost;
        this.requirement = requirement;
        this.technologyUnlocks = technologyUnlocks;
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