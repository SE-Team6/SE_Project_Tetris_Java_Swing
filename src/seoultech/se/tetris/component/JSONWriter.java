package seoultech.se.tetris.component;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import static seoultech.se.tetris.component.JSONLoader.getJSONObject;

public class JSONWriter {
    final static String SETTINGS_FILEPATH = "config/settings.json";

    JSONWriter(){}

    //배열로 할지? 그냥 변수로 할지?
    //배열로 한다면 0 : right, 1 : left, 2 : up : 3, down : 4, esc : 5, 6 : space
    public static void writeKey(int [] values){
        String[] key = {"LEFT", "RIGHT", "UP", "DOWN", "ESC", "SPACE"};
        JSONObject list = new JSONObject();
        for(int i=0; i<6; ++i){
            list.put(key[i], values[i]);
        }
        JSONDumps("key", list);
    }

    // WIDTH, HEIGHT
    public static void writeResolution(int width, int height, int font_size){
        JSONObject list = new JSONObject();
        list.put("width", width);
        list.put("height", height);
        list.put("font_size", font_size);
        JSONDumps("resolution", list);
    }

    public static void writeColorMode(int val){
        JSONObject list = new JSONObject();
        list.put("mode", val);
        JSONObject colorObject = new JSONObject();
        System.out.println(colorObject);
        JSONDumps("colorBlindMode", list);
    }

    //TODO
    // NAME, SCORE, TIME, difficulty, item
//    public static int appendScore(int name, int score, int time, int difficulty, boolean is_item){
//        int rank=10;
//        return rank;
//    }

    //TODO
//    public static void resetScore(){
//        return;
//    }

    public static void JSONDumps(String type, JSONObject content){
        JSONObject keyObj = getJSONObject("key");
        JSONObject resolutionObj = getJSONObject("resolution");
        JSONObject colorBlindModeObj = getJSONObject("colorBlindMode");

        switch (type){
            case "key":
                keyObj = content;
                break;
            case "resolution":
                resolutionObj = content;
                break;
            case "colorBlindMode":
                colorBlindModeObj = content;
                break;
        }

        JSONObject jsonList = new JSONObject();
        jsonList.put("key", keyObj);
        jsonList.put("resolution", resolutionObj);
        jsonList.put("colorBlindMode", colorBlindModeObj);

        try(FileWriter file = new FileWriter(SETTINGS_FILEPATH)){
            file.write(jsonList.toJSONString());
            file.flush();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
