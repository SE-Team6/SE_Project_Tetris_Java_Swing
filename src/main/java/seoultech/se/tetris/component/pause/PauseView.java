package seoultech.se.tetris.component.pause;

import seoultech.se.tetris.component.Keyboard;
import seoultech.se.tetris.component.board.Board;
import seoultech.se.tetris.component.pause.score.ScoreBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
        this.setBackground(Color.BLACK);
        PauseView.score = score;
        this.setLayout(new GridLayout(3, 1, 1, 1));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel scorePane = new ScoreBox(score);


        resumeBtn = new JButton();
        resumeBtn.setText(RESUME);
        resumeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disposeComponent();
            }
        });
        exitBtn = new JButton();
        exitBtn.setText(EXIT);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disposeExit();
                parent.gameOver(parent.getX(), parent.getY());
            }
        });

        this.add(scorePane);
        this.add(resumeBtn);
        this.add(exitBtn);

        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyValue = e.getKeyCode();
                if (keyValue == KeyEvent.VK_ENTER) {
                    if (idx == 0) {
                        disposeComponent();
                    } else {
                        disposeExit();
                        parent.gameOver(parent.getX(), parent.getY());
                    }
                } else if (keyValue == KeyEvent.VK_ESCAPE) {
                    disposeComponent();
                } else if (keyValue == Keyboard.DOWN) {
                    if (idx < IDX_LENGTH - 1) {
                        idx += 1;
                        showSelectedButton();

                    }
                } else if (keyValue == Keyboard.UP) {
                    if (idx > 0) {
                        idx -= 1;
                        showSelectedButton();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.setFocusable(true);
        showSelectedButton();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void disposeComponent() {
        this.dispose();

        this.parent.startTimer();
        this.parent.setIsPause();
    }

    public void disposeExit() {
        this.dispose();

        this.parent.setIsPause();
    }

    private void showSelectedButton() {
        if (idx == 0) {
            resumeBtn.setSelected(true);
            resumeBtn.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            exitBtn.setSelected(false);
            exitBtn.setBorder(BorderFactory.createEmptyBorder());
        } else {
            resumeBtn.setSelected(false);
            resumeBtn.setBorder(BorderFactory.createEmptyBorder());
            exitBtn.setSelected(true);
            exitBtn.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        }
    }
}
