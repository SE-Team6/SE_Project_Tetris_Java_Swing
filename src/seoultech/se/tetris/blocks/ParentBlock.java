package seoultech.se.tetris.blocks;

import java.awt.*;

public abstract class ParentBlock {
    protected Block[][] shape;
    protected Block[][][] shapes;
    protected Color color;
    // rotate type
    protected int type = 0;

    public ParentBlock() {
        color = Color.GRAY;
//        shape = new Block[][] {
//                {1, 1},
//                {1, 1},
//        };
    }

    public Color getParentColor() {
        return color;
    }

    // cost가 비쌈 (4 x 4)
    public void rotate() {
//        // rotate block
//        int height = this.height();
//        int width = this.width();
//        Block arr[][] = new Block[height][width];
//        for (int i=0;i<height;i++){
//            for(int j=0;j<width;j++){
//                arr[i][j] = shape[height-1-j][i];
//            }
//        }
//        shape = arr;
        type = (type + 1) % 4;
        shape = shapes[type];
    }

    public int height() {
        return shape.length;
    }

    public int width() {
        return shape.length > 0 ? shape[0].length : 0;
    }

    public Color getColor() {
        return color;
    }

    public Block getShape(int x, int y) {
        return shape[y][x];
    }
}
