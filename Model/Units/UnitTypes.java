package Model.Units;

import Model.Resources.ResourceTypes;
import Model.Technologies.TechnologyTypes;

public enum UnitTypes {
    ARCHER(70, CombatTypes.ARCHERY, 4, 6, 2, 2, null, TechnologyTypes.ARCHERY),
    CHARIOT_ARCHER(60, CombatTypes.MOUNTED, 3, 6, 2, 4, ResourceTypes.HORSES, TechnologyTypes.THE_WHEEL),
    SCOUT(25, CombatTypes.RECON, 4, 0, 0, 2, null, null),
    SETTLER(89, CombatTypes.CIVILIAN, 0, 0, 0, 2, null, null),
    SPEARMAN(50, CombatTypes.MELEE, 7, 0, 0, 2, null, null),
    WARRIOR(40, CombatTypes.MELEE, 6, 0, 0, 2, null, null),
    WORKER(70, CombatTypes.CIVILIAN, 0, 0, 0, 2, null, null),
    CATAPULT(100, CombatTypes.SIEGE, 4, 14, 2, 2, ResourceTypes.IRON, TechnologyTypes.MATHEMATICS),
    HORSESMAN(80, CombatTypes.MOUNTED, 12, 0, 0, 4, ResourceTypes.HORSES, TechnologyTypes.HORSESBACK_RIDING),
    SWORDSMAN(80, CombatTypes.MELEE, 11, 0, 0, 2, ResourceTypes.IRON, TechnologyTypes.IRON_WORKING),
    CROSSBOWMAN(120, CombatTypes.ARCHERY, 6, 12, 2, 2, null, TechnologyTypes.MACHINERY),
    KNIGHT(150, CombatTypes.MOUNTED, 18, 0, 0, 3, ResourceTypes.HORSES, TechnologyTypes.CHIVALRY),
    LONGSWORDSMAN(150, CombatTypes.MELEE, 18, 0, 0, 3, ResourceTypes.IRON, TechnologyTypes.STEEL),
    PIKEMAN(100, CombatTypes.MELEE, 10, 0, 0, 2, null, TechnologyTypes.CIVIL_SERVICE),
    TREBUCHET(170, CombatTypes.SIEGE, 6, 20, 2, 2, ResourceTypes.IRON, TechnologyTypes.PHYSICS),
    CANNON(250, CombatTypes.SIEGE, 10, 26, 2, 2, null, TechnologyTypes.CHEMISTRY),
    CAVALRY(260, CombatTypes.MOUNTED, 25, 0, 0, 3, ResourceTypes.HORSES, TechnologyTypes.MILITARYSCIENCE),
    LANCER(220, CombatTypes.MOUNTED, 22, 0, 0, 4, ResourceTypes.HORSES, TechnologyTypes.METALLURGY),
    MUSKETMAN(120, CombatTypes.GUNPOWDER, 16, 0, 0, 2, null, TechnologyTypes.GUNPOWDER),
    RIFLEMAN(200, CombatTypes.GUNPOWDER, 25, 0, 0, 2, null, TechnologyTypes.RIFLING),
    ANTI_TANKGUN(300, CombatTypes.GUNPOWDER, 32, 0, 0, 2, null, TechnologyTypes.REPLACEABLE_PARTS),
    ARTILLERY(420, CombatTypes.SIEGE, 16, 32, 3, 2, null, TechnologyTypes.DYNAMITE),
    INFANTRY(300, CombatTypes.GUNPOWDER, 36, 0, 0, 2, null, TechnologyTypes.REPLACEABLE_PARTS),
    PANZER(450, CombatTypes.ARMORED, 60, 0, 0, 5, null, TechnologyTypes.COMBUSTION),
    TANK(450, CombatTypes.ARMORED, 50, 0, 0, 4, null, TechnologyTypes.COMBUSTION);

    private final int cost;
    private final CombatTypes combatTypes;
    private final int combatStrengh;
    private final int rangedCombatStrengh;
    private final int Range;
    private final int movement;
    private final ResourceTypes resourceRequirements;
    private final TechnologyTypes technologyRequirements;

    UnitTypes(int cost, CombatTypes combatTypes, int combatStrengh, int rangedCombatStrengh, int range, int movement,
            ResourceTypes resourceRequirements, TechnologyTypes technologyRequirements) {
        this.cost = cost;
        this.combatTypes = combatTypes;
        this.combatStrengh = combatStrengh;
        this.rangedCombatStrengh = rangedCombatStrengh;
        Range = range;
        this.movement = movement;
        this.resourceRequirements = resourceRequirements;
        this.technologyRequirements = technologyRequirements;

    }


}