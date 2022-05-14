package seoultech.se.tetris.menu;

import seoultech.se.tetris.component.board.match.item.MatchItemBoardParent;
import seoultech.se.tetris.component.board.match.normal.MatchNormalBoardParent;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static seoultech.se.tetris.menu.SetDefault.*;
import static seoultech.se.tetris.menu.SetKeyMenu.positionPoint;

public class GameMode extends JFrame {

    public static int gameModeNum2; //0:노말 1:아이템

    private SetDefault bs;
    private BackMenuBtn bm = new BackMenuBtn();
    private ImageIcon normalModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/NormalMode_B.jpeg"));
    private ImageIcon itemModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/ItemMode_B.jpeg"));
    private ImageIcon normalModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/NormalMode_E.jpeg"));
    private ImageIcon itemModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/ItemMode_E.jpeg"));
    private ImageIcon[] BasicImage = {normalModeBtnImage, itemModeBtnImage, itemModeBtnImage, itemModeBtnImage};
    private ImageIcon[] EnterImage = {normalModeBtnEnterImage, itemModeBtnEnterImage, itemModeBtnEnterImage, itemModeBtnEnterImage};
    private JButton[] menuButton = new JButton[4];

    public GameMode(){}

    public GameMode(int x, int y) {
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
                if (positionPoint == 4) positionPoint = 0;
                setBtnImage();
            } else if (keyValue == key.UP) {
                positionPoint -= 1;
                if (positionPoint == -1) positionPoint = 2;
                setBtnImage();
            }
            else if(keyValue==KeyEvent.VK_ENTER){
                if (positionPoint==0){
                    gameModeNum2 =0;//노말
                    bs.setVisible(false);
                    new GameDifficulty(bs.getX(), bs.getY());
                } else if(positionPoint==1) {
                    gameModeNum2 = 1;//아이템
                    bs.setVisible(false);
                    new GameDifficulty(bs.getX(), bs.getY());
                } else if(positionPoint==2) {
                    bs.setVisible(false);
                    new MatchNormalBoardParent();
                } else {
                    bs.setVisible(false);
                    new MatchItemBoardParent();
                }
            }
            else if(keyValue==KeyEvent.VK_BACK_SPACE){
                bs.setVisible(false);
                new StartMenu(bs.getX(), bs.getY());
            }
        }
    }
    public void setBtnImage(){
        for (int i=0;i<4;i++){
            if (positionPoint==i) menuButton[i].setIcon(EnterImage[i]);
            else menuButton[i].setIcon(BasicImage[i]);
        }
    }
    public void setButton() { // 일반,아이템
        int addY = 0;
        for (int i = 0; i < 4; i++) {
            menuButton[i] = new JButton(BasicImage[i]);
            menuButton[i].setBounds(buttonX, buttonY + addY, buttonSizeX, buttonSizeY);
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
                new GameDifficulty(bs.getX(), bs.getY());
            }
        });
        menuButton[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gameModeNum2 =1;//아이템
                bs.setVisible(false);
                new GameDifficulty(bs.getX(), bs.getY());
            }
        });
        menuButton[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new MatchNormalBoardParent();
            }
        });
        menuButton[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new MatchItemBoardParent();
            }
        });

        setBtnImage();
    }

    public void backToMenu(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new StartMenu(bs.getX(), bs.getY());
            }
        });
    }
}
