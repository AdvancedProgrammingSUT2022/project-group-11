package MAP;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import Model.Map;
import Model.River;
import Model.Terrain;

@ExtendWith(MockitoExtension.class)
public class MapGeneratorTest {

    @Mock
    River river;
    @Mock
    Terrain terrainOne;
    @Mock
    Terrain terrainTwo;
    @Mock
    ArrayList<River> rivers;
    @Test
    public void hasRiverTest() {
        Map map = new Map();
        rivers = new ArrayList<River>();
        river = new River(terrainOne, terrainTwo);
        rivers.add(river);
        map.setRiver(rivers);
        River checkRiver = map.hasRiver(terrainOne, terrainTwo);
        assertNotNull(checkRiver);
    }

}