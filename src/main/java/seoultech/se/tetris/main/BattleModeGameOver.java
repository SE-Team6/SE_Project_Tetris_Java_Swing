package seoultech.se.tetris.main;

import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.menu.GetSetting;
import seoultech.se.tetris.menu.SetDefault;
import seoultech.se.tetris.menu.StartMenu;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleModeGameOver extends JFrame {

    public  static int gameOverTitleX,scoreBoardX,scoreBoardY,scoreBoardWidth,scoreBoardHeight, scoreAndNameLabelX,textFiledX,ButtonX;

    private ImageIcon StartMenuBtnBasicImage = new ImageIcon(getClass().getResource("/image/Button/gameover_btn/StartMenuBtn_B.jpg"));
    private ImageIcon StartMenuBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameover_btn/StartMenuBtn_E.jpg"));
    private ImageIcon ExitGameBtnBasicImage = new ImageIcon(getClass().getResource("/image/Button/gameover_btn/ExitGameBtn_B.jpg"));
    private ImageIcon ExitGameBtnEnterImage = new ImageIcon(getClass().getResource("/image/Button/gameover_btn/ExitGameBtn_E.jpg"));


    private JLabel gameOverTitle = new JLabel(new ImageIcon(getClass().getResource("/image/Label/title/GameOverTitle.png")),SwingConstants.CENTER);
    private JLabel myScoreLabel = new JLabel("1P Score");
    private JLabel NameLabel = new JLabel("2P Score");
    private JPanel scoreBoardSummary = new JPanel();


    private JLabel Score2P;
    private JLabel Score1P;
    private JButton StartMenuButton = new JButton(StartMenuBtnBasicImage);
    private JButton ExitGameButton = new JButton(ExitGameBtnBasicImage);


    SetDefault bs;
    private int score;


    public BattleModeGameOver(){}

    public BattleModeGameOver(int x, int y){
        bs = new SetDefault(x, y);
        setXY(400);
        bs.setVisible(true);
        score = 0;
        labelSet();
        buttonSet();
        System.out.println(ButtonX);
    }
    public void buttonSet(){
        StartMenuButton.setBounds(ButtonX,320,185,55);
        StartMenuButton.setBorderPainted(false);
        StartMenuButton.setContentAreaFilled(false);
        StartMenuButton.setFocusPainted(false);
        StartMenuButton.setRolloverIcon(StartMenuBtnEnterImage);
        StartMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bs.setVisible(false);
                new StartMenu(bs.getX(), bs.getY());
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
        myScoreLabel.setFont(new Font("Bahnschrift",Font.BOLD,20));
        myScoreLabel.setBounds(scoreAndNameLabelX,120,100,40);
        myScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myScoreLabel.setForeground(Color.YELLOW);

        bs.add(myScoreLabel);

        Score1P = new JLabel(String.valueOf(score));
        Score1P.setFont(new Font("Bahnschrift",Font.BOLD,20));
        Score1P.setBounds(textFiledX,120,100,40);
        Score1P.setHorizontalAlignment(SwingConstants.CENTER);
        Score1P.setOpaque(true);
        Score1P.setBackground(Color.BLACK);
        Score1P.setForeground(Color.RED);
        Score1P.setBorder(new LineBorder(Color.RED,1,true));
        bs.add(Score1P);

        NameLabel.setFont(new Font("Bahnschrift",Font.BOLD,20));
        NameLabel.setBounds(scoreAndNameLabelX,180,100,40);
        NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        NameLabel.setForeground(Color.YELLOW);
        bs.add(NameLabel);

        Score2P = new JLabel(String.valueOf(score));
        Score2P.setFont(new Font("Bahnschrift",Font.BOLD,20));
        Score2P.setBounds(textFiledX,180,100,40);
        Score2P.setHorizontalAlignment(SwingConstants.CENTER);
        Score2P.setOpaque(true);
        Score2P.setBackground(Color.BLACK);
        Score2P.setForeground(Color.RED);
        Score2P.setBorder(new LineBorder(Color.RED,1,true));
        bs.add(Score2P);

        bs.gameTitle.setVisible(false);
        gameOverTitle.setBounds(gameOverTitleX, 30,400,100);
        bs.add(gameOverTitle);
    }

    public void setXY(int num){ // 해상도 바뀔때 각 라벨 및 버튼 위치 설정.
        GetSetting ver =new GetSetting();
        switch (num){
            case 400:
                ButtonX=210;
                scoreAndNameLabelX=195;
                textFiledX=295;
                System.out.println("hello");
                ver.gameOverFirstSet();
                break;
            case 600:
                ver.gameOverSecondSet();
                break;
            case 800:
                ver.gameOverThirdSet();
                break;
        }
    }

}
