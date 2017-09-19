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
	private float lastX;
	private float lastY;

	
	public Player(float x, float y, int xOff, int yOff) 
			throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		this.lastX = getX();
		this.lastY = getY();
	}
	
	//respond to directional key input
	public void update(Input input, int delta, ArrayList<Sprite> allWalls) throws Exception {
		this.lastX = getX();
		this.lastY = getY();
		
		if(input.isKeyPressed(Input.KEY_UP)) {
			setY(getY() - 1);
		}
		else if(input.isKeyPressed(Input.KEY_DOWN)) {
			setY(getY() + 1);
		}
		else if(input.isKeyPressed(Input.KEY_LEFT)) {
			setX(getX() - 1);
		}
		else if(input.isKeyPressed(Input.KEY_RIGHT)) {
			setX(getX() + 1);
		}
		checkUpdate(allWalls);
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
	
	public void render(Graphics g) {
		//render a sprite on the game board
		super.render(g);
	}

	@Override
	public void move(float x, float y, GameMap map) {
		// TODO Auto-generated method stub
		
	}

}
