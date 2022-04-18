package seoultech.se.tetris.component.pause.score;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ScoreBox extends JPanel {
    public ScoreBox(int score) {
        int labelX = 870;
        int textFiledX = 470;
        this.setLayout(new GridLayout(1, 2, 1, 1));

        JLabel left = new JLabel("score");
        JLabel right = new JLabel(String.valueOf(score));
        left.setFont(new Font("Bahnschrift",Font.BOLD,25));
        left.setBounds(labelX,120,100,40);
        left.setHorizontalAlignment(SwingConstants.CENTER);
        left.setForeground(Color.YELLOW);

        right.setFont(new Font("Bahnschrift",Font.BOLD,20));
        right.setBounds(textFiledX,120,100,40);
        right.setHorizontalAlignment(SwingConstants.CENTER);
        right.setOpaque(true);
        right.setBackground(Color.BLACK);
        right.setForeground(Color.RED);
        right.setBorder(new LineBorder(Color.RED,1,true));

        this.add(left);
        this.add(right);
    }
}
