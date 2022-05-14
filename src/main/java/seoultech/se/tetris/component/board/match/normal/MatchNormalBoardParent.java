package seoultech.se.tetris.component.board.match.normal;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.component.board.match.MatchBoardParent;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class MatchNormalBoardParent extends MatchBoardParent {
    public MatchNormalBoardParent() {
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

        setSize(1000, 500);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
