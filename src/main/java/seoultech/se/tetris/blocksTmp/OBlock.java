package seoultech.se.tetris.blocksTmp;

import seoultech.se.tetris.config.ConfigBlock;

import java.awt.*;

public class OBlock extends ParentBlock {

    public OBlock() {
        color = Color.decode(ConfigBlock.BlOCK_COLOR[colorType][3]);

        shapes = new Block[][][]{
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),null,null,},
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),null,null,},
                        {null,null,null,null},
                        {null,null,null,null},
                },
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),null,null,},
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),null,null,},
                        {null,null,null,null},
                        {null,null,null,null},
                },
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),null,null,},
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),null,null,},
                        {null,null,null,null},
                        {null,null,null,null},
                },
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),null,null,},
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),null,null,},
                        {null,null,null,null},
                        {null,null,null,null},
                },
        };

        shape = shapes[type];

        centerPos = new int[][] {
                {0, 0},
                {0, 0},
                {0,0},
                {0,0},
        };

        left = new int[] {0, 0, 0, 0};
        right = new int[] {1, 1, 1, 1,};
        top = new int[] {0, 0, 0, 0};
        bottom = new int[] {1, 1, 1, 1};
    }
}
