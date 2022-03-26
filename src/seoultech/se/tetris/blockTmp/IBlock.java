package seoultech.se.tetris.blockTmp;

import java.awt.*;

public class IBlock extends ParentBlock{

    public IBlock() {
        color = Color.CYAN;
//        shape = new Block[][] {
//                {new Block(color), new Block(color), new Block(color), new Block(color)}
//        };
        shapes = new Block[][][] {
                {
                    {new Block(color), new Block(color), new Block(color), new Block(color)}
                },
                {
                        {
                            new Block(color),
                        },
                        {
                                new Block(color),
                        },
                        {
                                new Block(color),
                        },
                        {
                                new Block(color),
                        }
                },
                {
                    {new Block(color), new Block(color), new Block(color), new Block(color)}
                },
                {
                        {
                                new Block(color),
                        },
                        {
                                new Block(color),
                        },
                        {
                                new Block(color),
                        },
                        {
                                new Block(color),
                        }
                },
        };
        shape = shapes[type];
    }
}
