//Adapted for use by Constantinos Kavadias for Project1, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//August, 2017

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

@SuppressWarnings("unused")
public class World {	
	private ArrayList<Sprite> allSprites = new ArrayList<Sprite>();
	private GameMap gameMap;
	private Player player;
	
	//loads in all sprites available from the level while also creating 
	//more specific lists for the moment just walls to aid in updating
	public World() throws Exception {
		//load sprites from csv file and identify player
		this.allSprites = Loader.loadSprites("res/levels/0.lvl");
		this.gameMap = new GameMap(this.allSprites);
	}
	
	public void update(Input input, int delta) throws Exception {
		this.gameMap.update(input, delta);
	}
	
	public void render(Graphics g) {
		for (Sprite thisSprite : this.allSprites) {
			thisSprite.render(g);
		}
	}
}
