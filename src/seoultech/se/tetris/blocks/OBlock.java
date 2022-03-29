package seoultech.se.tetris.blocks;

import java.awt.*;

public class OBlock extends ParentBlock {

    public OBlock() {
        color = Color.YELLOW;

        shapes = new Block[][][]{
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                },
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                },
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                },
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                },
        };

        shape = shapes[type];
    }
}
