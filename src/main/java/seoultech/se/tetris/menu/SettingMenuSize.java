package seoultech.se.tetris.menu;

import seoultech.se.tetris.component.Keyboard;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static seoultech.se.tetris.component.JSONWriter.writeResolution;
import static seoultech.se.tetris.menu.BasicSet.*;


public class SettingMenuSize extends JFrame {

    //해상도 이미지
    private  ImageIcon firstScreenSizeImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/size_set_btn/400x500.jpg");
    private  ImageIcon secondScreenSizeImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/size_set_btn/600x800.jpg");
    private  ImageIcon thirdScreenSizeImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/size_set_btn/800x1000.jpg");

    private  ImageIcon firstScreenSizeEImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/size_set_btn/400x500_E.jpg");
    private  ImageIcon secondScreenSizeEImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/size_set_btn/600x800_E.jpg");
    private  ImageIcon thirdScreenSizeEImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/size_set_btn/800x1000_E.jpg");

    private ImageIcon[] BasicImage = {firstScreenSizeImage,secondScreenSizeImage,thirdScreenSizeImage};
    private ImageIcon[] EnterImage = {firstScreenSizeEImage,secondScreenSizeEImage,thirdScreenSizeEImage};
    private  JButton[] menuButton= new JButton[3];

    Version ver = new Version();
    BasicSet bs;
    BackMenu bm = new BackMenu();
    Keyboard key = new Keyboard();

    private int positionPoint=0;

    public SettingMenuSize(){}

    public SettingMenuSize(int x, int y){
        bs = new BasicSet(x, y);
        positionPoint =0;
        bs.setVisible(true);
        bs.add(bm.backMenuBtn);
        bs.addKeyListener(new menuKeyListener());
        settingScreenSizeBtn();
        backToMenu();
    }
    public void settingScreenSizeBtn(){//menuButton[] = {400x600,600x800,800x1000}
        int addY=0;
        for (int i=0;i<3;i++){
            menuButton[i]=new JButton(BasicImage[i]);
            menuButton[i].setBounds(buttonX,buttonY+addY,buttonSizeX,buttonSizeY);
            menuButton[i].setBorderPainted(false);
            menuButton[i].setContentAreaFilled(false);
            menuButton[i].setFocusPainted(false);
            bs.add(menuButton[i]);
            addY+=70;
        }
        allPositionPoint();
    }
    public class menuKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keyValue = e.getKeyCode();
            if(keyValue==key.DOWN){
                positionPoint +=1;
                if(positionPoint ==3) positionPoint =0;
                allPositionPoint();
            }
            else if(keyValue==key.UP) {
                positionPoint -=1;
                if(positionPoint ==-1) positionPoint =2;
                allPositionPoint();
            }
            else if(keyValue==KeyEvent.VK_ENTER) {
                switch (positionPoint){
                    case 0:
                        writeResolution(400,600,32);
                        ver.firstScreenSizeSet();
                        screenSizeSet();
                        break;
                    case 1:
                        writeResolution(600,800,48);
                        ver.secondScreenSizeSet();
                        screenSizeSet();
                        break;
                    case 2:
                        writeResolution(800,1000,64);
                        ver.thirdScreenSizeSet();
                        screenSizeSet();
                        break;
                }
            }
            else if(keyValue == KeyEvent.VK_BACK_SPACE){
                    bs.setVisible(false);
                    new SettingMenu(bs.getX(), bs.getY());
            }
        }
    }
    public void allPositionPoint(){
        for (int i=0;i<3;i++){
            if (positionPoint==i) menuButton[i].setIcon(EnterImage[i]);
            else menuButton[i].setIcon(BasicImage[i]);
        }
    }
    public void screenSizeSet(){
        bs.setVisible(false);
        bs.setVisible(true);
        bs.setSize(Width,Height);
        bs.menuBar.setBounds(0, 0, Width, menuBarHeight);
        bs.menuBarExitBtn.setBounds(Width-20,menuBarExitBtnHeight,20,20);
        bs.gameTitle.setBounds(gameTitleX, gameTitleY,300,50);
        menuButton[0].setBounds(buttonX,buttonY,buttonSizeX,buttonSizeY);
        menuButton[1].setBounds(buttonX,buttonY+70,buttonSizeX,buttonSizeY);
        menuButton[2].setBounds(buttonX,buttonY+140,buttonSizeX,buttonSizeY);
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
