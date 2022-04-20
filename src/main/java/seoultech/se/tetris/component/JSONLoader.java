package seoultech.se.tetris.component;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class JSONLoader {
    final static String SETTINGS_FILEPATH = "src/main/configs/settings.json";
    final static String SCORE_FILEPATH = "src/main/configs/score.json";
    static JSONParser parser = new JSONParser();

    JSONLoader(){}

    public static Object getJSONObject(String type, String key){
        JSONObject obj = new JSONObject();
        try {
            switch (type){
                case "settings":
                    FileReader settingFile = new FileReader(SETTINGS_FILEPATH);
                    obj = (JSONObject) parser.parse(settingFile);
                    settingFile.close();
                    break;
                case "score":
                    FileReader scoreFile = new FileReader(SCORE_FILEPATH);
                    obj = (JSONObject) parser.parse(scoreFile);
                    scoreFile.close();
                    break;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e){
            System.out.println("Score Board is Empty!");
        }
        return obj.get(key);
    }

    /*
        LEFT 값 얻기  : obj.get("LEFT");
        RIGHT 값 얻기  : obj.get("RIGHT");
        UP 값 얻기  : obj.get("UP");
        DOWN 값 얻기  : obj.get("DOWN");
        ESC 값 얻기  : obj.get("ESC");
        SPACE 값 얻기  : obj.get("SPACE");
     */
    public static JSONObject loaderKey(){
        JSONObject obj = (JSONObject) getJSONObject("settings","key");
        return obj;
    }

    /*
        width 값 얻기 : map.get("width")
        height 값 얻기 : map.get("height")
        font_size 값 얻기 : map.get("font_size")
     */
    public static HashMap<String, Integer> loaderResolution(){
        HashMap<String, Integer>map = new HashMap<>();
        JSONObject obj = (JSONObject) getJSONObject("settings", "resolution");
        for(Object key : obj.keySet()){
            map.put(key.toString(), Integer.parseInt(obj.get(key).toString()));
        }
        return map;
    }

    /*
        일반적인 배열처럼 index로 접근하면 됨.
        JSONArray scoreArr = JSONArray.loaderScore();
        for(int i=0; i<scoreArr.size(); ++i) 이런식으로
        scoreArr[i].get("Name") -> 이름 불러오기
         NAME, SCORE, TIME, difficulty, item
     */
    public static JSONArray loaderScore(){
        JSONArray arr = (JSONArray) getJSONObject("score", "scoreBoard");
        return  arr;
    }

    //return mode 번호
    //ex 적녹색맹 : 1, etc...
    public static int loaderColor(){
        JSONObject obj = (JSONObject) getJSONObject("settings","colorBlindMode");
        return Integer.parseInt(obj.get("mode").toString());
    }
    //스코어 보드 페이지 불러오기
    public static int loaderScoreBoardPage(){
        JSONObject obj = (JSONObject) getJSONObject("settings","scoreBoardPage");
        return Integer.parseInt(obj.get("mode").toString());
    }
}
