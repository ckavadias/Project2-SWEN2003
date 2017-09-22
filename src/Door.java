//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Door extends Tile {
	private final static String IMAGE_SRC = "res/door.png";
	private boolean isOpen = false;
	public Door(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Door(Door thatSprite) throws Exception{
		super(thatSprite);
		this.isOpen = !thatSprite.isBlocking();
	}
	
	public Door copy() throws Exception{
		return new Door(this);
	}
	
	public void openClose() {
		this.isOpen = !this.isOpen;
	}
	@Override
	public boolean isBlocking() {
		return !this.isOpen;
	}
	
	public void render(Graphics g) throws SlickException {
		if(this.isOpen) {
			return;
		}
		super.render(g);
	}

}
