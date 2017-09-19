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
	private ArrayList<Sprite> allWalls = new ArrayList<Sprite>();
	private Player player;
	
	//loads in all sprites available from the level while also creating 
	//more specific lists for the moment just walls to aid in updating
	public World() throws Exception {
		//load sprites from csv file and identify player
		this.allSprites = Loader.loadSprites("res/levels/0.lvl");
		this.player = ((Player)this.allSprites.get(this.allSprites.size() - 1));
		
		//construct list of all walls in the game for comparison to player in the
		//update method, split in two to reduce loop time 
		for(Sprite thisSprite : this.allSprites) {
			if (thisSprite instanceof Wall) {
					this.allWalls.add(thisSprite);
			}
		}
		//clean up any excess memory
		this.allWalls.trimToSize();
	}
	
	public void update(Input input, int delta) throws Exception {
		this.player.update(input, delta, this.allWalls);
	}
	
	public void render(Graphics g) {
		for (Sprite thisSprite : this.allSprites) {
			thisSprite.render(g);
		}
	}
}
