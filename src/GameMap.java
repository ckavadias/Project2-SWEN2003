import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameMap {
	public static enum Stopper {WALL, C_WALL, UNIT, BLOCK, NO};
	private int onTargets = 0;
	private int numTargets;
	private int numMoves = 0;
	private boolean explosion = false;
	private MapCell[][] gameMap;
	private ArrayList<Sprite> allSprites;
	private ArrayList<Sprite> removalList = new ArrayList<Sprite>();
	private ArrayList<Sprite> addList = new ArrayList<Sprite>();
	
	public GameMap(ArrayList<Sprite> allSprites) {
		this.allSprites = allSprites;
		initialiseMap(this.allSprites);
		//insert all sprites into Map
		loadMap(this.allSprites);
	}
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
	}
	
	//determine if and how a cell is blocked and communicate to calling class
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
		else {
			return Stopper.NO;
		}
	}
	
	public void putInCell (int x, int y, Sprite object){
		if (object instanceof Tile) {
			gameMap[x][y].setTile((Tile)object);
			return;
		}
		else if(gameMap[x][y].getTile() instanceof Target) {
			if(object == null && gameMap[x][y].getObject() instanceof Block) {
				decrementOnTarget();
			}
			if(object instanceof Block) {
				incrementOnTarget();
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
	
	//insert all sprites into Map and count number of targets
	private void loadMap(ArrayList<Sprite> allSprites) {
		int numTargets = 0;
		
		for (Sprite thisSprite : allSprites) {
			if(!(thisSprite instanceof Explosion)) {
				putInCell(thisSprite.getX(), thisSprite.getY(), thisSprite);
			}
			if (thisSprite instanceof Target) {
				numTargets++;
			}
		}
		this.numTargets = numTargets;
	}
	
	//allow a GameObject to relay a push command to a block
	public boolean pushBlock(int x, int y, GameObject.Direction direction) throws Exception {
		return ((Block)this.gameMap[x][y].getObject()).push(direction, this);
	}
	
	//relay game updates to all sprites and also update list where necessary
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
			System.out.println("REFACTOR");
			initialiseMap(this.allSprites);
			loadMap(this.allSprites);
		}
	}
	
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
	
	public void incrementNumMoves() {
		this.numMoves++;
	} 
	
	//determine if all targets have been covered
	public boolean winState() {
		return this.numTargets == this.onTargets;
	}

	public int getNumMoves() {
		return this.numMoves;
	}
	
	//list a sprite for removal during next update run
	public void removeSprite(Sprite thisSprite) {
		this.removalList.add(thisSprite);
		
	}
	
	//list a sprite for addition during the next update run
	public void addSprite(Sprite thisSprite) {
		this.addList.add(thisSprite);
		
	}
	
	//change gameMap state to notify of an explosion
	public void explode(int x, int y) {
		removeSprite(this.gameMap[x][y].getTile());
		//this.explosion = true;
		incrementNumMoves();
		
	}
	public boolean exploded() {
		return this.explosion;
	}
}
