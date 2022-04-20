package seoultech.se.tetris.blocksTmp;

import seoultech.se.tetris.config.ConfigBlock;

import java.awt.*;

public class IBlock extends ParentBlock {

    public IBlock() {
        color = Color.decode(ConfigBlock.BlOCK_COLOR[colorType][6]);

        shapes = new Block[][][]{
                {
                        {null,null,null,null,},
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                        {null,null,null,null,},
                        {null,null,null,null,},
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
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                        {
                                null,
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                        {
                                null,
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                        },
                },
                {
                        {null,null,null,null,},
                        {null,null,null,null,},
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)},
                        {null,null,null,null,},
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
                                null,
                                null,
                        },
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                                null,
                        },
                        {
                                null,
                                new Block(color, config.BLOCK_CHAR, blockType),
                                null,
                                null,
                        }
                },
        };
        shape = shapes[type];

        centerPos = new int[][] {
                {1, 2},
                {2, 2},
                {2, 1},
                {1, 1}
        };

        left = new int[] {0, 2, 0, 1};
        right = new int[] {3, 2, 3, 1};
        top = new int[] {1, 0, 2, 0};
        bottom = new int[] {1, 3, 2, 3};
    }
}
