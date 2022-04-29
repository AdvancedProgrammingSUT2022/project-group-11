package MAP;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    
    @Mock
    River river;
    @Mock
    Terrain terrainOne;
    @Mock
    Terrain terrainTwo;
    @Test
    public void hasRiverTest() {
       Map map = new Map();
       River checkRiver = map.hasRiver(terrainOne, terrainTwo);
       Assertions.assertNull(checkRiver);
    }

}