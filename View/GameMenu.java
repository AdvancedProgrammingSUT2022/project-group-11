package View;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

import Controller.DatabaseController;
import Enums.GameEnums;

public class GameMenu {
    private DatabaseController databaseController;
    private String username;

    public GameMenu(DatabaseController databaseController, String username) {
        this.databaseController = databaseController;
        this.username = username;
    }

    public void run(Scanner scanner) {
        String input, input2;

        while (true) {
            Matcher matcher;
            input = scanner.nextLine();
            // input.replaceFirst("^\\s*", "");
            // input = input.trim().replaceAll("\\s+", " ");
            if ((matcher = GameEnums.getMatcher(input, GameEnums.INFO)) != null) {

                showInfo(matcher);

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_UNIT)) != null) {
                selectUnit(matcher);

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_CITY_NAME)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.SELECT_CITY_POSITION)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_MOVETO)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_SLEEP)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_ALERT)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FORTIFY)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FORTIFY_HEAL)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_GARRISON)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_SETUP_RANGED)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_ATTACK)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_FOUND_CITY)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_CANCEL_MISSION)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_WAKE)) != null) {

            } else if ((matcher = GameEnums.getMatcher(input, GameEnums.UNIT_DELETE)) != null) {

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

        } else if (matcher.group("subdivision").equals("NONCOMBAT")) {

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
