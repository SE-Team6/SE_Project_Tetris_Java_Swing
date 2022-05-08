package seoultech.se.tetris.config;

import seoultech.se.tetris.component.JSONLoader;

// singleton
public class ConfigBlock {
    public static final String BORDER_CHAR_WIN = "X";
    public static final String BLOCK_CHAR_WIN = "O";
    public static final String NON_BLOCK_CHAR_WIN = "    ";
//    public static final String NON_BLOCK_CHAR_WIN = "    ";

    public static final String BORDER_CHAR_MAC = "◻";
    public static final String BLOCK_CHAR_MAC = "◼";
    public static final String NON_BLOCK_CHAR_MAC = " ";

    public static final String RANDOM_CHAR_MAC = "ʟ";
    public static final String CHESS_QUEEN_MAC = "♜";
    public static final String STAR_MAC = "★";

    public static final String RANDOM_CHAR_WIN = "L";
    public static final String CHESS_QUEEN_WIN = "R";
    public static final String STAR_WIN = "#";

    public static int fontSize = 32;

    public static int colorType = 0;
    public static final String[][] BlOCK_COLOR = {
            {"#FF0000",
                    "#800080",
                    "#02FF00",
                    "#FFFF00",
                    "#0000FF",
                    "#FFA500",
                    "#02FFFF"},
            {
                    "#E59F00",
                    "#57B4E9",
                    "#049D72",
                    "#EFE541",
                    "#0072B1",
                    "#D65F00",
                    "#CC79A6"},
            {"#D47D90",
                    "#BF40A6",
                    "#F0F9FA",
                    "#40BF44",
                    "#E8E4BA",
                    "#605C20",
                    "#A46337"},
    };

    public static String BORDER_CHAR;
    public static String BLOCK_CHAR;
    public static String NON_BLOCK_CHAR;
    public static String RANDOM_CHAR;
    public static String CHESS_QUEEN;
    public static String STAR;

    public static ConfigBlock instance = new ConfigBlock();

    public static ConfigBlock getInstance() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            BORDER_CHAR = BORDER_CHAR_WIN;
            BLOCK_CHAR = BLOCK_CHAR_WIN;
            NON_BLOCK_CHAR = NON_BLOCK_CHAR_WIN;
            RANDOM_CHAR = RANDOM_CHAR_WIN;
            CHESS_QUEEN = CHESS_QUEEN_WIN;
            STAR = STAR_WIN;
        } else {
            BORDER_CHAR = BORDER_CHAR_MAC;
            BLOCK_CHAR = BLOCK_CHAR_MAC;
            NON_BLOCK_CHAR = NON_BLOCK_CHAR_MAC;
            RANDOM_CHAR = RANDOM_CHAR_MAC;
            CHESS_QUEEN = CHESS_QUEEN_MAC;
            STAR = STAR_MAC;
        }
        colorType = JSONLoader.loaderColor();
        return instance;
    }

    public static void setColorType(int type) {
        colorType = type;
    }

    public static void setFontSize(int size) {
        fontSize = size;
    }
}
