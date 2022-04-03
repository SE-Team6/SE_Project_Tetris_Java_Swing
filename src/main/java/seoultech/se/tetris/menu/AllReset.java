package seoultech.se.tetris.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static seoultech.se.tetris.component.JSONWriter.*;
import static seoultech.se.tetris.menu.SettingMenu.*;

public class AllReset extends JFrame {

    private JLabel ask = new JLabel("정말로 설정을 초기화 하시겠습니까?");
    private JButton yesReset = new JButton("Yes");
    private JButton noReset = new JButton("No");
    private int [] keyValue = {37,39,38,40,27,32};

    public AllReset(){
        setVisible(true);
        setSize(400,100);
        setFocusable(true);
        setResizable(false); // 한번 만들어진 게임창은 사용자가 임의적으로 못바꿈
        setLocationRelativeTo(null); // 게임창이 컴퓨터 정중앙에 뜨도록
        setLayout(null);
        ask.setBounds(50,0,300,30);
        ask.setHorizontalAlignment(SwingConstants.CENTER);
        add(ask);
        yesReset.setBounds(80,30,80,30);
        add(yesReset);
        yesReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeResolution(400,600,18);
                writeKey(keyValue);
                writeColorMode(0);
                JOptionPane.showMessageDialog(null,"설정이 초기화 되었습니다(게임을 재시작 해주세요)");
                System.exit(0);
            }
        });
        noReset.setBounds(240,30,80,30);
        noReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(noReset);
    }



}
