package seoultech.se.tetris.previousBlocks;

import seoultech.se.tetris.config.ConfigBlock;

import java.awt.*;

public class TBlock extends ParentBlock{

    public TBlock() {
        color = Color.decode(ConfigBlock.BlOCK_COLOR[colorType][1]);

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
