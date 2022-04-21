package seoultech.se.tetris.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameOverTest {
    @Test
    public void gameOverInitTest() {
        new GameOver(0, 0);
        assertTrue(true);
    }
}
