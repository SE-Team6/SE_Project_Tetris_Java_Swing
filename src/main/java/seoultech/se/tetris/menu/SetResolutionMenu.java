package seoultech.se.tetris.menu;

import seoultech.se.tetris.component.Keyboard;
import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import static seoultech.se.tetris.component.JSONWriter.writeResolution;
import static seoultech.se.tetris.menu.SetDefault.*;


public class SetResolutionMenu extends JFrame {

    //해상도 이미지
    private  ImageIcon firstScreenSizeImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/size_set_btn/400x600.jpg"));
    private  ImageIcon secondScreenSizeImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/size_set_btn/600x800.jpg"));
    private  ImageIcon thirdScreenSizeImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/size_set_btn/800x1000.jpg"));

    private  ImageIcon firstScreenSizeEImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/size_set_btn/400x600_E.jpg"));
    private  ImageIcon secondScreenSizeEImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/size_set_btn/600x800_E.jpg"));
    private  ImageIcon thirdScreenSizeEImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/size_set_btn/800x1000_E.jpg"));

    private ImageIcon[] BasicImage = {firstScreenSizeImage,secondScreenSizeImage,thirdScreenSizeImage};
    private ImageIcon[] EnterImage = {firstScreenSizeEImage,secondScreenSizeEImage,thirdScreenSizeEImage};
    private  JButton[] menuButton= new JButton[3];

    GetSetting ver = new GetSetting();
    SetDefault bs;
    BackMenuBtn bm = new BackMenuBtn();
    Keyboard key = new Keyboard();

    private int positionPoint=0;

    public SetResolutionMenu(){}

    public SetResolutionMenu(int x, int y){
        URL urlIcon = Tetris.class.getResource("/image/icon/icon.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image imgIcon = kit.createImage(urlIcon);
        setIconImage(imgIcon);
        bs = new SetDefault(x, y);
        positionPoint =0;
        bs.setVisible(true);
        bs.add(bm.backMenuBtn);
        bs.addKeyListener(new menuKeyListener());
        setResolutionMenuBtn();
        backToMenuBtnAction();
    }
    public void setResolutionMenuBtn(){//menuButton[] = {400x600,600x800,800x1000}
        int addY=0;
        for (int i=0;i<3;i++){
            menuButton[i]=new JButton(BasicImage[i]);
            menuButton[i].setBounds(buttonX,buttonY+addY, buttonWidth, buttonHeight);
            menuButton[i].setBorderPainted(false);
            menuButton[i].setContentAreaFilled(false);
            menuButton[i].setFocusPainted(false);
            bs.add(menuButton[i]);
            addY+=70;
        }
        menuButton[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                writeResolution(400,600,28);
                ver.getFirstResolution();
                bs.setVisible(false);
                new SetResolutionMenu(bs.getX(),bs.getY());
            }
        });
        menuButton[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                writeResolution(600,800,48);
                ver.getSecondResolution();
                bs.setVisible(false);
                new SetResolutionMenu(bs.getX(),bs.getY());
            }
        });
        menuButton[2].addMouseListener((new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                writeResolution(800,1000,64);
                ver.getThirdResolution();
                bs.setVisible(false);
                new SetResolutionMenu(bs.getX(),bs.getY());
            }
        }));
        setBtnImage();
    }
    public class menuKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keyValue = e.getKeyCode();
            if(keyValue==key.DOWN){
                positionPoint +=1;
                if(positionPoint ==3) positionPoint =0;
                setBtnImage();
            }
            else if(keyValue==key.UP) {
                positionPoint -=1;
                if(positionPoint ==-1) positionPoint =2;
                setBtnImage();
            }
            else if(keyValue==KeyEvent.VK_ENTER) {
                switch (positionPoint){
                    case 0:
                        writeResolution(400,600,28);
                        break;
                    case 1:
                        writeResolution(600,800,48);
                        break;
                    case 2:
                        writeResolution(800,1000,64);
                        break;
                }
                ver.getFirstResolution();
                bs.setVisible(false);
                new SetResolutionMenu(bs.getX(),bs.getY());
            }
            else if(keyValue == KeyEvent.VK_BACK_SPACE){
                    bs.setVisible(false);
                    new SettingMenu(bs.getX(), bs.getY());
            }
        }
    }
    public void setBtnImage(){
        for (int i=0;i<3;i++){
            if (positionPoint==i) menuButton[i].setIcon(EnterImage[i]);
            else menuButton[i].setIcon(BasicImage[i]);
        }
    }
    public void backToMenuBtnAction(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new SettingMenu(bs.getX(), bs.getY());
            }
        });
    }
}
