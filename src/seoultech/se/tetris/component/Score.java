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
        this.setForeground(Color.BLACK);
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setFont(new Font("Courier", Font.PLAIN, 14));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
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
