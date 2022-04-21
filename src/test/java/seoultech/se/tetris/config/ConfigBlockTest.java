package seoultech.se.tetris.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfigBlockTest {
    @Test
    public void fontSizeTest() {
        int[] arr = {32, 48, 64};
        for (int j : arr) {
            ConfigBlock.setFontSize(j);
            assertTrue(32<=ConfigBlock.fontSize && ConfigBlock.fontSize <= 64);
        }
    }

    @Test
    public void colorTypeTest() {
        int[] arr = {0, 1, 2};
        for (int j : arr) {
            ConfigBlock.setColorType(j);
            assertTrue(0<=ConfigBlock.colorType && ConfigBlock.colorType <= 2);
        }
    }

    @Test
    public void getInstanceTest() {
        assertEquals(ConfigBlock.instance, ConfigBlock.getInstance());
    }
}
