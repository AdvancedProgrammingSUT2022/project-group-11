package View;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

import Controllers.DatabaseController;
import Enums.GameEnums;
import Model.User;

public class GameMenu {
    private DatabaseController databaseController;
    private ArrayList<User> users;

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

                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_UNIT)) != null) {
                        selectUnit(user, matcher);
                        while (this.databaseController.HasoneUnitBeenSelected()) {
                            input = scanner.nextLine();
                            if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_MOVETO)) != null) {
                                moveUnit(user, matcher);
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
                                    System.out.println(
                                            this.databaseController.changingTheStateOfAUnit("fortify until heal"));

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
                                    System.out
                                            .println(this.databaseController.changingTheStateOfAUnit("setup ranged"));

                                }

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_ATTACK)) != null) {

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FOUND_CITY)) != null) {

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_CANCEL_MISSION)) != null) {

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_WAKE)) != null) {
                                System.out.println(this.databaseController.changingTheStateOfAUnit("wake"));

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_DELETE)) != null) {
                                System.out.println(this.databaseController.changingTheStateOfAUnit("delete"));

                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
                                buildUnit(matcher);
                            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_REMOVE)) != null) {
                                if (matcher.group("subdivision").equals("JUNGLE")) {

                                } else if (matcher.group("subdivision").equals("ROUTE")) {

                                } else {
                                    System.out.println("INVALID COMMAND");
                                }

                            } else {
                                System.out.println("INVALID COMMAND");
                            }


                        }
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_CITY_NAME)) != null) {
                        // todo
                    } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_CITY_POSITION)) != null) {
                        // todo

                    } else if (input.equals("SHOW MAP")) {
                        String[][] result = this.databaseController.getMap()
                                .printMap(this.databaseController.getDatabase(), user);
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
        if (matcher.group("section").equals("RESEARCH")) {
            System.out.println(databaseController.researchInfo(user));
        } else if (matcher.group("section").equals("UNITS")) {
            System.out.println(databaseController.unitsInfo(user));

        } else if (matcher.group("section").equals("CITIIES")) {

        } else if (matcher.group("section").equals("DIPLOMACY")) {

        } else if (matcher.group("section").equals("VICTORY")) {

        } else if (matcher.group("section").equals("DEMOGRAPHICS")) {

        } else if (matcher.group("section").equals("NOTIFICATIONS")) {

        } else if (matcher.group("section").equals("MILITARY")) {

        } else if (matcher.group("section").equals("ECONOMIC")) {

        } else if (matcher.group("section").equals("DIPLOMATIC")) {

        } else if (matcher.group("section").equals("DEALS")) {

        } else {
            System.out.println("INVALID COMMAND");
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

    private void buildUnit(Matcher matcher) {
        if (matcher.group("subdivision").equals("ROAD")) {

        } else if (matcher.group("subdivision").equals("RAILROAD")) {

        } else if (matcher.group("subdivision").equals("FARM")) {

        } else if (matcher.group("subdivision").equals("MINE")) {

        } else if (matcher.group("subdivision").equals("TRADINGPOST")) {

        } else if (matcher.group("subdivision").equals("LUMBERMILL")) {

        } else if (matcher.group("subdivision").equals("PASTURE")) {

        } else if (matcher.group("subdivision").equals("CAMP")) {

        } else if (matcher.group("subdivision").equals("PLANTATION")) {

        } else if (matcher.group("subdivision").equals("QUARRY")) {

        } else {
            System.out.println("INVALID COMMAND");
        }
    }

    private void moveUnit(User user, Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        System.out.println(this.databaseController.unitMovement(x, y, user));

    }

}