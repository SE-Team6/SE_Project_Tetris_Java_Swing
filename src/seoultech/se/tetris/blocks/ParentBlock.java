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
    }

    public Color getParentColor() {
        return color;
    }

    public void rotate() {
        type = (type + 1) % 4;
        shape = shapes[type];
    }

    public void rotateBack() {
        type = (type - 1 + 4) % 4;
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
