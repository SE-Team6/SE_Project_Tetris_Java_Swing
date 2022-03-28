package seoultech.se.tetris.blocks;

import java.awt.*;

public class LBlock extends ParentBlock {

    public LBlock() {
        color = Color.BLUE;

        shapes = new Block[][][]{
                {
                        {new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR),},
                        {new Block(color, config.BLOCK_CHAR), null, null},
                },
                {
                        {new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR)},
                        {null, new Block(color, config.BLOCK_CHAR),},
                        {null, new Block(color, config.BLOCK_CHAR),},
                },
                {
                        {null, null, new Block(color, config.BLOCK_CHAR),},
                        {new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR),},
                },
                {
                        {new Block(color, config.BLOCK_CHAR), null,},
                        {new Block(color, config.BLOCK_CHAR), null,},
                        {new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR)},
                },
        };
        shape = shapes[type];
    }
}
