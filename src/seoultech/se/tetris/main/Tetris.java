package seoultech.se.tetris.main;

import seoultech.se.tetris.component.board.ItemBoard;

public class Tetris {

	public static void main(String[] args) {
		ItemBoard main = new ItemBoard();

		main.setSize(400, 600);
		main.setLocation(1700, 0);


		main.setVisible(true);
	}
}