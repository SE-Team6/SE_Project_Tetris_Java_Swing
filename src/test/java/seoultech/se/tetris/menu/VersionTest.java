package seoultech.se.tetris.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seoultech.se.tetris.menu.BasicSet.*;
import static seoultech.se.tetris.menu.SettingMenuKeySet.labelX;
import static seoultech.se.tetris.menu.SettingMenuKeySet.textFieldX;

public class VersionTest {
    @Test
    public void firstScreenSizeSetTest() {
        new Version().firstScreenSizeSet();
        assertTrue(WIDTH>-1);
        assertTrue(Height>-1);
        assertTrue(menuBarExitBtnWidth>-1);
        assertTrue(buttonX>-1);
        assertTrue(gameTitleX>-1);    }

    @Test
    public void secondScreenSizeSetTest() {
        new Version().secondScreenSizeSet();
        assertTrue(WIDTH>-1);
        assertTrue(Height>-1);
        assertTrue(menuBarExitBtnWidth>-1);
        assertTrue(buttonX>-1);
        assertTrue(gameTitleX>-1);
    }

    @Test
    public void thirdScreenSizeSetTest() {
        new Version().thirdScreenSizeSet();
        assertTrue(WIDTH>-1);
        assertTrue(Height>-1);
        assertTrue(menuBarExitBtnWidth>-1);
        assertTrue(buttonX>-1);
        assertTrue(gameTitleX>-1);    }

    @Test
    public void keySetFirstSizeTest() {
        new Version().keySetFirstSize();
        assertTrue(labelX>-1);
        assertTrue(textFieldX>-1);
    }

    @Test
    public void keySetSecondSizeTest() {
        new Version().keySetSecondSize();
        assertTrue(labelX>-1);
        assertTrue(textFieldX>-1);
    }

    @Test
    public void keySetThirdSizeTest() {
        new Version().keySetThirdSize();
        assertTrue(labelX>-1);
        assertTrue(textFieldX>-1);
    }

    @Test
    public void gameOverFirstSizeTest() {
        new Version().gameOverFirstSize();
        assertTrue(labelX>-1);
        assertTrue(textFieldX>-1);
    }

    @Test
    public void gameOverSecondSizeTest() {
        new Version().gameOverSecondSize();
        assertTrue(labelX>-1);
        assertTrue(textFieldX>-1);
    }

    @Test
    public void gameOverThirdSizeTest() {
        new Version().gameOverThirdSize();
        assertTrue(labelX>-1);
        assertTrue(textFieldX>-1);
    }
}
