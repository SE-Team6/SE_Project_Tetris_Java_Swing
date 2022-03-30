package seoultech.se.tetris.Menu;

import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BackMenu extends JFrame{
    private ImageIcon backMenuImage = new ImageIcon(Tetris.class.getResource("../image/Button/static_btn/back_B.png"));
    private ImageIcon backMenuEImage = new ImageIcon(Tetris.class.getResource("../image/Button/static_btn/back_E.png"));
    public JButton backMenuBtn = new JButton(backMenuImage);
    public BackMenu(){
        backMenuBtn.setBounds(10,50,50,50);
        backMenuBtn.setBorderPainted(false);
        backMenuBtn.setContentAreaFilled(false);
        backMenuBtn.setFocusPainted(false);
        backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backMenuBtn.setIcon(backMenuEImage);
                backMenuBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                backMenuBtn.setIcon(backMenuImage);
            }
        });
    }
}
