package seoultech.se.tetris.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;

import static seoultech.se.tetris.component.JSONLoader.loaderResolution;

public class BasicSet extends JFrame{

    public static int buttonSizeX = 145, buttonSizeY=45;
    public static int buttonX, buttonY =130;
    public static int Width, Height;
    public static int menuBarHeight =20;
    public static int menuBarExitBtnWidth =380, menuBarExitBtnHeight =0;
    public static int gameTitleX, gameTitleY =30;

    private Image screenImage;
    private Graphics screenGraphics;
    // 기본 이미지
    private Image background = new ImageIcon("src/main/resources/image/backGround/800x1000_BG.jpg").getImage();
    public JLabel gameTitle = new JLabel(new ImageIcon("src/main/resources/image/Label/title/title.png"),SwingConstants.CENTER);

    //상단 메뉴바 이미지
    public JLabel menuBar = new JLabel(new ImageIcon("src/main/resources/image/Label/menubar/third_menubar.jpg"));
    private ImageIcon menuBarExitBasicImage = new ImageIcon("src/main/resources/image/Button/static_btn/ExitButton_Basic.png");
    private ImageIcon menuBarExitEnterImage = new ImageIcon("src/main/resources/image/Button/static_btn/ExitButton_Enter.png");

    public JButton menuBarExitBtn = new JButton(menuBarExitBasicImage);

    private int mouseX, mouseY;



    public BasicSet(){
        callSize();
        buttonX =(Width-buttonSizeX)/2;
        gameTitleX=(Width-300)/2;
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




        gameTitle.setBounds(gameTitleX, gameTitleY,300,50);
        add(gameTitle);

        //상단메뉴바 종료 설정
        menuBarExitBtn.setBounds(Width-20, menuBarExitBtnHeight,20,20);
        menuBarExitBtn.setBorderPainted(false);
        menuBarExitBtn.setContentAreaFilled(false);
        menuBarExitBtn.setFocusPainted(false);
        menuBarExitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menuBarExitBtn.setIcon(menuBarExitEnterImage);
                menuBarExitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuBarExitBtn.setIcon(menuBarExitBasicImage);
            }
        });
        add(menuBarExitBtn);

        //상단메뉴바 설정
        menuBar.setBounds(0, 0, Width, menuBarHeight);
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
    public void callSize(){
        HashMap<String, Integer> map = loaderResolution();
        System.out.println(map.keySet());
        Width = map.get("width");
        Height = map.get("height");
    }
}