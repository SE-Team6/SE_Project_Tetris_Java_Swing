package seoultech.se.tetris.Menu;

import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static seoultech.se.tetris.Menu.StartMenu.*;
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
        positionPoint =1;
        back_Menu();
        Setting_Menu_Keyset_btn();
        addKeyListener(new menuKeyListner());
    }

    //기본설정
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
        key_Set1_btn.setBounds(buttonX, buttonY,145,45);
        key_Set1_btn.setBorderPainted(false);
        key_Set1_btn.setContentAreaFilled(false);
        key_Set1_btn.setFocusPainted(false);
        possionPoint_1();
        add(key_Set1_btn);

        key_Set2_btn.setBounds(buttonX, buttonY +70,145,45);
        key_Set2_btn.setBorderPainted(false);
        key_Set2_btn.setContentAreaFilled(false);
        key_Set2_btn.setFocusPainted(false);
        add(key_Set2_btn);

        key_Set3_btn.setBounds(buttonX, buttonY +140,145,45);
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
                positionPoint +=1;
                if(positionPoint ==4) positionPoint =1;
                possionPoint_1();
                possionPoint_2();
                possionPoint_3();
            }
            else if(key == KeyEvent.VK_UP){
                positionPoint -=1;
                if(positionPoint ==0) positionPoint =3;
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
        if(positionPoint ==1) key_Set1_btn.setIcon(key_Set1_E_Image);
        else key_Set1_btn.setIcon(key_Set1_Image);
    }
    public void possionPoint_2(){
        if(positionPoint ==2) key_Set2_btn.setIcon(key_Set2_E_Image);
        else key_Set2_btn.setIcon(key_Set2_Image);
    }
    public void possionPoint_3(){
        if(positionPoint ==3) key_Set3_btn.setIcon(key_Set3_E_Image);
        else key_Set3_btn.setIcon(key_Set3_Image);
    }
}
