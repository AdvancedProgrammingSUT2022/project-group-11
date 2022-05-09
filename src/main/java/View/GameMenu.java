package View;

import Controllers.DatabaseController;
import Enums.GameEnums;
import Model.Improvements.ImprovementTypes;
import Model.Resources.ResourceTypes;
import Model.Technologies.TechnologyTypes;
import Model.TerrainFeatures.TerrainFeatureTypes;
import Model.Terrains.TerrainTypes;
import Model.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private final DatabaseController databaseController;
    private final ArrayList<User> users;

    public GameMenu(DatabaseController databaseController, ArrayList<User> users) {
        this.databaseController = databaseController;
        this.users = users;
    }

    public void run(Scanner scanner) {

        this.databaseController.getMap().generateMap();
        this.databaseController.setCivilizations(users);

        while (true) {
            for (User user : users) {
                System.out.println(user.getUsername() + "'s turn");
                this.databaseController.setAllUnitsUnfinished(user);
                while (!this.databaseController.isAllTasksFinished(user)) {

                    Matcher matcher;
                    String input = scanner.nextLine();
                    // input.replaceFirst("^\\s*", "");
                    // input = input.trim().replaceAll("\\s+", " ");
                    if ((matcher = GameEnums.getMatcher(input, GameEnums.INFO)) != null) {

                        showInfo(matcher, user);

                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_TECHNOLOGY)) != null) {
                        selectTechnology(matcher, user);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_TURN)) != null) {
                        increaseTurnCheat(matcher);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_GOLD)) != null) {
                        increaseGoldCheat(user, matcher);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_HAPPINESS)) != null) {
                        increaseHappinessCheat(user, matcher);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.INCREASE_SCIENCE)) != null) {
                        increaseScienceCheat(user, matcher);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_TECHNOLOGY)) != null) {
                        buyTechnologyCheat(matcher, user);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.BUY_CHEAT_TILE)) != null) {
                        buyCheatTile(user, matcher);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_UNIT)) != null) {
                        setCheatUnit(user, matcher);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_IMPROVEMENT)) != null) {
                        setCheatImprovement(matcher);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_RESOURCE)) != null) {
                        setCheatResource(matcher);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_FEATURE_TYPE)) != null) {
                        setCheatTerrainFeature(matcher);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SET_CHEAT_TERRAIN_TYPE)) != null) {
                        setCheatTerrainType(matcher);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.DELETE_CHEAT_IMPROVEMENT)) != null) {
                        deleteImprovementCheat(matcher);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.REPAIR_CHEAT_IMPROVEMENT)) != null) {
                        repairImprovementCheat(matcher);
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_UNIT)) != null) {
                        selectUnit(user, matcher);
                        while (this.databaseController.HasOneUnitBeenSelected()) {
                            input = scanner.nextLine();
                            if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_MOVETO)) != null) {
                                moveUnit(user, matcher);
                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_SLEEP)) != null) {
                                this.databaseController.changingTheStateOfAUnit("sleep");
                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.COMBAT_UNIT_CHEAT_MOVE)) != null) {
                                cheatMoveNonCombatUnit(matcher);
                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.NON_COMBAT_UNIT_CHEAT_MOVE)) != null) {
                                cheatMoveNonCombatUnit(matcher);
                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_SLEEP)) != null) {
                                this.databaseController.changingTheStateOfAUnit("sleep");
                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_ALERT)) != null) {
                                if (this.databaseController.getSelectedCombatUnit() == null) {
                                    System.out.println("this unit is not a combat unit");
                                } else {
                                    System.out.println(this.databaseController.changingTheStateOfAUnit("alert"));

                                }

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FORTIFY)) != null) {
                                if (this.databaseController.getSelectedCombatUnit() == null) {
                                    System.out.println("this unit is not a combat unit");
                                } else {
                                    System.out.println(this.databaseController.changingTheStateOfAUnit("fortify"));

                                }

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FORTIFY_HEAL)) != null) {
                                if (this.databaseController.getSelectedCombatUnit() == null) {
                                    System.out.println("this unit is not a combat unit");
                                } else {
                                    System.out.println(this.databaseController.changingTheStateOfAUnit("fortify until heal"));

                                }

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_GARRISON)) != null) {
                                if (this.databaseController.getSelectedCombatUnit() == null) {
                                    System.out.println("this unit is not a combat unit");
                                } else {
                                    System.out.println(this.databaseController.changingTheStateOfAUnit("garrison"));

                                }

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_SETUP_RANGED)) != null) {
                                if (this.databaseController.getSelectedCombatUnit() == null) {
                                    System.out.println("this unit is not a combat unit");
                                } else if (this.databaseController.getSelectedCombatUnit() != null) {
                                    System.out.println(this.databaseController.changingTheStateOfAUnit("setup ranged"));

                                }

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_ATTACK)) != null) {

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FOUND_CITY)) != null) {

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_CANCEL_MISSION)) != null) {

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_WAKE)) != null) {
                                System.out.println(this.databaseController.changingTheStateOfAUnit("wake"));

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_DELETE)) != null) {
                                System.out.println(this.databaseController.changingTheStateOfAUnit("delete"));

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
                                buildUnit(matcher, user);
                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_REMOVE)) != null) {
                                deleteUnit(matcher, user);

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.IMPROVEMENT_REPAIR)) != null) {
                                repairImprovement();

                            } else {
                                System.out.println("INVALID COMMAND");
                            }


                        }
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_CITY_NAME)) != null) {
                        // todo
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_CITY_POSITION)) != null) {
                        // todo

                    } else if (input.equals("SHOW MAP")) {
                        String[][] result = this.databaseController.getMap().printMap(this.databaseController.getDatabase(), user);
                        for (int i = 0; i < this.databaseController.getMap().getROW(); i++) {
                            for (int j = 0; j < this.databaseController.getMap().getIteration(); j++) {
                                System.out.println(result[i][j]);
                            }
                        }
                    } else {
                        System.out.println("INVALID COMMAND");
                    }
                }
                this.databaseController.movementOfAllUnits(user);
                this.databaseController.setTerrainsOfEachCivilization(user);
            }

        }

    }

    private void showInfo(Matcher matcher, User user) {
        switch (matcher.group("section")) {
            case "RESEARCH":
                System.out.println(databaseController.researchInfo(user));
                break;
            case "UNITS":
                System.out.println(databaseController.unitsInfo(user));

                break;
            case "CITIES":

                break;
            case "DIPLOMACY":

                break;
            case "VICTORY":

                break;
            case "DEMOGRAPHICS":

                break;
            case "NOTIFICATIONS":

                break;
            case "MILITARY":

                break;
            case "ECONOMIC":

                break;
            case "DIPLOMATIC":

                break;
            case "DEALS":

                break;
            default:
                System.out.println("INVALID COMMAND");
                break;
        }
    }

    private void selectUnit(User user, Matcher matcher) {
        if (matcher.group("subdivision").equals("COMBAT")) {
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));
            System.out.println(this.databaseController.selectAndDeselectCombatUnit(user, x, y));

        } else if (matcher.group("subdivision").equals("NONCOMBAT")) {
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));

            System.out.println(this.databaseController.selectAndDeselectNonCombatUnit(user, x, y));

        } else {
            System.out.println("INVALID COMMAND");
        }
    }

    private void buildUnit(Matcher matcher, User user) {
        String name = matcher.group("subdivision");
        switch (name) {
            case "ROAD":
                System.out.println(this.databaseController.buildingAnImprovement(user, ImprovementTypes.ROAD));

                break;
            case "RAILROAD":
                System.out.println(this.databaseController.buildingAnImprovement(user, ImprovementTypes.RAILROAD));

                break;
            case "FARM":
                System.out.println(this.databaseController.buildingAnImprovement(user, ImprovementTypes.FARM));

                break;
            case "MINE":
                System.out.println(this.databaseController.buildingAnImprovement(user, ImprovementTypes.MINE));

                break;
            case "TRADINGPOST":
                System.out.println(this.databaseController.buildingAnImprovement(user, ImprovementTypes.TRADINGPOST));

                break;
            case "LUMBERMILL":
                System.out.println(this.databaseController.buildingAnImprovement(user, ImprovementTypes.LUMBERMILL));

                break;
            case "PASTURE":
                System.out.println(this.databaseController.buildingAnImprovement(user, ImprovementTypes.PASTURE));

                break;
            case "CAMP":
                System.out.println(this.databaseController.buildingAnImprovement(user, ImprovementTypes.CAMP));

                break;
            case "PLANTATION":
                System.out.println(this.databaseController.buildingAnImprovement(user, ImprovementTypes.PLANTATION));

                break;
            case "QUARRY":
                System.out.println(this.databaseController.buildingAnImprovement(user, ImprovementTypes.QUARRY));

                break;
            default:
                System.out.println("INVALID COMMAND");

        }

    }

    private void deleteUnit(Matcher matcher, User user) {
        String name = matcher.group("subdivision");
        switch (name) {
            case "FOREST":
                System.out.println(this.databaseController.deleteFeatures("FOREST"));

                break;
            case "JUNGLE":
                System.out.println(this.databaseController.deleteFeatures("JUNGLE"));

                break;
            case "MARSH":
                System.out.println(this.databaseController.deleteFeatures("MARSH"));

                break;
            case "ROUTE":
                System.out.println(this.databaseController.deleteFeatures("ROUTE"));

                break;
            default:
                System.out.println("INVALID COMMAND");

        }
    }

    public void repairImprovement() {
        System.out.println(this.databaseController.repairImprovement());

    }

    private void moveUnit(User user, Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        System.out.println(this.databaseController.unitMovement(x, y, user));

    }

    public void selectTechnology(Matcher matcher, User user) {
        String name = matcher.group("name");
        switch (name) {
            case "AGRICULTURE":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.AGRICULTURE));

                break;
            case "ANIMAL_HUSBANDRY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.ANIMAL_HUSBANDRY));

                break;
            case "ARCHERY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.ARCHERY));

                break;
            case "BRONZE_WORKING":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.BRONZE_WORKING));

                break;
            case "CALENDAR":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.CALENDAR));


                break;
            case "MASONRY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.MASONRY));


                break;
            case "MINING":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.MINING));


                break;
            case "POTTERY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.POTTERY));


                break;
            case "THE_WHEEL":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.THE_WHEEL));


                break;
            case "TRAPPING":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.TRAPPING));


                break;
            case "WRITING":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.WRITING));


                break;
            case "CONSTRUCTION":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.CONSTRUCTION));


                break;
            case "HORSEBACK_RIDING":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.HORSEBACK_RIDING));


                break;
            case "IRON_WORKING":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.IRON_WORKING));


                break;
            case "MATHEMATICS":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.MATHEMATICS));


                break;
            case "PHILOSOPHY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.PHILOSOPHY));


                break;
            case "CHIVALRY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.CHIVALRY));


                break;
            case "CIVIL_SERVICE":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.CIVIL_SERVICE));


                break;
            case "CURRENCY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.CURRENCY));


                break;
            case "EDUCATION":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.EDUCATION));


                break;
            case "ENGINEERING":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.ENGINEERING));

                break;
            case "MACHINERY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.MACHINERY));


                break;
            case "METAL_CASTING":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.METAL_CASTING));

                break;
            case "PHYSICS":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.PHYSICS));

                break;
            case "STEEL":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.STEEL));


                break;
            case "THEOLOGY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.THEOLOGY));


                break;
            case "ACOUSTICS":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.ACOUSTICS));


                break;
            case "ARCHAEOLOGY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.ARCHAEOLOGY));

                break;
            case "BANKING":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.BANKING));


                break;
            case "CHEMISTRY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.CHEMISTRY));


                break;
            case "ECONOMICS":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.ECONOMICS));


                break;
            case "FERTILIZER":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.FERTILIZER));


                break;
            case "GUNPOWDER":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.GUNPOWDER));


                break;
            case "METALLURGY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.METALLURGY));


                break;
            case "MILITARY_SCIENCE":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.MILITARY_SCIENCE));


                break;
            case "PRINTING_PRESS":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.PRINTING_PRESS));


                break;
            case "SCIENTIFIC_THEORY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.SCIENTIFIC_THEORY));

                break;
            case "BIOLOGY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.BIOLOGY));

                break;
            case "COMBUSTION":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.COMBUSTION));


                break;
            case "DYNAMITE":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.DYNAMITE));


                break;
            case "ELECTRICITY":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.ELECTRICITY));


                break;
            case "RADIO":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.RADIO));

                break;
            case "RAILROAD":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.RAILROAD));


                break;
            case "REPLACEABLE_PARTS":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.REPLACEABLE_PARTS));


                break;
            case "STEAM_POWER":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.STEAM_POWER));


                break;
            case "TELEGRAPH":
                System.out.println(this.databaseController.choosingATechnologyToStudy(user, TechnologyTypes.TELEGRAPH));

                break;


        }


    }

    public void increaseTurnCheat(Matcher matcher) {

        int amount = Integer.parseInt(matcher.group("amount"));
        System.out.println(this.databaseController.increaseTurnCheat(amount));

    }

    public void increaseGoldCheat(User user, Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));
        System.out.println(this.databaseController.increaseGoldCheat(user, amount));

    }

    public void increaseHappinessCheat(User user, Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));
        System.out.println(this.databaseController.increaseHappinessCheat(user, amount));

    }

    public void increaseScienceCheat(User user, Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));
        System.out.println(this.databaseController.increaseScienceCheat(user, amount));

    }

    public void buyTechnologyCheat(Matcher matcher, User user) {
        String name = matcher.group("name");
        switch (name) {
            case "AGRICULTURE":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.AGRICULTURE));

                break;
            case "ANIMAL_HUSBANDRY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.ANIMAL_HUSBANDRY));

                break;
            case "ARCHERY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.ARCHERY));

                break;
            case "BRONZE_WORKING":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.BRONZE_WORKING));

                break;
            case "CALENDAR":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.CALENDAR));


                break;
            case "MASONRY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.MASONRY));


                break;
            case "MINING":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.MINING));


                break;
            case "POTTERY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.POTTERY));


                break;
            case "THE_WHEEL":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.THE_WHEEL));


                break;
            case "TRAPPING":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.TRAPPING));


                break;
            case "WRITING":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.WRITING));


                break;
            case "CONSTRUCTION":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.CONSTRUCTION));


                break;
            case "HORSEBACK_RIDING":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.HORSEBACK_RIDING));


                break;
            case "IRON_WORKING":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.IRON_WORKING));


                break;
            case "MATHEMATICS":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.MATHEMATICS));


                break;
            case "PHILOSOPHY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.PHILOSOPHY));


                break;
            case "CHIVALRY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.CHIVALRY));


                break;
            case "CIVIL_SERVICE":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.CIVIL_SERVICE));


                break;
            case "CURRENCY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.CURRENCY));


                break;
            case "EDUCATION":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.EDUCATION));


                break;
            case "ENGINEERING":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.ENGINEERING));

                break;
            case "MACHINERY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.MACHINERY));


                break;
            case "METAL_CASTING":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.METAL_CASTING));

                break;
            case "PHYSICS":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.PHYSICS));

                break;
            case "STEEL":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.STEEL));


                break;
            case "THEOLOGY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.THEOLOGY));


                break;
            case "ACOUSTICS":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.ACOUSTICS));


                break;
            case "ARCHAEOLOGY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.ARCHAEOLOGY));

                break;
            case "BANKING":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.BANKING));


                break;
            case "CHEMISTRY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.CHEMISTRY));


                break;
            case "ECONOMICS":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.ECONOMICS));


                break;
            case "FERTILIZER":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.FERTILIZER));


                break;
            case "GUNPOWDER":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.GUNPOWDER));


                break;
            case "METALLURGY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.METALLURGY));


                break;
            case "MILITARY_SCIENCE":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.MILITARY_SCIENCE));


                break;
            case "PRINTING_PRESS":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.PRINTING_PRESS));


                break;
            case "SCIENTIFIC_THEORY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.SCIENTIFIC_THEORY));

                break;
            case "BIOLOGY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.BIOLOGY));

                break;
            case "COMBUSTION":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.COMBUSTION));


                break;
            case "DYNAMITE":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.DYNAMITE));


                break;
            case "ELECTRICITY":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.ELECTRICITY));


                break;
            case "RADIO":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.RADIO));

                break;
            case "RAILROAD":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.RAILROAD));


                break;
            case "REPLACEABLE_PARTS":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.REPLACEABLE_PARTS));


                break;
            case "STEAM_POWER":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.STEAM_POWER));


                break;
            case "TELEGRAPH":
                System.out.println(this.databaseController.buyTechnologyCheat(user, TechnologyTypes.TELEGRAPH));

                break;
        }

    }


    public void repairImprovementCheat(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        System.out.println(this.databaseController.repairCheatImprovement(x, y));


    }

    public void deleteImprovementCheat(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        System.out.println(this.databaseController.deleteCheatImprovement(x, y));


    }

    public void setCheatTerrainType(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String name = matcher.group("name");
        switch (name) {
            case "DESERT":
                System.out.println(this.databaseController.setCheatTerrainType(TerrainTypes.DESERT, x, y));
                break;
            case "GRASSLAND":
                System.out.println(this.databaseController.setCheatTerrainType(TerrainTypes.GRASSLLAND, x, y));
                break;
            case "HILLS":
                System.out.println(this.databaseController.setCheatTerrainType(TerrainTypes.HILLS, x, y));
                break;
            case "MOUNTAIN":
                System.out.println(this.databaseController.setCheatTerrainType(TerrainTypes.MOUNTAIN, x, y));
                break;
            case "OCEAN":
                System.out.println(this.databaseController.setCheatTerrainType(TerrainTypes.OCEAN, x, y));
                break;
            case "PLAINS":
                System.out.println(this.databaseController.setCheatTerrainType(TerrainTypes.PLAINS, x, y));
                break;
            case "SNOW":
                System.out.println(this.databaseController.setCheatTerrainType(TerrainTypes.SNOW, x, y));
                break;
            case "TUNDRA":
                System.out.println(this.databaseController.setCheatTerrainType(TerrainTypes.TUNDRA, x, y));
                break;
        }


    }

    public void setCheatTerrainFeature(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String name = matcher.group("name");
        switch (name) {
            case "FLOODPLAINS":
                System.out.println(this.databaseController.setCheatTerrainFeatureType(TerrainFeatureTypes.FLOODPLAINS, x, y));
                break;
            case "FOREST":
                System.out.println(this.databaseController.setCheatTerrainFeatureType(TerrainFeatureTypes.FOREST, x, y));
                break;
            case "ICE":
                System.out.println(this.databaseController.setCheatTerrainFeatureType(TerrainFeatureTypes.ICE, x, y));
                break;
            case "JUNGLE":
                System.out.println(this.databaseController.setCheatTerrainFeatureType(TerrainFeatureTypes.JUNGLE, x, y));
                break;
            case "MARSH":
                System.out.println(this.databaseController.setCheatTerrainFeatureType(TerrainFeatureTypes.MARSH, x, y));
                break;
            case "OASIS":
                System.out.println(this.databaseController.setCheatTerrainFeatureType(TerrainFeatureTypes.OASIS, x, y));
                break;

        }
    }

    public void setCheatResource(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String name = matcher.group("name");
        switch (name) {
            case "BANANAS":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.BANANAS, x, y));
                break;
            case "CATTLE":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.CATTLE, x, y));
                break;
            case "COAL":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.COAL, x, y));
                break;
            case "COTTON":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.COTTON, x, y));
                break;
            case "DEER":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.DEER, x, y));
                break;

            case "DYES":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.DYES, x, y));
                break;
            case "FURS":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.FURS, x, y));
                break;
            case "GEMS":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.GEMS, x, y));
                break;
            case "GOLD":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.GOLD, x, y));
                break;
            case "HORSES":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.HORSES, x, y));
                break;
            case "INCENSE":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.INCENSE, x, y));
                break;
            case "IVORY":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.IVORY, x, y));
                break;
            case "IRON":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.IRON, x, y));
                break;
            case "MARBLE":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.MARBLE, x, y));
                break;
            case "SHEEP":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.SHEEP, x, y));
                break;
            case "SILK":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.SILK, x, y));
                break;
            case "SILVER":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.SILVER, x, y));
                break;
            case "SUGAR":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.SUGAR, x, y));
                break;
            case "WHEAT":
                System.out.println(this.databaseController.setCheatResource(ResourceTypes.WHEAT, x, y));
                break;


        }
    }

    public void setCheatImprovement(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String name = matcher.group("name");
        switch (name) {
            case "CAMP":
                System.out.println(this.databaseController.setCheatImprovement(ImprovementTypes.CAMP, x, y));
                break;
            case "FARM":
                System.out.println(this.databaseController.setCheatImprovement(ImprovementTypes.FARM, x, y));
                break;
            case "MINE":
                System.out.println(this.databaseController.setCheatImprovement(ImprovementTypes.MINE, x, y));
                break;
            case "PLANTATION":
                System.out.println(this.databaseController.setCheatImprovement(ImprovementTypes.PLANTATION, x, y));
                break;
            case "PASTURE":
                System.out.println(this.databaseController.setCheatImprovement(ImprovementTypes.PASTURE, x, y));
                break;

            case "LUMBERBILL":
                System.out.println(this.databaseController.setCheatImprovement(ImprovementTypes.LUMBERMILL, x, y));
                break;
            case "MANUFACTURY":
                System.out.println(this.databaseController.setCheatImprovement(ImprovementTypes.MANUFACTORY, x, y));
                break;
            case "QUARRY":
                System.out.println(this.databaseController.setCheatImprovement(ImprovementTypes.QUARRY, x, y));
                break;
            case "RAILROAD":
                System.out.println(this.databaseController.setCheatImprovement(ImprovementTypes.RAILROAD, x, y));
                break;
            case "ROAD":
                System.out.println(this.databaseController.setCheatImprovement(ImprovementTypes.ROAD, x, y));
                break;
            case "TRADINGPOST":
                System.out.println(this.databaseController.setCheatImprovement(ImprovementTypes.TRADINGPOST, x, y));
                break;

        }


    }

    public void setCheatUnit(User user, Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String name = matcher.group("name");
        System.out.println(this.databaseController.setCheatUnit(user, name, x, y));
    }

    public void buyCheatTile(User user, Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        System.out.println(this.databaseController.buyCheatTile(user, x, y));

    }

    public void cheatMoveCombatUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        this.databaseController.cheatMoveCombatUnit(x, y);
    }

    public void cheatMoveNonCombatUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        this.databaseController.cheatMoveNonCombatUnit(x, y);
    }

}
