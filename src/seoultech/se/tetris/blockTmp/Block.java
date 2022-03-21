package seoultech.se.tetris.blockTmp;

import java.awt.*;

// 블락 쪼개기
public class Block {
    private Color color;
    private int x;
    private int y;

    public Block(Color color) {
        color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setPos(int x, int y) {
        x = x;
        y = y;
    }

    public void down() {
        y += 1;
    }

    public int[] getPos() {
        return new int[]{x, y};
    }
}
