package seoultech.se.tetris.component;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreBoardTest {
    @Test
    void scoreBoardNameEasyTest() {
        ScoreBoard sb = new ScoreBoard(0);
        assertEquals("Easy", sb.difficultyLabelSet("0"));
    }

    @Test
    void scoreBoardNameNormalTest() {
        ScoreBoard sb = new ScoreBoard(0);
        assertEquals("Normal", sb.difficultyLabelSet("1"));
    }

    @Test
    void scoreBoardNameHardTest() {
        ScoreBoard sb = new ScoreBoard(0);
        assertEquals("Hard", sb.difficultyLabelSet("2"));
    }
}
