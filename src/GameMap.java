import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameMap {
	public static enum Stopper {WALL, UNIT, BLOCK, NO};
	private int onTargets = 0;
	private MapCell[][] gameMap;
	private Player player;
	private Skeleton skeleton;
	private Rogue rogue;
	private Mage mage;
	private ArrayList<Ice> iceBlocks;
	
	public GameMap(ArrayList<Sprite> allSprites) {
		// TODO Auto-generated constructor stub
		int x = (allSprites.get(0).getXOffset()*-2) + App.COLUMNS;
		int y = (allSprites.get(0).getYOffset()*-2) + App.ROWS;
		gameMap = new MapCell[x][y];
		
		//initialise the gameMap
		for (int i = 0; i < x; i++) {
			for( int j = 0; j < y; j++) {
				this.gameMap[i][j] = new MapCell();
			}
		}
		
		//insert all sprites into Map
		loadMap(allSprites);
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
		}
		else if(object instanceof GameObject) {
			gameMap[x][y].setGameObject((GameObject)object);
		}
	}
	
	//insert all sprites into Map and determine which are units and ice
	private void loadMap(ArrayList<Sprite> allSprites) {
		for (Sprite thisSprite : allSprites) {
			putInCell(thisSprite.getX(), thisSprite.getY(), thisSprite);
			if (thisSprite instanceof Player) {
				this.player = (Player)thisSprite;
			}
		}
	}
	
	public boolean pushBlock(int x, int y, GameObject.Direction direction) throws Exception {
		return ((Block)this.gameMap[x][y].getObject()).push(direction, this);
	}
	
	public void update (Input input, float delta) throws Exception{
		this.player.update(input, delta, this);
	}
	
	public void incrementOnTarget() {
	} 
	
	public void decrementOnTarget () {
	}
}
