package seoultech.se.tetris.blocks.item.pendulum;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.blocks.ParentBlock;

import java.awt.*;

public class PendulumBlock extends ParentBlock {
    public PendulumBlock() {
        super();
        isItemType = true;
        color = Color.GRAY;
        shapes = new Block[][][]{
                {
                        {
                                null, new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR), null,
                        },
                        {
                                new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR),
                        }
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR), null,
                        },
                        {
                                new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR), null,
                        },
                },
                {
                        {
                                null, new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR), null,
                        },
                        {
                                new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR),
                                new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR),
                        }
                },
                {
                        {
                                null, new Block(color, config.BLOCK_CHAR)
                        },
                        {
                                new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR), new Block(color, config.BLOCK_CHAR),
                        },
                        {
                                null, new Block(color, config.BLOCK_CHAR)
                        },
                },
        };
        shape = shapes[type];
    }
}
