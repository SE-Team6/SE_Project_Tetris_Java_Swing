package seoultech.se.tetris.previousBlocks;

import seoultech.se.tetris.config.ConfigBlock;

import java.awt.*;

public class JBlock extends ParentBlock {

    public JBlock() {
        color = Color.decode(ConfigBlock.BlOCK_COLOR[colorType][4]);
        shapes = new Block[][][] {
                {
                    {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                    {null, null, new Block(color, config.BLOCK_CHAR, blockType)}
                },
                {
                    {null, new Block(color, config.BLOCK_CHAR, blockType)},
                    {null, new Block(color, config.BLOCK_CHAR, blockType)},
                    {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)}
                },
                {
                    { new Block(color, config.BLOCK_CHAR, blockType), null, null,},
                    {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                },
                {
                    {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                    {new Block(color, config.BLOCK_CHAR, blockType), null},
                    {new Block(color, config.BLOCK_CHAR, blockType), null},
                },
        };
        shape = shapes[type];

    }
}
