package seoultech.se.tetris.blocks;

import java.awt.*;

public class ZBlock extends ParentBlock {

    public ZBlock() {
        color = Color.RED;

        shapes = new Block[][][]{
                {
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                },
                {
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                },
                {
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                },
        };

        shape = shapes[type];
    }
}
