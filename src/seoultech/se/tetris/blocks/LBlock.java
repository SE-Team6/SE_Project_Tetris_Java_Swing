package seoultech.se.tetris.blocks;

import java.awt.*;

public class LBlock extends ParentBlock {

    public LBlock() {
        color = Color.ORANGE;

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
