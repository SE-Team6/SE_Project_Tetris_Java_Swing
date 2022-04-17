package seoultech.se.tetris.main;

import seoultech.se.tetris.component.board.Board;
import seoultech.se.tetris.component.board.ItemBoard;
import seoultech.se.tetris.component.board.NormalBoard;
import seoultech.se.tetris.menu.StartMenu;

public class Tetris {

	public static void main(String[] args) {
		Board main = new ItemBoard();
//		Board main = new NormalBoard();

		main.setSize(600, 800);
		main.setLocation(0, 0);
//
//
		main.setVisible(true);
//		System.out.println(System.getProperty("user.dir"));
//		StartMenu main = new StartMenu();
	}
}