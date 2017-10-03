//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import org.newdawn.slick.Input;

public class Ice extends Block {
	private final static String IMAGE_SRC = "res/ice.png";
	private final static float TIME = 250f;
	private boolean inMotion = false;
	private Direction currentDirection;
	private float timeSinceSlide = 0f;
	
	/**
	 * Constructor
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Ice(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite Ice that is being copied
	 * @throws Exception
	 */
	public Ice(Ice thatSprite) throws Exception{
		super(thatSprite);
		this.inMotion = thatSprite.inMotion;
		this.currentDirection = thatSprite.currentDirection;
		this.timeSinceSlide = thatSprite.timeSinceSlide;
	}
	
	/* (non-Javadoc)
	 * @see Sprite#copy()
	 */
	public Ice copy() throws Exception{
		return new Ice(this);
	}
	
	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, float, GameMap)
	 */
	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		this.timeSinceSlide += delta;
		
		if(inMotion && this.timeSinceSlide >= TIME) {
			this.timeSinceSlide = 0;
			push(this.currentDirection, gameMap);
			return;
		}
		
		super.update(input, delta, gameMap);
	}
	/* (non-Javadoc)
	 * @see Block#push(GameObject.Direction, GameMap)
	 */
	@Override
	public boolean push(Direction direction, GameMap gameMap) throws Exception {
		this.currentDirection = direction;
		if(!super.push(direction, gameMap)) {
			this.inMotion = false;
		}
		else {
			this.inMotion = true;
		}
		return this.inMotion;
	}
	
	/* (non-Javadoc)
	 * @see Block#isValidMove(int, int, GameObject.Direction, GameMap)
	 */
	public boolean isValidMove(int x, int y, Direction direction, GameMap gameMap) throws Exception{
		boolean isValid = false;
		switch(gameMap.isCellBlocked(x,y)) {
			case NO:
				isValid = true;
				break;
			case BLOCK:
			case UNIT:
			case C_WALL:
			case WALL:
				break;
		}
		return isValid;
	}

}
