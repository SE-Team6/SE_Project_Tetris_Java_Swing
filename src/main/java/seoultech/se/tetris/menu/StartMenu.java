package seoultech.se.tetris.menu;

import seoultech.se.tetris.component.board.Board;
import seoultech.se.tetris.component.board.NormalBoard;
import seoultech.se.tetris.main.GameOver;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static seoultech.se.tetris.menu.BasicSet.*;


public class StartMenu extends JFrame {

    // 시작 메뉴 버튼 이미지
    private ImageIcon gameStartBtnImage = new ImageIcon("src/main/resources/image/Button/start_Menu_btn/Game_Start_Basic.jpg");
    private ImageIcon gameSettingBtnImage = new ImageIcon("src/main/resources/image/Button/start_Menu_btn/Game_Setting_Basic.jpg");
    private ImageIcon scoreBoardBtnImage = new ImageIcon("src/main/resources/image/Button/start_Menu_btn/ScoreBoard_Basic.jpg");
    private ImageIcon exitBtnImage = new ImageIcon("src/main/resources/image/Button/start_Menu_btn/Exit_Basic.jpg");

    // 선택된 시작 메뉴 버튼 이미지
    private ImageIcon gameStartBtnEnterImage = new ImageIcon("src/main/resources/image/Button/start_Menu_btn/Game_Start_Enter.jpg");
    private ImageIcon gameSettingBtnEnterImage = new ImageIcon("src/main/resources/image/Button/start_Menu_btn/Game_Setting_Enter.jpg");
    private ImageIcon scoreBoardBtnEnterImage = new ImageIcon("src/main/resources/image/Button/start_Menu_btn/ScoreBoard_Enter.jpg");
    private ImageIcon exitBtnEnterImage = new ImageIcon("src/main/resources/image/Button/start_Menu_btn/Exit_Enter.jpg");

    private JButton gameStartBtn = new JButton(gameStartBtnImage);
    private JButton gameSettingBtn = new JButton(gameSettingBtnImage);
    private JButton scoreBoardBtn = new JButton(scoreBoardBtnImage);
    private JButton exitBtn = new JButton(exitBtnImage);

    public  static  int positionPoint = 1;
    BasicSet bs = new BasicSet();

    //시작 메뉴
    public StartMenu(){
        bs.addKeyListener(new menuListener());
        start_Menu_Screen_btn();
    }

    public class menuListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key= e.getKeyCode();
            if (key == KeyEvent.VK_DOWN){
                positionPoint +=1;
                if(positionPoint ==5) positionPoint =1;
                allPositionPoint();
            }
            else if(key == KeyEvent.VK_UP){
                positionPoint -=1;
                if(positionPoint ==0) positionPoint =4;
                allPositionPoint();
            }
            else if(key == KeyEvent.VK_ENTER){
                switch (positionPoint) {
                    case 1: // 게임 시작
                        Board main = new NormalBoard();
                        main.setSize(Width, Height);
                        main.setLocation(0, 0);
                        main.setVisible(true);
                        bs.setVisible(false);
                        break;
                    case 2: // 게임 설정
                        bs.setVisible(false);
                        new SettingMenu();
                        break;
                    case 3: // 스코어 보드
                        bs.setVisible(false);
                        new GameOver();
                        break;
                    case 4: // 게임종료
                        System.exit(0);
                        break;
                }

            }
        }
    }

    public void allPositionPoint(){
        if(positionPoint ==1) gameStartBtn.setIcon(gameStartBtnEnterImage);
        else gameStartBtn.setIcon(gameStartBtnImage);
        if(positionPoint ==2) gameSettingBtn.setIcon(gameSettingBtnEnterImage);
        else gameSettingBtn.setIcon(gameSettingBtnImage);
        if(positionPoint ==3) scoreBoardBtn.setIcon(scoreBoardBtnEnterImage);
        else scoreBoardBtn.setIcon(scoreBoardBtnImage);
        if(positionPoint ==4) exitBtn.setIcon(exitBtnEnterImage);
        else exitBtn.setIcon(exitBtnImage);
    }
    public void hideButton(){
        gameStartBtn.setVisible(false);
        gameSettingBtn.setVisible(false);
        scoreBoardBtn.setVisible(false);
        exitBtn.setVisible(false);
    }
    public void start_Menu_Screen_btn(){
        //시작 메뉴 버튼 4가지 설정
        gameStartBtn.setBounds(buttonX, buttonY,buttonSizeX,buttonSizeY);
        gameStartBtn.setBorderPainted(false);
        gameStartBtn.setContentAreaFilled(false);
        gameStartBtn.setFocusPainted(false);
        allPositionPoint();
        bs.add(gameStartBtn);

        gameSettingBtn.setBounds(buttonX, buttonY +70,buttonSizeX,buttonSizeY);
        gameSettingBtn.setBorderPainted(false);
        gameSettingBtn.setContentAreaFilled(false);
        gameSettingBtn.setFocusPainted(false);

        bs.add(gameSettingBtn);

        scoreBoardBtn.setBounds(buttonX, buttonY +140,buttonSizeX,buttonSizeY);
        scoreBoardBtn.setBorderPainted(false);
        scoreBoardBtn.setContentAreaFilled(false);
        scoreBoardBtn.setFocusPainted(false);


        bs.add(scoreBoardBtn);

        exitBtn.setBounds(buttonX, buttonY +210,buttonSizeX,buttonSizeY);
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setFocusPainted(false);
        bs.add(exitBtn);
    }
}