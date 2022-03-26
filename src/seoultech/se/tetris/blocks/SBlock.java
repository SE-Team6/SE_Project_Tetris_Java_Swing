package seoultech.se.tetris.blocks;

import java.awt.*;

public class SBlock extends ParentBlock {

    public SBlock() {
        color = Color.GREEN;

        shapes = new Block[][][]{
                {
                        {
                                null,
                                new Block(color),
                                new Block(color),
                        },
                        {
                                new Block(color),
                                new Block(color),
                                null,
                        },
                },
                {
                        {
                                new Block(color),
                                null,
                        },
                        {
                                new Block(color),
                                new Block(color),
                        },
                        {
                                null,
                                new Block(color),
                        },
                },
                {
                        {
                                null,
                                new Block(color),
                                new Block(color),
                        },
                        {
                                new Block(color),
                                new Block(color),
                                null,
                        },
                },
                {
                        {
                                new Block(color),
                                null,
                        },
                        {
                                new Block(color),
                                new Block(color),
                        },
                        {
                                null,
                                new Block(color),
                        },
                },
        };

        shape = shapes[type];
    }
}
