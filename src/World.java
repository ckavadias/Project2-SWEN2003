//Adapted for use by Constantinos Kavadias for Project1, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//August, 2017

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

@SuppressWarnings("unused")
public class World {	
	private final int MAX_LEVEL = 5;
	private final int START = 0;
	private final String DIR = "res/levels/";
	private final String EXT = ".lvl";
	private GameMap gameMap;
	
	private PriorityQueue<GameMap> pastStates = new PriorityQueue<GameMap>(
			new Comparator<GameMap>() {
				public int compare(GameMap map1, GameMap map2) {
					return map2.getNumMoves() - map1.getNumMoves();
				}
			});
	
	private int currentLevel = 1;
	private int numMoves = 0;
	private Player player;
	
	//loads in all sprites available from the level while also creating 
	//more specific lists for the moment just walls to aid in updating
	public World() throws Exception {
		//load sprites from csv file and identify player
		this.pastStates.add(new GameMap(loadLevel()));
		this.gameMap = new GameMap(pastStates.peek());
	}
	
	public void update(Input input, int delta) throws Exception {
		if(this.currentLevel < MAX_LEVEL) {
			//undo the most recent move
			if(input.isKeyPressed(Input.KEY_U)) {
				undoMove();
			}
			//restart the level
			else if(input.isKeyPressed(Input.KEY_R)) {
				restartLevel();
			}
			//execute all other moves
			else{
				this.gameMap.update(input, delta);
				recordState();
			}
		}
	}
	
	public void render(Graphics g) throws Exception {
		gameMap.render(g);
		if(gameMap.winState() && currentLevel < MAX_LEVEL) {
			currentLevel = currentLevel < MAX_LEVEL ? currentLevel + 1 : currentLevel;
			this.pastStates.clear();
			this.pastStates.add(new GameMap(loadLevel()));
			restartLevel();
		}
	}
	
	private ArrayList<Sprite> loadLevel() throws Exception {
		System.out.println("Loading");
		return Loader.loadSprites(DIR + currentLevel + EXT);
	}
	
	//record the state of the game after every move made 
	private void recordState() throws Exception {
		if( this.numMoves < gameMap.getNumMoves()) {
			//record the state by copying the current gameMap
			this.pastStates.add(new GameMap(gameMap));
			this.numMoves = gameMap.getNumMoves();
		}
	}
	
	private void undoMove() throws Exception {
		if (this.pastStates.size() > 1) {
			this.pastStates.poll();
			this.gameMap = new GameMap(this.pastStates.peek());
			this.numMoves = gameMap.getNumMoves();
		}
	}
	
	private void restartLevel() throws Exception {
		while (pastStates.size() > 1) {
			pastStates.poll();
		}
		this.gameMap = new GameMap(pastStates.peek());
		this.numMoves = 0;
		
	}
}
