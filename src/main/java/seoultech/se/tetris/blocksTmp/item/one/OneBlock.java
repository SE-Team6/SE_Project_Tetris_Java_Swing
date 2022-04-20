package seoultech.se.tetris.blocksTmp.item.one;

import seoultech.se.tetris.blocksTmp.Block;
import seoultech.se.tetris.blocksTmp.ParentBlock;
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
