package seoultech.se.tetris.blocks;

import java.awt.*;

public class IBlock extends ParentBlock {

    public IBlock() {
        color = Color.CYAN;

        shapes = new Block[][][]{
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)}
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                        }
                },
                {
                        {new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType), new Block(color, config.BLOCK_CHAR, blockType)}
                },
                {
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                        },
                        {
                                new Block(color, config.BLOCK_CHAR, blockType),
                        }
                },
        };
        shape = shapes[type];
    }
}
