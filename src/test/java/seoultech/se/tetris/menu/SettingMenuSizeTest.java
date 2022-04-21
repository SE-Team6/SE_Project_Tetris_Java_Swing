package seoultech.se.tetris.menu;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class SettingMenuSizeTest {
    @Test
    public void settingScreenSizeBtnTest() throws NoSuchFieldException, IllegalAccessException {
        new SettingMenuSize(0, 0).settingScreenSizeBtn();
        SettingMenuSize sms = new SettingMenuSize(0, 0);
        Field transfer = SettingMenuSize.class.getDeclaredField("menuButton");
        transfer.setAccessible(true);
        JButton[] menuButton = (JButton[]) transfer.get(sms);
        for (int i=0;i<3;i++){
            assertEquals(0.0, menuButton[i].getAlignmentX());
        }
    }

    @Test
    public void allPositionPointTest() throws NoSuchFieldException, IllegalAccessException {
        new SettingMenuSize(0, 0).allPositionPoint();
        SettingMenuSize sms = new SettingMenuSize(0, 0);
        Field transfer = SettingMenuSize.class.getDeclaredField("positionPoint");
        transfer.setAccessible(true);
        sms.allPositionPoint();
        int positionPoint = (int) transfer.get(sms);
        assertTrue(positionPoint >= 0 && positionPoint<=3);
    }

    @Test
    public void basicSetKeyListenerTest() {
        BasicSet bs = new SettingMenuSize(0, 0).bs;
        assertEquals(0.0, bs.menuBarExitBtn.getAlignmentX());
    }

    @Test
    public void menuKeyListenerTest() throws NoSuchFieldException, IllegalAccessException {
        SettingMenuSize sms = new SettingMenuSize(0,0);
        KeyAdapter mkl = sms.new menuKeyListener();
        KeyEvent ke = new KeyEvent(sms, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
        ke.setKeyCode(sms.key.DOWN);
        mkl.keyPressed(ke);
        Field transfer = SettingMenuSize.class.getDeclaredField("positionPoint");
        transfer.setAccessible(true);
        sms.allPositionPoint();
        int positionPoint = (int) transfer.get(sms);
        assertEquals(1, positionPoint);
        ke.setKeyCode(sms.key.UP);
        mkl.keyPressed(ke);
        positionPoint = (int) transfer.get(sms);
        assertEquals(0, positionPoint);
        ke.setKeyCode(KeyEvent.VK_ENTER);
        mkl.keyPressed(ke);
        positionPoint = (int) transfer.get(sms);
        assertEquals(0, positionPoint);
        ke.setKeyCode(KeyEvent.VK_BACK_SPACE);
        mkl.keyPressed(ke);
        assertFalse(sms.isVisible());
    }
}
