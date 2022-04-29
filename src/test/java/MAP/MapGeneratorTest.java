package MAP;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Model.Database;
import Model.Terrain;
import Model.User;

@ExtendWith(MockitoExtension.class)
public class MapGeneratorTest{
    @Mock
    Terrain Terrains[][];
    @Mock 
    Database database;
    @Mock 
    User user;
    
    
}