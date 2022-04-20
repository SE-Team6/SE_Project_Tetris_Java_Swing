package seoultech.se.tetris.blocks;

import seoultech.se.tetris.config.ConfigBlock;

import java.awt.*;

public class ZBlock extends ParentBlock {

    public ZBlock() {
        color = Color.decode(ConfigBlock.BlOCK_COLOR[colorType][0]);

        shapes = new Block[][][]{
                {
                        {null,null,null,null},
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                                null,
                        },
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                        {null,null,null,null},
                },
                {
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                                null,
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                                null,
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                                null,
                                null,
                        },
                        {null,null,null,null},
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                                null,
                        },
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                        {null,null,null,null},
                        {null,null,null,null},
                },
                {
                        {
                            null,
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                        {
                            null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                        {
                            null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                                null,
                        },
                        {null,null,null,null},
                },
        };

        shape = shapes[type];

        centerPos = new int[][] {
                {1,1},
                {1,1},
                {1,1},
                {1,1},
        };

        left = new int[] {0, 0, 0, 1};
        right = new int[] {2, 1, 2, 2};
        top = new int[] {1, 0, 0, 0};
        bottom = new int[] {2, 2, 1, 2};
    }
}
