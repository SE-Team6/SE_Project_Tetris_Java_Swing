package seoultech.se.tetris.component;

import org.json.simple.JSONObject;

import java.util.Comparator;

//for compare Score value
public class JSONObjectScoreComparator implements Comparator<JSONObject> {
    @Override
    public int compare(JSONObject f1, JSONObject f2) {
        if (Integer.parseInt(f1.get("Score").toString()) < Integer.parseInt(f2.get("Score").toString())) {
            return 1;
        } else if (Integer.parseInt(f1.get("Score").toString()) > Integer.parseInt(f2.get("Score").toString())) {
            return -1;
        }
        return 0;
    }
}