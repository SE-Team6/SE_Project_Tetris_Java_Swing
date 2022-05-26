package seoultech.se.tetris.menu;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static seoultech.se.tetris.menu.SetDefault.*;
import static seoultech.se.tetris.menu.SetKey1P.positionPoint;

public class TimerMenu extends JFrame {
    private SetDefault bs;
    private BackMenuBtn bm = new BackMenuBtn();
    private ImageIcon threeTimeModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/timer03_B.jpg"));
    private ImageIcon fiveTimeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/timer05_B.jpg"));
    private ImageIcon threeTimBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/timer03_E.jpg"));
    private ImageIcon fiveTimeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/timer05_E.jpg"));
    private ImageIcon[] BasicImage = {threeTimeModeBtnImage, fiveTimeBtnImage};
    private ImageIcon[] EnterImage = {threeTimBtnEnterImage, fiveTimeBtnEnterImage};
    private JButton[] menuButton = new JButton[2];

    public TimerMenu(){}

    public TimerMenu(int x, int y) {
        bs = new SetDefault(x, y);
        positionPoint=0;
        bs.setVisible(true);
        setButton();
        bs.addKeyListener(new menuKeyListener());
        bs.add(bm.backMenuBtn);
        backToMenu();
    }
    public class menuKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyValue = e.getKeyCode();
            if (keyValue == key.DOWN) {
                positionPoint += 1;
                if (positionPoint == 2) positionPoint = 0;
                setBtnImage();
            } else if (keyValue == key.UP) {
                positionPoint -= 1;
                if (positionPoint == -1) positionPoint = 1;
                setBtnImage();
            }
            else if(keyValue==KeyEvent.VK_ENTER){
                if (positionPoint==0){
                    bs.setVisible(false);
                    //3분 타이머 게임 시작
                }
                else if(positionPoint==1){
                    bs.setVisible(false);
                    //5분 타이머 게임시작
                }
            }
            else if(keyValue==KeyEvent.VK_BACK_SPACE){
                bs.setVisible(false);
                new BattleModeMenu(bs.getX(),bs.getY());
            }
        }
    }
    public void setBtnImage(){
        for (int i=0;i<2;i++){
            if (positionPoint==i) menuButton[i].setIcon(EnterImage[i]);
            else menuButton[i].setIcon(BasicImage[i]);
        }
    }
    public void setButton() { // 일반,아이템
        int addY = 0;
        for (int i = 0; i < 2; i++) {
            menuButton[i] = new JButton(BasicImage[i]);
            menuButton[i].setBounds(buttonX, buttonY + addY, buttonWidth, buttonHeight);
            menuButton[i].setBorderPainted(false);
            menuButton[i].setContentAreaFilled(false);
            menuButton[i].setFocusPainted(false);
            bs.add(menuButton[i]);
            addY += 70;
        }
        menuButton[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
            }
        });
        menuButton[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
            }
        });

        setBtnImage();
    }
    public void backToMenu(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new BattleModeMenu(bs.getX(),bs.getY());
            }
        });
    }
}
