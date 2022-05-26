package seoultech.se.tetris.component.board;

import seoultech.se.tetris.blocks.*;
import seoultech.se.tetris.component.Keyboard;
import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.component.pause.PauseView;
import seoultech.se.tetris.config.ConfigBlock;
import seoultech.se.tetris.main.GameOver;
import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;


public abstract class Board extends JFrame {
    public static final long serialVersionUID = 2434035659171694595L;

    //easy, normal, hard
    protected static final double[][] blockFitness = {
            {120,100,100,100,100,100,100},
            {100,100,100,100,100,100,100},
            {80,100,100,100,100,100,100}
    };

    protected static double[] prob = {0.142857,0.142857,0.142857,0.142857,0.142857,0.142857,0.142857};
    protected static double[] itemProb = new double[17];

    protected static Random random = new Random(System.currentTimeMillis());

    public static final int HEIGHT = 20;
    public static final int WIDTH = 10;

    // Erased Line Count
    protected static int lineCount = 0;
    protected static int stage = 1;
    protected static int seq = 0;

    protected ConfigBlock config = ConfigBlock.getInstance();

    public static float initInterval = 1000;

    protected JTextPane pane;
    protected JPanel rightPanel;
    protected Score score;
    protected JTextPane nextPanel;

    protected KeyListener playerKeyListener;
    protected MouseListener playerMouseListener;

    protected Style parentStyle;
    protected Style defaultStyle;
    protected Style blockStyle;

    protected Timer timer;
    protected ParentBlock focus;
    protected ParentBlock next;

    protected boolean isErased = false;
    protected static int diff = 0;
    // 몇 줄을 지우면 다음 아이템이 나올지?
    protected static int stageUpStandard = 10;
    protected static float rateInterval = 0.95F;

    protected int x = 3; //Default Position.
    protected int y = 0;
    protected int previousFallX = 3;
    protected int previousFallY = 0;

    protected Block[][] board;

    protected boolean isPause = false;

    protected PauseView pv;

    protected boolean isAction = false;

    public Board() {
        super("SW TEAM 6");
        URL urlIcon = Tetris.class.getResource("/image/icon/icon.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image imgIcon = kit.createImage(urlIcon);
        setIconImage(imgIcon);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    protected void focusFrame() {
        requestFocus();
        setFocusable(true);
    }

    // set difficulty -> probability and interval
    public static void setDifficulty(int difficulty){
        lineCount = 0;
        initInterval = 1000;
        double sum = Arrays.stream(blockFitness[difficulty]).sum();
        prob = Arrays.stream(blockFitness[difficulty]).map((x)->x/sum).toArray();
        for(int i=0; i<17; ++i) itemProb[i] = (double)1/17;
        diff = difficulty;
        stage = difficulty*5+1;
        for(int i=0; i<difficulty; ++i){
            initInterval *= 0.8;
        }
    }

    protected int getRoulette(){
        double u = random.nextDouble();
        for(int i=0; i<7; ++i){
            u -= prob[i];
            if(u<0){
                return i;
            }
        }
        return 6;
    }

    protected ParentBlock getRandomBlock() {
        int block = getRoulette();
        switch (block) {
            case 0: return new IBlock();
            case 1: return new JBlock();
            case 2: return new LBlock();
            case 3: return new ZBlock();
            case 4: return new SBlock();
            case 5: return new TBlock();
            default: return new OBlock();
        }
    }

    public void placeBlock() {
        for (int j = 0; j < focus.height(); j++) {
            for (int i = 0; i < focus.width(); i++) {
                if (x + i<0 || y + j<0 || x + i>=Board.WIDTH || y + j>=Board.HEIGHT) continue;
                if (board[y + j][x + i] == null && focus.getShape(i, j) != null) {
                    board[y + j][x + i] = focus.getShape(i, j);
                }
            }
        }
    }

    public void eraseCurr() {
        for (int i = x; i < x + focus.width(); i++) {
            for (int j = y; j < y + focus.height(); j++) {
                if (i<0 || j<0 || i>=Board.WIDTH || j>=Board.HEIGHT) continue;
                if (focus.getShape(i - x, j - y) != null) {
                    board[j][i] = null;
                }
            }
        }
    }

    protected void timerSet() {
        isAction = false;
        timer.stop();
        timer = new Timer(Math.round(initInterval), e -> {
            moveDown();
            drawBoard();
        });
        timer.start();
    }

    protected void timerSpeedUpSet() {
        isAction = false;
        timer.stop();
        initInterval *= rateInterval;
        timer = new Timer(Math.round(initInterval), e -> {
            moveDown();
            drawBoard();
        });
        timer.start();
    }

    // add overlap check
    protected boolean isOverlap() {
        for (int i = x; i < x + focus.width(); i++) {
            for (int j = y; j < y + focus.height(); j++) {
                if (i<0 || j<0 || i>=Board.WIDTH || j>=Board.HEIGHT) {
                    if (focus.getShape(i - x, j - y) != null && y < 0) {
                        return true;
                    }
                    continue;
                }
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
        return y + 1>= Board.HEIGHT - focus.getBottom();
    }

    // generate new block
    protected void generateNewBlock() {
        placeBlock();
        eraseLines();
        focus = next;
        next = getRandomBlock();
        drawNextBlock();
        x = 3;
        y = -focus.getTop();

        // GAME OVER
        if (isOverlap()) {
            gameOver(getX(), getY());
        }
    }

    protected void replaceBlockToStarHorizontal(int targetL, int targetR) {
        timer.stop();
        isAction = true;
        String all = pane.getText();
        String[] rows = all.split("\n");
        StringBuilder tmp = new StringBuilder();
        for(int i=0;i<WIDTH+2;i++){
            tmp.append(i==0 || i==WIDTH+1 ? ConfigBlock.BORDER_CHAR : ConfigBlock.STAR);
        }
        for (int i=targetL;i<=targetR;i++){
            rows[i+1] = tmp.toString();
        }
        // erase Curr
        x = previousFallX; y = previousFallY;
        for (int j = y; j < y + focus.height(); j++) {
            for (int i = x; i < x + focus.width(); i++) {
                if (focus.getShape(i - x, j - y) != null) {
                    rows[j+1] = rows[j+1].substring(0, i+1) + ConfigBlock.NON_BLOCK_CHAR + rows[j+1].substring(i+2);
                }
            }
        }

        StringBuilder res = new StringBuilder();
        for (String row: rows) {
            res.append(row);
            res.append("\n");
        }

        pane.setText(res.toString());
        timer = new Timer(Math.round(initInterval/10), e -> {
            activeDrawBoard();
        });
        timer.start();
    }

    protected void activeDrawBoard(){
        isAction = false;
        drawBoard();
        isAction = false;
        timerSet();
    }

    protected void eraseLines() {
        int combo = 0;
        isErased = false;
        int left = 0; int right = -1;
        boolean isErased = false;
        for (int i = Board.HEIGHT - 1; i >= 0; i--) {
            int tmp = 0;
            for (int j = 0; j < Board.WIDTH; j++) {
                if (board[i][j] != null) {
                    tmp++;
                }
            }
            if (tmp == Board.WIDTH) {
                if (right == -1) {
                    right = i;
                    left = i;
                } else {
                    left--;
                }
                for (int j = 0; j < Board.WIDTH; j++) {
                    for (int k = i; k >= 1; k--) {
                        board[k][j] = board[k - 1][j];
                    }
                    board[0][j] = null;
                }
                isErased = true;
                i++;
                combo++;
                lineCount++;
            }
        }
        if (left <= right) {
            replaceBlockToStarHorizontal(left, right);
        }
        if(isErased){
            seq += 1;
        }else{
            seq = 0;
        }
        score.addLineClearScore(combo, stage, seq);
    }

    protected void moveDown() {
        eraseCurr();
        score.addUnitScore(1);
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
        previousFallX = x;
        previousFallY = y;
        eraseCurr();
        score.addUnitScore((Board.HEIGHT - y)*(diff+1)*2);
        for (int i = y; i < Board.HEIGHT; i++) {
            if (!isBottomTouched()) {
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
        if (x + 1 < Board.WIDTH - focus.getRight()) x++;
        if (isOverlap()) {
            x--;
        }
        placeBlock();
    }


    protected void moveLeft() {
        eraseCurr();
        if (x + focus.getLeft()> 0){
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
        if (x +focus.getLeft() < 0 || x >= Board.WIDTH - focus.getRight()  || y + focus.getBottom() >= Board.HEIGHT || isOverlap()) {
            focus.rotateBack();
        }
        placeBlock();
    }

    protected void drawBoard() {
        if (isAction) {
            System.out.println("drawBoard stop");
            return;
        }
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

    public void gameOver(int x, int y) {
        System.out.println("Game over!");
        timer.stop();
        this.dispose();
        new GameOver(x, y, score.getScore());
    }

    protected void pause() {
        System.out.println("pause");
        System.out.println(isPause);
        if (!isPause) {
            timer.stop();
            pv = new PauseView(score.getScore(), this);
            pv.setScore(score.getScore());
            pv.setLocationRelativeTo(this);
            int w = this.getWidth();
            int h = this.getHeight();
            int x = this.getX();
            int y = this.getY();
            pv.setLocation(x + w/4, y + h/4);
            pv.setSize(w/2, h/2);
            pv.setVisible(true);
        }
        setIsPause();
    }

    public void setIsPause() {
        this.isPause = !this.isPause;
    }

    public void startTimer() {
        timer.start();
    }

    public class PlayerKeyListener extends Keyboard {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == Keyboard.DOWN) {
                moveDown();
                drawBoard();
            } else if (keyCode == Keyboard.RIGHT) {
                moveRight();
                drawBoard();
            } else if (keyCode == Keyboard.LEFT) {
                moveLeft();
                drawBoard();
            } else if (keyCode == Keyboard.UP) {
                moveRotate();
                drawBoard();
            } else if (keyCode == Keyboard.SPACE) {
                moveFall();
                drawBoard();
            } else if (keyCode == Keyboard.ESC) {
                pause();
            }
        }
    }


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
