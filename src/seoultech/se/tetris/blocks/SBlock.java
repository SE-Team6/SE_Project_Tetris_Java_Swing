package seoultech.se.tetris.blocks;

import seoultech.se.tetris.config.ConfigBlock;

import java.awt.*;

public class SBlock extends ParentBlock {

    public SBlock() {
        color = Color.decode(ConfigBlock.BlOCK_COLOR[colorType][2]);

        shapes = new Block[][][]{
                {
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
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
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                },
                {
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
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
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                },
        };

        shape = shapes[type];
    }
}
