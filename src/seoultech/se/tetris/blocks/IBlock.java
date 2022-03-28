package seoultech.se.tetris.blocks;

import seoultech.se.tetris.config.ConfigBlock;

import java.awt.*;

public class IBlock extends ParentBlock {

    public IBlock() {
        color = Color.CYAN;
//        shape = new Block[][] {
//                {new Block(color), new Block(color), new Block(color), new Block(color)}
//        };
        shapes = new Block[][][]{
                {
                        {new Block(color, ConfigBlock.BLOCK_CHAR), new Block(color, ConfigBlock.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR)}
                },
                {
                        {
                                new Block(color, ConfigBlock.BLOCK_CHAR),
                        },
                        {
                                new Block(color, ConfigBlock.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                        }
                },
                {
                        {new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR)}
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR),
                        }
                },
        };
        shape = shapes[type];
    }
}
