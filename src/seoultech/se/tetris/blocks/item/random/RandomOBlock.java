package seoultech.se.tetris.blocks.item.random;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.blocks.OBlock;
import seoultech.se.tetris.config.ConfigBlock;
import seoultech.se.tetris.config.block.BlockType;

import java.awt.*;
import java.util.Random;

public class RandomOBlock extends OBlock {
    private final int randomType[][][] = {
            {{0, 0}, {0, 1}, {1, 1}, {1, 0}},
            {{0, 1}, {1, 1}, {1, 0}, {0, 0}},
            {{1, 1}, {1, 0}, {0, 0}, {0, 1}},
            {{1, 0}, {0, 0}, {0, 1}, {1, 1}},
    };
    public RandomOBlock() {
        itemNum =5;
        blockType = BlockType.ITEM1;
        Random random = new Random(System.currentTimeMillis());
        int block = random.nextInt(4);
        for (int i=0;i<4;i++){
            shapes[i][randomType[block][i][0]][randomType[block][i][1]] = new Block(Color.WHITE, ConfigBlock.RANDOM_CHAR, blockType);
        }
        shape = shapes[type];
    }
}
