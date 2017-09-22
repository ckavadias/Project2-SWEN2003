//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import org.newdawn.slick.Input;

public class Rogue extends Unit {
	private final static String IMAGE_SRC = "res/rogue.png";
	private Direction currentDirection = Direction.LEFT;
	
	public Rogue(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Rogue(Rogue thatSprite) throws Exception{
		super(thatSprite);
		this.currentDirection = thatSprite.currentDirection;
	}
	
	public Rogue copy() throws Exception{
		return new Rogue(this);
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		if(!gameMap.moveWasMade()) {
			return;
		}
		if(!move(this.currentDirection, gameMap)){
			switch (this.currentDirection) {
				case LEFT:
					this.currentDirection = GameObject.Direction.RIGHT;
					break;
				case RIGHT:
					this.currentDirection = GameObject.Direction.LEFT;
					break;
			}
			move(currentDirection, gameMap);
		}
		
	}

}
