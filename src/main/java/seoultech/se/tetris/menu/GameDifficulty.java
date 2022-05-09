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
import static seoultech.se.tetris.menu.GameModeMenu.gameModeNum2;

public class GameDifficulty {
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
    public static int gameDifficultyNum=1; //0:이지,1:노말,2:하드

    private int positionPoint;

    public GameDifficulty(){}

    public GameDifficulty(int x, int y){
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
                if(gameModeNum2 == 0) normalMode(positionPoint);
                else if(gameModeNum2 == 1) itemMode(positionPoint);

            }
            else if(keyValue==KeyEvent.VK_BACK_SPACE){
                bs.setVisible(false);
                new GameModeMenu(bs.getX(), bs.getY());
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
                if(gameModeNum2 == 0) normalMode(positionPoint);
                else if(gameModeNum2 == 1) itemMode(positionPoint);
            }
        });
        menuButton[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gameDifficultyNum=1;
                if(gameModeNum2 == 0) normalMode(positionPoint);
                else if(gameModeNum2 == 1) itemMode(positionPoint);
            }
        });
        menuButton[2].addMouseListener((new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gameDifficultyNum=2;
                if(gameModeNum2 == 0) normalMode(positionPoint);
                else if(gameModeNum2 == 1) itemMode(positionPoint);
            }
        }));
        setBtnImage();
    }
    public void itemMode(int difficulty){
        ItemBoard.setDifficulty(difficulty);
        Board ib = new ItemBoard();
        ib.setSize(screenWidth, screenHeight);
        ib.setLocation(bs.getX(), bs.getY());
//        ib.setLocationRelativeTo(null);
        ib.setVisible(true);
        bs.setVisible(false);
    }
    public void normalMode(int difficulty){
        NormalBoard.setDifficulty(difficulty);
        Board nb =new NormalBoard();
        nb.setSize(screenWidth, screenHeight);
        nb.setLocation(bs.getX(), bs.getY());
//        nb.setLocationRelativeTo(null);
        nb.setVisible(true);
        bs.setVisible(false);
    }
    public void backToMenu(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new GameModeMenu(bs.getX(),bs.getY());
            }
        });
    }
}
