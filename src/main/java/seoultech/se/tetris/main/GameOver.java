package seoultech.se.tetris.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import seoultech.se.tetris.component.JSONLoader;
import seoultech.se.tetris.component.JSONWriter;
import seoultech.se.tetris.component.Score;
import seoultech.se.tetris.component.ScoreBoardItemMode;
import seoultech.se.tetris.menu.BasicSet;
import seoultech.se.tetris.menu.StartMenu;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static seoultech.se.tetris.component.JSONLoader.loaderScoreBoardPage;
import static seoultech.se.tetris.component.JSONWriter.appendScore;
import static seoultech.se.tetris.menu.BasicSet.*;
import static seoultech.se.tetris.menu.GameDifficulty.gameDifficultyNum;
import static seoultech.se.tetris.menu.GameMode.gameModeNum;

public class GameOver extends JFrame {

    private int gameOverTitleX,gameOverTitleY;
    private int scoreBoardX,scoreBoardY;
    private int scoreBoardWidth,scoreBoardHeight;
    private int labelX;
    private int textFiledX;
    private int ButtonX;
    private ImageIcon updateBtnBasicImage = new ImageIcon("src/main/resources/image/Button/gameover_btn/updateBtn_B.jpg");
    private ImageIcon updateBtnEnterImage = new ImageIcon("src/main/resources/image/Button/gameover_btn/updateBtn_E.jpg");
    private ImageIcon StartMenuBtnBasicImage = new ImageIcon("src/main/resources/image/Button/gameover_btn/StartMenuBtn_B.jpg");
    private ImageIcon StartMenuBtnEnterImage = new ImageIcon("src/main/resources/image/Button/gameover_btn/StartMenuBtn_E.jpg");
    private ImageIcon ExitGameBtnBasicImage = new ImageIcon("src/main/resources/image/Button/gameover_btn/ExitGameBtn_B.jpg");
    private ImageIcon ExitGameBtnEnterImage = new ImageIcon("src/main/resources/image/Button/gameover_btn/ExitGameBtn_E.jpg");
    private Image backGround;


    private JLabel gameOverTitle = new JLabel(new ImageIcon("src/main/resources/image/Label/title/GameOverTitle.png"),SwingConstants.CENTER);
    private JLabel myScoreLabel = new JLabel("Score");
    private JLabel NameLabel = new JLabel("Name");
    private JPanel scoreBoardSummary = new JPanel();

    private JLabel[]  rankLabel = new JLabel[10];
    private JLabel[]  nameLabel = new JLabel[10];
    private JLabel[]  scoreLabel = new JLabel[10];

    private JLabel[] scoreBoardLabel = new JLabel[3];
    private JTextField myName= new JTextField();
    private JLabel myScore;
    private JButton updateButton = new JButton(updateBtnBasicImage);
    private JButton StartMenuButton = new JButton(StartMenuBtnBasicImage);
    private JButton ExitGameButton = new JButton(ExitGameBtnBasicImage);

    BasicSet bs = new BasicSet();
    Score sc = new Score();
    private  int score;
    public GameOver(){

        bs.setVisible(true);
        score =sc.getScore();
        setXY(Width);
        labelSet();
        buttonSet();
        scoreBoardPanel();
        scoreBoardSet();
    }

    public void scoreBoardPanel(){
        JPanel scoreBoardSummaryPanel = new JPanel(){
            public void paintComponent(Graphics g){
                g.drawImage(backGround,0,0,null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        scoreBoardSummary.setBounds(scoreBoardX,scoreBoardY,150,330);
        scoreBoardSummary.setBorder(new LineBorder(Color.RED,1,true));
        scoreBoardSummary.setLayout(null);

        scoreBoardSummaryPanel.setBounds(25,120,scoreBoardWidth,scoreBoardHeight);
        scoreBoardSummaryPanel.setBorder(new LineBorder(Color.RED,2,true));
        scoreBoardSummaryPanel.setLayout(null);

        scoreBoardLabel();
        scoreBoardSummaryPanel.add(scoreBoardSummary);
        bs.add(scoreBoardSummaryPanel);
    }
    public void scoreBoardSet(){
        JSONArray res = JSONLoader.loaderScore();
        ArrayList<JSONObject> arr = JSONWriter.JSONArrayToArrayList(res);
        int j=0;
        for (int i=0;i<10;i++){
            nameLabel[j].setText((String) arr.get(i).get("Name"));
            scoreLabel[j].setText(String.valueOf(arr.get(i).get("Score")));
            j+=1;
        }
    }

    public void scoreBoardLabel(){
        String [] sbList = {"Rank","Name","Score"};
        int [] sbListX={0,30,110};
        int [] sbListSize={30,80,40};
        for(int i=0;i<3;i++){
            scoreBoardLabel[i] = new JLabel(sbList[i]);
            scoreBoardLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            scoreBoardLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            scoreBoardLabel[i].setBounds(sbListX[i],0,sbListSize[i],30);
            scoreBoardLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            scoreBoardLabel[i].setForeground(Color.BLACK);
            scoreBoardSummary.add(scoreBoardLabel[i]);
        }
        int LabelY=30;
        for (int i=0;i<10;i++) {
            rankLabel[i] = new JLabel(String.valueOf(i + 1));
            rankLabel[i].setFont(new Font("Bahnschrift", Font.BOLD, 10));
            rankLabel[i].setBorder(new LineBorder(Color.RED, 1, true));
            rankLabel[i].setBounds(sbListX[0], LabelY, sbListSize[0], 32);
            rankLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            rankLabel[i].setForeground(Color.BLACK);
            scoreBoardSummary.add(rankLabel[i]);

            nameLabel[i] =new JLabel();
            nameLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            nameLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            nameLabel[i].setBounds(sbListX[1],LabelY,sbListSize[1],32);
            nameLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            nameLabel[i].setForeground(Color.BLACK);
            scoreBoardSummary.add(nameLabel[i]);

            scoreLabel[i] =new JLabel();
            scoreLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            scoreLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            scoreLabel[i].setBounds(sbListX[2],LabelY,sbListSize[2],32);
            scoreLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            scoreLabel[i].setForeground(Color.BLACK);
            scoreBoardSummary.add(scoreLabel[i]);
            LabelY+=30;
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
                String[] updateArr= {myName.getText(),"2022/04/17", String.valueOf(score), String.valueOf(gameDifficultyNum),String.valueOf(gameModeNum)};//{"Name", "DateTime", "Score", "Difficulty", "isItem"}
                appendScore(updateArr);
                new ScoreBoardItemMode();
                updateButton.setVisible(false);
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
        myScore.setBorder(new LineBorder(Color.RED,1,true));
        bs.add(myScore);

        NameLabel.setFont(new Font("Bahnschrift",Font.BOLD,25));
        NameLabel.setBounds(labelX,180,100,40);
        NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        NameLabel.setForeground(Color.YELLOW);
        bs.add(NameLabel);

        myName.setBounds(textFiledX,180,100,40);
        myName.setBackground(Color.BLACK);
        myName.setForeground(Color.RED);
        myName.setBorder(new LineBorder(Color.RED,1,true));
//        myName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        bs.add(myName);

        bs.gameTitle.setVisible(false);
        gameOverTitle.setBounds(gameOverTitleX, 30,400,100);
        bs.add(gameOverTitle);
    }

    public void setXY(int num){ // 해상도 바뀔때 각 라벨 및 버튼 위치 설정.
        switch (num){
            case 400:
                backGround= new ImageIcon("src/main/resources/image/backGround/GameOver/scoreBoardsummary.jpg").getImage();
                gameOverTitleX=0;
                scoreBoardWidth=170;
                scoreBoardHeight=430;
                labelX=195;
                textFiledX=295;
                ButtonX=210;
                scoreBoardX=10;
                scoreBoardY=50;
                break;
            case 600:
                backGround= new ImageIcon("src/main/resources/image/backGround/GameOver/scoreBoardsummary_600.jpg").getImage();
                gameOverTitleX=100;
                scoreBoardWidth=300;
                scoreBoardHeight=630;
                labelX=370;
                textFiledX=470;
                ButtonX=385;
                scoreBoardX=20;
                scoreBoardY=100;
                break;
            case 800:
                backGround= new ImageIcon("src/main/resources/image/backGround/GameOver/scoreBoardsummary_800.jpg").getImage();
                gameOverTitleX=200;
                scoreBoardWidth=500;
                scoreBoardHeight=800;
                labelX=585;
                textFiledX=685;
                ButtonX=600;
                scoreBoardX=40;
                scoreBoardY=150;
                break;
        }
    }

}
