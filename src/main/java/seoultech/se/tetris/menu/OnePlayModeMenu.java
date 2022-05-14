package seoultech.se.tetris.menu;

import seoultech.se.tetris.component.board.Board;
import seoultech.se.tetris.component.board.ItemBoard;
import seoultech.se.tetris.component.board.NormalBoard;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static seoultech.se.tetris.menu.GameDifficultyMenu.gameDifficultyNum;
import static seoultech.se.tetris.menu.SetDefault.*;
import static seoultech.se.tetris.menu.SetKey1P.positionPoint;

public class OnePlayModeMenu extends JFrame {

    public static int gameModeNum2; //0:노말 1:아이템

    private SetDefault bs;
    private BackMenuBtn bm = new BackMenuBtn();
    private ImageIcon normalModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/NormalMode_B.jpeg"));
    private ImageIcon itemModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/ItemMode_B.jpeg"));
    private ImageIcon normalModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/NormalMode_E.jpeg"));
    private ImageIcon itemModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/ItemMode_E.jpeg"));
    private ImageIcon[] BasicImage = {normalModeBtnImage, itemModeBtnImage};
    private ImageIcon[] EnterImage = {normalModeBtnEnterImage, itemModeBtnEnterImage};
    private JButton[] menuButton = new JButton[2];

    public OnePlayModeMenu(){}

    public OnePlayModeMenu(int x, int y) {
        bs = new SetDefault(x, y);
        positionPoint=0;
        bs.setVisible(true);
        setButton();
        bs.addKeyListener(new menuKeyListener());
        bs.add(bm.backMenuBtn);
        backToMenu();
    }
    public class menuKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyValue = e.getKeyCode();
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
                    gameModeNum2 =0;//노말
                    bs.setVisible(false);
                    normalMode(gameDifficultyNum);
                }
                else if(positionPoint==1){
                    gameModeNum2 =1;//아이템
                    bs.setVisible(false);
                    itemMode(gameDifficultyNum);
                }
            }
            else if(keyValue==KeyEvent.VK_BACK_SPACE){
                bs.setVisible(false);
                new PlayModeMenu(bs.getX(), bs.getY());
            }
        }
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
    public void setBtnImage(){
        for (int i=0;i<2;i++){
            if (positionPoint==i) menuButton[i].setIcon(EnterImage[i]);
            else menuButton[i].setIcon(BasicImage[i]);
        }
    }
    public void setButton() { // 일반,아이템
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
                gameModeNum2 =0;//노말
                bs.setVisible(false);
                normalMode(gameDifficultyNum);
            }
        });
        menuButton[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gameModeNum2 =1;//아이템
                bs.setVisible(false);
                itemMode(gameDifficultyNum);
            }
        });

        setBtnImage();
    }

    public void backToMenu(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new PlayModeMenu(bs.getX(), bs.getY());
            }
        });
    }
}
