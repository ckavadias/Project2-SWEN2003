//Created by Constantinos Kavadias for Project1, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//August, 2017

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Wall extends Tile {
	private final static String IMAGE_SRC = "res/wall.png";
	
	public Wall(float x, float y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
	}

	public boolean isBlocking() {
		return true;
	}


}
