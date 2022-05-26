package seoultech.se.tetris.menu;

import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import static seoultech.se.tetris.component.JSONWriter.*;
import static seoultech.se.tetris.menu.SetDefault.*;

public class SettingMenu extends JFrame {

    //설정 화면 이미지
    private ImageIcon screenSizeImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/main_btn/Resolution_B.jpg"));
    private ImageIcon keySettingImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/main_btn/KeySet_B.jpg"));
    private ImageIcon scoreResetImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/main_btn/ScoreReset_B.jpg"));
    private ImageIcon colorBlindnessImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/main_btn/ColorBlindness_B.jpg"));
    private ImageIcon allResetImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/main_btn/SettingReset_B.jpg"));

    private ImageIcon screenSizeEImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/main_btn/Resolution_E.jpg"));
    private ImageIcon keySettingEImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/main_btn/KeySet_E.jpg"));
    private ImageIcon scoreResetEImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/main_btn/ScoreReset_E.jpg"));
    private ImageIcon colorBlindnessEImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/main_btn/ColorBlindness_E.jpg"));
    private ImageIcon allResetEImage = new ImageIcon(getClass().getResource("/image/Button/setting_Menu_btn/main_btn/SettingReset_E.jpg"));

    private ImageIcon[] BasicImage = {screenSizeImage,keySettingImage,scoreResetImage,colorBlindnessImage,allResetImage};
    private ImageIcon[] EnterImage = {screenSizeEImage,keySettingEImage,scoreResetEImage,colorBlindnessEImage,allResetEImage};
    private JButton[] menuButton= new JButton[5];

    private int positionPoint;

    SetDefault bs;
    BackMenuBtn bm = new BackMenuBtn();

    public SettingMenu(){}

    public SettingMenu(int x, int y){
        URL urlIcon = Tetris.class.getResource("/image/icon/icon.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image imgIcon = kit.createImage(urlIcon);
        setIconImage(imgIcon);
        bs = new SetDefault(x,y);
        positionPoint = 0;
        bs.add(bm.backMenuBtn);
        bs.addKeyListener(new menuKeyListener());
        bs.setVisible(true);
        setSettingMenuBtn();
        backToMenuBtnAction();
    }

    public class menuKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyValue= e.getKeyCode();
            if(keyValue==key.DOWN){
                positionPoint +=1;
                if(positionPoint ==5) positionPoint =0;
                setBtnImage();
            }
            else if(keyValue==key.UP){
                positionPoint -=1;
                if(positionPoint ==-1) positionPoint =4;
                setBtnImage();
            }
            else if(keyValue==KeyEvent.VK_ENTER){
                menuAction(positionPoint);
            }
            else if(keyValue==KeyEvent.VK_BACK_SPACE){
                bs.setVisible(false);
                new StartMenu(bs.getX(), bs.getY());
            }
        }
        }
    public void setBtnImage(){
        for(int i=0;i<5;i++){
            if (positionPoint==i) menuButton[i].setIcon(EnterImage[i]);
            else menuButton[i].setIcon(BasicImage[i]);
        }
    }
    public void setSettingMenuBtn(){//menuButton[] = {해상도 설정버튼,게임 조작설정버튼, 스코어 보드 초기화버튼,색맹모드버튼, 설정 초기화버튼}
        int addY=0;
        for (int i=0;i<5;i++){
            menuButton[i]=new JButton(BasicImage[i]);
            menuButton[i].setBounds(buttonX,buttonY+addY, buttonWidth, buttonHeight);
            menuButton[i].setBorderPainted(false);
            menuButton[i].setContentAreaFilled(false);
            menuButton[i].setFocusPainted(false);
            bs.add(menuButton[i]);
            addY+=70;
        }
        for (int i=0; i<5;i++){
            int finalI = i;
            menuButton[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    menuAction(finalI);
                }
            });}
        setBtnImage();
    }
    public void menuAction(int num){
        switch (num){
            case 0://해상도 설정
                bs.setVisible(false);
                new SetResolutionMenu(bs.getX(), bs.getY());
                break;
            case 1://키 설정
                bs.setVisible(false);
                new SetKeyMenu(bs.getX(), bs.getY());
                break;
            case 2://스코어 보드 초기화
                bs.setVisible(false);
                new ScoreResetMenu(bs.getX(), bs.getY());
                break;
            case 3://색맹 모드.
                new ColorMode();
                break;
            case 4://설정 초기화.
                int [] keyValueArr = {65, 68, 87, 83, 27, 32};
                SettingAllReset ar = new SettingAllReset();
                ar.yesReset.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        bs.setVisible(false);
                        writeResolution(400,600,28);
                        writeKey(keyValueArr, 1);
                        writeColorMode(0);
                        JOptionPane.showMessageDialog(null,"설정이 초기화 되었습니다");
                        new SettingMenu(bs.getX(), bs.getY());
                        ar.setVisible(false);
                    }
                });
                break;
        }
    }
    public void backToMenuBtnAction(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new StartMenu(bs.getX(), bs.getY());
            }
        });
    }

}
