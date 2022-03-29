package seoultech.se.tetris.component.board;

import seoultech.se.tetris.blocks.*;
import seoultech.se.tetris.blocks.item.pendulum.PendulumBlock;
import seoultech.se.tetris.blocks.item.random.*;
import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.config.ConfigBlock;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Random;


public abstract class Board extends JFrame {
    public static final long serialVersionUID = 2434035659171694595L;

    public static final int HEIGHT = 20;
    public static final int WIDTH = 10;

    protected ConfigBlock config = ConfigBlock.getInstance();

    public float initInterval = 1000;

    protected JTextPane pane;
    protected JPanel rightPanel;
    protected Score score;
    protected JTextPane nextPanel;

    protected KeyListener playerKeyListener;
    protected MouseListener playerMouseListener;
    protected SimpleAttributeSet styleSet;
    protected SimpleAttributeSet nextStyleSet;
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
            case 6 -> new OBlock();
            default -> new LBlock();
        };
    }

    public void printBoard() {
        System.out.print("    ");
        for (int i = 0; i < Board.WIDTH; i++) {
            System.out.printf("%2d", i);
        }
        System.out.println();

        for (int i = 0; i < Board.HEIGHT; i++) {
            System.out.printf("%d  ", i);
            for (int j = 0; j < Board.WIDTH; j++) {
                System.out.print(board[i][j] != null ? " 0" : "  ");
            }
            System.out.println();
        }
    }

    // 만약 블럭이 내려가면 똑같이 유지가 될까 아마 안될 것 같음.
    // draw에서 color를 주면 안되나?
    // 아마 여기서 color를 주는 것 같아요
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
                }
            }
        }
    }

    protected void timerSet(int combo) {
        timer.stop();
        initInterval *= rateInterval;
        timer = new Timer(Math.round(initInterval), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDown();
                drawBoard();
            }
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

    protected boolean isBottomTouch() {
        if (y < Board.HEIGHT - focus.height()) {
            return false;
        }
        return true;
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
//            score.setText("GAMEOVER");
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
        if (!isBottomTouch()) {
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
            if (!isBottomTouch()) {
                y++;
                if (isOverlap()) {
                    y--;
                    generateNewBlock();
                    break;
                }
            } else {
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
                doc.insertString(doc.getLength(), config.BORDER_CHAR, defaultStyle);
            }
            doc.insertString(doc.getLength(), "\n", defaultStyle);

            for (int i = 0; i < board.length; i++) {
                doc.insertString(doc.getLength(), config.BORDER_CHAR, defaultStyle);

                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != null) {
                        StyleConstants.setForeground(blockStyle, board[i][j].getColor());
                        doc.insertString(doc.getLength(), board[i][j].getCharacter(), blockStyle);
                    } else {
                        doc.insertString(doc.getLength(), config.NON_BLOCK_CHAR, defaultStyle);
                    }
                }
                doc.insertString(doc.getLength(), config.BORDER_CHAR, defaultStyle);
                doc.insertString(doc.getLength(), "\n", defaultStyle);

            }
            for (int t = 0; t < Board.WIDTH + 2; t++) {
                doc.insertString(doc.getLength(), config.BORDER_CHAR, defaultStyle);
            }
        } catch (Exception ignored) {
        }
    }

    // draw next block
    protected void drawNextBlock() {
        StringBuffer sb = new StringBuffer();
        SimpleAttributeSet styles = new SimpleAttributeSet();
        StyleConstants.setForeground(styles, next.getColor());

        int nW = next.width();
        int nH = next.height();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i < nH && j < nW && next.getShape(j, i) != null) {
                    sb.append(config.BLOCK_CHAR);
                } else {
                    sb.append(config.NON_BLOCK_CHAR);
                }
            }
            sb.append("\n");
        }
        nextPanel.setText(sb.toString());
        StyledDocument doc = nextPanel.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styles, true);
        nextPanel.setStyledDocument(doc);
    }

    protected ParentBlock getRandomItemBlock() {
        Random random = new Random(System.currentTimeMillis());
        int block = random.nextInt(14);
        switch (block) {
            case 0:
                return new RandomIBlock();
            case 1:
                return new RandomJBlock();
            case 2:
                return new RandomLBlock();
            case 3:
                return new RandomZBlock();
            case 4:
                return new RandomSBlock();
            case 5:
                return new RandomTBlock();
            case 6:
                return new RandomOBlock();
            default:
                return new PendulumBlock();
        }
    }

    protected void reset() {
        this.board = new Block[20][10];
    }
}
