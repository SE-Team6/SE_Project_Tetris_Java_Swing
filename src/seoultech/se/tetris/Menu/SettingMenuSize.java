package seoultech.se.tetris.Menu;

import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.event.*;

import static seoultech.se.tetris.Menu.BasicSet.*;
import static seoultech.se.tetris.Menu.StartMenu.*;
import static seoultech.se.tetris.component.JSONWriter.*;


public class SettingMenuSize extends JFrame {

    //해상도 이미지
    private  ImageIcon firstScreenSizeImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/size_set_btn/400x500.jpg"));
    private  ImageIcon secondScreenSizeImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/size_set_btn/600x800.jpg"));
    private  ImageIcon thirdScreenSizeImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/size_set_btn/800x1000.jpg"));

    private  ImageIcon firstScreenSizeEImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/size_set_btn/400x500_E.jpg"));
    private  ImageIcon secondScreenSizeEImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/size_set_btn/600x800_E.jpg"));
    private  ImageIcon thirdScreenSizeEImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/size_set_btn/800x1000_E.jpg"));

    private  JButton firstScreenSizeBtn = new JButton(firstScreenSizeImage);
    private  JButton secondScreenSizeBtn = new JButton(secondScreenSizeImage);
    private  JButton thirdScreenSizeBtn = new JButton(thirdScreenSizeImage);

    Version ver = new Version();
    BasicSet bs = new BasicSet();
    BackMenu bm = new BackMenu();
    public SettingMenuSize(){
        positionPoint =1;
        bs.setVisible(true);
        bs.add(bm.backMenuBtn);
        bs.addKeyListener(new menuKeyListener());
        settingScreenSizeBtn();
        backToMenu();
    }

    public void settingScreenSizeBtn(){
        allPositionPoint();
        firstScreenSizeBtn.setBounds(buttonX,buttonY,buttonSizeX,buttonSizeY);
        firstScreenSizeBtn.setBorderPainted(false);
        firstScreenSizeBtn.setContentAreaFilled(false);
        firstScreenSizeBtn.setFocusPainted(false);
        bs.add(firstScreenSizeBtn);

        secondScreenSizeBtn.setBounds(buttonX,buttonY+70,buttonSizeX,buttonSizeY);
        secondScreenSizeBtn.setBorderPainted(false);
        secondScreenSizeBtn.setContentAreaFilled(false);
        secondScreenSizeBtn.setFocusPainted(false);
        bs.add(secondScreenSizeBtn);

        thirdScreenSizeBtn.setBounds(buttonX,buttonY+140,buttonSizeX,buttonSizeY);
        thirdScreenSizeBtn.setBorderPainted(false);
        thirdScreenSizeBtn.setContentAreaFilled(false);
        thirdScreenSizeBtn.setFocusPainted(false);
        bs.add(thirdScreenSizeBtn);

    }
    public class menuKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_DOWN:
                    positionPoint +=1;
                    if(positionPoint ==4) positionPoint =1;
                    allPositionPoint();
                    break;
                case  KeyEvent.VK_UP:
                    positionPoint -=1;
                    if(positionPoint ==0) positionPoint =3;
                    allPositionPoint();
                    break;
                case KeyEvent.VK_ENTER:
                    switch (positionPoint){
                        case 1:
                            writeResolution(400,500,18);
                            ver.firstScreenSizeSet();
                            screenSizeSet();
                            break;
                        case 2:
                            writeResolution(600,800,18);
                            ver.secondScreenSizeSet();
                            screenSizeSet();
                            break;
                        case 3:
                            writeResolution(800,1000,18);
                            ver.thirdScreenSizeSet();
                            screenSizeSet();
                            break;
                    }
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    bs.setVisible(false);
                    new SettingMenu();
                    break;
            }
        }
    }

    public void allPositionPoint(){
        if(positionPoint ==1) firstScreenSizeBtn.setIcon(firstScreenSizeEImage);
        else firstScreenSizeBtn.setIcon(firstScreenSizeImage);
        if(positionPoint ==2) secondScreenSizeBtn.setIcon(secondScreenSizeEImage);
        else secondScreenSizeBtn.setIcon(secondScreenSizeImage);
        if(positionPoint ==3) thirdScreenSizeBtn.setIcon(thirdScreenSizeEImage);
        else thirdScreenSizeBtn.setIcon(thirdScreenSizeImage);
    }
    public void screenSizeSet(){
        bs.setVisible(false);
        bs.setSize(Width,Height);
        bs.setVisible(true);
        bs.menuBar.setBounds(0, 0, Width, menuBarHeight);
        bs.menuBarExitBtn.setBounds(Width-20,menuBarExitBtnHeight,20,20);
        bs.gameTitle.setBounds(gameTitleX, gameTitleY,300,50);
        firstScreenSizeBtn.setBounds(buttonX,buttonY,buttonSizeX,buttonSizeY);
        secondScreenSizeBtn.setBounds(buttonX,buttonY+70,buttonSizeX,buttonSizeY);
        thirdScreenSizeBtn.setBounds(buttonX,buttonY+140,buttonSizeX,buttonSizeY);
    }
    public void backToMenu(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new SettingMenu();
            }
        });
    }
}
