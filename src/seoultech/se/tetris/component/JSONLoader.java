package seoultech.se.tetris.component;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.naming.event.ObjectChangeListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class JSONLoader {
    final static String SETTINGS_FILEPATH = "config/settings.json";
    static JSONParser parser = new JSONParser();

    JSONLoader(){}

    public static JSONObject getJSONObject(String key){
        JSONObject obj = new JSONObject();
        try {
            obj = (JSONObject) parser.parse(new FileReader(SETTINGS_FILEPATH));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
        //원래 Object 객체라 casting
        return (JSONObject) obj.get(key);
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
        JSONObject obj = getJSONObject("key");
        return obj;
    }

    /*
    width 값 얻기 : map.get("width")
    height 값 얻기 : map.get("height")
    font_size 값 얻기 : map.get("font_size")
     */
    public static HashMap<String, Integer> loaderResolution(){
        HashMap<String, Integer>map = new HashMap<>();
        JSONObject obj = getJSONObject("resolution");
        for(Object key : obj.keySet()){
            map.put(key.toString(), Integer.parseInt(obj.get(key).toString()));
        }
        return map;
    }

//    TODO
//    public static void loaderScore(){
//
//    }

    //return mode 번호
    public static int loaderColor(){
        JSONObject obj = getJSONObject("colorBlindMode");
        return Integer.parseInt(obj.get("mode").toString());
    }
}
