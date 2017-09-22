//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CrackedWall extends Wall {
	private final static String IMAGE_SRC = "res/cracked_wall.png";
	
	public CrackedWall(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}

	public CrackedWall(CrackedWall thatSprite) throws Exception {
		super(thatSprite);
	}

	public Sprite copy() throws Exception {
		return new CrackedWall(this);
	}

	public void explode(GameMap gameMap) {
		gameMap.removeSprite(this);
		
	}
	
}
