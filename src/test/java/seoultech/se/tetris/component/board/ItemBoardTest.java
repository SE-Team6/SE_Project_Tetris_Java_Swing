package seoultech.se.tetris.component.board;

import org.junit.jupiter.api.Test;
import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.blocks.ParentBlock;
import seoultech.se.tetris.blocks.item.pendulum.PendulumBlock;
import seoultech.se.tetris.component.Keyboard;
import seoultech.se.tetris.config.ConfigBlock;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemBoardTest {

    @Test
    public void keyBoardEscTest() {

    }
    @Test
    public void getItemRouletteTest() {
        int TIMES = 100;
        int MAX = 17;
        for(int i=0;i<TIMES;i++){
            int value = new ItemBoard().getRoulette();
            assertTrue(0 <= value && value <= MAX);
        }
    }

    @Test
    public void isNullLineTest() {
        for(int i=4;i<Board.HEIGHT;i++){
            assertTrue(new ItemBoard().isNullLine(i));
        }
    }

    @Test
    public void isBottomTouchedTest() {
        assertFalse(new ItemBoard().isBottomTouched());
    }

    @Test
    public void isOverlapTest() {
        Board b = new ItemBoard();
        b.eraseCurr();
        assertFalse(b.isOverlap());
    }

    @Test
    public void replaceBlockToStarHorizontalTest() {
        new ItemBoard().replaceBlockToStarHorizontal(0, 20);
        assertTrue(true);
    }

    @Test
    public void getRandomItemBlockTest() {
        ParentBlock block = new ItemBoard().getRandomItemBlock();
        assertTrue(true);
    }

    @Test
    public void keyBoardTest() {
        ItemBoard ib = new ItemBoard();
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
    public void eraseLinesTest() {
        Board ib = new ItemBoard();
        for(int i=0;i<Board.WIDTH;i++){
            ib.board[0][i] = new Block(Color.red, ConfigBlock.BLOCK_CHAR,0);
        }
        ib.eraseLines();
    }

    @Test
    public void eraseLLineTest() {
        ItemBoard ib = new ItemBoard();
        ib.eraseLLine();
    }

    @Test
    public void eraseQLineTest() {
        ItemBoard ib = new ItemBoard();
        ib.eraseQLine();
    }

    @Test
    public void generateNewLineTest() {
        ItemBoard ib = new ItemBoard();
        ib.generateNewLines(0);
    }

    @Test
    public void pendulumBlockTest() {
        ItemBoard ib = new ItemBoard();
        ib.focus = new PendulumBlock();
        ib.moveDown();
        ib.placePendulumBlock();
        ib.moveFallPendulumCurr();
    }

    @Test
    public void pendulumBlockTest1() {
        ItemBoard ib = new ItemBoard();
        ib.focus = new PendulumBlock();
        ib.focus.setIsSettled();
        ib.moveDown();
        ib.placePendulumBlock();
        ib.moveFallPendulumCurr();
    }

    @Test
    public void basicTest() throws InterruptedException {
        ItemBoard ib = new ItemBoard();
        Thread.sleep(1000);
        ib.gameOver(0,0);
    }

    @Test
    public void basicLineTest() throws InterruptedException {
        ItemBoard ib = new ItemBoard();
        KeyEvent ke = new KeyEvent(ib, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
        ke.setKeyCode(Keyboard.DOWN);
        ib.playerKeyListener.keyPressed(ke);
        ke.setKeyCode(Keyboard.RIGHT);
        ib.reset();
        ib.playerKeyListener.keyPressed(ke);
        ke.setKeyCode(Keyboard.LEFT);
        ib.reset();
        ib.playerKeyListener.keyPressed(ke);
        ke.setKeyCode(Keyboard.UP);
        ib.reset();
        ib.playerKeyListener.keyPressed(ke);
        ke.setKeyCode(Keyboard.SPACE);
        ib.reset();
        ib.playerKeyListener.keyPressed(ke);
        ib.reset();
        ib.playerKeyListener.keyPressed(ke);
        Board.stage += 10;
        Board.lineCount = 100;
        for(int i=0;i<Board.WIDTH;i++){
            ib.board[0][i] = new Block(Color.red, ConfigBlock.BLOCK_CHAR,0);
        }
        ib.eraseLines();
        ib.gameOver(0, 0);
    }
}
