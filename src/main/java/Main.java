import java.util.ArrayList;

import Model.Civilization;
import Model.Database;
import Model.Map;
import Model.Terrain;
import Model.User;

public class Main{
    public static void main(String[] args) {
     
      Map map = new Map();
      map.initializeMap();
      ArrayList<Terrain> terrains = new ArrayList<Terrain>();
      for(int i = 0; i < 20;i++){
        for(int j = 0; j < 16;j++){
          terrains.add(map.getTerrain()[i][j]);
        }
      }
      Civilization civilization = new Civilization(terrains, null, 0, 0, "A");
      User user = new User(null, null, null, civilization);
      Database database = new Database();
      database.addUser(user);
      String [][]  result = map.printMap(database);
      for(int i = 0; i < 20;i++){
        for(int j = 0; j < 6;j++){
          System.out.println(result[i][j]);
        }
      }

    }
}