package seoultech.se.tetris.menu;

import javax.swing.*;
import java.util.HashMap;

import static seoultech.se.tetris.component.JSONLoader.*;

import static seoultech.se.tetris.menu.BasicSet.*;
import static seoultech.se.tetris.menu.SettingMenuKeySet.labelX;
import static seoultech.se.tetris.menu.SettingMenuKeySet.textFieldX;
import static seoultech.se.tetris.main.GameOver.*;

public class Version {
    public void firstScreenSizeSet(){
        HashMap<String, Integer>map = loaderResolution();
        Width = map.get("width");
        Height = map.get("height");
        menuBarExitBtnWidth=Width-20;
        buttonX =(Width-buttonSizeX)/2;
        gameTitleX=(Width-300)/2;
    }
    public void secondScreenSizeSet(){
        HashMap<String, Integer>map = loaderResolution();
        Width = map.get("width");
        Height = map.get("height");
        menuBarExitBtnWidth=Width-20;
        buttonX =(Width-buttonSizeX)/2;
        gameTitleX=(Width-300)/2;
    }
    public void thirdScreenSizeSet(){
        HashMap<String, Integer>map = loaderResolution();
        Width = map.get("width");
        Height = map.get("height");
        menuBarExitBtnWidth=Width-20;
        buttonX =(Width-buttonSizeX)/2;
        gameTitleX=(Width-300)/2;
    }

    public void keySetFirstSize(){
        labelX =40;
        textFieldX=220;
    }
    public void keySetSecondSize(){
        labelX =140;
        textFieldX=320;
    }
    public void keySetThirdSize(){
        labelX =240;
        textFieldX=420;
    }

    public void gameOverFirstSize(){
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
    public void gameOverSecondSize(){
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
    public void gameOverThirdSize(){
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
