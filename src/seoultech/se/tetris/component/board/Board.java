package seoultech.se.tetris.component.board;

import seoultech.se.tetris.blocks.ParentBlock;
import seoultech.se.tetris.config.ConfigBlock;

interface Board {
    public static final long serialVersionUID = 2434035659171694595L;

    public static final int HEIGHT = 20;
    public static final int WIDTH = 10;

    public ConfigBlock config = ConfigBlock.getInstance();

    public void eraseLines();
    public ParentBlock getRandomBlock();
    public void placeBlock();
    public void reset();
    public void focusFrame();
    public void eraseCurr();


    // test log
    public void printBoard();
}
