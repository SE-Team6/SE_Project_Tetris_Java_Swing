package seoultech.se.tetris.previousBlocks;

import seoultech.se.tetris.config.ConfigBlock;

import java.awt.*;

public class LBlock extends ParentBlock {

    public LBlock() {
        color = Color.decode(ConfigBlock.BlOCK_COLOR[colorType][5]);

        shapes = new Block[][][]{
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),},
                        {new Block(color, config.BLOCK_CHAR, blockType), null, null},
                },
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                        {null, new Block(color, config.BLOCK_CHAR, blockType),},
                        {null, new Block(color, config.BLOCK_CHAR, blockType),},
                },
                {
                        {null, null, new Block(color, config.BLOCK_CHAR, blockType),},
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),},
                },
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), null,},
                        {new Block(color, config.BLOCK_CHAR, blockType), null,},
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                },
        };
        shape = shapes[type];
    }
}
