import java.util.ArrayList;

import org.newdawn.slick.Input;

public abstract class GameObject extends Sprite {
	public enum Direction {LEFT, RIGHT, UP, DOWN};
	
	public GameObject(String image_src, float x, float y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void move(float x, float y, GameMap map);

}
