//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

@SuppressWarnings("unused")
public class Player extends Unit{
	private final static String IMAGE_SRC = "res/player_left.png";
    /**
     * Constructor
	 * @param x initial x coordinate in game coordinate format
	 * @param y initial y coordinate in game coordinate format
	 * @param xOff horizontal offset from edge of screen
	 * @param yOff vertical offset from edge of screen
	 * @throws Exception
	 */
	public Player(int x, int y, int xOff, int yOff) 
			throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
	}
	
	/**
	 * Copy Constructor
	 * @param thatSprite Player that is being copied
	 * @throws Exception
	 */
	public Player(Player thatSprite) throws Exception {
		super(thatSprite);
	}
	
	//respond to directional key input
	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, float, GameMap)
	 */
	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		boolean moved = false;
		gameMap.resetMove();
		if(input.isKeyPressed(Input.KEY_UP)) {
			moved = move(Direction.UP, gameMap);
		}
		else if(input.isKeyPressed(Input.KEY_DOWN)) {
			moved = move(Direction.DOWN, gameMap);
		}
		else if(input.isKeyPressed(Input.KEY_LEFT)) {
			moved = move(Direction.LEFT, gameMap);
		}
		else if(input.isKeyPressed(Input.KEY_RIGHT)) {
			moved = move(Direction.RIGHT, gameMap);
		}
	
		if(moved) {
			gameMap.incrementNumMoves();
		}
	}
	
	/* (non-Javadoc)
	 * @see Sprite#render(org.newdawn.slick.Graphics)
	 */
	public void render(Graphics g) throws SlickException {
		super.render(g);
	}
	
	/* (non-Javadoc)
	 * @see Sprite#copy()
	 */
	@Override
	public Sprite copy() throws Exception {
		return new Player(this);
	}

}
