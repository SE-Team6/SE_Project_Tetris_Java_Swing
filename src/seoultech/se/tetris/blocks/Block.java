package seoultech.se.tetris.blocks;

import java.awt.*;

// 블락 쪼개기
public class Block {
    private Color color;

    public Block(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
