//Adapted for use by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

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
	
	//takes image_src, x, y from Loader where x and y are game coordinates
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
		this.spriteImage = thatSprite.spriteImage;
	}

	//takes x in game coordinates, confirms is within graphic boundary
	protected void setX (int x) throws Exception {  
		if(x  < 0 || x > App.COLUMNS - getXOffset()+1) {
			throw new Exception("Invalid grid value");
		}
		else {
			this.x = x;
		}
	}
	
	//takes y in game coordinates, confirms is within graphic boundary
	protected void setY(int y) throws Exception {
		if(y  < 0 ||  y > App.ROWS - getYOffset() +1) {
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
	
	public void render(Graphics g) throws SlickException {
		//render a sprite on the game board
		this.spriteImage.draw(getXScreen(), getYScreen());
	}

	public abstract Sprite copy() throws Exception;

	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		// TODO Auto-generated method stub
	}

}
