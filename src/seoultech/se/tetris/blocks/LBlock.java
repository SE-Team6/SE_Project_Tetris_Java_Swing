package seoultech.se.tetris.blocks;

import java.awt.*;

public class LBlock extends ParentBlock {

    public LBlock() {
        color = Color.BLUE;

        shapes = new Block[][][]{
                {
                        {new Block(color), new Block(color), new Block(color),},
                        {new Block(color), null, null},
                },
                {
                        {new Block(color), new Block(color)},
                        {null, new Block(color),},
                        {null, new Block(color),},
                },
                {
                        {null, null, new Block(color),},
                        {new Block(color), new Block(color), new Block(color),},
                },
                {
                        {new Block(color), null,},
                        {new Block(color), null,},
                        {new Block(color), new Block(color)},
                },
        };
        shape = shapes[type];
    }
}
