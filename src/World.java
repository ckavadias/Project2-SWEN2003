//Adapted for use by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

@SuppressWarnings("unused")
public class World {	
	private final int MAX_LEVEL = 5;
	private final String DIR = "res/levels/";
	private final String EXT = ".lvl";
	private GameMap gameMap;
	private GameMap initialMap;
	
	private PriorityQueue<GameMap> pastStates = new PriorityQueue<GameMap>(
			new Comparator<GameMap>() {
				public int compare(GameMap map1, GameMap map2) {
					return map2.getNumMoves() - map1.getNumMoves();
				}
			});
	
	private int currentLevel = 0;
	private int numMoves = 0;
	private Player player;
	
	//loads in all sprites available from the level while also creating 
	public World() throws Exception {
		//initialise gameMap from loaded sprites
		this.initialMap = new GameMap(loadLevel());
		restartLevel();
	}
	
	public void update(Input input, int delta) throws Exception {
		if(this.currentLevel < MAX_LEVEL) {
			
			if(this.gameMap.unitContact()) {
				restartLevel();
			}
			//undo the most recent move
			if(input.isKeyPressed(Input.KEY_Z)) {
				undoMove();
			}
			//restart the level
			else if(input.isKeyPressed(Input.KEY_R)) {
				restartLevel();
			}
			//execute all other moves
			else if(this.gameMap.exploded()) {
				this.pastStates.clear();
				this.pastStates.add(new GameMap(this.gameMap));
				this.gameMap.explode();
			}
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
			this.initialMap = new GameMap(loadLevel());
			restartLevel();
		}
	}
	
	//retrieve loaded sprites
	private ArrayList<Sprite> loadLevel() throws Exception {
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
	
	//undo the most recent move, unless an explosion has taken place, only undo up to the move after
	//the explosion (this is a variation from the spec)
	private void undoMove() throws Exception {
		if (this.pastStates.size() > 1) {
			this.pastStates.poll();
			this.gameMap = new GameMap(this.pastStates.peek());
			this.numMoves = gameMap.getNumMoves();
		}
	}
	
	//restore the level to it's starting state
	private void restartLevel() throws Exception {
		pastStates.clear();
		pastStates.add(new GameMap(this.initialMap));
		this.gameMap = new GameMap(this.initialMap);
		this.numMoves = 0;
		
	}
}
