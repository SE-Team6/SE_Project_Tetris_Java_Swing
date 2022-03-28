package seoultech.se.tetris.component.board;

import seoultech.se.tetris.blocks.*;
import seoultech.se.tetris.blocks.item.pendulum.PendulumBlock;
import seoultech.se.tetris.blocks.item.random.*;
import seoultech.se.tetris.component.Score;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ItemBoard extends JFrame implements Board {
    private int cnt;
    public static float initInterval = 1000;

    private JTextPane pane;
    private JPanel rightPanel;
    private Score score;
    private JTextPane nextPanel;

    private KeyListener playerKeyListener;
    private MouseListener playerMouseListener;
    private SimpleAttributeSet styleSet;
    private SimpleAttributeSet nextStyleSet;
    private Style parentStyle;
    Style defaultStyle;
    Style blockStyle;

    private Timer timer;
    private ParentBlock focus;
    private ParentBlock next;



    private float rateInterval = 0.95F;

    int x = 3; //Default Position.
    int y = 0;

    // add new board
    private Block[][] board;

    public ItemBoard() {
        super("SE Team 6 Item");
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
    public void focusFrame() {
        requestFocus();
        setFocusable(true);
    }

    @Override
    public ParentBlock getRandomBlock() {
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

    public ParentBlock getRandomItemBlock() {
        Random random = new Random(System.currentTimeMillis());
        int block = random.nextInt(14);
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
    public void printBoard() {
        System.out.print("    ");
        for(int i=0;i<Board.WIDTH;i++){
            System.out.printf("%2d", i);
        }
        System.out.println();

        for(int i=0;i<Board.HEIGHT;i++){
            System.out.printf("%d  ", i);
            for(int j=0;j<Board.WIDTH;j++){
                System.out.print(board[i][j] != null ? " 0" : "  ");
            }
            System.out.println();
        }
    }

    // 만약 블럭이 내려가면 똑같이 유지가 될까 아마 안될 것 같음.
    // draw에서 color를 주면 안되나?
    // 아마 여기서 color를 주는 것 같아요
    @Override
    public void placeBlock() {
        for(int j=0; j<focus.height(); j++) {
            for(int i=0; i<focus.width(); i++) {
                if (board[y+j][x+i] == null && focus.getShape(i, j) != null) {
                    board[y+j][x+i] = focus.getShape(i, j);
                }
            }
        }
    }

    @Override
    public void eraseCurr() {
        for(int i=x; i<x+focus.width(); i++) {
            for(int j=y; j<y+focus.height(); j++) {
                if (focus.getShape(i-x, j-y) != null) {
                    board[j][i] = null;
                }
            }
        }
    }

    @Override
    public void eraseLines() {
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
        if(y < Board.HEIGHT - focus.height()) {
            return false;
        }
        return true;
    }

    // generate new block
    private void generateNewBlock() {
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
        for (int i=y;i<Board.HEIGHT;i++){
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
        if(x < Board.WIDTH - focus.width()) x++;
        if(isOverlap()) {
            x--;
        }
        placeBlock();
    }

    protected void moveLeft() {
        eraseCurr();
        if(x > 0) {
            x--;
        }
        if(isOverlap()) {
            x++;
        }
        placeBlock();
    }

    protected void moveRotate() {
        eraseCurr();
        focus.rotate();
        if (x<0||x>=Board.WIDTH-focus.width()+1||y+focus.height()-1>=Board.HEIGHT||isOverlap()) {
            focus.rotateBack();
        }
        placeBlock();
    }

    public void drawBoard() {
        StyledDocument doc = pane.getStyledDocument();
        pane.setText("");
        try {
            for(int t=0; t<Board.WIDTH+2; t++) {
                doc.insertString(doc.getLength(), config.BORDER_CHAR, defaultStyle);
            }
            doc.insertString(doc.getLength(), "\n", defaultStyle);

            for(int i=0; i < board.length; i++) {
                doc.insertString(doc.getLength(), config.BORDER_CHAR, defaultStyle);

                for(int j=0; j < board[i].length; j++) {
                    if(board[i][j] != null) {
                        StyleConstants.setForeground(blockStyle, board[i][j].getColor() == null ? Color.GRAY : board[i][j].getColor());
                        doc.insertString(doc.getLength(), board[i][j].getCharacter(), blockStyle);
                    } else {
                        doc.insertString(doc.getLength(), config.NON_BLOCK_CHAR, defaultStyle);
                    }
                }
                doc.insertString(doc.getLength(), config.BORDER_CHAR, defaultStyle);
                doc.insertString(doc.getLength(), "\n", defaultStyle);

            }
            for(int t=0; t<Board.WIDTH+2; t++) {
                doc.insertString(doc.getLength(), config.BORDER_CHAR, defaultStyle);
            }
        } catch (Exception ignored){}
    }

    // draw next block
    public void drawNextBlock() {
        StyledDocument doc = nextPanel.getStyledDocument();
        nextPanel.setText("");

        int nW = next.width(); int nH = next.height();
        try {
            for (int i=0;i<4;i++){
                for (int j=0;j<4;j++){
                    if (i < nH && j < nW && next.getShape(j, i) != null) {
                        StyleConstants.setForeground(blockStyle, next.getShape(j, i).getColor());
                        doc.insertString(doc.getLength(), next.getShape(j, i).getCharacter(), blockStyle);
                    }else {
                        doc.insertString(doc.getLength(), config.NON_BLOCK_CHAR, defaultStyle);
                    }
                }
                doc.insertString(doc.getLength(), "\n", defaultStyle);
            }
        } catch (Exception ignored) {}
    }

    @Override
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
