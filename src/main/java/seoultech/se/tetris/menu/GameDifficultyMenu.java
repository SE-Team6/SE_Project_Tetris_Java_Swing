package seoultech.se.tetris.menu;

import seoultech.se.tetris.component.board.Board;
import seoultech.se.tetris.component.board.ItemBoard;
import seoultech.se.tetris.component.board.NormalBoard;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static seoultech.se.tetris.menu.SetDefault.*;

public class GameDifficultyMenu {
    private SetDefault bs;
    private BackMenuBtn bm = new BackMenuBtn();
    private ImageIcon easyModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/EasyMode_B.jpeg"));
    private ImageIcon normalModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/NormalMode_B.jpeg"));
    private ImageIcon hardModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/HardMode_B.jpeg"));
    private ImageIcon easyModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/EasyMode_E.jpeg"));
    private ImageIcon normalModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/NormalMode_E.jpeg"));
    private ImageIcon hardModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/HardMode_E.jpeg"));
    private ImageIcon[] BasicImage = {easyModeBtnImage,normalModeBtnImage ,hardModeBtnImage};
    private ImageIcon[] EnterImage = {easyModeBtnEnterImage, normalModeBtnEnterImage,hardModeBtnEnterImage};
    private JButton[] menuButton = new JButton[3];
    public static int gameDifficultyNum; //0:이지,1:노말,2:하드

    private int positionPoint;

    public GameDifficultyMenu(){}

    public GameDifficultyMenu(int x, int y){
        bs = new SetDefault(x,y);
        positionPoint=0;
        bs.setVisible(true);
        bs.addKeyListener(new menuKeyListener());
        bs.add(bm.backMenuBtn);
        setGameDifficultyBtn();
        backToMenu();
    }
    public class menuKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyValue = e.getKeyCode();
            if (keyValue == key.DOWN) {
                positionPoint += 1;
                if (positionPoint == 3) positionPoint = 0;
                setBtnImage();
            } else if (keyValue == key.UP) {
                positionPoint -= 1;
                if (positionPoint == -1) positionPoint = 2;
                setBtnImage();
            }
            else if(keyValue==KeyEvent.VK_ENTER){
                /*
                    positionPoint == 0 : easy
                    positionPoint == 1 : normal
                    positionPoint == 2 : hard
                 */
                gameDifficultyNum = positionPoint;
                bs.setVisible(false);
                new PlayModeMenu(bs.getX(), bs.getY());
//                if(gameModeNum2 == 0) normalMode(positionPoint);
//                else if(gameModeNum2 == 1) itemMode(positionPoint);

            }
            else if(keyValue==KeyEvent.VK_BACK_SPACE){
//                if (playModeNum == 0) {
//                bs.setVisible(false);
//                new GameModeMenu(bs.getX(), bs.getY());
//                }
//                else if (playModeNum == 1) {
//                    bs.setVisible(false);
//                    new BattleModeMenu(bs.getX(), bs.getY());
//                }
                bs.setVisible(false);
                new StartMenu(bs.getX(),bs.getY());
            }
        }
    }
    public void setBtnImage(){
        for (int i=0;i<3;i++){
            if (positionPoint==i) menuButton[i].setIcon(EnterImage[i]);
            else menuButton[i].setIcon(BasicImage[i]);
        }
    }
    public void setGameDifficultyBtn() { // 이지,노말,하드
        int addY = 0;
        for (int i = 0; i < 3; i++) {
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
                gameDifficultyNum=0;
                new PlayModeMenu(bs.getX(), bs.getY());
//                if(gameModeNum2 == 0) normalMode(positionPoint);
//                else if(gameModeNum2 == 1) itemMode(positionPoint);
            }
        });
        menuButton[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gameDifficultyNum=1;
                new PlayModeMenu(bs.getX(), bs.getY());
//                if(gameModeNum2 == 0) normalMode(positionPoint);
//                else if(gameModeNum2 == 1) itemMode(positionPoint);
            }
        });
        menuButton[2].addMouseListener((new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gameDifficultyNum=2;
                new PlayModeMenu(bs.getX(), bs.getY());
//                if(gameModeNum2 == 0) normalMode(positionPoint);
//                else if(gameModeNum2 == 1) itemMode(positionPoint);
            }
        }));
        setBtnImage();
    }
    public void backToMenu(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//                if (playModeNum == 0) {
//                    bs.setVisible(false);
//                    new GameModeMenu(bs.getX(), bs.getY());
//                }
//                else if (playModeNum == 1) {
//                    bs.setVisible(false);
//                    new BattleModeMenu(bs.getX(), bs.getY());
//                }
                bs.setVisible(false);
                new StartMenu(bs.getX(),bs.getY());
            }
        });
    }
}
