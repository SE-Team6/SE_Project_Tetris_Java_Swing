package seoultech.se.tetris.Menu;

import seoultech.se.tetris.component.Board;
import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import static seoultech.se.tetris.Menu.Start_Menu.*;

public class Setting_Menu extends JFrame {
    private Image screenImage;
    private Graphics screenGraphics;

    // 기본 이미지
    private Image background = new ImageIcon(Tetris.class.getResource("../image/backGround/800x1000_BG.jpg")).getImage();
    private JLabel gametitle = new JLabel(new ImageIcon(Tetris.class.getResource("../image/Label/title/title.png")));

    //상단 메뉴바 이미지
    private JLabel menuBar = new JLabel(new ImageIcon(Tetris.class.getResource("../image/Label/menubar/third_menubar.jpg")));
    private ImageIcon menubar_Exit_Basic_Image = new ImageIcon(Tetris.class.getResource("../image/Button/static_btn/ExitButton_Basic.png"));
    private ImageIcon menubar_Exit_Enter_Image = new ImageIcon(Tetris.class.getResource("../image/Button/static_btn/ExitButton_Enter.png"));
    private JButton menubar_Exit_btn = new JButton(menubar_Exit_Basic_Image);
    private int mouseX, mouseY;

    //전 메뉴로 돌아가기 이미지
    private ImageIcon back_Menu_Image = new ImageIcon(Tetris.class.getResource("../image/Button/static_btn/back_B.png"));
    private ImageIcon back_Menu_E_Image = new ImageIcon(Tetris.class.getResource("../image/Button/static_btn/back_E.png"));

    private JButton back_Menu_btn = new JButton(back_Menu_Image);

    //설정 화면 이미지
    private ImageIcon screen_Size_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/해상도_B.jpg"));
    private ImageIcon key_Setting_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/조작설정_B.jpg"));
    private ImageIcon score_Reset_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/기록초기화_B.jpg"));
    private ImageIcon color_blindness_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/색맹모드_B.jpg"));
    private ImageIcon all_Reset_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/설정초기화_B.jpg"));

    private ImageIcon screen_Size_E_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/해상도_E.jpg"));
    private ImageIcon key_Setting_E_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/조작설정_E.jpg"));
    private ImageIcon score_Reset_E_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/기록초기화_E.jpg"));
    private ImageIcon color_blindness_E_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/색맹모드_E.jpg"));
    private ImageIcon all_Reset_E_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/main_btn/설정초기화_E.jpg"));

    private  JButton screen_Size_btn = new JButton(screen_Size_Image);
    private  JButton key_Setting_btn = new JButton(key_Setting_Image);
    private  JButton score_Reset_btn = new JButton(score_Reset_Image);
    private  JButton color_blindness_btn = new JButton(color_blindness_Image);
    private  JButton all_Reset_btn = new JButton(all_Reset_Image);

    public Setting_Menu (){
        possionPoint=1;
        basic_set();
        settingScreen_btn();
        back_Menu();
        addKeyListener(new menuKeyListner());
    }

    //기본 세팅
    public void paint(Graphics g){
        screenImage = createImage(Width,Height);
        screenGraphics = screenImage.getGraphics();
        screenDraw((Graphics2D)screenGraphics);
        g.drawImage(screenImage,0,0,null);
    }
    public void screenDraw(Graphics2D g){

        g.drawImage(background,0,0,null);
        paintComponents(g);

        this.repaint();
    }
    public void basic_set(){
        setUndecorated(true);
        setVisible(true); // 우리가 만든 게임창이 정상적으로 출력되도록.
        setTitle("Tetris Game");
        setSize(Width,Height);
        setResizable(false); // 한번 만들어진 게임창은 사용자가 임의적으로 못바꿈
        setLocationRelativeTo(null); // 게임창이 컴퓨터 정중앙에 뜨도록
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임창 종료시 프로그램도 종료하도록
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);
        setFocusable(true);

        gametitle.setBounds(gametitle_x,gametitle_y,300,50);
        add(gametitle);

        //상단메뉴바 종료 설정
        menubar_Exit_btn.setBounds(menubar_Exit_btn_Width,menubar_Exit_btn_Height,20,20);
        menubar_Exit_btn.setBorderPainted(false);
        menubar_Exit_btn.setContentAreaFilled(false);
        menubar_Exit_btn.setFocusPainted(false);
        menubar_Exit_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menubar_Exit_btn.setIcon(menubar_Exit_Enter_Image);
                menubar_Exit_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menubar_Exit_btn.setIcon(menubar_Exit_Basic_Image);
            }
        });
        add(menubar_Exit_btn);

        //상단메뉴바 설정
        menuBar.setBounds(0, 0, menubar_Width, menubar_Height);
        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();

                setLocation(x - mouseX, y - mouseY); // 메뉴바 이동가능하게
            }
        });
        add(menuBar);
    }
    public void back_Menu(){
        back_Menu_btn.setBounds(10,50,50,50);
        back_Menu_btn.setBorderPainted(false);
        back_Menu_btn.setContentAreaFilled(false);
        back_Menu_btn.setFocusPainted(false);

        add(back_Menu_btn);

        back_Menu_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                new Start_Menu();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                back_Menu_btn.setIcon(back_Menu_E_Image);
                back_Menu_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back_Menu_btn.setIcon(back_Menu_Image);
            }
        });
    }
    //

    public class menuKeyListner extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {


            switch (e.getKeyCode()){
                case KeyEvent.VK_DOWN:
                    possionPoint+=1;
                    if(possionPoint==6) possionPoint=1;
                    possionPoint1();
                    possionPoint2();
                    possionPoint3();
                    possionPoint4();
                    possionPoint5();
                    break;
                case  KeyEvent.VK_UP:
                    possionPoint-=1;
                    if(possionPoint==0) possionPoint=5;
                    possionPoint1();
                    possionPoint2();
                    possionPoint3();
                    possionPoint4();
                    possionPoint5();
                    break;
                case KeyEvent.VK_ENTER:
                    switch (possionPoint){
                        case 1:
                            setVisible(false);
                            new Setting_Menu_Size();
                            break;
                        case 2:
                            setVisible(false);
                            new Setting_Menu_Keyset();
                            break;
                        case 3:
                            setVisible(false);
                            new Setting_Menu_ScoreReset();
                            break;
                        case 4:
                            setVisible(false);
                            new Setting_Menu_ColorSet();
                            break;
                        case 5:
                            setVisible(false);
                            new Setting_Menu_AllReset();
                            break;
                    }
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    setVisible(false);
                    new Start_Menu();
                    break;

            }
        }
    }

    public void possionPoint1(){
        if(possionPoint==1) screen_Size_btn.setIcon(screen_Size_E_Image);
        else screen_Size_btn.setIcon(screen_Size_Image);
    }
    public void possionPoint2(){
        if(possionPoint==2) key_Setting_btn.setIcon(key_Setting_E_Image);
        else key_Setting_btn.setIcon(key_Setting_Image);
    }
    public void possionPoint3(){
        if(possionPoint==3) score_Reset_btn.setIcon(score_Reset_E_Image);
        else score_Reset_btn.setIcon(score_Reset_Image);
    }
    public void possionPoint4(){
        if(possionPoint==4) color_blindness_btn.setIcon(color_blindness_E_Image);
        else color_blindness_btn.setIcon(color_blindness_Image);
    }
    public void possionPoint5(){
        if(possionPoint==5) all_Reset_btn.setIcon(all_Reset_E_Image);
        else all_Reset_btn.setIcon(all_Reset_Image);
    }
    public void settingScreen_btn(){
        screen_Size_btn.setBounds(setting_Button_x,120,100,40);
        screen_Size_btn.setBorderPainted(false);
        screen_Size_btn.setContentAreaFilled(false);
        screen_Size_btn.setFocusPainted(false);
        possionPoint1();
        add(screen_Size_btn);

        key_Setting_btn.setBounds(setting_Button_x,180,100,40);
        key_Setting_btn.setBorderPainted(false);
        key_Setting_btn.setContentAreaFilled(false);
        key_Setting_btn.setFocusPainted(false);
        add(key_Setting_btn);

        score_Reset_btn.setBounds(setting_Button_x,240,100,40);
        score_Reset_btn.setBorderPainted(false);
        score_Reset_btn.setContentAreaFilled(false);
        score_Reset_btn.setFocusPainted(false);
        add(score_Reset_btn);

        color_blindness_btn.setBounds(setting_Button_x,300,100,40);
        color_blindness_btn.setBorderPainted(false);
        color_blindness_btn.setContentAreaFilled(false);
        color_blindness_btn.setFocusPainted(false);
        add(color_blindness_btn);

        all_Reset_btn.setBounds(setting_Button_x,360,100,40);
        all_Reset_btn.setBorderPainted(false);
        all_Reset_btn.setContentAreaFilled(false);
        all_Reset_btn.setFocusPainted(false);
        add(all_Reset_btn);
    }

}
