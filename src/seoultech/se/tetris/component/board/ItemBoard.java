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
        focus = getRandomItemBlock();
        placeBlock();
        drawBoard();
        timer.start();
        next = getRandomItemBlock();
        drawNextBlock();

    }

    protected ParentBlock getRandomItemBlock() {
        Random random = new Random(System.currentTimeMillis());
        int block = random.nextInt(14);
        return switch (block) {
            case 0 -> new RandomIBlock();
            case 1 -> new RandomJBlock();
            case 2 -> new RandomLBlock();
            case 3 -> new RandomZBlock();
            case 4 -> new RandomSBlock();
            case 5 -> new RandomTBlock();
            case 6 -> new RandomOBlock();
            default -> new PendulumBlock();
        };
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
        focus = next;
        next = getRandomItemBlock();
        drawNextBlock();
        x = 3;
        y = 0;

        // GAME OVER
        if (isOverlap()) {
            reset();
        }
    }
    protected void itemEraseLines() {
        for (int i = Board.HEIGHT - 1; i >= 0; i--) {
            int  itemCount = 0;
            for (int j = 0; j < Board.WIDTH; j++) {

//                if (Arrays.asList(board[i][j]).contains(RANDOM_CHAR) ) {
//                    itemCount++;
//                }
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
