package seoultech.se.tetris.blocks.item.random;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.blocks.LBlock;
import seoultech.se.tetris.config.ConfigBlock;
import seoultech.se.tetris.config.block.BlockType;

import java.awt.*;
import java.util.Random;

public class RandomLBlock extends LBlock {
    private final int randomType[][][] = {
            {{0, 0}, {0, 1}, {1, 2}, {2, 0}},
            {{0, 1}, {1, 1}, {1, 1}, {1, 0}},
            {{0, 2}, {2, 1}, {1, 0}, {0, 0}},
            {{1, 0}, {0, 0}, {0, 2}, {2, 1}},
    };
    public RandomLBlock() {
        itemNum =4;
        blockType = BlockType.ITEM1;
        Random random = new Random(System.currentTimeMillis());
        int block = random.nextInt(4);
        for (int i=0;i<4;i++){
            shapes[i][randomType[block][i][0]][randomType[block][i][1]] = new Block(Color.WHITE, ConfigBlock.RANDOM_CHAR, blockType);
        }
        shape = shapes[type];
    }
}
