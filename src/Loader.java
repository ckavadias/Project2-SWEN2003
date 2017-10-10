//Adapted for use by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;

@SuppressWarnings("unused")
public class Loader {
	/** filename designation for the Player class */
	public static final String PLAYER = "PLAYER";
	/** filename designation for the Skeleton class */
	public static final String SKELETON = "SKELETON";
	/** filename designation for the Rogue class */
	public static final String ROGUE = "ROGUE";
	/** filename designation for the Mage class */
	public static final String MAGE = "MAGE";
	/** filename designation for the Stone class */
	public static final String STONE = "STONE";
	/** filename designation for the Ice class */
	public static final String ICE = "ICE";
	/** filename designation for the TNT class */
	public static final String TNT = "TNT";
	/** filename designation for the Door class */
	public static final String DOOR = "DOOR";
	/** filename designation for the Switch class */
	public static final String SWITCH = "SWITCH";
	/** filename designation for the Floor class */
	public static final String FLOOR = "FLOOR";
	/** filename designation for the Wall class */
	public static final String WALL = "WALL";
	/** filename designation for the CrackedWall class */
	public static final String C_WALL = "CRACKED";
	/** filename designation for the Target class */
	public static final String TARGET = "TARGET";
	/**location of filename in comma split array*/
	public static final int FILE = 0;
	/**location of initial x coordinate in comma split array*/
	public static final int X = 1;
	/**location of initial y coordinate in comma split array*/
	public static final int Y = 2;
	/** file where ongoing user and score information stored */
	public static final String USER_FILE = "users.txt";
	
	/**
	 * Loads the sprites from a given file.
	 * @param filename the file from which the sprites are loaded
	 * @return returns a completed ArrayList of sprites
	 * @throws Exception 
	 */
	public static ArrayList<Sprite> loadSprites(String filename) throws Exception {
		ArrayList<Sprite> allSprites = new ArrayList<Sprite>();
		float maxX = 0f, maxY = 0f;
		
		try {
			@SuppressWarnings("resource")
			BufferedReader worldFile = new BufferedReader(new FileReader(filename));
			String nextLine;
			
			try {
				nextLine = worldFile.readLine();
				
				String[] pieces = nextLine.split(",");
				int xOffset = (App.COLUMNS - Integer.parseInt(pieces[0]))/2;
				int yOffset= (App.ROWS - Integer.parseInt(pieces[1]))/2;
				
				while ((nextLine = worldFile.readLine()) != null) {
					pieces = nextLine.split(",");
					//identifies which form of sprite the line is referring to
					//and instantiates appropriately
					int x = Integer.parseInt(pieces[X]);
					int y = Integer.parseInt(pieces[Y]);
					
					maxX = x > maxX ? x : maxX;
					maxY = y > maxY ? y : maxY;
					
					//instantiate the appropriate sprite type
					switch(pieces[FILE].toUpperCase()) {
						case PLAYER:
							allSprites.add(new Player(x, y, xOffset, yOffset));
							break;
						case SKELETON:
							allSprites.add(new Skeleton(x, y, xOffset, yOffset));
							break;
						case ROGUE:
							allSprites.add(new Rogue(x, y, xOffset, yOffset));
							break;
						case MAGE:
							allSprites.add(new Mage(x, y, xOffset, yOffset));
							break;
						case STONE:
							allSprites.add(new Stone(x, y, xOffset, yOffset));
							break;
						case ICE:
							allSprites.add(new Ice(x, y, xOffset, yOffset));
							break;
						case TNT:
							allSprites.add(new TNT(x, y, xOffset, yOffset));
							break;
						case DOOR:
							allSprites.add(new Door(x, y, xOffset, yOffset));
							break;
						case SWITCH:
							allSprites.add(new Switch(x, y, xOffset, yOffset));
							break;
						case FLOOR:
							allSprites.add(new Floor(x, y, xOffset, yOffset));
							break;
						case WALL:
							allSprites.add(new Wall(x, y, xOffset, yOffset));
							break;
						case C_WALL:
							allSprites.add(new CrackedWall(x, y, xOffset, yOffset));
							break;
						case TARGET:
							allSprites.add(new Target(x, y, xOffset, yOffset));
							break;
						default:
							throw new Exception("SpriteException");
					}
				}
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		allSprites.trimToSize();
		return allSprites;
	}
	
	public static User loadUser(String name){
		User thisUser = null;
		boolean found = false;
		
		try {
			BufferedReader userFile = new BufferedReader(new FileReader(USER_FILE));
			String nextLine;
			
			try {				
				while ((nextLine = userFile.readLine()) != null) {
					String[] userInfo = nextLine.split(",");
					
					if(userInfo[FILE].equals(name)) {
						double highScore = Double.parseDouble(userInfo[X]);
						thisUser = new User(userInfo[FILE], highScore);
						found = true;
						break;
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(!found) {
			thisUser = new User(name, 0);
		}
		return thisUser;
	}
}
