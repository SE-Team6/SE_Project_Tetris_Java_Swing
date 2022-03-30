package seoultech.se.tetris.blocks;

import seoultech.se.tetris.config.ConfigBlock;

import java.awt.*;

public class OBlock extends ParentBlock {

    public OBlock() {
        color = Color.decode(ConfigBlock.BlOCK_COLOR[colorType][3]);

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
