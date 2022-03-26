package seoultech.se.tetris.blocks;

import java.awt.*;

public class JBlock extends ParentBlock {

    public JBlock() {
        color = Color.BLUE;
        shapes = new Block[][][] {
                {
                    {new Block(color), new Block(color), new Block(color)},
                    {null, null, new Block(color)}
                },
                {
                    {null, new Block(color)},
                    {null, new Block(color)},
                    {new Block(color), new Block(color)}
                },
                {
                    { new Block(color), null, null,},
                    {new Block(color), new Block(color), new Block(color)},
                },
                {
                    {new Block(color), new Block(color)},
                    {new Block(color), null},
                    {new Block(color), null},
                },
        };
        shape = shapes[type];

    }
}
