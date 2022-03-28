package seoultech.se.tetris.Menu;

import java.util.HashMap;

import static seoultech.se.tetris.Menu.Start_Menu.*;
import static seoultech.se.tetris.component.JSONLoader.*;
import static seoultech.se.tetris.component.JSONWriter.*;

public class Version {
    public void first_Screensize_Set(){
        Width = 400;
        Height = 500;
        menubar_Width=400;
        menubar_Exit_btn_Width=380;
        gametitle_x=50;
        startbuttonx =125;
        setting_Button_x=140;
    }
    public void second_Screensize_Set(){
        HashMap<String, Integer>map = loaderResolution();
        System.out.println(map.keySet());
        Width = map.get("width");
        Height = map.get("height");
        menubar_Width=600;
        menubar_Exit_btn_Width=580;
        gametitle_x=130;
        startbuttonx =225;
        setting_Button_x=220;
    }
    public void third_Screensize_Set(){
        Width = 800;
        Height = 1000;
        menubar_Width=800;
        menubar_Exit_btn_Width=780;
        gametitle_x=230;
        startbuttonx =300;
        setting_Button_x=320;
    }
}
