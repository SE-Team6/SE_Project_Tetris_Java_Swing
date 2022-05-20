package seoultech.se.tetris.menu;

import seoultech.se.tetris.component.board.match.item.MatchItemBoardParent;
import seoultech.se.tetris.component.board.match.normal.MatchNormalBoardParent;
import seoultech.se.tetris.component.board.match.timer.MatchTimerBoardParent;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static seoultech.se.tetris.menu.SetDefault.*;
import static seoultech.se.tetris.menu.SetKey1P.positionPoint;

public class BattleModeMenu extends JFrame {

    private SetDefault bs;
    private BackMenuBtn bm = new BackMenuBtn();
    private ImageIcon normalModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/NormalMode_B.jpeg"));
    private ImageIcon itemModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/ItemMode_B.jpeg"));
    private ImageIcon timerModeBtnImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/TimerMode_B.jpg"));

    private ImageIcon normalModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/NormalMode_E.jpeg"));
    private ImageIcon itemModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/ItemMode_E.jpeg"));
    private ImageIcon timerModeBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameMode_btn/TimerMode_E.jpg"));

    private ImageIcon[] BasicImage = {normalModeBtnImage, itemModeBtnImage,timerModeBtnImage};
    private ImageIcon[] EnterImage = {normalModeBtnEnterImage, itemModeBtnEnterImage,timerModeBtnEnterImage};
    private JButton[] menuButton = new JButton[3];

    public BattleModeMenu(){}

    public BattleModeMenu(int x, int y) {
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
                if (positionPoint == 3) positionPoint = 0;
                setBtnImage();
            } else if (keyValue == key.UP) {
                positionPoint -= 1;
                if (positionPoint == -1) positionPoint = 2;
                setBtnImage();
            }
            else if(keyValue==KeyEvent.VK_ENTER){
                if (positionPoint==0){
                    bs.setVisible(false);
                    // 노말 배틀 모드
                    new MatchNormalBoardParent();
                }
                else if(positionPoint==1){
                    bs.setVisible(false);
                    // 아이템 배틀 모드
                    new MatchItemBoardParent();
                }
                else if(positionPoint==2){
                    bs.setVisible(false);
                    // 타이머 배틀 모드
                    new MatchTimerBoardParent(100000);
                }
            }
            else if(keyValue==KeyEvent.VK_BACK_SPACE){
                bs.setVisible(false);
                new PlayModeMenu(bs.getX(), bs.getY());
            }
        }
    }
    public void setBtnImage(){
        for (int i=0;i<3;i++){
            if (positionPoint==i) menuButton[i].setIcon(EnterImage[i]);
            else menuButton[i].setIcon(BasicImage[i]);
        }
    }
    public void setButton() { // 일반,아이템
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
                bs.setVisible(false);
                //노말 배틀 모드
//                MatchNormalBoardParent.setDifficulty();
                new MatchNormalBoardParent();
            }
        });
        menuButton[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                // 아이템 배틀 모드
                new MatchItemBoardParent();
            }
        });
        menuButton[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                // 타이머 배틀 모드
                new MatchTimerBoardParent(10000000);
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
