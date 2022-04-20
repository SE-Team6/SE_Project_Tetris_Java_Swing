package seoultech.se.tetris.previousBlocks.item.queen;

import seoultech.se.tetris.previousBlocks.Block;
import seoultech.se.tetris.previousBlocks.item.random.RandomSBlock;
import seoultech.se.tetris.config.ConfigBlock;
import seoultech.se.tetris.config.block.BlockType;

import java.awt.*;
import java.util.Random;

public class QueenSBlock extends RandomSBlock {
    public QueenSBlock() {
        blockType = BlockType.ITEM3;
        Random random = new Random(System.currentTimeMillis());
        randomIdx = random.nextInt(4);
        for (int i=0;i<4;i++){
            shapes[i][randomType[randomIdx][i][0]][randomType[randomIdx][i][1]] = new Block(Color.WHITE, ConfigBlock.CHESS_QUEEN, blockType);
        }
        shape = shapes[type];
    }
}
