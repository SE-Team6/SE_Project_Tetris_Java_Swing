package seoultech.se.tetris.blocks;

import java.awt.*;

public class SBlock extends ParentBlock {

    public SBlock() {
        color = Color.GREEN;

        shapes = new Block[][][]{
                {
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                                null,
                        },
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR),
                                null,
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR),
                        },
                },
                {
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                                null,
                        },
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR),
                                null,
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR),
                        },
                },
        };

        shape = shapes[type];
    }
}
