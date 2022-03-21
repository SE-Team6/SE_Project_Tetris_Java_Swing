package seoultech.se.tetris.blockTmp;

import java.awt.*;

public class JBlock extends ParentBlock {

    public JBlock() {
        color = Color.BLUE;
//        shape = new Block[][] {
//                {new Block(color), new Block(color), new Block(color)},
//                {null, null, new Block(color)}
//        };
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
