import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import seoultech.se.tetris.component.JSONLoader;
import seoultech.se.tetris.component.JSONWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONClassTest {

    @Test
    public void writeKeyTest(){
        String[] key = {"LEFT", "RIGHT", "UP", "DOWN","ESC", "SPACE"};
        int[] expected = {37,39,38,40,27,32};
        JSONWriter.writeKey(expected);
        JSONObject obj = JSONLoader.loaderKey();
        for(int i=0; i<6; ++i){
            assertEquals(expected[i], (int)(long)obj.get(key[i]));
        }
    }

    @Test
    public void writeResolutionTest(){
        String[] key = {"width", "height", "font_size"};
        int[] expected = {400,600,20};
        JSONWriter.writeResolution(400, 600, 20);
        HashMap<String, Integer> obj = JSONLoader.loaderResolution();
        for(int i=0; i<3; ++i){
            assertEquals(expected[i], Integer.parseInt(obj.get(key[i]).toString()));
        }
    }
    @Test
    public void writeColorModeTest(){
        JSONWriter.writeColorMode(1);
        int val = JSONLoader.loaderColor();
        assertEquals(1, val);
    }
    @Test
    public void appendScoreTest(){
        String[] key = {"Name", "DateTime", "Score", "Difficulty", "isItem"};
        String[] expected = {"khyo", "20220418", "2022041800", "2", "1"};
        JSONWriter.appendScore(expected);
        JSONArray res = JSONLoader.loaderScore();
        ArrayList<JSONObject> arr = JSONWriter.JSONArrayToArrayList(res);
        for(int i=0; i<2; ++i){
            assertEquals(expected[i], arr.get(0).get(key[i]));
        }
        for(int i=2; i<5; ++i){
            assertEquals(expected[i], arr.get(0).get(key[i]).toString());
        }
    }

//    @Test(expected = NullPointerException.class)
//    public void resetScoreTest(){
//        JSONWriter.resetScore();
//        JSONArray res = JSONLoader.loaderScore();
//        assertEquals(0, res.size());
//    }
}
