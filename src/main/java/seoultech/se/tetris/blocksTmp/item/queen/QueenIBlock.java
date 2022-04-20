package seoultech.se.tetris.blocksTmp.item.queen;

import seoultech.se.tetris.blocksTmp.Block;
import seoultech.se.tetris.blocksTmp.item.random.RandomIBlock;
import seoultech.se.tetris.config.ConfigBlock;
import seoultech.se.tetris.config.block.BlockType;

import java.awt.*;
import java.util.Random;

public class QueenIBlock extends RandomIBlock {
    public QueenIBlock() {
        blockType = BlockType.ITEM3;
        Random random = new Random(System.currentTimeMillis());
        randomIdx = random.nextInt(4);
        for (int i=0;i<4;i++){
            shapes[i][randomType[randomIdx][i][0]][randomType[randomIdx][i][1]] = new Block(Color.WHITE, ConfigBlock.CHESS_QUEEN, blockType);

        }
        shape = shapes[type];
    }
}
