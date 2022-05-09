package seoultech.se.tetris.menu;

import seoultech.se.tetris.component.Keyboard;
import seoultech.se.tetris.config.ConfigBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;

import static seoultech.se.tetris.component.JSONLoader.loaderResolution;

public class SetDefault extends JFrame {

    public static int buttonWidth = 145, buttonHeight = 45;
    public static int buttonX, buttonY = 130;
    public static int screenWidth, screenHeight;
    public static int menuBarHeight = 20;
    public static int menuBarExitBtnWidth = 380, menuBarExitBtnHeight = 0;
    public static int gameTitleX, gameTitleY = 30;

    // 기본 이미지
    private Image backGround = new ImageIcon(getClass().getResource("/image/backGround/600x800_BG.jpg")).getImage();
    public JLabel gameTitle = new JLabel(new ImageIcon(getClass().getResource("/image/Label/title/title.png")), SwingConstants.CENTER);

    //상단 메뉴바 이미지
    public JLabel menuBar = new JLabel(new ImageIcon(getClass().getResource("/image/Label/menubar/third_menubar.jpg")));
    private ImageIcon menuBarExitBasicImage = new ImageIcon(getClass().getResource("/image/Button/static_btn/ExitButton_Basic.png"));
    private ImageIcon menuBarExitEnterImage = new ImageIcon(getClass().getResource("/image/Button/static_btn/ExitButton_Enter.png"));
    public JButton menuBarExitBtn = new JButton(menuBarExitBasicImage);



    private int mouseX, mouseY;


    public static Keyboard key = Keyboard.getInstance();

    public SetDefault(){}

    public SetDefault(int x, int y) {
        key.setKey();
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(backGround, 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        setContentPane(panel);
        getResolution();
        basicSet(x, y);
        basicBtnSet();
    }
    public void getResolution() {
        HashMap<String, Integer> map = loaderResolution();
        screenWidth = map.get("width");
        screenHeight = map.get("height");
        ConfigBlock.setFontSize(map.get("font_size"));
    }
    public void basicSet(int x, int y) {
        buttonX = (screenWidth - buttonWidth) / 2;
        gameTitleX = (screenWidth - 300) / 2;
        setUndecorated(true);
        setSize(screenWidth, screenHeight);
        setResizable(false); // 한번 만들어진 게임창은 사용자가 임의적으로 못바꿈
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임창 종료시 프로그램도 종료하도록
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);
        setFocusable(true);
    }

    public void basicBtnSet() {
        gameTitle.setBounds(gameTitleX, gameTitleY, 300, 50);
        add(gameTitle);

        //상단메뉴바 종료 설정
        menuBarExitBtn.setBounds(screenWidth - 20, menuBarExitBtnHeight, 20, 20);
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
        menuBar.setBounds(0, 0, screenWidth, menuBarHeight);
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

}
