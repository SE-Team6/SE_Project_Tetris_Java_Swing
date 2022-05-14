package seoultech.se.tetris.main;

import seoultech.se.tetris.component.JSONLoader;
import seoultech.se.tetris.menu.Intro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Tetris {
	public static void initialization(){
		File dir = new File("./config");
		if(!dir.exists()) {
			try{
				dir.mkdir();
				File settingFile = new File("./config/settings.json");
				settingFile.createNewFile();
				String DefaultSettings = JSONLoader.jarPathToFile("/configs/default_settings.json");
				BufferedWriter writer = new BufferedWriter(new FileWriter("./config/settings.json"));
				writer.write(DefaultSettings);
				writer.close();

				File itemScoreFile = new File("./config/item_score.json");
				itemScoreFile.createNewFile();
				File normalScoreFile = new File("./config/normal_score.json");
				normalScoreFile.createNewFile();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		initialization();
		new Intro();
//		MatchBoardParent b = new MatchBoardParent();

	}
}