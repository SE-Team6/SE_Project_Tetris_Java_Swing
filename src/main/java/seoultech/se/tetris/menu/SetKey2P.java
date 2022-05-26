package seoultech.se.tetris.menu;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import static seoultech.se.tetris.menu.SetDefault.screenWidth;
import static seoultech.se.tetris.component.JSONLoader.loaderKey;
import static seoultech.se.tetris.menu.SetDefault.key;
import static seoultech.se.tetris.menu.GetSetting.*;
import static seoultech.se.tetris.menu.SetDefault.*;

public class SetKey2P {
    public static int labelWidth=200,labelHeight=40,labelFontSize=40;
    public static int textFieldWidth=120, textFieldHeight =40,textFieldFontSize=30;
    private int[] labelY = {120,170,220,270,320,370};// 0:left/1:right/2:up/3:down/4:esc/5:space

    private String [] textSequence = {"LEFT","RIGHT","UP","DOWN","ESC","SPACE"};
    private JLabel[] getLabel = new JLabel[6]; // 0:left/1:right/2:up/3:down/4:esc/5:space
    private JLabel[] setLabel =  new JLabel[6];
    private JButton saveButton = new JButton("SAVE");

    private LineBorder selectBorder = new LineBorder(Color.BLUE,3,true);
    private LineBorder noBorder = new LineBorder(Color.BLACK,3,true);

    SetDefault bs;
    BackMenuBtn bm = new BackMenuBtn();

    public static int positionPoint =0;
    char [] keyLoadCharValue = new char[6];
    String [] keyLoadStringValue = new String[6];
    JSONObject obj = loaderKey();
    Object [] var = new Object[6];
    public SetKey2P(){}

    public SetKey2P(int x, int y){

        bs = new SetDefault(x, y);
        bs.setVisible(true);
        keyLoad();
        setLocation();
        setLabel();
        getKeyLabel();
        bs.add(bm.backMenuBtn);
        bs.addKeyListener(new setKeyListener());
        backToMenuBtnAction();
        System.out.println(labelX);
    }
    public class setKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyVal= e.getKeyCode();
            if (keyVal==key.DOWN) {
                positionPoint +=1;
                if(positionPoint ==6) positionPoint =0;
                setBtnImage();
            }
            else if(keyVal == key.UP){
                positionPoint -=1;
                if(positionPoint == -1) positionPoint =5;
                setBtnImage();
            }
            else if(keyVal== KeyEvent.VK_ENTER){
                if(positionPoint<6) {
                    new GetKeyPanel(bs.getX(), bs.getY());
                    bs.setVisible(false);
                }
            }
            else if (keyVal==KeyEvent.VK_BACK_SPACE){
                bs.setVisible(false);
                new SetKeyMenu(bs.getX(), bs.getY());
            }
        }
    }
    public void setBtnImage(){
        for (int i=0;i<6;i++){
            if(positionPoint==i) getLabel[i].setBorder(selectBorder);
            else getLabel[i].setBorder(noBorder);
        }
        if(positionPoint==6) saveButton.setBackground(Color.RED);
        else saveButton.setBackground(Color.white);
    }
    public void setLabel(){ // 라벨 세팅
        for (int i=0;i<6;i++){
            setLabel[i]=new JLabel(textSequence[i]);
            setLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,labelFontSize));
            setLabel[i].setBounds(labelX,labelY[i],labelWidth,labelHeight);
            setLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            setLabel[i].setForeground(Color.YELLOW);
            bs.add(setLabel[i]);
        }
    }
    public void getKeyLabel(){
        for (int i=0;i<6;i++){
            getLabel[i]= new JLabel();
            getLabel[i].setBounds(textFieldX,labelY[i],textFieldWidth,textFieldHeight);
            getLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,textFieldFontSize));
            getLabel[i].setOpaque(true);
            getLabel[i].setBackground(Color.BLACK);
            getLabel[i].setForeground(Color.RED);
            getLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            int num= Integer.parseInt(var[i].toString());
            switch (num){
                case 37: // left
                    getLabel[i].setText("Left");
                    break;
                case 39: // Right
                    getLabel[i].setText("Right");
                    break;
                case 38: // Up
                    getLabel[i].setText("Up");
                    break;
                case 40: // Down
                    getLabel[i].setText("Down");
                    break;
                case 27: // Esc
                    getLabel[i].setText("Esc");
                    break;
                case 32: // Space
                    getLabel[i].setText("Space");
                    break;
                default:
                    char value = (char)num;
                    keyLoadCharValue[i]=value;
                    getLabel[i].setText(String.valueOf(keyLoadCharValue[i]));
            }
            bs.add(getLabel[i]);
        }
        setBtnImage();
    }
    public void keyLoad(){// 기존 키 정보 불러오기?
        Arrays.fill(var,0);
        for (int i =0;i<6;i++){
            var[i]=obj.get(textSequence[i]);
        }
    }
    public void backToMenuBtnAction(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new SetKeyMenu(bs.getX(), bs.getY());
            }
        });
    }
    public void setLocation(){
        GetSetting gs = new GetSetting();
        switch (screenWidth){
            case 400:
                gs.keySetFirstSet();
                break;
            case 600:
                gs.keySetSecondSet();
                break;
            case 800:
                gs.keySetThirdSet();
                break;

        }
    }
}
