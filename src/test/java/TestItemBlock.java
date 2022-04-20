import org.junit.jupiter.api.Test;
import seoultech.se.tetris.previousBlocks.ParentBlock;
import seoultech.se.tetris.previousBlocks.item.random.RandomIBlock;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestItemBlock {
    @Test
    void test() {
        ParentBlock block = new RandomIBlock();

        assertTrue(block.height() > 0);
    }

    @Test
    void ww() {
        ParentBlock block = new RandomIBlock();

        assertTrue(block.width() > 0);
    }
}
