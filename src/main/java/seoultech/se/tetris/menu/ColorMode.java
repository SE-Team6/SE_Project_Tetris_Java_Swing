package seoultech.se.tetris.menu;

import seoultech.se.tetris.blocks.ParentBlock;
import seoultech.se.tetris.main.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import static seoultech.se.tetris.component.JSONLoader.loaderColor;
import static seoultech.se.tetris.component.JSONWriter.writeColorMode;

public class ColorMode extends JFrame {

    //색맹 모드 노말모드,적록색맹모드,청황색맹모드

    private JLabel notice = new JLabel("원하시는 모드를 클릭해주세요");
    private JButton[] colorMode= new JButton[3];
    private  String[] colorModeText = {"일반 모드","적록 색맹","청황 색맹"};
    private int colorNum= loaderColor();
    private String currentMode="";
    public ColorMode(){
        URL urlIcon = Tetris.class.getResource("/image/icon/icon.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image imgIcon = kit.createImage(urlIcon);
        setIconImage(imgIcon);
        setVisible(true);
        setSize(400,100);
        setFocusable(true);
        setResizable(false); // 한번 만들어진 게임창은 사용자가 임의적으로 못바꿈
        setLocationRelativeTo(null); // 게임창이 컴퓨터 정중앙에 뜨도록
        setLayout(null);
        setColorMode();
        setButton();
        notice.setBounds(0,0,400,30);
        notice.setHorizontalAlignment(SwingConstants.CENTER);
        add(notice);
        notice.setFont(new Font("Sans Serif",Font.BOLD,12));
        notice.setText("현재 "+currentMode+"를 사용중이십니다 변경을 원하시는 모드를 선택하세요");
    }

    public void setButton(){
        int addX = 0;
        for (int i=0;i<3;i++){
            colorMode[i] = new JButton(colorModeText[i]);
            colorMode[i].setFont(new Font("Sans Serif",Font.BOLD,10));
            colorMode[i].setBounds(40+addX,30,80,30);
            addX +=120;
            add(colorMode[i]);
            int cn = i;
            colorMode[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    writeColorMode(cn);
                    ParentBlock.setColorType(cn);
                    JOptionPane.showMessageDialog(null,"모드가 변경되었습니다.");
                    setVisible(false);
                }
            });
        }
    }

    public void setColorMode(){
        switch (colorNum){
            case 0:
                currentMode="일반모드";
                break;
            case 1:
                currentMode="적록색맹모드";
                break;
            case 2:
                currentMode="청황색맹모드";
                break;
        }
    }
}
