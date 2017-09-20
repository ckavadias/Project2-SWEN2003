//Adapted for use by Constantinos Kavadias for Project1, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//August, 2017

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

@SuppressWarnings("unused")
public class World {	
	private final int MAX_LEVEL = 5;
	private final String DIR = "res/levels/";
	private final String EXT = ".lvl";
	private GameMap gameMap;
	private int currentLevel = 0;
	private Player player;
	
	//loads in all sprites available from the level while also creating 
	//more specific lists for the moment just walls to aid in updating
	public World() throws Exception {
		//load sprites from csv file and identify player
		this.gameMap = new GameMap(loadLevel());
	}
	
	public void update(Input input, int delta) throws Exception {
		this.gameMap.update(input, delta);
	}
	
	public void render(Graphics g) throws Exception {
		gameMap.render(g);
		if(gameMap.winState()) {
			currentLevel = currentLevel < MAX_LEVEL ? currentLevel + 1 : currentLevel;
			this.gameMap = new GameMap(loadLevel());
		}
	}
	
	private ArrayList<Sprite> loadLevel() throws Exception {
		return Loader.loadSprites(DIR + currentLevel + EXT);
	}
}
