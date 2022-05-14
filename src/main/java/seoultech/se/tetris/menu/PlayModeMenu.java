package seoultech.se.tetris.menu;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static seoultech.se.tetris.menu.SetDefault.*;
import static seoultech.se.tetris.menu.SetDefault.buttonHeight;
import static seoultech.se.tetris.menu.SetKey1P.positionPoint;

public class PlayModeMenu extends JFrame {

    public static int playModeNum; //0:1p 1:battle mode

    private SetDefault bs;
    private BackMenuBtn bm = new BackMenuBtn();
    private ImageIcon onePlayModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/1PMode_B.jpg"));
    private ImageIcon battleModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/2PMode_B.jpg"));
    private ImageIcon onePlayModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/1PMode_E.jpg"));
    private ImageIcon battleModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/2PMode_E.jpg"));
    private ImageIcon[] BasicImage = {onePlayModeBtnImage, battleModeBtnImage};
    private ImageIcon[] EnterImage = {onePlayModeBtnEnterImage, battleModeBtnEnterImage};
    private JButton[] menuButton = new JButton[2];
    public PlayModeMenu(){}

    public PlayModeMenu(int x, int y) {
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
                    playModeNum=0;
                    new OnePlayModeMenu(bs.getX(), bs.getY());
                }
                else if(positionPoint==1){
                    bs.setVisible(false);
                    playModeNum=1;
                    new BattleModeMenu(bs.getX(), bs.getY());
                }
            }
            else if(keyValue==KeyEvent.VK_BACK_SPACE){
                bs.setVisible(false);
                new GameDifficultyMenu(bs.getX(), bs.getY());
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
                playModeNum=0;
                new OnePlayModeMenu(bs.getX(), bs.getY());
            }
        });
        menuButton[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                playModeNum=1;
                new BattleModeMenu(bs.getX(), bs.getY());
            }
        });

        setBtnImage();
    }

    public void backToMenu(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new GameDifficultyMenu(bs.getX(), bs.getY());
            }
        });
    }
}
