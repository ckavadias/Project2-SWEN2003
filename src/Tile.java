//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

public abstract class Tile extends Sprite {

	/**
	 * Constructor
	 * @param image_src the file path for the image
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Tile(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite Tile that is being copied
	 * @throws Exception
	 */
	public Tile(Tile thatSprite) throws Exception {
		super(thatSprite);
	}
	
	/**
	 * Determines whether the tile blocks movement or not
	 * @return true if this tile blocks movement, false otherwise
	 */
	public abstract boolean isBlocking();
	
}
