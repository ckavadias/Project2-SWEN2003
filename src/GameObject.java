//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import java.util.ArrayList;
import org.newdawn.slick.Input;

public abstract class GameObject extends Sprite {
	/** Direction indicator */
	public enum Direction {LEFT, RIGHT, UP, DOWN};
	
	/**
	 * Constructor
	 * @param image_src the file path for the image
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public GameObject(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite GameObject that is being copied
	 * @throws Exception
	 */
	public GameObject(GameObject thatSprite) throws Exception {
		super(thatSprite);
	}
	
	//determine what x and y should be based on the travel direction
	/**
	 * Determine how the x coordinate would change if travelling in a given Direction
	 * @param direction direction in which the GameObject intends to travel
	 * @return the new x value if the move is deemed valid
	 */
	@SuppressWarnings("unused")
	public int findNewX(Direction direction) {
		switch(direction){
			case LEFT:
				return getX() - 1;
			case RIGHT:
				return getX() + 1;
			default:
				return getX();
		}
	}
	
	/**
	 * Determine how the y coordinate would change if travelling in a given Direction
	 * @param direction direction in which the GameObject intends to travel
	 * @return the new y value if the move is deemed valid
	 */
	@SuppressWarnings("unused")
	public int findNewY(Direction direction) {
		switch(direction){
			case UP:
				return getY() - 1;
			case DOWN:
				return getY() + 1;
			default:
				return getY();

		}
	}

	/**
	 * This method takes coordinate representing a tile, the direction of intended travel
	 * and the current gameMap and discerns whether it is possible to move to that square
	 * based on consequential moves and what is contained at that location
	 * @param x x coordinate of tile intended to move to
	 * @param y y coordinate of tile intended to move to
	 * @param direction direction of travel
	 * @param gameMap the current game state
	 * @return true if move is possible, false otherwise
	 * @throws Exception
	 */
	public abstract boolean isValidMove(int x, int y, Direction direction, GameMap gameMap) throws Exception;
}
