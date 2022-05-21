package seoultech.se.tetris.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seoultech.se.tetris.menu.SetDefault.*;
import static seoultech.se.tetris.menu.GetSetting.*;

public class VersionTest {
    @Test
    public void firstScreenSizeSetTest() {
        new GetSetting().getFirstResolution();
        assertTrue(WIDTH>-1);
        assertTrue(screenHeight >-1);
        assertTrue(menuBarExitBtnWidth>-1);
        assertTrue(buttonX>-1);
        assertTrue(gameTitleX>-1);    }

    @Test
    public void secondScreenSizeSetTest() {
        new GetSetting().getSecondResolution();
        assertTrue(WIDTH>-1);
        assertTrue(screenHeight >-1);
        assertTrue(menuBarExitBtnWidth>-1);
        assertTrue(buttonX>-1);
        assertTrue(gameTitleX>-1);
    }

    @Test
    public void thirdScreenSizeSetTest() {
        new GetSetting().getThirdResolution();
        assertTrue(WIDTH>-1);
        assertTrue(screenHeight >-1);
        assertTrue(menuBarExitBtnWidth>-1);
        assertTrue(buttonX>-1);
        assertTrue(gameTitleX>-1);    }

    @Test
    public void keySetFirstSizeTest() {
        new GetSetting().keySetFirstSet();
        assertTrue(labelX>-1);
        assertTrue(textFieldX>-1);
    }

    @Test
    public void keySetSecondSizeTest() {
        new GetSetting().keySetSecondSet();
        assertTrue(labelX>-1);
        assertTrue(textFieldX>-1);
    }

    @Test
    public void keySetThirdSizeTest() {
        new GetSetting().keySetThirdSet();
        assertTrue(labelX>-1);
        assertTrue(textFieldX>-1);
    }

    @Test
    public void gameOverFirstSizeTest() {
        new GetSetting().gameOverFirstSet();
        assertTrue(labelX>-1);
        assertTrue(textFieldX>-1);
    }

    @Test
    public void gameOverSecondSizeTest() {
        new GetSetting().gameOverSecondSet();
        assertTrue(labelX>-1);
        assertTrue(textFieldX>-1);
    }

    @Test
    public void gameOverThirdSizeTest() {
        new GetSetting().gameOverThirdSet();
        assertTrue(labelX>-1);
        assertTrue(textFieldX>-1);
    }
}
