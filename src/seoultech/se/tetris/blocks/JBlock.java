package seoultech.se.tetris.blocks;

import java.awt.*;

public class JBlock extends ParentBlock {

    public JBlock() {
        color = Color.BLUE;
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
