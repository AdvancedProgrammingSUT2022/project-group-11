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
    IMPROVEMENT_REPAIR("^UNIT\\sREPAIR"),
    UNIT_REPAIR("^UNIT\\sREPAIR$"),
    CREATE_UNIT("^CONSTRUCT UNIT (?<unitName>[A-Z_a-z]+)"),
    MAP_SHOW_POSITION("^MAP\\sSHOW\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    MAP_SHOW_CITYNAME("^MAP\\sSHOW\\s(?<subdivision>\\S+)$"),
    MAP_MOVE("^MAP\\sMOVE\\s(?<subdivision>\\S+)\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    INFO_RESEARCH("INFO RESEARCH"),
    SELECT_TECHNOLOGY("^SELECT\\sTECHNOLOGY\\s(?<name>\\S+)$"),
    INCREASE_TURN("^INCREASE\\s-TURN\\s(?<amount>\\S+)$"),
    INCREASE_GOLD("^INCREASE\\s-GOLD\\s(?<amount>\\S+)$"),
    INCREASE_FOOD("^INCREASE\\s-FOOD\\s(?<amount>\\S+)$"),
    INCREASE_HAPPINESS("^INCREASE\\s-HAPPINESS\\s(?<amount>\\S+)$"),
    INCREASE_SCIENCE("^INCREASE\\s-SCIENCE\\s(?<amount>\\S+)$"),
    BUY_TECHNOLOGY("^BUY\\sTECHNOLOGY\\s(?<name>\\S+)$"),
    COMBAT_UNIT_CHEAT_MOVE("^COMBAT\\sUNIT\\sCHEAT\\sMOVE\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    NON_COMBAT_UNIT_CHEAT_MOVE("^NONCOMBAT\\sUNIT\\sCHEAT\\sMOVE\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    BUY_CHEAT_TILE("^BUY\\sCHEAT\\sTILE\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    SET_CHEAT_UNIT("^SET\\sCHEAT\\sUNIT\\s(?<name>\\S+)\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    SET_CHEAT_IMPROVEMENT("^SET\\sCHEAT\\sIMPROVEMENT\\s(?<name>\\S+)\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    SET_CHEAT_RESOURCE("^SET\\sCHEAT\\sRESOURCE\\s(?<name>\\S+)\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    SET_CHEAT_TERRAIN_FEATURE_TYPE("^SET\\sCHEAT\\sTERRAIN\\sFEATURE\\sTYPE\\s(?<name>\\S+)\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    SET_CHEAT_TERRAIN_TYPE("^SET\\sCHEAT\\sTERRAIN\\sTYPE\\s(?<name>\\S+)\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    DELETE_CHEAT_IMPROVEMENT("^UNIT\\sCHEAT\\sREMOVE\\s(?<x>\\d+)\\s(?<y>\\d+)$"),
    REPAIR_CHEAT_IMPROVEMENT("^UNIT\\sCHEAT\\sREPAIR\\s(?<x>\\d+)\\s(?<y>\\d+)$"),

    INTEGER("^-?\\d+$");

    public String regex;

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