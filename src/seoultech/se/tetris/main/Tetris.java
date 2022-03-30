package seoultech.se.tetris.main;

import seoultech.se.tetris.component.board.Board;
import seoultech.se.tetris.component.board.ItemBoard;

public class Tetris {

	public static void main(String[] args) {
		Board main = new ItemBoard();
//		Board main = new NormalBoard();

		main.setSize(400, 450);
		main.setLocation(0, 0);
//
//
		main.setVisible(true);
//		Start_Menu main = new Start_Menu();
	}
}