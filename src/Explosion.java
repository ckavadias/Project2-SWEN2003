//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Explosion extends Sprite {
	private final static String IMAGE_SRC = "res/explosion.png";
	private final float TIME = 400f;
	private float timeSinceBang = 0f;
	
	/**
	 * Constructor
	 * @param image_src the file path for the image
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Explosion(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x -1, y -1, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite Block that is being copied
	 * @throws Exception
	 */
	public Explosion(Sprite thatSprite) throws Exception {
		super(thatSprite);
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, float, GameMap)
	 */
	public void update(Input input, float delta, GameMap gameMap) throws Exception{
		this.timeSinceBang+=delta;
		if(this.timeSinceBang >= TIME) {
			gameMap.removeSprite(this);
		}
	}
	
	/* (non-Javadoc)
	 * @see Sprite#copy()
	 */
	@Override
	public Sprite copy() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
