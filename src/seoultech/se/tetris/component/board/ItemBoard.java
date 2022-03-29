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
import java.awt.event.*;
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
        rightPanel.setLayout(new GridLayout(7, 1));
        rightPanel.setLayout(new GridLayout(7, 1));
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

        //Document default style.
        styleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(styleSet, 18);
        StyleConstants.setFontFamily(styleSet, "Courier");
        StyleConstants.setBold(styleSet, true);
        StyleConstants.setForeground(styleSet, Color.WHITE);
        StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);

        nextStyleSet = new SimpleAttributeSet();

        StyleConstants.setFontSize(nextStyleSet, 10);
        StyleConstants.setFontFamily(nextStyleSet, "Courier");
        StyleConstants.setBold(nextStyleSet, true);
        StyleConstants.setForeground(nextStyleSet, Color.WHITE);
        StyleConstants.setAlignment(nextStyleSet, StyleConstants.ALIGN_CENTER);

        //Set timer for block drops.
        timer = new Timer(Math.round(initInterval), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDown();
                drawBoard();
            }
        });

        //Initialize board for the game.
        board = new Block[Board.HEIGHT][Board.WIDTH];
        playerKeyListener = new PlayerKeyListener();
        addKeyListener(playerKeyListener);
        playerMouseListener = new PlayerMouseListener();
        addMouseListener(playerMouseListener);
        requestFocus();
        setFocusable(true);

        // parent Style
        parentStyle = pane.addStyle("parentStyle", null);
        StyleConstants.setFontSize(parentStyle, 18);
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





    @Override
    protected ParentBlock getRandomItemBlock() {
        Random random = new Random(System.currentTimeMillis());
        int block = random.nextInt(14);
        System.out.println(block);
        switch(block) {
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
//            score.setText("GAMEOVER");
        }
    }

    public class PlayerKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    moveDown();
                    drawBoard();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveRight();
                    drawBoard();
                    break;
                case KeyEvent.VK_LEFT:
                    moveLeft();
                    drawBoard();
                    break;
                case KeyEvent.VK_UP:
                    moveRotate();
                    drawBoard();
                    break;
                case KeyEvent.VK_SPACE:
                    moveFall();
                    drawBoard();
                    break;
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
