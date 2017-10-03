//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

public class Target extends Floor {
	private final static String IMAGE_SRC = "res/target.png";
	
	/**
	 * Constructor
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Target(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite Target that is being copied
	 * @throws Exception
	 */
	public Target(Target thatSprite) throws Exception{
		super(thatSprite);
	}
	
	/* (non-Javadoc)
	 * @see Floor#copy()
	 */
	public Target copy() throws Exception{
		return new Target(this);
	}

}
