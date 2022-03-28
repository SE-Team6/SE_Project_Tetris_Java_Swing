package seoultech.se.tetris.blocks;

import java.awt.*;

// 블락 쪼개기
public class Block {
    private Color color;
    private String character;

    public Block(Color color, String character) {
        this.color = color;this.character = character;
    }

    public Color getColor() {
        return this.color;
    }

    public String getCharacter() {return this.character;}
}
