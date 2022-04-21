package seoultech.se.tetris.config.block;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockTypeTest {
    @Test
    public void blockTypeNoneTest() {
        assertEquals(0, BlockType.NONE);
    }

    @Test
    public void blockTypeITEM1Test() {
        assertEquals(1, BlockType.ITEM1);
    }

    @Test
    public void blockTypeITEM2Test() {
        assertEquals(2, BlockType.ITEM2);
    }

    @Test
    public void blockTypeITEM3Test() {
        assertEquals(3, BlockType.ITEM3);
    }

    @Test
    public void blockTypeITEM4Test() {
        assertEquals(4, BlockType.ITEM4);
    }

    @Test
    public void blockTypeITEM5Test() {
        assertEquals(5, BlockType.ITEM5);
    }
}
