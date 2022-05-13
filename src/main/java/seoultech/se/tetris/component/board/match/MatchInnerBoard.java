package seoultech.se.tetris.component.board.match;

import seoultech.se.tetris.blocks.*;
import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.component.board.Board;
import seoultech.se.tetris.config.ConfigBlock;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MatchInnerBoard extends JPanel {
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
    public static final int STACK_MAX = 10;

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
    protected JTextPane stackPanel;

    protected KeyListener playerKeyListener;
    protected MouseListener playerMouseListener;

    protected Style parentStyle;
    protected Style defaultStyle;
    protected Style blockStyle;
    protected Style stackStyle;

    protected Timer timer;
    protected ParentBlock focus;
    protected ParentBlock next;

    protected boolean isErased = false;
    protected static int diff = 0;
    protected static int stageUpStandard = 5;
    protected static float rateInterval = 0.95F;

    protected int x = 3; //Default Position.
    protected int y = 0;
    protected int previousFallX = 3;
    protected int previousFallY = 0;

    protected Block[][] board;
    protected Block[][] stackBoard;
    protected Block[][] prevBoard;
    public int stackLine = 0;

    protected boolean isAction = false;
    public boolean gameDone = false;

    public ArrayList<Block[]> attProp;

    public MatchInnerBoard() {

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
                if (x + i<0 || y + j<0 || x + i>= Board.WIDTH || y + j>=Board.HEIGHT) continue;
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
        copyBlock();
        placeBlock();
        loadStackBaord();
        eraseLines();
        focus = next;
        next = getRandomBlock();
        drawNextBlock();
        x = 3;
        y = -focus.getTop();

        // GAME OVER
        if (isOverlap()) {
            gameOver();
        }
    }

    protected void copyBlock() {
//        prevBoard = board.clone();
        for(int i=0;i<HEIGHT;i++){
            prevBoard[i] = Arrays.copyOf(board[i], WIDTH);
        }
    }

    protected void attackLines(ArrayList<Integer> att) {
        ArrayList<Block[]> stack = new ArrayList<Block[]>();
        for(int i=0;i<att.size();i++){
            int row = att.get(i);
            stack.add(prevBoard[row].clone());
        }

        firePropertyChange("attProp", attProp, stack);
    }

    public void attackedLines(ArrayList<Block[]> att) {
        if (stackLine >= STACK_MAX) return;
        int size = stackLine + att.size() > STACK_MAX ? stackLine+ att.size()-STACK_MAX+1 : att.size();
        //        int size = STACK_MAX-stackLine<att.size()?1:0;
        // size 만큼 땡김
        for(int i=0;i<STACK_MAX-size;i++){
            stackBoard[i] = stackBoard[i+size];
        }
        // prev 밑에 저장
        for(int i=att.size()-size,j=0;i<size;i++,j++){
            stackBoard[STACK_MAX-j-1] = att.get(i);
        }

        this.stackLine += att.size();
        if(this.stackLine > STACK_MAX) {
            this.stackLine = STACK_MAX;
        }

        drawStackBoard();
    }

    protected void loadStackBaord() {
        if (stackLine == 0) return;
        for(int i=0;i<HEIGHT-stackLine;i++){
            if (i<stackLine && !isNullLine(i)) {
                gameOver();
            }
            board[i] = board[i+stackLine];
        }
        for(int i=0;i<stackLine;i++){
            board[HEIGHT-i-1] = stackBoard[STACK_MAX - i - 1];
        }

        stackBoard = new Block[STACK_MAX][WIDTH];
        stackLine = 0;
        drawStackBoard();
    }

    protected boolean isNullLine(int row) {
        for(int i=0;i<WIDTH;i++){
            if (board[row][i] != null) return false;
        }
        return true;
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
        ArrayList<Integer> attack = new ArrayList<Integer>();
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
                attack.add(i-combo);
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
        if (combo>1){
            attackLines(attack);
        }
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

    protected void drawStackBoard() {
        StyledDocument doc = stackPanel.getStyledDocument();
        stackPanel.setText("");
        try {
            for (Block[] blocks : stackBoard) {

                for (Block block : blocks) {
                    if (block != null) {
                        StyleConstants.setForeground(blockStyle, block.getColor());
                        doc.insertString(doc.getLength(), block.getCharacter(), blockStyle);
                    } else {
                        doc.insertString(doc.getLength(), ConfigBlock.NON_BLOCK_CHAR, defaultStyle);
                    }
                }
                doc.insertString(doc.getLength(), "\n", defaultStyle);

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

    public void gameOver() {
        System.out.println("Game over!");
        timer.stop();

        firePropertyChange("gameDone", this.gameDone, true);
        this.gameDone = true;

        //        new GameOver(x, y);
    }



    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
}
