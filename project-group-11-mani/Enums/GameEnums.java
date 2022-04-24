package Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameEnums {
    INFO("^INFO\\s(?<section>\\S+)$"),
    SELECT_UNIT("^SELECT\\sUNIT\\s(?<subdivision>\\S+)\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    SELECT_CITY_POSITION("^SELECT\\sCITY\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    SELECT_CITY_NAME("^SELECT\\sCITY\\s(?<subdivision>\\S+)$"),
    UNIT_MOVETO("^UNIT\\sMOVETO\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    UNIT_SLEEP("^UNIT\\sSLEEP$"),
    UNIT_ALERT("^UNIT\\sALERT$"),
    UNIT_FORTIFY("^UNIT\\sFORTIFY$"),
    UNIT_FORTIFY_HEAL("^UNIT\\sFORTIFY\\sHEAL$"),
    UNIT_GARRISON("^UNIT\\sGARRISON$"),
    UNIT_SETUP_RANGED("^UNIT\\sSETUP\\sRANGED$"),
    UNIT_ATTACK("^UNIT\\sATTACK\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    UNIT_FOUND_CITY("^UNIT\\sFOUND\\sCITY$"),
    UNIT_CANCEL_MISSION("^UNIT\\sCANCEL\\sMISSION$"),
    UNIT_WAKE("^UNIT\\sWAKE$"),
    UNIT_DELETE("^UNIT\\sDELETE$"),
    UNIT_BUILD("^UNIT\\sBUILD\\s(?<subdivision>\\S+)$"),
    UNIT_REMOVE("^UNIT\\sREMOVE\\s(?<subdivision>\\S+)$"),
    UNIT_REPAIR("^UNIT\\sREPAIR$"),
    CREATE_UNIT("^CONSTRUCT UNIT (?<unitName>[A-Za-z]+)"),
    
    INTEGER("^-?\\d+$");

    private String regex;

    GameEnums(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameEnums command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

}