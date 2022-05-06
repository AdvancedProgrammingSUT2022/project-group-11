package Model.Buildings;

import Model.Technologies.TechnologyTypes;

public enum BuildingTypes {
    BARRACKS(80, 1, TechnologyTypes.BRONZE_WORKING) {

    }, GRANARY(100, 1, TechnologyTypes.POTTERY) {

    }, LIBRARY(80, 1, TechnologyTypes.WRITING) {

    }, MONUMENT(60, 1, null) {

    }, WALLS(100, 1, TechnologyTypes.MASONRY) {

    }, WATERMILL(120, 2, TechnologyTypes.THE_WHEEL) {

    }, ARMORY(130, 3, TechnologyTypes.IRON_WORKING) {

    }, BURIAL_TOMB(120, 0, TechnologyTypes.PHILOSOPHY) {

    }, CIRCUS(150, 3, TechnologyTypes.HORSEBACK_RIDING) {

    }, COLOSSEUM(150, 3, TechnologyTypes.CONSTRUCTION) {

    }, COURTHOUSE(200, 5, TechnologyTypes.MATHEMATICS) {

    }, STABLE(100, 1, TechnologyTypes.HORSEBACK_RIDING) {

    }, TEMPLE(120, 2, TechnologyTypes.PHILOSOPHY) {

    }, CASTLE(200, 3, TechnologyTypes.CHIVALRY) {

    }, FORGE(150, 2, TechnologyTypes.METAL_CASTING) {

    }, GARDEN(120, 2, TechnologyTypes.THEOLOGY) {

    }, MARKET(120, 0, TechnologyTypes.CURRENCY) {

    }, MINT(120, 0, TechnologyTypes.CURRENCY) {

    }, MONASTERY(120, 2, TechnologyTypes.THEOLOGY) {

    }, UNIVERSITY(200, 3, TechnologyTypes.EDUCATION) {

    }, WORKSHOP(100, 2, TechnologyTypes.METAL_CASTING) {

    }, BANK(220, 0, TechnologyTypes.BANKING) {

    }, MILITARY_ACADEMY(350, 3, TechnologyTypes.MILITARY_SCIENCE) {

    }, MUSEUM(350, 3, TechnologyTypes.ARCHAEOLOGY) {

    }, OPERA_HOUSE(220, 3, TechnologyTypes.ARCHAEOLOGY) {

    }, PUBLIC_SCHOOL(350, 3, TechnologyTypes.SCIENTIFIC_THEORY) {

    }, SATRAPS_COURT(220, 0, TechnologyTypes.BANKING) {

    }, THEATER(300, 5, TechnologyTypes.PRINTING_PRESS) {

    }, WINDMILL(180, 2, TechnologyTypes.ECONOMICS) {

    }, ARSENAL(350, 3, TechnologyTypes.RAILROAD) {

    }, BROADCAST_TOWER(600, 3, TechnologyTypes.RADIO) {

    }, FACTORY(300, 3, TechnologyTypes.STEAM_POWER) {

    }, HOSPITAL(400, 2, TechnologyTypes.BIOLOGY) {

    }, MILITARY_BASE(450, 4, TechnologyTypes.TELEGRAPH) {

    }, STOCK_EXCHANGE(650, 0, TechnologyTypes.ELECTRICITY) {

    };

    final int cost;
    final int maintenance;
    final TechnologyTypes requirement;

    BuildingTypes(int cost, int maintenance, TechnologyTypes requirement) {
        this.cost = cost;
        this.maintenance = maintenance;
        this.requirement = requirement;
    }

    public int getMeintenance() {
        return this.maintenance;
    }
}