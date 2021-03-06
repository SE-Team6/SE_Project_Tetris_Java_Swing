package seoultech.se.tetris.component;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class JSONLoader {
    static String SETTINGS_FILEPATH = "./config/settings.json";
    static String NORMAL_SCORE_FILEPATH = "./config/normal_score.json";
    static String ITEM_SCORE_FILEPATH = "./config/item_score.json";

    static JSONParser parser = new JSONParser();

    JSONLoader(){}

    public static String makeSettingsFileToPath(String path) {
        String s = null;
        try {
            byte[] buffer = new byte[251];
            InputStream stream = JSONLoader.class.getResourceAsStream(path);
            ByteArrayOutputStream byteArrayOutputStream
                    = new ByteArrayOutputStream();
            int temp;
            while ((temp = stream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, temp);
            }
            s = new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static Object getJSONObject(String type, String key){
        JSONObject obj = new JSONObject();
        try {
            switch (type) {
                case "settings":
                    FileReader settingFile = new FileReader(SETTINGS_FILEPATH);
                    obj = (JSONObject) parser.parse(settingFile);
                    settingFile.close();
                    break;
                case "normal":
                    FileReader normalFile = new FileReader(NORMAL_SCORE_FILEPATH);
                    obj = (JSONObject) parser.parse(normalFile);
                    normalFile.close();
                    break;
                case "item":
                    FileReader itemFile = new FileReader(ITEM_SCORE_FILEPATH);
                    obj = (JSONObject) parser.parse(itemFile);
                    itemFile.close();
                    break;
            }
        }catch (Exception e){
            System.out.println("Scoreboard is empty");
        }
        return obj.get(key);
    }

    /*
        LEFT ??? ??????  : obj.get("LEFT");
        RIGHT ??? ??????  : obj.get("RIGHT");
        UP ??? ??????  : obj.get("UP");
        DOWN ??? ??????  : obj.get("DOWN");
        ESC ??? ??????  : obj.get("ESC");
        SPACE ??? ??????  : obj.get("SPACE");
     */
    public static JSONObject loaderKey(int player){
        JSONObject obj;
        if (player == 1){
            obj = (JSONObject) getJSONObject("settings","key1p");
        }else {
            obj = (JSONObject) getJSONObject("settings","key2p");
        }
        return obj;
    }

    /*
        width ??? ?????? : map.get("width")
        height ??? ?????? : map.get("height")
        font_size ??? ?????? : map.get("font_size")
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
        ???????????? ???????????? index??? ???????????? ???.
        JSONArray scoreArr = JSONArray.loaderScore();
        for(int i=0; i<scoreArr.size(); ++i) ???????????????
        scoreArr[i].get("Name") -> ?????? ????????????
         NAME, SCORE, TIME, difficulty, item
     */
    public static JSONArray loaderScore(String mode){
        JSONArray arr = new JSONArray();
        if(mode.equals("normal")){
            arr = (JSONArray) getJSONObject("normal", "scoreBoard");
        }else if(mode.equals("item")){
            arr = (JSONArray) getJSONObject("item", "scoreBoard");
        }
        return arr;
    }

    //return mode ??????
    //ex ???????????? : 1, etc...
    public static int loaderColor(){
        JSONObject obj = (JSONObject) getJSONObject("settings","colorBlindMode");
        return Integer.parseInt(obj.get("mode").toString());
    }
    //????????? ?????? ????????? ????????????
    public static int loaderScoreBoardPage(){
        JSONObject obj = (JSONObject) getJSONObject("settings","scoreBoardPage");
        return Integer.parseInt(obj.get("mode").toString());
    }
}
