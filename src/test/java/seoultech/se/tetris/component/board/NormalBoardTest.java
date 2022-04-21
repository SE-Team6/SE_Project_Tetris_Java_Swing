package seoultech.se.tetris.component.board;

import org.junit.jupiter.api.Test;
import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.component.Keyboard;
import seoultech.se.tetris.config.ConfigBlock;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class NormalBoardTest {
    @Test
    public void isOverlapTest() {
        Board b = new NormalBoard();
        b.eraseCurr();
        assertFalse(b.isOverlap());
    }

    @Test
    public void isBottomTouchedTest() {
        assertFalse(new NormalBoard().isBottomTouched());
    }

    @Test
    public void keyBoardTest() {
        Board ib = new NormalBoard();
        KeyEvent ke = new KeyEvent(ib, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
        ke.setKeyCode(Keyboard.DOWN);
        ib.playerKeyListener.keyPressed(ke);
        ke.setKeyCode(Keyboard.RIGHT);
        ib.playerKeyListener.keyPressed(ke);
        ke.setKeyCode(Keyboard.LEFT);
        ib.playerKeyListener.keyPressed(ke);
        ke.setKeyCode(Keyboard.UP);
        ib.playerKeyListener.keyPressed(ke);
        ke.setKeyCode(Keyboard.SPACE);
        ib.playerKeyListener.keyPressed(ke);
//        ke.setKeyCode(Keyboard.ESC);
//        ib.playerKeyListener.keyPressed(ke);
//        assertFalse(ib.isPause);
//        ib.playerKeyListener.keyPressed(ke);
//        assertFalse(ib.isPause);
    }

    @Test
    public void gameOverTest() {
        Board ib = new NormalBoard();
        ib.gameOver(0,0);
    }

    @Test
    public void eraseLinesTest() {
        Board ib = new NormalBoard();
        for(int i=0;i<Board.WIDTH;i++){
            ib.board[0][i] = new Block(Color.red, ConfigBlock.BLOCK_CHAR,0);
        }
        ib.eraseLines();
    }
}
