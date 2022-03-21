package seoultech.se.tetris.componentTmp;

import seoultech.se.tetris.blockTmp.Block;
import seoultech.se.tetris.blockTmp.IBlock;
import seoultech.se.tetris.blockTmp.JBlock;
import seoultech.se.tetris.blockTmp.ParentBlock;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Board extends JFrame {
    private static final long serialVersionUID = 2434035659171694595L;

    public static final int HEIGHT = 20;
    public static final int WIDTH = 10;
    public static final String BORDER_CHAR = "X";
    private static final int initInterval = 1000;

    private JTextPane pane;
    private JLabel score;
    private JPanel rightPanel;
    private KeyListener playerKeyListener;
    private SimpleAttributeSet styleSet;
    private Timer timer;
    private ParentBlock focus;

    int x = 3; //Default Position.
    int y = 0;

    // add new board
    private Block[][] board;

    public Board() {
        super("SE Team 6");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Board display setting.
        pane = new JTextPane();
        pane.setEditable(false);
        pane.setBackground(Color.BLACK);
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        pane.setBorder(border);

        rightPanel = new JPanel();
//        rightPanel.setSize(400, 100);
        rightPanel.setBackground(Color.WHITE);
        score = new JLabel();
        CompoundBorder scoreBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        score.setBorder(scoreBorder);
        score.setText("init");

        rightPanel.add(score);
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

        //Set timer for block drops.
        timer = new Timer(initInterval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDown();
                drawBoard();
            }
        });

        //Initialize board for the game.
        board = new Block[HEIGHT][WIDTH];
        playerKeyListener = new PlayerKeyListener();
        addKeyListener(playerKeyListener);
        setFocusable(true);
        requestFocus();

        //Create the first block and draw.
        focus = getRandomBlock();
        placeBlock();
        drawBoard();
        timer.start();
    }

    private void initStyle() {

    }


    private ParentBlock getRandomBlock() {

        Random random = new Random(System.currentTimeMillis());
        int block = random.nextInt(2);
        switch(block) {
            case 0:
                return new IBlock();
            case 1:
                return new JBlock();
        }
        return new IBlock();
    }

    // 만약 블럭이 내려가면 똑같이 유지가 될까 아마 안될 것 같음.
    // draw에서 color를 주면 안되나?
    // 아마 여기서 color를 주는 것 같아요
    private void placeBlock() {
//        StyledDocument doc = pane.getStyledDocument();
//        SimpleAttributeSet styles = new SimpleAttributeSet();
//        StyleConstants.setForeground(styles, focus.getColor());
        for(int j=0; j<focus.height(); j++) {
//            int rows = y+j == 0 ? 0 : y+j-1;
//            int offset = rows * (WIDTH+3) + x + 1;
//            doc.setCharacterAttributes(offset, focus.width(), styles, true);
            for(int i=0; i<focus.width(); i++) {
                board[y+j][x+i] = focus.getShape(i, j);
            }
        }
    }

    private void eraseCurr() {
        for(int i=x; i<x+focus.width(); i++) {
            for(int j=y; j<y+focus.height(); j++) {
                board[j][i] = null;
            }
        }
    }

    // TODO
    // erase line
    // at focus down bottom;
    // i, j = j, i
    private void eraseLines() {
        for(int i=HEIGHT-1;i>=0;i--){
            int tmp = 0;
            for(int j=0;j<WIDTH;j++){
                if (board[i][j] != null) {
                    tmp++;
                }
            }
            if (tmp == WIDTH) {
                for(int j=0;j<WIDTH;j++){
                    if (i != 0) {
                        board[i][j] = board[i-1][j];
                        board[i-1][j] = null;
                    }
                }
            }
        }
    }

    // add overlap check
    private boolean isOverlap() {
        for(int i=x; i<x+focus.width(); i++) {
            for(int j=y; j<y+focus.height(); j++) {
                if (board[j][i] != null) {
                    return true;
                }
            }
        }
        return false;
    }

    // add bottom check
    private boolean isBottomTouch() {
        if(y < HEIGHT - focus.height()) {
            return false;
        }
        return true;
    }

    // generate new block
    private void generateNewBlock() {
        placeBlock();
        eraseLines();
        focus = getRandomBlock();
        x = 3;
        y = 0;

        // GAME OVER
        if (isOverlap()) {
            score.setText("GAMEOVER");
        }
    }

    protected void moveDown() {
        eraseCurr();
        if(!isBottomTouch()) {
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

    protected void moveRight() {
        eraseCurr();
        if(x < WIDTH - focus.width()) x++;
        placeBlock();
    }

    protected void moveLeft() {
        eraseCurr();
        if(x > 0) {
            x--;
        }
        placeBlock();
    }

    public void drawBoard() {
        StringBuffer sb = new StringBuffer();
        for(int t=0; t<WIDTH+2; t++) sb.append(BORDER_CHAR);
        sb.append("\n");
        for(int i=0; i < board.length; i++) {
            sb.append(BORDER_CHAR);
            for(int j=0; j < board[i].length; j++) {
                if(board[i][j] != null) {
                    sb.append("O");
                } else {
                    sb.append(" ");
                }
            }
            sb.append(BORDER_CHAR);
            sb.append("\n");
        }
        for(int t=0; t<WIDTH+2; t++) sb.append(BORDER_CHAR);
        pane.setText(sb.toString());
        StyledDocument doc = pane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        pane.setStyledDocument(doc);
    }

    public void drawBoard1() {
        StyledDocument doc = pane.getStyledDocument();
        Style root = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        Style blue = doc.addStyle("blue", root);
        Style s = doc.addStyle("blue", blue);
        StyleConstants.setForeground(s, Color.blue);
        Style red = doc.addStyle("red", root);
        doc.addStyle("red", red);
        StyleConstants.setForeground(s, Color.red);

        for(int t=0; t<WIDTH+2; t++) {
            System.out.println(t);
            try {
                doc.insertString(doc.getLength(), BORDER_CHAR, doc.getStyle("red"));
            } catch (Exception e) {
                System.out.println("insertString" + e);
            }
        }
        pane.setStyledDocument(doc);
    }

    public void reset() {
        this.board = new Block[20][10];
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
                    eraseCurr();
                    focus.rotate();
                    drawBoard();
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
