package seoultech.se.tetris.menu;

import org.junit.jupiter.api.Test;
import seoultech.se.tetris.component.Keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameDifficultyTest {
    @Test
    public void menuKeyListenerTest() throws NoSuchFieldException, IllegalAccessException {
        GameDifficulty sms = new GameDifficulty(0,0);
        KeyAdapter mkl = sms.new menuKeyListener();
        Field transfer = GameDifficulty.class.getDeclaredField("bs");
        transfer.setAccessible(true);
        SetDefault bs = (SetDefault)transfer.get(sms);

        KeyEvent ke = new KeyEvent(bs, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
        ke.setKeyCode(Keyboard.DOWN);
        mkl.keyPressed(ke);
        transfer = GameDifficulty.class.getDeclaredField("positionPoint");
        transfer.setAccessible(true);
        sms.setBtnImage();
        int positionPoint = (int) transfer.get(sms);
        assertEquals(1, positionPoint);
        ke.setKeyCode(Keyboard.UP);
        mkl.keyPressed(ke);
        positionPoint = (int) transfer.get(sms);
        assertEquals(0, positionPoint);
        ke.setKeyCode(KeyEvent.VK_ENTER);
        mkl.keyPressed(ke);
        positionPoint = (int) transfer.get(sms);
        assertEquals(0, positionPoint);

        ke.setKeyCode(KeyEvent.VK_BACK_SPACE);
        mkl.keyPressed(ke);
        positionPoint = (int) transfer.get(sms);
        assertEquals(0, positionPoint);
    }

    @Test
    public void itemModeTest() {
        new GameDifficulty(0, 0).itemMode(0);
        assertTrue(true);
    }
}
