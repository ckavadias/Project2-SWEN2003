import org.newdawn.slick.Input;

public class Ice extends Block {
	private final static String IMAGE_SRC = "res/ice.png";
	private final static float TIME = 250f;
	private boolean inMotion = false;
	private Direction currentDirection;
	private float timeSinceSlide = 0f;
	
	public Ice(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Ice(Ice thatSprite) throws Exception{
		super(thatSprite);
	}
	
	public Ice copy() throws Exception{
		return new Ice(this);
	}
	
	public void update(Input input, float delta, GameMap gameMap) throws Exception {
		this.timeSinceSlide += delta;
		
		if(inMotion && this.timeSinceSlide >= TIME) {
			this.timeSinceSlide = 0;
			push(this.currentDirection, gameMap);
			return;
		}
		
		super.update(input, delta, gameMap);
	}
	@Override
	public boolean push(Direction direction, GameMap gameMap) throws Exception {
		this.currentDirection = direction;
		if(!super.push(direction, gameMap)) {
			this.inMotion = false;
		}
		else {
			this.inMotion = true;
		}
		return this.inMotion;
	}
	
	public boolean isValidMove(int x, int y, Direction direction, GameMap gameMap) throws Exception{
		boolean isValid = false;
		switch(gameMap.isCellBlocked(x,y)) {
			case NO:
				isValid = true;
				break;
			case BLOCK:
			case UNIT:
			case C_WALL:
			case WALL:
				break;
		}
		return isValid;
	}

}
