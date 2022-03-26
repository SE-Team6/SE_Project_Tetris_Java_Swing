package seoultech.se.tetris.Menu;

import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static seoultech.se.tetris.Menu.Start_Menu.*;


public class Setting_Menu_Size extends JFrame {
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

    //해상도 이미지
    private  ImageIcon first_screensize_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/size_set_btn/400x500.jpg"));
    private  ImageIcon second_screensize_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/size_set_btn/600x800.jpg"));
    private  ImageIcon third_screensize_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/size_set_btn/800x1000.jpg"));

    private  ImageIcon first_screensize_E_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/size_set_btn/400x500_E.jpg"));
    private  ImageIcon second_screensize_E_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/size_set_btn/600x800_E.jpg"));
    private  ImageIcon third_screensize_E_Image = new ImageIcon(Tetris.class.getResource("../image//Button/setting_Menu_btn/size_set_btn/800x1000_E.jpg"));

    private  JButton first_screensize_btn = new JButton(first_screensize_Image);
    private  JButton second_screensize_btn = new JButton(second_screensize_Image);
    private  JButton third_screensize_btn = new JButton(third_screensize_Image);

    Version ver = new Version();

    public Setting_Menu_Size(){
        possionPoint =1;
        basic_set();
        settingScreen_Size_btn();
        back_Menu();
        addKeyListener(new menuKeyListner());
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
                new Setting_Menu();
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
    public void settingScreen_Size_btn(){
        first_screensize_btn.setBounds(setting_Button_x,120,100,40);
        first_screensize_btn.setBorderPainted(false);
        first_screensize_btn.setContentAreaFilled(false);
        first_screensize_btn.setFocusPainted(false);
        possionPoint1();
        add(first_screensize_btn);

        second_screensize_btn.setBounds(setting_Button_x,180,100,40);
        second_screensize_btn.setBorderPainted(false);
        second_screensize_btn.setContentAreaFilled(false);
        second_screensize_btn.setFocusPainted(false);
        add(second_screensize_btn);

        third_screensize_btn.setBounds(setting_Button_x,240,100,40);
        third_screensize_btn.setBorderPainted(false);
        third_screensize_btn.setContentAreaFilled(false);
        third_screensize_btn.setFocusPainted(false);
        add(third_screensize_btn);

    }
    public class menuKeyListner extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_DOWN:
                    possionPoint+=1;
                    if(possionPoint==4) possionPoint=1;
                    possionPoint1();
                    possionPoint2();
                    possionPoint3();
                    break;
                case  KeyEvent.VK_UP:
                    possionPoint-=1;
                    if(possionPoint==0) possionPoint=3;
                    possionPoint1();
                    possionPoint2();
                    possionPoint3();
                    break;
                case KeyEvent.VK_ENTER:
                    switch (possionPoint){
                        case 1:
                            ver.first_Screensize_Set();
                            screensize_set();
                            break;
                        case 2:
                            ver.second_Screensize_Set();
                            screensize_set();
                            break;
                        case 3:
                            ver.third_Screensize_Set();
                            screensize_set();
                            break;
                    }
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    setVisible(false);
                    new Setting_Menu();
                    break;
            }
        }
    }

    public void possionPoint1(){
        if(possionPoint==1) first_screensize_btn.setIcon(first_screensize_E_Image);
        else first_screensize_btn.setIcon(first_screensize_Image);
    }
    public void possionPoint2(){
        if(possionPoint==2) second_screensize_btn.setIcon(second_screensize_E_Image);
        else second_screensize_btn.setIcon(second_screensize_Image);
    }
    public void possionPoint3(){
        if(possionPoint==3) third_screensize_btn.setIcon(third_screensize_E_Image);
        else third_screensize_btn.setIcon(third_screensize_Image);
    }
    public void screensize_set(){
        setVisible(false);
        setSize(Width,Height);
        setVisible(true);
        menuBar.setBounds(0, 0, menubar_Width, menubar_Height);
        menubar_Exit_btn.setBounds(menubar_Exit_btn_Width,menubar_Exit_btn_Height,20,20);
        gametitle.setBounds(gametitle_x,gametitle_y,300,50);
        first_screensize_btn.setBounds(setting_Button_x,120,100,40);
        second_screensize_btn.setBounds(setting_Button_x,180,100,40);
        third_screensize_btn.setBounds(setting_Button_x,240,100,40);
    }
}
