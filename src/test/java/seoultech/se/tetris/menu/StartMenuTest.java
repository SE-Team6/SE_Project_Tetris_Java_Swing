package seoultech.se.tetris.menu;

import org.junit.jupiter.api.Test;
import seoultech.se.tetris.component.Keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StartMenuTest {
    @Test
    public void addPositionPointTest() throws NoSuchFieldException, IllegalAccessException {
        new StartMenu(0, 0).setBtnImage();
        StartMenu sm = new StartMenu(0, 0);
        sm.setBtnImage();
        Field transfer = StartMenu.class.getDeclaredField("positionPoint");
        transfer.setAccessible(true);
        int positionPoint = (int) transfer.get(sm);
        assertTrue(positionPoint >= 0 && positionPoint<=3);
    }

    @Test
    public void start_Menu_Screen_btnTest() throws NoSuchFieldException, IllegalAccessException {
        StartMenu sm = new StartMenu(0, 0);
        sm.setStartMenuBtn();
        Field transfer = StartMenu.class.getDeclaredField("positionPoint");
        transfer.setAccessible(true);
        int positionPoint = (int) transfer.get(sm);
        assertTrue(positionPoint >= 0 && positionPoint<=3);
    }

    @Test
    public void keyLoadTest() throws NoSuchFieldException, IllegalAccessException {
        new StartMenu(0, 0).keyLoad();
        StartMenu sm = new StartMenu(0, 0);
        sm.keyLoad();
        Field transfer = StartMenu.class.getDeclaredField("positionPoint");
        transfer.setAccessible(true);
        int positionPoint = (int) transfer.get(sm);
        assertTrue(positionPoint >= 0 && positionPoint<=3);
    }

    @Test
    public void setCurrentKeyLabelTest() throws NoSuchFieldException, IllegalAccessException {
        new StartMenu(0, 0).setCurrentKeyLabel();
        StartMenu sm = new StartMenu(0, 0);
        sm.setCurrentKeyLabel();
        Field transfer = StartMenu.class.getDeclaredField("positionPoint");
        transfer.setAccessible(true);
        int positionPoint = (int) transfer.get(sm);
        assertTrue(positionPoint >= 0 && positionPoint<=3);
    }

    @Test
    public void menuActionTest() throws NoSuchFieldException, IllegalAccessException {
        new StartMenu(0, 0).menuAction(0);
        StartMenu sm = new StartMenu(0, 0);
        sm.menuAction(0);
        Field transfer = StartMenu.class.getDeclaredField("positionPoint");
        transfer.setAccessible(true);
        int positionPoint = (int) transfer.get(sm);
        assertTrue(positionPoint >= 0 && positionPoint<=3);
    }

    @Test
    public void menuListenerTest() throws NoSuchFieldException, IllegalAccessException {
        new StartMenu(0, 0).new menuListener();
        assertTrue(true);
    }

    @Test
    public void menuKeyListenerTest() throws NoSuchFieldException, IllegalAccessException {
        StartMenu sms = new StartMenu(0,0);
        KeyAdapter mkl = sms.new menuListener();
        KeyEvent ke = new KeyEvent(sms, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
        ke.setKeyCode(Keyboard.DOWN);
        mkl.keyPressed(ke);
        Field transfer = StartMenu.class.getDeclaredField("positionPoint");
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
