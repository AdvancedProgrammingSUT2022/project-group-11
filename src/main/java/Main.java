
import java.util.ArrayList;
import java.util.Scanner;

import Controllers.DatabaseController;
import Model.Civilization;
import Model.Database;
import Model.Map;
import Model.Terrain;
import Model.User;
public class Main{
    public static void main(String[] args) {
        Database database = new Database();
        Map map = new Map();
        map.generateMap();
        ArrayList<Terrain> terrains = new ArrayList<Terrain>();
        for(int i = 0; i < map.getROW();i++){
          for(int j = 0; j < map.getCOL();j++){
            terrains.add(map.getTerrain()[i][j]);
          }
        }
        Civilization civilization = new Civilization(null, null,terrains, 0, 0,null, "A");
        User user = new User(null, null, null, civilization);
        database.addUser(user);
        database.setMap(map);
        String [][]  result = map.printMap(database, user);
        for(int i = 0; i < map.getROW();i++){
            for(int j = 0; j < map.getIteration();j++){
              System.out.println(result[i][j]);
            }
          }
    }
}