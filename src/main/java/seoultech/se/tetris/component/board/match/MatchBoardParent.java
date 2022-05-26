package seoultech.se.tetris.component.board.match;

import seoultech.se.tetris.component.Keyboard;
import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.component.pause.MatchPauseView;
import seoultech.se.tetris.main.BattleModeGameOver;
import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static seoultech.se.tetris.component.JSONLoader.loaderResolution;

public class MatchBoardParent extends JFrame {
    protected MatchInnerBoard left;
    protected MatchInnerBoard right;
    protected boolean isPause = false;
    protected MatchPauseView pv;
    private final Set<Integer> pressedKeys = new HashSet<>();
    protected Score leftScore;
    protected Score rightScore;


    public MatchBoardParent() {
        URL urlIcon = Tetris.class.getResource("/image/icon/icon.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image imgIcon = kit.createImage(urlIcon);
        setIconImage(imgIcon);
        // focus out
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                pressedKeys.clear();
            }
        });
        HashMap<String, Integer> map = loaderResolution();
        this.setSize(map.get("width")*2, map.get("height"));
    }

    protected void pause() {
        System.out.println("pause");
        if (!isPause) {
            stopTimer();
            pv = new MatchPauseView(leftScore.getScore(), rightScore.getScore(), this);
            pv.setScore(leftScore.getScore());
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
        this.left.startTimer();
        this.right.startTimer();
    }
    public void stopTimer() {
        this.left.stopTimer();
        this.right.stopTimer();
    }

    public void focusFrame() {
        requestFocus();
        this.setFocusable(true);
    }


    public class PlayerLeftKeyListener extends Keyboard {
        @Override
        public void keyPressed(KeyEvent e) {
            pressedKeys.add(e.getKeyCode());
            if (!pressedKeys.isEmpty()) {
                for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                    int keyCode = it.next();
                    // 1p
                    if (keyCode == Keyboard.DOWN) {
                        left.moveDown();
                        left.drawBoard();
                    } else if (keyCode == Keyboard.RIGHT) {
                        left.moveRight();
                        left.drawBoard();
                    } else if (keyCode == Keyboard.LEFT) {
                        left.moveLeft();
                        left.drawBoard();
                    } else if (keyCode == Keyboard.UP) {
                        left.moveRotate();
                        left.drawBoard();
                    } else if (keyCode == Keyboard.SPACE) {
                        left.moveFall();
                        left.drawBoard();
                    } else if (keyCode == Keyboard.ESC) {
                        pause();
                    }
                    // 2p
                    else if (keyCode == Keyboard.DOWN2) {
                        right.moveDown();
                        right.drawBoard();
                    } else if (keyCode == Keyboard.RIGHT2) {
                        right.moveRight();
                        right.drawBoard();
                    } else if (keyCode == Keyboard.LEFT2) {
                        right.moveLeft();
                        right.drawBoard();
                    } else if (keyCode == Keyboard.UP2) {
                        right.moveRotate();
                        right.drawBoard();
                    } else if (keyCode == Keyboard.SPACE2) {
                        right.moveFall();
                        right.drawBoard();
                    }
                }
            }
        }
        @Override
        public synchronized void keyReleased(KeyEvent e) {
            pressedKeys.remove(e.getKeyCode());
        }
    }

    public void matchGameOver(int winType) {
        stopTimer();
        this.dispose();
        int leftScoreValue = leftScore.getScore();
        int rightScoreValue = rightScore.getScore();

        if (winType == 1) {
//            leftScoreValue += 100000;
            rightScoreValue = 0;
        } else if (winType == 2) {
//            rightScoreValue += 100000;
            leftScoreValue = 0;
        }
        new BattleModeGameOver(getX(), getY(), leftScoreValue, rightScoreValue);
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
