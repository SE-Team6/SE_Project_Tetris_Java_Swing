package seoultech.se.tetris.component;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class Score extends JLabel {
    private int score;
    private CompoundBorder border;

    public Score() {
        this.score = 0;
        border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        this.setBorder(border);
        this.setSize(300, 200);
        updateScore();
    }

    public int getScore() {
        return score;
    }

    public void addScore(int combo) {
        this.score += combo;
        updateScore();
    }

    private void updateScore() {
        this.setText(Integer.toString(this.score));
    }
}
