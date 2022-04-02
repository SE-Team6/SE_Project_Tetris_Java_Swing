package seoultech.se.tetris.component.board;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.blocks.ParentBlock;
import seoultech.se.tetris.blocks.item.pendulum.PendulumBlock;
import seoultech.se.tetris.blocks.item.random.*;
import seoultech.se.tetris.component.Score;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.Random;

public class ItemBoard extends Board {
    private int cnt;
    boolean pendulum = false;
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
//        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
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

//        //Document default style.
//        styleSet = new SimpleAttributeSet();
//        StyleConstants.setFontSize(styleSet, 18);
//        StyleConstants.setFontFamily(styleSet, "Courier");
//        StyleConstants.setBold(styleSet, true);
//        StyleConstants.setForeground(styleSet, Color.WHITE);
//        StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);
//
//        nextStyleSet = new SimpleAttributeSet();
//
//        StyleConstants.setFontFamily(nextStyleSet, "Courier");
//        StyleConstants.setBold(nextStyleSet, true);
//        StyleConstants.setForeground(nextStyleSet, Color.WHITE);
//        StyleConstants.setAlignment(nextStyleSet, StyleConstants.ALIGN_CENTER);

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
        requestFocus();
        setFocusable(true);

        // line height
        SimpleAttributeSet tmp = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(tmp, -0.5F);
        StyleConstants.setAlignment(tmp, StyleConstants.ALIGN_CENTER);
        nextPanel.setParagraphAttributes(tmp, false);
        pane.setParagraphAttributes(tmp, false);

        // parent Style
        parentStyle = pane.addStyle("parentStyle", null);
        StyleConstants.setFontSize(parentStyle, 24);
        StyleConstants.setFontFamily(parentStyle, "Courier");
        StyleConstants.setBold(parentStyle, true);
        StyleConstants.setForeground(parentStyle, Color.WHITE);
        StyleConstants.setAlignment(parentStyle, StyleConstants.ALIGN_CENTER);

        defaultStyle = pane.addStyle("defaultStyle", parentStyle);
        blockStyle = pane.addStyle("blockStyle", parentStyle);

        //Create the first block and draw.
        focus = getRandomBlock();
        placeBlock();
        drawBoard();
        timer.start();
        next = getRandomBlock();
        drawNextBlock();

    }

    protected ParentBlock getRandomItemBlock() {
        Random random = new Random(System.currentTimeMillis());
        int block = random.nextInt(14);
        //return new PendulumBlock();
        switch (block) {
            case 0: return new RandomIBlock();
            case 1: return new RandomJBlock();
            case 2: return new RandomLBlock();
            case 3: return new RandomZBlock();
            case 4: return new RandomSBlock();
            case 5: return new RandomTBlock();
            case 6: return new RandomOBlock();
            default: return new PendulumBlock();
        }
    }

    @Override
    protected void eraseLines() {
        int combo = 0;
        for(int i=Board.HEIGHT-1;i>=0;i--){
            int tmp = 0;
            for(int j=0;j<Board.WIDTH;j++){
                if (board[i][j] != null) {
                    tmp++;
                }
            }
            if (tmp == Board.WIDTH) {
                for(int j=0;j<Board.WIDTH;j++){
                    for(int k=i;k>=1;k--){
                        board[k][j] = board[k-1][j];
                    }
                    board[0][j] = null;
                }
                i++;
                combo++;
                this.cnt++;
            }
        }
        if (combo > 0) {
            timerSet(combo);
        }
    }

    // generate new block
    @Override
    protected void generateNewBlock() {
        placeBlock();
        eraseLines();
        itemEraseLines();
        focus = next;
        if(cnt>=10){
            next = getRandomItemBlock();
            cnt=0;
        }
       else{
            next = getRandomBlock();
        }
        drawNextBlock();
       pendulum =false;
        x = 3;
        y = 0;

        // GAME OVER
        if (isOverlap()) {
            reset();
        }
    }

    public void eraseCurr() {
        for (int i = x; i < x + focus.width(); i++) {
            for (int j = y; j < y + focus.height(); j++) {

                if (focus.getShape(i - x, j - y) != null) {
                    if(getItemNum()==2 && y < HEIGHT - focus.height())
                    {
                        if(j+2<HEIGHT&&board[j+2][i]!=null){
                            pendulum=true;
                        }
                        board[j+1][i]=null;
                    }
                    board[j][i] = null;


                }
            }
        }
    }

    public int getItemNum(){
        int item=0;
        for (int i = y; i < y + focus.height(); i++) {
            for (int j = x; j < x + focus.width(); j++) {

                if (focus.getShape(j - x, i - y) != null && board[i][j] != null) {
                    item = board[i][j].getBlockType();

                }
            }
        }
        return item;
    }



    protected void moveFall() {
        eraseCurr();
        for (int i = y; i < Board.HEIGHT; i++) {

            if (!isBottomTouched()) {
                y++;
                if(getItemNum()==2) {
                    eraseCurr();
                }
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
        if(pendulum){
            return;
        }
        eraseCurr();
        if (x < Board.WIDTH - focus.width()) x++;
        if (isOverlap()) {
            x--;
        }
        placeBlock();
    }

    protected void moveLeft() {
        if(pendulum){
            return;
        }
        eraseCurr();
        if (x > 0) {
            x--;
        }
        if (isOverlap()) {
            x++;
        }
        placeBlock();
    }


    //퀸 블럭일 경우
    //대각선의 블럭을 없애야 함
    //사라진 블럭의 빈자리를 채워야함

    protected void itemEraseLines() {
        for (int i = y; i < y + focus.height(); i++) {
            int  itemCount = 0;
            for (int j = x; j < x + focus.width(); j++) {
                if(board[i][j]!=null){
                    if ( board[i][j].getBlockType()==1||board[i][j].getBlockType()==3) {
                        itemCount++;
                        if(board[i][j].getBlockType()==3){
                            for(int k=0; k<Board.HEIGHT; k++) {
                                board[k][j] = null;
                            }
                        }
                    }
                }
            }
            if (itemCount==1) {
                for (int j = 0; j < Board.WIDTH; j++) {
                    for (int k = i; k >= 1; k--) {
                        board[k][j] = board[k - 1][j];
                    }
                    board[0][j] = null;
                }
                i++;
            }
        }
    }
}
