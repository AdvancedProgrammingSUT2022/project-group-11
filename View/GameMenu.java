package View;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

import Controller.DatabaseController;
import Enums.GameEnums;
import Model.Map;
import Model.User;

public class GameMenu {
    private DatabaseController databaseController;
    private ArrayList<User> users;

    public GameMenu(DatabaseController databaseController, ArrayList<User> users) {
        this.databaseController = databaseController;
        this.users = users;
    }

    public void run(Scanner scanner) {

        for (User user : users) {
            while (this.databaseController.isAllTasksFinished(user)) {

                Matcher matcher;
                String input = scanner.nextLine();
                // input.replaceFirst("^\\s*", "");
                // input = input.trim().replaceAll("\\s+", " ");
                if ((matcher = GameEnums.getMatcher(input, GameEnums.INFO)) != null) {

                    showInfo(matcher);

                } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_UNIT)) != null) {
                    selectUnit(matcher);
                    while (this.databaseController.HasoneUnitBeenSelected()) {
                        input = scanner.nextLine();
                        if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_MOVETO)) != null) {
                            // todo

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_SLEEP)) != null) {
                            this.databaseController.changingTheStateOfAUnit(user, "sleep");
                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_ALERT)) != null) {
                            if (this.databaseController.getSelectedCombatUnit() == null) {
                                System.out.println("this unit is not a combat unit");
                            } else {
                                System.out.println(this.databaseController.changingTheStateOfAUnit(user, "alert"));

                            }

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FORTIFY)) != null) {
                            if (this.databaseController.getSelectedCombatUnit() == null) {
                                System.out.println("this unit is not a combat unit");
                            } else {
                                System.out.println(this.databaseController.changingTheStateOfAUnit(user, "fortify"));

                            }

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FORTIFY_HEAL)) != null) {
                            if (this.databaseController.getSelectedCombatUnit() == null) {
                                System.out.println("this unit is not a combat unit");
                            } else {
                                System.out.println(
                                        this.databaseController.changingTheStateOfAUnit(user, "fortify until heal"));

                            }

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_GARRISON)) != null) {
                            if (this.databaseController.getSelectedCombatUnit() == null) {
                                System.out.println("this unit is not a combat unit");
                            } else {
                                System.out.println(this.databaseController.changingTheStateOfAUnit(user, "garrison"));

                            }

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_SETUP_RANGED)) != null) {
                            if (this.databaseController.getSelectedCombatUnit() == null) {
                                System.out.println("this unit is not a combat unit");
                            } else if (this.databaseController.getSelectedCombatUnit() != null) {
                                System.out
                                        .println(this.databaseController.changingTheStateOfAUnit(user, "setup ranged"));

                            }

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_ATTACK)) != null) {

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FOUND_CITY)) != null) {

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_CANCEL_MISSION)) != null) {

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_WAKE)) != null) {
                            System.out.println(this.databaseController.changingTheStateOfAUnit(user, "wake"));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_DELETE)) != null) {
                            System.out.println(this.databaseController.changingTheStateOfAUnit(user, "delete"));

                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_BUILD)) != null) {
                            buildUnit(matcher);
                        } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_REMOVE)) != null) {
                            if (matcher.group("subdivision").equals("JUNGLE")) {

                            } else if (matcher.group("subdivision").equals("ROUTE")) {

                            } else {
                                System.out.println("INVALID COMMAND");
                            }

                        }
                    }

                } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_CITY_NAME)) != null) {
                    // todo
                } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_CITY_POSITION)) != null) {
                    // todo

                } else {
                    System.out.println("INVALID COMMAND");
                }
            }
        }

    }

    private void showInfo(Matcher matcher) {
        if (matcher.group("section").equals("RESEARCH")) {

        } else if (matcher.group("section").equals("UNITS")) {

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

    private void selectUnit(Matcher matcher) {
        if (matcher.group("subdivision").equals("COMBAT")) {
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));
            Map map = this.databaseController.getMap();
            int mapRows = map.getROW();
            int mapColumns = map.getCOL();
            if (x > mapRows || x < 0 || y > mapColumns || y < 0) {
                System.out.println("there is no tile with these coordinates");
            } else if (map.getTiles()[x][y].getCombatUnit() == null) {
                System.out.println("there is no combat unit in this tile");
            } else {
                this.databaseController.selectAndDeslectCombatUnit(x, y);
            }
        } else if (matcher.group("subdivision").equals("NONCOMBAT")) {
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));
            Map map = this.databaseController.getMap();
            int mapRows = map.getROW();
            int mapColumns = map.getCOL();
            if (x > mapRows || x < 0 || y > mapColumns || y < 0) {
                System.out.println("there is no tile with these coordinates");
            } else if (map.getTiles()[x][y].getNonCombatUnit() == null) {
                System.out.println("there is no non combat unit in this tile");

            } else {
                this.databaseController.selectAndDeslectNonCombatUnit(x, y);
            }
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

}
