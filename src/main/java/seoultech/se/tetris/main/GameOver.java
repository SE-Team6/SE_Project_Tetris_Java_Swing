package seoultech.se.tetris.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import seoultech.se.tetris.component.*;
import seoultech.se.tetris.menu.BasicSet;
import seoultech.se.tetris.menu.StartMenu;
import seoultech.se.tetris.menu.Version;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static seoultech.se.tetris.component.JSONLoader.getJSONObject;
import static seoultech.se.tetris.component.JSONWriter.JSONArrayToArrayList;
import static seoultech.se.tetris.component.JSONWriter.appendScore;
import static seoultech.se.tetris.menu.BasicSet.*;
import static seoultech.se.tetris.menu.GameDifficulty.gameDifficultyNum;
import static seoultech.se.tetris.menu.GameMode.gameModeNum2;
import static seoultech.se.tetris.menu.ScoreMode.gameModeNum;

public class GameOver extends JFrame {

    public  static int gameOverTitleX,scoreBoardX,scoreBoardY,scoreBoardWidth,scoreBoardHeight, scoreAndNameLabelX,textFiledX,ButtonX;

    private ImageIcon updateBtnBasicImage = new ImageIcon("src/main/resources/image/Button/gameover_btn/updateBtn_B.jpg");
    private ImageIcon updateBtnEnterImage = new ImageIcon("src/main/resources/image/Button/gameover_btn/updateBtn_E.jpg");
    private ImageIcon StartMenuBtnBasicImage = new ImageIcon("src/main/resources/image/Button/gameover_btn/StartMenuBtn_B.jpg");
    private ImageIcon StartMenuBtnEnterImage = new ImageIcon("src/main/resources/image/Button/gameover_btn/StartMenuBtn_E.jpg");
    private ImageIcon ExitGameBtnBasicImage = new ImageIcon("src/main/resources/image/Button/gameover_btn/ExitGameBtn_B.jpg");
    private ImageIcon ExitGameBtnEnterImage = new ImageIcon("src/main/resources/image/Button/gameover_btn/ExitGameBtn_E.jpg");
    public static Image gameOverBackGround;


    private JLabel gameOverTitle = new JLabel(new ImageIcon("src/main/resources/image/Label/title/GameOverTitle.png"),SwingConstants.CENTER);
    private JLabel myScoreLabel = new JLabel("Score");
    private JLabel NameLabel = new JLabel("Name");
    private JPanel scoreBoardSummary = new JPanel();

    private JLabel[]  rankLabel = new JLabel[20];
    private JLabel[]  nameLabel = new JLabel[20];
    private JLabel[]  scoreLabel = new JLabel[20];

    private JLabel[] scoreBoardLabel = new JLabel[3];
    private JTextField setMyName = new JTextField();
    private JLabel myScore;
    private JButton updateButton = new JButton(updateBtnBasicImage);
    private JButton StartMenuButton = new JButton(StartMenuBtnBasicImage);
    private JButton ExitGameButton = new JButton(ExitGameBtnBasicImage);

    public static int [] sbListX;
    public static  int [] sbListSize;
    public static  int scoreBoardPanelWidth;
    public static int scoreBoardPanelHeight;

    public static int higLightNum = 0;
    BasicSet bs = new BasicSet();
    private int score;
    public static String nowDate;
    private JSONArray res;
    private JSONArray loadedScores;

    public GameOver(){
        bs.setVisible(true);
        score = Score.score;
        System.out.println(Score.score);
        setXY(Width);
        labelSet();
        buttonSet();
        scoreBoardPanel();
        scoreBoardSet();
    }
    public void dateGet(){
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM월dd일HH시mm분ss");
        nowDate = formatter.format(now);
    }
    public void scoreBoardPanel(){
        JPanel scoreBoardSummaryPanel = new JPanel(){
            public void paintComponent(Graphics g){
                g.drawImage(gameOverBackGround,0,0,null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        scoreBoardSummary.setBounds(scoreBoardX,scoreBoardY,scoreBoardPanelWidth,scoreBoardPanelHeight);
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
        if (gameModeNum2 ==0) {
            res = JSONLoader.loaderScore("normal");
            loadedScores = (JSONArray) getJSONObject("normal", "scoreBoard");
        }
        else if(gameModeNum2 ==1) {
            res = JSONLoader.loaderScore("item");
            loadedScores = (JSONArray) getJSONObject("item", "scoreBoard");
        }
        ArrayList<JSONObject> arr = JSONWriter.JSONArrayToArrayList(res);
        ArrayList<JSONObject> allScores = JSONArrayToArrayList(loadedScores);
        int j=0;
        for (int i=0;i<Math.min(allScores.size(),20);i++){
            nameLabel[j].setText((String) arr.get(i).get("Name"));
            scoreLabel[j].setText(String.valueOf(arr.get(i).get("Score")));
            j+=1;}
    }
    public void scoreBoardLabel(){
        String [] sbList = {"Rank","Name","Score"};
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
        for (int i=0;i<20;i++) {
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
                if (setMyName.getText().isEmpty()==true) {JOptionPane.showMessageDialog(null, "이름을 입력해주세요");}
                else {
                    bs.setFocusable(false);
                    dateGet();
                    JOptionPane.showMessageDialog(null, "점수가 업데이트 되었습니다");
                    String[] updateArr = {setMyName.getText(), nowDate, String.valueOf(score), String.valueOf(gameDifficultyNum), String.valueOf(gameModeNum2)};//{"Name", "DateTime", "Score", "Difficulty", "isItem"}
                    if (gameModeNum2 == 0) {
                        res = JSONLoader.loaderScore("normal");
                        loadedScores = (JSONArray) getJSONObject("normal", "scoreBoard");
                        higLightNum = appendScore(updateArr, "normal");
                        gameModeNum = 0;
                    } else if (gameModeNum2 == 1) {
                        res = JSONLoader.loaderScore("item");
                        loadedScores = (JSONArray) getJSONObject("item", "scoreBoard");
                        higLightNum = appendScore(updateArr, "item");
                        gameModeNum = 1;
                    }
                    new ScoreBoard(higLightNum);
                    scoreBoardSet();
                    bs.setFocusable(true);
                }
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
        myScoreLabel.setBounds(scoreAndNameLabelX,120,100,40);
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
        NameLabel.setBounds(scoreAndNameLabelX,180,100,40);
        NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        NameLabel.setForeground(Color.YELLOW);
        bs.add(NameLabel);

        setMyName.setBounds(textFiledX,180,100,40);
        setMyName.setBackground(Color.BLACK);
        setMyName.setForeground(Color.RED);
        setMyName.setBorder(new LineBorder(Color.RED,1,true));
//        myName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        bs.add(setMyName);

        bs.gameTitle.setVisible(false);
        gameOverTitle.setBounds(gameOverTitleX, 30,400,100);
        bs.add(gameOverTitle);
    }

    public void setXY(int num){ // 해상도 바뀔때 각 라벨 및 버튼 위치 설정.
        Version ver =new Version();
        switch (num){
            case 400:
                ver.gameOverFirstSize();
                break;
            case 600:
                ver.gameOverSecondSize();
                break;
            case 800:
                ver.gameOverThirdSize();
                break;
        }
    }

}
