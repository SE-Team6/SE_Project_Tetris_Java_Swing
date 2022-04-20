package seoultech.se.tetris.previousBlocks;

import java.awt.*;

// 블락 쪼개기
public class Block {
    private Color color;
    private String character;
    private int blockType;

    public Block(Color color, String character, int blockType) {
        this.color = color;this.character = character;this.blockType = blockType;
    }

    public Color getColor() {
        return this.color;
    }

    public String getCharacter() {return this.character;}

    public int getBlockType() {return this.blockType;}
}
