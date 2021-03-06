package seoultech.se.tetris.component;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static seoultech.se.tetris.component.JSONLoader.*;

public class JSONWriter {
    final static String SETTINGS_FILEPATH = "./config/settings.json";
    final static String NORMAL_SCORE_FILEPATH = "./config/normal_score.json";
    final static String ITEM_SCORE_FILEPATH = "./config/item_score.json";

    JSONWriter(){}

    /*
        JSONArray -> JSONObject를 변환하는 함수
        귀찮게 이 함수를 쓰지 않으면 JSON의 키로 접근할 수가 없음.
    */
    public static ArrayList<JSONObject> JSONArrayToArrayList(JSONArray array) {
        ArrayList<JSONObject> newList = new ArrayList<>();
        if (array != null) {
            try {
                for (int i = 0; i < array.size(); ++i) {
                    newList.add((JSONObject) parser.parse(array.get(i).toString()));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return newList;
    }
    /*
        int [] values = {right, left, up, down, esc, space};
    */
    public static void writeKey(int [] values, int player){
        String[] key = {"LEFT", "RIGHT", "UP", "DOWN", "ESC", "SPACE"};
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(int i=0; i<6; ++i){
            hashMap.put(key[i], values[i]);
        }
        JSONObject list = new JSONObject(hashMap);
        if(player == 1)
            JSONDumps("key1p", list);
        else
            JSONDumps("key2p", list);
    }

    // WIDTH, HEIGHT, FONT_SIZE
    public static void writeResolution(int width, int height, int font_size){
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("width", width);
        hashMap.put("height", height);
        hashMap.put("font_size", font_size);
        JSONObject list = new JSONObject(hashMap);
        JSONDumps("resolution", list);
    }

    // Loader와 동일
    // 적녹 : 1, 이런식으로
    public static void writeColorMode(int val){
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("mode", val);
        JSONObject list = new JSONObject(hashMap);
        JSONDumps("colorBlindMode", list);
    }

    public static void writeScoreBoardPage(int val){
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("mode", val);
        JSONObject list = new JSONObject(hashMap);
        JSONDumps("scoreBoardPage", list);
    }


    /*
        int[] inputScore = {name, score, time, difficulty, isItem};
        return rank -> rank를 업데이트 하고 하이라이트로 보여주기 위해서 반환함.
        input 스코어를 입력받고, 하나의 리스트에 입력한 후 sort
     */
    public static int appendScore(String[] inputScore, String mode){
        int rank = -1;
        // InputScore -> New Score Object(JSONObject)
        String[] key = {"Name", "DateTime", "Score", "Difficulty", "isItem"};

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(key[0], inputScore[0]);
        hashMap.put(key[1], inputScore[1]);
        for(int i=2; i<5; ++i)
            hashMap.put(key[i], Integer.parseInt(inputScore[i]));

        JSONObject newScore = new JSONObject(hashMap);
        JSONArray loadedScores = new JSONArray();

        //Load JSONArray
        if(mode.equals("normal")){
            loadedScores = (JSONArray) getJSONObject("normal", "scoreBoard");
        }else if(mode.equals("item")){
            loadedScores = (JSONArray) getJSONObject("item", "scoreBoard");
        }

        //For compare To new Score
        ArrayList<JSONObject> allScores = JSONArrayToArrayList(loadedScores);
        allScores.add(newScore);

        //sort(descending)
        allScores.sort(new JSONObjectScoreComparator());

        // Write to FILE
        JSONObject result = new JSONObject();
        JSONArray arr2 = new JSONArray();
        for(int i=0; i < Math.min(allScores.size(), 100); ++i){
            if(newScore.equals(allScores.get(i))){
                rank = i;
            }
            arr2.add(allScores.get(i));
        }
        result.put("scoreBoard", arr2);

        if(mode.equals("normal")){
            try(FileWriter file = new FileWriter(NORMAL_SCORE_FILEPATH)){
                file.write(result.toJSONString());
                file.flush();
            } catch (IOException e){
                e.printStackTrace();
            }
        }else if(mode.equals("item")){
            try(FileWriter file = new FileWriter(ITEM_SCORE_FILEPATH)){
                file.write(result.toJSONString());
                file.flush();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return rank;
    }

    //SCORE_FILEPATH의 내용을 비운다.
    public static void resetScore(String mode) {
        if (mode.equals("normal")) {
            try {
                new FileOutputStream(NORMAL_SCORE_FILEPATH).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (mode.equals("item")) {
            try {
                new FileOutputStream(ITEM_SCORE_FILEPATH).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void JSONDumps(String type, JSONObject content){
        JSONObject key1pObj = (JSONObject) getJSONObject("settings","key1p");
        JSONObject key2pObj = (JSONObject) getJSONObject("settings","key2p");
        JSONObject resolutionObj = (JSONObject) getJSONObject("settings", "resolution");
        JSONObject colorBlindModeObj = (JSONObject) getJSONObject("settings", "colorBlindMode");
        JSONObject scoreBoardPageObj = (JSONObject) getJSONObject("settings", "scoreBoardPage");

        switch (type){
            case "key1p":
                key1pObj = content;
                break;
            case "key2p":
                key2pObj = content;
                break;
            case "resolution":
                resolutionObj = content;
                break;
            case "colorBlindMode":
                colorBlindModeObj = content;
                break;
            case "scoreBoardPage":
                scoreBoardPageObj = content;
                break;
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("key1p", key1pObj);
        hashMap.put("key2p", key2pObj);
        hashMap.put("resolution", resolutionObj);
        hashMap.put("colorBlindMode", colorBlindModeObj);
        hashMap.put("scoreBoardPage",scoreBoardPageObj);

        JSONObject jsonList = new JSONObject(hashMap);
        try(FileWriter file = new FileWriter(SETTINGS_FILEPATH)){
            file.write(jsonList.toJSONString());
            file.flush();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
