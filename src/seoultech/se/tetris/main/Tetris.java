package seoultech.se.tetris.main;

import seoultech.se.tetris.component.Board;
import seoultech.se.tetris.Menu.Start_Menu;
import seoultech.se.tetris.component.JSONWriter;
import seoultech.se.tetris.component.Keyboard;
public class Tetris {

	public static void main(String[] args) {
//		Board main = new Board();
//		main.setSize(400, 600);
//		main.setLocation(1700, 0);
//
//
//		main.setVisible(true);
		Start_Menu menu = new Start_Menu();
		Keyboard keyboard = Keyboard.getInstance();
		keyboard.setKey();
		System.out.println(keyboard.LEFT);
	}
}