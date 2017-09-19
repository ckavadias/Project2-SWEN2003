import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameMap {
	public enum Stopper {WALL, UNIT, BLOCK, NO};
	private int onTargets = 0;
	private MapCell[][] gameMap;
	private Player player;
	private Skeleton skeleton;
	private Rogue rogue;
	private Mage mage;
	private ArrayList<Ice> iceBlocks;
	
	public GameMap(ArrayList<Sprite> allSprites) {
		// TODO Auto-generated constructor stub
	}
	public Stopper isCellBlocked (float x, float y){
		return Stopper.NO;
	}
	public void putInCell (float x, float y, GameObject object){
	}
	public void update (Input input, float delta ){
	}
	public void incrementOnTarget() {
	} 
	public void decrementOnTarget () {
	}

}
