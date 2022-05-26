package seoultech.se.tetris.menu;

import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import static seoultech.se.tetris.menu.SetDefault.*;

public class ScoreResetMenu extends JFrame {

    public static int resetModeNum; //0:노말 1:아이템

    private SetDefault bs = new SetDefault();
    private BackMenuBtn bm = new BackMenuBtn();
    private ImageIcon normalModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/NormalMode_B.jpeg"));
    private ImageIcon itemModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/ItemMode_B.jpeg"));
    private ImageIcon normalModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/NormalMode_E.jpeg"));
    private ImageIcon itemModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/ItemMode_E.jpeg"));
    private ImageIcon[] BasicImage = {normalModeBtnImage, itemModeBtnImage};
    private ImageIcon[] EnterImage = {normalModeBtnEnterImage, itemModeBtnEnterImage};
    private JButton[] menuButton = new JButton[2];

    private int positionPoint;

    public ScoreResetMenu(){

    }
    public ScoreResetMenu(int x , int y) {
        URL urlIcon = Tetris.class.getResource("/image/icon/icon.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image imgIcon = kit.createImage(urlIcon);
        setIconImage(imgIcon);
        bs = new SetDefault(x,y);
        positionPoint=0;
        bs.setVisible(true);
        setButton();
        bs.addKeyListener(new menuKeyListener());
        bs.add(bm.backMenuBtn);
        backToMenu();
        bs.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusBS();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void requestFocusBS() {
        bs.requestFocus();
        bs.setFocusable(true);
    }
    public class menuKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyValue = e.getKeyCode();
            System.out.println(keyValue);
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
                    resetModeNum =0;
                    new ScoreReset();
                }
                else if(positionPoint==1){
                    resetModeNum =1;
                    new ScoreReset();
                }
            }
            else if(keyValue==KeyEvent.VK_BACK_SPACE){
                backMenu();
            }
        }
    }
    public void setBtnImage(){
        for (int i=0;i<2;i++){
            if (positionPoint==i) menuButton[i].setIcon(EnterImage[i]);
            else menuButton[i].setIcon(BasicImage[i]);
        }
    }
    public void setButton() {
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
                resetModeNum =0;
                new ScoreReset();
            }
        });
        menuButton[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                resetModeNum =1;
                new ScoreReset();
            }
        });
        setBtnImage();
    }

    public void backToMenu(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                backMenu();
            }
        });
    }
    public void backMenu(){
        bs.setVisible(false);
        new SettingMenu(bs.getX(),bs.getY());
    }
}
