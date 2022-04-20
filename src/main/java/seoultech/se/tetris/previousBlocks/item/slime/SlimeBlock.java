package seoultech.se.tetris.previousBlocks.item.slime;

import seoultech.se.tetris.previousBlocks.Block;
import seoultech.se.tetris.previousBlocks.ParentBlock;
import seoultech.se.tetris.config.block.BlockType;

import java.awt.*;

public class SlimeBlock extends ParentBlock {

    public SlimeBlock() {
        super();
        blockType = BlockType.ITEM5;
        color = Color.GREEN;
        shapes = new Block[][][] {
                {
                        {
                            new Block(color, config.BLOCK_CHAR, blockType),
                        },
                },
                {
                        {
                            new Block(color, config.BLOCK_CHAR, blockType),
                            new Block(color, config.BLOCK_CHAR, blockType),
                        },
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                            null, new Block(color, config.BLOCK_CHAR, blockType),
                        }
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType), null,
                        },
                },
        };
        shape = shapes[type];
    }
}
