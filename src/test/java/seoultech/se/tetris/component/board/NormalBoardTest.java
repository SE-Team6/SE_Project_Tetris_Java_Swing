package seoultech.se.tetris.component.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NormalBoardTest {
    @Test
    public void isOverlapTest() {
        assertTrue(new NormalBoard().isOverlap());
    }

    @Test
    public void isBottomTouchedTest() {
        assertFalse(new NormalBoard().isBottomTouched());
    }
}
