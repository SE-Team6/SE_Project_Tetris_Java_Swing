package seoultech.se.tetris.menu;

import org.junit.jupiter.api.Test;
import seoultech.se.tetris.component.Keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SettingMenuKeySetTest {
    @Test
    public void setKeyListenerTest() throws NoSuchFieldException, IllegalAccessException {
        SetKey1P sms = new SetKey1P(0,0);
        KeyAdapter mkl = sms.new setKeyListener();
        KeyEvent ke = new KeyEvent(sms, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
        ke.setKeyCode(Keyboard.DOWN);
        mkl.keyPressed(ke);
        Field transfer = SetKey1P.class.getDeclaredField("positionPoint");
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
    }
}
