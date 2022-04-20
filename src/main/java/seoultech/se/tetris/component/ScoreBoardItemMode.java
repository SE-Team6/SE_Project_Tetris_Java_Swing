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

import static seoultech.se.tetris.component.JSONLoader.getJSONObject;
import static seoultech.se.tetris.component.JSONWriter.JSONArrayToArrayList;

public class ScoreBoardItemMode extends JFrame {
    private Image backGround = new ImageIcon("src/main/resources/image/backGround/ScoreBoard/ScoreBoard_BG.jpg").getImage();
    private ImageIcon rightPageButtonImg =  new ImageIcon("src/main/resources/image/Button/ScoreBoard/RightButton.png");
    private ImageIcon leftPageButtonImg =  new ImageIcon("src/main/resources/image/Button/ScoreBoard/LeftButton.png");

    public JFrame frame = new JFrame();
    private JPanel[] panel = new JPanel[10];
    private JLabel[] listLabel = new JLabel[5];
    private JLabel[]  rankLabel = new JLabel[100];
    private JLabel[]  nameLabel = new JLabel[100];
    private JLabel[]  scoreLabel = new JLabel[100];
    private JLabel[]  dateLabel = new JLabel[100];
    private JLabel[]  difficultyLabel = new JLabel[100];

    private JLabel pageNumLabel;
    private int scoreBoardNum =0;//스코어 정보를 불러오기 위한
    private int page = 1; // 페이지
    private JButton rightPageBtn = new JButton();
    private JButton leftPageBtn = new JButton();
    int [] sbListSize={30,100,40,90,40};
    int [] sbListX={0,30,130,170,260};

    Keyboard key = Keyboard.getInstance();
    JSONArray res = JSONLoader.loaderScore();
    ArrayList<JSONObject> arr = JSONArrayToArrayList(res);
    JSONArray loadedScores = (JSONArray) getJSONObject("score", "scoreBoard");
    ArrayList<JSONObject> allScores = JSONArrayToArrayList(loadedScores);
    public ScoreBoardItemMode(int num){
        setting();
        pageButton();
        pageNumLabel();
        if(num>0){
            higLightSB(num);
        }
        else{
            scorePanel(scoreBoardNum);
            loadScoreBoard(scoreBoardNum);
        }
        frame.addKeyListener(new pageControl());
    }
    public void setting(){ // 기본 세팅
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
    }
    public void scorePanel(int scoreBoardNum){ // 스코어보드 내용 들어갈 panel
        panel[page-1] = new JPanel();
        panel[page-1].setBounds(50,100,300,420);
        panel[page-1].setBorder(new LineBorder(Color.RED,2,true));
        panel[page-1].setLayout(null);
        frame.add(panel[page-1]);
        scoreBoardList(scoreBoardNum);
    }
    public void scoreBoardList(int scoreBoardNum){// 각 스코어보드 정보 작동 내용
        String [] sbList = {"Rank","Name","Score","Date","Difficulty"};
        for(int i=0;i<5;i++){
            listLabel[i] = new JLabel(sbList[i]);
            listLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            listLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            listLabel[i].setBounds(sbListX[i],0,sbListSize[i],40);
            listLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            listLabel[i].setForeground(Color.BLACK);
            panel[page-1].add(listLabel[i]);
        }
        int LabelY=38;
        for (int i=scoreBoardNum;i<scoreBoardNum+10;i++){
            rankLabel[i] =new JLabel(String.valueOf(i+1));
            rankLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            rankLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            rankLabel[i].setBounds(sbListX[0],LabelY,sbListSize[0],40);
            rankLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            rankLabel[i].setForeground(Color.BLACK);

            nameLabel[i] =new JLabel();
            nameLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            nameLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            nameLabel[i].setBounds(sbListX[1],LabelY,sbListSize[1],40);
            nameLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            nameLabel[i].setForeground(Color.BLACK);

            scoreLabel[i] =new JLabel();
            scoreLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            scoreLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            scoreLabel[i].setBounds(sbListX[2],LabelY,sbListSize[2],40);
            scoreLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            scoreLabel[i].setForeground(Color.BLACK);

            dateLabel[i] =new JLabel();
            dateLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,7));
            dateLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            dateLabel[i].setBounds(sbListX[3],LabelY,sbListSize[3],40);
            dateLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            dateLabel[i].setForeground(Color.BLACK);

            difficultyLabel[i] =new JLabel();
            difficultyLabel[i].setFont(new Font("Bahnschrift",Font.BOLD,10));
            difficultyLabel[i].setBorder(new LineBorder(Color.RED,1,true));
            difficultyLabel[i].setBounds(sbListX[4],LabelY,sbListSize[4],40);
            difficultyLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            difficultyLabel[i].setForeground(Color.BLACK);
            panel[page-1].add(difficultyLabel[i]);
            panel[page-1].add(dateLabel[i]);
            panel[page-1].add(scoreLabel[i]);
            panel[page-1].add(rankLabel[i]);
            panel[page-1].add(nameLabel[i]);
            LabelY+=38;
        }
    }
    public void pageNumLabel(){ //현재 페이지 작동 내용
        pageNumLabel = new JLabel(page+"/10");
        pageNumLabel.setFont(new Font("Bahnschrift",Font.BOLD,12));
        pageNumLabel.setBounds(180,520,40,40);
        pageNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pageNumLabel.setForeground(Color.RED);
        frame.add(pageNumLabel);
    }
    public void pageButton(){ // 페이지 넘김 버튼 좌우
        rightPageBtn = new JButton(rightPageButtonImg);
        rightPageBtn.setBounds(220,520,50,50);
        rightPageBtn.setBorderPainted(false);
        rightPageBtn.setContentAreaFilled(false);
        rightPageBtn.setFocusPainted(false);
        rightPageBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                keyRight();
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
                keyLeft();
            }
        });
        frame.add(leftPageBtn);
    }
    public void loadScoreBoard(int scoreBoardNum){ // 스코어븓 제이슨에서 값들을 불러와 라벨에 작성 내용
        for (int i=scoreBoardNum;i<scoreBoardNum+10;i++){
            rankLabel[i].setText(String.valueOf(i+1));// 순위 설정
        }
        for (int i=scoreBoardNum;i<scoreBoardNum+10;i++){
            nameLabel[i].setText("");
            scoreLabel[i].setText("");
            dateLabel[i].setText("");
            difficultyLabel[i].setText("");
        }
        if (allScores.size()>scoreBoardNum){
        for (int i=scoreBoardNum;i<Math.min(allScores.size(),scoreBoardNum+10);i++){ // 스코어 사이즈가 그다음 페이지의 시작 랭크보다 클때만
            nameLabel[i].setText((String) arr.get(i).get("Name"));
            scoreLabel[i].setText(String.valueOf(arr.get(i).get("Score")));
            dateLabel[i].setText((String) arr.get(i).get("DateTime"));
            String difficultyVal = String.valueOf(arr.get(i).get("Difficulty"));
            difficultyLabel[i].setText(difficultyLabelSet(difficultyVal));
         }
        }
        else{
            for (int i=scoreBoardNum;i<scoreBoardNum+10;i++){ // 크지 않다면  빈칸으로
                nameLabel[i].setText("");
                scoreLabel[i].setText("");
                dateLabel[i].setText("");
                difficultyLabel[i].setText("");
            }
        }
        pageNumLabel.setText(String.valueOf(page+"/10")); // 현재 페이지 출력
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
                keyLeft();
            }
            else if(keyValue==key.RIGHT){
                keyRight();
            }
        }
    }
    public void keyLeft(){//왼쪽 으로 넘길때 작동 내용
        panel[page-1].setVisible(false);
        scoreBoardNum -=10;
        page-=1;
        if(scoreBoardNum <0) scoreBoardNum = 0;
        if (page==0) page=1;
        scorePanel(scoreBoardNum);
        loadScoreBoard(scoreBoardNum); // 스코어 정보 불러오기
    }
    public void keyRight(){// 오른쪽으로 넘길때 작동 내용
        panel[page-1].setVisible(false);
        scoreBoardNum +=10;
        page+=1;
        if(scoreBoardNum >90) scoreBoardNum =90;
        if(page==11)page=10;
        scorePanel(scoreBoardNum);
        loadScoreBoard(scoreBoardNum);
    }
    public void higLightSB(int a){ // 점수 업데이트시에는 자기 점수 하이라이트
        int num = (a/10) *10;
        page=a/10;
        scoreBoardNum=num;
        scorePanel(scoreBoardNum);
        loadScoreBoard(scoreBoardNum);
        rankLabel[a].setOpaque(true);
        scoreLabel[a].setOpaque(true);
        dateLabel[a].setOpaque(true);
        nameLabel[a].setOpaque(true);
        difficultyLabel[a].setOpaque(true);
        rankLabel[a].setBackground(Color.YELLOW);
        scoreLabel[a].setBackground(Color.YELLOW);
        dateLabel[a].setBackground(Color.YELLOW);
        nameLabel[a].setBackground(Color.YELLOW);
        difficultyLabel[a].setBackground(Color.YELLOW);
    }
}
