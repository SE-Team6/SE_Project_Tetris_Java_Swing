package seoultech.se.tetris.previousBlocks;

import seoultech.se.tetris.config.ConfigBlock;
import seoultech.se.tetris.config.block.BlockType;

import java.awt.*;

public abstract class ParentBlock {
    protected Block[][] shape;
    protected Block[][][] shapes;
    protected Color color;
    // rotate type
    protected int type = 0;
    protected int blockType = BlockType.NONE;
    protected ConfigBlock config = ConfigBlock.getInstance();
    protected static int colorType = ConfigBlock.colorType;
    protected int randomIdx = 0;
    protected boolean isSettled = false;

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
}
