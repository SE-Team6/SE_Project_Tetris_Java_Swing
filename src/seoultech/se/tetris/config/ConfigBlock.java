package seoultech.se.tetris.config;

// singleton
public class ConfigBlock {
    public static final String BORDER_CHAR_WIN = "X";
    public static final String BLOCK_CHAR_WIN = "O";
    public static final String NON_BLOCK_CHAR_WIN = "    ";
    public static final String BORDER_CHAR_MAC = "X";
    public static final String BLOCK_CHAR_MAC = "O";
    public static final String NON_BLOCK_CHAR_MAC = " ";
    public static final String RANDOM_CHAR = "L";

    public static String BORDER_CHAR;
    public static String BLOCK_CHAR;
    public static String NON_BLOCK_CHAR;

    public static ConfigBlock instance = new ConfigBlock();

    public static ConfigBlock getInstance() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            BORDER_CHAR = BORDER_CHAR_WIN;
            BLOCK_CHAR = BLOCK_CHAR_WIN;
            NON_BLOCK_CHAR = NON_BLOCK_CHAR_WIN;
        } else {
            BORDER_CHAR = BORDER_CHAR_MAC;
            BLOCK_CHAR = BLOCK_CHAR_MAC;
            NON_BLOCK_CHAR = NON_BLOCK_CHAR_MAC;
        }
        return instance;
    }
}
