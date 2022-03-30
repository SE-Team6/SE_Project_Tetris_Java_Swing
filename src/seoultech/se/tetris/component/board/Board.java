package seoultech.se.tetris.component.board;

import seoultech.se.tetris.blocks.*;
import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.config.ConfigBlock;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import static seoultech.se.tetris.blocks.ParentBlock.itemNum;


public abstract class Board extends JFrame {
    public static final long serialVersionUID = 2434035659171694595L;

    public static final int HEIGHT = 20;
    public static final int WIDTH = 10;
    public boolean itemBlock =false;
    protected ConfigBlock config = ConfigBlock.getInstance();

    public float initInterval = 1000;

    protected JTextPane pane;
    protected JPanel rightPanel;
    protected Score score;
    protected JTextPane nextPanel;

    protected KeyListener playerKeyListener;
    protected MouseListener playerMouseListener;
//    protected SimpleAttributeSet styleSet;
//    protected SimpleAttributeSet nextStyleSet;
    protected Style parentStyle;
    protected Style defaultStyle;
    protected Style blockStyle;

    protected Timer timer;
    protected ParentBlock focus;
    protected ParentBlock next;

    protected float rateInterval = 0.95F;

    protected int x = 3; //Default Position.
    protected int y = 0;

    protected Block[][] board;

    public Board() {
        super("SW TEAM 6");
    }

    protected void focusFrame() {
        requestFocus();
        setFocusable(true);
    }

    protected ParentBlock getRandomBlock() {
        Random random = new Random(System.currentTimeMillis());
        int block = random.nextInt(7);
        return switch (block) {
            case 0 -> new IBlock();
            case 1 -> new JBlock();
            case 2 -> new LBlock();
            case 3 -> new ZBlock();
            case 4 -> new SBlock();
            case 5 -> new TBlock();
            default ->  new OBlock();
        };
    }

    public void placeBlock() {
        for (int j = 0; j < focus.height(); j++) {
            for (int i = 0; i < focus.width(); i++) {
                if (board[y + j][x + i] == null && focus.getShape(i, j) != null) {
                    board[y + j][x + i] = focus.getShape(i, j);
                }
            }
        }
    }

    public void eraseCurr() {
        for (int i = x; i < x + focus.width(); i++) {
            for (int j = y; j < y + focus.height(); j++) {
                if (focus.getShape(i - x, j - y) != null) {
                    board[j][i] = null;
                    if(itemNum ==21 && y < HEIGHT - focus.height()){
                        board[j+1][i] = null;}

                }
            }
        }
    }

    protected void timerSet(int combo) {
        timer.stop();
        initInterval *= rateInterval;
        timer = new Timer(Math.round(initInterval), e -> {
            moveDown();
            drawBoard();
        });
        timer.start();
        score.addScore(combo);
    }


    // add overlap check
    protected boolean isOverlap() {
        for (int i = x; i < x + focus.width(); i++) {
            for (int j = y; j < y + focus.height(); j++) {
                if (
                        (board[j][i] != null) &&
                                (focus.getShape(i - x, j - y) != null)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isBottomTouched() {
        return y >= Board.HEIGHT - focus.height();
    }


    // generate new block
    protected void generateNewBlock() {
        placeBlock();
        eraseLines();
        focus = next;
        next = getRandomBlock();
        drawNextBlock();
        x = 3;
        y = 0;

        // GAME OVER
        if (isOverlap()) {
            reset();
            score.resetScore();
        }
    }

    protected void eraseLines() {
        int combo = 0;
        for (int i = Board.HEIGHT - 1; i >= 0; i--) {
            int tmp = 0;
            for (int j = 0; j < Board.WIDTH; j++) {
                if (board[i][j] != null) {
                    tmp++;
                }
            }
            if (tmp == Board.WIDTH) {
                for (int j = 0; j < Board.WIDTH; j++) {
                    for (int k = i; k >= 1; k--) {
                        board[k][j] = board[k - 1][j];
                    }
                    board[0][j] = null;
                }
                i++;
                combo++;
            }
        }
        if (combo > 0) {
            timerSet(combo);
        }
    }

    protected void moveDown() {
        eraseCurr();
        if (!isBottomTouched()) {
            y++;

            if (isOverlap()) {
                y--;
                generateNewBlock();
            }
        } else {
            generateNewBlock();
        }
        placeBlock();
    }

    protected void moveFall() {
        eraseCurr();
        for (int i = y; i < Board.HEIGHT; i++) {
            if (!isBottomTouched()) {
                y++;
                if(itemNum ==21 && y < HEIGHT - focus.height()){
                    eraseCurr();}

                if (isOverlap()) {
                    y--;
                    generateNewBlock();
                    break;
                }
            }


            else {
                generateNewBlock();
                break;
            }
        }
        placeBlock();
    }

    protected void moveRight() {
        eraseCurr();
        if (x < Board.WIDTH - focus.width()) x++;
        if (isOverlap()) {
            x--;
        }
        placeBlock();
    }


    protected void moveLeft() {
        eraseCurr();
        if (x > 0) {
            x--;
        }
        if (isOverlap()) {
            x++;
        }
        placeBlock();
    }

    protected void moveRotate() {
        eraseCurr();
        focus.rotate();
        if (x < 0 || x >= Board.WIDTH - focus.width() + 1 || y + focus.height() - 1 >= Board.HEIGHT || isOverlap()) {
            focus.rotateBack();
        }
        placeBlock();
    }

    protected void drawBoard() {
        StyledDocument doc = pane.getStyledDocument();
        pane.setText("");
        try {
            for (int t = 0; t < Board.WIDTH + 2; t++) {
                doc.insertString(doc.getLength(), ConfigBlock.BORDER_CHAR, defaultStyle);
            }
            doc.insertString(doc.getLength(), "\n", defaultStyle);

            for (Block[] blocks : board) {
                doc.insertString(doc.getLength(), ConfigBlock.BORDER_CHAR, defaultStyle);

                for (Block block : blocks) {
                    if (block != null) {
                        StyleConstants.setForeground(blockStyle, block.getColor());
                        doc.insertString(doc.getLength(), block.getCharacter(), blockStyle);
                    } else {
                        doc.insertString(doc.getLength(), ConfigBlock.NON_BLOCK_CHAR, defaultStyle);
                    }
                }
                doc.insertString(doc.getLength(), ConfigBlock.BORDER_CHAR, defaultStyle);
                doc.insertString(doc.getLength(), "\n", defaultStyle);

            }
            for (int t = 0; t < Board.WIDTH + 2; t++) {
                doc.insertString(doc.getLength(), ConfigBlock.BORDER_CHAR, defaultStyle);
            }
        } catch (Exception ignored) {
        }
    }

    // draw next block
    protected void drawNextBlock() {
        StyledDocument doc = nextPanel.getStyledDocument();
        nextPanel.setText("");

        int nW = next.width();
        int nH = next.height();
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (i < nH && j < nW && next.getShape(j, i) != null) {
                        StyleConstants.setForeground(blockStyle, next.getShape(j, i).getColor());
                        doc.insertString(doc.getLength(), next.getShape(j, i).getCharacter(), blockStyle);
                    } else {
                        doc.insertString(doc.getLength(), ConfigBlock.NON_BLOCK_CHAR, defaultStyle);
                    }
                }
                doc.insertString(doc.getLength(), "\n", defaultStyle);
            }
        } catch(Exception ignored) {}
    }

    protected void reset() {
        this.board = new Block[20][10];
    }

    public class PlayerKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN -> {
                    moveDown();
                    drawBoard();
                }
                case KeyEvent.VK_RIGHT -> {
                    moveRight();
                    drawBoard();
                }
                case KeyEvent.VK_LEFT -> {
                    moveLeft();
                    drawBoard();
                }
                case KeyEvent.VK_UP -> {
                    moveRotate();
                    drawBoard();
                }
                case KeyEvent.VK_SPACE -> {
                    moveFall();
                    drawBoard();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }


    // @TODO
    // 일단 불편해서 추가함
    // Mouse 객체도 관리해야할 듯
    public class PlayerMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            focusFrame();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
