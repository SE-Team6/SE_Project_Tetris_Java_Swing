package seoultech.se.tetris.Menu;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static seoultech.se.tetris.Menu.BasicSet.Width;
import static seoultech.se.tetris.component.JSONLoader.loaderKey;
import static seoultech.se.tetris.component.JSONWriter.writeKey;

public class SettingMenuKeySet extends JFrame{

    private int labelX=20,labelWidth=200,labelHeight=40,labelFontSize=40;
    private int labelLeftY=120,labelRightY=170,labelUpY=220,labelDownY=270,labelEscY=320,labelSpaceY=370;

    private int textFieldX=200,textFieldWidth=80, textFieldHeight =30,textFieldFontSize=30;
    private int textFieldLeftY=120,textFieldRightY=170,textFieldUpY=220,textFieldDownY=270,textFieldEscY=320,textFieldSpaceY=370;


    private  JLabel leftSet = new JLabel("LEFT");
    private  JLabel rightSet = new JLabel("RIGHT");
    private  JLabel upSet = new JLabel("UP");
    private  JLabel downSet = new JLabel("DOWN");
    private  JLabel escSet = new JLabel("ESC");
    private  JLabel spaceSet = new JLabel("SPACE");
    private JTextField leftGet = new JTextField();
    private JTextField rightGet = new JTextField();
    private JTextField upGet = new JTextField();
    private JTextField downGet = new JTextField();
    private JTextField escGet = new JTextField();
    private JTextField spaceGet = new JTextField();

    String [] keyGetValue = new String[6];
    int [] keyWriteValue = new int[6];
    char [] keyLoadCharValue = new char[6];



    private JButton keyUpdateButton = new JButton();
    BasicSet bs = new BasicSet();
    BackMenu bm = new BackMenu();

    public SettingMenuKeySet(){
        setLocation();
        keyLoad();
        setLabel();
        keyUpdate();
        getKeyTextField();
        bs.add(bm.backMenuBtn);
        backToMenu();
    }

    public void setLabel(){ // 라벨 세팅
        leftSet.setFont(new Font("Bahnschrift",Font.BOLD,labelFontSize));
        leftSet.setBounds(labelX,labelLeftY,labelWidth,40);
        leftSet.setHorizontalAlignment(SwingConstants.CENTER);
        leftSet.setForeground(Color.YELLOW);
        bs.add(leftSet);

        rightSet.setFont(new Font("Bahnschrift",Font.BOLD,labelFontSize));
        rightSet.setBounds(labelX,labelRightY,labelWidth,40);
        rightSet.setHorizontalAlignment(SwingConstants.CENTER);
        rightSet.setForeground(Color.YELLOW);
        bs.add(rightSet);

        upSet.setFont(new Font("Bahnschrift",Font.BOLD,labelFontSize));
        upSet.setBounds(labelX,labelUpY,labelWidth,labelHeight);
        upSet.setHorizontalAlignment(SwingConstants.CENTER);
        upSet.setForeground(Color.YELLOW);
        bs.add(upSet);

        downSet.setFont(new Font("Bahnschrift",Font.BOLD,labelFontSize));
        downSet.setBounds(labelX,labelDownY,labelWidth,labelHeight);
        downSet.setHorizontalAlignment(SwingConstants.CENTER);
        downSet.setForeground(Color.YELLOW);
        bs.add(downSet);

        spaceSet.setFont(new Font("Bahnschrift",Font.BOLD,labelFontSize));
        spaceSet.setBounds(labelX,labelSpaceY,labelWidth,labelHeight);
        spaceSet.setHorizontalAlignment(SwingConstants.CENTER);
        spaceSet.setForeground(Color.YELLOW);
        bs.add(spaceSet);

        escSet.setFont(new Font("Bahnschrift",Font.BOLD,40));
        escSet.setBounds(labelX,labelEscY,labelWidth,labelHeight);
        escSet.setHorizontalAlignment(SwingConstants.CENTER);
        escSet.setForeground(Color.YELLOW);
        bs.add(escSet);
    }
    public void getKeyTextField(){// 텍스트필드 세팅
        leftGet.setBounds(textFieldX,textFieldLeftY,textFieldWidth,textFieldHeight);
        leftGet.setBackground(Color.BLACK);
        leftGet.setForeground(Color.RED);
        leftGet.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leftGet.setHorizontalAlignment(SwingConstants.CENTER);
        leftGet.setFont(new Font("Bahnschrift",Font.BOLD,textFieldFontSize));
        leftGet.setText(String.valueOf(keyLoadCharValue[0]));
        bs.add(leftGet);

        rightGet.setBounds(textFieldX,textFieldRightY,textFieldWidth,textFieldHeight);
        rightGet.setBackground(Color.BLACK);
        rightGet.setForeground(Color.RED);
        rightGet.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        rightGet.setHorizontalAlignment(SwingConstants.CENTER);
        rightGet.setFont(new Font("Bahnschrift",Font.BOLD,textFieldFontSize));
        rightGet.setText(String.valueOf(keyLoadCharValue[1]));
        bs.add(rightGet);

        upGet.setBounds(textFieldX,textFieldUpY,textFieldWidth,textFieldHeight);
        upGet.setBackground(Color.BLACK);
        upGet.setForeground(Color.RED);
        upGet.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        upGet.setHorizontalAlignment(SwingConstants.CENTER);
        upGet.setFont(new Font("Bahnschrift",Font.BOLD,textFieldFontSize));
        upGet.setText(String.valueOf(keyLoadCharValue[2]));
        bs.add(upGet);

        downGet.setBounds(textFieldX,textFieldDownY,textFieldWidth,textFieldHeight);
        downGet.setBackground(Color.BLACK);
        downGet.setForeground(Color.RED);
        downGet.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        downGet.setHorizontalAlignment(SwingConstants.CENTER);
        downGet.setFont(new Font("Bahnschrift",Font.BOLD,textFieldFontSize));
        downGet.setText(String.valueOf(keyLoadCharValue[3]));
        bs.add(downGet);

        spaceGet.setBounds(textFieldX,textFieldSpaceY,textFieldWidth,textFieldHeight);
        spaceGet.setBackground(Color.BLACK);
        spaceGet.setForeground(Color.RED);
        spaceGet.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        spaceGet.setHorizontalAlignment(SwingConstants.CENTER);
        spaceGet.setFont(new Font("Bahnschrift",Font.BOLD,textFieldFontSize));
        spaceGet.setText(String.valueOf(keyLoadCharValue[4]));
        bs.add(spaceGet);

        escGet.setBounds(textFieldX,textFieldEscY,textFieldWidth,textFieldHeight);
        escGet.setBackground(Color.BLACK);
        escGet.setForeground(Color.RED);
        escGet.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        escGet.setHorizontalAlignment(SwingConstants.CENTER);
        escGet.setFont(new Font("Bahnschrift",Font.BOLD,30));
        escGet.setText(String.valueOf(keyLoadCharValue[5]));
        bs.add(escGet);
    }
    public void keyUpdate(){ // 업데이트 버튼 (디자인 아직 못함)
        keyUpdateButton.setBounds(300,120,50,280);
        keyUpdateButton.setVisible(true);
        keyUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//기존에 사용하던 키를 알기위해
                keyGetValue[0]=leftGet.getText();
                keyGetValue[1]=rightGet.getText();
                keyGetValue[2]=upGet.getText();
                keyGetValue[3]=downGet.getText();
                keyGetValue[4]=spaceGet.getText();
                keyGetValue[5]=escGet.getText();
                ketWrite();
                JOptionPane.showMessageDialog(null,"키 설정이 변경되었습니다.");
            }
        });
        bs.add(keyUpdateButton);

    }
    public void keyLoad(){// 기존 키 정보 불러오기
        JSONObject obj = loaderKey();
        Object [] var = new Object[6];
        var[0]= obj.get("LEFT");
        var[1]= obj.get("RIGHT");
        var[2]= obj.get("UP");
        var[3]= obj.get("DOWN");
        var[4]= obj.get("ESC");
        var[5]= obj.get("SPACE");
        for(int i=0;i<6;i++){
            int a= Integer.parseInt(var[i].toString());
            char b = (char)a;
            keyLoadCharValue[i]=b;
            System.out.println(keyLoadCharValue[i]);
        }
    }
    public void ketWrite(){//json에 바꾼 키 입력
        for(int i=0;i<6;i++){
            char tmp= keyGetValue[i].charAt(0);
            keyWriteValue[i]=(int)tmp;
        }
        writeKey(keyWriteValue);
    }
    public void backToMenu(){
        bm.backMenuBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bs.setVisible(false);
                new SettingMenu();
            }
        });
    }
    public void setLocation(){// 해상도에 따른 버튼 라벨 텍스트필드 등 위치 설정 불러오기
        Version ver =new Version();
        switch (Width){
            case 400:
                ver.firstKeySetScreen();
            case 600:
                ver.secondKeySetScreen();
            case 800:
                ver.thirdScreenSizeSet();
        }

    }
}
