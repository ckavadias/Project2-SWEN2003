//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Wall extends Tile {
	private final static String IMAGE_SRC = "res/wall.png";
	
	public Wall(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
	}
	
	public Wall(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
	}
	
	public Wall(Wall thatSprite) throws Exception{
		super(thatSprite);
	}
	public Sprite copy() throws Exception{
		return new Wall(this);
	}
	public boolean isBlocking() {
		return true;
	}


}
