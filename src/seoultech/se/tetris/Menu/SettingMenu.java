package seoultech.se.tetris.Menu;

import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.event.*;

import static seoultech.se.tetris.Menu.BasicSet.*;
import static seoultech.se.tetris.Menu.StartMenu.*;

public class SettingMenu extends JFrame {

    //설정 화면 이미지
    private ImageIcon screenSizeImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/해상도_B.jpg"));
    private ImageIcon keySettingImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/조작설정_B.jpg"));
    private ImageIcon scoreResetImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/기록초기화_B.jpg"));
    private ImageIcon colorBlindnessImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/색맹모드_B.jpg"));
    private ImageIcon allResetImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/설정초기화_B.jpg"));

    private ImageIcon screenSizeEImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/해상도_E.jpg"));
    private ImageIcon keySettingEImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/조작설정_E.jpg"));
    private ImageIcon scoreResetEImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/기록초기화_E.jpg"));
    private ImageIcon colorBlindnessEImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/색맹모드_E.jpg"));
    private ImageIcon allResetEImage = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/설정초기화_E.jpg"));

    private  JButton screenSizeBtn = new JButton(screenSizeImage);
    private  JButton keySettingBtn = new JButton(keySettingImage);
    private  JButton scoreResetBtn = new JButton(scoreResetImage);
    private  JButton colorBlindnessBtn = new JButton(colorBlindnessImage);
    private  JButton allResetBtn = new JButton(allResetImage);

    BasicSet bs = new BasicSet();
    BackMenu bm = new BackMenu();
    public SettingMenu(){
        positionPoint =1;
        bs.setVisible(true);
        bs.add(bm.backMenuBtn);
        bs.addKeyListener(new menuKeyListener());
        settingScreenBtn();
        backToMenu();
    }

    public class menuKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {


            switch (e.getKeyCode()){
                case KeyEvent.VK_DOWN:
                    positionPoint +=1;
                    if(positionPoint ==6) positionPoint =1;
                    allPositionPoint();
                    break;
                case  KeyEvent.VK_UP:
                    positionPoint -=1;
                    if(positionPoint ==0) positionPoint =5;
                    allPositionPoint();
                    break;
                case KeyEvent.VK_ENTER:
                    switch (positionPoint){
                        case 1:
                            bs.setVisible(false);
                            new SettingMenuSize();
                            break;
                        case 2:
                            bs.setVisible(false);
                            new SettingMenuKeySet();
                            break;
                        case 3:
                            setVisible(false);
                            break;
                        case 4:
                            setVisible(false);
                            break;
                        case 5:
                            setVisible(false);
                            break;
                    }
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    bs.setVisible(false);
                    new StartMenu();
                    break;

            }
        }
    }

    public void allPositionPoint(){
        if(positionPoint ==1) screenSizeBtn.setIcon(screenSizeEImage);
        else screenSizeBtn.setIcon(screenSizeImage);
        if(positionPoint ==2) keySettingBtn.setIcon(keySettingEImage);
        else keySettingBtn.setIcon(keySettingImage);
        if(positionPoint ==3) scoreResetBtn.setIcon(scoreResetEImage);
        else scoreResetBtn.setIcon(scoreResetImage);
        if(positionPoint ==4) colorBlindnessBtn.setIcon(colorBlindnessEImage);
        else colorBlindnessBtn.setIcon(colorBlindnessImage);
        if(positionPoint ==5) allResetBtn.setIcon(allResetEImage);
        else allResetBtn.setIcon(allResetImage);
    }
    public void settingScreenBtn(){
        screenSizeBtn.setBounds(buttonX,buttonY,buttonSizeX,buttonSizeY);
        screenSizeBtn.setBorderPainted(false);
        screenSizeBtn.setContentAreaFilled(false);
        screenSizeBtn.setFocusPainted(false);
        allPositionPoint();
        bs.add(screenSizeBtn);

        keySettingBtn.setBounds(buttonX,buttonY+70,buttonSizeX,buttonSizeY);
        keySettingBtn.setBorderPainted(false);
        keySettingBtn.setContentAreaFilled(false);
        keySettingBtn.setFocusPainted(false);
        bs.add(keySettingBtn);

        scoreResetBtn.setBounds(buttonX,buttonY+140,buttonSizeX,buttonSizeY);
        scoreResetBtn.setBorderPainted(false);
        scoreResetBtn.setContentAreaFilled(false);
        scoreResetBtn.setFocusPainted(false);
        bs.add(scoreResetBtn);

        colorBlindnessBtn.setBounds(buttonX,buttonY+210,buttonSizeX,buttonSizeY);
        colorBlindnessBtn.setBorderPainted(false);
        colorBlindnessBtn.setContentAreaFilled(false);
        colorBlindnessBtn.setFocusPainted(false);
        bs.add(colorBlindnessBtn);

        allResetBtn.setBounds(buttonX,buttonY+280,buttonSizeX,buttonSizeY);
        allResetBtn.setBorderPainted(false);
        allResetBtn.setContentAreaFilled(false);
        allResetBtn.setFocusPainted(false);
        bs.add(allResetBtn);
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

}
