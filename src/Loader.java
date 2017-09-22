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
	public static final String PLAYER = "PLAYER";
	public static final String SKELETON = "SKELETON";
	public static final String ROGUE = "ROGUE";
	public static final String MAGE = "MAGE";
	public static final String STONE = "STONE";
	public static final String ICE = "ICE";
	public static final String TNT = "TNT";
	public static final String DOOR = "DOOR";
	public static final String SWITCH = "SWITCH";
	public static final String FLOOR = "FLOOR";
	public static final String WALL = "WALL";
	public static final String C_WALL = "CRACKED";
	public static final String TARGET = "TARGET";
	public static final int FILE = 0;
	public static final int X = 1;
	public static final int Y = 2;
	
	/**
	 * Loads the sprites from a given file.
	 * @param filename
	 * @return
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
}
