package seoultech.se.tetris.component.board;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.blocks.ParentBlock;
import seoultech.se.tetris.blocks.item.one.OneBlock;
import seoultech.se.tetris.blocks.item.pendulum.PendulumBlock;
import seoultech.se.tetris.blocks.item.queen.*;
import seoultech.se.tetris.blocks.item.random.*;
import seoultech.se.tetris.blocks.item.slime.SlimeBlock;
import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.component.pause.PauseView;
import seoultech.se.tetris.config.ConfigBlock;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.Random;

public class ItemBoard extends Board {
    private int cnt;
    private int eventX; int eventY;
    private boolean event = false;

    public ItemBoard() {
        this.cnt = 0;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Board display setting.
        pane = new JTextPane();
        pane.setEditable(false);
        pane.setBackground(Color.BLACK);
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        pane.setBorder(border);

        // right items
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 1));
        rightPanel.setLayout(new GridLayout(4, 1));
        rightPanel.setBackground(Color.GRAY);

        score = new Score();
        rightPanel.add(score);

        nextPanel = new JTextPane();
        nextPanel.setEditable(false);
        nextPanel.setBackground(Color.BLACK);
        CompoundBorder nextBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        nextPanel.setBorder(nextBorder);
        rightPanel.add(nextPanel);

        CompoundBorder rightBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        rightPanel.setBorder(rightBorder);

        this.getContentPane().add(pane, BorderLayout.CENTER);
        this.getContentPane().add(rightPanel, BorderLayout.LINE_END);

        //Set timer for block drops.
        timer = new Timer(Math.round(initInterval), e -> {
            moveDown();
            drawBoard();
        });

        //Initialize board for the game.
        board = new Block[Board.HEIGHT][Board.WIDTH];
        playerKeyListener = new PlayerKeyListener();
        addKeyListener(playerKeyListener);
        playerMouseListener = new PlayerMouseListener();
        addMouseListener(playerMouseListener);
        pane.addMouseListener(playerMouseListener);
        rightPanel.addMouseListener(playerMouseListener);
        requestFocus();
        setFocusable(true);

        // line height
        SimpleAttributeSet tmp = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(tmp, ConfigBlock.lineSpacing);
        StyleConstants.setAlignment(tmp, StyleConstants.ALIGN_CENTER);
        nextPanel.setParagraphAttributes(tmp, false);
        pane.setParagraphAttributes(tmp, false);

        // parent Style
        parentStyle = pane.addStyle("parentStyle", null);
        StyleConstants.setFontSize(parentStyle, ConfigBlock.fontSize);
        StyleConstants.setFontFamily(parentStyle, ConfigBlock.font);
        StyleConstants.setBold(parentStyle, true);
        StyleConstants.setForeground(parentStyle, Color.WHITE);
        StyleConstants.setAlignment(parentStyle, StyleConstants.ALIGN_CENTER);

        defaultStyle = pane.addStyle("defaultStyle", parentStyle);
        blockStyle = pane.addStyle("blockStyle", parentStyle);

        //Create the first block and draw.
        focus = getRandomBlock();
        y = - focus.getTop();
        placeBlock();
        drawBoard();
        timer.start();
        next = getRandomBlock();
        drawNextBlock();

        pv = new PauseView(0, this);
    }

    protected int getItemRoulette() {
        double u = random.nextDouble();
        for(int i=0; i<16; ++i){
            u -= itemProb[i];
            if(u<0){
                return i;
            }
        }
        return 17;
    }

    @Override
    protected void replaceBlockToStarHorizontal(int targetL, int targetR) {
        timer.stop();
        isAction = true;
        String all = pane.getText();
        String[] rows = all.split("\n");
        StringBuilder tmp = new StringBuilder();
        for(int i=0;i<WIDTH+2;i++){
            tmp.append(i==0 || i==WIDTH+1 ? ConfigBlock.BORDER_CHAR : ConfigBlock.STAR);
        }

        // erase Curr
        x = previousFallX; y = previousFallY;
        for (int j = y; j < y + focus.height(); j++) {
            for (int i = x; i < x + focus.width(); i++) {
                if (i<0 || j<0 || i>=Board.WIDTH || j>=Board.HEIGHT) continue;
                if (focus.getShape(i - x, j - y) != null) {
                    rows[j+1] = rows[j+1].substring(0, i+1) + ConfigBlock.NON_BLOCK_CHAR + rows[j+1].substring(i+2);
                }
            }
        }

        if (eventX != -1) {
            for(int i=0;i<HEIGHT;i++){
                rows[i+1] = rows[i+1].substring(0, eventX+1) + ConfigBlock.STAR + rows[i+1].substring(eventX+2);
            }
            eventX = -1;
        }
        for (int i=0;i<HEIGHT;i++){
            if ((targetL<=i && i<=targetR) || i == eventY) {
                rows[i+1] = tmp.toString();
            }
        }

        StringBuilder res = new StringBuilder();
        for (String row: rows) {
            res.append(row);
            res.append("\n");
        }

        pane.setText(res.toString());
        event = false;

        timer = new Timer(Math.round(initInterval/10), e -> {
            activeDrawBoard();
        });
        timer.start();
    }

    protected ParentBlock getRandomItemBlock() {
        int block = getItemRoulette();
        switch (block) {
            case 0: return new RandomIBlock();
            case 1: return new RandomJBlock();
            case 2: return new RandomLBlock();
            case 3: return new RandomZBlock();
            case 4: return new RandomSBlock();
            case 5: return new RandomTBlock();
            case 6: return new RandomOBlock();
            case 7: return new OneBlock();
            case 8: return new SlimeBlock();
            case 9: return new QueenIBlock();
            case 10: return new QueenJBlock();
            case 11: return new QueenLBlock();
            case 12: return new QueenZBlock();
            case 13: return new QueenSBlock();
            case 14: return new QueenTBlock();
            case 15: return new QueenOBlock();
            default: return new PendulumBlock();
        }
    }

    @Override
    protected void eraseLines() {
        int combo = 0;
        int left = 0; int right = -1;
        boolean isErased = false;

        // itemType == 1 L block
        if (focus.getBlockType() == 1) {
            eraseLLine();
            combo++;
        }

        for(int i=Board.HEIGHT-1;i>=0;i--){
            int tmp = 0;
            for(int j=0;j<Board.WIDTH;j++){
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
                for(int j=0;j<Board.WIDTH;j++){
                    for(int k=i;k>=1;k--){
                        board[k][j] = board[k-1][j];
                    }
                    board[0][j] = null;
                }
                i++;
                combo++;
                lineCount++;
                isErased = true;
            }
        }

        // itemType == 2 무게추

        // itemType == 3 Queen
        if (focus.getBlockType() == 3) {
            eraseQLine();
        }

        if (left <= right || event) {
            replaceBlockToStarHorizontal(left, right);
        }

        // itemType == 4
        if (focus.getBlockType() == 4 && !isErased) {
            generateNewLines(2);
        }
        if(isErased){
            seq += 1;
        }else{
            seq = 0;
        }
        score.addLineClearScore(combo, stage, seq);
        if (lineCount >= stageUpStandard) {
            stage += 1;
            System.out.println(stage);
            timerSpeedUpSet();
        }
    }

    protected void eraseLLine() {
        int[] pos = focus.getBlockRandomPos();
        int targetX = x + pos[1];
        int targetY = y + pos[0];

        eventX = -1; eventY = targetY; event = true;
        eventX = -1; eventY = targetY; event = true;

        eraseHorizontalLine(targetY);
    }

    protected void eraseQLine() {
        int[] pos = focus.getBlockRandomPos();
        int targetX = x + pos[1];
        int targetY = y + pos[0];

        eventX = targetX; eventY = targetY; event = true;
        eraseHorizontalLine(targetY);
        eraseVerticalLine(targetX);
    }

    private void eraseHorizontalLine(int targetY) {
        for(int j=0;j<Board.WIDTH;j++){
            for(int k=targetY;k>=1;k--){
                board[k][j] = board[k-1][j];
            }
            board[0][j] = null;
        }
    }

    private void eraseVerticalLine(int targetX) {
        for(int j=0;j<Board.HEIGHT;j++){
            board[j][targetX] = null;
        }
    }
    protected void generateNewLines(int line) {
        Random random = new Random(System.currentTimeMillis());
        for(int i=0;i<HEIGHT-line;i++){
            if (i<line && !isNullLine(i)) {
                gameOver(getX(), getY());
            }
            board[i] = board[i+line];
        }
        for(int i=0;i<line;i++){
            int idx = random.nextInt(WIDTH);
            Block[] row = generateNewLine(idx);
            board[HEIGHT-i-1] = row;
        }
    }

    private Block[] generateNewLine(int idx) {
        Block[] row = new Block[WIDTH];
        for(int i=0;i<WIDTH;i++){
            if (i==idx) continue;
            row[i] = new Block(Color.GRAY, ConfigBlock.BLOCK_CHAR, 0);
        }
        return row;
    }

    protected boolean isNullLine(int row) {
        for(int i=0;i<WIDTH;i++){
            if (board[row][i] != null) return false;
        }
        return true;
    }

    // generate new block
    @Override
    protected void generateNewBlock() {
        if (focus.getBlockType() != 2) {
            placeBlock();
        }
        eraseLines();
        focus = next;
        if (lineCount >= stageUpStandard) {
            lineCount -= stageUpStandard;
            next = getRandomBlock();
        } else {
            next = getRandomItemBlock();
        }
        drawNextBlock();
        x = 3;
        y = 0;

        // GAME OVER
        if (isOverlap()) {
            gameOver(getX(), getY());
        }
    }

    public void placePendulumBlock() {
        for (int j = 0; j < focus.height(); j++) {
            for (int i = 0; i < focus.width(); i++) {
//                if (board[y + j][x + i] == null && focus.getShape(i, j) != null) {
                if (x + i<0 || y + j<0 || x + i>=Board.WIDTH || y + j>=Board.HEIGHT) continue;
                board[y + j][x + i] = focus.getShape(i, j);
            }
        }
    }

    public void erasePendulumCurr() {
        for (int i = x; i < x + focus.width(); i++) {
            for (int j = y; j < y + focus.height(); j++) {
//                if (focus.getShape(i - x, j - y) != null) {
                if (i<0 || j<0 || i>=Board.WIDTH || j>=Board.HEIGHT) continue;
                board[j][i] = null;
//                }
            }
        }
    }

    @Override
    protected void moveRight() {
        if (focus.getIsSettled()) return;
        eraseCurr();
//        if (x < Board.WIDTH - focus.width()) x++;
        if (x + 1 < Board.WIDTH - focus.getRight()) x++;
        if (isOverlap()) {
            x--;
        }
        placeBlock();
    }


    @Override
    protected void moveLeft() {
        if (focus.getIsSettled()) return;
        eraseCurr();
        if (x + focus.getLeft()> 0){
            x--;
        }
        if (isOverlap()) {
            x++;
        }
        placeBlock();
    }

    @Override
    protected void moveDown() {
        if (focus.getBlockType() == 2 && focus.getIsSettled()) {
            erasePendulumCurr();
            if (!isBottomTouched()) {
                y++;
                placePendulumBlock();
            } else {
                generateNewBlock();
            }

            return;
        }
        eraseCurr();
        score.addUnitScore(diff+1);
        if (!isBottomTouched()) {
            y++;
            if (isOverlap()) {
                if (focus.getBlockType() == 2 && !focus.getIsSettled()) {
                    focus.setIsSettled();
                    placePendulumBlock();
                    return;
                }
                y--;
                generateNewBlock();
            }
        } else {
            generateNewBlock();
        }
        placeBlock();
    }

    protected void moveFallPendulumCurr() {
        erasePendulumCurr();
        for (int i=x;i<x+focus.width();i++){
            eraseVerticalLine(i);
        }
        generateNewBlock();
        return;
    }

    @Override
    protected void moveFall() {
        if (focus.getBlockType() == 2) {
            moveFallPendulumCurr();
            return;
        }
        previousFallX = x; previousFallY = y;
        super.moveFall();
    }
}
