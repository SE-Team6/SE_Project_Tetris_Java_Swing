package seoultech.se.tetris.component.board;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seoultech.se.tetris.blocks.ParentBlock;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockGenerateProbabilityTest {

    private static final int TIMES = 100000;
    private static double[] MIN = new double[3];
    private static double[] MAX = new double[3];
    private static double[] IBLOCK_MIN = new double[3];
    private static double[] IBLOCK_MAX = new double[3];
    private static double ITEM_MIN;
    private static double ITEM_MAX;


    private final static NormalBoard board = new NormalBoard();
    private final static ItemBoard iBoard = new ItemBoard();

    private static int [][] count = new int[3][7];
    private static int [] iCount = new int[17];


    private static double [][] arr = {
                                        {120,100,100,100,100,100,100},
                                        {100,100,100,100,100,100,100},
                                        {80,100,100,100,100,100,100}
                                     };
    private static double[] itemProb = new double[17];

    private final String[] blockTypes = {"IBlock","JBlock","LBlock","OBlock","SBlock","TBlock","ZBlock"};

    @BeforeAll
    public static void beforeTest(){
        System.out.println("Before Test...");
        // Item Block Generation
        ItemBoard.setDifficulty(0);
        for(int i=0; i<17; ++i)
            itemProb[i] = (double)1/17;
        ITEM_MIN = (TIMES * itemProb[0]) * 0.95;
        ITEM_MAX = (TIMES * itemProb[0]) * 1.05;
        for(int i=0; i<TIMES; ++i){
            ParentBlock generated = iBoard.getRandomItemBlock();

            String[] path = generated.getClass().toString().split("[.]");
            switch (path[6]){
                case "RandomIBlock":
                    iCount[0] += 1;
                    break;
                case "RandomJBlock":
                    iCount[1] += 1;
                    break;
                case "RandomLBlock":
                    iCount[2] += 1;
                    break;
                case "RandomZBlock":
                    iCount[3] += 1;
                    break;
                case "RandomSBlock":
                    iCount[4] += 1;
                    break;
                case "RandomTBlock":
                    iCount[5] += 1;
                    break;
                case "RandomOBlock":
                    iCount[6] += 1;
                    break;
                case "OneBlock":
                    iCount[7] += 1;
                    break;
                case "SlimeBlock":
                    iCount[8] += 1;
                    break;
                case "QueenIBlock":
                    iCount[9] += 1;
                    break;
                case "QueenJBlock":
                    iCount[10] += 1;
                    break;
                case "QueenLBlock":
                    iCount[11] += 1;
                    break;
                case "QueenZBlock":
                    iCount[12] += 1;
                    break;
                case "QueenSBlock":
                    iCount[13] += 1;
                    break;
                case "QueenTBlock":
                    iCount[14] += 1;
                    break;
                case "QueenOBlock":
                    iCount[15] += 1;
                    break;
                case "PendulumBlock":
                    iCount[16] += 1;
                    break;
            }
        }
        // Normal Block Generation
        for(int i=0; i<3; ++i){
            NormalBoard.setDifficulty(i);
            double sum = Arrays.stream(arr[i]).sum();
            double[] prob = Arrays.stream(arr[i]).map((x)->x/sum).toArray();

            IBLOCK_MIN[i] = (TIMES * prob[0]) * 0.95;
            IBLOCK_MAX[i] = (TIMES * prob[0]) * 1.05;
            MIN[i] = (TIMES * prob[1]) * 0.95;
            MAX[i] = (TIMES * prob[1]) * 1.05;

            for (int j = 0; j < TIMES; ++j){
                ParentBlock generated = board.getRandomBlock();
                String[] path = generated.getClass().toString().split("blocks.");
                switch (path[1]){
                    case "IBlock":
                        count[i][0] += 1;
                        break;
                    case "JBlock":
                        count[i][1] += 1;
                        break;
                    case "LBlock":
                        count[i][2] += 1;
                        break;
                    case "OBlock":
                        count[i][3] += 1;
                        break;
                    case "SBlock":
                        count[i][4] += 1;
                        break;
                    case "TBlock":
                        count[i][5] += 1;
                        break;
                    case "ZBlock":
                        count[i][6] += 1;
                        break;
                }
            }
        }
        System.out.println("Test Start!");
    }

    @Test
    void itemBlockGenerationTest(){
        for(int i=0; i<17; ++i){
            assertTrue(ITEM_MIN <= iCount[i] && iCount[i] <= ITEM_MAX, "The probability of "+i+"th item block does not meet the condition.");
        }
    }
    @Test
    public void easyModeBlockGenerationTest(){
        assertTrue(IBLOCK_MIN[0] <= count[0][0] && count[0][0] <= IBLOCK_MAX[0], "The probability of "+blockTypes[0]+" generation does not meet the condition in Easy Mode.");
        for(int i=1; i<7; ++i){
            assertTrue(MIN[0] <= count[0][i] && count[0][i] <= MAX[0], "The probability of "+blockTypes[i]+" generation does not meet the condition in Easy Mode.");
        }
    }
    @Test
    public void NormalModeBlockGenerationTest(){
        assertTrue(IBLOCK_MIN[1] <= count[1][0] && count[1][0] <= IBLOCK_MAX[1], "The probability of "+blockTypes[0]+" generation does not meet the condition in Normal Mode.");
        for(int i=1; i<7; ++i){
            assertTrue(MIN[1] <= count[1][i] && count[1][i] <= MAX[1], "The probability of "+blockTypes[i]+" generation does not meet the condition in Normal Mode.");
        }
    }
    @Test
    public void HardModeBlockGenerationTest(){
        assertTrue(IBLOCK_MIN[2] <= count[2][0] && count[2][0] <= IBLOCK_MAX[2], "The probability of "+blockTypes[0]+" generation does not meet the condition in Hard Mode.");
        for(int i=1; i<7; ++i){
            assertTrue(MIN[2] <= count[2][i] && count[2][i] <= MAX[2], "The probability of "+blockTypes[i]+" generation does not meet the condition in Hard Mode.");
        }
    }

}
