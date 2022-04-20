package seoultech.se.tetris.blocksTmp.item.slime;

import seoultech.se.tetris.blocksTmp.Block;
import seoultech.se.tetris.blocksTmp.ParentBlock;
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

    @Override
    public int getLeft() {
        return 0;
    }

    @Override
    public int getRight() {
        return width()-1;
    }

    @Override
    public int getTop() {
        return 0;
    }

    @Override
    public int getBottom() {
        return height()-1;
    }
}
