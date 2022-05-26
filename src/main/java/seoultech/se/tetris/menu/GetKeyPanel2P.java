package seoultech.se.tetris.menu;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static seoultech.se.tetris.component.JSONLoader.loaderKey;
import static seoultech.se.tetris.component.JSONWriter.writeKey;
import static seoultech.se.tetris.menu.SetKey2P.positionPoint;

public class GetKeyPanel2P extends JFrame {

    private Image backGround = new ImageIcon(getClass().getResource("/image/backGround/testBackground.jpg")).getImage();
    private JButton updateKey = new JButton("UPDATE");
    private JLabel getKeyLabel = new JLabel();

    String [] keyGetValue = new String[6];
    int [] keyWriteValue = new int[6];
    char [] keyLoadCharValue = new char[6];
    int checkValue=0;
    int prevX, prevY;
    // 보이는 방향키 및 스페이스 바와 ESC=> {"SPACE":9251,"DOWN":8595,"LEFT":8592,"ESC":9099,"RIGHT":8594,"UP":8593} => 시각적으로 보일때 쓰일 코드 // 내가 입력하는 코드
    // 실제 아스키 코드 =>{"SPACE":32,"DOWN":40,"LEFT":37,"ESC":27,"RIGHT":39,"UP":38}=> 실제 json 파일에 입력되어야할 코드 // 내가 입력한 코드가 변해야할 코드

    public GetKeyPanel2P(){}

    public GetKeyPanel2P(int x, int y){
        prevX = x;
        prevY = y;
        setting();
        keyLoad();
        addKeyListener(new setKeyListener());
    }
    public class setKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyVal= e.getKeyCode();
            if(keyVal == KeyEvent.VK_ENTER){
                Action();
            }
            else{
                getKeyLabel.setText(e.getKeyText(keyVal));
                switch (getKeyLabel.getText()){
                    case "Left":
                        checkValue = 37;
                        break;
                    case "Right":
                        checkValue  = 39;
                        break;
                    case "Up":
                        checkValue  = 38;
                        break;
                    case "Down":
                        checkValue  = 40;
                        break;
                    case "Escape":
                        checkValue  = 27;
                        break;
                    case "Space":
                        checkValue  = 32;
                        break;
                    default:
                        checkValue=(int)getKeyLabel.getText().charAt(0);
//                        checkValue=checkingValue(checkValue);
                }
            }
        }
    }
    public void keyWrite(){//json에 바꾼 키 입력
        switch (keyGetValue[positionPoint]){
            case "Left":
                keyWriteValue[positionPoint] = 37;
                break;
            case "Right":
                keyWriteValue[positionPoint] = 39;
                break;
            case "Up":
                keyWriteValue[positionPoint] = 38;
                break;
            case "Down":
                keyWriteValue[positionPoint] = 40;
                break;
            case "Escape":
                keyWriteValue[positionPoint] = 27;
                break;
            case "Space":
                keyWriteValue[positionPoint] = 32;
                break;
            default:
                char tmp= keyGetValue[positionPoint].charAt(0);
                keyWriteValue[positionPoint]=(int)tmp;
        }
        writeKey(keyWriteValue,2);
    }
    public void keyLoad(){// 중복을 검사하기 위한 기존 키 정보 불러오기
        JSONObject obj = loaderKey();
        Object [] var = new Object[6];
        var[0]= obj.get("LEFT");
        var[1]= obj.get("RIGHT");
        var[2]= obj.get("UP");
        var[3]= obj.get("DOWN");
        var[4]= obj.get("ESC");
        var[5]= obj.get("SPACE");
        for(int i=0;i<6;i++){
            keyWriteValue[i]= Integer.parseInt(var[i].toString());
            char b = (char)keyWriteValue[i];
            keyLoadCharValue[i]=b;
        }
    }
    public void setting(){
        JPanel bg = new JPanel(){
            public void paintComponent(Graphics g){
                g.drawImage(backGround,0,0,null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        setContentPane(bg);
        setSize(300,100);
        setVisible(true);
        setFocusable(true);
        setResizable(false); // 한번 만들어진 게임창은 사용자가 임의적으로 못바꿈
        setLocationRelativeTo(null); // 게임창이 컴퓨터 정중앙에 뜨도록
        setBackground(Color.BLACK);
        setLayout(null);
        setTitle("키를 입력하시고 엔터를 누르세요");
        add(updateKey);
        updateKey.setBounds(190,10,100,50);
        updateKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });
        getKeyLabel.setBounds(10,10,170,50);
        getKeyLabel.setFont(new Font("Bahnschrift",Font.BOLD,30));
        getKeyLabel.setOpaque(true);
        getKeyLabel.setBackground(Color.BLACK);
        getKeyLabel.setForeground(Color.BLUE);
        getKeyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(getKeyLabel);
    }
    public void Action(){
        if(checkValue==0) JOptionPane.showMessageDialog(null,"변경하실 키를 입력해주세요");
        else {
            int count = 0;
            for (int i = 0; i < 6; i++) {
                if (positionPoint == i) {
                } else if (checkValue == keyLoadCharValue[i]) count += 1;
            }
            if (count > 0) JOptionPane.showMessageDialog(null, "중복된 키가 존재합니다 다시 세팅해주세요");
            else {
                switch (positionPoint) {
                    case 0:
                        keyGetValue[0] = getKeyLabel.getText();
                    case 1:
                        keyGetValue[1] = getKeyLabel.getText();
                    case 2:
                        keyGetValue[2] = getKeyLabel.getText();
                    case 3:
                        keyGetValue[3] = getKeyLabel.getText();
                    case 4:
                        keyGetValue[4] = getKeyLabel.getText();
                    case 5:
                        keyGetValue[5] = getKeyLabel.getText();
                }
                keyWrite();
                JOptionPane.showMessageDialog(null, "키 변경이 완료되었습니다.");
                setVisible(false);
                new SetKey2P(prevX, prevY);
            }
        }
    }
    public int checkingValue(int value){
        switch (value){//내가 시각적으로 원하는 방향키와 space 및 esc 키를 입력하면 실제 사용되는 아스키코드로 처리가 안되므로 그에대한 처리
            case 8592://left
                value=37;
                break;
            case 8594://right
                value=39;
                break;
            case 8593://up
                value=38;
                break;
            case 8595://down
                value=40;
                break;
            case 9099://esc
                value=27;
                break;
            case 9251://space
                value=32;
                break;
            default:
        }
        return value;
    }
    public int checkingValue2(String value){
        int num= Integer.parseInt(value);
        switch (value){//내가 시각적으로 원하는 방향키와 space 및 esc 키를 입력하면 실제 사용되는 아스키코드로 처리가 안되므로 그에대한 처리
            case "Left"://left
                num=37;
                break;
            case "Right"://right
                num=39;
                break;
            case "Up"://up
                num=38;
                break;
            case "Down"://down
                num=40;
                break;
            case "Escape"://esc
                num=27;
                break;
            case "space"://space
                num=32;
                break;
            default:
        }
        return num;
    }
}


