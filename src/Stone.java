public class Stone extends Block {
	private final static String IMAGE_SRC = "res/stone.png";
	
	public Stone(int x, int y, int xOff, int yOff) throws Exception {
		super(IMAGE_SRC, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	public Stone(String image_src, int x, int y, int xOff, int yOff) throws Exception {
		super(image_src, x, y, xOff, yOff);
		// TODO Auto-generated constructor stub
	}
	
	public Stone(Stone thatSprite) throws Exception {
		super(thatSprite);
	}
	public boolean push(Direction direction, GameMap gameMap) throws Exception {
		int newX = 0, newY = 0;
		
		//choose the new x and y values based on direction
		newX = findNewX(direction);
		newY = findNewY(direction);
		
		if (isValidMove(newX,newY,direction, gameMap)) {
			gameMap.putInCell(getX(), getY(), (GameObject)null);
			setX(newX);
			setY(newY);
			gameMap.putInCell(newX, newY, this);
			return true;
		}
		return false;
	}
	@Override
	public Sprite copy() throws Exception {
		return new Stone(this);
	}
}
