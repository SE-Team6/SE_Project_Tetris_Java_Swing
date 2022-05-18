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

public class SetKey1P extends JFrame{

    public static int labelX,labelWidth=200,labelHeight=35,labelFontSize=40;
    public static int textFieldX,textFieldWidth=80, textFieldHeight =35,textFieldFontSize=30;
    private int[] labelY = {120,170,220,270,320,370};// 0:left/1:right/2:up/3:down/4:esc/5:space

    private String [] textSequence = {"LEFT","RIGHT","UP","DOWN","ESC","SPACE"};
    private char [] arrows={'←','→','↑','↓','⎋','␣'};
    private JLabel[] getLabel = new JLabel[6]; // 0:left/1:right/2:up/3:down/4:esc/5:space
    private JLabel[] setLabel =  new JLabel[6];
    private JButton saveButton = new JButton("SAVE");

    private LineBorder selectBorder = new LineBorder(Color.RED,3,true);
    private LineBorder noBorder = new LineBorder(Color.BLACK,3,true);

    SetDefault bs;
    BackMenuBtn bm = new BackMenuBtn();

    public static int positionPoint =0;
    char [] keyLoadCharValue = new char[6];

    public SetKey1P(){}

    public SetKey1P(int x, int y){
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
                else{//중복키 처리 과정
                    int count=0;
                    for (int i=0;i<5;i++){
                        for (int j=i+1;j<6;j++){
                            if(keyLoadCharValue[i]==keyLoadCharValue[j]) {
                                count+=1;
                                getLabel[i].setText("");
                                getLabel[j].setText("");
                            }
                        }
                    }
                    if (count>0)JOptionPane.showMessageDialog(null,"중복된 키가 존재합니다 다시 세팅해주세요");
                    else JOptionPane.showMessageDialog(null,"저장이 완료되었숩니다.");
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
            getLabel[i].setText(String.valueOf(keyLoadCharValue[i]));
            bs.add(getLabel[i]);
        }
        setBtnImage();
    }
    public void keyLoad(){// 기존 키 정보 불러오기
        JSONObject obj = loaderKey();
        Object [] var = new Object[6];
        Arrays.fill(var,0);
        for (int i=0;i<6;i++){
            var[i]=obj.get(textSequence[i]);
        }
        for(int i=0;i<6;i++){
            int a= Integer.parseInt(var[i].toString());
            //json에서 받아온 아스키코드를 그대로 받으면 시각적으로 방향키와 esc 및 space가 원하는 대로 안보임 그래서 원하는대로 보기위한 처리과정
            switch (a) {
                case 37://left
                    keyLoadCharValue[i]=arrows[0];
                    break;
                case 39://right
                    keyLoadCharValue[i]=arrows[1];
                    break;
                case 38://up
                    keyLoadCharValue[i]=arrows[2];
                    break;
                case 40://down
                    keyLoadCharValue[i]=arrows[3];
                    break;
                case 27://esc
                    keyLoadCharValue[i]=arrows[4];
                    break;
                case 32://space
                    keyLoadCharValue[i]=arrows[5];
                    break;
                default:
            }
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
    public void setLocation(){// 해상도에 따른 버튼 라벨 텍스트필드 등 위치 설정 불러오기
        GetSetting ver =new GetSetting();
        switch (screenWidth){
            case 400:
                ver.keySetFirstSet();
                break;
            case 600:
                ver.keySetSecondSet();
                break;
            case 800:
                ver.keySetThirdSet();
                break;
        }
    }
}
