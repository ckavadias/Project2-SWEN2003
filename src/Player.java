//Created by Constantinos Kavadias for Project1, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//August, 2017

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

@SuppressWarnings("unused")
public class Player extends Unit{
	private final static String IMAGE_SRC = "res/player_left.png";
	private int lastX;
	private int lastY;

	
	public Player(int x, int y, int xOff, int yOff) 
			throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		this.lastX = getX();
		this.lastY = getY();
	}
	
	//respond to directional key input
	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		
		if(input.isKeyPressed(Input.KEY_UP)) {
			move(Direction.UP, gameMap);
		}
		else if(input.isKeyPressed(Input.KEY_DOWN)) {
			move(Direction.DOWN, gameMap);
		}
		else if(input.isKeyPressed(Input.KEY_LEFT)) {
			move(Direction.LEFT, gameMap);
		}
		else if(input.isKeyPressed(Input.KEY_RIGHT)) {
			move(Direction.RIGHT, gameMap);
		}
	}
	
	private void checkUpdate(ArrayList<Sprite> allWalls) throws Exception {
		//determine if a player is too close to any walls by updating position and
		//terminate update if so
		for (Sprite thisWall : allWalls) {
			if (isCloseTo(thisWall)) {
				resetPos();
				return;
			}
		}
	}
	private void resetPos() throws Exception {
		setY(this.lastY);
		setX(this.lastX);
	}

}
