package seoultech.se.tetris.blocks.item.random;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.blocks.IBlock;
import seoultech.se.tetris.config.ConfigBlock;
import seoultech.se.tetris.config.block.BlockType;

import java.awt.*;
import java.util.Random;

public class RandomIBlock extends IBlock {
    protected final int[][][] randomType = {
            {{1, 0}, {0, 2}, {2, 3}, {3, 1}},
            {{1, 1}, {1, 2}, {2, 2}, {2, 1}},
            {{1, 2}, {2, 2}, {2, 1}, {1, 1}},
            {{1, 3}, {3, 2}, {2, 0}, {0, 1}},
    };
    public RandomIBlock() {
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
