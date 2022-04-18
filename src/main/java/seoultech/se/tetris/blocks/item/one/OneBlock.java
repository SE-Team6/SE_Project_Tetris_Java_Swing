package seoultech.se.tetris.blocks.item.one;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.blocks.ParentBlock;
import seoultech.se.tetris.config.block.BlockType;

import java.awt.*;

public class OneBlock extends ParentBlock {
    public OneBlock() {
        super();
        blockType = BlockType.ITEM4;
        color = Color.GRAY;
        shapes = new Block[][][] {
                {
                        {
                            new Block(color, config.BLOCK_CHAR, blockType),
                        },
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                },
        };
        shape = shapes[type];
    };
}
