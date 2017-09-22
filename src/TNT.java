//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

public class TNT extends Stone {
	private final static String IMAGE_SRC = "res/tnt.png";
	
	public TNT(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public TNT(TNT thatSprite) throws Exception {
		super(thatSprite);
	}
	
	//called when an explosion is triggered, creates the explosion sprite to be rendered and removes itself
	//from the gameMap so that it can't interfere with future game play
	private void explode(GameMap gameMap, Direction direction) throws Exception {
		gameMap.removeSprite(this);
		gameMap.explode(findNewX(direction), findNewY(direction));
		gameMap.addSprite(new Explosion(getX(),getY(), getXOffset(), getYOffset()));
		
	}
	
	public boolean push(Direction direction, GameMap gameMap) throws Exception {
		if(!super.push(direction, gameMap)) {
			if(gameMap.isCellBlocked(findNewX(direction), findNewY(direction)) == GameMap.Stopper.C_WALL) {
				explode(gameMap, direction);
			}
			return false;
		}
		return true;
		
	}
	
	public Sprite copy() throws Exception {
		return new TNT(this);
	}
}
