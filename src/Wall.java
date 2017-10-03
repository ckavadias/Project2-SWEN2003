//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Wall extends Tile {
	private final static String IMAGE_SRC = "res/wall.png";
	
	/**
	 * Constructor
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Wall(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
	}
	
	public Wall(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite Wall that is being copied
	 * @throws Exception
	 */
	public Wall(Wall thatSprite) throws Exception{
		super(thatSprite);
	}
	
	/* (non-Javadoc)
	 * @see Sprite#copy()
	 */
	public Sprite copy() throws Exception{
		return new Wall(this);
	}
	
	/* (non-Javadoc)
	 * @see Tile#isBlocking()
	 */
	public boolean isBlocking() {
		return true;
	}


}
