//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

public class Stone extends Block {
	private final static String IMAGE_SRC = "res/stone.png";
	
	/**
	 * Constructor
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Stone(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Stone(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite Stone that is being copied
	 * @throws Exception
	 */
	public Stone(Stone thatSprite) throws Exception {
		super(thatSprite);
	}
	
	/* (non-Javadoc)
	 * @see Sprite#copy()
	 */
	@Override
	public Sprite copy() throws Exception {
		return new Stone(this);
	}
}
