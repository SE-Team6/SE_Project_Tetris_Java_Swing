package seoultech.se.tetris.Menu;

import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static seoultech.se.tetris.Menu.Start_Menu.*;
import static seoultech.se.tetris.Menu.Start_Menu.menubar_Height;
//import static seoultech.se.tetris.component.Board.Keyset;

public class Setting_Menu_Keyset extends JFrame{
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

    //키 설정 버튼
    private ImageIcon key_Set1_Image = new ImageIcon(Tetris.class.getResource("../image/Button/setting_Menu_btn/key_set_btn/set1_B.jpg"));
    private ImageIcon key_Set2_Image = new ImageIcon(Tetris.class.getResource("../image/Button/setting_Menu_btn/key_set_btn/set2_B.jpg"));
    private ImageIcon key_Set3_Image = new ImageIcon(Tetris.class.getResource("../image/Button/setting_Menu_btn/key_set_btn/set3_B.jpg"));

    private ImageIcon key_Set1_E_Image = new ImageIcon(Tetris.class.getResource("../image/Button/setting_Menu_btn/key_set_btn/set1_E.jpg"));
    private ImageIcon key_Set2_E_Image = new ImageIcon(Tetris.class.getResource("../image/Button/setting_Menu_btn/key_set_btn/set2_E.jpg"));
    private ImageIcon key_Set3_E_Image = new ImageIcon(Tetris.class.getResource("../image/Button/setting_Menu_btn/key_set_btn/set3_E.jpg"));

    private  JButton key_Set1_btn= new JButton(key_Set1_Image);
    private  JButton key_Set2_btn= new JButton(key_Set2_Image);
    private  JButton key_Set3_btn= new JButton(key_Set3_Image);

    public Setting_Menu_Keyset(){
        possionPoint=1;
        basic_set();
        back_Menu();
        Setting_Menu_Keyset_btn();
        addKeyListener(new menuKeyListner());
    }

    //기본설정
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


    //버튼
    public void Setting_Menu_Keyset_btn(){
        key_Set1_btn.setBounds(startbuttonx,start_button_y,145,45);
        key_Set1_btn.setBorderPainted(false);
        key_Set1_btn.setContentAreaFilled(false);
        key_Set1_btn.setFocusPainted(false);
        possionPoint_1();
        add(key_Set1_btn);

        key_Set2_btn.setBounds(startbuttonx,start_button_y+70,145,45);
        key_Set2_btn.setBorderPainted(false);
        key_Set2_btn.setContentAreaFilled(false);
        key_Set2_btn.setFocusPainted(false);
        add(key_Set2_btn);

        key_Set3_btn.setBounds(startbuttonx,start_button_y+140,145,45);
        key_Set3_btn.setBorderPainted(false);
        key_Set3_btn.setContentAreaFilled(false);
        key_Set3_btn.setFocusPainted(false);
        add(key_Set3_btn);
    }

    //키 이벤트
    public class menuKeyListner extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key= e.getKeyCode();
            if (key == KeyEvent.VK_DOWN){
                possionPoint+=1;
                if(possionPoint==4) possionPoint=1;
                possionPoint_1();
                possionPoint_2();
                possionPoint_3();
            }
            else if(key == KeyEvent.VK_UP){
                possionPoint-=1;
                if(possionPoint==0) possionPoint=3;
                possionPoint_1();
                possionPoint_2();
                possionPoint_3();

            }
//            else if(key == KeyEvent.VK_ENTER){
//                switch (possionPoint) {
//                    case 1:
//                        Keyset=1;
//                        JOptionPane.showMessageDialog(null,"키 설정이 변경되었습니다.");
//                        break;
//                    case 2:
//                        Keyset=2;
//                        JOptionPane.showMessageDialog(null,"키 설정이 변경되었습니다.");
//                        break;
//                    case 3:
//                        Keyset=3;
//                        JOptionPane.showMessageDialog(null,"키 설정이 변경되었습니다.");
//                        break;
//                }
//
//            }
        }
    }

    public void possionPoint_1(){
        if(possionPoint==1) key_Set1_btn.setIcon(key_Set1_E_Image);
        else key_Set1_btn.setIcon(key_Set1_Image);
    }
    public void possionPoint_2(){
        if(possionPoint==2) key_Set2_btn.setIcon(key_Set2_E_Image);
        else key_Set2_btn.setIcon(key_Set2_Image);
    }
    public void possionPoint_3(){
        if(possionPoint==3) key_Set3_btn.setIcon(key_Set3_E_Image);
        else key_Set3_btn.setIcon(key_Set3_Image);
    }
}
