package seoultech.se.tetris.blocks;

import java.awt.*;

public class TBlock extends ParentBlock{

    public TBlock() {
        color = Color.MAGENTA;

        shapes = new Block[][][]{
                {
                        {
                            null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                        {
                            new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
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
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
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
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                },
        };

        shape = shapes[type];
    }
}
