package seoultech.se.tetris.component;

import seoultech.se.tetris.blocks.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Board extends JFrame {
    private static final long serialVersionUID = 2434035659171694595L;

    public static final int HEIGHT = 20;
    public static final int WIDTH = 10;
    public static final String BORDER_CHAR = "□";
    public static final String BLOCK_CHAR = "■";
    public static final String NON_BLOCK_CHAR = "    ";
    private static final int initInterval = 1000;

    private JTextPane pane;
    private JPanel rightPanel;
    private JLabel score;
    private JTextPane nextPanel;

    private KeyListener playerKeyListener;
    private MouseListener playerMouseListener;
    private SimpleAttributeSet styleSet;
    private SimpleAttributeSet nextStyleSet;
    private Timer timer;
    private ParentBlock focus;
    private ParentBlock next;

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

        // right items
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
//        rightPanel.setSize(400, 100);
        rightPanel.setBackground(Color.WHITE);
        score = new JLabel();
        CompoundBorder scoreBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        score.setBorder(scoreBorder);
        score.setText("init");
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
//        System.out.printf("%f %f %f %f %f \n", StyleConstants.getFirstLineIndent(styleSet), StyleConstants.getLeftIndent(styleSet), StyleConstants.getRightIndent(styleSet), StyleConstants.getLineSpacing(styleSet), StyleConstants.getTabSet(styleSet));
//        StyleConstants.setFontFamily(styleSet, "Courier");
//        StyleConstants.setBold(styleSet, true);
        StyleConstants.setForeground(styleSet, Color.WHITE);
//        StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);

        nextStyleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(nextStyleSet, 10);
        StyleConstants.setFontFamily(nextStyleSet, "Courier");
        StyleConstants.setBold(nextStyleSet, true);
        StyleConstants.setForeground(nextStyleSet, Color.WHITE);
        StyleConstants.setAlignment(nextStyleSet, StyleConstants.ALIGN_CENTER);

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
        playerMouseListener = new PlayerMouseListener();
        addMouseListener(playerMouseListener);
        requestFocus();
        setFocusable(true);


        //Create the first block and draw.
        focus = getRandomBlock();
        next = getRandomBlock();
        placeNextBlock();
        placeBlock();
        drawBoard();
        timer.start();
    }

    public void focusFrame() {
        requestFocus();
        setFocusable(true);
    }

    private ParentBlock getRandomBlock() {

        Random random = new Random(System.currentTimeMillis());
        int block = random.nextInt(7);
        switch(block) {
            case 0:
                return new IBlock();
            case 1:
                return new JBlock();
            case 2:
                return new LBlock();
            case 3:
                return new ZBlock();
            case 4:
                return new SBlock();
            case 5:
                return new TBlock();
            case 6:
                return new OBlock();
        }
        return new LBlock();
    }

    private void printBoard() {
        System.out.print("    ");
        for(int i=0;i<WIDTH;i++){
            System.out.printf("%2d", i);
        }
        System.out.println();

        for(int i=0;i<HEIGHT;i++){
            System.out.printf("%d  ", i);
            for(int j=0;j<WIDTH;j++){
                System.out.print(board[i][j] != null ? " 0" : "  ");
            }
            System.out.println();
        }
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
//                board[y+j][x+i] = focus.getShape(i, j);

                if (board[y+j][x+i] == null && focus.getShape(i, j) != null) {
                    board[y+j][x+i] = focus.getShape(i, j);
                }
            }
        }
    }

    private void eraseCurr() {
        for(int i=x; i<x+focus.width(); i++) {
            for(int j=y; j<y+focus.height(); j++) {
//                board[j][i] = null;
                if (focus.getShape(i-x, j-y) != null) {
                    board[j][i] = null;
                }
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
                    for(int k=i;k>=1;k--){
                        board[k][j] = board[k-1][j];
                    }
                    board[0][j] = null;
                }
            }
         }
    }

    // add overlap check
    private boolean isOverlap() {
        for(int i=x; i<x+focus.width(); i++) {
            for(int j=y; j<y+focus.height(); j++) {
                if (
                        (board[j][i] != null) &&
                        (focus.getShape(i-x, j-y) != null))
                {
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
        focus = next;
        next = getRandomBlock();
        placeNextBlock();
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

    protected void moveFall() {
        eraseCurr();
        for (int i=y;i<HEIGHT;i++){
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
                    sb.append(BLOCK_CHAR);
                } else {
                    sb.append(NON_BLOCK_CHAR);
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


    // draw next block
    public void placeNextBlock() {
        StringBuffer sb = new StringBuffer();
        SimpleAttributeSet styles = new SimpleAttributeSet();
        StyleConstants.setForeground(styles, next.getColor());

        int nW = next.width(); int nH = next.height();
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                if (i < nH && j < nW && next.getShape(j, i) != null) {
                    sb.append(BLOCK_CHAR);
                }else {
                    sb.append(NON_BLOCK_CHAR);
                }
            }
            sb.append("\n");
        }
        nextPanel.setText(sb.toString());
        StyledDocument doc = nextPanel.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styles, true);
        nextPanel.setStyledDocument(doc);
    }

    public void reset() {
        this.board = new Block[20][10];
    }

    public class PlayerKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        // @TODO
        // check
        // 1. 이동 시 block과 겹칠 때
        // 2. 이동 시 벽을 넘을 때
        // 3. 회전 시 block과 겹칠 때
        // 4. 회전 시 벽을 넘을 때
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
