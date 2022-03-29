package seoultech.se.tetris.main;

import seoultech.se.tetris.component.board.*;

public class Tetris {

	public static void main(String[] args) {
//		Board main = new ItemBoard();
		Board main = new NormalBoard();
//
		main.setSize(400, 450);
		main.setLocation(1700, 0);
//
//
		main.setVisible(true);
//		Start_Menu main = new Start_Menu();
	}
}