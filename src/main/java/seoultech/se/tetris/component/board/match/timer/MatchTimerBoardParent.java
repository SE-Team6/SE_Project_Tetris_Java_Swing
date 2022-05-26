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
import java.util.HashMap;

import static seoultech.se.tetris.component.JSONLoader.loaderResolution;

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
        HashMap<String, Integer> map = loaderResolution();
        out.setLayout(new GridLayout(1, 2));


        left.setSize(map.get("width"), map.get("height"));
        right.setSize(map.get("width"), map.get("height"));
        out.setSize(map.get("width")*2, map.get("height"));
        out.add(left);
        out.add(right);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0.9;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        this.add(out, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.1;
        c.gridy = 0;       //third row
        this.add(timeView, c);

//        this.add(left, c);
//        this.add(right, c);
        this.addKeyListener(new PlayerLeftKeyListener());
        this.setFocusable(true);

        setVisible(true);
        setLocationRelativeTo(null);


        gameTimer = new Timer(time, e -> {
           matchGameOver();
        });
        currentTime = time;
        timeView.setText(Long.toString(currentTime / 1000 / 60) + ":"+ Long.toString(currentTime / 1000 % 60));
        startTime = System.currentTimeMillis();
        timeViewController = new Timer(999, e -> {
            currentTime -= 1000;
            long hour = currentTime / 1000 / 60;
            long minute = currentTime / 1000 % 60;
            if (currentTime < 0) {
                matchGameOver();
            }
            timeView.setText(Long.toString(hour) + ":"+ Long.toString(minute));
        });
        timeViewController.start();
//        gameTimer.start();
    }

    @Override
    public void matchGameOver() {
        gameTimer.stop();
        timeViewController.stop();
        super.matchGameOver();
    }

    @Override
    public void stopTimer() {
        super.stopTimer();
        this.timeViewController.stop();
        this.gameTimer.stop();
    }

    @Override
    public void startTimer() {
        super.startTimer();
        this.timeViewController.start();
        this.gameTimer.start();
    }
}
