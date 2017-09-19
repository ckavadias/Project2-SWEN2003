//Adapted for use by Constantinos Kavadias for Project1, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//August, 2017

import org.newdawn.slick.Input;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

@SuppressWarnings("unused")
public abstract class Sprite {	
	
	private Image spriteImage;
	private float x;
	private float y;
	private int xOffset;
	private int yOffset;
	
	//takes image_src, x, y from Loader and converts x and y into pixels
	//also takes a graphical offset to centre rendering 
	public Sprite(String image_src, float x, float y, int xOff, int yOff) 
			throws Exception {
		this.spriteImage = new Image(image_src);
		setXOffset(xOff);
		setYOffset(yOff);
		setX(x + getXOffset());
		setY(y + getYOffset());
	}
	
	//takes x in pixels, confirms is within graphic boundary
	protected void setX (float x) throws Exception {  
		if(x < getXOffset() || x > App.COLUMNS - getXOffset()+1) {
			throw new Exception("Invalid grid value");
		}
		else {
			this.x = x;
		}
	}
	
	//takes y in pixels, confirms is within graphic boundary
	protected void setY(float y) throws Exception {
		if(y < getYOffset() ||  y > App.ROWS - getYOffset() +1) {
			throw new Exception("Invalid grid value");
		}
		else {
			this.y = y;
		}
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void setXOffset(int xOffset){
		this.xOffset = xOffset;
	}
	
	public void setYOffset(int yOffset) {
		this.yOffset = yOffset;
	}
	
	public int getXOffset(){
		return this.xOffset;
	}

	public int getYOffset() {
		return this.yOffset;
	}
	
	//this has been included in the Sprite class as expansions of the game in which
	//blocks can be move will require this functionality in more than just the player
	public boolean isCloseTo(Sprite otherSprite) {
		float distX = Math.abs(getX() - otherSprite.getX())*App.TILE_SIZE;
		float distY = Math.abs(getY() - otherSprite.getY())*App.TILE_SIZE;
		
		//if these conditions are met the tiles have crossed over and is Close
		if (distX < App.TILE_SIZE && distY < App.TILE_SIZE) {
			return true;
		}
		
		return false;
	}
	
	public void render(Graphics g) {
		//render a sprite on the game board, centering the sprite makes comparing closeness
		//in the isCloseTo method mathematically simpler and a better centering of the overall
		//graphics when using the stored offsets
		this.spriteImage.drawCentered(this.x*App.TILE_SIZE, this.y*App.TILE_SIZE);
	}
}
