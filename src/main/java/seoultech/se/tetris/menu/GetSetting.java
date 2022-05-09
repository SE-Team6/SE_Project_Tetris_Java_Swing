package seoultech.se.tetris.menu;

import javax.swing.*;
import java.util.HashMap;

import static seoultech.se.tetris.component.JSONLoader.*;

import static seoultech.se.tetris.menu.SetDefault.*;
import static seoultech.se.tetris.menu.SetKeyMenu.labelX;
import static seoultech.se.tetris.menu.SetKeyMenu.textFieldX;
import static seoultech.se.tetris.main.GameOver.*;

public class GetSetting {
    public void getFirstResolution(){
        HashMap<String, Integer>map = loaderResolution();
        screenWidth = map.get("width");
        screenHeight = map.get("height");
        menuBarExitBtnWidth= screenWidth -20;
        buttonX =(screenWidth - buttonWidth)/2;
        gameTitleX=(screenWidth -300)/2;
    }
    public void getSecondResolution(){
        HashMap<String, Integer>map = loaderResolution();
        screenWidth = map.get("width");
        screenHeight = map.get("height");
        menuBarExitBtnWidth= screenWidth -20;
        buttonX =(screenWidth - buttonWidth)/2;
        gameTitleX=(screenWidth -300)/2;
    }
    public void getThirdResolution(){
        HashMap<String, Integer>map = loaderResolution();
        screenWidth = map.get("width");
        screenHeight = map.get("height");
        menuBarExitBtnWidth= screenWidth -20;
        buttonX =(screenWidth - buttonWidth)/2;
        gameTitleX=(screenWidth -300)/2;
    }

    public void keySetFirstSet(){
        labelX =40;
        textFieldX=220;
    }
    public void keySetSecondSet(){
        labelX =140;
        textFieldX=320;
    }
    public void keySetThirdSet(){
        labelX =240;
        textFieldX=420;
    }

    public void gameOverFirstSet(){
        gameOverBackGround = new ImageIcon(getClass().getResource("/image/backGround/GameOver/scoreBoardsummary.jpg")).getImage();
        gameOverTitleX=0;
        scoreBoardWidth=170;
        scoreBoardHeight=430;
        scoreAndNameLabelX=195;
        textFiledX=295;
        ButtonX=210;
        scoreBoardX=10;
        scoreBoardY=50;
        scoreBoardPanelWidth=150;
        scoreBoardPanelHeight=330;
        sbListSize= new int[]{30, 80, 40};
        sbListX= new int[]{0, 30, 110};
    }
    public void gameOverSecondSet(){
        gameOverBackGround = new ImageIcon(getClass().getResource("/image/backGround/GameOver/scoreBoardsummary_600.jpg")).getImage();
        gameOverTitleX=100;
        scoreBoardWidth=300;
        scoreBoardHeight=630;
        scoreAndNameLabelX=370;
        textFiledX=470;
        ButtonX=385;
        scoreBoardX=20;
        scoreBoardY=80;
        scoreBoardPanelWidth = 260;
        scoreBoardPanelHeight = 510;
        sbListSize= new int[]{60, 140, 60};
        sbListX= new int[]{0, 60, 200};
    }
    public void gameOverThirdSet(){
        gameOverBackGround = new ImageIcon(getClass().getResource("/image/backGround/GameOver/scoreBoardsummary_800.jpg")).getImage();
        gameOverTitleX=200;
        scoreBoardWidth=500;
        scoreBoardHeight=800;
        scoreAndNameLabelX=585;
        textFiledX=685;
        ButtonX=600;
        scoreBoardX=65;
        scoreBoardY=100;
        scoreBoardPanelWidth = 370;
        scoreBoardPanelHeight = 630;
        sbListSize= new int[]{85, 200, 85};
        sbListX= new int[]{0, 85, 285};
    }

}
