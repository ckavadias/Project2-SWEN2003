//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CrackedWall extends Wall {
	private final static String IMAGE_SRC = "res/cracked_wall.png";
	
	/**
	 * Constructor
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public CrackedWall(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Copy Constructor
	 * @param thatSprite CrackedWall that is being copied
	 * @throws Exception
	 */
	public CrackedWall(CrackedWall thatSprite) throws Exception {
		super(thatSprite);
	}

	/* (non-Javadoc)
	 * @see Wall#copy()
	 */
	public Sprite copy() throws Exception {
		return new CrackedWall(this);
	}

	/**
	 * triggered to allow consequential actions when an explosion occurs such
	 * as removing the cracekd wall from the game map
	 * @param gameMap current game state
	 */
	public void explode(GameMap gameMap) {
		gameMap.removeSprite(this);
		
	}
	
}
