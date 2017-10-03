//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import org.newdawn.slick.Input;

public class Mage extends Unit {
	private final static String IMAGE_SRC = "res/mage.png";
	private final static int POS = 1;
	private final static int NEG = -1;
	/**
	 * Constructor
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Mage(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite Mage that is being copied
	 * @throws Exception
	 */
	public Mage(Mage thatSprite) throws Exception{
		super(thatSprite);
	}
	
	public Mage copy() throws Exception{
		return new Mage(this);
	}
	
	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, float, GameMap)
	 */
	@Override
	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		if(!gameMap.moveWasMade()) {
			return;
		}
		
		//implement algorithm1
		int playerX = gameMap.getPlayerX(), playerY = gameMap.getPlayerY();
		int distX = playerX - getX(), distY = playerY - getY();
		Direction direction = null;
		
		if(Math.abs(distX) > Math.abs(distY)) {
			switch(distX/Math.abs(distX)) {
				case NEG:
					direction = Direction.LEFT;
					break;
				case POS:
					direction = Direction.RIGHT;
					break;
			}
		}
		else {
			switch(distY/Math.abs(distY)) {
				case NEG:
					direction = Direction.UP;
					break;
				case POS:
					direction = Direction.DOWN;
					break;
			}
		}
		move(direction, gameMap);
	}

}
