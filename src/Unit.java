//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import java.util.ArrayList;
import org.newdawn.slick.Input;

public abstract class Unit extends GameObject {
	private int lastX = 0;
	private int lastY = 0;
	/**
	 * Constructor
	 * @param image_src the file path for the image
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Unit(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		setLastX(x);
		setLastY(y);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite Unit that is being copied
	 * @throws Exception
	 */
	public Unit(Unit thatSprite) throws Exception {
		super(thatSprite);
	}
	
	private void setLastY(int y) {
		this.lastY = y;	
	}

	private void setLastX(int x) {
		this.lastX = x;	
	}
	
	public int getLastX() {
		return this.lastX;
	}
	
	public int getLastY() {
		return this.lastY;
	}
	
	/**
	 * Attempts to make the specified move and returns whether it's possible or not
	 * @param direction direction in which the Unit is moving
	 * @param gameMap the current game state in which the move is being made
	 * @return returns true if move is possible, false otherwise
	 * @throws Exception
	 */
	public boolean move(Direction direction, GameMap gameMap) throws Exception {
		int newX = 0, newY = 0;
		
		//choose the new x and y values based on direction
		newX = findNewX(direction);
		newY = findNewY(direction);
		
		if (isValidMove(newX,newY,direction, gameMap)) {
			gameMap.putInCell(getX(), getY(), (GameObject)null);
			setLastX(getX());
			setLastY(getY());
			setX(newX);
			setY(newY);
			gameMap.putInCell(newX, newY, this);
			gameMap.unitContact(this);
			return true;
		}
		return false;
	}
	
	//check that the proposed move is valid given the game state and propagate
	//to other gameObjects where needed
	/* (non-Javadoc)
	 * @see GameObject#isValidMove(int, int, GameObject.Direction, GameMap)
	 */
	public boolean isValidMove(int x, int y,Direction direction, GameMap gameMap) throws Exception {
		boolean isValid = false;
		switch(gameMap.isCellBlocked(x,y)) {
			case NO:
				isValid = true;
				break;
			case UNIT:
				isValid = true;
				break;
			case BLOCK:
				isValid = gameMap.pushBlock(x,y, direction);
				break;
			case C_WALL:
			case WALL:
				break;
		}
		return isValid;
	}
}
