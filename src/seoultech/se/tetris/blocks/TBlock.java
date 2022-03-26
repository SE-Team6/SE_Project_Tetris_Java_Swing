package seoultech.se.tetris.blocks;

import java.awt.*;

public class TBlock extends ParentBlock{

    public TBlock() {
        color = Color.MAGENTA;

        shapes = new Block[][][]{
                {
                        {
                            null,
                                new Block(color),
                                null,
                        },
                        {
                            new Block(color),
                                new Block(color),
                                new Block(color),
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
                                new Block(color),
                                null,
                        },
                },
                {
                        {
                                new Block(color),
                                new Block(color),
                                new Block(color),
                        },
                        {
                                null,
                                new Block(color),
                                null,
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
                                null,
                                new Block(color),
                        },
                },
        };

        shape = shapes[type];
    }
}
