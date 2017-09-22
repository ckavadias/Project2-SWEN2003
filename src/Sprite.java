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
	private int x;
	private int y;
	private int xOffset;
	private int yOffset;
	
	//takes image_src, x, y from Loader and converts x and y into pixels
	//also takes a graphical offset to centre rendering 
	public Sprite(String image_src, int x, int y, int xOff, int yOff) 
			throws Exception {
		this.spriteImage = new Image(image_src);
		setXOffset(xOff);
		setYOffset(yOff);
		setX(x);
		setY(y);
	}
	
	public Sprite(Sprite thatSprite) throws Exception {
		setX(thatSprite.getX());
		setY(thatSprite.getY());
		setXOffset(thatSprite.getXOffset());
		setYOffset(thatSprite.getYOffset());
		this.spriteImage = thatSprite.getSpriteImage();
	}
	
	private Image getSpriteImage() throws SlickException {
		// TODO Auto-generated method stub
		return new Image(this.spriteImage.getResourceReference());
	}

	//takes x in game coordinates, confirms is within graphic boundary
	protected void setX (int x) throws Exception {  
		if(x + getXOffset() < getXOffset() || x > App.COLUMNS - getXOffset()+1) {
			throw new Exception("Invalid grid value");
		}
		else {
			this.x = x;
		}
	}
	
	//takes y in game coordinates, confirms is within graphic boundary
	protected void setY(int y) throws Exception {
		if(y + getYOffset() < getYOffset() ||  y > App.ROWS - getYOffset() +1) {
			throw new Exception("Invalid grid value");
		}
		else {
			this.y = y;
		}
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
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
	
	public int getXScreen() {
		return (this.x + this.xOffset)*App.TILE_SIZE;
	}
	
	public int getYScreen() {
		return (this.y+this.yOffset)*App.TILE_SIZE;
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
	
	public void render(Graphics g) throws SlickException {
		//render a sprite on the game board
		this.spriteImage.draw(getXScreen(), getYScreen());
	}

	public abstract Sprite copy() throws Exception;

	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		// TODO Auto-generated method stub
	}

}
