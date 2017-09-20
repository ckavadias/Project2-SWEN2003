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
	private int numMoves = 0;

	
	public Player(int x, int y, int xOff, int yOff) 
			throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
	}
	
	//respond to directional key input
	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		boolean moved = false;
		
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
		this.numMoves++;
		}
	}
	
	public void render(Graphics g) {
		g.drawString("Number of moves: " + this.numMoves, 0, 0);
		super.render(g);
	}
	public int getNumMoves() {
		return this.numMoves;
	}

}
