package seoultech.se.tetris.main;

import seoultech.se.tetris.component.Board;

public class Tetris {

	public static void main(String[] args) {
		Board main = new Board();
		main.setSize(400, 600);
		main.setLocation(1700, 0);


		main.setVisible(true);
	}
}