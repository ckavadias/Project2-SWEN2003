//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

public class Floor extends Tile {
	private final static String IMAGE_SRC = "res/floor.png";
	
	/**
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Floor(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	/**
	* Constructor
	 * @param image_src the file path for the image
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Floor( String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Copy Constructor
	 * @param thatSprite Floor that is being copied
	 * @throws Exception
	 */
	public Floor(Floor thatSprite) throws Exception {
		super(thatSprite);
	}
	
	/* (non-Javadoc)
	 * @see Sprite#copy()
	 */
	public Floor copy() throws Exception {
		return new Floor(this);
	}
	
	/* (non-Javadoc)
	 * @see Tile#isBlocking()
	 */
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

}
