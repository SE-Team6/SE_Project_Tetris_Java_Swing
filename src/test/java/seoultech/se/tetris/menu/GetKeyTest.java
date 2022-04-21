package seoultech.se.tetris.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetKeyTest {
    @Test
    void getLeftKeyCheckingValue(){
        GetKeyPanel gp = new GetKeyPanel();
        assertEquals(37,gp.checkingValue(8592));
    }
    @Test
    void getRightKeyCheckingValue(){
        GetKeyPanel gp = new GetKeyPanel();
        assertEquals(39,gp.checkingValue(8594));
    }
    @Test
    void getUPKeyCheckingValue(){
        GetKeyPanel gp = new GetKeyPanel();
        assertEquals(38,gp.checkingValue(8593));
    }
    @Test
    void getDownKeyCheckingValue(){
        GetKeyPanel gp = new GetKeyPanel();
        assertEquals(40,gp.checkingValue(8595));
    }
    @Test
    void getEscKeyCheckingValue(){
        GetKeyPanel gp = new GetKeyPanel();
        assertEquals(27,gp.checkingValue(9099));
    }
    @Test
    void getSpaceKeyCheckingValue(){
        GetKeyPanel gp = new GetKeyPanel();
        assertEquals(32,gp.checkingValue(9251));
    }

}
