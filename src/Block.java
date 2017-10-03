//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

/**
 * @author constantinoskavadias
 *
 */

public abstract class Block extends GameObject {

	/**
	 * Constructor
	 * @param image_src the file path for the image
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Block(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Copy Constructor
	 * @param thatSprite Block that is being copied
	 * @throws Exception
	 */
	public Block(Block thatSprite) throws Exception {
		super(thatSprite);
	}

	//pass a directional instruction to an adjacent block
	/**
	 * Push the block in the direction provided that's a valid move
	 * @param direction direction (LEFT, RIGHT, UP, DOWN) in which the block is being pushed
	 * @param gameMap the current game state
	 * @return returns true if move is possible, false otherwise
	 * @throws Exception
	 */
	public boolean push(Direction direction, GameMap gameMap) throws Exception {
		int newX = 0, newY = 0;
		
		//choose the new x and y values based on direction
		newX = findNewX(direction);
		newY = findNewY(direction);
		
		if (isValidMove(newX,newY,direction, gameMap)) {
			gameMap.putInCell(getX(), getY(), (GameObject)null);
			setX(newX);
			setY(newY);
			gameMap.putInCell(newX, newY, this);
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see GameObject#isValidMove(int, int, GameObject.Direction, GameMap)
	 */
	public boolean isValidMove(int x, int y, Direction direction, GameMap gameMap) throws Exception{
		boolean isValid = false;
		switch(gameMap.isCellBlocked(x,y)) {
			case NO:
				isValid = true;
				break;
			case BLOCK:
				isValid = gameMap.pushBlock(x,y, direction);
				break;
			case UNIT:
			case C_WALL:
			case WALL:
				break;
		}
		return isValid;
	}

}
