package seoultech.se.tetris.Menu;

import java.util.HashMap;

import static seoultech.se.tetris.component.JSONLoader.*;

import static seoultech.se.tetris.Menu.StartMenu.*;
import static seoultech.se.tetris.Menu.BasicSet.*;

public class Version {
    public void firstScreenSizeSet(){
        HashMap<String, Integer>map = loaderResolution();
        System.out.println(map.keySet());
        Width = map.get("width");
        Height = map.get("height");
        menuBarWidth=400;
        menuBarExitBtnWidth=380;
        gameTitleX =50;
        buttonX =125;
    }
    public void secondScreenSizeSet(){
        HashMap<String, Integer>map = loaderResolution();
        System.out.println(map.keySet());
        Width = map.get("width");
        Height = map.get("height");
        menuBarWidth=600;
        menuBarExitBtnWidth=580;
        gameTitleX =130;
        buttonX =225;
    }
    public void thirdScreenSizeSet(){
        HashMap<String, Integer>map = loaderResolution();
        System.out.println(map.keySet());
        Width = map.get("width");
        Height = map.get("height");
        menuBarWidth=800;
        menuBarExitBtnWidth=780;
        gameTitleX =230;
        buttonX =300;
    }
}
