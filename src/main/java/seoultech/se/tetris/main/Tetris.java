package seoultech.se.tetris.main;

import seoultech.se.tetris.menu.StartMenu;

import javax.swing.*;

public class Tetris {
	private ImageIcon gameStartBtnImage = new ImageIcon("src/main/resources/image/Button/start_Menu_btn/Game_Start_Basic.jpg");
	private JButton gameStartBtn = new JButton(gameStartBtnImage);
	public static void main(String[] args) {
		new StartMenu();
	}

}