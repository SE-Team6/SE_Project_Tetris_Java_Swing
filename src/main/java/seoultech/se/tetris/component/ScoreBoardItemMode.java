package seoultech.se.tetris.component;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static seoultech.se.tetris.component.JSONLoader.loaderScoreBoardPage;
import static seoultech.se.tetris.component.JSONWriter.writeScoreBoardPage;

public class ScoreBoardItemMode extends JFrame {
    private Image backGround = new ImageIcon("src/main/resources/image/backGround/ScoreBoard/ScoreBoard_BG.jpg").getImage();
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel[] label = new JLabel[5];

    private JLabel[]  rankLabel = new JLabel[10];
    private JLabel[]  nameLabel = new JLabel[10];
    private JLabel[]  scoreLabel = new JLabel[10];
    private JLabel[]  dateLabel = new JLabel[10];
    private JLabel[]  difficultyLabel = new JLabel[10];
    private JLabel pageNum;
    private int rankNum=0; //
    private int scoreBoardNum =0;//스코어 정보를 불러오기 위한
    private int page = 1;
    private JButton rightPageBtn = new JButton();
    private JButton leftPageBtn = new JButton();
    private ImageIcon rightPageButtonImg =  new ImageIcon("src/main/resources/image/Button/ScoreBoard/RightButton.png");
    private ImageIcon leftPageButtonImg =  new ImageIcon("src/main/resources/image/Button/ScoreBoard/LeftButton.png");
    int [] sbListSize={30,120,50,50,50};
    int [] sbListX={0,30,150,200,250};
    int [] currentRank={0,10,20,30,40,50,60,70,80,90};
    int [] test = {1,11,21,31,41,51,61,71,81,91};
    Keyboard key = Keyboard.getInstance();
    public ScoreBoardItemMode(){
        setting();
        pageButton();
        scorePanel();
        frame.addKeyListener(new pageControl());
        loadScoreBoard(scoreBoardNum);
        pageNumLabel();
    }
    public void setting(){
        JPanel bg = new JPanel(){
            public void paintComponent(Graphics g){
                g.drawImage(backGround,0,0,null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        frame.setContentPane(bg);
        frame.setSize(400,600);
        frame.setFocusable(true);
        frame.setResizable(false); // 한번 만들어진 게임창은 사용자가 임의적으로 못바꿈
        frame.setLocationRelativeTo(null); // 게임창이 컴퓨터 정중앙에 뜨도록
        frame.setLayout(null);
        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void scorePanel(){
        panel.setBounds(50,100,300,420);
        panel.setBorder(new LineBorder(Color.RED,2,true));
        panel.setLayout(null);
        frame.add(panel);
        scoreBoardList();
    }
    public void scoreBoardList(){
        String [] sbList = {"Rank","Name","Score","Date","Difficulty"};
        for(int i=0;i<5;i++){
            label[i] = new JLabel(sbList[i]);
            label[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            label[i].setBorder(new LineBorder(Color.RED,1,true));
            label[i].setBounds(sbListX[i],0,sbListSize[i],40);
            label[i].setHorizontalAlignment(SwingConstants.CENTER);
            label[i].setForeground(Color.BLACK);
            panel.add(label[i]);
        }
        int LabelY=38;
        for (int i=0;i<10;i++){
            rankLabel[i] =new JLabel(String.valueOf(currentRank[loaderScoreBoardPage()]+i+1));
            rankLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            rankLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            rankLabel[i].setBounds(sbListX[0],LabelY,sbListSize[0],40);
            rankLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            rankLabel[i].setForeground(Color.BLACK);
            panel.add(rankLabel[i]);

            nameLabel[i] =new JLabel();
            nameLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            nameLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            nameLabel[i].setBounds(sbListX[1],LabelY,sbListSize[1],40);
            nameLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            nameLabel[i].setForeground(Color.BLACK);
            panel.add(nameLabel[i]);

            scoreLabel[i] =new JLabel();
            scoreLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            scoreLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            scoreLabel[i].setBounds(sbListX[2],LabelY,sbListSize[2],40);
            scoreLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            scoreLabel[i].setForeground(Color.BLACK);
            panel.add(scoreLabel[i]);

            dateLabel[i] =new JLabel();
            dateLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            dateLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            dateLabel[i].setBounds(sbListX[3],LabelY,sbListSize[3],40);
            dateLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            dateLabel[i].setForeground(Color.BLACK);
            panel.add(dateLabel[i]);

            difficultyLabel[i] =new JLabel();
            difficultyLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            difficultyLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            difficultyLabel[i].setBounds(sbListX[4],LabelY,sbListSize[4],40);
            difficultyLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            difficultyLabel[i].setForeground(Color.BLACK);
            panel.add(difficultyLabel[i]);
            LabelY+=38;
        }
    }
    public void pageNumLabel(){
        pageNum = new JLabel(page+"/10");
        pageNum.setFont(new Font("Bahnschrift",Font.BOLD,12));
        pageNum.setBounds(180,520,40,40);
        pageNum.setHorizontalAlignment(SwingConstants.CENTER);
        pageNum.setForeground(Color.RED);
        frame.add(pageNum);
    }
    public void pageButton(){
        rightPageBtn = new JButton(rightPageButtonImg);
        rightPageBtn.setBounds(220,520,50,50);
        rightPageBtn.setBorderPainted(false);
        rightPageBtn.setContentAreaFilled(false);
        rightPageBtn.setFocusPainted(false);
        rightPageBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int a= loaderScoreBoardPage() +1;
                frame.setVisible(false);
                if(a==10) a=9;
                writeScoreBoardPage(a);
                new ScoreBoardItemMode();
            }
        });
        frame.add(rightPageBtn);
        leftPageBtn = new JButton(leftPageButtonImg);
        leftPageBtn.setBounds(130,520,50,50);
        leftPageBtn.setBorderPainted(false);
        leftPageBtn.setContentAreaFilled(false);
        leftPageBtn.setFocusPainted(false);
        leftPageBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int a= loaderScoreBoardPage() -1;
                frame.setVisible(false);
                if(a==-1) a=0;
                writeScoreBoardPage(a);
                new ScoreBoardItemMode();
            }
        });
        frame.add(leftPageBtn);
    }
    public void loadScoreBoard(int a){
        JSONArray res = JSONLoader.loaderScore();
        ArrayList<JSONObject> arr = JSONWriter.JSONArrayToArrayList(res);
        int j=0;
        for (int i=a;i<a+10;i++){
            nameLabel[j].setText((String) arr.get(i).get("Name"));
            scoreLabel[j].setText(String.valueOf(arr.get(i).get("Score")));
            dateLabel[j].setText((String) arr.get(i).get("DateTime"));
            String difficultyVal = String.valueOf(arr.get(i).get("Difficulty"));
            difficultyLabel[j].setText(difficultyLabelSet(difficultyVal));
            j+=1;
        }
    }
    public String difficultyLabelSet(String a){ // 임시
        int to = Integer.parseInt(a);
        if(to==49) return "Easy";
        else if(to==48) return "Normal";
        else if(to==47) return "Hard";
        else return "hello";
    }
    public class pageControl extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int keyValue = e.getKeyCode();
            if (keyValue==key.LEFT){
                scoreBoardNum -=10;
                page-=1;
                if(scoreBoardNum <0) {
                    scoreBoardNum = 0;
                    page=1;
                }
                loadScoreBoard(scoreBoardNum); // 스코어 정보 불러오기
                pageNum.setText(String.valueOf(page+"/10")); // 현재 페이지 출력
                for (int i=0;i<10;i++){
                    rankLabel[i].setText(String.valueOf(scoreBoardNum+1+i));// 순위 설정
                }
            }
            else if(keyValue==key.RIGHT){
                scoreBoardNum +=10;
                page+=1;
                if(scoreBoardNum >90) {
                    scoreBoardNum =90;
                    page=10;
                }
                loadScoreBoard(scoreBoardNum);
                pageNum.setText(String.valueOf(page+"/10")); //페이지
                for (int i=0;i<10;i++){
                    rankLabel[i].setText(String.valueOf(scoreBoardNum+i+1)); // 순위
                }
            }
        }
    }

}
