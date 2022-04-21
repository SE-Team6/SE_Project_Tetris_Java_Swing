package seoultech.se.tetris.blocks;

import seoultech.se.tetris.config.ConfigBlock;
import seoultech.se.tetris.config.block.BlockType;

import java.awt.*;

public class ParentBlock {
    protected Block[][] shape;
    protected Block[][][] shapes;
    protected int[][] centerPos;
    protected Color color;
    // rotate type
    protected int type = 0;
    protected int blockType = BlockType.NONE;
    protected static int colorType = ConfigBlock.colorType;
    protected int randomIdx = 0;
    protected boolean isSettled = false;

    protected int[] left;
    protected int[] top;
    protected int[] right;
    protected int[] bottom;

    public ParentBlock() {
        color = Color.GRAY;
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

    public int getBlockType() {
        return blockType;
    }

    public int[] getBlockRandomPos() {
        return new int[]{0,0};
    }

    public void setIsSettled() {
        isSettled = true;
    }

    public boolean getIsSettled() {
        return isSettled;
    }

    public static void setColorType(int type) {colorType=type;}

    public int getLeft() {return left[type];}

    public int getRight() { return right[type]; }


    public int getTop() {return top[type];}

    // bottom 까지 거리
    public int getBottom() { return bottom[type]; }
}
