import java.util.ArrayList;
import org.newdawn.slick.Input;


public abstract class GameObject extends Sprite {
	public enum Direction {LEFT, RIGHT, UP, DOWN};
	
	public GameObject(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public GameObject(GameObject thatSprite) throws Exception {
		super(thatSprite);
	}
	
	//determine what x and y should be based on the travel direction
	@SuppressWarnings("unused")
	public int findNewX(Direction direction) {
		switch(direction){
			case LEFT:
				return getX() - 1;
			case RIGHT:
				return getX() + 1;
			default:
				return getX();
		}
	}
		
	@SuppressWarnings("unused")
	public int findNewY(Direction direction) {
		switch(direction){
			case UP:
				return getY() - 1;
			case DOWN:
				return getY() + 1;
			default:
				return getY();

		}
	}

	public abstract boolean isValidMove(int x, int y, Direction direction, GameMap gameMap) throws Exception;
}
