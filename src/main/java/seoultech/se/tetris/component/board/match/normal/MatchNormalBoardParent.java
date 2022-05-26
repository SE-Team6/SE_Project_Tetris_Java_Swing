package seoultech.se.tetris.component.board.match.normal;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.component.board.match.MatchBoardParent;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import static seoultech.se.tetris.component.JSONLoader.loaderResolution;

public class MatchNormalBoardParent extends MatchBoardParent {
    public MatchNormalBoardParent(int diff) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        leftScore = new Score();
        rightScore = new Score();
        left = new MatchBoardChild(leftScore, diff);
        right = new MatchBoardChild(rightScore, diff);
        left.addPropertyChangeListener("gameDone", new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                matchGameOver(2);
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
                matchGameOver(1);
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




        JPanel out = new JPanel();
//        c.fill = GridBagConstraints.BOTH;
        HashMap<String, Integer> map = loaderResolution();
        out.setLayout(new GridLayout(1, 2));

        out.add(left);
        out.add(right);
        left.setSize(map.get("width"), map.get("height"));
        right.setSize(map.get("width"), map.get("height"));
        out.setSize(map.get("width")*2, map.get("height"));
        this.setSize(map.get("width")*2, map.get("height"));
        this.add(out);
        this.addKeyListener(new PlayerLeftKeyListener());
        this.setFocusable(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
