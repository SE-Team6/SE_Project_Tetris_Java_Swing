package seoultech.se.tetris.blocks.item.pendulum;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.blocks.ParentBlock;
import seoultech.se.tetris.config.block.BlockType;

import java.awt.*;

public class PendulumBlock extends ParentBlock {
    public PendulumBlock() {
        super();
        blockType = BlockType.ITEM2;
        color = Color.GRAY;
        shapes = new Block[][][]{
                {
                        {
                                null, new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), null,
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),
                        }
                },
                {
                        {
                                null, new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), null,
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),
                        }
                },
                {
                        {
                                null, new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), null,
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),
                        }
                },
                {
                        {
                                null, new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), null,
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType),
                        }
                },
        };
        shape = shapes[type];
    }
}
