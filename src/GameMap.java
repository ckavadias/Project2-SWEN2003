//Created by Constantinos Kavadias for Project2, SWEN20003
//University of Melbourne, Student ID 664790, ckavadias@student.unimelb.edu.au
//September, 2017

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameMap {
	/**
	 * @author constantinoskavadias
	 *
	 */
	/** Enum representing all possible forms of blocking in game */
	public static enum Stopper {WALL, C_WALL, UNIT, BLOCK, NO};
	private int onTargets = 0;
	private int numTargets;
	private int numMoves = 0;
	private boolean moveMade = false;
	private boolean explosion = false;
	private boolean setup = true;
	private boolean unitContact = false;
	private MapCell[][] gameMap;
	private Door theDoor = null;
	private Player thePlayer = null;
	private ArrayList<Sprite> allSprites;
	private ArrayList<Sprite> removalList = new ArrayList<Sprite>();
	private ArrayList<Sprite> addList = new ArrayList<Sprite>();
	
	/**
	 * @param allSprites list of sprites to be input on the gameMap
	 */
	public GameMap(ArrayList<Sprite> allSprites) {
		this.allSprites = allSprites;
		initialiseMap(this.allSprites);
		//insert all sprites into Map
		loadMap(this.allSprites);
		this.setup = false;
	}
	
	/**
	 * @param gameMap GameMap that needs to be copied
	 * @throws Exception
	 */
	public GameMap(GameMap gameMap) throws Exception {
		this.allSprites = new ArrayList<Sprite>();
		
		//copy the list of sprites
		for (Sprite thatSprite : gameMap.allSprites) {
			if(!(thatSprite instanceof Explosion)) {
				this.allSprites.add(thatSprite.copy());
			}
		}
		
		//reconstruct the map
		this.allSprites.trimToSize();
		initialiseMap(this.allSprites);
		loadMap(this.allSprites);
		
		this.numMoves = gameMap.getNumMoves();
		this.onTargets = gameMap.getOnTargets();
		this.setup = false;
	}
	
	//determine if and how a cell is blocked and communicate to calling class
	/**
	 * @param x the x location of the cell being checked
	 * @param y the y location of the cell being checked
	 * @return if/how the cell is blocked
	 */
	public Stopper isCellBlocked (int x, int y){
		if(gameMap[x][y].getTile().isBlocking()) {
			if(gameMap[x][y].getTile() instanceof CrackedWall) {
				return Stopper.C_WALL;
			}
			return Stopper.WALL;
		}
		else if (gameMap[x][y].getObject() instanceof Block) {
			return Stopper.BLOCK;
		}
		else if (gameMap[x][y].getObject() instanceof Unit) {
			return Stopper.UNIT;
		}
		else {
			return Stopper.NO;
		}
	}
	
	//places or removes a GameObject from a cell and activates other consequential actions
	//note that it is guaranteed that where there is a switch there is also a door and there is
	//only one of each so the possibility of passing a null to the turnOnOff method is eliminated
	//null pointer exception may occur during copying of gameMap this was solved by using a 
	//setup trigger
	/**
	 * places an object in a cell and activated consequential actions
	 * @param x the x location of the cell in which we are inserting/removing
	 * @param y the y location of the cell in which we are inserting/removing
	 * @param object the object(possibly null) that we are inserting
	 */
	public void putInCell (int x, int y, Sprite object){
		if (object instanceof Tile) {
			gameMap[x][y].setTile((Tile)object);
			return;
		}
		else if(gameMap[x][y].getTile() instanceof Target && !this.setup) {
			if(object == null && gameMap[x][y].getObject() instanceof Block) {
				decrementOnTarget();
			}
			if(object instanceof Block) {
				incrementOnTarget();
			}
		}
		else if (gameMap[x][y].getTile() instanceof Switch && !this.setup) {
			if(object == null && gameMap[x][y].getObject() instanceof Block) {
				((Switch)gameMap[x][y].getTile()).turnOnOff(theDoor);
			}
			if(object instanceof Block) {
				((Switch)gameMap[x][y].getTile()).turnOnOff(theDoor);
			}
		}
		gameMap[x][y].setObject((GameObject)object);
	}
	
	//initialise an empty gameMap to be filled
	private void initialiseMap(ArrayList<Sprite> allSprites){
		int x = (allSprites.get(0).getXOffset()*-2) + App.COLUMNS;
		int y = (allSprites.get(0).getYOffset()*-2) + App.ROWS;
		
		gameMap = new MapCell[x][y];
		
		//initialise the gameMap
		for (int i = 0; i < x; i++) {
			for( int j = 0; j < y; j++) {
				this.gameMap[i][j] = new MapCell();
			}
		}
	}
	
	//insert all sprites into Map and count number of targets, identify the player and door
	private void loadMap(ArrayList<Sprite> allSprites) {
		int numTargets = 0;
		
		for (Sprite thisSprite : allSprites) {
			if(!(thisSprite instanceof Explosion)) {
				putInCell(thisSprite.getX(), thisSprite.getY(), thisSprite);
			}
			if (thisSprite instanceof Target) {
				numTargets++;
			}
			if(thisSprite instanceof Door) {
				this.theDoor = (Door)thisSprite;
			}
			if(thisSprite instanceof Player) {
				this.thePlayer = (Player)thisSprite;
			}
		}
		this.numTargets = numTargets;
	}
	
	//allow a GameObject to relay a push command to a block
	/**
	 * pass a movement from one GameObject to another via the gameMap
	 * @param x the x location of the desired cell
	 * @param y the y location of the desired cell
	 * @param direction the direction of the passed push
	 * @return true if the push was successful, false otherwise
	 * @throws Exception
	 */
	public boolean pushBlock(int x, int y, GameObject.Direction direction) throws Exception {
		return ((Block)this.gameMap[x][y].getObject()).push(direction, this);
	}
	
	//relay game updates to all sprites and also update list where necessary
	//ArrayList cannot be updated during iteration due to concurrent update
	//exceptions, due to this the removal and add list functionality was added
	//to occur after iteration
	/**
	 * pass the input and delta values to all sprites to use as necessary to update
	 * their position and action in the game, also update the sprite list where
	 * it has been requested a sprite is removed or added
	 * @param input keyboard input as specified in the slick library
	 * @param delta the number of miliseconds since last call
	 * @throws Exception
	 */
	public void update (Input input, float delta) throws Exception{
		boolean change = false;
		for(Sprite thisSprite : this.allSprites) {
			thisSprite.update(input, delta, this);
		}
		if(this.removalList.size() > 0) {
			this.allSprites.removeAll(this.removalList);
			this.removalList.clear();
			change = true;
		}
		if(this.addList.size() > 0) {
			this.allSprites.addAll(this.addList);
			this.addList.clear();
			
		}
		//may need to refactor map where changes to the sprites have been made
		if(change) {
			initialiseMap(this.allSprites);
			loadMap(this.allSprites);
			change = false;
		}
	}
	
	/**
	 * call render on all Sprites currently in the Sprite list in order
	 * to render the game board
	 * @param g the graphics as specified in the slick library
	 * @throws SlickException
	 */
	public void render(Graphics g) throws SlickException {
		for (Sprite thisSprite : this.allSprites) {
			thisSprite.render(g);
		}
		g.drawString("Number of moves: " + getNumMoves(), 0, 0);
	}
	
	private void incrementOnTarget() {
		onTargets++;
	} 
	
	private void decrementOnTarget () {
		onTargets--;
	}
	
	protected int getOnTargets(){
		return this.onTargets;
	}
	
	/**
	 * Increment the number of moves made in the game, called by player
	 */
	public void incrementNumMoves() {
		this.moveMade = true;
		this.numMoves++;
	} 
	
	//determine if all targets have been covered
	/**
	 * Determine if the level has reached a win state (All targets covered)
	 * @return true if in win state, false otherwise
	 */
	public boolean winState() {
		return this.numTargets == this.onTargets;
	}

	/**
	 * @return the number of moves made
	 */
	public int getNumMoves() {
		return this.numMoves;
	}
	
	//list a sprite for removal during next update run
	/**
	 * Add a sprite to a list for removal at the next update call
	 * @param thisSprite the calling sprite requesting to be removed
	 */
	public void removeSprite(Sprite thisSprite) {
		this.removalList.add(thisSprite);
		
	}
	
	//list a sprite for addition during the next update run
	/**
	 * Add a sprite to a list for addition at the next update call
	 * @param thisSprite the calling sprite requesting to be added
	 */
	public void addSprite(Sprite thisSprite) {
		this.addList.add(thisSprite);
		
	}
	
	//change gameMap state to notify of an explosion
	/**
	 * Activate the explosion tag and remove the CrackedWall at the specified tile
	 * @param x the cell in which the explosion will occur
	 * @param y the cell in which the explosion will occur
	 */
	public void explode(int x, int y) {
		removeSprite(this.gameMap[x][y].getTile());
		this.explosion = true;
		incrementNumMoves();
		
	}
	
	/**
	 *  set the explosion tag to false following an explosion
	 */
	public void explode() {
		this.explosion = false;
	}
	
	/**
	 * @return whether an explosion has occurred
	 */
	public boolean exploded() {
		return this.explosion;
	}
	
	/**
	 * @return whether a move was made at the last update call
	 */
	public boolean moveWasMade() {
		return this.moveMade;
	}
	
	/**
	 * reset moveMade tag to false at teh start of the next move cycle
	 */
	public void resetMove() {
		this.moveMade = false;
	}
	
	/**
	 * @return the x coordinate of the player
	 */
	public int getPlayerX() {
		return thePlayer.getX();
	}
	
	/**
	 * @return the y coordinate of the player
	 */
	public int getPlayerY() {
		return thePlayer.getY();
	}
	
	//determine if the player has come in contact with another unit
	/**
	 * Set a unit contact tag based on whether the current unit has passed through
	 * a player or occupies the same square as a player
	 * This is called after every successful move by a unit to check.
	 * @param unit the unit calling
	 */
	public void unitContact(Unit unit) {
		//only checking consequences of unit action, the player will be checked as a result
		//and does not need to be independently checked
		if (!(unit instanceof Player)) {
			
			//intended position of unit is the same as the last position of the player
			if(unit.getX() == this.thePlayer.getLastX() && unit.getY() == this.thePlayer.getLastY()) {
				//what will be last position of the unit is the same as the player's current
				//this means they have passed through each other, implying contact
				if(unit.getLastX() == this.thePlayer.getX() && unit.getLastY() == this.thePlayer.getY()) {
					this.unitContact = true;
				}
			}
			
			//intended position of the unit is the same as the current position of the player
			//this is an obvious cooccupation 
			if(unit.getX() == this.thePlayer.getX() && unit.getY() == this.thePlayer.getY()) {
				this.unitContact = true;
			}
		}
	}
	
	/**
	 * @return whether or not unit contact has been flagged
	 */
	public boolean unitContact() {
		return this.unitContact;
	}
}
