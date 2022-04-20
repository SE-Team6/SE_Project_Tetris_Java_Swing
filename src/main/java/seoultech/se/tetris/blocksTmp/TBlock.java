package seoultech.se.tetris.blocksTmp;

import seoultech.se.tetris.config.ConfigBlock;

import java.awt.*;

public class TBlock extends ParentBlock {

    public TBlock() {
        color = Color.decode(ConfigBlock.BlOCK_COLOR[colorType][1]);

        shapes = new Block[][][]{
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
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                        {null,null,null,null},
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
                {
                        {null,null,null,null},
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
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

        left = new int[] {0, 1, 0, 0};
        right = new int[] {2, 2, 2, 1};
        top = new int[] {0, 0, 1, 0};
        bottom = new int[] {1, 2, 2, 2};
    }
}
