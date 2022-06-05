package com.example.civilization.Model;

import com.example.civilization.Model.Technologies.Technology;
import com.example.civilization.Model.Technologies.TechnologyTypes;
import com.example.civilization.Model.Units.NonCombatUnit;
import com.example.civilization.Model.Units.UnitTypes;

import java.util.Random;

public class Ruins {

    public Ruins(int x, int y, Civilization civilization, Map map) {

        Random ran = new Random();
        int nxt = ran.nextInt(2);
        if (nxt == 0) {
            if (map.getTerrain()[x][y].getNonCombatUnit() == null) {
                NonCombatUnit settler = new NonCombatUnit(x, y, 0, 0, 0, 0, false, false, UnitTypes.SETTLER, false);
                civilization.getUnits().add(settler);
                map.getTerrain()[x][y].setNonCombatUnit(settler);
                System.out.println("settler added");
            }

        } else {
            if (map.getTerrain()[x][y].getNonCombatUnit() == null) {
                NonCombatUnit worker = new NonCombatUnit(x, y, 0, 0, 0, 0, false, false, UnitTypes.WORKER, false);
                civilization.getUnits().add(worker);
                map.getTerrain()[x][y].setNonCombatUnit(worker);
                System.out.println("worker added");
            }
        }

        nxt = ran.nextInt(civilization.getCities().size());
        civilization.getCities().get(nxt).setGold(civilization.getCities().get(nxt).getGold() + ran.nextInt(200));
        TechnologyTypes value = TechnologyTypes.values()[new Random().nextInt(TechnologyTypes.values().length)];
        boolean isNew = true;
        for (Technology technology : civilization.getTechnologies()) {
            if (technology.getTechnologyType().equals(value)) {
                System.out.println("you have this tech");
                isNew = false;
                break;
            }
        }
        if (isNew) {
            Technology technology = new Technology(false, 0, value, true);
            civilization.getTechnologies().add(technology);
            System.out.println("Tech added");
        }


    }
}
