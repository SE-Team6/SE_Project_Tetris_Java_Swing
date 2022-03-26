package seoultech.se.tetris.componentTmp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Keyboard {
    private static HashMap<String, Integer>KeyMap = new HashMap<String, Integer>();

    private Keyboard(){}

    private static final Keyboard key = new Keyboard();

    public static Keyboard getInstance(){
        return key;
    }

    //Set Key For Control Using TextFile and return keymap
    public static HashMap<String, Integer> loadKey(){
        try (
                FileReader fr = new FileReader("key.txt");
                BufferedReader br = new BufferedReader(fr);
        ) {
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                String[] curLine = readLine.split(" ");
                if (curLine[0].equals("left")) {
                    KeyMap.put("left", Integer.parseInt(curLine[2]));
                } else if (curLine[0].equals("right")) {
                    KeyMap.put("right", Integer.parseInt(curLine[2]));
                } else if (curLine[0].equals("down")) {
                    KeyMap.put("down", Integer.parseInt(curLine[2]));
                } else if (curLine[0].equals("fall")) {
                    KeyMap.put("fall", Integer.parseInt(curLine[2]));
                } else if (curLine[0].equals("rotate")) {
                    KeyMap.put("rotate", Integer.parseInt(curLine[2]));
                } else if (curLine[0].equals("pause")) {
                    KeyMap.put("pause", Integer.parseInt(curLine[2]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e){
            System.out.println(e);
        }
        return KeyMap;
    }
}
