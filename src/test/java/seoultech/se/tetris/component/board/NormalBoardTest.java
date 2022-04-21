package seoultech.se.tetris.component.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class NormalBoardTest {
    @Test
    public void isOverlapTest() {
        Board b = new NormalBoard();
        b.eraseCurr();
        assertFalse(b.isOverlap());
    }

    @Test
    public void isBottomTouchedTest() {
        assertFalse(new NormalBoard().isBottomTouched());
    }
}
