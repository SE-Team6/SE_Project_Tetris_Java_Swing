package seoultech.se.tetris.Menu;

import seoultech.se.tetris.component.Board;
import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;


public class Start_Menu  extends JFrame {

    public static int Width=400, Height=500;
    public static int menubar_Width=400, menubar_Height=20;
    public static int menubar_Exit_btn_Width=380, menubar_Exit_btn_Height=0;
    public static int gametitle_x=50, gametitle_y=30;
    public static int start_button_x=125,start_button_y=130;
    public static int setting_Button_x=140,setting_Button_y=120;
    public static char Version_char=1;
    private Image screenImage;
    private Graphics screenGraphics;

    // 기본 이미지
    private Image background = new ImageIcon(Tetris.class.getResource("../image/backGround/800x1000_BG.jpg")).getImage();
    private JLabel gametitle = new JLabel(new ImageIcon(Tetris.class.getResource("../image/Label/title/title.png")));

    //상단 메뉴바 이미지
    private JLabel menuBar = new JLabel(new ImageIcon(Tetris.class.getResource("../image/Label/menubar/third_menubar.jpg")));
    private ImageIcon menubar_Exit_Basic_Image = new ImageIcon(Tetris.class.getResource("../image/Button/static_btn/ExitButton_Basic.png"));
    private ImageIcon menubar_Exit_Enter_Image = new ImageIcon(Tetris.class.getResource("../image/Button/static_btn/ExitButton_Enter.png"));

    // 시작 메뉴 버튼 이미지
    private ImageIcon game_start_btn_Image = new ImageIcon(Tetris.class.getResource("../image/Button/start_Menu_btn/Game_Start_Basic.jpg"));
    private ImageIcon game_setting_btn_Image= new ImageIcon(Tetris.class.getResource("../image/Button/start_Menu_btn/Game_Setting_Basic.jpg"));
    private ImageIcon score_board_btn_Image= new ImageIcon(Tetris.class.getResource("../image/Button/start_Menu_btn/ScoreBoard_Basic.jpg"));
    private ImageIcon Exit_btn_Image= new ImageIcon(Tetris.class.getResource("../image/Button/start_Menu_btn/Exit_Basic.jpg"));


    // 선택된 시작 메뉴 버튼 이미지
    private ImageIcon game_start_btn_Enter_Image = new ImageIcon(Tetris.class.getResource("../image/Button/start_Menu_btn/Game_Start_Enter.jpg"));
    private ImageIcon game_setting_btn_Enter_Image= new ImageIcon(Tetris.class.getResource("../image/Button/start_Menu_btn/Game_Setting_Enter.jpg"));
    private ImageIcon score_board_btn_Enter_Image= new ImageIcon(Tetris.class.getResource("../image/Button/start_Menu_btn/ScoreBoard_Enter.jpg"));
    private ImageIcon Exit_btn_Enter_Image= new ImageIcon(Tetris.class.getResource("../image/Button/start_Menu_btn/Exit_Enter.jpg"));

    private JButton menubar_Exit_btn = new JButton(menubar_Exit_Basic_Image);
    private JButton game_start_btn = new JButton(game_start_btn_Image);
    private JButton game_setting_btn = new JButton(game_setting_btn_Image);
    private JButton score_board_btn = new JButton(score_board_btn_Image);
    private JButton Exit_btn = new JButton(Exit_btn_Image);

    public  static  int possionPoint = 1;
    private int mouseX, mouseY;
    Version ver = new Version();

    //시작 메뉴
    public Start_Menu(){
//        Setting_Menu_version_Read();
        call_set();
        basic_set();
        addKeyListener(new menuKeyListner());
        start_Menu_Screen_btn();
    }

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

    public class menuKeyListner extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key= e.getKeyCode();
            if (key == KeyEvent.VK_DOWN){
                possionPoint+=1;
                if(possionPoint==5) possionPoint=1;
                possionPoint_1();
                possionPoint_2();
                possionPoint_3();
                possionPoint_4();

            }
            else if(key == KeyEvent.VK_UP){
                possionPoint-=1;
                if(possionPoint==0) possionPoint=4;
                possionPoint_1();
                possionPoint_2();
                possionPoint_3();
                possionPoint_4();
            }
            else if(key == KeyEvent.VK_ENTER){
                switch (possionPoint) {
                    case 1: // 게임 시작
                        new Board();
                        setVisible(false);
                        break;
                    case 2: // 게임 설정
                        setVisible(false);
                        new Setting_Menu();
                        break;
                    case 3: // 스코어 보드
                        hideButton();
                        break;
                    case 4: // 게임종료
                        System.exit(0);
                        break;
                }

            }
        }
    }

    public void possionPoint_1(){
        if(possionPoint==1) game_start_btn.setIcon(game_start_btn_Enter_Image);
        else game_start_btn.setIcon(game_start_btn_Image);
    }
    public void possionPoint_2(){
        if(possionPoint==2) game_setting_btn.setIcon(game_setting_btn_Enter_Image);
        else game_setting_btn.setIcon(game_setting_btn_Image);
    }
    public void possionPoint_3(){
        if(possionPoint==3) score_board_btn.setIcon(score_board_btn_Enter_Image);
        else score_board_btn.setIcon(score_board_btn_Image);
    }
    public void possionPoint_4(){
        if(possionPoint==4) Exit_btn.setIcon(Exit_btn_Enter_Image);
        else Exit_btn.setIcon(Exit_btn_Image);
    }
    public void hideButton(){
        game_start_btn.setVisible(false);
        game_setting_btn.setVisible(false);
        score_board_btn.setVisible(false);
        Exit_btn.setVisible(false);
    }
    public void start_Menu_Screen_btn(){
        //시작 메뉴 버튼 4가지 설정
        game_start_btn.setBounds(start_button_x,start_button_y,145,45);
        game_start_btn.setBorderPainted(false);
        game_start_btn.setContentAreaFilled(false);
        game_start_btn.setFocusPainted(false);
        possionPoint_1();
        add(game_start_btn);

        game_setting_btn.setBounds(start_button_x,start_button_y+70,145,45);
        game_setting_btn.setBorderPainted(false);
        game_setting_btn.setContentAreaFilled(false);
        game_setting_btn.setFocusPainted(false);

        add(game_setting_btn);

        score_board_btn.setBounds(start_button_x,start_button_y+140,145,45);
        score_board_btn.setBorderPainted(false);
        score_board_btn.setContentAreaFilled(false);
        score_board_btn.setFocusPainted(false);


        add(score_board_btn);

        Exit_btn.setBounds(start_button_x,start_button_y+210,145,45);
        Exit_btn.setBorderPainted(false);
        Exit_btn.setContentAreaFilled(false);
        Exit_btn.setFocusPainted(false);
        add(Exit_btn);

    }

    public void call_set(){
        switch (Version_char){
            case '1':
                ver.first_Screensize_Set();
                break;
            case '2':
                ver.second_Screensize_Set();
            case '3':
                ver.third_Screensize_Set();
        }
    }





}
