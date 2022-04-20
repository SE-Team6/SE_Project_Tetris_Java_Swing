package seoultech.se.tetris.menu;

import java.awt.*;
import java.awt.event.*;

public class IntroTest extends Frame{
    Image img[] = new Image[8];
    /*8개의 이미지 객체를 선언함.*/
    static int num = 0;
    public IntroTest(){
        for(int i = 0; i < 8; i++){
            img[i] = Toolkit.getDefaultToolkit().getImage("src/main/resources/image/Test/step"+(i)+".jpg");
        }
        addWindowListener(new WindowHandler());
        setLocation(100,100);
        setSize(400, 500);
        setVisible(true);
        num=0;
        while(num<8){
            try{
                Thread.sleep(500);
                repaint();
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());
                break;
            }
        }
        setVisible(false);
        new StartMenu(getX(), getY());
    }
    public void paint(Graphics g){
        if(num > 7)num = 0;
        g.drawImage(img[num++], 0, 0, this);
    }
    class WindowHandler extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }
}
