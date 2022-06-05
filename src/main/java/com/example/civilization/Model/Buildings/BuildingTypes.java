package com.example.civilization.Model.Buildings;


import com.example.civilization.Model.Resources.ResourceTypes;
import com.example.civilization.Model.Technologies.TechnologyTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BuildingTypes {
    BARRACKS(7,80, 1, TechnologyTypes.BRONZE_WORKING, null,null)
    , GRANARY(8,100, 1, TechnologyTypes.POTTERY,null,null)
    , LIBRARY(7,80, 1, TechnologyTypes.WRITING,null,null)
    , MONUMENT(9,60, 1, null,null,null)
    , WALLS(10,100, 1, TechnologyTypes.MASONRY,null,null)
    , WATERMILL(8,120, 2, TechnologyTypes.THE_WHEEL,null,null)
    , ARMORY(9,130, 3, TechnologyTypes.IRON_WORKING, new ArrayList<>(List.of(BuildingTypes.BARRACKS)),null)
    , BURIAL_TOMB(11,120, 0, TechnologyTypes.PHILOSOPHY,null,null)
    , CIRCUS(9,150, 3, TechnologyTypes.HORSEBACK_RIDING,null,new ArrayList<>(Arrays.asList(ResourceTypes.IVORY,ResourceTypes.HORSES)))
    , COLOSSEUM(7,150, 3, TechnologyTypes.CONSTRUCTION,null,null)
    , COURTHOUSE(9,200, 5, TechnologyTypes.MATHEMATICS,null,null)
    , STABLE(12,100, 1, TechnologyTypes.HORSEBACK_RIDING,null,new ArrayList<>(Arrays.asList(ResourceTypes.HORSES)))
    , TEMPLE(13,120, 2, TechnologyTypes.PHILOSOPHY,new ArrayList<>(List.of(BuildingTypes.MONUMENT)),null)
    , CASTLE(14,200, 3, TechnologyTypes.CHIVALRY,new ArrayList<>(List.of(BuildingTypes.WALLS)),null)
    , FORGE(11,150, 2, TechnologyTypes.METAL_CASTING,null,new ArrayList<>(Arrays.asList(ResourceTypes.IRON)))
    , GARDEN(13,120, 2, TechnologyTypes.THEOLOGY,null,null)
    , MARKET(15,120, 0, TechnologyTypes.CURRENCY,null,null)
    , MINT(16,120, 0, TechnologyTypes.CURRENCY,null,null)
    , MONASTERY(14,120, 2, TechnologyTypes.THEOLOGY,null,null)
    , UNIVERSITY(13,200, 3, TechnologyTypes.EDUCATION,new ArrayList<>(List.of(BuildingTypes.LIBRARY)),null)
    , WORKSHOP(15,100, 2, TechnologyTypes.METAL_CASTING,null,null)
    , BANK(16,220, 0, TechnologyTypes.BANKING,new ArrayList<>(List.of(BuildingTypes.MARKET)),null)
    , MILITARY_ACADEMY(13,350, 3, TechnologyTypes.MILITARY_SCIENCE,new ArrayList<>(List.of(BuildingTypes.BARRACKS)),null)
    , OPERA_HOUSE(17,220, 3, TechnologyTypes.ARCHAEOLOGY,new ArrayList<>(Arrays.asList(BuildingTypes.TEMPLE,BuildingTypes.BURIAL_TOMB)),null)
    , MUSEUM(15,350, 3, TechnologyTypes.ARCHAEOLOGY,new ArrayList<>(List.of(BuildingTypes.OPERA_HOUSE)),null)
    , PUBLIC_SCHOOL(15,350, 3, TechnologyTypes.SCIENTIFIC_THEORY,new ArrayList<>(List.of(BuildingTypes.UNIVERSITY)),null)
    , SATRAPS_COURT(14,220, 0, TechnologyTypes.BANKING,new ArrayList<>(List.of(BuildingTypes.MARKET)),null)
    , THEATER(18,300, 5, TechnologyTypes.PRINTING_PRESS,new ArrayList<>(List.of(BuildingTypes.COLOSSEUM)),null)
    , WINDMILL(19,180, 2, TechnologyTypes.ECONOMICS,null,null)
    , ARSENAL(17,350, 3, TechnologyTypes.RAILROAD,new ArrayList<>(List.of(BuildingTypes.MILITARY_ACADEMY)),null)
    , BROADCAST_TOWER(20,600, 3, TechnologyTypes.RADIO,new ArrayList<>(List.of(BuildingTypes.MUSEUM)),null)
    , FACTORY(21,300, 3, TechnologyTypes.STEAM_POWER,null,new ArrayList<>(Arrays.asList(ResourceTypes.COAL)))
    , HOSPITAL(22,400, 2, TechnologyTypes.BIOLOGY,null,null)
    , MILITARY_BASE(23,450, 4, TechnologyTypes.TELEGRAPH,new ArrayList<>(List.of(BuildingTypes.CASTLE)),null)
    , STOCK_EXCHANGE(18,650, 0, TechnologyTypes.ELECTRICITY,new ArrayList<>(Arrays.asList(BuildingTypes.BANK,BuildingTypes.SATRAPS_COURT)),null) ;

    final int cost;

    final int turn;
    final int maintenance;

    final ArrayList<BuildingTypes> buildingRequirements;

    final ArrayList<ResourceTypes> resourceRequirements;
    final TechnologyTypes requirement;

    BuildingTypes(int turn ,int cost, int maintenance, TechnologyTypes requirement, ArrayList<BuildingTypes> buildingRequirements, ArrayList<ResourceTypes> resourceRequirements) {
        this.turn = turn;
        this.cost = cost;
        this.maintenance = maintenance;
        this.requirement = requirement;
        this.buildingRequirements = buildingRequirements;
        this.resourceRequirements = resourceRequirements;
    }

    public int getMeintenance() {
        return this.maintenance;
    }

    public int getTurn() {
        return turn;
    }

    public int getCost() {
        return cost;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public ArrayList<BuildingTypes> getBuildingRequirements() {
        return buildingRequirements;
    }

    public ArrayList<ResourceTypes> getResourceRequirements() {
        return resourceRequirements;
    }

    public TechnologyTypes getRequirement() {
        return requirement;
    }
}