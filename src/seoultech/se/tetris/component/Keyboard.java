package seoultech.se.tetris.component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import seoultech.se.tetris.component.JSONLoader;

public class Keyboard implements KeyListener {
    public static int LEFT, RIGHT, UP, DOWN, ESC, SPACE;

    private Keyboard(){}

    private static final Keyboard key = new Keyboard();

    public static Keyboard getInstance(){
        return key;
    }

    //Set Key For Control Using TextFile and return keymap
    public static void setKey(){
        JSONObject obj = JSONLoader.loaderKey();
        LEFT = Integer.parseInt(obj.get("LEFT").toString());
        RIGHT = Integer.parseInt(obj.get("RIGHT").toString());
        UP = Integer.parseInt(obj.get("UP").toString());
        DOWN = Integer.parseInt(obj.get("DOWN").toString());
        ESC = Integer.parseInt(obj.get("ESC").toString());
        SPACE = Integer.parseInt(obj.get("SPACE").toString());
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
