//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import java.util.ArrayList;
import org.newdawn.slick.Input;

public abstract class Unit extends GameObject {
	
	public Unit(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Unit(Unit thatSprite) throws Exception {
		super(thatSprite);
	}
	
	public boolean move(Direction direction, GameMap gameMap) throws Exception {
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
	
	//check that the proposed move is valid given the game state and propagate
	//to other gameObjects where needed
	public boolean isValidMove(int x, int y,Direction direction, GameMap gameMap) throws Exception {
		boolean isValid = false;
		switch(gameMap.isCellBlocked(x,y)) {
			case NO:
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
