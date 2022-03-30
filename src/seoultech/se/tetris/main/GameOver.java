package seoultech.se.tetris.main;

import seoultech.se.tetris.Menu.BasicSet;
import seoultech.se.tetris.Menu.StartMenu;
import seoultech.se.tetris.Menu.Version;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static seoultech.se.tetris.Menu.BasicSet.*;

public class GameOver extends JFrame {

    private int gameOverTitleX,gameOverTitleY;
    private int scoreBoardX,scoreBoardY;
    private int scoreBoardWidth,scoreBoardHeight;
    private int labelX;
    private int textFiledX;
    private int ButtonX;
    private ImageIcon updateBtnBasicImage = new ImageIcon(Tetris.class.getResource("../image/Button/gameover_btn/updateBtn_B.jpg"));
    private ImageIcon updateBtnEnterImage = new ImageIcon(Tetris.class.getResource("../image/Button/gameover_btn/updateBtn_E.jpg"));
    private ImageIcon StartMenuBtnBasicImage = new ImageIcon(Tetris.class.getResource("../image/Button/gameover_btn/StartMenuBtn_B.jpg"));
    private ImageIcon StartMenuBtnEnterImage = new ImageIcon(Tetris.class.getResource("../image/Button/gameover_btn/StartMenuBtn_E.jpg"));
    private ImageIcon ExitGameBtnBasicImage = new ImageIcon(Tetris.class.getResource("../image/Button/gameover_btn/ExitGameBtn_B.jpg"));
    private ImageIcon ExitGameBtnEnterImage = new ImageIcon(Tetris.class.getResource("../image/Button/gameover_btn/ExitGameBtn_E.jpg"));



    private JPanel scoreBoardSummary = new JPanel();
    private JLabel gameOverTitle = new JLabel(new ImageIcon(Tetris.class.getResource("../image/Label/title/GameOverTitle.png")),SwingConstants.CENTER);
    private JLabel myScoreLabel = new JLabel("Score");
    private JLabel NameLabel = new JLabel("Name");
    private JTextField myName= new JTextField();
    private JLabel myScore;
    private JButton updateButton = new JButton(updateBtnBasicImage);
    private JButton StartMenuButton = new JButton(StartMenuBtnBasicImage);
    private JButton ExitGameButton = new JButton(ExitGameBtnBasicImage);

    BasicSet bs = new BasicSet();
    private  int score =100;
    public GameOver(){

        setXY(Width);
        labelSet();
        buttonSet();
        scoreBoardSummary.setBounds(25,120,scoreBoardWidth,scoreBoardHeight);
        bs.add(scoreBoardSummary);
    }
    public void setXY(int num){ // 해상도 바뀔때 각 라벨 및 버튼 위치 설정.
        switch (num){
            case 400:
                gameOverTitleX=0;
                scoreBoardWidth=170;
                scoreBoardHeight=330;
                labelX=195;
                textFiledX=295;
                ButtonX=210;
                break;
            case 600:
                gameOverTitleX=100;
                scoreBoardWidth=300;
                scoreBoardHeight=630;
                labelX=370;
                textFiledX=470;
                ButtonX=385;
                break;
            case 800:
                gameOverTitleX=200;
                scoreBoardWidth=500;
                scoreBoardHeight=800;
                labelX=585;
                textFiledX=685;
                ButtonX=600;
                break;
        }
    }
    public void buttonSet(){
        updateButton.setBounds(ButtonX,230,185,40);
        updateButton.setBorderPainted(false);
        updateButton.setContentAreaFilled(false);
        updateButton.setFocusPainted(false);
        updateButton.setRolloverIcon(updateBtnEnterImage);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //추후 업데이트 누르면 스코어보드 객체가 실행되서 한번더 강조
                JOptionPane.showMessageDialog(null,"점수가 업데이트 되었습니다");
            }
        });
        bs.add(updateButton);

        StartMenuButton.setBounds(ButtonX,320,185,55);
        StartMenuButton.setBorderPainted(false);
        StartMenuButton.setContentAreaFilled(false);
        StartMenuButton.setFocusPainted(false);
        StartMenuButton.setRolloverIcon(StartMenuBtnEnterImage);
        StartMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bs.setVisible(false);
                new StartMenu();
            }
        });
        bs.add(StartMenuButton);

        ExitGameButton.setBounds(ButtonX,390,185,55);
        ExitGameButton.setBorderPainted(false);
        ExitGameButton.setContentAreaFilled(false);
        ExitGameButton.setFocusPainted(false);
        ExitGameButton.setRolloverIcon(ExitGameBtnEnterImage);
        ExitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bs.add(ExitGameButton);
    }

    public void labelSet(){
        myScoreLabel.setFont(new Font("Bahnschrift",Font.BOLD,25));
        myScoreLabel.setBounds(labelX,120,100,40);
        myScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myScoreLabel.setForeground(Color.YELLOW);
        bs.add(myScoreLabel);

        myScore = new JLabel(String.valueOf(score));
        myScore.setFont(new Font("Bahnschrift",Font.BOLD,20));
        myScore.setBounds(textFiledX,120,100,40);
        myScore.setHorizontalAlignment(SwingConstants.CENTER);
        myScore.setOpaque(true);
        myScore.setBackground(Color.BLACK);
        myScore.setForeground(Color.RED);
        bs.add(myScore);

        NameLabel.setFont(new Font("Bahnschrift",Font.BOLD,25));
        NameLabel.setBounds(labelX,180,100,40);
        NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        NameLabel.setForeground(Color.YELLOW);
        bs.add(NameLabel);

        myName.setBounds(textFiledX,180,100,40);
        myName.setBackground(Color.BLACK);
        myName.setForeground(Color.RED);
        myName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        bs.add(myName);

        bs.gameTitle.setVisible(false);
        gameOverTitle.setBounds(gameOverTitleX, 30,400,100);
        bs.add(gameOverTitle);
    }

}
