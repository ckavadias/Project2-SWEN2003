//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import org.newdawn.slick.Input;

public class Skeleton extends Unit {
	private final static String IMAGE_SRC = "res/skull.png";
	private Direction currentDirection = Direction.UP;
	private final float SECOND = 1000f;
	private int timeSinceMove = 0;
	
	/**
	 * Constructor
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Skeleton(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite Skeleton that is being copied
	 * @throws Exception
	 */
	public Skeleton(Skeleton thatSprite) throws Exception{
		super(thatSprite);
		this.timeSinceMove = thatSprite.timeSinceMove;
		this.currentDirection = thatSprite.currentDirection;
	}
	
	/* (non-Javadoc)
	 * @see Sprite#copy()
	 */
	public Skeleton copy() throws Exception{
		return new Skeleton(this);
	}
	
	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, float, GameMap)
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		//determine if a second has passed, knowing that delta is measured in milliseconds since last 
		//call to update
		this.timeSinceMove+=delta;
		
		if(this.timeSinceMove >= SECOND){
			this.timeSinceMove = 0;
			if(!move(currentDirection, gameMap)){
				switch (this.currentDirection) {
					case UP:
						this.currentDirection = GameObject.Direction.DOWN;
						break;
					case DOWN:
						this.currentDirection = GameObject.Direction.UP;
						break;
				}
				move(currentDirection, gameMap);
			}
		}
		
	}

}
