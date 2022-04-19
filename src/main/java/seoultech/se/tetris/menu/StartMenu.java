package seoultech.se.tetris.menu;

import org.json.simple.JSONObject;
import seoultech.se.tetris.component.ScoreBoardItemMode;
import seoultech.se.tetris.main.GameOver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import static seoultech.se.tetris.component.JSONLoader.loaderKey;
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

    private ImageIcon[] BasicImage = {gameStartBtnImage,gameSettingBtnImage,scoreBoardBtnImage,exitBtnImage};
    private ImageIcon[] EnterImage = {gameStartBtnEnterImage,gameSettingBtnEnterImage,scoreBoardBtnEnterImage,exitBtnEnterImage};
    private JButton[] menuButton= new JButton[4];

    //현재 사용중인 키 표시
    private String[] textSequence = {"LEFT", "RIGHT", "UP", "DOWN", "ESC", "SPACE"};
    private JLabel[] currentKey = new JLabel[6];
    char[] keyLoadCharValue = new char[6];
    String [] keyLoadStringValue = new String[6];

    private int positionPoint = 0;
    BasicSet bs = new BasicSet();

    //시작 메뉴
    public StartMenu(){
        start_Menu_Screen_btn();
        bs.setVisible(true);
        keyLoad();
        setCurrentKeyLabel();
        bs.addKeyListener(new menuListener());
    }

    public class menuListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyValue= e.getKeyCode();
            if (keyValue == key.DOWN){
                positionPoint +=1;
                if(positionPoint ==4) positionPoint =0;
                System.out.println(positionPoint);
                allPositionPoint();
            }
            else if(keyValue == key.UP){
                positionPoint -=1;
                if(positionPoint ==-1) positionPoint =3;
                allPositionPoint();
            }
            else if(keyValue == KeyEvent.VK_ENTER){
                switch (positionPoint) {
                    case 0: // 게임 시작
                        new GameMode();
                        bs.setVisible(false);
                        break;
                    case 1: // 게임 설정
                        bs.setVisible(false);
                        new SettingMenu();
                        break;
                    case 2: // 스코어 보드
                        new ScoreBoardItemMode();
                        break;
                    case 3: // 게임종료
                        bs.setVisible(false);
                        new GameOver();
                        break;
                }
            }
        }
    }
    public void allPositionPoint(){
        for (int i=0;i<4;i++){
            if (positionPoint==i) menuButton[i].setIcon(EnterImage[i]);
            else menuButton[i].setIcon(BasicImage[i]);
        }
    }
    public void start_Menu_Screen_btn(){//menuButton[] = {게임 시작버튼,게임 설정버튼, 스코어 보드버튼, 게임종료버튼}
        int addH = 0;
        for(int i=0;i<4;i++){
            menuButton[i] = new JButton(BasicImage[i]);
            menuButton[i].setBounds(buttonX, buttonY+addH,buttonSizeX,buttonSizeY);
            menuButton[i].setBorderPainted(false);
            menuButton[i].setContentAreaFilled(false);
            menuButton[i].setFocusPainted(false);
            bs.add(menuButton[i]);
            addH+=70;
        }
        allPositionPoint();
    }
    public void keyLoad() {// 기존 키 정보 불러오기
        JSONObject obj = loaderKey();
        Object[] var = new Object[6];
        Arrays.fill(var, 0);
        for (int i = 0; i < 6; i++) {
            var[i] = obj.get(textSequence[i]);
        }
        for (int i = 0; i < 6; i++) {
            int a = Integer.parseInt(var[i].toString());
            //json에서 받아온 아스키코드를 그대로 받으면 시각적으로 방향키와 esc 및 space가 원하는 대로 안보임 그래서 원하는대로 보기위한 처리과정
            switch (a) {
                case 37://left
                    a = 8592;
                    break;
                case 39://right
                    a = 8594;
                    break;
                case 38://up
                    a = 8593;
                    break;
                case 40://down
                    a = 8595;
                    break;
                case 27://esc
                    a = 9099;
                    break;
                case 32://space
                    a = 9251;
                    break;
                default:
            }
            char b = (char) a;
            keyLoadCharValue[i] = b;
            keyLoadStringValue[i]= String.valueOf(keyLoadCharValue[i]);
        }
    }
    public void setCurrentKeyLabel() {
        int addH = 0;
        for (int i = 0; i < 3; i++) {
            currentKey[i] = new JLabel();
            currentKey[i].setBounds(10, (Height - 100) + addH, 100, 30);
            currentKey[i].setFont(new Font("Bahnschrift", Font.BOLD, 15));
            currentKey[i].setForeground(Color.RED);
            currentKey[i].setText(textSequence[i]+" : "+keyLoadStringValue[i]);
            bs.add(currentKey[i]);
            addH += 30;
        }
        addH = 0;
        for (int i = 3; i < 6; i++) {
            currentKey[i] = new JLabel();
            currentKey[i].setBounds(110, (Height - 100) + addH, 100, 30);
            currentKey[i].setFont(new Font("Bahnschrift", Font.BOLD, 15));
            currentKey[i].setForeground(Color.RED);
            currentKey[i].setText(textSequence[i]+" : "+keyLoadStringValue[i]);
            bs.add(currentKey[i]);
            addH += 30;
        }
    }
}
