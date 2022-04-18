package seoultech.se.tetris.menu;

import java.util.HashMap;

import static seoultech.se.tetris.component.JSONLoader.*;

import static seoultech.se.tetris.menu.BasicSet.*;

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
    public void firstKeySetScreen(){

    }
    public void secondKeySetScreen(){

    }
    public void thirdKeySetScreen(){

    }

}
