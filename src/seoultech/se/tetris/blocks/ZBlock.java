package seoultech.se.tetris.blocks;

import java.awt.*;

public class ZBlock extends ParentBlock {

    public ZBlock() {
        color = Color.RED;

        shapes = new Block[][][]{
                {
                        {
                                new Block(color),
                                new Block(color),
                                null,
                        },
                        {
                                null,
                                new Block(color),
                                new Block(color),
                        },
                },
                {
                        {
                                null,
                                new Block(color),
                        },
                        {
                                new Block(color),
                                new Block(color),
                        },
                        {
                                new Block(color),
                                null,
                        },
                },
                {
                        {
                                new Block(color),
                                new Block(color),
                                null,
                        },
                        {
                                null,
                                new Block(color),
                                new Block(color),
                        },
                },
                {
                        {
                                null,
                                new Block(color),
                        },
                        {
                                new Block(color),
                                new Block(color),
                        },
                        {
                                new Block(color),
                                null,
                        },
                },
        };

        shape = shapes[type];
    }
}
