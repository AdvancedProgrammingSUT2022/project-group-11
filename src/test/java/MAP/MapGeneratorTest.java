package MAP;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Model.Database;
import Model.GameMapGenerator;
import Model.Map;
import Model.River;
import Model.Terrain;
import Model.User;

@ExtendWith(MockitoExtension.class)
public class MapGeneratorTest {
   
  //  @Mock
  //  Database database;
  //  @Mock
  //  User user;
 //   @Mock
  //  String[][] Printmap;
    @Mock
    ArrayList<River> rivers;
   
    @Mock
    River river;
    @Mock
    Terrain terrainOne;
    @Mock
    Terrain terrainTwo;
    @Mock 
    Map map;
    @Test
    public void hasRiverTest() {
      GameMapGenerator mapGenerator = new GameMapGenerator(map.getTerrain(), map.getRiver(), 32, 16, 6);
       River checkRiver = mapGenerator.hasRiver(terrainOne, terrainTwo);
       Assertions.assertNull(checkRiver);
      
    }

}