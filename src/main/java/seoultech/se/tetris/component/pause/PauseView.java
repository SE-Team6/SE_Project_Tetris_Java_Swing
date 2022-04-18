package seoultech.se.tetris.component.pause;

import seoultech.se.tetris.component.board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PauseView extends JDialog {
    private static String RESUME = "RESUME";
    private static String EXIT = "EXIT";
    private static int score = 0;

    private int idx = 0;
    private final int IDX_LENGTH = 2;
    private Board parent;

    JButton resumeBtn;
    JButton exitBtn;

    public PauseView(int score, Board parent) {
        this.parent = parent;

        this.setUndecorated(true);
        this.score = score;
        this.setLayout(new GridLayout(3, 1, 0, 0));
//        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel scorePane = new JLabel();
        scorePane.setText(String.valueOf(this.score));
        resumeBtn = new JButton();
        resumeBtn.setText(RESUME);
        exitBtn = new JButton();
        exitBtn.setText(EXIT);

        this.add(scorePane);
        this.add(resumeBtn);
        this.add(exitBtn);

        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        if (idx == 0) {
                            disposeComponent();
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        disposeComponent();
                        break;
                    case KeyEvent.VK_DOWN:
                        if (idx < IDX_LENGTH) {
                            idx += 1;
                            showSelectedButton();

                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (idx > 0) {
                            idx -= 1;
                            showSelectedButton();
                        }
                        break;
                    default:
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.setFocusable(true);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void disposeComponent() {
        this.dispose();
        this.parent.startTimer();
        this.parent.setIsPause();
    }

    private void showSelectedButton() {
        if (idx == 0) {
            resumeBtn.setSelected(true);
            exitBtn.setSelected(false);
        } else {
            resumeBtn.setSelected(false);
            exitBtn.setSelected(true);
        }
    }
}
