package seoultech.se.tetris.menu;

import seoultech.se.tetris.component.Keyboard;

import javax.swing.*;
import java.awt.event.*;

import static seoultech.se.tetris.component.JSONWriter.*;
import static seoultech.se.tetris.menu.BasicSet.*;

public class SettingMenu extends JFrame {

    //설정 화면 이미지
    private ImageIcon screenSizeImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/main_btn/해상도_B.jpg");
    private ImageIcon keySettingImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/main_btn/조작설정_B.jpg");
    private ImageIcon scoreResetImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/main_btn/기록초기화_B.jpg");
    private ImageIcon colorBlindnessImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/main_btn/색맹모드_B.jpg");
    private ImageIcon allResetImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/main_btn/설정초기화_B.jpg");

    private ImageIcon screenSizeEImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/main_btn/해상도_E.jpg");
    private ImageIcon keySettingEImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/main_btn/조작설정_E.jpg");
    private ImageIcon scoreResetEImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/main_btn/기록초기화_E.jpg");
    private ImageIcon colorBlindnessEImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/main_btn/색맹모드_E.jpg");
    private ImageIcon allResetEImage = new ImageIcon("src/main/resources/image//Button/setting_Menu_btn/main_btn/설정초기화_E.jpg");

    private ImageIcon[] BasicImage = {screenSizeImage,keySettingImage,scoreResetImage,colorBlindnessImage,allResetImage};
    private ImageIcon[] EnterImage = {screenSizeEImage,keySettingEImage,scoreResetEImage,colorBlindnessEImage,allResetEImage};
    private JButton[] menuButton= new JButton[5];

    private int positionPoint;

    BasicSet bs = new BasicSet();
    BackMenu bm = new BackMenu();
    public SettingMenu(){
        positionPoint =0;
        bs.add(bm.backMenuBtn);
        bs.addKeyListener(new menuKeyListener());
        bs.setVisible(true);
        settingScreenBtn();
        backToMenu();
    }

    public class menuKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyValue= e.getKeyCode();
            if(keyValue==key.DOWN){
                positionPoint +=1;
                if(positionPoint ==5) positionPoint =0;
                allPositionPoint();
            }
            else if(keyValue==key.UP){
                positionPoint -=1;
                if(positionPoint ==-1) positionPoint =4;
                allPositionPoint();
            }
            else if(keyValue==KeyEvent.VK_ENTER){
                switch (positionPoint){
                    case 0://해상도 설정
                        bs.setVisible(false);
                        new SettingMenuSize();
                        break;
                    case 1://키 설정
                        bs.setVisible(false);
                        new SettingMenuKeySet();
                        break;
                    case 2://스코어 보드 초기화
                        new ScoreReset();
                        break;
                    case 3://색맹 모드.
                        new ColorMode();
                        break;
                    case 4://설정 초기화.
                        int [] keyValueArr = {37,39,38,40,27,32};
                        AllReset ar = new AllReset();
                        ar.yesReset.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                bs.setVisible(false);
                                writeResolution(400,600,18);
                                writeKey(keyValueArr);
                                writeColorMode(0);
                                JOptionPane.showMessageDialog(null,"설정이 초기화 되었습니다");
                                bs.setVisible(true);
                            }
                        });
                        break;
                }
            }
            else if(keyValue==KeyEvent.VK_BACK_SPACE){
                bs.setVisible(false);
                new StartMenu();
            }
        }
        }
    public void allPositionPoint(){

        for(int i=0;i<5;i++){
            if (positionPoint==i) menuButton[i].setIcon(EnterImage[i]);
            else menuButton[i].setIcon(BasicImage[i]);
        }
    }
    public void settingScreenBtn(){//menuButton[] = {해상도 설정버튼,게임 조작설정버튼, 스코어 보드 초기화버튼,색맹모드버튼, 설정 초기화버튼}
        int addY=0;
        for (int i=0;i<5;i++){
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
    public void backToMenu(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new StartMenu();
            }
        });
    }
    public void allReset(){

    }

}
