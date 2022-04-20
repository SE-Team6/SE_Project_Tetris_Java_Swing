package seoultech.se.tetris.menu;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static seoultech.se.tetris.menu.BasicSet.*;
import static seoultech.se.tetris.menu.BasicSet.buttonSizeY;
import static seoultech.se.tetris.menu.SettingMenuKeySet.positionPoint;

public class GameMode extends JFrame {

    public static int gameModeNum=0; //0:노말 1:아이템

    private BasicSet bs = new BasicSet();
    private BackMenu bm = new BackMenu();
    private ImageIcon normalModeBtnImage = new ImageIcon("src/main/resources/image/Button/gameMode_btn/NormalMode_B.jpeg");
    private ImageIcon itemModeBtnImage = new ImageIcon("src/main/resources/image/Button/gameMode_btn/itemMode_B.jpeg");
    private ImageIcon normalModeBtnEnterImage = new ImageIcon("src/main/resources/image/Button/gameMode_btn/NormalMode_E.jpeg");
    private ImageIcon itemModeBtnEnterImage = new ImageIcon("src/main/resources/image/Button/gameMode_btn/itemMode_E.jpeg");
    private ImageIcon[] BasicImage = {normalModeBtnImage, itemModeBtnImage};
    private ImageIcon[] EnterImage = {normalModeBtnEnterImage, itemModeBtnEnterImage};
    private JButton[] menuButton = new JButton[2];

    public GameMode() {
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
                allPositionPoint();
            } else if (keyValue == key.UP) {
                positionPoint -= 1;
                if (positionPoint == -1) positionPoint = 1;
                allPositionPoint();
            }
            else if(keyValue==KeyEvent.VK_ENTER){
                if (positionPoint==0){
                    gameModeNum =0;
                    bs.setVisible(false);
                    new GameDifficulty();
                }
                else if(positionPoint==1){
                    gameModeNum =1;
                    bs.setVisible(false);
                    new GameDifficulty();
                }
            }
            else if(keyValue==KeyEvent.VK_BACK_SPACE){
                bs.setVisible(false);
                new StartMenu();
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
