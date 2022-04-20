package seoultech.se.tetris.component;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class Score extends JLabel {
    private final int[] combo = {0,100,300,500,800};
    public static int score;
    private CompoundBorder border;

    public Score() {
        score = 0;
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

    public static int getScore() {
        return score;
    }

    // 연속으로 제거하면 원래 점수의 50% 씩 추가됨.
    public void addLineClearScore(int num, int stage, int seq) {
        this.score += ((this.combo[num]*stage)*(1+(seq-1)*0.5));
        updateScore();
    }

    public void addUnitScore(int num) {
        this.score += num;
        updateScore();
    }

    private void updateScore() {
        this.setText(Integer.toString(this.score));
    }

    public void resetScore() {
        this.score = 0;
    }
}
