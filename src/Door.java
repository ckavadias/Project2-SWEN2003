//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Door extends Tile {
	private final static String IMAGE_SRC = "res/door.png";
	private boolean isOpen = false;
	
	/**
	 * Constructor
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Door(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite Door that is being copied
	 * @throws Exception
	 */
	public Door(Door thatSprite) throws Exception{
		super(thatSprite);
		this.isOpen = !thatSprite.isBlocking();
	}
	
	/* (non-Javadoc)
	 * @see Sprite#copy()
	 */
	public Door copy() throws Exception{
		return new Door(this);
	}
	
	/**
	 * Change the open status of the door to the opposite of 
	 * what it is currently
	 */
	public void openClose() {
		this.isOpen = !this.isOpen;
	}
	/* (non-Javadoc)
	 * @see Tile#isBlocking()
	 */
	@Override
	public boolean isBlocking() {
		return !this.isOpen;
	}
	
	/* (non-Javadoc)
	 * @see Sprite#render(org.newdawn.slick.Graphics)
	 */
	public void render(Graphics g) throws SlickException {
		if(this.isOpen) {
			return;
		}
		super.render(g);
	}

}
