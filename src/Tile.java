//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

public abstract class Tile extends Sprite {

	public Tile(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Tile(Tile thatSprite) throws Exception {
		super(thatSprite);
	}
	
	public abstract boolean isBlocking();
	
}
