package seoultech.se.tetris.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static seoultech.se.tetris.component.JSONWriter.resetScore;

public class ScoreReset extends JFrame {

    private JLabel ask = new JLabel("정말로 스코어보드를 초기화 하시겠습니까?");
    private JButton yesReset = new JButton("Yes");
    private JButton noReset = new JButton("No");
    public ScoreReset(){
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
                resetScore();
                JOptionPane.showMessageDialog(null,"스코어보드가 초기화 되었습니다.");
                setVisible(false);
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
