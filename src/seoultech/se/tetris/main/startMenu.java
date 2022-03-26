package seoultech.se.tetris.main;

import seoultech.se.tetris.component.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//시작메뉴입니다.
//방향키로 이동, ENTER키로 선택

public class startMenu extends JFrame{

    JLabel title = new JLabel("테트리스 게임");
    JLabel arrow = new JLabel(("<<<"));

    JLabel b1 = new JLabel("게임 시작");
    JLabel b2 = new JLabel("스코어 보드");
    JLabel b3 = new JLabel("설정");
    JLabel b4 = new JLabel("게임 종료");

    startMenu()  {
        add(title);
        add(arrow);
        add (b1);
        add (b2);
        add (b3);
        add (b4);

        title.setBounds(150, 60, 100, 40);
        arrow.setBounds(300, 180, 100, 40);
        b1.setBounds(150, 180, 100, 40);
        b2.setBounds(150, 240, 100, 40);
        b3.setBounds(150, 300, 100, 40);
        b4.setBounds(150, 360, 100, 40);


        setTitle("시작 메뉴 화면");
        addKeyListener(new MyKeyListener());
        setLayout(null);
        setLocation(new Point(700, 350));
        setVisible(true);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       requestFocus();
    }

    class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    if(arrow.getY()>180)
                    {
                        arrow.setLocation(arrow.getX(), arrow.getY() - 60);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(arrow.getY()<360)
                    {
                        arrow.setLocation(arrow.getX(), arrow.getY() + 60);
                    }

                    break;
                case KeyEvent.VK_ENTER:
                    if(b1.getY()==arrow.getY())
                    {
                        //게임 시작
                        Board main = new Board();
                        main.setSize(400, 500);
                        main.setVisible(true);
                        main.setLocation(new Point(700, 350));
                        setVisible(false);
                    }
                    else if(b2.getY()==arrow.getY())
                    {
                        //스코어 보드
                    }
                    else if(b3.getY()==arrow.getY())
                    {
                        //설정
                    }
                    else if(b4.getY()==arrow.getY())
                    {
                        //게임 종료
                        System.exit(0);
                    }


            }








        }
    }


}
