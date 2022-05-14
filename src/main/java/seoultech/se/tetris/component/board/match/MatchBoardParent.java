package seoultech.se.tetris.component.board.match;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.component.Keyboard;
import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.component.pause.MatchPauseView;
import seoultech.se.tetris.main.GameOver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MatchBoardParent extends JFrame {
    protected MatchBoardChild left;
    protected MatchBoardChild right;
    protected boolean isPause = false;
    protected MatchPauseView pv;
    private final Set<Integer> pressedKeys = new HashSet<>();
    protected Score leftScore;
    protected Score rightScore;


    public MatchBoardParent() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        leftScore = new Score();
        rightScore = new Score();
        left = new MatchBoardChild(leftScore);
        right = new MatchBoardChild(rightScore);
        left.addPropertyChangeListener("gameDone", new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                matchGameOver();
            }
        });
        left.addPropertyChangeListener("attProp", new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                ArrayList<Block[]> stack = (ArrayList<Block[]>) evt.getNewValue();
                right.attackedLines(stack);
            }
        });
        right.addPropertyChangeListener("gameDone", new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                matchGameOver();
            }
        });
        right.addPropertyChangeListener("attProp", new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                ArrayList<Block[]> stack = (ArrayList<Block[]>) evt.getNewValue();
                left.attackedLines(stack);
            }
        });

        this.addMouseListener(new PlayerMouseListener());
        left.addMouseListener(new PlayerMouseListener());
        right.addMouseListener(new PlayerMouseListener());



//        this.setLayout(new GridLayout(1, 2));
        JPanel out = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        out.setLayout(new GridLayout(1, 2));
        out.add(left);
        out.add(right);
        this.add(out);
//        this.setLayout(new GridBagLayout());
//        this.add(left, c);
//        this.add(right, c);
        this.addKeyListener(new PlayerLeftKeyListener());
        this.setFocusable(true);

        setSize(700, 320);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    protected void pause() {
        System.out.println("pause");
        if (!isPause) {
            stopTimer();
            pv = new MatchPauseView(leftScore.getScore(),this);
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
                    if (keyCode == KeyEvent.VK_S) {
                        left.moveDown();
                        left.drawBoard();
                    } else if (keyCode == KeyEvent.VK_D) {
                        left.moveRight();
                        left.drawBoard();
                    } else if (keyCode == KeyEvent.VK_A) {
                        left.moveLeft();
                        left.drawBoard();
                    } else if (keyCode == KeyEvent.VK_W) {
                        left.moveRotate();
                        left.drawBoard();
                    } else if (keyCode == KeyEvent.VK_F) {
                        left.moveFall();
                        left.drawBoard();
                    } else if (keyCode == Keyboard.ESC) {
                        pause();
                    } else if (keyCode == Keyboard.DOWN) {
                        right.moveDown();
                        right.drawBoard();
                    } else if (keyCode == Keyboard.RIGHT) {
                        right.moveRight();
                        right.drawBoard();
                    } else if (keyCode == Keyboard.LEFT) {
                        right.moveLeft();
                        right.drawBoard();
                    } else if (keyCode == Keyboard.UP) {
                        right.moveRotate();
                        right.drawBoard();
                    } else if (keyCode == Keyboard.SPACE) {
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

    public void matchGameOver() {
        stopTimer();
        this.dispose();
        new GameOver(getX(), getY(), leftScore.getScore());
    }

//    public class PlayerLeftKeyListener extends Keyboard {
//        @Override
//        public void keyPressed(KeyEvent e) {
//            int keyCode = e.getKeyCode();
//
//            if (keyCode == KeyEvent.VK_S) {
//                left.moveDown();
//                left.drawBoard();
//            } else if (keyCode == KeyEvent.VK_D) {
//                left.moveRight();
//                left.drawBoard();
//            } else if (keyCode == KeyEvent.VK_A) {
//                left.moveLeft();
//                left.drawBoard();
//            } else if (keyCode == KeyEvent.VK_W) {
//                left.moveRotate();
//                left.drawBoard();
//            } else if (keyCode == KeyEvent.VK_F) {
//                left.moveFall();
//                left.drawBoard();
//            }
//        }
//    }
//
//    public class PlayerRightKeyListener extends Keyboard {
//        @Override
//        public void keyPressed(KeyEvent e) {
//            int keyCode = e.getKeyCode();
//
//            if (keyCode == Keyboard.DOWN) {
//                right.moveDown();
//                right.drawBoard();
//            } else if (keyCode == Keyboard.RIGHT) {
//                right.moveRight();
//                right.drawBoard();
//            } else if (keyCode == Keyboard.LEFT) {
//                right.moveLeft();
//                right.drawBoard();
//            } else if (keyCode == Keyboard.UP) {
//                right.moveRotate();
//                right.drawBoard();
//            } else if (keyCode == Keyboard.SPACE) {
//                right.moveFall();
//                right.drawBoard();
//            }else if (keyCode == Keyboard.ESC) {
//                pause();
//            }
//        }
//    }

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
