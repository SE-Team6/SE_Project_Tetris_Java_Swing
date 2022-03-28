package seoultech.se.tetris.blocks;

import java.awt.*;

public class TBlock extends ParentBlock{

    public TBlock() {
        color = Color.MAGENTA;

        shapes = new Block[][][]{
                {
                        {
                            null,
                                new Block(color, config.BLOCK_CHAR),
                                null,
                        },
                        {
                            new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
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
                                new Block(color, config.BLOCK_CHAR),
                                null,
                        },
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR),
                                null,
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
                                null,
                                new Block(color, config.BLOCK_CHAR),
                        },
                },
        };

        shape = shapes[type];
    }
}
