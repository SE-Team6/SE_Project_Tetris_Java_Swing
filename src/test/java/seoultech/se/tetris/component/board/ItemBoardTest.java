package seoultech.se.tetris.component.board;

import org.junit.jupiter.api.Test;
import seoultech.se.tetris.blocks.ParentBlock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemBoardTest {
    @Test
    public void getItemRouletteTest() {
        int TIMES = 100;
        int MAX = 17;
        for(int i=0;i<TIMES;i++){
            int value = new ItemBoard().getRoulette();
            assertTrue(0 <= value && value <= MAX);
        }
    }

    @Test
    public void checkBoundaryTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ItemBoard b = new ItemBoard();
        Method test = ItemBoard.class.getDeclaredMethod("checkBoundary", int.class, int.class);
        test.setAccessible(true);

        for(int i = 0; i< Board.HEIGHT; i++){
            for(int j=0;j<Board.WIDTH;j++){
                assertTrue((Boolean) test.invoke(b, j, i));
            }
        }
    }

    @Test
    public void checkBoundaryXTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ItemBoard b = new ItemBoard();
        Method test = ItemBoard.class.getDeclaredMethod("checkBoundaryX", int.class);
        test.setAccessible(true);

        for(int j=0;j<Board.WIDTH;j++){
            assertTrue((Boolean) test.invoke(b, j));
        }
    }

    @Test
    public void checkBoundaryYTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ItemBoard b = new ItemBoard();
        Method test = ItemBoard.class.getDeclaredMethod("checkBoundaryY", int.class);
        test.setAccessible(true);

        for(int j=0;j<Board.HEIGHT;j++){
            assertTrue((Boolean) test.invoke(b, j));
        }
    }

    @Test
    public void isNullLineTest() {
        for(int i=4;i<Board.HEIGHT;i++){
            assertTrue(new ItemBoard().isNullLine(i));
        }
    }

    @Test
    public void isBottomTouchedTest() {
        assertFalse(new ItemBoard().isBottomTouched());
    }

    @Test
    public void isOverlapTest() {
        assertTrue(new ItemBoard().isOverlap());
    }

    @Test
    public void replaceBlockToStarHorizontalTest() {
        new ItemBoard().replaceBlockToStarHorizontal(0, 20);
        assertTrue(true);
    }

    @Test
    public void getRandomItemBlockTest() {
        ParentBlock block = new ItemBoard().getRandomItemBlock();
        assertTrue(true);
    }
}
