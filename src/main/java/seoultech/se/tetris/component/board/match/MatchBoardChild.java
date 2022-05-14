package seoultech.se.tetris.component.board.match;

import seoultech.se.tetris.blocks.Block;
import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.component.board.Board;
import seoultech.se.tetris.config.ConfigBlock;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class MatchBoardChild extends MatchInnerBoard {
    public MatchBoardChild(Score score) {
        GridBagConstraints outLayout = new GridBagConstraints();
        GridBagConstraints layout = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        pane = new JTextPane();
        pane.setEditable(false);
        pane.setBackground(Color.BLACK);
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        pane.setBorder(border);

        // right items
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
//        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBackground(Color.GRAY);

        this.score = score;

        rightPanel.add(this.score, layout);

        nextPanel = new JTextPane();
        nextPanel.setEditable(false);
        nextPanel.setBackground(Color.BLACK);
        CompoundBorder nextBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        nextPanel.setBorder(nextBorder);

        rightPanel.add(nextPanel, layout);

        stackPanel = new JTextPane();
        stackPanel.setEditable(false);
        stackPanel.setBackground(Color.BLACK);
        stackPanel.setBorder(nextBorder);

        rightPanel.add(stackPanel, layout);

        CompoundBorder rightBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        rightPanel.setBorder(rightBorder);

        outLayout.fill = GridBagConstraints.BOTH;
        outLayout.weightx = 0.3;
        this.add(pane, outLayout);
        outLayout.weightx = 0.1;
        this.add(rightPanel, outLayout);

        //Set timer for block drops.
        timer = new Timer(Math.round(initInterval), e -> {
            moveDown();
            drawBoard();
        });

        //Initialize board for the game.
        board = new Block[Board.HEIGHT][Board.WIDTH];
        prevBoard = new Block[Board.HEIGHT][Board.WIDTH];
        stackBoard = new Block[STACK_MAX][Board.WIDTH];

        // line height
        SimpleAttributeSet tmp = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(tmp, -0.5F);

        StyleConstants.setAlignment(tmp, StyleConstants.ALIGN_CENTER);

        nextPanel.setParagraphAttributes(tmp, false);
        pane.setParagraphAttributes(tmp, false);
        stackPanel.setParagraphAttributes(tmp, false);


        // parent Style
        parentStyle = pane.addStyle("parentStyle", null);

        StyleConstants.setFontSize(parentStyle, ConfigBlock.fontSize);
        StyleConstants.setFontFamily(parentStyle, "Courier");
        StyleConstants.setBold(parentStyle, true);
        StyleConstants.setForeground(parentStyle, Color.WHITE);
        StyleConstants.setAlignment(parentStyle, StyleConstants.ALIGN_RIGHT);

        defaultStyle = pane.addStyle("defaultStyle", parentStyle);
        blockStyle = pane.addStyle("blockStyle", parentStyle);

        StyleConstants.setFontSize(parentStyle, ConfigBlock.fontSize / 2);
        stackStyle = nextPanel.addStyle("stackStyle", parentStyle);

        //Create the first block and draw.
        focus = getRandomBlock();
        y = - focus.getTop();
        placeBlock();
        drawBoard();
        timer.start();
        next = getRandomBlock();
        drawNextBlock();
        drawStackBoard();
    }
}
