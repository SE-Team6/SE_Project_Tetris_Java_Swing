package seoultech.se.tetris.menu;
import seoultech.se.tetris.component.board.Board;
import seoultech.se.tetris.component.board.ItemBoard;
import seoultech.se.tetris.component.board.NormalBoard;

import static seoultech.se.tetris.menu.BasicSet.*;
import static seoultech.se.tetris.menu.GameMode.gameModeNum;
import static seoultech.se.tetris.menu.SettingMenuKeySet.positionPoint;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameDifficulty {
    private BasicSet bs = new BasicSet();
    private BackMenu bm = new BackMenu();
    private ImageIcon easyModeBtnImage = new ImageIcon("src/main/resources/image/Button/gameMode_btn/EasyMode_B.jpeg");
    private ImageIcon hardModeBtnImage = new ImageIcon("src/main/resources/image/Button/gameMode_btn/HardMode_B.jpeg");
    private ImageIcon easyModeBtnEnterImage = new ImageIcon("src/main/resources/image/Button/gameMode_btn/EasyMode_E.jpeg");
    private ImageIcon hardModeBtnEnterImage = new ImageIcon("src/main/resources/image/Button/gameMode_btn/HardMode_E.jpeg");
    private ImageIcon[] BasicImage = {easyModeBtnImage, hardModeBtnImage};
    private ImageIcon[] EnterImage = {easyModeBtnEnterImage, hardModeBtnEnterImage};
    private JButton[] menuButton = new JButton[2];

    public GameDifficulty(){
        positionPoint=0;
        bs.setVisible(true);
        bs.addKeyListener(new menuKeyListener());
        bs.add(bm.backMenuBtn);
        setButton();
        backToMenu();
    }
    public class menuKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyValue = e.getKeyCode();
            if (keyValue == key.DOWN) {
                positionPoint += 1;
                if (positionPoint == 2) positionPoint = 0;
                allPositionPoint();
            } else if (keyValue == key.UP) {
                positionPoint -= 1;
                if (positionPoint == -1) positionPoint = 1;
                allPositionPoint();
            }
            else if(keyValue==KeyEvent.VK_ENTER){
                if (positionPoint ==0){//이지 모드
                    if (gameModeNum==0)normalMode();
                    else if(gameModeNum==1)itemMode();
                }
                else if(positionPoint==1){// 하드 모드
                    if (gameModeNum==0)normalMode();
                    else if(gameModeNum==1)itemMode();
                }
            }
        }
    }
    public void allPositionPoint(){
        for (int i=0;i<2;i++){
            if (positionPoint==i) menuButton[i].setIcon(EnterImage[i]);
            else menuButton[i].setIcon(BasicImage[i]);
        }
    }
    public void setButton() {
        int addY = 0;
        for (int i = 0; i < 2; i++) {
            menuButton[i] = new JButton(BasicImage[i]);
            menuButton[i].setBounds(buttonX, buttonY + addY, buttonSizeX, buttonSizeY);
            menuButton[i].setBorderPainted(false);
            menuButton[i].setContentAreaFilled(false);
            menuButton[i].setFocusPainted(false);
            bs.add(menuButton[i]);
            addY += 70;
        }
        allPositionPoint();
    }
    public void itemMode(){
        Board ib = new ItemBoard();
        ib.setSize(Width,Height);
        ib.setLocationRelativeTo(null);
        ib.setVisible(true);
        bs.setVisible(false);
    }
    public void normalMode(){
        Board nb =new NormalBoard();
        nb.setSize(Width,Height);
        nb.setLocationRelativeTo(null);
        nb.setVisible(true);
        bs.setVisible(false);
    }
    public void backToMenu(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new GameMode();
            }
        });
    }
}
