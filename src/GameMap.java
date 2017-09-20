import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameMap {
	public static enum Stopper {WALL, UNIT, BLOCK, NO};
	private int onTargets = 0;
	private int numTargets;
	private MapCell[][] gameMap;
	private Player player;
	private Skeleton skeleton;
	private Rogue rogue;
	private Mage mage;
	private ArrayList<Ice> iceBlocks;
	protected ArrayList<Sprite> allSprites;
	
	public GameMap(ArrayList<Sprite> allSprites) {
		this.allSprites = allSprites;
		initialiseMap(this.allSprites);
		//insert all sprites into Map
		loadMap(this.allSprites);
	}
	public GameMap(GameMap gameMap) {
		this.allSprites = new ArrayList<Sprite>();
		
		//copy the list of sprites
		for (Sprite thatSprite : gameMap.allSprites) {
			this.allSprites.add(thatSprite.copy());
		}
		
		//reconstruct the map
		this.allSprites.trimToSize();
		initialiseMap(this.allSprites);
		loadMap(this.allSprites);
		
		this.onTargets = gameMap.getOnTargets();
	}
	
	public Stopper isCellBlocked (int x, int y){
		if(gameMap[x][y].getTile().isBlocking()) {
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
		gameMap[x][y].setGameObject((GameObject)object);
	}
	
	//initialise and empty gameMap to be filled
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
	
	//insert all sprites into Map and determine which are units and ice
	private void loadMap(ArrayList<Sprite> allSprites) {
		int numTargets = 0;
		this.iceBlocks = new ArrayList<Ice>();
		
		for (Sprite thisSprite : allSprites) {
			putInCell(thisSprite.getX(), thisSprite.getY(), thisSprite);
			if (thisSprite instanceof Player) {
				this.player = (Player)thisSprite;
			}
			else if (thisSprite instanceof Skeleton) {
				this.skeleton = (Skeleton)thisSprite;
			}
			else if (thisSprite instanceof Mage) {
				this.mage = (Mage)thisSprite;
			}
			else if (thisSprite instanceof Rogue) {
				this.rogue = (Rogue)thisSprite;
			}
			else if (thisSprite instanceof Ice) {
				this.iceBlocks.add((Ice)thisSprite);
			}
			else if (thisSprite instanceof Target) {
				numTargets++;
			}
		}
		this.iceBlocks.trimToSize();
		this.numTargets = numTargets;
	}
	
	public boolean pushBlock(int x, int y, GameObject.Direction direction) throws Exception {
		return ((Block)this.gameMap[x][y].getObject()).push(direction, this);
	}
	
	public void update (Input input, float delta) throws Exception{
		this.player.update(input, delta, this);
	}
	
	public void render(Graphics g) {
		for (Sprite thisSprite : this.allSprites) {
			thisSprite.render(g);
		}
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
	
	public boolean winState() {
		return this.numTargets == this.onTargets;
	}

	public int getNumMoves() {
		return this.player.getNumMoves();
	}
}
