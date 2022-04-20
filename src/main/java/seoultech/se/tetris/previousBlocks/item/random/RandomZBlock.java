package seoultech.se.tetris.previousBlocks.item.random;

import seoultech.se.tetris.previousBlocks.Block;
import seoultech.se.tetris.previousBlocks.ZBlock;
import seoultech.se.tetris.config.ConfigBlock;
import seoultech.se.tetris.config.block.BlockType;

import java.awt.*;
import java.util.Random;

public class RandomZBlock extends ZBlock {
    protected final int[][][] randomType = {
            {{0, 0}, {0, 1}, {1, 2}, {2, 0}},
            {{0, 1}, {1, 1}, {1, 1}, {1, 0}},
            {{1, 1}, {1, 0}, {0, 1}, {1, 1}},
            {{1, 2}, {2, 0}, {0, 0}, {0, 1}},
    };
    public RandomZBlock() {
        blockType = BlockType.ITEM1;
        Random random = new Random(System.currentTimeMillis());
        randomIdx = random.nextInt(4);
        for (int i=0;i<4;i++){
            shapes[i][randomType[randomIdx][i][0]][randomType[randomIdx][i][1]] = new Block(Color.WHITE, ConfigBlock.RANDOM_CHAR, blockType);
        }
        shape = shapes[type];
    }

    public int[] getBlockRandomPos() {
        return randomType[randomIdx][type];
    }
}
