package seoultech.se.tetris.component.board.match.timer;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.component.board.match.MatchBoardParent;
import seoultech.se.tetris.component.board.match.normal.MatchBoardChild;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class MatchTimerBoardParent extends MatchBoardParent {
    public Timer gameTimer;
    public long startTime;
    public JLabel timeView;
    public Timer timeViewController;
    public long currentTime = 0;

    public MatchTimerBoardParent(int time) {
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

        timeView = new JLabel(String.valueOf(time));
        timeView.setHorizontalAlignment(JLabel.CENTER);

        this.addMouseListener(new PlayerMouseListener());
        left.addMouseListener(new PlayerMouseListener());
        right.addMouseListener(new PlayerMouseListener());
        this.setLayout(new GridBagLayout());

        JPanel out = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        out.setLayout(new GridLayout(1, 2));
        out.add(left);
        out.add(right);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        this.add(out, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;       //third row
        this.add(timeView, c);

//        this.add(left, c);
//        this.add(right, c);
        this.addKeyListener(new PlayerLeftKeyListener());
        this.setFocusable(true);

        setSize(1000, 500);
        setVisible(true);
        setLocationRelativeTo(null);


        gameTimer = new Timer(time, e -> {
           matchGameOver();
        });
        currentTime = time;
        startTime = System.currentTimeMillis();
        timeViewController = new Timer(99, e -> {
            currentTime -= 100;
            long hour = currentTime / 1000 / 60;
            long minute = currentTime / 1000 % 60;
            timeView.setText(Long.toString(hour) + ":"+ Long.toString(minute));
        });
        timeViewController.start();
        gameTimer.start();
    }

    @Override
    public void matchGameOver() {
        gameTimer.stop();
        super.matchGameOver();
    }
}
