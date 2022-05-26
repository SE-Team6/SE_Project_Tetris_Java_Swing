package seoultech.se.tetris.component;

import org.json.simple.JSONObject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    public static int LEFT, RIGHT, UP, DOWN, ESC, SPACE;
    public static int LEFT2, RIGHT2, UP2, DOWN2, ESC2, SPACE2;

    public Keyboard(){}

    private static final Keyboard key = new Keyboard();

    public static Keyboard getInstance(){
        return key;
    }


    //Set Key For Control Using TextFile and return keymap
    public static void setKey(){
        JSONObject obj = JSONLoader.loaderKey(1);
        LEFT = Integer.parseInt(obj.get("LEFT").toString());
        RIGHT = Integer.parseInt(obj.get("RIGHT").toString());
        UP = Integer.parseInt(obj.get("UP").toString());
        DOWN = Integer.parseInt(obj.get("DOWN").toString());
        ESC = Integer.parseInt(obj.get("ESC").toString());
        SPACE = Integer.parseInt(obj.get("SPACE").toString());
        obj = JSONLoader.loaderKey(2);
        LEFT2 = Integer.parseInt(obj.get("LEFT").toString());
        RIGHT2 = Integer.parseInt(obj.get("RIGHT").toString());
        UP2 = Integer.parseInt(obj.get("UP").toString());
        DOWN2 = Integer.parseInt(obj.get("DOWN").toString());
        ESC2 = Integer.parseInt(obj.get("ESC").toString());
        SPACE2 = Integer.parseInt(obj.get("SPACE").toString());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
