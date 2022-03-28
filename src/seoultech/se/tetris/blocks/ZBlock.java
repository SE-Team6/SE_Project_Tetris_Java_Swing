package seoultech.se.tetris.blocks;

import java.awt.*;

public class ZBlock extends ParentBlock {

    public ZBlock() {
        color = Color.RED;

        shapes = new Block[][][]{
                {
                        {
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                                null,
                        },
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                        },
                },
                {
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                                null,
                        },
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                                null,
                        },
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                        },
                },
                {
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                                null,
                        },
                },
        };

        shape = shapes[type];
    }
}
