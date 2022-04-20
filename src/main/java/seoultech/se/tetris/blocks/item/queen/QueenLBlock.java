package seoultech.se.tetris.blocks.item.queen;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.blocks.item.random.RandomLBlock;
import seoultech.se.tetris.config.ConfigBlock;
import seoultech.se.tetris.config.block.BlockType;

import java.awt.*;
import java.util.Random;

public class QueenLBlock extends RandomLBlock {
    public QueenLBlock() {
        blockType = BlockType.ITEM3;
        Random random = new Random(System.currentTimeMillis());
        randomIdx = random.nextInt(4);
        for (int i=0;i<4;i++){
            shapes[i][randomType[randomIdx][i][0]][randomType[randomIdx][i][1]] = new Block(Color.WHITE, ConfigBlock.CHESS_QUEEN, blockType);
        }
        shape = shapes[type];
    }
}
