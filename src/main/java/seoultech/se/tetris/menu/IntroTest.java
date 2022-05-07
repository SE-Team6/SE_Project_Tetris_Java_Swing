package seoultech.se.tetris.menu;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IntroTest extends Frame{
    Image img[] = new Image[8];
    /*8개의 이미지 객체를 선언함.*/
    static int num = 0;
    public IntroTest(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icon.ico")));
        for(int i = 0; i < 7; i++){
            img[i] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/Test/step" + i + ".jpg"));
        }
        System.out.println(getClass().getResource("/image/Test/step0.jpg"));
        addWindowListener(new WindowHandler());
        setSize(400, 500);
        setVisible(true);
        setLocationRelativeTo(null);
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
